
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
public class ClienteMain {
    public static void main(String[] args) {
        Random random = new Random();
        char[] operations = {'-', '+'};
        
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
                randomOperation = random.nextInt(2);
                operation = operations[randomOperation];
                
                // Somente para visualizar os resultados
                Thread.sleep(2000);
            }
            
        } catch (SocketException ex) {
            Logger.getLogger(ClienteMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(ClienteMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
