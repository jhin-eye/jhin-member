package com.yanoos.member.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/test/upload")
@Slf4j
public class FileUploadController {

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException, InterruptedException {
        System.out.println("📂 파일 이름: " + file.getOriginalFilename());
        System.out.println("📏 파일 크기: " + file.getSize() + " bytes");

        log.info("파일이 임시 디스크에 있음");
        Thread.sleep(5000);
        log.info("파일 읽기 ㅅ ㅣ작");
        File destination = new File("c:\\uploads\\" + file.getOriginalFilename());
        if (!destination.getParentFile().exists()) {
            destination.getParentFile().mkdirs();
        }
        file.transferTo(destination);
        return ResponseEntity.ok("파일 업로드 성공");
    }
}
