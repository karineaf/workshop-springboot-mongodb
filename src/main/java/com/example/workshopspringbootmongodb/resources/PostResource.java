package com.example.workshopspringbootmongodb.resources;

import com.example.workshopspringbootmongodb.domain.Post;
import com.example.workshopspringbootmongodb.domain.User;
import com.example.workshopspringbootmongodb.dto.AuthorDTO;
import com.example.workshopspringbootmongodb.dto.PostDTO;
import com.example.workshopspringbootmongodb.dto.UserDTO;
import com.example.workshopspringbootmongodb.resources.util.URL;
import com.example.workshopspringbootmongodb.services.PostService;
import com.example.workshopspringbootmongodb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<PostDTO>> findAll() {
        List<Post> posts = postService.findAll();
        List<PostDTO> postsDTO = posts.stream().map(PostDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(postsDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PostDTO> findById(@PathVariable String id) {
        Post post = postService.findById(id);
        return ResponseEntity.ok().body(new PostDTO(post));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody PostDTO postDTO, @RequestParam String idAuthor) {
        postDTO.setDate(new Date());
        AuthorDTO authorDTO = new AuthorDTO(userService.findById(idAuthor));
        postDTO.setAuthorDTO(authorDTO);
        Post post = postService.fromDTO(postDTO);
        post = postService.insert(post);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<PostDTO> update(@PathVariable String id, @RequestBody PostDTO postDTO) {
        Post post = postService.fromDTO(postDTO);
        post.setId(id);
        post = postService.update(post);
        return ResponseEntity.ok().body(new PostDTO(post));
    }

    @RequestMapping(value = "/titlesearch", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findPostsByTitleContainingIgnoreCase(@RequestParam(value = "text", defaultValue = "") String text) {
        text = URL.decodeParam(text);
        List<Post> posts = postService.findPostsByTitleContaining(text);
        return ResponseEntity.ok().body(posts);
    }

    @RequestMapping(value = "/textanddatesearch", method = RequestMethod.GET)
    public ResponseEntity<List<Post>> findPostsByTextAndDate(@RequestParam(value = "text", defaultValue = "") String text,
                                                             @RequestParam(value = "initalDate", defaultValue = "") String initalDate,
                                                             @RequestParam(value = "finalDate", defaultValue = "") String finalDate) {
        text = URL.decodeParam(text);
        Date iDate = URL.convertDate(initalDate, new Date(0L));
        Date fDate = URL.convertDate(finalDate, new Date());
        List<Post> posts = postService.findPostsByTextAndDate(text, iDate, fDate);
        return ResponseEntity.ok().body(posts);
    }

}
