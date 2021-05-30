/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Andrea Monti
 *
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
    
   
    ServerSocket ss2 = new ServerSocket(5000);
    //client 1
    serverT c = new serverT();
    c.setAb('a');
    Thread t1 = new Thread(c);
    t1.start();
    //client 2
    serverT ab = new serverT();
    ab.setAb('b');
    Thread t2 = new Thread(ab);
    t2.start();
    
    }
    
}
