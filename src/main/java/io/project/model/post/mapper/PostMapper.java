package io.project.model.post.mapper;

import io.project.model.post.PostDTO;
import io.project.model.post.PostEntity;
import io.project.model.post.PostResponse;
import io.project.model.post.request.CreatePostRequestModel;
import io.project.model.post.request.UpdatePostRequestModel;
import io.project.model.post.response.CreatePostResponseModel;
import io.project.model.post.response.GetPostResponseModel;
import io.project.model.post.response.UpdatePostResponseModel;
import io.project.model.todo.TodoDTO;
import io.project.model.todo.TodoResponse;

public interface PostMapper {
    PostEntity dtoToEntity(PostDTO dto);

    PostDTO entityToDto(PostEntity entity);

    PostDTO createPostRequestModelToDto(CreatePostRequestModel requestModel);

    PostDTO updatePostRequestModelToDto(UpdatePostRequestModel requestModel);

    CreatePostResponseModel dtoToCreatePostResponseModel(PostDTO dto);

    GetPostResponseModel dtoToGetPostResponseModel(PostDTO dto);

    UpdatePostResponseModel dtoToUpdatePostResponseModel(PostDTO dto);

    PostDTO postResponseToPostDto(PostResponse postResponse);
}













