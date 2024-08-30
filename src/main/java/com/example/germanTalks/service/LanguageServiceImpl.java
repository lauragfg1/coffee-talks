package com.example.germanTalks.service;

import com.example.germanTalks.model.Language;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.example.germanTalks.repository.LanguageRepository;
import org.springframework.stereotype.Service;

@Service
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    @Override
    public Language findById(Integer id) {
        return languageRepository.findById(id).orElse(null);
    }
}