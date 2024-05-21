package it.epicode.u5w2d2pratica.service;

import it.epicode.u5w2d2pratica.exception.BlogNotFoundException;
import it.epicode.u5w2d2pratica.model.Blog;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    private List<Blog> blogs = new ArrayList<>();

    public String saveBlog(Blog blog){
        blogs.add(blog);
        return "Blog creato con id=" + blog.getId();
    }

    public List<Blog> getBlogs(){
        return blogs;
    }

    public Optional<Blog> getBlogById(int id){
        return blogs.stream().filter(blog -> blog.getId()==id).findFirst();
    }

    public Blog updateBlog(int id, Blog blogUpd){
        Optional<Blog> blogOptional = getBlogById(id);

        if(blogOptional.isPresent()){
            Blog blog = blogOptional.get();

            blog.setCategoria(blogUpd.getCategoria());
            blog.setContenuto(blogUpd.getContenuto());
            blog.setTitolo(blogUpd.getTitolo());
            blog.setTempoLettura(blogUpd.getTempoLettura());
            return blog;
        }
        else{
            throw new BlogNotFoundException("Blog non trovato");
        }
    }

    public String deleteBlog(int id){
        Optional<Blog> blogOptional = getBlogById(id);

        if(blogOptional.isPresent()){
            blogs.remove(blogOptional.get());
            return "Blog con id="+ id + " rimosso";
        }
        else{
            throw new BlogNotFoundException("Blog non trovato");
        }
    }
}
