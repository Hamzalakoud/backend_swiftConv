package com.biat.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@RestController
@RequestMapping("/api")
public class FileUploadMTController {

    private static final String SAVE_MT_DIR = "/Users/hamza/biat_project/fichier/fichier_MT";

    @PostMapping("/upload-mt")
    public ResponseEntity<String> uploadMtFile(@RequestParam("file") MultipartFile file) {
        System.out.println("Upload endpoint hit");
        System.out.println("Received file: " + file.getOriginalFilename());
        System.out.println("File size: " + file.getSize());
        try {
            Path dirPath = Paths.get(SAVE_MT_DIR);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            Path filePath = dirPath.resolve(file.getOriginalFilename());
            Files.write(filePath, file.getBytes(), StandardOpenOption.CREATE);

            System.out.println("File saved to: " + filePath);
            return ResponseEntity.ok("Fichier enregistré avec succès : " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Erreur lors de l'enregistrement du fichier : " + e.getMessage());
        }
    }

}
