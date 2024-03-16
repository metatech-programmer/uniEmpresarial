package com.usta.universidad.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.usta.universidad.entities.SeccionalEntity;
import com.usta.universidad.models.services.ISeccionalService;
import com.usta.universidad.models.services.IUniversidadService;

@Controller
public class SeccionalController {

    @Autowired
    private ISeccionalService iSeccionalService;

    @Autowired
    private IUniversidadService iUniversidadService;

    /* ----------------------------------------------------------------------------- */

    @GetMapping(value = "/seccional")
    public String saludar(Model model) {
model.addAttribute("urlRegistro", "/crearSeccional");
model.addAttribute("title", "Listado de Seccionales");


        model.addAttribute("seccionales", iSeccionalService.findAll());
        return "seccionales/listarSeccional";
    }

    /* ----------------------------------------------------------------------------- */

    @GetMapping(value = "/crearSeccional")
    public String listarFormSeccional(Model model) {
        model.addAttribute("title", "Resgistrar Seccional");
        model.addAttribute("seccional", new SeccionalEntity());

        model.addAttribute("universidades", iUniversidadService.findAll());

        return "seccionales/crearSeccional";

    }

    /* ----------------------------------------------------------------------------- */

    @PostMapping(value = "/crearSeccional")
    public String guardarSeccional(@Valid SeccionalEntity seccional,
            @RequestParam(value = "foto") MultipartFile foto, BindingResult result) {

        String nombreImagen = guardarImagen(foto);

        if (result.hasErrors()) {
            return "error500";
        }
        seccional.setPhotoSeccional(nombreImagen);
        seccional.setEstadoSeccional(true);
        iSeccionalService.save(seccional);

        return "redirect:/seccional";
    }

    /* ----------------------------------------------------------------------------- */

    @RequestMapping(value = "/eliminarSeccional/{id}")
    public String eliminarById(@PathVariable(value = "id") Long id) throws IOException {

        if (id > 0) {
            iSeccionalService.deleteById(id);

        } else {
            return "redirect:/error500";
        }
        return "redirect:/seccional";
    }

    /* ----------------------------------------------------------------------------- */

    @RequestMapping(value = "/eliminarSeccionalEstado/{id}")
    public String eliminarByState(@PathVariable(value = "id") Long id) {
        if (id > 0) {
            iSeccionalService.changeState(id);
        } else {
            return "redirect:/error500";
        }
        return "redirect:/seccional";
    }

    /* ----------------------------------------------------------------------------- */

    @GetMapping(value = "/editarSeccional/{id}")
    public String editarSeccional(Model model, @PathVariable(value = "id") Long idSeccional) {
        model.addAttribute("title", "Editar Seccional");
        model.addAttribute("seccionalEdit", iSeccionalService.findById(idSeccional));
        model.addAttribute("imagen", iSeccionalService.findById(idSeccional).getPhotoSeccional());
        model.addAttribute("nombreS", iSeccionalService.findById(idSeccional).getNombreSeccional());

        model.addAttribute("universidades", iUniversidadService.findAll());
        return "seccionales/editarSeccional";

    }


    @PostMapping(value = "/editarSeccional/{id}")
    public String editSeccional(@ModelAttribute("seccionalEdit") SeccionalEntity seccionalEntity,
            @PathVariable(value = "id") Long idSeccional, @RequestParam(value = "foto") MultipartFile foto,
            BindingResult result) throws IOException {

        if (result.hasErrors()) {
            return "error500";
        }

        SeccionalEntity seccionalAuxiliar = iSeccionalService.findById(idSeccional);
        seccionalAuxiliar.setIdSeccional(idSeccional);
        seccionalAuxiliar.setNombreSeccional(seccionalEntity.getNombreSeccional());
        seccionalAuxiliar.setCantidadFacultadSeccional(seccionalEntity.getCantidadFacultadSeccional());
        seccionalAuxiliar.setEstadoSeccional(true);
        seccionalAuxiliar.setIdUniversidad(seccionalEntity.getIdUniversidad());

        /* Actualizar Foto */
        String imagen = seccionalAuxiliar.getPhotoSeccional();
        String nombreImagen = guardarImagen(foto);
        if (nombreImagen.isBlank() || nombreImagen.isEmpty() || nombreImagen == null) {

            seccionalAuxiliar.setPhotoSeccional(imagen);
        } else {

            seccionalAuxiliar.setPhotoSeccional(nombreImagen);
        }
        /* Actualizar Foto fin */

        iSeccionalService.actualizarSeccionalEntity(seccionalAuxiliar);

        return "redirect:/seccional";
    }

    /* ----------------------------------------------------------------------------- */

    @GetMapping(value = "/detalleSeccional/{id}")
    public String detalleSeccional(Model model, @PathVariable(value = "id") Long idSeccional) {
        model.addAttribute("title", "Detalle Seccional");
        model.addAttribute("detalleS", iSeccionalService.findById(idSeccional));
        return "seccionales/detalleSeccional";
    }

    /* ----------------------------------------------------------------------------- */



    
    private String guardarImagen(MultipartFile imagen) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("https://api.imgbb.com/1/upload");
    
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addTextBody("key", "9cde2b63dc86c906bd9f66c4d82814b5", ContentType.TEXT_PLAIN);
            builder.addBinaryBody("image", imagen.getInputStream(), ContentType.DEFAULT_BINARY, imagen.getOriginalFilename());
    
            HttpEntity multipart = builder.build();
            httpPost.setEntity(multipart);
    
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
    
            if (response.getStatusLine().getStatusCode() == 200) {
                String responseString = EntityUtils.toString(responseEntity);
    
                // Analizar la respuesta JSON
                JSONObject jsonResponse = new JSONObject(responseString);
                boolean success = jsonResponse.getBoolean("success");
    
                if (success) {
                    JSONObject data = jsonResponse.getJSONObject("data");
                    String imageUrl = data.getString("url");
    
                    return imageUrl;
                } else {
                    // Si la carga falló, puedes manejar el error aquí
                    String errorMessage = jsonResponse.optString("error", "Error desconocido");
                    System.err.println("Error al cargar la imagen: " + errorMessage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return null;
    }
    
}
