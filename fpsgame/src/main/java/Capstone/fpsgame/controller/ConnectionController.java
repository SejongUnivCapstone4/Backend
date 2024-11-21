package Capstone.fpsgame.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class ConnectionController {

    private final String UNREAL_SERVER_URL = "http://<UNREAL_SERVER_IP>:<UNREAL_SERVER_PORT>/receive-fbx"; // Unreal 서버 URL
    private final String AI_SERVER_URL = "http://<AI_SERVER_IP>:<AI_SERVER_PORT>/receive-text"; // Unreal 서버 URL
    @ResponseBody
    @PostMapping("/AiToUnreal/file")
    public String fileCommunication(@RequestParam("file") MultipartFile multipartFile){
        sendFileToUnreal(multipartFile);
        return "!!";
    }
    @ResponseBody
    @PostMapping("/UnrealToAi/text")
    public String textCommunication(@RequestBody String text){
        sendTextToAi(text);
        return "!!";
    }
    private String sendFileToUnreal(MultipartFile multipartFile) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        try {
            // 파일 데이터 준비
            HttpEntity<byte[]> fileEntity = new HttpEntity<>(multipartFile.getBytes(), headers);

            // Unreal 서버로 요청
            ResponseEntity<String> response = restTemplate.postForEntity(UNREAL_SERVER_URL, fileEntity, String.class);
            return "Unreal 서버 응답: " + response.getBody();
        } catch (IOException e) {
            e.printStackTrace();
            return "파일 전송 실패: " + e.getMessage();
        }
    }
    private String sendTextToAi(String text) {
        RestTemplate restTemplate = new RestTemplate();
        // 파일 데이터 준비
        HttpEntity<String> textEntity = new HttpEntity<>(text);
        // Unreal 서버로 요청
        ResponseEntity<String> response = restTemplate.postForEntity(AI_SERVER_URL, textEntity, String.class);
        return "Unreal 서버 응답: " + response.getBody();
    }

}
