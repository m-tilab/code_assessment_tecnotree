package io.project.model.comment.response;

import io.project.model.post.PostEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateCommentResponseModel {
    private Long id;
    private PostEntity post;
    private String name;
    private String email;
    private String body;
}
