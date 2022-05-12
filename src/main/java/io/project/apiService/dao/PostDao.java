package io.project.apiService.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.project.apiService.WebClientSingleton;
import io.project.model.post.PostResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostDao {
    private final WebClient client;
    private final ObjectMapper mapper;

    public PostDao() {
        client = WebClientSingleton.getClientInstance();
        mapper = new ObjectMapper();
    }

    public List<PostResponse> initAllPosts() {
        Mono<PostResponse[]> response = client.get()
                .uri("/posts")
                .retrieve()
                .bodyToMono(PostResponse[].class);
        PostResponse[] todoObjects = response.block();
        return Arrays.stream(todoObjects)
                .map(object -> mapper.convertValue(object, PostResponse.class))
                .collect(Collectors.toList());
    }
}
