package com.usta.universidad.models.DAO;

import com.usta.universidad.entities.TelefonoEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface TelefonoDAO extends CrudRepository<TelefonoEntity,Long> {
    @Transactional
    @Modifying
    @Query("UPDATE TelefonoEntity SET estadoTelefono = false WHERE idTelefono = ?1")
    public void changeState(Long idTelefono);

}
