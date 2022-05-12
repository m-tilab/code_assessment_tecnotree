package io.project.service;

import io.project.model.comment.CommentDTO;

import java.util.List;

public interface CommentService {

    CommentDTO createComment(CommentDTO commentDTO);

    CommentDTO updateComment(Long commentId, CommentDTO commentDTO);

    List<CommentDTO> getAllComments(int pageNumber, int pageSize);

    List<CommentDTO> getAllCommentsByPostId(Long postId);

    void deleteCommentById(Long commentId);

    void saveAll(List<CommentDTO> commentDTOS);
}
