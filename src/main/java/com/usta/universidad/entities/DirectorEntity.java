package com.usta.universidad.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "DIRECTORES")
public class DirectorEntity implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_director")
    private Long idDirector;

    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "documento_director", unique = true)
    private String documentoDirector;

    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombre_director")
    private String nombreDirector;

    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "apellido_director")
    private String apellidoDirector;

    @NotNull
    @Column(name = "estado_Director", columnDefinition = "boolean")
    private Boolean estadoDirector;

    @NotNull
    @JoinColumn(name = "id_universidad")
    @OneToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UniversidadEntity idUniversidad;
}
