package com.javaweb.shopping.api;

import com.javaweb.shopping.entity.IRole;
import com.javaweb.shopping.entity.RoleEntity;
import com.javaweb.shopping.entity.UserEntity;
import com.javaweb.shopping.payload.request.LoginRequest;
import com.javaweb.shopping.payload.request.SignupRequest;
import com.javaweb.shopping.payload.response.JwtResponse;
import com.javaweb.shopping.payload.response.MessageResponse;
import com.javaweb.shopping.repository.IRoleRepository;
import com.javaweb.shopping.repository.IUserRepository;
import com.javaweb.shopping.utils.JwtUtils;
import com.javaweb.shopping.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthAPI {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IRoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication, request);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        Optional<UserEntity> userEntity = userRepository.findByUsername(userDetails.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userEntity.get().getName(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Username is exists"));
        }
        UserEntity user = new UserEntity(signupRequest.getUsername(), encoder.encode(signupRequest.getPassword()));
        user.setName(signupRequest.getName());
        user.setActive(true);
        user.setStatus(1);
        List<String> strRoles = signupRequest.getRoles();
        List<RoleEntity> roles = new ArrayList<>();
        if (strRoles == null) {
            RoleEntity userRole = roleRepository.findByName(IRole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Role is not found"));
            roles.add(userRole);
        } else {
            for (String role : strRoles) {
                switch (role) {
                    case "admin":
                        RoleEntity adminRole = roleRepository.findByName(IRole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Role is not found"));
                        roles.add(adminRole);
                        break;
                    case "dev":
                        RoleEntity modRole = roleRepository.findByName(IRole.ROLE_DEVELOP)
                                .orElseThrow(() -> new RuntimeException("Role is not found"));
                        roles.add(modRole);
                        break;
                    case "edit":
                        RoleEntity editRole = roleRepository.findByName(IRole.ROLE_EDITOR)
                                .orElseThrow(() -> new RuntimeException("Role is not found"));
                        roles.add(editRole);
                        break;
                    default:
                        RoleEntity userRole = roleRepository.findByName(IRole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Role is not found"));
                        roles.add(userRole);
                        break;
                }
            }
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered success"));
    }

    @RequestMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ResponseEntity.ok(new MessageResponse("Logout successfully"));
    }
}
