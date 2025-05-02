/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WebSocketServer;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author micro
 */
public class Api {

    public static void send(String sound) {
        try {
            // URL de la API
            URL url = new URL("http://localhost:3000/api/data");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "text/plain"); // Cambia a text/plain
            conn.setDoOutput(true);

            // Enviar datos
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = sound.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Leer la respuesta
            int responseCode = conn.getResponseCode();
            System.out.println("Respuesta del servidor: " + responseCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}