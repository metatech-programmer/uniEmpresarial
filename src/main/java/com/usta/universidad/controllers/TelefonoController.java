package com.usta.universidad.controllers;

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

import com.usta.universidad.entities.TelefonoEntity;
import com.usta.universidad.models.services.ITelefonoService;
import com.usta.universidad.models.services.IUniversidadService;

@Controller
public class TelefonoController {

    @Autowired
    private ITelefonoService iTelefonoService;

    @Autowired
    private IUniversidadService iUniversidadService;

    /* ----------------------------------------------------------------------------- */
    @GetMapping(value = "/telefono")
    public String listarT(Model model) {
model.addAttribute("urlRegistro", "/crearTelefono");
model.addAttribute("title", "Listado de Telefonos");

        model.addAttribute("telefonos", iTelefonoService.findAll());
        return "telefonos/listarTelefono";

    }

    /* ----------------------------------------------------------------------------- */
    @GetMapping(value = "/crearTelefono")
    public String listarFormTelefono(Model model) {
        model.addAttribute("title", "Resgistrar Telefono");
        model.addAttribute("telefono", new TelefonoEntity());

        model.addAttribute("universidades", iUniversidadService.findAll());

        return "telefonos/crearTelefono";

    }

    /* ----------------------------------------------------------------------------- */
    @PostMapping(value = "/crearTelefono")
    public String guardarTelefono(@Valid TelefonoEntity telefono, BindingResult result) {

        if (result.hasErrors()) {
            return "error500";
        }

        telefono.setEstadoTelefono(true);
        iTelefonoService.save(telefono);

        return "redirect:/telefono";
    }

    /* ----------------------------------------------------------------------------- */
    @RequestMapping(value = "/eliminarTelefono/{id}")
    public String eliminarById(@PathVariable(value = "id") Long id) {
        if (id > 0) {
            iTelefonoService.deleteById(id);
        } else {
            return "redirect:/error500";
        }
        return "redirect:/telefono";
    }

    /* ----------------------------------------------------------------------------- */
    @RequestMapping(value = "/eliminarTelefonoEstado/{id}")
    public String eliminarByState(@PathVariable(value = "id") Long id) {
        if (id > 0) {
            iTelefonoService.changeState(id);
        } else {
            return "redirect:/error500";
        }
        return "redirect:/telefono";
    }

    /* ----------------------------------------------------------------------------- */
    @GetMapping(value = "/editarTelefono/{id}")
    public String editarTelefono(Model model, @PathVariable(value = "id") Long idTelefono) {
        model.addAttribute("title", "Editar Telefono");
        model.addAttribute("telefonoEdit", iTelefonoService.findById(idTelefono));

        model.addAttribute("universidades", iUniversidadService.findAll());
        return "telefonos/editarTelefono";

    }

    /* ----------------------------------------------------------------------------- */
    @PostMapping(value = "/editarTelefono/{id}")
    public String editTelefono(@ModelAttribute("telefonoEdit") TelefonoEntity telefonoEntity,
            @PathVariable(value = "id") Long idTelefono, BindingResult result) {

        if (result.hasErrors()) {
            return "error500";
        }

        TelefonoEntity telefonoAuxiliar = iTelefonoService.findById(idTelefono);
        telefonoAuxiliar.setIdTelefono(idTelefono);
        telefonoAuxiliar.setNumeroTelefono(telefonoEntity.getNumeroTelefono());
        telefonoAuxiliar.setEstadoTelefono(true);
        telefonoAuxiliar.setIdUniversidad(telefonoEntity.getIdUniversidad());

        iTelefonoService.actualizarTelefonoEntity(telefonoAuxiliar);

        return "redirect:/telefono";
    }

    /* ----------------------------------------------------------------------------- */
    @GetMapping(value = "/detalleTelefono/{id}")
    public String detalleTelefono(Model model, @PathVariable(value = "id") Long idTelefono) {
        model.addAttribute("title", "Detalle Telefono");
        model.addAttribute("detalleT", iTelefonoService.findById(idTelefono));
        return "telefonos/detalleTelefono";
    }
}
