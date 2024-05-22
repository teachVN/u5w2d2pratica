package it.epicode.u5w2d2pratica.controller;

import it.epicode.u5w2d2pratica.dto.BlogDto;
import it.epicode.u5w2d2pratica.exception.BlogNotFoundException;
import it.epicode.u5w2d2pratica.model.Blog;
import it.epicode.u5w2d2pratica.service.BlogService;
import it.epicode.u5w2d2pratica.service.BlogServiceList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;

    @PostMapping("/api/blogPosts")
    public String saveBlog(@RequestBody BlogDto blogDto){

        return blogService.saveBlog(blogDto);
    }

    @GetMapping("/api/blogPosts")
    public List<Blog> getBlogs(){

        return blogService.getBlogs();
    }
    @GetMapping("/api/blogPosts/{id}")
    public Blog getBlogById(@PathVariable int id){
        Optional<Blog> blogOptional = blogService.getBlogById(id);

        if(blogOptional.isPresent()){
            return blogOptional.get();
        }
        else{
            throw new BlogNotFoundException("Blog non trovato");
        }
    }
    @PutMapping("/api/blogPosts/{id}")
    public Blog updateBlog(@PathVariable int id,@RequestBody BlogDto blogDto){

        return blogService.updateBlog(id, blogDto);
    }
    @DeleteMapping("/api/blogPosts/{id}")
    public String deleteBlog(@PathVariable int id){

        return blogService.deleteBlog(id);
    }


}
