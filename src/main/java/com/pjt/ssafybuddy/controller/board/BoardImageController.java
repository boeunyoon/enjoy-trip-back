package com.pjt.ssafybuddy.controller.board;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/image")
public class BoardImageController {
    private final String uploadDir = Paths.get("/Users/yunbo-eun/desktop/study-spring/Spring-Workspace/file-test-storage").toString();
    @PostMapping("/upload")
    public String uploadEditorImage(@RequestParam final MultipartFile image){
        if(image.isEmpty()){
            return "";
        }
        String orgFilename = image.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String extension = orgFilename.substring(orgFilename.lastIndexOf("."));
        String saveFilename = uuid + "." + extension;
        String fileFullPath = Paths.get(uploadDir, saveFilename).toString();

        File dir = new File(uploadDir);
        if(dir.exists() == false){
            dir.mkdirs();
        }
        try{
            File uploadFile = new File(fileFullPath);
            image.transferTo(uploadFile);
            return saveFilename;
        }catch (Exception e){
            e.printStackTrace();
            throw  new RuntimeException(e);
        }
    }
    @GetMapping(value = "/print", produces = {MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public byte[] printEditorImage(@RequestParam final String filename){
        //업로드된 파일의 전체 경로
        String fileFullPath = Paths.get(uploadDir, filename).toString();
        File uploadFile = new File(fileFullPath);
        if(uploadFile.exists() == false){
            throw new RuntimeException();
        }
        try{
            byte[] imageBytes = Files.readAllBytes(uploadFile.toPath());
            return imageBytes;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
