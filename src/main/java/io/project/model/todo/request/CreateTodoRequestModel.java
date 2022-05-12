package io.project.model.todo.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateTodoRequestModel {
    private int userId;
    @NotEmpty(message = "title can't be empty")
    @NotNull(message = "title is required")
    private String title;
    private boolean completed;
}
