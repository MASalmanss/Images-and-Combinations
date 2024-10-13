package com.DevelopersGroupINU.Images_And_Combinations.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/api/file")
public class FileController {

    // Yüklemelerin kaydedileceği dizin
    private static final String UPLOAD_DIR = "uploads/";

    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Lütfen bir dosya gönderin";
        }

        try {
            // Yükleme dizininin var olup olmadığını kontrol et, yoksa oluştur
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Dosya adını UUID ile benzersiz hale getir ve uzantıyı koru
            String originalFilename = file.getOriginalFilename();
            String fileExtension = getFileExtension(originalFilename);
            String uniqueFilename = UUID.randomUUID().toString() + fileExtension;

            // Dosyayı belirtilen dizine kaydet
            Path path = Paths.get(UPLOAD_DIR + uniqueFilename);
            Files.write(path, file.getBytes());

            return "Dosya başarıyla kaydedildi: " + uniqueFilename;
        } catch (IOException e) {
            e.printStackTrace();
            return "Dosya yüklenirken hata oluştu: " + e.getMessage();
        }
    }

    // Dosya uzantısını almak için yardımcı metod
    private String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return ""; // Eğer dosya uzantısı yoksa boş bir string döndür
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
