package com.oguzhan_gundogan.whitegoods_project.controller;
import com.oguzhan_gundogan.whitegoods_project.Service.categoryservice;
import com.oguzhan_gundogan.whitegoods_project.entity.Category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryCont {

    private final categoryservice categoryService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.addCategory(category), HttpStatus.OK);
    }
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<Category>> getCategoryList() {
        return new ResponseEntity<>(categoryService.getCategoryList(), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<Category> getCategory(@PathVariable(value = "categoryId") Long categoryId) {
        return new ResponseEntity<>(categoryService.getCategory(categoryId), HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteCategory(@PathVariable(value = "categoryId") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.updateCategory(category), HttpStatus.OK);
    }
}