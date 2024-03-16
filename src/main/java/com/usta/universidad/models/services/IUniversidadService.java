package com.usta.universidad.models.services;

import com.usta.universidad.entities.UniversidadEntity;

import java.util.List;

public interface IUniversidadService {

    public List<UniversidadEntity> findAll();

    public void save(UniversidadEntity universidad);

    public UniversidadEntity findById(Long id);

    public void deleteById(Long id);

    public UniversidadEntity actualizarUniversidadEntity(UniversidadEntity universidad);

    public void changeState(Long id);

    public List<UniversidadEntity> selectOneUni();

    public UniversidadEntity viewDetail(Long id);
}
