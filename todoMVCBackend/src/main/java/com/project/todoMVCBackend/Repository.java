package com.project.todoMVCBackend;

import org.springframework.data.repository.CrudRepository;

public interface Repository extends CrudRepository<TodoObject, Integer> {

}
