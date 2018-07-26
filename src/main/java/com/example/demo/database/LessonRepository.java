package com.example.demo.database;

import com.example.demo.model.Lesson;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson, Long> {
    public Lesson findFirstByTitle(String title);

    public List<Lesson> findAllByDeliveredOnBetween(Date date1, Date date2);
}