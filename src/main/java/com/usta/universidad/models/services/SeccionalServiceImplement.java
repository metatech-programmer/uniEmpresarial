package com.usta.universidad.models.services;

import com.usta.universidad.entities.SeccionalEntity;
import com.usta.universidad.models.DAO.SeccionalDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SeccionalServiceImplement implements  ISeccionalService {
    @Autowired
    private SeccionalDAO seccionalDao;

    @Override
    @Transactional(readOnly = true)
    public List<SeccionalEntity> findAll() {
        return (List<SeccionalEntity>) seccionalDao.findAll();
    }

    @Override
    @Transactional
    public void save(SeccionalEntity director) {
seccionalDao.save(director);
    }

    @Override
    @Transactional
    public SeccionalEntity findById(Long id) {
        return seccionalDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
     seccionalDao.deleteById(id);
    }

    @Override
    @Transactional
    public SeccionalEntity actualizarSeccionalEntity(SeccionalEntity director) {
        return seccionalDao.save(director);
    }

    @Override
    @Transactional
    public void changeState(Long id) {

    }
}
