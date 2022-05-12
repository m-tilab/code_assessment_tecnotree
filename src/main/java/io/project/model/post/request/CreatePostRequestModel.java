package io.project.model.post.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreatePostRequestModel {
    private int userId;
    @NotEmpty(message = "title can't be empty")
    @NotNull(message = "title is required")
    private String title;
    @NotEmpty(message = "body can't be empty")
    @NotNull(message = "body is required")
    private String body;
}
