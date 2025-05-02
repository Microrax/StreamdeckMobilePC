package WebSocketServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StartNodeServer {

    private Process nodeProcess;

    public void start() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("node", "src/main/java/WebSocketServer/server.js");
            processBuilder.redirectErrorStream(true);
            nodeProcess = processBuilder.start();

            // Agrega el shutdown hook
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                if (nodeProcess != null && nodeProcess.isAlive()) {
                    System.out.println("Cerrando servidor Node.js...");
                    nodeProcess.destroy();
                }
            }));

            // Opcionalmente puedes leer la salida en un hilo aparte:
            new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(nodeProcess.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println("[Node.js] " + line);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
