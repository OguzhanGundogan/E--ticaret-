package com.oguzhan_gundogan.whitegoods_project.Service;

import com.oguzhan_gundogan.whitegoods_project.Repository.ProductRep;
import com.oguzhan_gundogan.whitegoods_project.entity.Product;
import com.oguzhan_gundogan.whitegoods_project.Repository.ProductRep;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class productservice {

    private final ProductRep productRepository;
    private static final String UPLOAD_DIR = "uploads";

    public Product updateProduct(MultipartFile file, Product product) {
        if(Objects.nonNull(file)){
            String imagePath = saveFile(file, product.getName());
            product.setImage(imagePath);
        }
        return productRepository.save(product);
    }

    public Product saveProduct(MultipartFile file, Product product) {
        String imagePath = saveFile(file, product.getName());
        product.setImage(imagePath);
        return productRepository.save(product);
    }

    public String saveFile(MultipartFile file, String productName) {
        String fileName = productName + "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
        Path uploadPath = Path.of(UPLOAD_DIR);
        Path filePath;
        try {
            Files.createDirectories(uploadPath);
            filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return filePath.toString();
    }

    public List<Product> getProductList(Long categoryId) {
        return productRepository.findProductListByCategoryId(categoryId);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product id bulunamadı"));
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }


    public void deactiveProduct(Long id) {
        productRepository.updateStatusOfProductById(false, id);
    }

    public void activeProduct(Long id) {
        productRepository.updateStatusOfProductById(true, id);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }
}