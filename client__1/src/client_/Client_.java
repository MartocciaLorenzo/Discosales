/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Andrea Monti
 */
public class Client_ {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
    Scanner sc = new Scanner(System.in);
    //client one
    Socket s = new Socket("localhost", 6000);
    ObjectOutputStream ob = new ObjectOutputStream(s.getOutputStream());
    ObjectInputStream io = new ObjectInputStream(s.getInputStream());
        System.out.println("benvenuto, dimmi le credenziali");
        System.out.println("username: ");String userN = sc.next();
        System.out.println("pw: ");String pw = sc.next();
        System.out.println("mail: ");String mail = sc.next();
     String invio=userN+"/"+pw+"/"+mail;
        System.out.println("a");
        Thread.sleep(100);  
    ob.writeObject(invio);
    ob.flush();
        System.out.println("a");
    
        
    String mess = (String)io.readObject();
     
    if(mess.equals("3")){
    //verifica account    
        System.out.println("ciao: "+userN);
        boolean inf = true;
        while(inf){
        System.out.println("(1)scrivi nella chat \n(2)leggiChat\n(3)esci");
        int x = sc.nextInt();
        ob.writeObject(x);
        ob.flush();
        switch(x){
            case 1:
          //scrittura  
            System.out.println("dimmi cosa sccrivere: ");
            String mex = sc.next();
         ob.writeObject(mex);
        ob.flush();
       break;
            case 2:
          //lettura
              
                String var = (String)io.readObject();
                System.out.println("chat: "+var);
        break;
            case 3:
                inf=false;
                break;
        }
        }
        
    }else{
        System.out.println("nessun account esistente");
    }
    
    
    
    
    }
    
}
