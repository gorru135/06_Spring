package edu.kh.todo.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.todo.student.model.dto.Student;


@Controller
@RequestMapping
public class memberController {
	
	@PostMapping("student/student")
	public String memberMain(@ModelAttribute Student student) {
	
		

		return "student/student";
	}
}
