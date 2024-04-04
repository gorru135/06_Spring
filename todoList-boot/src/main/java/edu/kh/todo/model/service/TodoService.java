package edu.kh.todo.model.service;

import java.util.Map;

public interface TodoService {

	/** 할일 목록
	 * @return map
	 */
	Map<String, Object> selectAll();

	/** 할 일 추가 서비스
	 * @param todoTitle
	 * @param todoContent
	 * @return result
	 */
	int addTodo(String todoTitle, String todoContent);
	
}
