package io.project.apiService;

import org.springframework.web.reactive.function.client.WebClient;

public class WebClientSingleton {
    private static WebClient CLIENT;

    public static WebClient getClientInstance() {
        if (CLIENT == null)
            CLIENT = WebClient.builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .build();
        return CLIENT;
    }
}
