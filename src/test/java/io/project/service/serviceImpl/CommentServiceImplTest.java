package io.project.service.serviceImpl;

import io.project.exception.NotFoundException;
import io.project.logger.CrudOperationLogger;
import io.project.model.comment.CommentDTO;
import io.project.model.comment.CommentEntity;
import io.project.model.comment.mapper.CommentMapper;
import io.project.model.comment.mapper.CommentMapperImpl;
import io.project.model.post.PostEntity;
import io.project.repository.CommentRepository;
import io.project.repository.PostRepository;
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


class CommentServiceImplTest {
    private final List<CommentEntity> commentEntities = new ArrayList<>();
    private final CrudOperationLogger logger = new CrudOperationLogger();
    private final CommentMapper commentMapper = new CommentMapperImpl();
    private CommentEntity fakeCommentEntity;
    private CommentDTO fakeCommentDTO;
    private PostEntity fakePostEntity;
    private AutoCloseable closeable;
    @Mock
    private CommentRepository repository;
    @Mock
    private PostRepository postRepository;
    private CommentServiceImpl service;

    @BeforeEach
    void init() {
        closeable = MockitoAnnotations.openMocks(this);
        service = new CommentServiceImpl(logger, repository, postRepository, commentMapper);


        fakePostEntity = new PostEntity();
        fakePostEntity.setId(2L);
        fakePostEntity.setUserId(1);
        fakePostEntity.setTitle("title");
        fakePostEntity.setBody("body");

        fakeCommentEntity = new CommentEntity();
        fakeCommentEntity.setId(3L);
        fakeCommentEntity.setPost(fakePostEntity);
        fakeCommentEntity.setName("name");
        fakeCommentEntity.setBody("body");
        fakeCommentEntity.setEmail("email");

        fakeCommentDTO = new CommentDTO();
        fakeCommentDTO.setId(3L);
        fakeCommentDTO.setPost(fakePostEntity);
        fakeCommentDTO.setName("name");
        fakeCommentDTO.setBody("body");
        fakeCommentDTO.setEmail("email");

        for (int i = 0; i < 32; i++) {
            commentEntities.add(fakeCommentEntity);
        }
    }

    @AfterEach
    void onStop() throws Exception {
        closeable.close();
    }

    @Test
    void getAllCommentsByPostId() {

        when(postRepository.findById(anyLong())).thenReturn(Optional.of(fakePostEntity));
        when(repository.findAllByPostId(anyLong())).thenReturn(commentEntities);

        List<CommentDTO> commentDTOS = service.getAllCommentsByPostId(fakePostEntity.getId());

        assertEquals(commentDTOS.size(), commentEntities.size());

        for (int i = 0; i < commentDTOS.size() - 1; i++) {
            CommentEntity commentEntity = commentEntities.get(i);
            CommentDTO commentDTO = commentDTOS.get(i);

            assertEquals(commentEntity.getPost().getUserId(), commentDTO.getPost().getUserId());
            assertEquals(commentEntity.getPost().getUserId(), commentDTO.getPost().getUserId());

            assertEquals(commentEntity.getPost().getId(), commentDTO.getPost().getId());
            assertEquals(commentEntity.getPost().getTitle(), commentDTO.getPost().getTitle());
            assertEquals(commentEntity.getPost().getBody(), commentDTO.getPost().getBody());

            assertEquals(commentEntity.getBody(), commentDTO.getBody());
            assertEquals(commentEntity.getName(), commentDTO.getName());
            assertEquals(commentEntity.getEmail(), commentDTO.getEmail());
        }
    }

    @Test
    void getAllCommentsByPostId_PostNotFoundException() {
        when(postRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> service.getAllCommentsByPostId(1L));
    }

    @Test
    void createComment() {
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(fakePostEntity));
        when(repository.save(any(CommentEntity.class))).thenReturn(fakeCommentEntity);

        CommentDTO commentDTO = service.createComment(fakeCommentDTO);

        assertNotNull(commentDTO);

