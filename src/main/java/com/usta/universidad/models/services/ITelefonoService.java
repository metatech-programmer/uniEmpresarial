package com.usta.universidad.models.services;

import com.usta.universidad.entities.TelefonoEntity;

import java.util.List;

public interface ITelefonoService {

    public List<TelefonoEntity> findAll();

    public TelefonoEntity findById(Long id);

    public void save(TelefonoEntity telefono);

    public void deleteById(Long id);

    public TelefonoEntity actualizarTelefonoEntity(TelefonoEntity telefono);

    public void changeState(Long id);
}
