/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.natansevero.cliente;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author natan
 */
public class Worker implements Runnable {
    
    private int n1;
    private int n2;
    private char operation;
    
    final Random random = new Random();
    final char[] operations = {'-', '+'};

    
    @Override
    public void run() {
        
        int n1 = random.nextInt(10);
        int n2 = random.nextInt(10);
        char operation = operations[random.nextInt(2)];
        
        UtilCliente utilCliente = new UtilCliente();
        
        try(DatagramSocket socket = new DatagramSocket()) {
            ExecutorService executorService = Executors.newFixedThreadPool(1);

            long init = 0, end = 0, totalTime = 0;
            int count = 0;
            
            while(true) {
                if(totalTime < 1000) {
                    init = System.currentTimeMillis();
                    
                    utilCliente.send(n1, n2, operation, socket);

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    totalTime = 0;
                    count = 0;
                    continue;
                }
                
                int result = utilCliente.receive(socket);
                
                end = System.currentTimeMillis();
                
                count++;
                
                totalTime += end - init;
                
                if(count == 10) System.out.println(String.format("Quantidade de req: %d/%d", count, totalTime));
                
                System.out.println("opa " + result);
                
                n1 = result;
                n2 = random.nextInt(10);
                operation = operations[random.nextInt(2)];
            }
            
            
        } catch (SocketException ex) {
            Logger.getLogger(ClienteMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
