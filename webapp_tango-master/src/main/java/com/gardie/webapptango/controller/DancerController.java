package com.gardie.webapptango.controller;

import com.gardie.webapptango.model.Lesson;
import com.gardie.webapptango.service.LessonService;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gardie.webapptango.model.Dancer;
import com.gardie.webapptango.service.DancerService;
import lombok.Data;

import java.io.FilterOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Controller
public class DancerController {

	@Autowired
	private DancerService dancerService;
	@Autowired
	private LessonService lessonService;
	
	@GetMapping("/dancersSection")
	public String home(Model model) {
		Iterable<Dancer> listDancer = dancerService.getDancers();
		model.addAttribute("dancers", listDancer);
		return "dancers";
	}
	
	@GetMapping("/createDancer")
	public String createDancer(Model model) {
		Dancer d = new Dancer();
		model.addAttribute("dancer", d);
		return "formNewDancer";
	}
	
	@GetMapping("/updateDancer/{id}")
	public String updateDancer(@PathVariable("id") final int id, Model model) {
		Dancer d = dancerService.getDancer(id);
		model.addAttribute("dancer", d);	
		return "formUpdateDancer";		
	}

	@GetMapping("/unsubscribeDancer/{dancer_id}/{lesson_id}")
	public ModelAndView unsubscribeDancer(@PathVariable("dancer_id") final int dancer_id, @PathVariable("lesson_id") final int lesson_id){

		Dancer d = dancerService.getDancer(dancer_id);
		List<Lesson> dancerLessonList = d.getFollowedLessons();
		int index = 0;

		for(Lesson followedLesson : dancerLessonList){
			if (followedLesson.getId().equals(lesson_id)){
				System.out.println("BON");
				index = dancerLessonList.indexOf(followedLesson);
			} else {
				System.out.println("FAUX");
			}
		}
		System.out.println(index);
		dancerLessonList.remove(index);
		System.out.println("SUPPRIME");
		dancerService.saveDancer(d);

		return new ModelAndView("redirect:/lessonsSection");
	}

	@GetMapping("/registrationsDancer/{id}")
	public String registrationsDancer(@PathVariable("id") final int id, Model model) {

		Dancer d = dancerService.getDancer(id);
		Iterable<Lesson> allLessons = lessonService.getLessons();
		List<Lesson> dancerLessonList = d.getFollowedLessons();
		List<Lesson> updatedLessonList = new ArrayList<>();

		if(dancerLessonList.isEmpty()){
			model.addAttribute("lessons", allLessons);
		} else {
			int count = 0;
			for(Lesson lesson : allLessons){
				for(Lesson followedLesson : dancerLessonList){
					if(followedLesson.getLessonName().equals(lesson.getLessonName())){
						count ++;
					}
				}
				if(count == 0){
					updatedLessonList.add(lesson);
				}
				count = 0;
			}
			model.addAttribute("lessons", updatedLessonList );
		}
		model.addAttribute("dancer", d);

		return "registrationsDancer";
	}

	@GetMapping("registerDancer/{lesson_id}/{dancer_id}")
	public ModelAndView registerDancer(@PathVariable("lesson_id") final int lesson_id, @PathVariable("dancer_id") final int dancer_id) {

		Dancer d = dancerService.getDancer(dancer_id);
		Lesson l = lessonService.getLesson(lesson_id);

		d.getFollowedLessons().add(l);
		dancerService.saveDancer(d);
		lessonService.saveLesson(l);

		return new ModelAndView("redirect:/dancersSection");
	}
	
	@GetMapping("/deleteDancer/{id}")
	public ModelAndView deleteDancer(@PathVariable("id") final int id) {
		dancerService.deleteDancer(id);
		return new ModelAndView("redirect:/dancersSection");
	}
	
	@PostMapping("/saveDancer")
	public ModelAndView saveDancer(@ModelAttribute Dancer dancer) {
		dancerService.saveDancer(dancer);
		return new ModelAndView("redirect:/dancersSection");
	}
	
}