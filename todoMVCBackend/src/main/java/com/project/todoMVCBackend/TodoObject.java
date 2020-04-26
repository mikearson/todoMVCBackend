package com.project.todoMVCBackend;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TodoItems")
public class TodoObject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="id", nullable=false)
	private Integer id;
	
	@Column(name ="todoItem", nullable=false)
	private String todoItem;
	
	
	// Boolean to determine if a todo object is Completed or not. True = Completed, False = Active
	@Column(name ="status", nullable=false)
	private boolean completed;

	public int getId() {
		return id;
	}

	public String getTodoItem() {
		return todoItem;
	}

	public void setTodoItem(String todoItem) {
		this.todoItem = todoItem;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean status) {
		this.completed = status;
	}
	
	
}
