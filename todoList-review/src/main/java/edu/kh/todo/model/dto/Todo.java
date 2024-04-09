package edu.kh.todo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Todo {
	private int todoNo;
	private String todoTitle;
	private String todoContent;
	private String complete;
	private String regDate;
}
