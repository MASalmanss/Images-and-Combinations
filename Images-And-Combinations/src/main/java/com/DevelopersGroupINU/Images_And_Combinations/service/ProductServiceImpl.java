package com.DevelopersGroupINU.Images_And_Combinations.service;

import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.ProductCreateDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.ProductUpdateDto;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Product;
import com.DevelopersGroupINU.Images_And_Combinations.entity.ProductDetail;
import com.DevelopersGroupINU.Images_And_Combinations.repository.ProductDetailsRepository;
import com.DevelopersGroupINU.Images_And_Combinations.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductDetailsRepository productDetailsRepository;

    private static final String UPLOAD_DIR = "uploads/";


    @Override
    public Void save(ProductCreateDto productCreateDto) {
        Product product = new Product();
        ProductDetail productDetail = new ProductDetail();
        productDetail.setBrand(productCreateDto.getBrand());
        productDetail.setPrice(productCreateDto.getPrice());
        productDetail.setSize(productCreateDto.getSize());
        productDetail.setMaterial(productCreateDto.getMaterial());
        product.setName(productCreateDto.getName());
        productDetail.setProduct(product);
        product.setProductDetail(productDetail);
        productRepository.save(product);
        return null;
    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public Void deleteById(Long id) {
        return null;
    }

    @Override
    public Product update(ProductUpdateDto productUpdateDto) {
        return null;
    }


    @Override
    public List<Product> findAll() {
        return productRepository.findAll();

    }

    @Override
    public Boolean saveImg(MultipartFile file, Long id) {
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }


            String originalFileName = file.getOriginalFilename();
            String fileExtension = getFileExtension(originalFileName);


            String uniqueFileName = "product_" + id + "_" + UUID.randomUUID() + fileExtension;
           Product product = productRepository.findById(id).orElseThrow();
           product.getProductDetail().setImageName(uniqueFileName);
           productRepository.save(product);

            Path path = Paths.get(UPLOAD_DIR, uniqueFileName); // Dosya yolunu oluşturma
            Files.write(path, file.getBytes());
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    private String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
