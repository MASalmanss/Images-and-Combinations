package com.DevelopersGroupINU.Images_And_Combinations.service;

import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.ProductCreateDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.ProductUpdateDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.responseDtos.ProductViewDto;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Category;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Product;
import com.DevelopersGroupINU.Images_And_Combinations.entity.ProductDetail;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Users;
import com.DevelopersGroupINU.Images_And_Combinations.mapper.ProductMapper;
import com.DevelopersGroupINU.Images_And_Combinations.repository.CategoryRepository;
import com.DevelopersGroupINU.Images_And_Combinations.repository.ProductDetailsRepository;
import com.DevelopersGroupINU.Images_And_Combinations.repository.ProductRepository;
import com.DevelopersGroupINU.Images_And_Combinations.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductDetailsRepository productDetailsRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;
    private final UsersRepository usersRepository;

    private static final String UPLOAD_DIR = "uploads/";


    @Override
    public ProductViewDto save(ProductCreateDto productCreateDto) {
        Category category = categoryRepository.findById(productCreateDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı"));

        Product product = new Product();
        ProductDetail productDetail = new ProductDetail();

        // DTO'dan gelen veriler
        productDetail.setBrand(productCreateDto.getBrand());
        productDetail.setPrice(productCreateDto.getPrice());
        productDetail.setSize(productCreateDto.getSize());
        productDetail.setMaterial(productCreateDto.getMaterial());
        product.setName(productCreateDto.getName());
        product.setGender(productCreateDto.getGender());
        product.setAgeStage(productCreateDto.getAgeStage());

        // İlişkiler
        productDetail.setProduct(product);
        product.setProductDetail(productDetail);
        product.setCategory(category);

        productRepository.save(product);
        return productMapper.entityToDto(product);
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
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ürün bulunamadı"));

        // Güncellenebilir alanlar
        product.setName(productUpdateDto.getName());
        product.setGender(productUpdateDto.getGender());
        product.setAgeStage(productUpdateDto.getAgeStage());

        // Kaydet ve döndür
        Product updatedProduct = productRepository.save(product);
        return productMapper.entityToDto(updatedProduct);
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

    @Override
    public List<ProductViewDto> getAllProductsByPersonal(Long userId) {

        Users user = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        String userGender = user.getGender();
        String userAgeStage = user.getAgeStage();

        List<Product> filteredProducts = productRepository.findByGenderAndAgeStage(userGender, userAgeStage);

        if (filteredProducts.isEmpty()) {
            throw new RuntimeException("Bu kullanıcıya uygun ürün bulunamadı");
        }

        Collections.shuffle(filteredProducts);

        return filteredProducts.stream()
                .limit(4)
                .map(product -> productMapper.entityToDto(product))
                .collect(Collectors.toList());
    }




    private String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
