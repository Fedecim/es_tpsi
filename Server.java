package client_server_test;

import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
        	// oggetto ServerSocket che rappresenta un server socket. Il server socket ascolta sulla porta 8087.
            ServerSocket ssocket = new ServerSocket(8087);
            
            System.out.println("Server connesso!\n");
            // Una volta che un client si è connesso, viene creato un oggetto Socket
            // che rappresenta la connessione tra il server e il client.
            Socket connessione = ssocket.accept();
            
            System.out.println("Server in ascolto...\n");
            
            // Ricezione dati
            InputStream in = connessione.getInputStream();
            InputStreamReader input = new InputStreamReader(in);
            BufferedReader sIN = new BufferedReader(input);
                
            // Invio dati
            OutputStream out = connessione.getOutputStream();
            PrintWriter sOUT = new PrintWriter(out);
            
            // invio stringa al client

            sOUT.println( "<Server> Connessione riuscita ! " );
            sOUT.flush();
            
            // leggo dati in arrivo dal client
            String mex = sIN.readLine();
            // ciclo per leggere dati in arrivo dal client
            while(mex != null && mex.length() > 0) {
            	int numero = Integer.parseInt(mex);
            	numero *= 5;
            	mex = String.valueOf(numero);
            	sOUT.println( "<Server> risultato operazione : " + mex );
            	sOUT.flush();
                mex = sIN.readLine();
            }
            //System.out.println("Messaggio ricevuto dal client: " + mex);
                
            // Chiusura connessione
            sIN.close();
            sOUT.close();
            connessione.close();
        } catch(Exception err) {
            System.out.println("ERRORE...\n");
        }
    }
}
