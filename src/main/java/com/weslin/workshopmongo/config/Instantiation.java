package com.weslin.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.weslin.workshopmongo.domain.Post;
import com.weslin.workshopmongo.domain.User;
import com.weslin.workshopmongo.dto.AuthorDTO;
import com.weslin.workshopmongo.dto.CommentDTO;
import com.weslin.workshopmongo.repository.PostRepository;
import com.weslin.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User nerylda = new User(null, "Nerylda", "tetosolar@gmail.com");
		User enzin = new User(null, "Enzoido", "cariocafudido@gmail.com");
		User clebin = new User(null, "CobGamer", "clebin@gmail.com");
		
		userRepository.saveAll(Arrays.asList(nerylda, enzin, clebin));
		
		Post post1 = new Post(null, sdf.parse("10/06/2021"), "Twittando Parte 1", "Estou twittando", new AuthorDTO(nerylda));
		Post post2 = new Post(null, sdf.parse("10/06/2021"), "Twittando Parte 2", "Acordei, vou twittar", new AuthorDTO(nerylda));
		Post post3 = new Post(null, sdf.parse("10/06/2021"), "Galo", "GALUDO GALUDO GALUDO GALUDO", new AuthorDTO(clebin));
		Post post4 = new Post(null, sdf.parse("10/06/2021"), "Para o Weslin", "Te amo meu nego", new AuthorDTO(clebin));
		Post post5 = new Post(null, sdf.parse("12/06/2021"), "FLU x FLA", "Tenho que concordar, FLA >>> FLU", new AuthorDTO(enzin));

		CommentDTO c1 = new CommentDTO("SAI DO TWITTER POHA", sdf.parse("10/06/2021"), new AuthorDTO(clebin));
		CommentDTO c2 = new CommentDTO("DE NOVO CRL??", sdf.parse("10/06/2021"), new AuthorDTO(clebin));
		CommentDTO c3 = new CommentDTO("Seu sonho!", sdf.parse("10/06/2021"), new AuthorDTO(enzin));
		CommentDTO c4 = new CommentDTO("Seu sonho!/2", sdf.parse("10/06/2021"), new AuthorDTO(nerylda));
		
		post1.getComments().addAll(Arrays.asList(c1));
		post2.getComments().addAll(Arrays.asList(c2));
		post3.getComments().addAll(Arrays.asList(c3, c4));
		
		postRepository.saveAll(Arrays.asList(post1, post2, post3, post4, post5));
		
		nerylda.getPosts().addAll(Arrays.asList(post1, post2));
		enzin.getPosts().addAll(Arrays.asList(post5));
		clebin.getPosts().addAll(Arrays.asList(post3, post5));
		
		userRepository.saveAll(Arrays.asList(nerylda, enzin, clebin));
	}

}
