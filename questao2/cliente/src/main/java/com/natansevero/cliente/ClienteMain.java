
package com.natansevero.cliente;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author natan
 */
public class ClienteMain {
    public static void main(String[] args) {
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(1);
        newFixedThreadPool.submit(new Worker());
        newFixedThreadPool.shutdown();
    }
}
