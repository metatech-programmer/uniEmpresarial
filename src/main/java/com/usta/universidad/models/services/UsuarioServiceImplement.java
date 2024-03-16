package com.usta.universidad.models.services;

import com.usta.universidad.entities.UsuarioEntity;
import com.usta.universidad.models.DAO.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImplement implements  IUsuarioService {
    @Autowired
    private UsuarioDAO usuarioDao;

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioEntity> findAll() {
  return (List<UsuarioEntity>) usuarioDao.findAll(); }

    @Override
    @Transactional
    public void save(UsuarioEntity usuario) {
   usuarioDao.save(usuario);
   
    }

    @Override
    @Transactional
    public UsuarioEntity findById(Long id) {
   return usuarioDao.findById(id).orElse(null);

   
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
    usuarioDao.deleteById(id);  
    }

    @Override
    @Transactional
    public UsuarioEntity actualizarUsuarioEntity(UsuarioEntity usuario) {
    return usuarioDao.save(usuario);
    
    }

    @Override
    @Transactional
    public void changeState(Long id) {
        usuarioDao.changeState(id);


    }

    @Override
    @Transactional
    public UsuarioEntity findByEmail(String email) {
        return usuarioDao.findByEmail(email);
    }

}
