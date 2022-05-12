package io.project.repository;

import io.project.model.todo.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {

    List<TodoEntity> findAllByUserIdAndCompleted(int userId, boolean completed);

}
