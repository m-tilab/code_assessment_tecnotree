package io.project.service;


import io.project.model.post.PostDTO;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDTO);

    PostDTO updatePost(Long postId, PostDTO postDTO);

    List<PostDTO> getAllPosts(int pageNumber, int pageSize);

    PostDTO getPostById(Long postId);

    List<PostDTO> getAllPostsByKeyword(String keyword);

    void deletePostById(Long postId);

    void saveAll(List<PostDTO> postDTOS);

}














