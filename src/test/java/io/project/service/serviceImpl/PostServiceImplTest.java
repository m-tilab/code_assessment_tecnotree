package io.project.service.serviceImpl;

import io.project.exception.EmptyParameterException;
import io.project.exception.NotFoundException;
import io.project.logger.CrudOperationLogger;
import io.project.model.post.PostDTO;
import io.project.model.post.PostEntity;
import io.project.model.post.mapper.PostMapper;
import io.project.model.post.mapper.PostMapperImpl;
import io.project.repository.PostRepository;
import io.project.service.PostService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PostServiceImplTest {
    private final CrudOperationLogger logger = new CrudOperationLogger();
    private final PostMapper postMapper = new PostMapperImpl();
    private List<PostEntity> postEntities;
    private PostEntity fakePostEntity;
    private PostDTO fakePostDTO;
    private AutoCloseable closeable;
    @Mock
    private PostRepository repository;
    private PostService service;

    @BeforeEach
    void init() {
        closeable = MockitoAnnotations.openMocks(this);
        service = new PostServiceImpl(logger, repository, postMapper);


        fakePostEntity = new PostEntity();
        fakePostEntity.setId(2L);
        fakePostEntity.setUserId(1);
        fakePostEntity.setTitle("title");
        fakePostEntity.setBody("body");

        fakePostDTO = new PostDTO();
        fakePostDTO.setId(2L);
        fakePostDTO.setUserId(1);
        fakePostDTO.setTitle("title");
        fakePostDTO.setBody("body");

        postEntities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            postEntities.add(fakePostEntity);
        }
    }

    @AfterEach
    void onStop() throws Exception {
        closeable.close();
    }

    @Test
    void createPost() {
        when(repository.save(any(PostEntity.class))).thenReturn(fakePostEntity);

        PostDTO postDTO = service.createPost(fakePostDTO);

        assertNotNull(postDTO);

        assertEquals(postDTO.getUserId(), fakePostDTO.getUserId());
        assertEquals(postDTO.getUserId(), fakePostDTO.getUserId());
        assertNotNull(postDTO.getId());
        assertEquals(postDTO.getTitle(), fakePostDTO.getTitle());
        assertEquals(postDTO.getBody(), fakePostDTO.getBody());
    }

    @Test
    void updatePost() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(fakePostEntity));
        when(repository.save(any(PostEntity.class))).thenReturn(fakePostEntity);

        PostDTO postDTO = service.updatePost(fakePostDTO.getId(), fakePostDTO);

        assertNotNull(postDTO);

        assertEquals(postDTO.getUserId(), fakePostDTO.getUserId());
        assertEquals(postDTO.getUserId(), fakePostDTO.getUserId());

        assertEquals(postDTO.getId(), fakePostDTO.getId());
        assertEquals(postDTO.getTitle(), fakePostDTO.getTitle());
        assertEquals(postDTO.getBody(), fakePostDTO.getBody());
    }

    @Test
    void updatePost_PostNotFound() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.updatePost(1L, fakePostDTO));
    }

    @Test
    void deletePostById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(fakePostEntity));

        Optional<PostEntity> optionalPostEntity = repository.findById(fakePostDTO.getId());
        assertTrue(optionalPostEntity.isPresent());
        service.deletePostById(fakePostDTO.getId());
        verify((repository)).deleteById(anyLong());
    }

    @Test
    void deletePostById_PostNotFound() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.deletePostById(1L));
    }

    // todo -> I can't simulate pagination
//    @Test
//    void getAllPosts() {
//
//        Page<PostEntity> page = new PageImpl<>(postEntities);
//        int pageSize = 10;
//        int pageNumber = 0;
//
//        when(repository.findAll(any(Pageable.class))).thenReturn(page);
//
//        List<PostDTO> postDTOS = service.getAllPosts(0, 10);
//
//        assertEquals(postDTOS.size(), page.getSize());
//
//        for (int i = pageNumber; i < page.getSize() / pageSize; i++) {
//            PostEntity postEntity = page.getContent().get(i);
//            PostDTO postDTO = postDTOS.get(i);
//
//            assertEquals(postEntity.getUserId(), postDTO.getUserId());
//            assertEquals(postEntity.getUserId(), postDTO.getUserId());
//
//            assertEquals(postEntity.getId(), postDTO.getId());
//            assertEquals(postEntity.getTitle(), postDTO.getTitle());
//            assertEquals(postEntity.getBody(), postDTO.getBody());
//        }
//    }

    @Test
    void getPostById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(fakePostEntity));

        PostDTO postDTO = service.getPostById(fakePostDTO.getId());

        assertNotNull(postDTO);

        assertEquals(fakePostEntity.getUserId(), postDTO.getUserId());
        assertEquals(fakePostEntity.getUserId(), postDTO.getUserId());

        assertEquals(fakePostEntity.getId(), postDTO.getId());
        assertEquals(fakePostEntity.getBody(), postDTO.getBody());
        assertEquals(fakePostEntity.getTitle(), postDTO.getTitle());
    }

    @Test
    void getPostById_PostNotFound() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.getPostById(1L));

    }

    @Test
    void getAllPostsByKeyword() {
        String keyword = "keyword";

        when(repository.findAllByTitleContainingOrBodyContaining(anyString(), anyString())).thenReturn(postEntities);

        List<PostDTO> postDTOS = service.getAllPostsByKeyword(keyword);

        assertEquals(postDTOS.size(), postEntities.size());

        for (int i = 0; i < postDTOS.size() - 1; i++) {
            PostEntity postEntity = postEntities.get(i);
            PostDTO postDTO = postDTOS.get(i);

            assertEquals(postEntity.getUserId(), postDTO.getUserId());
            assertEquals(postEntity.getUserId(), postDTO.getUserId());

            assertEquals(postEntity.getId(), postDTO.getId());
            assertEquals(postEntity.getTitle(), postDTO.getTitle());
            assertEquals(postEntity.getBody(), postDTO.getBody());
        }
    }

    @Test
    void getAllPostsByKeyword_EmptyParameter() {
        assertThrows(EmptyParameterException.class, () -> service.getAllPostsByKeyword(""));
    }

//    @Test
//    void saveAll() {
//        when(repository.saveAll(anyList())).thenReturn(postEntities);
//
//        verify(repository.saveAll(postEntities));
//    }

}











































