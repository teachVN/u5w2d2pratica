package it.epicode.u5w2d2pratica.model;

import lombok.Data;

@Data
public class Blog {
    private int id;
    private static int cont;
    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private int tempoLettura;

    public Blog(String categoria, String titolo, String contenuto, int tempoLettura) {
        this.categoria = categoria;
        this.titolo = titolo;
        this.contenuto = contenuto;
        this.tempoLettura = tempoLettura;
        cont++;
        id=cont;
        cover="https://picsum.photos/200/300";
    }
}
