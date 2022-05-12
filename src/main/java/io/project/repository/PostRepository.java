package io.project.repository;

import io.project.model.post.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends PagingAndSortingRepository<PostEntity, Long> {

    List<PostEntity> findAllByTitleContainingOrBodyContaining(String titleKeyword, String bodyKeyword);

    Page<PostEntity> findAll(Pageable pageable);

}
