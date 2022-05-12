package io.project.apiService.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.project.apiService.WebClientSingleton;
import io.project.model.todo.TodoResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoDao {
    private final WebClient client;
    private final ObjectMapper mapper;

    public TodoDao() {
        client = WebClientSingleton.getClientInstance();
        mapper = new ObjectMapper();
    }

    public List<TodoResponse> initAllTodos() {
        Mono<TodoResponse[]> response = client.get()
                .uri("/todos")
                .retrieve()
                .bodyToMono(TodoResponse[].class);
        TodoResponse[] todoObjects = response.block();
        return Arrays.stream(todoObjects)
                .map(object -> mapper.convertValue(object, TodoResponse.class))
                .collect(Collectors.toList());
    }
}




























