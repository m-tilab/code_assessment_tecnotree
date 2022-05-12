package io.project.model.post.mapper;

import io.project.model.post.PostDTO;
import io.project.model.post.PostEntity;
import io.project.model.post.PostResponse;
import io.project.model.post.request.CreatePostRequestModel;
import io.project.model.post.request.UpdatePostRequestModel;
import io.project.model.post.response.CreatePostResponseModel;
import io.project.model.post.response.GetPostResponseModel;
import io.project.model.post.response.UpdatePostResponseModel;
import org.springframework.stereotype.Component;

@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public PostEntity dtoToEntity(PostDTO dto) {
        if (dto == null) {
            return null;
        }

        PostEntity postEntity = new PostEntity();

        postEntity.setId(dto.getId());
        postEntity.setUserId(dto.getUserId());
        postEntity.setTitle(dto.getTitle());
        postEntity.setBody(dto.getBody());

        return postEntity;
    }

    @Override
    public PostDTO entityToDto(PostEntity entity) {
        if (entity == null) {
            return null;
        }

        PostDTO postDTO = new PostDTO();

        postDTO.setId(entity.getId());
        postDTO.setUserId(entity.getUserId());
        postDTO.setTitle(entity.getTitle());
        postDTO.setBody(entity.getBody());

        return postDTO;
    }

    @Override
    public PostDTO createPostRequestModelToDto(CreatePostRequestModel requestModel) {
        if (requestModel == null) {
            return null;
        }

        PostDTO postDTO = new PostDTO();

        postDTO.setUserId(requestModel.getUserId());
        postDTO.setTitle(requestModel.getTitle());
        postDTO.setBody(requestModel.getBody());

        return postDTO;
    }

    @Override
    public PostDTO updatePostRequestModelToDto(UpdatePostRequestModel requestModel) {
        if (requestModel == null) {
            return null;
        }

        PostDTO postDTO = new PostDTO();

        postDTO.setId(requestModel.getId());
        postDTO.setUserId(requestModel.getUserId());
        postDTO.setTitle(requestModel.getTitle());
        postDTO.setBody(requestModel.getBody());

        return postDTO;
    }

    @Override
    public CreatePostResponseModel dtoToCreatePostResponseModel(PostDTO dto) {
        if (dto == null) {
            return null;
        }

        CreatePostResponseModel createPostResponseModel = new CreatePostResponseModel();

        createPostResponseModel.setUserId(dto.getUserId());
        createPostResponseModel.setId(dto.getId());
        createPostResponseModel.setTitle(dto.getTitle());
        createPostResponseModel.setBody(dto.getBody());

        return createPostResponseModel;
    }

    @Override
    public GetPostResponseModel dtoToGetPostResponseModel(PostDTO dto) {
        if (dto == null) {
            return null;
        }

        GetPostResponseModel getPostResponseModel = new GetPostResponseModel();

        getPostResponseModel.setId(dto.getId());
        getPostResponseModel.setUserId(dto.getUserId());
        getPostResponseModel.setTitle(dto.getTitle());
        getPostResponseModel.setBody(dto.getBody());

        return getPostResponseModel;
    }

    @Override
    public UpdatePostResponseModel dtoToUpdatePostResponseModel(PostDTO dto) {
        if (dto == null) {
            return null;
        }

        UpdatePostResponseModel updatePostResponseModel = new UpdatePostResponseModel();

        updatePostResponseModel.setId(dto.getId());
        updatePostResponseModel.setUserId(dto.getUserId());
        updatePostResponseModel.setTitle(dto.getTitle());
        updatePostResponseModel.setBody(dto.getBody());

        return updatePostResponseModel;
    }

    @Override
    public PostDTO postResponseToPostDto(PostResponse postResponse) {
        if (postResponse == null) {
            return null;
        }

        PostDTO postDTO = new PostDTO();

        postDTO.setId(postResponse.getId());
        postDTO.setUserId(postResponse.getUserId());
        postDTO.setTitle(postResponse.getTitle());
        postDTO.setBody(postResponse.getBody());

        return postDTO;
    }
}
