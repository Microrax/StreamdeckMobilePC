/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author micro
 */
public class SonidosPanel extends javax.swing.JPanel {

   public static ArrayList<String> todas_canciones;
   JLabel[] canciones_labels = new JLabel[10];
   JButton[] canciones_buttons = new JButton[10];
   JButton[] canciones_delete = new JButton[10];
   int pasarela = 0;
   boolean deleteMode = false;
   int items_delete = 0;
   String basePath = new File("").getAbsolutePath();
   /**
    * Creates new form SonidosPanel
   */
    /**
     * Creates new form SonidosPanel
     */
    public SonidosPanel() {
       initComponents();
       ListarCanciones();
       InitLabelsArray();
       InitButtonsDelete();
       OcultarDeleteButtons();
       InitButtonsArray();
       OcultarDatos();
       Rellenar();
    }

    void ListarCanciones(){
       todas_canciones = new ArrayList();
       Scanner sc = new Scanner(System.in);
       String ruta = basePath + "\\src\\main\\java\\WebSocketServer\\public\\Sounds";
       File carpeta = new File(ruta);
       File[] archivos;

       if(carpeta.exists()){
           if(carpeta.isDirectory()){
               archivos = carpeta.listFiles();
               for(int i=0; i<archivos.length; i++){
                  todas_canciones.add(archivos[i].getName());
               }
           }
       }
   }
    
    void recargar(){
       OcultarDatos();
       Rellenar();
       if(deleteMode){
            OcultarDeleteButtons();
            MostrarEliminar();
        }
   }
   
   void recargarTodo(){
       ListarCanciones();
       InitLabelsArray();
       InitButtonsDelete();
       OcultarDeleteButtons();
       InitButtonsArray();
       OcultarDatos();
       Rellenar();
       if(deleteMode){
            OcultarDeleteButtons();
            MostrarEliminar();
        }
   }
  
   void EliminarCancion(String evt){
       
       int evtnumber = Integer.parseInt(evt.substring(48, 49))+pasarela;
       
       Scanner sc = new Scanner(System.in);
       String ruta = basePath + "\\src\\main\\java\\WebSocketServer\\public\\Sounds";
       File carpeta = new File(ruta);
       File[] archivos;

       if(carpeta.exists()){
           if(carpeta.isDirectory()){
               archivos = carpeta.listFiles();
               archivos[evtnumber].delete();
               System.out.println(evtnumber);
               System.out.println(todas_canciones.get(evtnumber));
               todas_canciones.remove(evtnumber);
           }
       }
       recargar();
   }
   
   void InitLabelsArray(){
       canciones_buttons[0] = Song1_1;
       canciones_buttons[1] = Song1_2;
       canciones_buttons[2] = Song1_3;
       canciones_buttons[3] = Song1_4;
       canciones_buttons[4] = Song1_5;
        
       canciones_buttons[5] = Song2_1;
       canciones_buttons[6] = Song2_2;
       canciones_buttons[7] = Song2_3;
       canciones_buttons[8] = Song2_4;
       canciones_buttons[9] = Song2_5;

       

       ImageIcon icon = new ImageIcon(basePath+"\\src\\main\\java\\Res\\icon.png");
       Image image = icon.getImage(); // transform it 
       Image newimg = image.getScaledInstance(94, 94,  java.awt.Image.SCALE_SMOOTH);
       icon = new ImageIcon(newimg);  // transform it back
       
       for(JButton c:canciones_buttons){
            c.setIcon(icon);
       }
   }
   
   void OcultarDatos(){
       for(JButton b:canciones_buttons){
           b.setVisible(false);
       }
       for(JLabel lb:canciones_labels){
           lb.setVisible(false);
       }       
   }
   
   void OcultarDeleteButtons(){
       
       for(JButton b:canciones_delete){
           b.setVisible(false);
       }
   }
   
   void InitButtonsDelete(){
       
       canciones_delete[0] = Delete1_1;
       canciones_delete[1] = Delete1_2;
       canciones_delete[2] = Delete1_3;
       canciones_delete[3] = Delete1_4;
       canciones_delete[4] = Delete1_5;
       
       canciones_delete[5] = Delete2_1;
       canciones_delete[6] = Delete2_2;
       canciones_delete[7] = Delete2_3;
       canciones_delete[8] = Delete2_4;
       canciones_delete[9] = Delete2_5;
       
       ImageIcon icon = new ImageIcon(basePath + "\\src\\main\\java\\Res\\delete.png");
       Image image = icon.getImage(); // transform it 
       Image newimg = image.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH);
       icon = new ImageIcon(newimg);  // transform it back
       
