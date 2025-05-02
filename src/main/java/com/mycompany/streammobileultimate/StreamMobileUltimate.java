/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.streammobileultimate;

import GUI.MainFrame;
import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 *
 * @author micro
 */
public class StreamMobileUltimate {

    public static void main(String[] args) {
        addWindowListener(new java.awt.event.WindowAdapter() {
    @Override
    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
        System.exit(0); // Esto asegura que se ejecuten los shutdown hooks
    }
});
        InetAddress wifiIp = getWifiIp();
        if (wifiIp != null) {
            System.out.println("La IP de la conexión WiFi es: " + wifiIp.getHostAddress());
            MainFrame MF = new MainFrame(wifiIp);
            MF.setVisible(true);
        } else {
            System.out.println("No se encontró una dirección IP de WiFi.");
        }
    }
    public static InetAddress GetIp(){
        InetAddress inet = null;
        try{
            inet = InetAddress.getLocalHost();
        }catch(UnknownHostException e){
            
            System.out.println("No se ha podido obtener la ip del equipo.");
        }
        return inet;
    }
    
    public static void listNetworkInterfaces() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();
                System.out.println("Nombre: " + networkInterface.getName());
                System.out.println("Display Name: " + networkInterface.getDisplayName());
                System.out.println("Está activa: " + networkInterface.isUp());
                System.out.println("Es loopback: " + networkInterface.isLoopback());
                
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    System.out.println("Dirección IP: " + inetAddress.getHostAddress());
                }
                System.out.println("-----------------------------");
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    
    public static InetAddress getWifiIp() {
    try {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();
            
            // Cambia "wlan" por el nombre correcto de tu interfaz WiFi
            if (networkInterface.isUp() && !networkInterface.isLoopback() && networkInterface.getDisplayName().toLowerCase().contains("wi-fi")) {
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if (inetAddress.isSiteLocalAddress() && !inetAddress.isLoopbackAddress()) {
                        return inetAddress; // Retorna la dirección IP
                    }
                }
            }
        }
    } catch (SocketException e) {
        e.printStackTrace();
    }
    return null; // Si no se encuentra una IP válida
}
}
