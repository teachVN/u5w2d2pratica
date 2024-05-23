package it.epicode.u5w2d2pratica.controller;

import it.epicode.u5w2d2pratica.dto.BlogDto;
import it.epicode.u5w2d2pratica.exception.BadRequestException;
import it.epicode.u5w2d2pratica.exception.BlogNotFoundException;
import it.epicode.u5w2d2pratica.model.Blog;
import it.epicode.u5w2d2pratica.service.BlogService;
import it.epicode.u5w2d2pratica.service.BlogServiceList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;

    @PostMapping("/api/blogPosts")
    public String saveBlog(@RequestBody @Validated BlogDto blogDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s, s2) -> s+s2));
        }

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
    public Blog updateBlog(@PathVariable int id,@RequestBody @Validated BlogDto blogDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s, s2) -> s+s2));
        }

        return blogService.updateBlog(id, blogDto);
    }
    @DeleteMapping("/api/blogPosts/{id}")
    public String deleteBlog(@PathVariable int id){

        return blogService.deleteBlog(id);
    }
    @PatchMapping("/api/blogPosts/{id}")
    public String uploadCoverBlog(@PathVariable int id,@RequestBody MultipartFile cover) throws IOException {
        return blogService.uploadCoverBlog(id, cover);
    }

}
