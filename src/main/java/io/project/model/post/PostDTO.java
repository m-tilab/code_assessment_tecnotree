package io.project.model.post;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PostDTO {
    private Long id;
    private int userId;
    private String title;
    private String body;
}
