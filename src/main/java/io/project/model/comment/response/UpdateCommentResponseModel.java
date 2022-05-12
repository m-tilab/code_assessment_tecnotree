package io.project.model.comment.response;

import io.project.model.post.PostEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCommentResponseModel {
    private Long id;
    private PostEntity post;
    private String name;
    private String email;
    private String body;
}
