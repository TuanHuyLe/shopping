package com.javaweb.shopping.api;

import com.javaweb.shopping.converter.CategoryConvert;
import com.javaweb.shopping.dto.CategoryDTO;
import com.javaweb.shopping.entity.CategoryEntity;
import com.javaweb.shopping.payload.response.MessageResponse;
import com.javaweb.shopping.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/shopping")
public class CategoryAPI {
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private CategoryConvert categoryConvert;

    @GetMapping(value = "/category/{id}")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DEVELOP') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<Optional<CategoryDTO>> findById(@PathVariable("id") Integer id) {
        Optional<CategoryEntity> category = categoryService.findById(id);
        if (!category.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(Optional.of(categoryConvert.toDTO(category.get())));
    }

    @GetMapping(value = "/categories")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_DEVELOP') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<CategoryEntity> categories = categoryService.findAll();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        categories.forEach(c -> categoryDTOS.add(categoryConvert.toDTO(c)));
        return ResponseEntity.ok(categoryDTOS);
    }

    @PostMapping(value = "/category")
    @PreAuthorize("hasRole('ROLE_DEVELOP') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> create(@RequestBody CategoryDTO newCategory) {
        try {
            CategoryEntity categoryEntity = categoryConvert.toEntity(newCategory);
            categoryEntity = categoryService.save(categoryEntity);
            if (categoryEntity == null) {
                return ResponseEntity.badRequest().body(new MessageResponse("Create failed! Category is exists"));
            }
            return new ResponseEntity<>(categoryConvert.toDTO(categoryEntity), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/category")
    @PreAuthorize("hasRole('ROLE_EDITOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<CategoryDTO> update(@RequestBody CategoryDTO newCategory) {
        try {
            CategoryEntity categoryEntity = categoryConvert.toEntity(newCategory);
            categoryEntity = categoryService.save(categoryEntity);
            return ResponseEntity.ok(categoryConvert.toDTO(categoryEntity));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/category/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Optional<CategoryEntity> categoryEntity = categoryService.remove(id);
        if (categoryEntity.isPresent()) {
            return ResponseEntity.ok(categoryConvert.toDTO(categoryEntity.get()));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Delete failed! Id not found"));
    }
}
