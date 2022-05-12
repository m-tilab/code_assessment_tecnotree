package io.project.model.todo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoDTO {
    private Long id;
    private int userId;
    private String title;
    private boolean completed;
}
