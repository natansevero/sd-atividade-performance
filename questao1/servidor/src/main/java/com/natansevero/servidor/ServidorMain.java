/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.servidor;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author natan
 */
public class ServidorMain {
    public static void main(String[] args) {
        
        UtilServidor utilServidor = new UtilServidor();
        
        try(DatagramSocket socket = new DatagramSocket(1234)) {

            while(true) {
                DatagramPacket packet = utilServidor.receive(socket);
                int result = utilServidor.processor(packet);

                utilServidor.send(socket, packet, result);    
                
                // somente para visualizar os resultados
                Thread.sleep(2000);
            }
            
        } catch (SocketException ex) {
            Logger.getLogger(ServidorMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ServidorMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
