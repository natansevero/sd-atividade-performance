
package com.natansevero.cliente;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author natan
 */
public class ClienteMain {
    public static void main(String[] args) {
        
        ExecutorService service = Executors.newFixedThreadPool(10);
        
        // Apenas pra teste
        for(int i = 0; i < 10; i++) {
            service.submit(new Worker());
        }
        
        service.shutdown();
        
    }
}
