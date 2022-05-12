package io.project.model.todo.mapper;

import io.project.model.todo.TodoDTO;
import io.project.model.todo.TodoEntity;
import io.project.model.todo.TodoResponse;
import io.project.model.todo.request.CreateTodoRequestModel;
import io.project.model.todo.response.CreateTodoResponseModel;
import io.project.model.todo.response.GetTodoResponseModel;


public interface TodoMapper {
    TodoEntity dtoToTodoEntity(TodoDTO dto);

    TodoDTO todoEntityToDto(TodoEntity entity);

    CreateTodoRequestModel DtoToCreateTodoRequestModel(TodoDTO dto);

    TodoDTO createTodoRequestModelToDto(CreateTodoRequestModel requestModel);

    CreateTodoResponseModel dtoToCreateTodoResponseModel(TodoDTO dto);

    TodoDTO createTodoResponseModelToDto(CreateTodoResponseModel responseModel);

    GetTodoResponseModel dtoToGetTodoResponseModel(TodoDTO dto);

    TodoDTO getTodoResponseModelToDto(GetTodoResponseModel responseModel);

    TodoDTO todoResponseToTodoDto(TodoResponse todoResponse);
}
