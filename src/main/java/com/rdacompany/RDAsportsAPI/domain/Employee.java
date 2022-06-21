package com.rdacompany.RDAsportsAPI.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private String name;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String phone;
    @Column
    private Boolean active;

    //como ya está relacionado en el otro lado aquí solo indico por que objeto tiene que mapearse
    // 1 employee varias sesion, pero 1 sesion solo 1 employee
    @OneToMany(mappedBy = "employee")
    private List<Session> sessions;
}