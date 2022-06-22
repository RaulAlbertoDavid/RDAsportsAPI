package com.rdacompany.RDAsportsAPI.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sessionId;
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTime;
    @Column
    private int duration;
    @Column
    private int capacity;
    @Column
    private int level;

    //Debemos establecer como se relaciona con los customers
    //Indicamos el tipo de relación, 1 sesion tendrá asociado uno o n customers, y un customer puede tener 1 o n sesions
    //CascadeType.Persist y Merge para que no permita añadir dos veces la misma relación y permita borrar sin eliminar el otro elemento asociado.
    @ManyToMany
    private List<Customer> customers;

    //Debemos establecer como se relaciona con los employees
    //Indicamos el tipo de relación, 1 sesion solo tendrán asociado un employee, pero un employee puede tener n sesiones
    // por eso es ManyToOne porque sesion es el lado n
    @ManyToOne
    //indica la columa por la que estaran relacionadas que tendra la clave ajena employee_id
    @JoinColumn(name = "employee_id")
    private Employee employee;

    //Debemos establecer como se relaciona con las areas
    //Indicamos el tipo de relación, 1 sesion solo tendrán asociado un area, pero un area puede tener n sesiones
    // por eso es ManyToOne porque sesion es el lado n
    @ManyToOne
    //indica la columa por la que estaran relacionadas que tendra la clave ajena area_id
    @JoinColumn(name = "area_id")
    private Area area;

    //Debemos establecer como se relaciona con las activities
    //Indicamos el tipo de relación, 1 sesion solo tendrán asociado una activity, pero una activity puede tener n sesiones
    // por eso es ManyToOne porque sesion es el lado n
    @ManyToOne
    //indica la columa por la que estaran relacionadas que tendra la clave ajena area_id
    @JoinColumn(name = "activity_id")
    private Activity activity;

}
