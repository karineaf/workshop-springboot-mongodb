package com.example.workshopspringbootmongodb.repository;

import com.example.workshopspringbootmongodb.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findPostsByTitleContainingIgnoreCase(String title);
}
