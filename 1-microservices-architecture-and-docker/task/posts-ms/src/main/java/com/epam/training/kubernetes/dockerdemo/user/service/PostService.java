package com.epam.training.kubernetes.dockerdemo.user.service;

import com.epam.training.kubernetes.dockerdemo.user.domain.model.Post;
import com.epam.training.kubernetes.dockerdemo.user.domain.repository.PostRepository;
import com.epam.training.kubernetes.dockerdemo.user.dto.PostDto;
import com.epam.training.kubernetes.dockerdemo.user.dto.PostInput;
import com.epam.training.kubernetes.dockerdemo.user.exception.NotFoundException;
import com.epam.training.kubernetes.dockerdemo.user.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final AuthorService authorService;
    private final PostMapper postMapper;

    public PostDto create(PostInput input) {
        Post entity = new Post();
        entity.setAuthorId(input.getAuthorId());
        entity.setText(input.getText());
        authorService.incrementAuthorPostsCount(input.getAuthorId());
        entity = postRepository.save(entity);
        return postMapper.postToPostDto(entity);
    }

    public PostDto getById(String id) {
        return postMapper.postToPostDto(postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post with id "+ id +", not found.")));
    }

    public PostDto update(String id, PostInput input) {
        return postRepository.findById(id)
                .map(
                        post -> {
                            post.setText(input.getText());
                            postRepository.save(post);
                            return postMapper.postToPostDto(post);
                        }
                ).orElseThrow(() -> new NotFoundException("No post with id "+ id +" exists."));
    }

    public void delete(String id) {
        postRepository.findById(id)
                .map(
                        post -> {
                            authorService.decrementAuthorPostsCount(post.getAuthorId());
                            postRepository.deleteById(post.getId());
                            return post;
                        }
                ).orElseThrow(() -> new NotFoundException("No post with id "+ id +" exists."));
    }

}
