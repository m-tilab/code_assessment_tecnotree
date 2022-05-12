package io.project;

import io.project.apiService.dao.CommentDao;
import io.project.apiService.dao.PostDao;
import io.project.apiService.dao.TodoDao;
import io.project.model.comment.CommentDTO;
import io.project.model.comment.CommentResponse;
import io.project.model.comment.mapper.CommentMapper;
import io.project.model.post.PostDTO;
import io.project.model.post.PostEntity;
import io.project.model.post.PostResponse;
import io.project.model.post.mapper.PostMapper;
import io.project.model.todo.TodoDTO;
import io.project.model.todo.TodoResponse;
import io.project.model.todo.mapper.TodoMapper;
import io.project.repository.TodoRepository;
import io.project.service.CommentService;
import io.project.service.PostService;
import io.project.service.TodoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class ProjectApplication
        implements CommandLineRunner {
    private final TodoDao todoDao;
    private final PostDao postDao;
    private final CommentDao commentDao;

    private final TodoService todoService;
    private final PostService postService;
    private final CommentService commentService;

    private final TodoMapper todoMapper;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;

    private final TodoRepository repository;

    public ProjectApplication(TodoDao todoDao, PostDao postDao, CommentDao commentDao, TodoService todoService, PostService postService, CommentService commentService, TodoMapper todoMapper, PostMapper postMapper, CommentMapper commentMapper, TodoRepository repository) {
        this.todoDao = todoDao;
        this.postDao = postDao;
        this.commentDao = commentDao;
        this.todoService = todoService;
        this.postService = postService;
        this.commentService = commentService;
        this.todoMapper = todoMapper;
        this.postMapper = postMapper;
        this.commentMapper = commentMapper;
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

    @Override
    public void run(String... args) {
        List<TodoResponse> todoResponses = todoDao.initAllTodos();
        List<TodoDTO> todoDTOs = new ArrayList<>();
        for (TodoResponse todoResponse : todoResponses) {
            TodoDTO todoDTO = todoMapper.todoResponseToTodoDto(todoResponse);
            todoDTOs.add(todoDTO);
        }
        todoService.saveAll(todoDTOs);

        List<PostResponse> postResponses = postDao.initAllPosts();
        List<PostDTO> postDTOs = new ArrayList<>();
        for (PostResponse postResponse : postResponses) {
            PostDTO postDTO = postMapper.postResponseToPostDto(postResponse);
            postDTOs.add(postDTO);
        }
        postService.saveAll(postDTOs);

        List<CommentResponse> commentResponses = commentDao.initAllComments();
        List<CommentDTO> commentDTOs = new ArrayList<>();
        for (CommentResponse commentResponse : commentResponses) {
            PostDTO postDTO = postService.getPostById(commentResponse.getPostId());
            PostEntity postEntity = postMapper.dtoToEntity(postDTO);
            CommentDTO commentDTO = commentMapper.commentResponseToDto(commentResponse);
            commentDTO.setPost(postEntity);
            commentDTOs.add(commentDTO);
        }
        commentService.saveAll(commentDTOs);
    }
}











