package io.project.service.serviceImpl;

import io.project.exception.NotFoundException;
import io.project.logger.CrudOperationLogger;
import io.project.model.comment.CommentDTO;
import io.project.model.comment.CommentEntity;
import io.project.model.comment.mapper.CommentMapper;
import io.project.repository.CommentRepository;
import io.project.repository.PostRepository;
import io.project.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private final CrudOperationLogger logger;
    private final CommentRepository repository;
    private final PostRepository postRepository;

    private final CommentMapper commentMapper;

    public CommentServiceImpl(CrudOperationLogger logger, CommentRepository repository, PostRepository postRepository, CommentMapper commentMapper) {
        this.logger = logger;
        this.repository = repository;
        this.postRepository = postRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public CommentDTO createComment(CommentDTO commentDTO) {
        if (postRepository.findById(commentDTO.getPost().getId()).isEmpty())
            throw new NotFoundException("post", commentDTO.getPost().getId());

        CommentEntity commentEntity = commentMapper.dtoToEntity(commentDTO);
        commentEntity = repository.save(commentEntity);
        return commentMapper.entityToDto(commentEntity);
    }

    @Override
    public CommentDTO updateComment(Long commentId, CommentDTO commentDTO) {
        if (postRepository.findById(commentDTO.getPost().getId()).isEmpty())
            throw new NotFoundException("post", commentDTO.getPost().getId());

        Optional<CommentEntity> optionalCommentEntity = repository.findById(commentId);
        if (optionalCommentEntity.isEmpty())
            throw new NotFoundException("comment", commentDTO.getId());
        CommentEntity commentEntity = optionalCommentEntity.get();
        commentMapper.dtoToEntity(commentDTO);
        commentEntity = repository.save(commentEntity);
        logger.updateInfo(commentEntity);
        return commentMapper.entityToDto(commentEntity);
    }

    @Override
    public List<CommentDTO> getAllComments(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<CommentEntity> commentEntities = repository.findAll(pageable);
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntities) {
            commentDTOS.add(commentMapper.entityToDto(commentEntity));
        }
        logger.readInfo(commentEntities);
        return commentDTOS;
    }

    @Override
    public List<CommentDTO> getAllCommentsByPostId(Long postId) {
        if (postRepository.findById(postId).isEmpty())
            throw new NotFoundException("post", postId);
        List<CommentEntity> commentEntities = repository.findAllByPostId(postId);
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntities) {
            commentDTOS.add(commentMapper.entityToDto(commentEntity));
        }
        logger.readInfo(commentEntities);
        return commentDTOS;
    }

    @Override
    public void deleteCommentById(Long commentId) {
        Optional<CommentEntity> optionalCommentEntity = repository.findById(commentId);
        if (optionalCommentEntity.isEmpty())
            throw new NotFoundException("comment", commentId);
        logger.deleteInfo(optionalCommentEntity.get());
        repository.deleteById(commentId);
    }

    @Override
    public void saveAll(List<CommentDTO> commentDTOS) {
        List<CommentEntity> commentEntities = new ArrayList<>();
        for (int i = 0; i < commentDTOS.size() - 1; i++)
            commentEntities.add(commentMapper.dtoToEntity(commentDTOS.get(i)));

        repository.saveAll(commentEntities);
        logger.createInfo(commentEntities);
    }

}




















































