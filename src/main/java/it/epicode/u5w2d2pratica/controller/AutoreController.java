package it.epicode.u5w2d2pratica.controller;

import it.epicode.u5w2d2pratica.dto.AutoreDto;
import it.epicode.u5w2d2pratica.exception.AutoreNotFoundException;
import it.epicode.u5w2d2pratica.exception.BadRequestException;
import it.epicode.u5w2d2pratica.model.Autore;
import it.epicode.u5w2d2pratica.service.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AutoreController {
    @Autowired
    private AutoreService autoreService;

    @PostMapping("/api/autori")
    public String saveAutore(@RequestBody @Validated AutoreDto autoreDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s, s2) -> s+s2));
        }

        return autoreService.saveAutore(autoreDto);
    }

    @GetMapping("/api/autori")
    public Page<Autore> getAutori(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  @RequestParam(defaultValue = "id") String sortBy){
        return autoreService.getAutori(page, size, sortBy);
    }
    @GetMapping("/api/autori/{id}")
    public Autore getAutoreById(@PathVariable int id){
        Optional<Autore> autoreOptional = autoreService.getAutoreById(id);

        if(autoreOptional.isPresent()){
            return autoreOptional.get();
        }
        else{
            throw new AutoreNotFoundException("Autore con id=" + id+ " non trovato");
        }
    }
    @PutMapping("/api/autori/{id}")
    public Autore updateAutore(@PathVariable int id , @RequestBody @Validated AutoreDto autoreDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (s, s2) -> s+s2));
        }

       return autoreService.updateAutore(id, autoreDto);
    }
    @DeleteMapping("/api/autori/{id}")
    public String deleteAutore(@PathVariable int id){
        return autoreService.deleteAutore(id);
    }
}
