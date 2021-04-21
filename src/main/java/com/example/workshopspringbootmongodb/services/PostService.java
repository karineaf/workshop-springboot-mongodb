package com.example.workshopspringbootmongodb.services;

import com.example.workshopspringbootmongodb.domain.Post;
import com.example.workshopspringbootmongodb.repository.PostRepository;
import com.example.workshopspringbootmongodb.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public List<Post> findAll(){
        return repository.findAll();
    }

    public Post findById(String id) {
        Optional<Post> post = repository.findById(id);
        return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public Post insert(Post post){
        return repository.insert(post);
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public Post update(Post post) {
        Post newPost = findById(post.getId());
        updateData(newPost, post);
        return repository.save(newPost);
    }

    private void updateData(Post newPost, Post post) {
        newPost.setBody(post.getTitle());
        newPost.setBody(post.getBody());
    }

    public List<Post> findPostsByTitleContaining(String title) {
        return repository.findPostsByTitleContainingIgnoreCase(title);
    }

//    public Post fromDTO(PostDTO PostDTO){
//        return new Post(PostDTO.getId(), PostDTO.getName(), PostDTO.getEmail());
//    }

}
