package edu.kh.todo.model.service;

import java.util.Map;

import edu.kh.todo.model.dto.Todo;

public interface TodoService {

	Map<String, Object> selectAll();

	int addTodo(String todoTitle, String todoContent);

	/** 할 일 상세 조회 
	 * @param todoNo
	 * @return todo
	 */
	Todo todoDetail(int todoNo);

}
