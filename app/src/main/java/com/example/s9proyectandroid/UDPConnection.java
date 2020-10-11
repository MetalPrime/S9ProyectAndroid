package com.example.s9proyectandroid;

import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPConnection extends Thread{

    private DatagramSocket socket;

    @Override
    public void run() {
        try {
            //1.Escuchar
            socket = new DatagramSocket(5000);
            //2.Esperar mensajes Datagramas

            while (true){
                byte[] buffer = new byte[30];
                DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
                Log.e("Status","Esperando datagrama");
                socket.receive(packet);

                String mensaje = new String(packet.getData()).trim();
                Log.e("Mensaje","Mensaje Actual"+mensaje);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessages(String msg){
        new Thread(
                ()->{
                    try {
                        InetAddress ip = InetAddress.getByName("192.168.0.4");
                        DatagramPacket packet = new DatagramPacket(msg.getBytes(),msg.getBytes().length,ip,6000);
                        socket.send(packet);
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        ).start();
    }
}
