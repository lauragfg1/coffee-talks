package com.example.germanTalks.controller;

import java.util.List;
import com.example.germanTalks.model.Language;
import com.example.germanTalks.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/language")
@CrossOrigin(origins = "http://localhost:3000")
public class LanguageController {

    @Autowired
    private LanguageService languageService;

    @GetMapping("/getAll")
    public List<Language> getLanguages() {
        System.out.println(languageService.getAllLanguages().toString());

        return languageService.getAllLanguages();
    }
}