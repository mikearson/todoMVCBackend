package com.project.todoMVCBackend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class Services {
 
	
	private Repository repository;
	
	@Autowired
	public Services(Repository repository) {
		this.repository = repository;
	}
	
	// creates a new todo object
	// returns a TodoObject using save() method from CrudRepository
	public TodoObject createTodo(String todoString) {
		
		TodoObject item = new TodoObject();
		item.setTodoItem(todoString);
		
		// set new item as active
		item.setCompleted(false);
		
		return repository.save(item);
	}
	
	// returns all todoObjects
	public List<TodoObject> getAllTodos() {
		return (List<TodoObject>) repository.findAll();
	}
	
	
	// set todo to completed
	public void setStatus(Integer id) {
		
		TodoObject item = repository.findById(id).get();
		
		// if item is not completed set to completed
		if(!item.isCompleted()) {
			item.setCompleted(true);
		} else { // if all items are completed set to active
			item.setCompleted(false);
		}
	}
	
	// set all todo's to either completed or active
	public void setAllTodosStatus() {
		List<TodoObject> list = (List<TodoObject>) repository.findAll();
		if(getActive() > 0) {
			for(TodoObject todo : list) {
				todo.setCompleted(true);
			}
		} else {
			for(TodoObject todo : list) {
				todo.setCompleted(false);
			}
		}
		
	}
	
	// Returns amount of active items
	public int getActive() {
	
		List<TodoObject> list = (List<TodoObject>) repository.findAll();
		
		return list.size();
	}
	

	// delete todoObject by ID
	
	public void deleteTodoObject(Integer id) {
		repository.deleteById(id);
	}
	
	// delete completed todoObjects
	
	public void deleteCompletedTodoObjects() {
		
		List<TodoObject> list = (List<TodoObject>) repository.findAll();
		
		list.stream().filter(TodoObject::isCompleted).forEach(todo -> repository.deleteAll()); 
	}

	
	
}
