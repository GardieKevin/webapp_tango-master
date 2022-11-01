package com.gardie.webapptango.controller;

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

@Data
@Controller
public class DancerController {

	@Autowired
	private DancerService service;
	
	@GetMapping("/dancersSection")
	public String home(Model model) {
		Iterable<Dancer> listDancer = service.getDancers();
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
		Dancer d = service.getDancer(id);		
		model.addAttribute("dancer", d);	
		return "formUpdateDancer";		
	}
	
	@GetMapping("/deleteDancer/{id}")
	public ModelAndView deleteDancer(@PathVariable("id") final int id) {
		service.deleteDancer(id);
		return new ModelAndView("redirect:/dancersSection");
	}
	
	@PostMapping("/saveDancer")
	public ModelAndView saveDancer(@ModelAttribute Dancer dancer) {
		service.saveDancer(dancer);
		return new ModelAndView("redirect:/dancersSection");
	}
	
}