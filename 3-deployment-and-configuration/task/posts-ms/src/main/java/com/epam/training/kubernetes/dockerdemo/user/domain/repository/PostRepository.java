package com.epam.training.kubernetes.dockerdemo.user.domain.repository;


import com.epam.training.kubernetes.dockerdemo.user.domain.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findByAuthorId(Long authorId);

}
