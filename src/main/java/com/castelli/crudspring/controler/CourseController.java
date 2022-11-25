package com.castelli.crudspring.controler;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    
    //exibir lista de cursos 
    @GetMapping
    public List<Course> list() {
        return courseRepository.findAll();
    }


    //buscar curso por id 
    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable Long id) {
        return courseRepository.findById(id)
        .map(recordFound -> ResponseEntity.ok().body(recordFound))
        .orElse(ResponseEntity.notFound().build());
    }

    //salvar curso 
    @PostMapping //Course é o meu model aqui do spring
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course create(@RequestBody Course course) {
        // System.out.println(course.getName());
        // System.out.println(course.getCategory());
        return courseRepository.save(course);
        // return ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));
    }

    //editar curso
    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course course){
        return courseRepository.findById(id)
        .map(recordFound -> {
            recordFound.setName(course.getName());
            recordFound.setCategory(course.getCategory());

            Course updated = courseRepository.save(recordFound);
            return ResponseEntity.ok().body(updated);
        }) //se o curso existe faz algo.
        .orElse(ResponseEntity.notFound().build()); //se o curso não existe retorna um notFound
    }

    //deletar curso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return courseRepository.findById(id)
        .map(recordFound -> {
            courseRepository.deleteById(id);
            return ResponseEntity.noContent().<Void>build();
        })
        .orElse(ResponseEntity.notFound().build());
        
    }
}
