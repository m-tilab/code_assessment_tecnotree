package io.project.service.serviceImpl;

import io.project.exception.EmptyParameterException;
import io.project.exception.NotFoundException;
import io.project.logger.CrudOperationLogger;
import io.project.model.post.PostDTO;
import io.project.model.post.PostEntity;
import io.project.model.post.mapper.PostMapper;
import io.project.repository.PostRepository;
import io.project.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PostServiceImpl implements PostService {
    private final CrudOperationLogger logger;
    private final PostRepository repository;

    private final PostMapper postMapper;

    public PostServiceImpl(CrudOperationLogger logger, PostRepository repository, PostMapper postMapper) {
        this.logger = logger;
        this.repository = repository;
        this.postMapper = postMapper;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        PostEntity postEntity = postMapper.dtoToEntity(postDTO);
        postEntity = repository.save(postEntity);
        logger.createInfo(postEntity);
        return postMapper.entityToDto(postEntity);
    }

    @Override
    public PostDTO updatePost(Long postId, PostDTO postDTO) {
        Optional<PostEntity> optionalPostEntity = repository.findById(postId);
        if (optionalPostEntity.isEmpty())
            throw new NotFoundException("post", postDTO.getId());
        // todo -> have a conversation about this with mr tilab. he said I should use get but in this situation get is redundant
        PostEntity postEntity = optionalPostEntity.get();
        postEntity = postMapper.dtoToEntity(postDTO);
        postEntity = repository.save(postEntity);
        logger.updateInfo(postEntity);
        return postMapper.entityToDto(postEntity);
    }

    @Override
    public List<PostDTO> getAllPosts(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<PostDTO> postDTOS = new ArrayList<>();
        Page<PostEntity> postEntities = repository.findAll(pageable);
        for (PostEntity postEntity : postEntities)
            postDTOS.add(postMapper.entityToDto(postEntity));
        logger.readInfo(postEntities);
        return postDTOS;
    }

    @Override
    public PostDTO getPostById(Long postId) {
        Optional<PostEntity> optionalPostEntity = repository.findById(postId);
        if (optionalPostEntity.isEmpty())
            throw new NotFoundException("post", postId);
        logger.readInfo(optionalPostEntity.get());
        return postMapper.entityToDto(optionalPostEntity.get());
    }

    @Override
    public List<PostDTO> getAllPostsByKeyword(String keyword) {
        if (keyword.equals(""))
            throw new EmptyParameterException(keyword);
        List<PostDTO> postDTOS = new ArrayList<>();
        List<PostEntity> postEntities = repository.findAllByTitleContainingOrBodyContaining(keyword, keyword);
        for (PostEntity postEntity : postEntities)
            postDTOS.add(postMapper.entityToDto(postEntity));
        logger.readInfo(postEntities);
        return postDTOS;
    }

    @Override
    public void deletePostById(Long postId) {
        Optional<PostEntity> optionalPostEntity = repository.findById(postId);
        if (optionalPostEntity.isEmpty())
            throw new NotFoundException("post", postId);
        logger.deleteInfo(optionalPostEntity.get());
        repository.deleteById(postId);
    }

    @Override
    public void saveAll(List<PostDTO> postDTOS) {
        List<PostEntity> postEntities = new ArrayList<>();
        for (PostDTO postDTO : postDTOS) postEntities.add(postMapper.dtoToEntity(postDTO));

        repository.saveAll(postEntities);
        logger.createInfo(postEntities);
    }
}



















