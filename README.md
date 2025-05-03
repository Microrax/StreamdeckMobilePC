# StreamdeckMobilePC

**StreamdeckMobilePC** is a desktop application built in **Java (NetBeans)** that allows users to manage and trigger audio effects remotely from a mobile device. It integrates with **OBS**, **Twitch Studio**, and similar streaming tools, offering real-time sound playback on stream through a combination of WebSocket communication and a Node.js server.

## 📱 Overview

The application ecosystem consists of:

- **Java Desktop App** (`StreamdeckMobilePC`)
- **Android App** (Java-based, not included in this repository - https://github.com/Microrax/StreamdeckMobileAPP)
- **Node.js Sound Playback Server**
- **QR Code Connection System**

> ⚠️ **Note:** The **source code for the Android mobile app is not included** in this repository. Only the desktop application and Node.js server are available.

## 🔗 How It Works

1. **Connect via QR Code**  
   When the user clicks the **"Generate QR Code"** button in the desktop app:
   - The **Node.js sound server is automatically started** in the background.
   - A **QR code** is generated using an external API, containing the **IP address** and **WebSocket port** required for the mobile app to connect.
   - The mobile app scans this QR code and establishes a **WebSocket** connection to the desktop.

2. **Load Sounds to Mobile**  
   Sounds stored locally on the PC can be **added or removed** using the desktop app.  
   These are sent asynchronously to the mobile app via a **background thread** over WebSocket.

3. **Trigger Sounds from Mobile**  
   When the user taps a sound on the mobile app:
   - The sound name is sent via **WebSocket** to the desktop app.
   - The desktop app forwards this to the **Node.js server** using a local **Java API**.
   - The Node.js server then **plays the sound** on the PC.

4. **Streaming Integration**  
   The local Node.js server exposes a **web link** which can be added to:
   - **OBS**
   - **Twitch Studio**
   - Other broadcasting software

   This ensures the sound is played through the streaming setup and is audible to viewers.

## 🛠️ Technologies Used

- **Java (NetBeans)** – Desktop and Android apps
- **WebSockets** – Bi-directional communication
- **Node.js** – Server-side sound playback
- **External QR Code API** – Connection automation
- **Java API** – Interface between desktop and Node server

## ⚙️ Features

- 📤 Add or remove local sound files
- 🔄 Load sound list to mobile app via WebSocket
- 🔊 Play sound remotely from mobile, streamed to OBS/Twitch
- 🔗 Easy mobile-PC connection via QR code
- ⚙️ Automatic launch of Node.js sound server
- 🌐 WebSocket and API-based modular architecture


**Developed with ❤️ using Java, WebSockets, and Node.js**
