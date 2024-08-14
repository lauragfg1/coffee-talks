package com.example.germanTalks;

import com.example.germanTalks.model.Language;
import com.example.germanTalks.repository.LanguageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class GermanTalksApplicationTests {

	@Autowired
	private LanguageRepository languageRepository;

	@Test
	public void testFindAllLanguages() {
		List<Language> languages = languageRepository.findAll();
		assertFalse(languages.isEmpty(), "The language list should not be empty");
	}
}

