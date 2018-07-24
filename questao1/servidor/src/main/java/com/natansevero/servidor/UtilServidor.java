/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author natan
 */
public class UtilServidor {
    
    public DatagramPacket receive(DatagramSocket socket) {
        byte[] dados = new byte[10];
        DatagramPacket packet = new DatagramPacket(dados, dados.length);
        
        try {
            socket.receive(packet);
        } catch (IOException ex) {
            Logger.getLogger(UtilServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return packet;
    }
    
    public int processor(DatagramPacket packet) {
        
        ByteBuffer wrap = ByteBuffer.wrap(packet.getData());
        int n1 = wrap.getInt();
        int n2 = wrap.getInt();
        char operation = wrap.getChar();
            
        System.out.println(" " + n1 + " " + n2 + " " + operation);
            
        int result = 0;
            
        switch(operation) {
            case '+':
                result = n1 + n2;
            break;
                
            case '-':
                result = n1 - n2;
            break;
        }
        
        return result;
    }
    
    public void send(DatagramSocket socket, DatagramPacket packet, int result) {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(result);
            
        DatagramPacket packetResposta;
        
        try {
            packetResposta = new DatagramPacket(allocate.array(), allocate.array().length,
                    InetAddress.getByName("localhost"), packet.getPort());
            
            socket.send(packetResposta);
        } catch (IOException ex) {
            Logger.getLogger(UtilServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
