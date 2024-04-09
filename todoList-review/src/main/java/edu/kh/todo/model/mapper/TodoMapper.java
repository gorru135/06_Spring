package edu.kh.todo.model.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.todo.model.dto.Todo;

@Mapper
public interface TodoMapper {

	/** 할 일 목록 조회 
	 * @return todoList
	 */
	List<Todo> selectAll();

	/** 완료된 할 일 개수 조회
	 * @return
	 */
	int getCompleteCount();

	int addTodo(Todo todo);


	
	
	

}
