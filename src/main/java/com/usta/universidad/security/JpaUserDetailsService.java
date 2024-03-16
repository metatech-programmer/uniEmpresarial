package com.usta.universidad.security;

import java.util.stream.Collectors;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.usta.universidad.entities.RolEntity;
import com.usta.universidad.entities.UsuarioEntity;
import com.usta.universidad.models.DAO.UsuarioDAO;

@Service("jpaUserDetailsService")
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioDAO usuarioDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuario = usuarioDao.findByEmail(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("El usuario no existe");

        } 
          return new User(usuario.getCorreoUsuario(), usuario.getClaveUsuario(), mapearAutoridadesRoles(usuario.getRole()));
    }

    private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<RolEntity> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRol())).collect(Collectors.toList());

    }

}
