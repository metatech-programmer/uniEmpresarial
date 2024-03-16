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

import com.usta.universidad.entities.UniversidadEntity;
import com.usta.universidad.models.services.IUniversidadService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UniversidadController {

    @Autowired
    private IUniversidadService iUniversidadService;

    /* ----------------------------------------------------------------------------- */
    @GetMapping(value = "/universidad")
    public String listarU(Model model) {
        model.addAttribute("title", "Listado de Universidades");
        model.addAttribute("urlRegistro", "/crearUniversidad");

        model.addAttribute("universidades", iUniversidadService.findAll());
        return "universidades/listarUniversidad";
    }

    /* ----------------------------------------------------------------------------- */
    @GetMapping(value = "/crearUniversidad")
    public String listarFormUniversidad(Model model) {
        model.addAttribute("title", "Resgistrar Universidad");
        model.addAttribute("universidad", new UniversidadEntity());
        return "universidades/crearUniversidad";

    }

    /* ----------------------------------------------------------------------------- */
    @PostMapping(value = "/crearUniversidad")
    public String guardarUniversidad(@Valid UniversidadEntity universidad,
            @RequestParam(value = "foto") MultipartFile foto, BindingResult result) {

        String nombreImagen = guardarImagen(foto);

        if (result.hasErrors()) {
            return "error500";
        }

        universidad.setPhotoUniversidad(nombreImagen);
        universidad.setEstadoUniversidad(true);
        iUniversidadService.save(universidad);

        return "redirect:/universidad";
    }

    /* ----------------------------------------------------------------------------- */
    @RequestMapping(value = "/eliminarUniversidad/{id}")
    public String eliminarById(@PathVariable(value = "id") Long id) throws IOException {

        if (id > 0) {
            iUniversidadService.deleteById(id);
        } else {
            return "redirect:/error500";
        }
        return "redirect:/universidad";
    }

    /* ----------------------------------------------------------------------------- */
    @RequestMapping(value = "/eliminarUniversidadEstado/{id}")
    public String eliminarByState(@PathVariable(value = "id") Long id) {
        if (id > 0) {
            iUniversidadService.changeState(id);
        } else {
            return "redirect:/error500";
        }
        return "redirect:/universidad";
    }

    /* ----------------------------------------------------------------------------- */
    @GetMapping(value = "/editarUniversidad/{id}")
    public String editarUniversidad(Model model, @PathVariable(value = "id") Long idUniversidad) {
        model.addAttribute("title", "Editar Universidad");
        model.addAttribute("universidadEdit", iUniversidadService.findById(idUniversidad));
        model.addAttribute("imagen", iUniversidadService.viewDetail(idUniversidad).getPhotoUniversidad());
        model.addAttribute("nombreU", iUniversidadService.viewDetail(idUniversidad).getNombreUniversidad());
        return "universidades/editarUniversidad";

    }

    /* ----------------------------------------------------------------------------- */
    @PostMapping(value = "/editarUniversidad/{id}")
    public String editUniversidad(@ModelAttribute("universidadEdit") UniversidadEntity universidadEntity,
            @PathVariable(value = "id") Long idUniversidad, @RequestParam(value = "foto") MultipartFile foto,
            BindingResult result) throws IOException {

        if (result.hasErrors()) {
            return "error500";
        }

        UniversidadEntity universidadAuxiliar = iUniversidadService.findById(idUniversidad);

        universidadAuxiliar.setIdUniversidad(idUniversidad);
        universidadAuxiliar.setNombreUniversidad(universidadEntity.getNombreUniversidad());
        universidadAuxiliar.setNitUniversidad(universidadEntity.getNitUniversidad());
        universidadAuxiliar.setFechaFundacionUniversidad(universidadEntity.getFechaFundacionUniversidad());
        universidadAuxiliar.setEstadoUniversidad(true);

        /* Actualizar Foto */
        String imagen = universidadAuxiliar.getPhotoUniversidad();

        String nombreImagen = guardarImagen(foto);
        if (nombreImagen.isBlank() || nombreImagen.isEmpty() || nombreImagen == null) {

            universidadAuxiliar.setPhotoUniversidad(imagen);
        } else {

            universidadAuxiliar.setPhotoUniversidad(nombreImagen);
        }
        /* Actualizar Foto fin */

        universidadAuxiliar.setDireccionUniversidad(universidadEntity.getDireccionUniversidad());

        iUniversidadService.actualizarUniversidadEntity(universidadAuxiliar);

        return "redirect:/universidad";
    }

    /* ----------------------------------------------------------------------------- */
    @RequestMapping(value = "/detalleUniversidad/{id}")
    public String detalleUniversidad(Model model, @PathVariable(value = "id") Long idUniversidad) {
        model.addAttribute("title", "Detalle Universidad");
        model.addAttribute("detalleU", iUniversidadService.viewDetail(idUniversidad));
        return "universidades/detalleUniversidad";
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
