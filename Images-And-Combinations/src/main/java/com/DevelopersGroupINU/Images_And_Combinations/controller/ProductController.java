package com.DevelopersGroupINU.Images_And_Combinations.controller;

import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.ProductCreateDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.ProductUpdateDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.responseDtos.ProductViewDto;
import com.DevelopersGroupINU.Images_And_Combinations.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {

    private final ProductService productService;
    private static final String UPLOAD_DIR = "uploads/";

    @GetMapping("/{id}")
    public ResponseEntity<ProductViewDto> findById(@PathVariable Long id) {
        var product = productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("")
    public List<ProductViewDto> findAll() {
        return productService.findAll();
    }

    @PostMapping("")
    public ResponseEntity<Void> save(@RequestBody ProductCreateDto productCreateDto) {
        if (productCreateDto.getCategoryId() == null) {
            return ResponseEntity.badRequest().build();
        }
        productService.save(productCreateDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductViewDto> update(@RequestBody ProductUpdateDto productUpdateDto, @PathVariable Long id) {
        var nesne = productService.update(productUpdateDto, id);
        return ResponseEntity.ok(nesne);
    }

    @PostMapping("/file/{id}")
    public ResponseEntity<Boolean> fileUpdate(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(false);
        }
        Boolean sonuc = productService.saveImg(file, id);
        return sonuc
                ? ResponseEntity.ok(true)
                : ResponseEntity.status(500).body(false);
    }

    @GetMapping("/file/{name}")
    public ResponseEntity<Resource> getFileByName(@PathVariable("name") String name) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR).resolve(name).normalize();

            if (!Files.exists(filePath)) {
                return ResponseEntity.notFound().build();
            }

            Resource resource = new UrlResource(filePath.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.notFound().build();
            }

            String mimeType = Files.probeContentType(filePath);
            if (mimeType == null) {
                mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(mimeType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}
