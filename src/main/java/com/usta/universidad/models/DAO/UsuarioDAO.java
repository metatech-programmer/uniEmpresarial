package com.usta.universidad.models.DAO;

import com.usta.universidad.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UsuarioDAO  extends CrudRepository<UsuarioEntity, Long> {
    @Transactional
    @Query("SELECT US FROM UsuarioEntity US WHERE US.correoUsuario = ?1")
    public UsuarioEntity findByEmail(String email);

    @Transactional
    @Modifying
     @Query("UPDATE UsuarioEntity SET estadoUsuario = false  WHERE idUsuario = ?1")
    public void changeState(Long idUsuario);
}
