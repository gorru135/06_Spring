package edu.kh.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.kh.todo.model.service.TodoService;

@Controller
@RequestMapping("todo")
public class TodoController {

	@Autowired
	private TodoService service;
	
	@PostMapping("add")
	public String addTodo(
				@RequestParam("todoTitle")String todoTitle,
				@RequestParam("todoContent")String todoContent) {
		
		int result = service.addTodo(todoTitle,todoContent);
		
		if(result > 0) {
			
		}
		
		return "todo/add";
	}
}
