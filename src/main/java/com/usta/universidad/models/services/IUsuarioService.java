package com.usta.universidad.models.services;

import com.usta.universidad.entities.UsuarioEntity;

import java.util.List;

public interface IUsuarioService {
    
    public List<UsuarioEntity> findAll();

    public void save(UsuarioEntity usuario);

    public UsuarioEntity findById(Long id);

    public void deleteById(Long id);

    public UsuarioEntity actualizarUsuarioEntity(UsuarioEntity usuario);

    public void changeState(Long id);

    public UsuarioEntity findByEmail(String email);
}
