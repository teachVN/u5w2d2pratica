package it.epicode.u5w2d2pratica.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BlogDto {


    private String categoria;
    @NotNull(message = "Il titolo non può essere null")
    private String titolo;
    private String cover;
    @NotNull(message = "Il contenuto non può essere null")
    private String contenuto;
    private int tempoLettura;
    @NotNull(message = "L'autore non può essere null")
    private int autoreId;
}
