package io.project.model.comment.request;

import io.project.model.post.PostEntity;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateCommentRequestModel {
    private Long id;
    private PostEntity post;
    @NotEmpty(message = "name can't be empty")
    @NotNull(message = "name is required")
    private String name;
    @NotEmpty(message = "email can't be empty")
    @NotNull(message = "email is required")
    private String email;
    @NotEmpty(message = "body can't be empty")
    @NotNull(message = "body is required")
    private String body;
}
