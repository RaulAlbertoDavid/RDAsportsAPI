package com.rdacompany.RDAsportsAPI.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;
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
    @Column
    private Boolean active;

    //como ya está relacionado en el otro lado aquí solo indico por que objeto tiene que mapearse
    // 1 employee varias sesion, pero 1 sesion solo 1 employee
    @OneToMany(mappedBy = "employee")
    @JsonBackReference
    private List<Session> sessions;
}