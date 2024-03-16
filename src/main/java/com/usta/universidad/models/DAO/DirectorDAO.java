package com.usta.universidad.models.DAO;

import com.usta.universidad.entities.DirectorEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface DirectorDAO extends CrudRepository<DirectorEntity , Long> {

    @Transactional
    @Modifying
    @Query("UPDATE DirectorEntity  SET estadoDirector = false  WHERE idDirector = ?1")
    public void changeState(Long idDirector);
    
}
