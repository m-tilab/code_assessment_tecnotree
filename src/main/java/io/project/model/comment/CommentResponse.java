package io.project.model.comment;

import io.project.model.post.PostEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentResponse {
    private Long id;
    private Long postId;
    private String name;
    private String email;
    private String body;
}
