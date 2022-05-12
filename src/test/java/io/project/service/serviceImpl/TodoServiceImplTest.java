package io.project.service.serviceImpl;

import io.project.logger.CrudOperationLogger;
import io.project.model.todo.TodoDTO;
import io.project.model.todo.TodoEntity;
import io.project.model.todo.mapper.TodoMapper;
import io.project.model.todo.mapper.TodoMapperImpl;
import io.project.repository.TodoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class TodoServiceImplTest {
    private final CrudOperationLogger logger = new CrudOperationLogger();
    private final TodoMapper todoMapper = new TodoMapperImpl();
    private TodoDTO fakeTodoDTO;
    private List<TodoEntity> todoEntities;


    private AutoCloseable closeable;
    @Mock
    private TodoRepository repository;
    private TodoServiceImpl service;

    @BeforeEach
    void init() {
        closeable = MockitoAnnotations.openMocks(this);
        service = new TodoServiceImpl(logger, repository, todoMapper);

        TodoEntity fakeTodoEntity = new TodoEntity();
        fakeTodoEntity.setId(2L);
        fakeTodoEntity.setUserId(1);
        fakeTodoEntity.setTitle("title");
        fakeTodoEntity.setCompleted(true);

        fakeTodoDTO = new TodoDTO();
        fakeTodoDTO.setId(2L);
        fakeTodoDTO.setUserId(1);
        fakeTodoDTO.setTitle("title");
        fakeTodoDTO.setCompleted(true);

        todoEntities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            todoEntities.add(fakeTodoEntity);
        }
    }

    @AfterEach
    void onStop() throws Exception {
        closeable.close();
    }

    @Test
    void getAllTodos() {

        when(repository.findAll()).thenReturn(todoEntities);

        List<TodoDTO> todoDTOS = service.getAllTodos();

        assertEquals(todoDTOS.size(), todoEntities.size());
        for (int i = 0; i < todoDTOS.size() - 1; i++) {
            TodoDTO todoDTO = todoDTOS.get(i);
            TodoEntity todoEntity = todoEntities.get(i);

            assertEquals(todoDTO.getUserId(), todoEntity.getUserId());
            assertEquals(todoDTO.getUserId(), todoEntity.getUserId());

            assertEquals(todoDTO.getId(), todoEntity.getId());
            assertEquals(todoDTO.getTitle(), todoEntity.getTitle());
            assertEquals(todoDTO.isCompleted(), todoEntity.isCompleted());
        }
    }

    @Test
    void getAllTodosByUserIdAndState() {
        when(repository.findAllByUserIdAndCompleted(anyInt(), anyBoolean())).thenReturn(todoEntities);

        List<TodoDTO> todoDTOS = service.getAllTodosByUserIdAndState(fakeTodoDTO.getUserId(), fakeTodoDTO.isCompleted());

        assertEquals(todoDTOS.size(), todoEntities.size());
        for (int i = 0; i < todoDTOS.size() - 1; i++) {
            TodoDTO todoDTO = todoDTOS.get(i);
            TodoEntity todoEntity = todoEntities.get(i);
            assertEquals(todoDTO.getId(), todoEntity.getId());
            assertEquals(todoDTO.getTitle(), todoEntity.getTitle());
            assertEquals(todoDTO.getUserId(), todoEntity.getUserId());
            assertEquals(todoDTO.isCompleted(), todoEntity.isCompleted());
        }
    }

//    @Test
//    void saveAll() {
//        when(repository.saveAll(anyList())).thenReturn(todoEntities);
//
//        verify(repository.saveAll(todoEntities));
//    }

}























































