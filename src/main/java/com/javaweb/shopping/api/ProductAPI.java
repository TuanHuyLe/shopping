package com.javaweb.shopping.api;

import com.javaweb.shopping.converter.ProductConverter;
import com.javaweb.shopping.dto.ProductDTO;
import com.javaweb.shopping.entity.CategoryEntity;
import com.javaweb.shopping.entity.ProductEntity;
import com.javaweb.shopping.entity.UserEntity;
import com.javaweb.shopping.payload.response.MessageResponse;
import com.javaweb.shopping.repository.IUserRepository;
import com.javaweb.shopping.service.ICategoryService;
import com.javaweb.shopping.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/shopping")
public class ProductAPI {
    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private IUserRepository userRepository;

    @GetMapping(value = "/products")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DEVELOP') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findAll() {
        List<ProductEntity> products = productService.findAll();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ProductDTO> productDTOs = new ArrayList<>();
        products.forEach(x -> productDTOs.add(productConverter.toDTO(x)));
        return ResponseEntity.ok(productDTOs);
    }

    @GetMapping(value = "/product/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DEVELOP') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findOne(@PathVariable("id") Integer id) {
        Optional<ProductEntity> productEntity = productService.findById(id);
        if (productEntity.isPresent()) {
            return ResponseEntity.ok(productConverter.toDTO(productEntity.get()));
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/product")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DEVELOP') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findById(@RequestParam("id") Integer id) {
        Optional<ProductEntity> productEntity = productService.findById(id);
        if (productEntity.isPresent()) {
            return ResponseEntity.ok(productConverter.toDTO(productEntity.get()));
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/product")
    @PreAuthorize("hasRole('ROLE_DEVELOP') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> create(@RequestBody ProductDTO productDTO) {
        try {
            Optional<CategoryEntity> categoryEntity = categoryService.findById(productDTO.getCategoryId());
            ProductEntity productEntity = productConverter.toEntity(productDTO);
            if (!categoryEntity.isPresent()) {
                return ResponseEntity.badRequest().body(new MessageResponse("Category not exists!"));
            }
            productEntity.setCategory(categoryEntity.get());
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Optional<UserEntity> userEntity = userRepository.findByUsername(username);
            if (!userEntity.isPresent()) {
                return ResponseEntity.badRequest().body(new MessageResponse("User not exists!"));
            }
            productEntity.setUser(userEntity.get());
            productEntity = productService.save(productEntity);
            if (productEntity != null) {
                return new ResponseEntity<>(productConverter.toDTO(productEntity), HttpStatus.CREATED);
            }
            return ResponseEntity.badRequest().body(new MessageResponse("Create failed! Product is exists"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/product")
    @PreAuthorize("hasRole('ROLE_DEVELOP') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@RequestBody ProductDTO productDTO) {
        try {
            Optional<CategoryEntity> categoryEntity = categoryService.findById(productDTO.getCategoryId());
            ProductEntity productEntity = productConverter.toEntity(productDTO);
            if (!categoryEntity.isPresent()) {
                return ResponseEntity.badRequest().body(new MessageResponse("Category not exists!"));
            }
            productEntity.setCategory(categoryEntity.get());
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Optional<UserEntity> userEntity = userRepository.findByUsername(username);
            if (!userEntity.isPresent()) {
                return ResponseEntity.badRequest().body(new MessageResponse("User not exists!"));
            }
            productEntity.setUser(userEntity.get());
            productEntity = productService.save(productEntity);
            return new ResponseEntity<>(productConverter.toDTO(productEntity), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
