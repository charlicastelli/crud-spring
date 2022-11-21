package com.castelli.crudspring.controler;

import java.util.List;

import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @PostMapping //Course é a meu model aqui do spring
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course create(@RequestBody Course course) {
        // System.out.println(course.getName());
        // System.out.println(course.getCategory());
        return courseRepository.save(course);
        // return ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));
    }
}
