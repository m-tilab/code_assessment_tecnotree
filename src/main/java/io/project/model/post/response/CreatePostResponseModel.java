package io.project.model.post.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePostResponseModel {
    private int userId;
    private Long id;
    private String title;
    private String body;
}
