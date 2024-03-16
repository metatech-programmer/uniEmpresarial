package com.usta.universidad.models.DAO;

import com.usta.universidad.entities.SeccionalEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface SeccionalDAO extends CrudRepository<SeccionalEntity,Long> {
    @Transactional
    @Modifying
    @Query("UPDATE SeccionalEntity  SET estadoSeccional = false  WHERE idSeccional = ?1")
    public void changeState(Long idSeccional);

}
