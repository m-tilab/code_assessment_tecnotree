package io.project.controller;

import io.project.model.comment.CommentDTO;
import io.project.model.comment.mapper.CommentMapper;
import io.project.model.comment.response.GetCommentResponseModel;
import io.project.model.post.PostDTO;
import io.project.model.post.mapper.PostMapper;
import io.project.model.post.request.CreatePostRequestModel;
import io.project.model.post.request.UpdatePostRequestModel;
import io.project.model.post.response.CreatePostResponseModel;
import io.project.model.post.response.GetPostResponseModel;
import io.project.model.post.response.UpdatePostResponseModel;
import io.project.service.CommentService;
import io.project.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
@Validated
public class PostController {
    private final PostService service;
    private final CommentService commentService;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;

    public PostController(PostService service, CommentService commentService, PostMapper postMapper, CommentMapper commentMapper) {
        this.service = service;
        this.commentService = commentService;
        this.postMapper = postMapper;
        this.commentMapper = commentMapper;
    }

    @PostMapping("/")
    @Operation(summary = "creates new post")
    public CreatePostResponseModel createPost(@Valid @RequestBody CreatePostRequestModel requestModel, HttpServletResponse response) {
        PostDTO postDTO = postMapper.createPostRequestModelToDto(requestModel);
        postDTO = service.createPost(postDTO);

        response.setStatus(HttpServletResponse.SC_CREATED);
        return postMapper.dtoToCreatePostResponseModel(postDTO);
    }

    @GetMapping("")
    @Operation(summary = "returns posts with pagination")
    public List<GetPostResponseModel> getAllPosts(@RequestParam int pageNumber, @RequestParam int pageSize) {
        List<GetPostResponseModel> responseModels = new ArrayList<>();
        List<PostDTO> postDTOS = service.getAllPosts(pageNumber, pageSize);
        for (PostDTO postDTO : postDTOS)
            responseModels.add(postMapper.dtoToGetPostResponseModel(postDTO));
        return responseModels;
    }

    @GetMapping("/posts/{postId}")
    @Operation(summary = "returns a post by given id")
    public GetPostResponseModel getPostById(@PathVariable Long postId) {
        PostDTO postDTO = service.getPostById(postId);
        return postMapper.dtoToGetPostResponseModel(postDTO);
    }

    @GetMapping("posts/{postId}/comments")
    @Operation(summary = "returns all comments of a post by given id")
    public List<GetCommentResponseModel> getCommentsByPostId(@PathVariable Long postId) {
        List<GetCommentResponseModel> responseModels = new ArrayList<>();
        List<CommentDTO> commentDTOS = commentService.getAllCommentsByPostId(postId);
        for (CommentDTO commentDTO : commentDTOS)
            responseModels.add(commentMapper.dtoToGetCommentResponseModel(commentDTO));
        return responseModels;
    }

    // todo -> java.lang.IllegalArgumentException: Parameter value [\] did not match expected type [java.lang.String (n/a)] white request param
    @GetMapping("/posts")
    @Operation(summary = "returns all posts that contains a given keyword")
    public List<GetPostResponseModel> getAllPostsWithFilter(@RequestParam String keyword) {
        List<GetPostResponseModel> responseModels = new ArrayList<>();
        List<PostDTO> postDTOS = service.getAllPostsByKeyword(keyword);
        for (PostDTO postDTO : postDTOS)
            responseModels.add(postMapper.dtoToGetPostResponseModel(postDTO));
        return responseModels;
    }

    @PatchMapping("/posts/{id}")
    @Operation(summary = "updates a post by given id if it's exist")
    public UpdatePostResponseModel updatePostByPostId(@PathVariable Long id, @Valid @RequestBody UpdatePostRequestModel requestModel) {
        PostDTO postDTO = postMapper.updatePostRequestModelToDto(requestModel);
        postDTO = service.updatePost(id, postDTO);
        return postMapper.dtoToUpdatePostResponseModel(postDTO);
    }

    @DeleteMapping("posts/{postId}")
    @Operation(summary = "deletes a post by given id if it's exist")
    public void deletePostById(@PathVariable Long postId) {
        service.deletePostById(postId);
    }
}































