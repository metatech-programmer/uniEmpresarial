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
@Table(name = "TELEFONOS")
public class TelefonoEntity implements Serializable {

    private static long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_telefono")
    private Long idTelefono;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "numero_telefono", unique = true)
    private String numeroTelefono;

    @NotNull
    @Column(name = "estado_telefono", columnDefinition = "boolean")
    private Boolean estadoTelefono;

    @NotNull
    @JoinColumn(name = "id_universidad", referencedColumnName = "id_universidad")
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UniversidadEntity idUniversidad;

}
