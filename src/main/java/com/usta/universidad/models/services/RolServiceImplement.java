package com.usta.universidad.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.usta.universidad.entities.RolEntity;
import com.usta.universidad.models.DAO.RolDAO;

@Service
public class RolServiceImplement implements IRolService {

    @Autowired
    private RolDAO rolDao;

    @Override
    @Transactional(readOnly = true)
    public List<RolEntity> findAll() {
        return (List<RolEntity>) rolDao.findAll();
    }

    @Override
    @Transactional
    public void save(RolEntity rol) {
        rolDao.save(rol);
    }

    @Override
    @Transactional
    public RolEntity findById(Long id) {
        return rolDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

        rolDao.deleteById(id);

    }

    @Override
    @Transactional
    public RolEntity actualizarRolEntity(RolEntity rol) {

        return rolDao.save(rol);

    }

}
