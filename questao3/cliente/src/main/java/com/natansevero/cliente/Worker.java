/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.cliente;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author natan
 */
public class Worker implements Runnable {

    Random random = new Random();
    char[] operations = {'-', '+'};
    
    @Override
    public void run() {
        
        int n1 = random.nextInt(10);
        int n2 = random.nextInt(10);
        int randomOperation = random.nextInt(2);
        char operation = operations[randomOperation];
        
        UtilCliente utilCliente = new UtilCliente();
        
        try(DatagramSocket socket = new DatagramSocket()) {
            
            while(true) {
                utilCliente.send(n1, n2, operation, socket);
                int result = utilCliente.receive(socket);
                System.out.println(result);

                n1 = result;
                n2 = random.nextInt(10);
                operation = operations[random.nextInt(2)];
            }
            
        } catch (SocketException ex) {
            Logger.getLogger(ClienteMain.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
    
}
