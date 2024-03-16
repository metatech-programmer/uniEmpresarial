package com.usta.universidad.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.usta.universidad.entities.DirectorEntity;
import com.usta.universidad.entities.UniversidadEntity;
import com.usta.universidad.models.services.IDirectorService;
import com.usta.universidad.models.services.IUniversidadService;

@Controller
public class DirectorController {

    @Autowired
    private IDirectorService iDirectorService;

    @Autowired
    private IUniversidadService iUniversidadService;

    @GetMapping(value = "/director")
    public String saludar(Model model) {
model.addAttribute("urlRegistro", "/crearDirector");
model.addAttribute("title", "Listado de Directores");
        model.addAttribute("directores", iDirectorService.findAll());

        return "directores/listarDirector";
    }

    /* ----------------------------------------------------------------------------- */
    @GetMapping(value = "/crearDirector")
    public String listarFormDirector(Model model) {
        model.addAttribute("title", "Resgistrar Director");
        model.addAttribute("director", new DirectorEntity());
        model.addAttribute("color", "text-white-50");

        model.addAttribute("universidades", iUniversidadService.selectOneUni());

        return "directores/crearDirector";

    }

    /* ----------------------------------------------------------------------------- */
    @PostMapping(value = "crearDirector")
    public String guardarDirector(@Valid DirectorEntity director, BindingResult result) {

        if (result.hasErrors()) {
            return "error500";
        }

        director.setEstadoDirector(true);
        iDirectorService.save(director);

        return "redirect:/director";
    }

    /* ----------------------------------------------------------------------------- */
    @RequestMapping(value = "/eliminarDirector/{id}")
    public String eliminarById(@PathVariable(value = "id") Long id) {
        if (id > 0) {
            iDirectorService.deleteById(id);
        } else {
            return "redirect:/error500";
        }
        return "redirect:/director";
    }

    /* ----------------------------------------------------------------------------- */
    @RequestMapping(value = "/eliminarDirectorEstado/{id}")
    public String eliminarByState(@PathVariable(value = "id") Long id) {
        if (id > 0) {
            iDirectorService.changeState(id);
        } else {
            return "redirect:/error500";
        }
        return "redirect:/director";
    }

    @GetMapping(value = "/editarDirector/{id}")
    public String editarDirector(Model model, @PathVariable(value = "id") Long idDirector) {
        model.addAttribute("title", "Editar Director");

        DirectorEntity director = iDirectorService.findById(idDirector);

        List<UniversidadEntity> universidad = iUniversidadService.selectOneUni();

        if (universidad.isEmpty()) {

            model.addAttribute("universidades",
                    iUniversidadService.findById(director.getIdUniversidad().getIdUniversidad()));
        } else {
            model.addAttribute("universidades", iUniversidadService.selectOneUni());

        }
        model.addAttribute("directorEdit", iDirectorService.findById(idDirector));

        return "directores/editarDirector";

    }

    /* ----------------------------------------------------------------------------- */
    @PostMapping(value = "/editarDirector/{id}")
    public String editDirector(@ModelAttribute("directorEdit") DirectorEntity directorEntity,
            @PathVariable(value = "id") Long idDirector, BindingResult result) {

        if (result.hasErrors()) {
            return "error500";
        }

        DirectorEntity directorAuxiliar = iDirectorService.findById(idDirector);

        directorAuxiliar.setIdDirector(idDirector);
        directorAuxiliar.setNombreDirector(directorEntity.getNombreDirector());
        directorAuxiliar.setApellidoDirector(directorEntity.getApellidoDirector());
        directorAuxiliar.setDocumentoDirector(directorEntity.getDocumentoDirector());
        directorAuxiliar.setEstadoDirector(true);
        directorAuxiliar.setIdUniversidad(directorEntity.getIdUniversidad());

        iDirectorService.actualizarDirectorEntity(directorAuxiliar);

        return "redirect:/director";
    }

    /* ----------------------------------------------------------------------------- */
    @GetMapping(value = "/detalleDirector/{id}")
    public String detalleDirector(Model model, @PathVariable(value = "id") Long idDirector) {
        model.addAttribute("title", "Detalle del director");
        model.addAttribute("detalleD", iDirectorService.findById(idDirector));
        return "directores/detalleDirector";
    }

}
