package io.project.service;

import io.project.model.todo.TodoDTO;

import java.util.List;

public interface TodoService {

    List<TodoDTO> getAllTodos();

    List<TodoDTO> getAllTodosByUserIdAndState(int userId, boolean state);


    void saveAll(List<TodoDTO> todoDTOS);
}
