package com.rdacompany.RDAsportsAPI.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SessionDto {

    /* Es una especie de clon de la Session pero no tiene correspondencia con la base de datos solo sirve para definir
    como quiero mostrar mis datos de una forma acotada
    Así envio los campos de la session y dos campos a mayores que me servirán para saber con que está relacionado.
    */
    //debo indicarle en que formato voy a introducir las fechas

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTime;
    private int duration;
    private int capacity;
    private String level;
    private int activity;
    private int employee;
    private int area;



}
