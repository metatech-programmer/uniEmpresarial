package com.usta.universidad.models.services;

import com.usta.universidad.entities.SeccionalEntity;

import java.util.List;

public interface ISeccionalService {
    
    public List<SeccionalEntity> findAll();

    public void save(SeccionalEntity seccional);

    public SeccionalEntity findById(Long id);

    public void deleteById(Long id);

    public SeccionalEntity actualizarSeccionalEntity(SeccionalEntity seccional);

    public void changeState(Long id);
}
