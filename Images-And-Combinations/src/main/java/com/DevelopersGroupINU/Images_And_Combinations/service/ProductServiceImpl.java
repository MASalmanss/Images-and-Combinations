package com.DevelopersGroupINU.Images_And_Combinations.service;

import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.ProductCreateDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.ProductUpdateDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.responseDtos.ProductViewDto;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Category;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Product;
import com.DevelopersGroupINU.Images_And_Combinations.entity.ProductDetail;
import com.DevelopersGroupINU.Images_And_Combinations.mapper.ProductMapper;
import com.DevelopersGroupINU.Images_And_Combinations.repository.CategoryRepository;
import com.DevelopersGroupINU.Images_And_Combinations.repository.ProductDetailsRepository;
import com.DevelopersGroupINU.Images_And_Combinations.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
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
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    private static final String UPLOAD_DIR = "uploads/";


    @Override
    public ProductViewDto save(ProductCreateDto productCreateDto) {
        Category category = categoryRepository.findById(productCreateDto.getCategoryId()).orElseThrow(()-> new RuntimeException("Kategöri bulunamadı"));

        Product product = new Product();
        ProductDetail productDetail = new ProductDetail();
        productDetail.setBrand(productCreateDto.getBrand());
        productDetail.setPrice(productCreateDto.getPrice());
        productDetail.setSize(productCreateDto.getSize());
        productDetail.setMaterial(productCreateDto.getMaterial());
        product.setName(productCreateDto.getName());
        productDetail.setProduct(product);
        product.setProductDetail(productDetail);
        product.setCategory(category);
        productRepository.save(product);
        var view = productMapper.entityToDto(product);
        return view;
    }

    @Override
    public ProductViewDto findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Id not found"));
       return productMapper.entityToDto(product);
    }

    @Override
    public Void deleteById(Long id) {
        return null;
    }

    @Override
    public ProductViewDto update(ProductUpdateDto productUpdateDto, Long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Not found"));
        product.setName(productUpdateDto.getName());
        var productnew = productRepository.save(product);
        return productMapper.entityToDto(productnew);
    }


    @Override
    public List<ProductViewDto> findAll() {
        List<Product> products = productRepository.findAll();

        return productMapper.entityListToDtoList(products);

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
