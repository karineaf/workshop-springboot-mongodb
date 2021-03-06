package com.example.workshopspringbootmongodb.config;

import com.example.workshopspringbootmongodb.domain.Post;
import com.example.workshopspringbootmongodb.domain.User;
import com.example.workshopspringbootmongodb.dto.AuthorDTO;
import com.example.workshopspringbootmongodb.dto.CommentDTO;
import com.example.workshopspringbootmongodb.repository.PostRepository;
import com.example.workshopspringbootmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");
        userRepository.saveAll(Arrays.asList(maria, alex, bob));

        Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para Sao Paulo. Abracos!", new AuthorDTO(maria));
        Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje", new AuthorDTO(maria));
        Post post3 = new Post(null, sdf.parse("21/05/2018"), "Partiu viagem", "Vou viajar para Belo Horizonte. Beijos!", new AuthorDTO(alex));
        Post post4 = new Post(null, sdf.parse("15/06/2018"), "Simbora", "Vou para o Rio de Janeiro. Beijos!", new AuthorDTO(bob));
        postRepository.saveAll(Arrays.asList(post1, post2, post3, post4));

        CommentDTO comment1 = new CommentDTO("Boa viagem!", sdf.parse("22/03/2018"), new AuthorDTO(alex));
        CommentDTO comment2 = new CommentDTO("Aproveite bem", sdf.parse("23/03/2018"), new AuthorDTO(bob));
        CommentDTO comment3 = new CommentDTO("Tenha um otimo dia", sdf.parse("24/03/2018"), new AuthorDTO(maria));
        CommentDTO comment4 = new CommentDTO("Boa viagem, mano!", sdf.parse("15/06/2018"), new AuthorDTO(alex));
        post1.getComments().addAll(Arrays.asList(comment1, comment2));
        post2.getComments().add(comment3);
        post4.getComments().add(comment4);
        postRepository.saveAll(Arrays.asList(post1, post2, post3, post4));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);
    }
}
