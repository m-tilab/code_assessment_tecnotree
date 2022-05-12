package io.project.controller;

import io.project.model.todo.TodoDTO;
import io.project.model.todo.mapper.TodoMapper;
import io.project.model.todo.response.GetTodoResponseModel;
import io.project.service.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/todos")
@Validated
public class TodoController {
    private final TodoService service;
    private final TodoMapper todoMapper;

    public TodoController(TodoService service, TodoMapper todoMapper) {
        this.service = service;
        this.todoMapper = todoMapper;
    }

    @GetMapping("")
    @Operation(summary = "returns all todos")
    public List<GetTodoResponseModel> getAllTodos() {
        List<GetTodoResponseModel> responseModels = new ArrayList<>();
        List<TodoDTO> todoDTOS = service.getAllTodos();
        for (TodoDTO todoDTO : todoDTOS)
            responseModels.add(todoMapper.dtoToGetTodoResponseModel(todoDTO));
        return responseModels;
    }

    @GetMapping("/todos")
    @Operation(summary = "returns all todos by given user id and a state that says if its complete or not")
    public List<GetTodoResponseModel> getAllTodosByUserIdAndState(@RequestParam int userId, @RequestParam Boolean completed) {
        List<GetTodoResponseModel> responseModels = new ArrayList<>();
        List<TodoDTO> todoDTOS = service.getAllTodosByUserIdAndState(userId, completed);
        for (TodoDTO todoDTO : todoDTOS)
            responseModels.add(todoMapper.dtoToGetTodoResponseModel(todoDTO));
        return responseModels;
    }
}






























