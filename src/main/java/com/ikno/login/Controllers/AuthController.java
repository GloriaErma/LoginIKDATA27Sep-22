package com.ikno.login.Controllers;

//import com.jsm.java_sprint_maven.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ikno.login.Models.User;
import com.ikno.login.utils.JWTUtil;

@RestController
public class AuthController {

     // Básico
    // @RequestMapping(value = "api/login", method = RequestMethod.POST)
    // public String login(@RequestBody Usuario usuario) {
    //     if (usuarioDao.obtenerUsuarioPorCredenciales(usuario)) {
    //         return "OK";
    //     }
    //     return "FAIL";
    // }
    // cargar automaticamente, inyección Dependencias
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody User usuario) {

        if (usuario!= null && usuario.username.equalsIgnoreCase("GECS") && usuario.password.equalsIgnoreCase("1234")) {
            // crea token con key y value
            String tokenJwt = jwtUtil.create(String.valueOf(usuario.username), usuario.password);
            return tokenJwt;
        }else {
            System.out.println(" ELSE user : " + usuario.username + " password: " + usuario.password);
            return "FAIL";
            // return (ResponseEntity<?>) ResponseEntity.badRequest();
        }
    }




}
