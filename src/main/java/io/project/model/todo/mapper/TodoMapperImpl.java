package io.project.model.todo.mapper;

import io.project.model.todo.TodoDTO;
import io.project.model.todo.TodoEntity;
import io.project.model.todo.TodoResponse;
import io.project.model.todo.request.CreateTodoRequestModel;
import io.project.model.todo.response.CreateTodoResponseModel;
import io.project.model.todo.response.GetTodoResponseModel;
import org.springframework.stereotype.Component;

@Component
public class TodoMapperImpl implements TodoMapper {

    @Override
    public TodoEntity dtoToTodoEntity(TodoDTO dto) {
        if (dto == null) {
            return null;
        }

        TodoEntity todoEntity = new TodoEntity();

        todoEntity.setId(dto.getId());
        todoEntity.setUserId(dto.getUserId());
        todoEntity.setTitle(dto.getTitle());
        todoEntity.setCompleted(dto.isCompleted());

        return todoEntity;
    }

    @Override
    public TodoDTO todoEntityToDto(TodoEntity entity) {
        if (entity == null) {
            return null;
        }

        TodoDTO todoDTO = new TodoDTO();

        todoDTO.setId(entity.getId());
        todoDTO.setUserId(entity.getUserId());
        todoDTO.setTitle(entity.getTitle());
        todoDTO.setCompleted(entity.isCompleted());

        return todoDTO;
    }

    @Override
    public CreateTodoRequestModel DtoToCreateTodoRequestModel(TodoDTO dto) {
        if (dto == null) {
            return null;
        }

        CreateTodoRequestModel createTodoRequestModel = new CreateTodoRequestModel();

        createTodoRequestModel.setUserId(dto.getUserId());
        createTodoRequestModel.setTitle(dto.getTitle());
        createTodoRequestModel.setCompleted(dto.isCompleted());

        return createTodoRequestModel;
    }

    @Override
    public TodoDTO createTodoRequestModelToDto(CreateTodoRequestModel requestModel) {
        if (requestModel == null) {
            return null;
        }

        TodoDTO todoDTO = new TodoDTO();

        todoDTO.setUserId(requestModel.getUserId());
        todoDTO.setTitle(requestModel.getTitle());
        todoDTO.setCompleted(requestModel.isCompleted());

        return todoDTO;
    }

    @Override
    public CreateTodoResponseModel dtoToCreateTodoResponseModel(TodoDTO dto) {
        if (dto == null) {
            return null;
        }

        CreateTodoResponseModel createTodoResponseModel = new CreateTodoResponseModel();

        createTodoResponseModel.setId(dto.getId());
        createTodoResponseModel.setUserId(dto.getUserId());
        createTodoResponseModel.setTitle(dto.getTitle());
        createTodoResponseModel.setCompleted(dto.isCompleted());

        return createTodoResponseModel;
    }

    @Override
    public TodoDTO createTodoResponseModelToDto(CreateTodoResponseModel responseModel) {
        if (responseModel == null) {
            return null;
        }

        TodoDTO todoDTO = new TodoDTO();

        todoDTO.setId(responseModel.getId());
        todoDTO.setUserId(responseModel.getUserId());
        todoDTO.setTitle(responseModel.getTitle());
        todoDTO.setCompleted(responseModel.isCompleted());

        return todoDTO;
    }

    @Override
    public GetTodoResponseModel dtoToGetTodoResponseModel(TodoDTO dto) {
        if (dto == null) {
            return null;
        }

        GetTodoResponseModel getTodoResponseModel = new GetTodoResponseModel();

        getTodoResponseModel.setId(dto.getId());
        getTodoResponseModel.setUserId(dto.getUserId());
        getTodoResponseModel.setTitle(dto.getTitle());
        getTodoResponseModel.setCompleted(dto.isCompleted());

        return getTodoResponseModel;
    }

    @Override
    public TodoDTO getTodoResponseModelToDto(GetTodoResponseModel responseModel) {
        if (responseModel == null) {
            return null;
        }

        TodoDTO todoDTO = new TodoDTO();

        todoDTO.setId(responseModel.getId());
        todoDTO.setUserId(responseModel.getUserId());
        todoDTO.setTitle(responseModel.getTitle());
        todoDTO.setCompleted(responseModel.isCompleted());

        return todoDTO;
    }

    @Override
    public TodoDTO todoResponseToTodoDto(TodoResponse todoResponse) {
        if (todoResponse == null) {
            return null;
        }

        TodoDTO todoDTO = new TodoDTO();

        todoDTO.setId(todoResponse.getId());
        todoDTO.setUserId(todoResponse.getUserId());
        todoDTO.setTitle(todoResponse.getTitle());
        todoDTO.setCompleted(todoResponse.isCompleted());

        return todoDTO;
    }
}
