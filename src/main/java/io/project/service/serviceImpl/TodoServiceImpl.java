package io.project.service.serviceImpl;

import io.project.logger.CrudOperationLogger;
import io.project.model.todo.TodoDTO;
import io.project.model.todo.TodoEntity;
import io.project.model.todo.mapper.TodoMapper;
import io.project.repository.TodoRepository;
import io.project.service.TodoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    private final CrudOperationLogger logger;
    private final TodoRepository repository;
    private final TodoMapper todoMapper;

    public TodoServiceImpl(CrudOperationLogger logger, TodoRepository repository, TodoMapper todoMapper) {
        this.logger = logger;
        this.repository = repository;
        this.todoMapper = todoMapper;
    }

    @Override
    public List<TodoDTO> getAllTodos() {
        List<TodoEntity> todoEntities = repository.findAll();
        List<TodoDTO> todoDTOS = new ArrayList<>();
        for (TodoEntity todoEntity : todoEntities)
            todoDTOS.add(todoMapper.todoEntityToDto(todoEntity));
        logger.readInfo(todoEntities);
        return todoDTOS;
    }

    @Override
    public List<TodoDTO> getAllTodosByUserIdAndState(int userId, boolean state) {
        List<TodoEntity> todoEntities = repository.findAllByUserIdAndCompleted(userId, state);
        List<TodoDTO> todoDTOS = new ArrayList<>();
        for (TodoEntity todoEntity : todoEntities)
            todoDTOS.add(todoMapper.todoEntityToDto(todoEntity));
        logger.readInfo(todoEntities);
        return todoDTOS;
    }

    @Override
    public void saveAll(List<TodoDTO> todoDTOS) {
        List<TodoEntity> todoEntities = new ArrayList<>();
        for (int i = 0; i < todoDTOS.size() - 1; i++)
            todoEntities.add(todoMapper.dtoToTodoEntity(todoDTOS.get(i)));

        repository.saveAll(todoEntities);
        logger.createInfo(todoEntities);
    }
}
