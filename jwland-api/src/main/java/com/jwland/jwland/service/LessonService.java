package com.jwland.jwland.service;

import com.jwland.jwland.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LessonService {

    private final LessonRepository lessonRepository;

}
