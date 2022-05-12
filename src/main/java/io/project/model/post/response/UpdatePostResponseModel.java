package io.project.model.post.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdatePostResponseModel {
    private Long id;
    private int userId;
    private String title;
    private String body;
}
