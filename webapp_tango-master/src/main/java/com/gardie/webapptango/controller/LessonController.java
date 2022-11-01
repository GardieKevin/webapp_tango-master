package com.gardie.webapptango.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gardie.webapptango.model.Lesson;
import com.gardie.webapptango.service.LessonService;

import lombok.Data;

@Data
@Controller
public class LessonController {

	@Autowired
	private LessonService service;
	
	@GetMapping("/lessonsSection")
	public String home(Model model) {
		Iterable<Lesson> listLesson = service.getLessons();
		model.addAttribute("lessons", listLesson);
		return "lessons";
	}
	
	@GetMapping("/createLesson")
	public String createLesson(Model model) {
		Lesson l = new Lesson();
		model.addAttribute("lesson", l);
		return "formNewLesson";
	}
	
	@GetMapping("/updateLesson/{id}")
	public String updateLesson(@PathVariable("id") final int id, Model model) {
		Lesson l = service.getLesson(id);		
		model.addAttribute("lesson", l);	
		return "formUpdateLesson";		
	}

	@GetMapping("/getLesson/{id}")
	public String getLesson(@PathVariable("id") final int id, Model model) {
		Lesson l = service.getLesson(id);
		model.addAttribute("lesson", l);
		return "aLesson";
	}
	
	@GetMapping("/deleteLesson/{id}")
	public ModelAndView deleteLesson(@PathVariable("id") final int id) {
		service.deleteLesson(id);
		return new ModelAndView("redirect:/lessonsSection");
	}
	
	@PostMapping("/saveLesson")
	public ModelAndView saveLesson(@ModelAttribute Lesson lesson) {
		service.saveLesson(lesson);
		return new ModelAndView("redirect:/lessonsSection");
	}
	
}