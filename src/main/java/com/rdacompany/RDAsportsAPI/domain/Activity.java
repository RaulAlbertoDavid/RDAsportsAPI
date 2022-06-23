package com.rdacompany.RDAsportsAPI.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int activityId;
    @Column
    @NotNull
    private String name;
    @Column
    private String description;
    @Column
    private String material;
    @Column (name = "is_aerobic")
    private boolean isAerobic;

    //como ya está relacionado en el otro lado aquí solo indico por que objeto tiene que mapearse
    // 1 activity varias sesion, pero 1 sesion solo 1 activity
    @OneToMany(mappedBy = "activity")
    @JsonBackReference
    private List<Session> sessions;

}