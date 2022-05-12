package io.project.model.comment.mapper;

import io.project.model.comment.CommentDTO;
import io.project.model.comment.CommentEntity;
import io.project.model.comment.CommentResponse;
import io.project.model.comment.request.CreateCommentRequestModel;
import io.project.model.comment.request.UpdateCommentRequestModel;
import io.project.model.comment.response.CreateCommentResponseModel;
import io.project.model.comment.response.GetCommentResponseModel;
import io.project.model.comment.response.UpdateCommentResponseModel;
import org.mapstruct.Mapper;

public interface CommentMapper {

    CommentEntity dtoToEntity(CommentDTO dto);

    CommentDTO entityToDto(CommentEntity entity);

    CommentDTO createCommentRequestModelToDto(CreateCommentRequestModel requestModel);

    CommentDTO updateCommentRequestModelToDto(UpdateCommentRequestModel requestModel);

    CreateCommentResponseModel dtoToCreateCommentResponseModel(CommentDTO dto);

    GetCommentResponseModel dtoToGetCommentResponseModel(CommentDTO dto);

    UpdateCommentResponseModel dtoToUpdateCommentResponseModel(CommentDTO dto);

    CommentDTO commentResponseToDto(CommentResponse commentResponse);
}




















