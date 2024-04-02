package com.oguzhan_gundogan.whitegoods_project.controller;


import com.oguzhan_gundogan.whitegoods_project.entity.Product;
import com.oguzhan_gundogan.whitegoods_project.Service.productservice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final productservice productService;

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Product saveProduct(@RequestPart("file") MultipartFile file,
                               @RequestPart("product") Product product) {
        return productService.saveProduct(file, product);
    }

    @GetMapping("/category/{categoryId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<Product>> getProductList(@PathVariable(value = "categoryId") Long categoryId) {
        return new ResponseEntity<>(productService.getProductList(categoryId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public Product getProduct(@PathVariable(value = "id") Long id) {
        return productService.getProductById(id);
    }
    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @PutMapping("/deactive/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deactiveProduct(@PathVariable(value = "id") Long id) {
        productService.deactiveProduct(id);
    }

    @PutMapping("/active/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void activeProduct(@PathVariable(value = "id") Long id) {
        productService.activeProduct(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable(value = "id") Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<Product>> getAllProduct(){
        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);

    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Product updateProduct(@RequestPart(value = "file",required = false) MultipartFile file,
                                 @RequestPart("product") Product product) {
        return productService.updateProduct(file, product);
    }
}
