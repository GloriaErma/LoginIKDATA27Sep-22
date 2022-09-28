package com.ikno.login.Services;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpHeaders;
import java.util.Base64;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.apache.commons.io.FileUtils;
import com.ikno.login.Interfaces.IUsuario;

//import net.iharder.Base64; ///ncode

@Service
public class UsuarioService implements IUsuario {

    @Override

    public String getImagen() {
        // TODO Auto-generated method stub
        return "Hello ola world";
    }
   
    public String encodeV(String value) {
       return net.iharder.Base64.encodeBytes(value!=null?value.getBytes():null);
    }

    public String decodeV(String base64) {
        try {
            byte[] value = net.iharder.Base64.decode(base64);
            return new String(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 
    public String toBase64(String filePath) {
        // lee la matriz de bytes de imagen
        try {
            byte[] fileContent = FileUtils.readFileToByteArray(new File(filePath.toString()));
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            return encodedString;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String toImagen(String encodeS) {
        try {
          //  byte[] decodedBytes = Base64.getMimeDecoder().decode(encodeS);
          String decode = new String(Base64.getMimeDecoder().decode(encodeS),"utf-8");
            // for (int i = 0; i < decodedBytes.length; ++i) {
			// 	 if (decodedBytes [i] <0) {// ajusta datos anormales
			// 		decodedBytes[i] += 256;
			// 	}
			// }
            // String outputFile=" ";
            // // String outputFile = " ";
            // FileUtils.writeByteArrayToFile(new File(outputFile), decodedBytes);
            return new String(decode);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
        
    }
    
    public ResponseEntity<byte[]> decodeFileO(String base64) {
		try {
			byte[] image = net.iharder.Base64.decode(base64);
			//Files.write(Path.of(local.concat(nameWithExtention)), image); optional save local file
			// HttpHeaders httpHeaders = new HttpHeaders();
			// httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION,
			// 		ContentDisposition.attachment().filename(nameWithExtention).build().toString());
			return ResponseEntity.ok().body(image);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
