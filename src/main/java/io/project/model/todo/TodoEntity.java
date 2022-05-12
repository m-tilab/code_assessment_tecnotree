package io.project.model.todo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int userId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private boolean completed;


    @Override
    public String toString() {
        return "TodoEntity{" +
                "id=" + id +
                ", user=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                '}';
    }
}
