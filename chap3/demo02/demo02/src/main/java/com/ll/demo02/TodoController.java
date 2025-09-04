package com.ll.demo02;

import java.util.*;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController {
	private long todosLastId;
	private List<Todo> todos;

	public TodoController(){
		todos = new ArrayList<>();
	}

	@GetMapping("")
	public List<Todo> getTodos() {
		return todos;
	}

	@GetMapping("/detail")
	public Todo getTodo(
		long id
	) {
		return todos
			.stream()
			.filter(
				todo -> todo.getId()==id // 해당 조건을 만족하면 리턴, 아니면 null 리턴
			)
			.findFirst()
			.orElse(null);
	}

	@GetMapping("/{id}")
	public Todo getTodo2(
		@PathVariable long id
	) {
		return todos
			.stream()
			.filter(
				todo -> todo.getId() == id
			)
			.findFirst()
			.orElse(null);
	}

	@GetMapping("/add")
	public Todo add(
		String body
	) {
		Todo todo = Todo
			.builder()
			.id(++todosLastId)
			.body(body)
			.build();

		todos.add(todo);

		return todo;
	}

	@GetMapping("/remove/{id}")
	public boolean remove(
		@PathVariable long id
	) {
		boolean removed = todos.removeIf((todo -> todo.getId() == id));

		return removed;
	}

	@GetMapping("/modify/{id}")
	public boolean modify(
		@PathVariable long id,
		String body
	) {
		Todo todo = todos
			.stream()
			.filter(
				_todo -> _todo.getId() == id
			)
			.findFirst()
			.orElse(null);

		if( todo == null ) return false;

		todo.setBody(body);

		return true;
	}
}
