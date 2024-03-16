package com.usta.universidad.entities;

import java.util.Collection;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "USUARIOS")
public class UsuarioEntity {

    private static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nombres_usuario")
    private String nombresUsuario;

    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "apellidos_usuario")
    private String apellidosUsuario;

    @Column(name = "estado_usuario", columnDefinition = "boolean")
    private Boolean estadoUsuario;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "correo_usuario", unique = true)
    private String correoUsuario;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "clave_usuario")
    private String claveUsuario;

    @NotNull
    @JoinColumn(name = "id_director", referencedColumnName = "id_director")
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private DirectorEntity id_director;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Usuarios_roles", joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "id_rol"))
    private Collection<RolEntity> role;

}
