package com.DevelopersGroupINU.Images_And_Combinations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/file")
@CrossOrigin(origins = "http://localhost:5173")
public class FileController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String UPLOAD_DIR = "uploads/";
    private static final String API_URL_1 = "http://127.0.0.1:8000/predict/gender";
    private static final String API_URL_2 = "http://127.0.0.1:8000/predict/age";

    @PostMapping("/upload")
    public Map<String, Object> fileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Lütfen bir dosya gönderin");
            return errorResponse;
        }

        try {
            // 1. Dosyayı yerel olarak kaydet
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String originalFilename = file.getOriginalFilename();
            String fileExtension = getFileExtension(originalFilename);
            String uniqueFilename = UUID.randomUUID().toString() + fileExtension;

            Path path = Paths.get(UPLOAD_DIR + uniqueFilename);
            Files.write(path, file.getBytes());

            // 2. Dosyayı iki farklı API'ye gönder
            String responseFromApi1 = sendFileToExternalApi(file, API_URL_1);
            String responseFromApi2 = sendFileToExternalApi(file, API_URL_2);

            // 3. Gelen yanıtları birleştir
            Map<String, Object> combinedResponse = new HashMap<>();
            combinedResponse.put("api1Response", responseFromApi1);
            combinedResponse.put("api2Response", responseFromApi2);

            // 4. Yanıtı döndür
            return combinedResponse;

        } catch (IOException e) {
            e.printStackTrace();
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Dosya yüklenirken hata oluştu: " + e.getMessage());
            return errorResponse;
        }
    }

    private String sendFileToExternalApi(MultipartFile file, String apiUrl) throws IOException {
        // Header ve body hazırlama
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new org.springframework.core.io.ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename(); // API'de dosya ismi gerekliyse
            }
        });

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // API'ye POST isteği gönder
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, requestEntity, String.class);

        // Yanıtı döndür
        return response.getBody();
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
