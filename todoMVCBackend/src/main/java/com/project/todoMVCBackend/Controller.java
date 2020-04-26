package com.project.todoMVCBackend;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
public class Controller {

	// CONTROLLER HANTERAR USER INPUT
	
	
	
	private Services services;
	
	
	//Autowired gör att
	@Autowired
	public Controller(Services services) {
		
		this.services = services;
	}
	
	//CREATE TODO ITEM
	@PostMapping
	public TodoObject createTodoObject(@RequestParam String todoText) {
		
		return services.createTodo(todoText);
	}
	
	
	//GET ALL TODO ITEMS
	@GetMapping("/")
	public List<TodoObject> todoList() {
		
		List<TodoObject> todoObject = services.getAllTodos();
		
		return todoObject;
	}
	
	// set todo to completed
	@GetMapping("/")
	public void setStatus(Integer id) {
		services.setStatus(id);
	}
	// Completed items
	
	// return amount of active items
	@GetMapping("/")
	public int getActive() {
		return services.getActive();
	}
	// Active Items
	
	// Delete object
	@GetMapping("/")
	public void deleteTodoObject(Integer id) {
		services.deleteTodoObject(id);
	}
	// Delete  all completed Todo's
	@GetMapping("/")
	public void deleteCompletedTodoObjects() {
		
	}
	
}
