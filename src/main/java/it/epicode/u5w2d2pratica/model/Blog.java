package it.epicode.u5w2d2pratica.model;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Blog {
    @Id
    @GeneratedValue
    private int id;
    //private static int cont;
    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private int tempoLettura;

    @ManyToOne
    @JoinColumn(name = "autore_id")
    private Autore autore;

//    public Blog(String categoria, String titolo, String contenuto, int tempoLettura) {
//        this.categoria = categoria;
//        this.titolo = titolo;
//        this.contenuto = contenuto;
//        this.tempoLettura = tempoLettura;
//        cont++;
//        id=cont;
//        cover="https://picsum.photos/200/300";
//    }
}
