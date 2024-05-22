package it.epicode.u5w2d2pratica.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class Error {

    private String message;
    private HttpStatus errorStatus;
    private LocalDateTime errorTime;
}
