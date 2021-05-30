/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrea Monti
 */
public class serverT implements Runnable {
    int i =0;
    Socket s;
    File a = new File("chat");
    public void setS(Socket s) {
        this.s = s;
    }
    public void writeOnFile(String str) throws IOException{
    FileWriter fw = new  FileWriter(a);  
    String scrittura = readOnFile()+str;
    
    System.out.println("ciao: "+scrittura);
    fw.write(scrittura);
    fw.flush();
    }
    public String readOnFile() throws FileNotFoundException, IOException{
    FileReader fr = new FileReader(a);
    int x = fr.read();
    String messaggio="";
    while(x!=-1){
    messaggio = messaggio+(char)x;
    x = fr.read(); 
    }
        System.out.println("messaggio: "+messaggio);
    return messaggio;
    }
   char ab;
   public void setAb(char a){
    this.ab=a;
   }
   public char getAb(){
   return ab;
   }
    @Override
    public void run() {
    
        try {
            System.out.println("a");
            ServerSocket ss;
            if(ab=='a'){
            ss = new ServerSocket(4000);
            }else{
            ss = new ServerSocket(6000);
            }
            s = ss.accept();
            a.createNewFile();
            System.out.println("a");
            utenti one = new utenti("andrea", "monti", "monti@andrea");
            utenti two = new utenti("gianluca", "atlante", "giangluca@atlante");
            System.out.println("a");
            ObjectOutputStream ob = new ObjectOutputStream(s.getOutputStream());
            ObjectInputStream io = new ObjectInputStream(s.getInputStream());
            System.out.println("a");
            
            
            boolean searchOne=false;
            boolean srarchTwo=false;
            System.out.println("a"); 
            String h =(String)io.readObject();
            String[]dive=h.split("/");
            if(dive[0].equals(one.nome) && dive[1].equals(one.pw) && dive[2].equals(one.mail)){
            searchOne=true;   
            }else{
            if(dive[0].equals(two.nome) && dive[1].equals(two.pw) && dive[2].equals(two.mail)){
             srarchTwo=true;   
            }    
            }
            
            if(searchOne){
            //log one  
             ob.writeObject("1");
            boolean inf = true;
            while(inf){
               
            int x = (int)io.readObject();
                System.out.println("s: "+x);
            switch(x){
                case 1:
                  //scrivi
                    
                 String mess = "\n"+"<"+one.nome+">"+(String)io.readObject();
                 writeOnFile(mess);
                    break;
                    
                case 2:
                 //leggi 
                    System.out.println("a");
               ob.writeObject(readOnFile());
                    break;
                case 3:
                   inf=false; 
                    break;
            }
            }    
                
            }else {
            if(srarchTwo){
            //log two
           ob.writeObject("3");
            boolean inf = true;
            while(inf){
               
            int x = (int)io.readObject();
                System.out.println("s: "+x);
            switch(x){
                case 1:
                  //scrivi
                    
                 String mess = "\n"+"<"+two.nome+">"+(String)io.readObject();
                 writeOnFile(mess);
                    break;
                    
                case 2:
                 //leggi 
                    System.out.println("a");
               ob.writeObject(readOnFile());
                    break;
                case 3:
                   inf=false; 
                    break;
            }
            }    
           
           
            
            }else{
               ob.writeObject("2");
            }
            }
            
            
            
            
            
            
            
            
            
            
            
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(serverT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(serverT.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        
    }

}
