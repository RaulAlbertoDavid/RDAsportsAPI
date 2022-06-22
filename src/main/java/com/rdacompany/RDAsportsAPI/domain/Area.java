package com.rdacompany.RDAsportsAPI.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "areas")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int areaId;
    @Column
    private int number;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private boolean outdoor;

    //como ya está relacionado en el otro lado aquí solo indico por que objeto tiene que mapearse
    // 1 area varias sesion, pero 1 sesion solo 1 area
    @OneToMany(mappedBy = "area")
    @JsonBackReference
    private List<Session> sessions;
    
}