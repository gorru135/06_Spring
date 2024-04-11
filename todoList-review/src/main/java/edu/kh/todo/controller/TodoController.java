package edu.kh.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.todo.model.dto.Todo;
import edu.kh.todo.model.service.TodoService;

@Controller
@RequestMapping("todo")
public class TodoController {

	@Autowired
	private TodoService service;
	
	@PostMapping("add")
	public String addTodo(
				@RequestParam("todoTitle")String todoTitle,
				@RequestParam("todoContent")String todoContent,
				RedirectAttributes ra) {
		
		int result = service.addTodo(todoTitle,todoContent);
		
		String message = null;
		
		if(result > 0) message = "할 일 추가 성공!!";
		else message = "할 일 추가 실패...";
		
		ra.addFlashAttribute("message",message);
		
		
		return "redirect:/"; // 메인페이지 재요청 
	}
	
	@GetMapping("detail")
	public String todoDetail(@RequestParam("todoNo")int todoNo,
							 Model model,
							 RedirectAttributes ra) {
		
		Todo todo = service.todoDetail(todoNo);
		
		String path = null;
		
		if(todo != null) {
			path = "todo/detail";
			model.addAttribute("todo",todo);
			
		} else {
			
			path ="redirect:/";
			ra.addFlashAttribute("message","해당 할 일이 존재하지 않습니다");
		}
		
		return path;
	}
}
