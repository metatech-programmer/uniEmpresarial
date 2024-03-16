package com.usta.universidad.models.services;

import java.util.List;

import com.usta.universidad.entities.TelefonoEntity;
import com.usta.universidad.models.DAO.TelefonoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TelefonoServiceImplement implements  ITelefonoService{

    @Autowired
    private TelefonoDAO telefonoDao;

    @Override
    @Transactional(readOnly = true)
    public List<TelefonoEntity> findAll() {
        return (List<TelefonoEntity>) telefonoDao.findAll();
    }

    @Override
    @Transactional
    public void save(TelefonoEntity director) {

        telefonoDao.save(director);

    }

    @Override
    @Transactional
    public TelefonoEntity findById(Long id) {
        return telefonoDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
telefonoDao.deleteById(id);
    }

    @Override
    @Transactional
    public TelefonoEntity actualizarTelefonoEntity(TelefonoEntity director) {
     return telefonoDao.save(director);
    }

    @Override
    @Transactional
    public void changeState(Long id) {
telefonoDao.changeState(id);
    }
}
