package io.project.repository;

import io.project.model.comment.CommentEntity;
import io.project.model.post.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByPostId(Long postId);

    Page<CommentEntity> findAll(Pageable pageable);
}
