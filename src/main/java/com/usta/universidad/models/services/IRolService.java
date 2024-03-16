package com.usta.universidad.models.services;

import java.util.List;

import com.usta.universidad.entities.RolEntity;

public interface IRolService {

    public List<RolEntity> findAll();

    public void save(RolEntity rol);

    public RolEntity findById(Long id);

    public void deleteById(Long id);

    public RolEntity actualizarRolEntity(RolEntity rol);

}
