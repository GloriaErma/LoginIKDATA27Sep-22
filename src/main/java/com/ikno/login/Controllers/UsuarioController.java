package com.ikno.login.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ikno.login.Interfaces.IUsuario;
import com.ikno.login.Services.UsuarioService;
import com.ikno.login.utils.JWTUtil;

@RestController
public class UsuarioController {

    @RequestMapping(value = "GECS")
    public String prueba() {
        return "Kiwi";
    }

    @Autowired
    private IUsuario usuarioD;
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/imagen", method = RequestMethod.GET)
    // en variable String Token envia el Token de la cabecera
    public String getImagen(@RequestHeader(value = "Authorization") String token) {
        if (!validarToken(token)) {
            return "IMAGE FAIL";
        }

        return usuarioD.getImagen();
    }

    @Autowired
    private UsuarioService converterService;

    @GetMapping(value = "/encodeV")
    public String encodeValue(@RequestHeader(value = "Authorization") String token, @RequestParam String value) {
        if (!validarToken(token)) {
            return "/encodeV FAIL";
        }
        return converterService.encodeV(value);
    }

    @GetMapping(value = "/decodeV")
    public String decodeValue(@RequestHeader(value = "Authorization") String token, @RequestParam String base64Value) {
        if (!validarToken(token)) {
            return "/decodeV FAIL";
        }
        return converterService.decodeV(base64Value);
    }

    // https://www.xtrafondos.com/thumbs/1_10192.jpg
    // C:\DEV\LoginIKDATA27Sep-22\login\src\main\resources\static\1_10192.jpg
    // filePath=src/main/resources/static/1_10192.jpg
    @GetMapping(value = "/encode/file")
    public String encodeImage(@RequestHeader(value = "Authorization") String token, @RequestParam String filePath) {
        if (!validarToken(token)) {
            return "/encodeV FAIL";
        }
        return converterService.toBase64(filePath);
    }

    // @GetMapping(value = "/decode/file")
    // public String decodeImage(@RequestParam String encodeS) {
    // return converterService.toImagen(encodeS);
    // }
    @RequestMapping(value = "decode/file", method = RequestMethod.POST)
    public String decodeImage(@RequestHeader(value = "Authorization") String token, @RequestBody String encodeS) {
        if (!validarToken(token)) {
            return "/decode/file FAIL";
        }
        return converterService.toImagen(encodeS);
    }

    @GetMapping(value = "/decodeO/file")
	public ResponseEntity<byte[]> decodeImageO(@RequestParam String base64Value) {
		return converterService.decodeFileO(base64Value);
	}

    private boolean validarToken(String token) {
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }
}
