package com.pranesh;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResources {
	
	@Autowired
	RestTemplate restTemplate;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalogItem(@PathVariable String userId){
		
		List<Rating> ratings = Arrays.asList(
				new Rating(1, 1),
				new Rating(2, 2)
		);
		
		return ratings.stream().map(rating-> {
			Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+ rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getName(), "Iron Man 1", rating.getRating());
		})
		.collect(Collectors.toList());
		
		
//		return Arrays.asList(
//				new CatalogItem("Iron Man 1", "Iron Man 1", 10)
//		);
	}
}
