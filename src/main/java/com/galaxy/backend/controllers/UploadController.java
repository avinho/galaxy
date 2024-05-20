package com.galaxy.backend.controllers;

import com.galaxy.backend.dtos.SaldoCorretorDTO;
import com.galaxy.backend.services.ReadExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("api/files")
@CrossOrigin(origins = "*")
public class UploadController {

    @Autowired
    private ReadExcel excelService;

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    @PostMapping
    public ResponseEntity<List<SaldoCorretorDTO>> handleFileUpload(@RequestParam("file") MultipartFile file){
        File tempFile = null;
        try {
            tempFile = File.createTempFile("temp", file.getOriginalFilename());
            file.transferTo(tempFile);
            logger.info("File uploaded: {}", file.getOriginalFilename());
            return ResponseEntity.ok().body(excelService.readExcel(tempFile));
        } catch (IOException e) {
            logger.error("Error during file upload", e);
            return ResponseEntity.badRequest().build();
        } finally {
            if (tempFile != null && tempFile.exists()) {
                if (!tempFile.delete()) {
                    logger.warn("Failed to delete temporary file: {}", tempFile.getAbsolutePath());
                }
            }
        }
    }
}
