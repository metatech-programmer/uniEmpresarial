package com.usta.universidad.models.services;

import com.usta.universidad.entities.UniversidadEntity;
import com.usta.universidad.models.DAO.UniversidadDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UniversidadServiceImplement implements IUniversidadService {
    @Autowired
    private UniversidadDAO universidadDao;

    @Override
    @Transactional(readOnly = true)
    public List<UniversidadEntity> findAll() {

        return (List<UniversidadEntity>) universidadDao.findAll();
    	}

    @Override
    @Transactional
    public void save(UniversidadEntity universidad) {
    
        universidadDao.save(universidad);
    }

    @Override
    @Transactional
    public UniversidadEntity findById(Long id) {
    
        return universidadDao.findById(id).orElse(null);
    
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
    
        universidadDao.deleteById(id);
    
    }

    @Override
    @Transactional
    public UniversidadEntity actualizarUniversidadEntity(UniversidadEntity universidad) {
    
        return universidadDao.save(universidad);
    
    }

    @Override
    @Transactional
    public void changeState(Long id) {

        universidadDao.changeState(id);

    }

    @Override
    @Transactional
    public List<UniversidadEntity> selectOneUni() {
    
        return universidadDao.selectOneUni();
    
    }

    @Override
    @Transactional(readOnly = true)
    public UniversidadEntity viewDetail(Long id) {
        
        return universidadDao.viewDetail(id);
  
    }

}
