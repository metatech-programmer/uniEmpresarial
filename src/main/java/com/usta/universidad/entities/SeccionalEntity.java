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
@Table(name = "SECCIONALES")
public class SeccionalEntity implements Serializable {

    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seccional")
    private Long idSeccional;

    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombre_seccional", unique = true)
    private String nombreSeccional;

    @NotNull
    @Column(name = "cantidad_facultad_seccional")
    private Integer cantidadFacultadSeccional;

    @NotNull
    @Column(name = "estado_seccional", columnDefinition = "boolean")
    private Boolean estadoSeccional;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "photo_seccional")
    private String photoSeccional;

    @NotNull
    @JoinColumn(name = "id_universidad", referencedColumnName = "id_universidad")
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UniversidadEntity idUniversidad;
}
