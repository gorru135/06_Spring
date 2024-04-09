package edu.kh.todo.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.todo.model.dto.Todo;
import edu.kh.todo.model.mapper.TodoMapper;

@Service
public class TodoServiceImpl implements TodoService{

	@Autowired
	private TodoMapper mapper;
	
	
	@Override
	public Map<String, Object> selectAll() {
		
		List<Todo> todoList= mapper.selectAll();
		
		int compleateCount = mapper.getCompleteCount();
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("todoList", todoList);
		map.put("compleateCount", compleateCount);
		
		return map;
	}


	@Override
	public int addTodo(String todoTitle, String todoContent) {

		Todo todo = new Todo();
		todo.setTodoTitle(todoTitle);
		todo.setTodoContent(todoContent);
		
		return mapper.addTodo(todo);
	}

}
