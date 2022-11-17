package com.castelli.crudspring.controler;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.castelli.crudspring.model.Course;
import com.castelli.crudspring.repository.CourseRepository;

import lombok.AllArgsConstructor;

@RestController //para poder acessar a API
@RequestMapping("/api/courses") //endpoint que vai ficar exposto
@AllArgsConstructor
public class CourseController {
    
    private final CourseRepository courseRepository;
    
    @GetMapping
    public List<Course> list() {
        return courseRepository.findAll();
    }
}
