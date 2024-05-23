package it.epicode.u5w2d2pratica.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AutoreDto {
    @NotBlank(message = "il nome non può essere null o vuoto o solo spazi")
    private String nome;
    @NotBlank(message = "il cognome non può essere null o vuoto o solo spazi")
    private String cognome;
    @Email
    @NotBlank(message = "l'email non può essere null o vuoto o solo spazi")
    private String email;
    private LocalDate dataNascita;
}