       for(JButton c:canciones_delete){
            c.setIcon(icon);
       }
   }
   
   void InitButtonsArray(){
       canciones_labels[0] = Label1_1;
       canciones_labels[1] = Label1_2;
       canciones_labels[2] = Label1_3;
       canciones_labels[3] = Label1_4;
       canciones_labels[4] = Label1_5;
        
       canciones_labels[5] = Label2_1;
       canciones_labels[6] = Label2_2;
       canciones_labels[7] = Label2_3;
       canciones_labels[8] = Label2_4;
       canciones_labels[9] = Label2_5;
   }
   
   
    void Rellenar(){
        items_delete = 0;
        int label_rellenar = 0;
        int final_pasarela = pasarela+10;
        if(todas_canciones != null){
            if(todas_canciones.size() >=final_pasarela){
                for(int i=pasarela; i<final_pasarela; i++){
                    if(todas_canciones.size() >= i){
                        canciones_labels[label_rellenar].setText(todas_canciones.get(i)); 
                        canciones_labels[label_rellenar].setVisible(true);
                        canciones_buttons[label_rellenar].setVisible(true);
                        label_rellenar = label_rellenar+1;
                        items_delete = items_delete+1;
                    }
                }   
            }else{
                for(int x = pasarela; x<=todas_canciones.size()-1; x++){
                    canciones_labels[label_rellenar].setText(todas_canciones.get(x));
                    canciones_labels[label_rellenar].setVisible(true);
                    canciones_buttons[label_rellenar].setVisible(true);
                    label_rellenar = label_rellenar+1;
                    items_delete = items_delete+1;
                }
            }
        } 
   }
    
    void MostrarEliminar(){
        for(int i = 0;i< items_delete;i++){
                canciones_delete[i].setVisible(true);
            }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Delete1_1 = new javax.swing.JButton();
        Delete1_2 = new javax.swing.JButton();
        Delete1_3 = new javax.swing.JButton();
        Delete1_4 = new javax.swing.JButton();
        Delete1_5 = new javax.swing.JButton();
        Delete2_5 = new javax.swing.JButton();
        Delete2_4 = new javax.swing.JButton();
        Delete2_3 = new javax.swing.JButton();
        Delete2_1 = new javax.swing.JButton();
        Delete2_2 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Song1_1 = new javax.swing.JButton();
        Song1_2 = new javax.swing.JButton();
        Song1_4 = new javax.swing.JButton();
        Song1_3 = new javax.swing.JButton();
        Song1_5 = new javax.swing.JButton();
        Song2_1 = new javax.swing.JButton();
        Song2_2 = new javax.swing.JButton();
        Song2_3 = new javax.swing.JButton();
        Song2_4 = new javax.swing.JButton();
        Song2_5 = new javax.swing.JButton();
        Label1_1 = new javax.swing.JLabel();
        Label1_2 = new javax.swing.JLabel();
        Label1_3 = new javax.swing.JLabel();
        Label1_4 = new javax.swing.JLabel();
        Label1_5 = new javax.swing.JLabel();
        Label2_2 = new javax.swing.JLabel();
        Label2_1 = new javax.swing.JLabel();
        Label2_3 = new javax.swing.JLabel();
        Label2_4 = new javax.swing.JLabel();
        Label2_5 = new javax.swing.JLabel();
        BackButton = new javax.swing.JButton();
        NextButton = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();

        Delete1_1.setText("0");
        Delete1_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete1_1ActionPerformed(evt);
            }
        });

        Delete1_2.setText("1");
        Delete1_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete1_2ActionPerformed(evt);
            }
        });

        Delete1_3.setText("2");
        Delete1_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete1_3ActionPerformed(evt);
            }
        });

        Delete1_4.setText("3");
        Delete1_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete1_4ActionPerformed(evt);
            }
        });

        Delete1_5.setText("4");
        Delete1_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete1_5ActionPerformed(evt);
            }
        });

        Delete2_5.setText("9");
        Delete2_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete2_5ActionPerformed(evt);
            }
        });

        Delete2_4.setText("8");
        Delete2_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete2_4ActionPerformed(evt);
            }
        });

        Delete2_3.setText("7");
        Delete2_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete2_3ActionPerformed(evt);
            }
        });

        Delete2_1.setText("5");
        Delete2_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete2_1ActionPerformed(evt);
            }
        });

        Delete2_2.setText("6");
        Delete2_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete2_2ActionPerformed(evt);
            }
        });

        jButton2.setText("Añadir Sonido");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        Song1_1.setText("jButton4");
        Song1_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Song1_1ActionPerformed(evt);
            }
        });

        Song1_2.setText("jButton4");

        Song1_4.setText("jButton4");

        Song1_3.setText("jButton4");

        Song1_5.setText("jButton4");

        Song2_1.setText("jButton4");

        Song2_2.setText("jButton4");

        Song2_3.setText("jButton4");

        Song2_4.setText("jButton4");

        Song2_5.setText("jButton4");

        Label1_1.setText("jLabel1");

        Label1_2.setText("jLabel1");

        Label1_3.setText("jLabel1");

        Label1_4.setText("jLabel1");

        Label1_5.setText("jLabel1");

        Label2_2.setText("jLabel1");

        Label2_1.setText("jLabel1");

        Label2_3.setText("jLabel1");

        Label2_4.setText("jLabel1");

        Label2_5.setText("jLabel1");

        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });

        NextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextButtonActionPerformed(evt);
            }
        });

        jToggleButton1.setText("Eliminar Sonido");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 536, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(93, 93, 93)
                            .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(87, 87, 87)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(BackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(11, 11, 11)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(143, 143, 143)
                                            .addComponent(Delete1_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(90, 90, 90)
                                            .addComponent(Song1_2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(53, 53, 53)
                                            .addComponent(Delete1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(Song1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(50, 50, 50)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(180, 180, 180)
                                            .addComponent(Delete1_5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(90, 90, 90)
                                            .addComponent(Delete1_4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(37, 37, 37)
                                            .addComponent(Song1_4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(127, 127, 127)
                                            .addComponent(Song1_5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(Delete1_3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(Label1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(6, 6, 6)
                                    .addComponent(Label1_2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(6, 6, 6)
                                    .addComponent(Label1_3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(6, 6, 6)
                                    .addComponent(Label1_4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(6, 6, 6)
                                    .addComponent(Label1_5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(53, 53, 53)
                                            .addComponent(Delete2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(90, 90, 90)
                                            .addComponent(Song2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(143, 143, 143)
                                            .addComponent(Delete2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(Song2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(50, 50, 50)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(37, 37, 37)
                                            .addComponent(Song2_4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(180, 180, 180)
                                            .addComponent(Delete2_5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(127, 127, 127)
                                            .addComponent(Song2_5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(Delete2_3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(90, 90, 90)
                                            .addComponent(Delete2_4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(180, 180, 180)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Song1_3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Song2_3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(NextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(47, 47, 47)
                            .addComponent(Label2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6)
                            .addComponent(Label2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6)
                            .addComponent(Label2_3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6)
                            .addComponent(Label2_4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6)
                            .addComponent(Label2_5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jToggleButton1)
                        .addComponent(jButton2))
                    .addGap(7, 7, 7)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addComponent(BackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Delete1_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Delete1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Delete1_5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Delete1_4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Delete1_3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(7, 7, 7)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Song1_2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Song1_1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Song1_4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Song1_5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Song1_3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(4, 4, 4)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Label1_1)
                                .addComponent(Label1_2)
                                .addComponent(Label1_3)
                                .addComponent(Label1_4)
                                .addComponent(Label1_5))
                            .addGap(12, 12, 12)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Song2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Song2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Song2_4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Song2_5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Song2_3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(37, 37, 37)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Delete2_1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Delete2_2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Delete2_5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Delete2_3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Delete2_4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(NextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(3, 3, 3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(Label2_1)
                        .addComponent(Label2_2)
                        .addComponent(Label2_3)
                        .addComponent(Label2_4)
                        .addComponent(Label2_5))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void Delete1_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete1_1ActionPerformed
        // TODO add your handling code here:
        EliminarCancion(evt.toString());
    }//GEN-LAST:event_Delete1_1ActionPerformed

    private void Delete1_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete1_2ActionPerformed
        // TODO add your handling code here:
        EliminarCancion(evt.toString());
    }//GEN-LAST:event_Delete1_2ActionPerformed

    private void Delete1_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete1_3ActionPerformed
        // TODO add your handling code here:
        EliminarCancion(evt.toString());
    }//GEN-LAST:event_Delete1_3ActionPerformed

    private void Delete1_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete1_4ActionPerformed
        // TODO add your handling code here:
        EliminarCancion(evt.toString());
    }//GEN-LAST:event_Delete1_4ActionPerformed

    private void Delete1_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete1_5ActionPerformed
        // TODO add your handling code here:
        EliminarCancion(evt.toString());
    }//GEN-LAST:event_Delete1_5ActionPerformed

    private void Delete2_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete2_5ActionPerformed
        // TODO add your handling code here:
        EliminarCancion(evt.toString());
    }//GEN-LAST:event_Delete2_5ActionPerformed

    private void Delete2_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete2_4ActionPerformed
        // TODO add your handling code here:
        EliminarCancion(evt.toString());
    }//GEN-LAST:event_Delete2_4ActionPerformed

    private void Delete2_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete2_3ActionPerformed
        // TODO add your handling code here:
        EliminarCancion(evt.toString());
    }//GEN-LAST:event_Delete2_3ActionPerformed

    private void Delete2_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete2_1ActionPerformed
        // TODO add your handling code here:
        EliminarCancion(evt.toString());
    }//GEN-LAST:event_Delete2_1ActionPerformed

    private void Delete2_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete2_2ActionPerformed
        // TODO add your handling code here:
        EliminarCancion(evt.toString());
    }//GEN-LAST:event_Delete2_2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Audio Files", "mp3"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        int seleccion = fileChooser.showOpenDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION){

            File cancion = fileChooser.getSelectedFile();
            try {

                FileChannel sourceChannel = new FileInputStream(cancion.getAbsolutePath()).getChannel();
                FileChannel destChannel = new FileOutputStream(basePath + "\\src\\main\\java\\WebSocketServer\\public\\Sounds\\"+cancion.getName()).getChannel();
                    destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
                    sourceChannel.close();
                    destChannel.close();
                    recargarTodo();
                }catch (FileNotFoundException ex) {

                    Logger.getLogger(SonidosPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {

                    Logger.getLogger(SonidosPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void Song1_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Song1_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Song1_1ActionPerformed

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
        // TODO add your handling code here:
        if(pasarela-10>=0){
            pasarela = pasarela-10;
            recargar();
        }
    }//GEN-LAST:event_BackButtonActionPerformed

    private void NextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextButtonActionPerformed
        // TODO add your handling code here:
        if(pasarela+10<todas_canciones.size()){
            pasarela = pasarela+10;
            recargar();
        }

    }//GEN-LAST:event_NextButtonActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // TODO add your handling code here:

        if(!deleteMode){
            for(JButton jb:canciones_buttons){
                jb.setEnabled(false);
                deleteMode = true;
            }
            MostrarEliminar();
        }else{
            for(JButton jb:canciones_buttons){
                jb.setEnabled(true);
            }
            deleteMode = false;
            OcultarDeleteButtons();
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JButton Delete1_1;
    private javax.swing.JButton Delete1_2;
    private javax.swing.JButton Delete1_3;
    private javax.swing.JButton Delete1_4;
    private javax.swing.JButton Delete1_5;
    private javax.swing.JButton Delete2_1;
    private javax.swing.JButton Delete2_2;
    private javax.swing.JButton Delete2_3;
    private javax.swing.JButton Delete2_4;
    private javax.swing.JButton Delete2_5;
    private javax.swing.JLabel Label1_1;
    private javax.swing.JLabel Label1_2;
    private javax.swing.JLabel Label1_3;
    private javax.swing.JLabel Label1_4;
    private javax.swing.JLabel Label1_5;
    private javax.swing.JLabel Label2_1;
    private javax.swing.JLabel Label2_2;
    private javax.swing.JLabel Label2_3;
    private javax.swing.JLabel Label2_4;
    private javax.swing.JLabel Label2_5;
    private javax.swing.JButton NextButton;
    private javax.swing.JButton Song1_1;
    private javax.swing.JButton Song1_2;
    private javax.swing.JButton Song1_3;
    private javax.swing.JButton Song1_4;
    private javax.swing.JButton Song1_5;
    private javax.swing.JButton Song2_1;
    private javax.swing.JButton Song2_2;
    private javax.swing.JButton Song2_3;
    private javax.swing.JButton Song2_4;
    private javax.swing.JButton Song2_5;
    private javax.swing.JButton jButton2;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables
}