        assertEquals(fakeCommentEntity.getPost().getUserId(), commentDTO.getPost().getUserId());
        assertEquals(fakeCommentEntity.getPost().getUserId(), commentDTO.getPost().getUserId());
        assertEquals(fakeCommentEntity.getPost().getId(), commentDTO.getPost().getId());
        assertEquals(fakeCommentEntity.getPost().getTitle(), commentDTO.getPost().getTitle());
        assertEquals(fakeCommentEntity.getPost().getBody(), commentDTO.getPost().getBody());
        assertEquals(fakeCommentEntity.getBody(), commentDTO.getBody());
        assertEquals(fakeCommentEntity.getName(), commentDTO.getName());
        assertEquals(fakeCommentEntity.getEmail(), commentDTO.getEmail());
    }

    @Test
    void createComment_PostNotFoundException() {
        when(postRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.createComment(fakeCommentDTO));
    }

    @Test
    void updateComment() {
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(fakePostEntity));
        when(repository.findById(anyLong())).thenReturn(Optional.of(fakeCommentEntity));
        when(repository.save(any(CommentEntity.class))).thenReturn(fakeCommentEntity);

        CommentDTO commentDTO = service.updateComment(fakeCommentDTO.getId(), fakeCommentDTO);

        assertNotNull(commentDTO);

        assertEquals(fakeCommentEntity.getPost().getUserId(), commentDTO.getPost().getUserId());
        assertEquals(fakeCommentEntity.getPost().getUserId(), commentDTO.getPost().getUserId());
        assertEquals(fakeCommentEntity.getPost().getId(), commentDTO.getPost().getId());
        assertEquals(fakeCommentEntity.getPost().getTitle(), commentDTO.getPost().getTitle());
        assertEquals(fakeCommentEntity.getPost().getBody(), commentDTO.getPost().getBody());
        assertEquals(fakeCommentEntity.getBody(), commentDTO.getBody());
        assertEquals(fakeCommentEntity.getName(), commentDTO.getName());
        assertEquals(fakeCommentEntity.getEmail(), commentDTO.getEmail());
    }

    @Test
    void updateComment_PostNotFoundException() {
        when(postRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.updateComment(fakeCommentDTO.getId(), fakeCommentDTO));
    }

    @Test
    void updateComment_CommentNotFoundException() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.updateComment(fakeCommentDTO.getId(), fakeCommentDTO));
    }

    // todo -> I can't simulate pagination
//    @Test
//    void getAllComments() {
//        int pageSize = 10;
//        int pageNumber = 0;
//        Pageable pageable = PageRequest.of(pageNumber, pageSize);
////        PagedListHolder<CommentEntity> page = new PagedListHolder<CommentEntity>(commentEntities);
////        page.setPageSize(pageSize);
////        page.setPage(pageNumber);
////
////        when(repository.findAll(any(Pageable.class))).thenReturn(page.getPageList());
//
//        List<CommentDTO> commentDTOS = service.getAllComments(pageNumber, pageSize);
//
//        assertEquals(commentDTOS.size(), page.getSize());
//
//        for (int i = pageNumber; i < page.getSize(); i++) {
//            CommentEntity commentEntity = page.getContent().get(i);
//            CommentDTO commentDTO = commentDTOS.get(i);
//
//            assertEquals(commentEntity.getPost().getUser().getId(), commentDTO.getPost().getUser().getId());
//            assertEquals(commentEntity.getPost().getUser().getName(), commentDTO.getPost().getUser().getName());
//
//            assertEquals(commentEntity.getPost().getId(), commentDTO.getPost().getId());
//            assertEquals(commentEntity.getPost().getTitle(), commentDTO.getPost().getTitle());
//            assertEquals(commentEntity.getPost().getBody(), commentDTO.getPost().getBody());
//
//            assertEquals(commentEntity.getBody(), commentDTO.getBody());
//            assertEquals(commentEntity.getName(), commentDTO.getName());
//            assertEquals(commentEntity.getEmail(), commentDTO.getEmail());
//        }
//    }

    @Test
    void deleteCommentById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(fakeCommentEntity));
        Optional<CommentEntity> optionalCommentEntity = repository.findById(fakeCommentEntity.getId());

        assertTrue(optionalCommentEntity.isPresent());
        service.deleteCommentById(fakeCommentDTO.getId());
        verify(repository).deleteById(anyLong());
    }

    @Test
    void deleteComment_CommentNotFoundException() {
        when(postRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.deleteCommentById(1L));
    }

//    @Test
//    void saveAll() {
//        when(repository.saveAll(anyCollection())).thenReturn(commentEntities);
//
//        verify(repository.saveAll(commentEntities));
//    }
}









































