package com.example.germanTalks.service;

import com.example.germanTalks.model.Language;

import java.util.List;

public interface LanguageService {
    List<Language> getAllLanguages();
    Language findById(Integer id);
}
