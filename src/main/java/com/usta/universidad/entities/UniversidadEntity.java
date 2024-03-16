package com.usta.universidad.entities;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "UNIVERSIDADES")
public class UniversidadEntity implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_universidad")
    private Long idUniversidad;

    @NotNull
    @Size(min = 1, max = 13)
    @Column(name = "nit_universidad", unique = true)
    private String nitUniversidad;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre_universidad")
    private String nombreUniversidad;

    @NotNull
    @DateTimeFormat(pattern = "yyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_fundacion")
    private Date fechaFundacionUniversidad;
    
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "direccion_universidad")
    private String direccionUniversidad;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "photo_universidad")
    private String photoUniversidad;

    @NotNull
    @Column(name = "estado_universidad", columnDefinition = "boolean")
    private Boolean estadoUniversidad;

    @OneToOne(mappedBy = "idUniversidad")
    private DirectorEntity director;
}
