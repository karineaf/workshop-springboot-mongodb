package com.example.workshopspringbootmongodb.resources;

import com.example.workshopspringbootmongodb.domain.Post;
import com.example.workshopspringbootmongodb.resources.util.URL;
import com.example.workshopspringbootmongodb.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findAll() {
        List<Post> posts = service.findAll();
        return ResponseEntity.ok().body(posts);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post post = service.findById(id);
        return ResponseEntity.ok().body(post);
    }

//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<Void> insert(@RequestBody Post Post) {
//        Post Post = service.fromDTO(Post);
//        Post = service.insert(Post);
//        //pega o endereço do Post inserido
//        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Post.getId()).toUri();
//        return ResponseEntity.created(uri).build();
//    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post post) {
//        Post newPost = new Post();
//        newPost.setId(id);
//        newPost = service.update(post);
//        return ResponseEntity.ok().body(newPost);
//    }

    @RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findPostsByTitleContainingIgnoreCase(@RequestParam(value = "text", defaultValue = "") String text) {
        text = URL.decodeParam(text);
        List<Post> posts = service.findPostsByTitleContaining(text);
        return ResponseEntity.ok().body(posts);
    }

}
