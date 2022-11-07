package com.gardie.webapptango.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gardie.webapptango.model.Lesson;
import com.gardie.webapptango.repository.LessonProxy;

import lombok.Data;

@Data
@Service
public class LessonService {

    @Autowired
    private LessonProxy lessonProxy;

    public Lesson getLesson(final int id) {
        return lessonProxy.getLesson(id);
    }

    public Iterable<Lesson> getLessons() {
        return lessonProxy.getLessons();
    }

    public void deleteLesson(final int id) {
    	lessonProxy.deleteLesson(id);;
    }

     public Lesson saveLesson(Lesson lesson) {
    	 Lesson savedLesson;

        if(lesson.getId() == null) {
            // Si l'id est nul, alors c'est une nouvelle leçon.
        	savedLesson = lessonProxy.createLesson(lesson);
        } else {
        	savedLesson = lessonProxy.updateLesson(lesson);
        }
        
        return savedLesson;
    }


}
