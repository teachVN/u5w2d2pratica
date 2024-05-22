package it.epicode.u5w2d2pratica.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Autore {
    @Id
    @GeneratedValue
    private int id;
    //private static int cont;
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataNascita;
    private String avatar;

    @OneToMany(mappedBy = "autore")
    private List<Blog> blogs;

//    public Autore(String nome, String cognome, String email, LocalDate dataNascita) {
//        this.nome = nome;
//        this.cognome = cognome;
//        this.email = email;
//        this.dataNascita = dataNascita;
//        cont++;
//        id=cont;
//        avatar="https://ui-avatars.com/api/?name="+nome+"+"+cognome;
//    }
}
