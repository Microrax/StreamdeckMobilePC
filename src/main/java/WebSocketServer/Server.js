/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


// server.js
const express = require('express');
const bodyParser = require('body-parser');
const WebSocket = require('ws');
const path = require('path');

const app = express();
const PORT = 3000;

// Middleware para parsear el cuerpo de las solicitudes
app.use(bodyParser.text()); // Cambia a text para recibir cadenas de texto

app.use(express.static(path.join(__dirname, 'public')));

// Endpoint para recibir datos a través de POST
app.post('/api/data', (req, res) => {
    const mensaje = req.body; // La cadena de texto se encuentra aquí
    console.log(`Mensaje recibido: ${mensaje}`);
    
    // Enviar el mensaje a todos los clientes conectados
    wss.clients.forEach((client) => {
        if (client.readyState === WebSocket.OPEN) {
            client.send(mensaje); // Envía el mensaje a todos los clientes
        }
    });

    res.status(200).send('Mensaje recibido con éxito');
});

// Crear el servidor HTTP y WebSocket
const server = app.listen(PORT, () => {
    console.log(`Servidor corriendo en http://localhost:${PORT}`);
});

// Crear un servidor WebSocket
const wss = new WebSocket.Server({ server });

// Manejar conexiones WebSocket
wss.on('connection', (ws) => {
    console.log('Cliente WebSocket conectado');

    ws.on('close', () => {
        console.log('Cliente WebSocket desconectado');
    });
});

