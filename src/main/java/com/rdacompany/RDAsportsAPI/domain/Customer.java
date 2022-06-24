package com.rdacompany.RDAsportsAPI.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.awt.geom.NoninvertibleTransformException;
import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    @Column
    @NotNull
    private String name;
    @Column
    @NotNull
    private String email;
    @Column
    @NotNull
    private String password;
    @Column
    private String phone;
    @Column (name = "birth_date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    //como ya está relacionado en el otro lado aquí solo indico por que objeto tiene mapearse
    // 1 customer varias sesiones, pero 1 sesión solo 1 customer
    @JsonBackReference
    @ManyToMany(mappedBy = "customers")
    private List<Session> sessions;
}