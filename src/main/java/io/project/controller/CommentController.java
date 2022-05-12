package io.project.controller;

import io.project.exception.RequiredRequestParameterException;
import io.project.model.comment.CommentDTO;
import io.project.model.comment.mapper.CommentMapper;
import io.project.model.comment.request.CreateCommentRequestModel;
import io.project.model.comment.request.UpdateCommentRequestModel;
import io.project.model.comment.response.CreateCommentResponseModel;
import io.project.model.comment.response.GetCommentResponseModel;
import io.project.model.comment.response.UpdateCommentResponseModel;
import io.project.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
@Valid
public class CommentController {
    private final CommentService service;
    private final CommentMapper commentMapper;

    public CommentController(CommentService service, CommentMapper commentMapper) {
        this.service = service;
        this.commentMapper = commentMapper;
    }

    @PostMapping("")
    @Operation(summary = "creates a comments and returns it")
    public CreateCommentResponseModel createComment(@Valid @RequestBody CreateCommentRequestModel requestModel, HttpServletResponse response) {
        CommentDTO commentDTO = commentMapper.createCommentRequestModelToDto(requestModel);
        commentDTO = service.createComment(commentDTO);

        response.setStatus(HttpServletResponse.SC_CREATED);
        return commentMapper.dtoToCreateCommentResponseModel(commentDTO);
    }

    @PatchMapping("/comments/{id}")
    @Operation(summary = "updates a comment by given id if it's exist")
    public UpdateCommentResponseModel updateComment(@PathVariable Long id, @Valid @RequestBody UpdateCommentRequestModel requestModel) {
        CommentDTO commentDTO = commentMapper.updateCommentRequestModelToDto(requestModel);
        commentDTO = service.updateComment(id, commentDTO);
        return commentMapper.dtoToUpdateCommentResponseModel(commentDTO);
    }

    @GetMapping("")
    @Operation(summary = "returns all comments by pagination")
    public List<GetCommentResponseModel> getAllComments(@RequestParam int pageNumber, @RequestParam int pageSize) {
        List<GetCommentResponseModel> responseModels = new ArrayList<>();
        List<CommentDTO> commentDTOS = service.getAllComments(pageNumber, pageSize);
        for (CommentDTO commentDTO : commentDTOS)
            responseModels.add(commentMapper.dtoToGetCommentResponseModel(commentDTO));
        return responseModels;
    }

    @GetMapping("/comments")
    @Operation(summary = "returns all comments of a post by id of that post")
    public List<GetCommentResponseModel> getAllCommentsByPostId(@RequestParam Long postId) {
        if (postId == null)
            throw new RequiredRequestParameterException("postId");
        List<GetCommentResponseModel> responseModels = new ArrayList<>();
        List<CommentDTO> commentDTOS = service.getAllCommentsByPostId(postId);
        for (CommentDTO commentDTO : commentDTOS)
            responseModels.add(commentMapper.dtoToGetCommentResponseModel(commentDTO));
        return responseModels;
    }

    @DeleteMapping("/comments/{commentId}")
    @Operation(summary = "deletes a comment by given id")
    public void deleteCommentById(@PathVariable Long commentId) {
        service.deleteCommentById(commentId);
    }

}

















