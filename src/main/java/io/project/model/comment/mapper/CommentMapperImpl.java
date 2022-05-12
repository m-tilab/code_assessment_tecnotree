package io.project.model.comment.mapper;

import io.project.model.comment.CommentDTO;
import io.project.model.comment.CommentEntity;
import io.project.model.comment.CommentResponse;
import io.project.model.comment.request.CreateCommentRequestModel;
import io.project.model.comment.request.UpdateCommentRequestModel;
import io.project.model.comment.response.CreateCommentResponseModel;
import io.project.model.comment.response.GetCommentResponseModel;
import io.project.model.comment.response.UpdateCommentResponseModel;
import org.springframework.stereotype.Component;

@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentEntity dtoToEntity(CommentDTO dto) {
        if (dto == null) {
            return null;
        }

        CommentEntity commentEntity = new CommentEntity();

        commentEntity.setId(dto.getId());
        commentEntity.setPost(dto.getPost());
        commentEntity.setName(dto.getName());
        commentEntity.setEmail(dto.getEmail());
        commentEntity.setBody(dto.getBody());

        return commentEntity;
    }

    @Override
    public CommentDTO entityToDto(CommentEntity entity) {
        if (entity == null) {
            return null;
        }

        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setId(entity.getId());
        commentDTO.setPost(entity.getPost());
        commentDTO.setName(entity.getName());
        commentDTO.setEmail(entity.getEmail());
        commentDTO.setBody(entity.getBody());

        return commentDTO;
    }

    @Override
    public CommentDTO createCommentRequestModelToDto(CreateCommentRequestModel requestModel) {
        if (requestModel == null) {
            return null;
        }

        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setPost(requestModel.getPost());
        commentDTO.setName(requestModel.getName());
        commentDTO.setEmail(requestModel.getEmail());
        commentDTO.setBody(requestModel.getBody());

        return commentDTO;
    }

    @Override
    public CommentDTO updateCommentRequestModelToDto(UpdateCommentRequestModel requestModel) {
        if (requestModel == null) {
            return null;
        }

        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setId(requestModel.getId());
        commentDTO.setPost(requestModel.getPost());
        commentDTO.setName(requestModel.getName());
        commentDTO.setEmail(requestModel.getEmail());
        commentDTO.setBody(requestModel.getBody());

        return commentDTO;
    }

    @Override
    public CreateCommentResponseModel dtoToCreateCommentResponseModel(CommentDTO dto) {
        if (dto == null) {
            return null;
        }

        CreateCommentResponseModel createCommentResponseModel = new CreateCommentResponseModel();

        createCommentResponseModel.setId(dto.getId());
        createCommentResponseModel.setPost(dto.getPost());
        createCommentResponseModel.setName(dto.getName());
        createCommentResponseModel.setEmail(dto.getEmail());
        createCommentResponseModel.setBody(dto.getBody());

        return createCommentResponseModel;
    }

    @Override
    public GetCommentResponseModel dtoToGetCommentResponseModel(CommentDTO dto) {
        if (dto == null) {
            return null;
        }

        GetCommentResponseModel getCommentResponseModel = new GetCommentResponseModel();

        getCommentResponseModel.setId(dto.getId());
        getCommentResponseModel.setPost(dto.getPost());
        getCommentResponseModel.setName(dto.getName());
        getCommentResponseModel.setEmail(dto.getEmail());
        getCommentResponseModel.setBody(dto.getBody());

        return getCommentResponseModel;
    }

    @Override
    public UpdateCommentResponseModel dtoToUpdateCommentResponseModel(CommentDTO dto) {
        if (dto == null) {
            return null;
        }

        UpdateCommentResponseModel updateCommentResponseModel = new UpdateCommentResponseModel();

        updateCommentResponseModel.setId(dto.getId());
        updateCommentResponseModel.setPost(dto.getPost());
        updateCommentResponseModel.setName(dto.getName());
        updateCommentResponseModel.setEmail(dto.getEmail());
        updateCommentResponseModel.setBody(dto.getBody());

        return updateCommentResponseModel;
    }

    @Override
    public CommentDTO commentResponseToDto(CommentResponse commentResponse) {
        if (commentResponse == null) {
            return null;
        }

        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setId(commentResponse.getId());
        commentDTO.setPost(null);
        commentDTO.setName(commentResponse.getName());
        commentDTO.setEmail(commentResponse.getEmail());
        commentDTO.setBody(commentResponse.getBody());

        return commentDTO;
    }
}
