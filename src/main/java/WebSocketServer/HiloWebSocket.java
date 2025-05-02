/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package WebSocketServer;

import GUI.MainFrame;
import GUI.SonidosPanel;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author micro
 */
public class HiloWebSocket extends Thread{

    int PUERTO;
    InetAddress address;
    boolean detener;
    
    public HiloWebSocket(int PUERTO, InetAddress address){
        
        this.PUERTO =  PUERTO;
        this.address = address;
        this.detener = true;
    }
    
    @Override
    public void run() {
        super.run();
    
        ServerSocket servidor = null;
        Socket sc = null;
        final int PUERTO = 5000;
        
        DataInputStream input;
        DataOutputStream output;
        System.out.println("antes");
        try{
            servidor = new ServerSocket(PUERTO, PUERTO, address);
            System.out.println(servidor);
            while(detener){
                System.out.println("while");
                sc = servidor.accept();
                
                input = new DataInputStream(sc.getInputStream());
                output = new DataOutputStream(sc.getOutputStream());

                String mensaje = input.readUTF();
                String respuesta;
                
                switch (mensaje) {
                    case "conectado":
                        MainFrame.conn(mensaje);
                        respuesta = String.join(",", SonidosPanel.todas_canciones);
                        break;
                    case "desconectado":
                        MainFrame.conn(mensaje);
                        respuesta = "desconectado"; 
                        break;
                    default:
                        Api.send(mensaje);
                        respuesta = "enviado"; 
                        break;
                }
                    
                output.writeUTF(respuesta);
                System.out.println(mensaje);
                sc.close();
            }
        }catch(IOException e){  
        }
    }
    
    public void detener(){
        this.detener = false;
    } 
}
