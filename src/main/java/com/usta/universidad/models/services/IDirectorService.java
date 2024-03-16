package com.usta.universidad.models.services;

import com.usta.universidad.entities.DirectorEntity;

import java.util.List;

public interface IDirectorService {
    
    public List<DirectorEntity> findAll();

    public void save(DirectorEntity director);

    public DirectorEntity findById(Long id);

    public void deleteById(Long id);

    public DirectorEntity actualizarDirectorEntity(DirectorEntity director);

    public void changeState(Long id);
}
