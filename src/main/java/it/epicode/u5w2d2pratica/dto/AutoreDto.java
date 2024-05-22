package it.epicode.u5w2d2pratica.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AutoreDto {

    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataNascita;
}
