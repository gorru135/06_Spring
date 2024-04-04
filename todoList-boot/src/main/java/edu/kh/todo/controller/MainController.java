package edu.kh.todo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.todo.model.dto.Todo;
import edu.kh.todo.model.service.TodoService;
import edu.kh.todo.model.service.TodoServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j // 로그 객체 자동 생성 
@Controller // 요청/응답 제어 역활 명시 + Bean 등록
public class MainController {
	
	@Autowired // DI(의존성 주입)
	private TodoService service;
	
	@RequestMapping("/")
	public String mainPage(Model model) {
		
		// 의존성 주입(DI) 확인
		log.debug("service"+service);
		
		// Service 메서드 호출 후 결과 반환 받기 
		Map<String, Object> map = service.selectAll();
		
		// map에 담긴 내용 추출 
		List<Todo> todoList = (List)map.get("todoList");
		int completeCount = (int)map.get("completeCount");
		
		// Model : 값 전달용 객체(request scope) + session 변환 가능 
		model.addAttribute("todoList",todoList);
		model.addAttribute("completeCount",completeCount);
		
		
		// 접두사 : classpath:/templates/
		// 접미사 : .html
		//-> 이쪽으로 forward
		return "common/main";
	}
}
