package it.epicode.u5w2d2pratica.service;

import it.epicode.u5w2d2pratica.exception.AutoreNotFoundException;
import it.epicode.u5w2d2pratica.model.Autore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AutoreServiceList {

    private List<Autore> autori = new ArrayList<>();

    public String saveAutore(Autore autore){
        autori.add(autore);
        return "Autore creato con id=" + autore.getId();
    }

    public List<Autore> getBlogs(){
        return autori;
    }

    public Optional<Autore> getAutore(int id){
        return autori.stream().filter(autore -> autore.getId()==id).findFirst();
    }

    public Autore updateAutore(int id, Autore autoreUpd){
        Optional<Autore> autoreOptional = getAutore(id);

        if(autoreOptional.isPresent()){
            Autore autore = autoreOptional.get();

            autore.setNome(autoreUpd.getNome());
            autore.setCognome(autoreUpd.getCognome());
            autore.setEmail(autoreUpd.getEmail());
            autore.setDataNascita(autoreUpd.getDataNascita());
            return autore;
        }
        else{
            throw new AutoreNotFoundException("Autore non trovato");
        }
    }

    public String deleteAutore(int id){
        Optional<Autore> autoreOptional = getAutore(id);

        if(autoreOptional.isPresent()){
            autori.remove(autoreOptional.get());
            return "Autore con id="+ id + " rimosso";
        }
        else{
            throw new AutoreNotFoundException("Autore non trovato");
        }
    }
}
