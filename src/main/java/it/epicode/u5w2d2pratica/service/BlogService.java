package it.epicode.u5w2d2pratica.service;

import com.cloudinary.Cloudinary;
import it.epicode.u5w2d2pratica.dto.BlogDto;
import it.epicode.u5w2d2pratica.exception.AutoreNotFoundException;
import it.epicode.u5w2d2pratica.exception.BlogNotFoundException;
import it.epicode.u5w2d2pratica.model.Autore;
import it.epicode.u5w2d2pratica.model.Blog;
import it.epicode.u5w2d2pratica.repository.AutoreRepository;
import it.epicode.u5w2d2pratica.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private AutoreRepository autoreRepository;
    @Autowired
    private Cloudinary cloudinary;

    public String saveBlog(BlogDto blogDto){
        Optional<Autore> autoreOptional = autoreRepository.findById(blogDto.getAutoreId());

        Blog blog = new Blog();

        if(autoreOptional.isPresent()){

            blog.setContenuto(blogDto.getContenuto());
            blog.setTitolo(blogDto.getTitolo());
            blog.setCategoria(blogDto.getCategoria());
            blog.setTempoLettura(blogDto.getTempoLettura());
            blog.setCover("https://picsum.photos/200/300");
            blog.setAutore(autoreOptional.get());

            blogRepository.save(blog);
            return "Blog con id=" + blog.getId() + " salvato correttamente";
        }
        else{
            throw new AutoreNotFoundException("Autore con id=" + blog.getId()+ " non trovato");
        }
    }

    public List<Blog> getBlogs(){
        return blogRepository.findAll();
    }

    public Optional<Blog> getBlogById(int id){
        return blogRepository.findById(id);
    }

    public Blog updateBlog(int id, BlogDto blogDto){
        Optional<Blog> blogOptional = getBlogById(id);

        if(blogOptional.isPresent()){
            Blog blog = blogOptional.get();
            blog.setContenuto(blogDto.getContenuto());
            blog.setTitolo(blogDto.getTitolo());
            blog.setCategoria(blogDto.getCategoria());
            blog.setTempoLettura(blogDto.getTempoLettura());

            Optional<Autore> autoreOptional = autoreRepository.findById(blogDto.getAutoreId());

            if(autoreOptional.isPresent()){
                blog.setAutore(autoreOptional.get());
                return blogRepository.save(blog);
            }
            else{
                throw new AutoreNotFoundException("Autore con id=" + id+ " non trovato");
            }
        }
        else{
            throw new BlogNotFoundException("Blog con id=" + id+ " non trovato");
        }
    }

    public String deleteBlog(int id){
        Optional<Blog> blogOptional = getBlogById(id);

        if(blogOptional.isPresent()){
            blogRepository.delete(blogOptional.get());
            return "Blog con id=" + id + " cancellato con successo";
        }
        else{
            throw new BlogNotFoundException("Blog con id=" + id+ " non trovato");
        }
    }

    public String uploadCoverBlog(int id, MultipartFile cover) throws IOException {
        Optional<Blog> blogOptional = getBlogById(id);

        if(blogOptional.isPresent()){
            String url = (String)cloudinary.uploader().upload(cover.getBytes(), Collections.emptyMap()).get("url");
            Blog blog = blogOptional.get();
            blog.setCover(url);

            blogRepository.save(blog);
            return "Cover con url=" + url + " salvata correttamente sul blog id=" + id;
        }
        else{
            throw new BlogNotFoundException("Blog con id=" + id+ " non trovato");
        }




    }

}
