package com.smith.controller;

import com.smith.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/compare")
    public String compareFiles(MultipartFile file1, MultipartFile file2, Model model) {
        try {
            // Save the uploaded files to a temporary directory
            File tempFile1 = File.createTempFile("file1", ".xlsx");
            file1.transferTo(tempFile1);

            File tempFile2 = File.createTempFile("file2", ".xlsx");
            file2.transferTo(tempFile2);

            boolean areSimilar = excelService.compareExcelFiles(tempFile1.getAbsolutePath(), tempFile2.getAbsolutePath());

            model.addAttribute("areSimilar", areSimilar);
        } catch (IOException e) {
            model.addAttribute("error", "An error occurred while processing the files.");
        }
        return "result";
    }
}
