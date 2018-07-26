package com.example.demo.controller;

import com.example.demo.database.LessonRepository;
import com.example.demo.model.Lesson;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
    public Lesson read(@PathVariable long id) {
        return this.repository.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        this.repository.deleteById(id);
    }

    @PatchMapping("/{id}")
    public Lesson update(@PathVariable long id,
                       @RequestBody Lesson lesson) {
        Lesson orignalLesson = this.repository.findById(id).get();
        orignalLesson.setTitle(lesson.getTitle());
        orignalLesson.setDeliveredOn(lesson.getDeliveredOn());
        this.repository.save(orignalLesson);
        return orignalLesson;
    }

    @GetMapping("/find/{title}")
    public Lesson findByTitle(@PathVariable String title) {
        return this.repository.findFirstByTitle(title);
    }

    @GetMapping("/between")
    public List<Lesson> findBetweenDates(@RequestParam Date date1,
                                         @RequestParam Date date2){
        return this.repository.findAllByDeliveredOnBetween(date1, date2);

    }

}
