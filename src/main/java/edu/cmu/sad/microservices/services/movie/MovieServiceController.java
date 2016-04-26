package edu.cmu.sad.microservices.services.movie;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieServiceController {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/movie")
    public String getMovie (@RequestParam (value = "t" , defaultValue = "Batman") String name,
                            @RequestParam (value = "y" , defaultValue = "2016") String year) {
        restTemplate = new RestTemplate();
        JsonNode a = restTemplate.getForObject("http://www.omdbapi.com/?t="+name+"&y="+year+"&plot=short&r=json",JsonNode.class);

        return a.toString();
    }
    @RequestMapping("/movie/{name}")
    public String getMovieByName (@PathVariable("name") String name, @RequestParam (value = "y" , defaultValue = "2016") String year) {
        restTemplate = new RestTemplate();
        JsonNode a = restTemplate.getForObject("http://www.omdbapi.com/?t="+name+"&y="+year+"&plot=short&r=json",JsonNode.class);

        return a.toString();
    }
}

