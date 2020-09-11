package com.javaweb.shopping.api;

import com.javaweb.shopping.converter.ProductConverter;
import com.javaweb.shopping.converter.TagConverter;
import com.javaweb.shopping.dto.ProductDTO;
import com.javaweb.shopping.dto.TagDTO;
import com.javaweb.shopping.entity.CategoryEntity;
import com.javaweb.shopping.entity.ProductEntity;
import com.javaweb.shopping.entity.TagEntity;
import com.javaweb.shopping.entity.UserEntity;
import com.javaweb.shopping.payload.response.MessageResponse;
import com.javaweb.shopping.repository.IUserRepository;
import com.javaweb.shopping.service.ICategoryService;
import com.javaweb.shopping.service.IProductImageService;
import com.javaweb.shopping.service.IProductService;
import com.javaweb.shopping.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @Autowired
    private ITagService tagService;

    @Autowired
    private TagConverter tagConverter;

    @Autowired
    private IProductImageService productImageService;

    @GetMapping(value = "/products")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DEVELOP') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findAll(@RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "page", required = false, defaultValue = "1") String page,
                                     @RequestParam(value = "limit", required = false, defaultValue = "3") String limit) {
        Pageable pageable = PageRequest.of(Integer.parseInt(page) - 1, Integer.parseInt(limit), Sort.by("createdDate")
                .descending());
        Page<ProductEntity> products;
        if (name == null) {
            products = productService.findAll(pageable);
        } else {
            products = productService.findBySlugContaining(name, pageable);
        }
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ProductDTO> productDTOs = new ArrayList<>();
        products.forEach(x -> productDTOs.add(productConverter.toDTO(x)));
        Map<String, Object> response = new HashMap<>();
        response.put("totalPages", products.getTotalPages());
        response.put("totalItems", products.getTotalElements());
        response.put("currentPage", products.getNumber() + 1);
        response.put("products", productDTOs);
        return ResponseEntity.ok(response);
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
            // add category
            Optional<CategoryEntity> categoryEntity = categoryService.findById(productDTO.getCategoryId());
            ProductEntity productEntity = productConverter.toEntity(productDTO);
            if (!categoryEntity.isPresent()) {
                return ResponseEntity.badRequest().body(new MessageResponse("Category not exists!"));
            }
            productEntity.setCategory(categoryEntity.get());
            // add current user post
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Optional<UserEntity> userEntity = userRepository.findByUsername(username);
            if (!userEntity.isPresent()) {
                return ResponseEntity.badRequest().body(new MessageResponse("User not exists!"));
            }
            productEntity.setUser(userEntity.get());
            // add tag
            List<String> strTags = productDTO.getTags();
            List<TagEntity> tagEntities = new ArrayList<>();
            if (strTags != null) {
                for (String tag : strTags) {
                    Optional<TagEntity> tagEntity = tagService.findByName(tag);
                    if (!tagEntity.isPresent()) {
                        TagDTO tagDTO = new TagDTO();
                        tagDTO.setName(tag);
                        tagEntity = tagService.save(tagConverter.toEntity(tagDTO));
                    }
                    tagEntities.add(tagEntity.get());
                }
            }
            productEntity.setTags(tagEntities);
            // save product
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
            //check exists product to update
            Optional<ProductEntity> product = productService.findById(productDTO.getId());
            if (!product.isPresent()) {
                return ResponseEntity.badRequest().body(new MessageResponse("Product not exists!"));
            }
            // check exists category
            Optional<CategoryEntity> categoryEntity = categoryService.findById(productDTO.getCategoryId());
            ProductEntity productEntity = productConverter.toEntity(productDTO);
            if (!categoryEntity.isPresent()) {
                return ResponseEntity.badRequest().body(new MessageResponse("Category not exists!"));
            }
            productEntity.setCategory(categoryEntity.get());
            // check user
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Optional<UserEntity> userEntity = userRepository.findByUsername(username);
            if (!userEntity.isPresent()) {
                return ResponseEntity.badRequest().body(new MessageResponse("User not exists!"));
            }
            productEntity.setUser(userEntity.get());
            // add tag
            List<String> strTags = productDTO.getTags();
            List<TagEntity> oldTagEntities = new ArrayList<>();
            if (strTags != null) {
                for (String tag : strTags) {
                    Optional<TagEntity> tagEntity = tagService.findByName(tag);
                    if (!tagEntity.isPresent()) {
                        TagDTO tagDTO = new TagDTO();
                        tagDTO.setName(tag);
                        tagEntity = tagService.save(tagConverter.toEntity(tagDTO));
                    }
                    oldTagEntities.add(tagEntity.get());
                }
            }
            productEntity.setTags(oldTagEntities);
            // save update product
            productEntity = productService.save(productEntity);
            return ResponseEntity.ok(productConverter.toDTO(productEntity));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/product/{id}")
    @PreAuthorize("hasRole('ROLE_DEVELOP') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> remove(@PathVariable("id") Integer id) {
        Optional<ProductEntity> productEntity = productService.remove(id);
        if (productEntity.isPresent()) {
            return ResponseEntity.ok(new MessageResponse("Delete successfully"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Delete failed"));
    }
}
