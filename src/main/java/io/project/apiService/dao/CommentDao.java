package io.project.apiService.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.project.apiService.WebClientSingleton;
import io.project.model.comment.CommentResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentDao {
    private final WebClient client;
    private final ObjectMapper mapper;

    public CommentDao() {
        client = WebClientSingleton.getClientInstance();
        mapper = new ObjectMapper();
    }

    public List<CommentResponse> initAllComments() {
        Mono<CommentResponse[]> response = client.get()
                .uri("/comments")
                .retrieve()
                .bodyToMono(CommentResponse[].class);
        CommentResponse[] todoObjects = response.block();
        return Arrays.stream(todoObjects)
                .map(object -> mapper.convertValue(object, CommentResponse.class))
                .collect(Collectors.toList());
    }
}
