package com.usta.universidad.models.services;

import com.usta.universidad.entities.DirectorEntity;
import com.usta.universidad.models.DAO.DirectorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DirectorServiceImplement implements IDirectorService {
   
    @Autowired
    private DirectorDAO directorDao;

    @Override
    @Transactional(readOnly = true)
    public List<DirectorEntity> findAll() {
        return (List<DirectorEntity>) directorDao.findAll();
    }

    @Override
    @Transactional
    public void save(DirectorEntity director) {
        directorDao.save(director);
    }

    @Override
    @Transactional
    public DirectorEntity findById(Long id) {
        return directorDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        directorDao.deleteById(id);
    }

    @Override
    @Transactional
    public DirectorEntity actualizarDirectorEntity(DirectorEntity director) {
        return directorDao.save(director);
    }

    @Override
    @Transactional
    public void changeState(Long id) {
        directorDao.changeState(id);
    }
}
