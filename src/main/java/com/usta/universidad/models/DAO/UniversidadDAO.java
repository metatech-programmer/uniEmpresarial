package com.usta.universidad.models.DAO;

import com.usta.universidad.entities.UniversidadEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UniversidadDAO  extends CrudRepository<UniversidadEntity,Long> {
    @Transactional
    @Modifying
    @Query("UPDATE UniversidadEntity  SET estadoUniversidad = false  WHERE idUniversidad = ?1")
    public void changeState(Long idUniversidad);

    @Transactional
    @Query("SELECT uni FROM UniversidadEntity  uni WHERE uni.idUniversidad NOT IN" +
            "( SELECT  dir.idUniversidad.idUniversidad FROM DirectorEntity dir)")
    List<UniversidadEntity> selectOneUni();

    @Transactional
    @Query("SELECT uni FROM UniversidadEntity  uni WHERE uni.idUniversidad = ?1")
    public UniversidadEntity viewDetail(Long id);
}
