/* package com.usta.universidad.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class erroresController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        // Obtener el código de estado del error
        int statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Math.log(statusCode);

        // Redirigir a la página de error correspondiente
        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            return "/error404";
        }
        if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            return "/error500";
        }
        return "error";
    }

    public String getErrorPath() {
        return "/error";
    }

}
  */