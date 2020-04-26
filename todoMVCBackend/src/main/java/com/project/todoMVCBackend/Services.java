package com.project.todoMVCBackend;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class Services {
 
	
	private Repository repository;
	
	/** This constructor creates a new repository object
	 * @Param this constructor is autowired and will create a repository object by itself
	 */
	@Autowired
	public Services(Repository repository) {
		this.repository = repository;
	}
	
	/** This method adds a new object to the database and sets its status to active
	 * @Param takes in the string of the new todo
	 * @return a repository.save() function of the new object
	 */
	public TodoObject createTodo(String todoString) {
		
		TodoObject item = new TodoObject();
		item.setTodoItem(todoString);
		
		// set new item as active
		item.setCompleted(false);
		
		return repository.save(item);
	}
	
	/** This method returns all todo objects
	 * @return a list of all objects in repository
	 */
	public List<TodoObject> getAllTodos() {
		return (List<TodoObject>) repository.findAll();
	}
	
	/** This method returns all active todo objects
	 * @return a list of all active objects in repository
	 */
	public List<TodoObject> getActiveTodos() {
		List<TodoObject> list = (List<TodoObject>) repository.findAll();
		
		for(TodoObject todo : list) {
			if(todo.isCompleted()) {
				list.remove(todo);
			}
		}
		
		return list;
	}
	
	/** This method returns all completed todo objects
	 * @return a list of all completed objects in repository
	 */
	public List<TodoObject> getCompletedTodos() {
		List<TodoObject> list = (List<TodoObject>) repository.findAll();
		
		for(TodoObject todo : list) {
			if(!todo.isCompleted()) {
				list.remove(todo);
			}
		}
		
		return list;
	}
	
	/** This method changes the status of of an object by id
	 * @Param id
	 */
	public void setStatus(Integer id) {
		
		TodoObject item = repository.findById(id).get();
		
		// if item is active set to completed
		if(!item.isCompleted()) {
			item.setCompleted(true);
		} else { // if item is completed set to active
			item.setCompleted(false);
		}
	}
	
	/** This method changes the status of all the objects
	 */
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
	
	/** This method returns the amount of active items
	 * @return size of list
	 */
	public int getActive() {
	
		List<TodoObject> list = (List<TodoObject>) repository.findAll();
		
		return list.size();
	}
	

	/** This method deletes an object by id
	 * @Param id 
	 */
	public void deleteTodoObject(Integer id) {
		repository.deleteById(id);
	}
	
	/** This method deletes all completed objects
	 */
	public void deleteCompletedTodoObjects() {
		
		List<TodoObject> list = (List<TodoObject>) repository.findAll();
		
		list.stream().filter(TodoObject::isCompleted).forEach(todo -> repository.deleteAll()); 
	}

	
	
}
