 package com.usta.universidad.controllers;

import java.util.Arrays;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.usta.universidad.entities.RolEntity;
import com.usta.universidad.entities.UsuarioEntity;
import com.usta.universidad.models.services.IDirectorService;
import com.usta.universidad.models.services.IUsuarioService;

@Controller
public class UsuarioController {

    @Autowired
    private IDirectorService iDirectorService;

    @Autowired
    private IUsuarioService iUsuarioService;


    @GetMapping(value = "/register")
    public String crearUsuario(Map<String, Object> model, Model model2) {
        UsuarioEntity usuario = new UsuarioEntity();
        model.put("usuario", usuario);
        model.put("directores", iDirectorService.findAll());
        model2.addAttribute("title", "Registrar Usuario");
        return "register";
    }

    @RequestMapping(value = "/register", method = { RequestMethod.POST })
    public String register(@Valid UsuarioEntity usuario, BindingResult result, @RequestParam(value="rol") String rol, SessionStatus status) {

        if (result.hasErrors()) {

            return "register";
        }

        String pass = new BCryptPasswordEncoder().encode(usuario.getClaveUsuario());
        usuario.setEstadoUsuario(true);
        usuario.setClaveUsuario(pass);
        usuario.setRole(Arrays.asList(new RolEntity(rol)));
        iUsuarioService.save(usuario);
        status.setComplete();

        return "redirect:/login";

    }
}
 
