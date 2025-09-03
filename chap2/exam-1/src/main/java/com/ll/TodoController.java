package com.ll;

import java.util.*;

public class TodoController {
	private List<Todo> todos;
	private long todoLastId;
	private Scanner sc;

	public TodoController(){
		sc=new Scanner(System.in);
		todos=new ArrayList<>();
		todoLastId=0;
	}

	public void add(){
		long id = todoLastId+1;
		System.out.print("할일: ");
		String content=sc.nextLine().trim();

		Todo todo = new Todo(id, content);
		todos.add(todo);
		todoLastId++;

		System.out.printf("%d번 할일이 생성되었습니다.\n", id);
	}

	public void list(){
		System.out.println("번호 / 내용");

		todos.forEach(todo-> System.out.printf("%d / %s\n", todo.getId(), todo.getContent()));
	}

	public void del(){
		System.out.print("삭제할 할일의 번호: ");
		long id = Long.parseLong(sc.nextLine().trim());
		boolean isRemoved=todos.removeIf(todo->todo.getId()==id);
		if(!isRemoved) {
			System.out.printf("%d번 할일은 존재하지 않습니다.\n",id);
			return;
		}
		System.out.printf("%d번 할일이 삭제되었습니다.\n",id);
	}

	public void modify(){
		System.out.print("수정할 할일의 번호: ");
		long id = Long.parseLong(sc.nextLine().trim());

		Todo foundTodo=todos.stream()
			.filter(t->t.getId()==id)
			.findFirst()
			.orElse(null);

		if(foundTodo==null) {
			System.out.printf("%d번 할일은 존재하지 않습니다.\n",id);
			return;
		}
		System.out.printf("기존 할일: %s\n",foundTodo.getContent());
		System.out.printf("새 할일: ");
		foundTodo.setContent(sc.nextLine().trim());

		System.out.printf("%d번 할일이 수정되었습니다.\n",id);
	}
}
