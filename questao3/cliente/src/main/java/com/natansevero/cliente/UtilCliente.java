/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.cliente;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author natan
 */
public class UtilCliente {

    public void send(int n1, int n2, char operation, DatagramSocket socket) {
        ByteBuffer allocate = ByteBuffer.allocate(10);
        allocate.putInt(n1);
        allocate.putInt(n2);
        allocate.putChar(operation);
        
        try { 
            DatagramPacket packet = new DatagramPacket(allocate.array(), allocate.array().length,
                    InetAddress.getByName("localhost"), 1234);
            
            socket.send(packet);
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(UtilCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UtilCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int receive(DatagramSocket socket) {
        byte[] resposta = new byte[4];
            DatagramPacket packetRespota = new DatagramPacket(resposta, resposta.length);
        
        int result = 0;
        try {
            // Recebendo
            socket.receive(packetRespota);
            
            ByteBuffer wrap = ByteBuffer.wrap(packetRespota.getData());
            result = wrap.getInt();
            
        } catch (IOException ex) {
            Logger.getLogger(UtilCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
}
