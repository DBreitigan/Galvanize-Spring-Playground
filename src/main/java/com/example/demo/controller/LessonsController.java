package com.example.demo.controller;

import com.example.demo.database.LessonRepository;
import com.example.demo.model.Lesson;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lessons")
public class LessonsController {

    private final LessonRepository repository;

    public LessonsController(LessonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Lesson> all() {
        return this.repository.findAll();
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson) {
        return this.repository.save(lesson);
    }

    @GetMapping("/{id}")
    public Lesson get(@PathVariable long id) {
        return this.repository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        this.repository.deleteById(id);
    }

}
