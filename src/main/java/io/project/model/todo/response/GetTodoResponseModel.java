package io.project.model.todo.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetTodoResponseModel {
    private Long id;
    private int userId;
    private String title;
    private boolean completed;
}
