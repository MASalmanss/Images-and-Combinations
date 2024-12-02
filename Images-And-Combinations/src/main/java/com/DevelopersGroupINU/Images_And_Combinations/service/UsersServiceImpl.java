package com.DevelopersGroupINU.Images_And_Combinations.service;

import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.UserCreateDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.requestDtos.UserUpdateDto;
import com.DevelopersGroupINU.Images_And_Combinations.dto.responseDtos.UserViewDto;
import com.DevelopersGroupINU.Images_And_Combinations.entity.Users;
import com.DevelopersGroupINU.Images_And_Combinations.mapper.UserMapper;
import com.DevelopersGroupINU.Images_And_Combinations.repository.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UserMapper userMapper;
    private final RestTemplate restTemplate;
    private static final String UPLOAD_DIR = "uploads/";
    private static final String API_URL_1 = "http://127.0.0.1:8000/predict/gender";
    private static final String API_URL_2 = "http://127.0.0.1:8000/predict/age";

    @Override
    public Void save(UserCreateDto userCreateDto) {
        Users users = userMapper.dtoToEntity(userCreateDto);
        users.setActive(true);
        usersRepository.save(users);
        return null;
    }

    @Override
    public UserViewDto findById(Long id) {
        Users users = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found !"));
        return userMapper.entityToDto(users);
    }

    @Override
    public Void deleteById(Long id) {
        return null;
    }

    @Override
    public UserViewDto update(UserUpdateDto userUpdateDto) {
        return null;
    }

    @Override
    public List<UserViewDto> findAll() {
        List<Users> users = usersRepository.findAll();
        return userMapper.entityListToDtoList(users);
    }

    @Override
    public Boolean saveImg(MultipartFile file, Long id) {
        try {
            // Dosyayı kaydet
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String originalFileName = file.getOriginalFilename();
            String fileExtension = getFileExtension(originalFileName);
            String uniqueFileName = "users_" + id + "_" + UUID.randomUUID() + fileExtension;

            Path path = Paths.get(UPLOAD_DIR, uniqueFileName);
            Files.write(path, file.getBytes());

            // Kullanıcıyı al
            Users user = usersRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found!"));

            // Dosyayı API'lere gönder ve yanıtları işle
            Map<String, Object> responseFromApi1 = parseJsonResponse(sendFileToExternalApi(file, API_URL_1));
            Map<String, Object> responseFromApi2 = parseJsonResponse(sendFileToExternalApi(file, API_URL_2));

            // Yanıtları işleyerek kullanıcının bilgilerini güncelle
            Map<String, Object> genderPrediction = (Map<String, Object>) responseFromApi1.get("gender_prediction");
            Map<String, Object> agePrediction = (Map<String, Object>) responseFromApi2.get("age_prediction");

            user.setGender((String) genderPrediction.get("label"));
            user.setAgeStage((String) agePrediction.get("label"));

            // Kullanıcıyı güncelle
            user.setImageName(uniqueFileName);
            usersRepository.save(user);

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String sendFileToExternalApi(MultipartFile file, String apiUrl) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new org.springframework.core.io.ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        });

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, requestEntity, String.class);

        return response.getBody();
    }

    private Map<String, Object> parseJsonResponse(String jsonResponse) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonResponse, Map.class);
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
