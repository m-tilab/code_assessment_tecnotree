package io.project.model.post.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class UpdatePostRequestModel {
    private Long id;
    private int userId;
    @NotNull
    @NotEmpty
    private String title;
    @NotNull
    @NotEmpty
    private String body;
}
