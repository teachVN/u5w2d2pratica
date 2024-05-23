package it.epicode.u5w2d2pratica.service;

import it.epicode.u5w2d2pratica.dto.AutoreDto;
import it.epicode.u5w2d2pratica.exception.AutoreNotFoundException;
import it.epicode.u5w2d2pratica.model.Autore;
import it.epicode.u5w2d2pratica.repository.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutoreService {
    @Autowired
    private AutoreRepository autoreRepository;
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public String saveAutore(AutoreDto autoreDto){
        Autore autore = new Autore();
        autore.setNome(autoreDto.getNome());
        autore.setCognome(autoreDto.getCognome());
        autore.setEmail(autoreDto.getEmail());
        autore.setDataNascita(autoreDto.getDataNascita());
        autore.setAvatar("https://ui-avatars.com/api/?name="+autore.getNome()+"+"+autore.getCognome());

        autoreRepository.save(autore);

        sendMail(autore.getEmail());

        return "Autore con id=" + autore.getId() + " salvato correttamente";
    }

    public Page<Autore> getAutori(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return autoreRepository.findAll(pageable);
    }

    public Optional<Autore> getAutoreById(int id){
        return autoreRepository.findById(id);
    }

    public Autore updateAutore(int id, AutoreDto autoreDto){
        Optional<Autore> autoreOptional = getAutoreById(id);

        if(autoreOptional.isPresent()){
            Autore autore = autoreOptional.get();
            autore.setNome(autoreDto.getNome());
            autore.setCognome(autoreDto.getCognome());
            autore.setEmail(autoreDto.getEmail());
            autore.setDataNascita(autoreDto.getDataNascita());

            return autoreRepository.save(autore);
        }
        else{
            throw new AutoreNotFoundException("Autore con id=" + id+ " non trovato");
        }
    }

    public String deleteAutore(int id){
        Optional<Autore> autoreOptional = getAutoreById(id);

        if(autoreOptional.isPresent()){
            autoreRepository.delete(autoreOptional.get());
            return "Autore con id=" + id + " cancellato correttamente";
        }
        else{
            throw new AutoreNotFoundException("Autore con id=" + id+ " non trovato");
        }
    }

    private void sendMail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Registrazione Servizio rest");
        message.setText("Registrazione al servizio rest avvenuta con successo");

        javaMailSender.send(message);
    }
}
