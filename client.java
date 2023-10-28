package client_server_test;
import java.io.*;
import java.net.*;

public class client {
    public static void main(String[] args) {
        try {
        	// istanza oggetto di classe socket che rappresenta un socket di tipo client
        	// si connette all indirizzi ip localhost e alla porta 8087
            Socket connessione = new Socket("127.0.0.1", 8087);
            // RICEZIONE DATI
            //Il metodo getInputStream() restituisce un oggetto InputStream
            //che rappresenta il canale di ricezione dati.
            InputStream in = connessione.getInputStream();
            // oggetto InputStreamReader per convertire i dati ricevuti dal socket in caratteri.
            InputStreamReader input = new InputStreamReader(in);
            // oggetto BufferedReader per facilitare la lettura dei dati dal socket.
            BufferedReader sIN = new BufferedReader(input);
            
            // Invio dati
            // canale di trasmissione dati dal socket.
            OutputStream out = connessione.getOutputStream();
            // oggetto PrintWriter per inviare dati al socket.
            PrintWriter sOUT = new PrintWriter(out);
            
            // TEST INVIO UN NUMERO AL SERVER E MI ASPETTO QUEL NUMERO MOLTIPLICATO PER 5
            for(int i = 0; i < 10; i++)
            {
            	String numero = String.valueOf(i);
            	sOUT.println(numero);
            	sOUT.flush();
            }
            
            // Risposta dal server
            // legge una linea di testo dalla risposta del server.
            String risposta = sIN.readLine();

            // ciclo che legge le linee di testo dalla risposta del server finché non viene ricevuta una linea vuota.
            while( risposta != null )
            {
            	// stampo la risposta
                System.out.println( risposta );
                // leggo la prossima risposta dal server
                risposta = sIN.readLine();
            }

            // chiudo tutti gli stream 
            sIN.close();
            sOUT.close();
            connessione.close();
            // gestione eccezione
        } catch (Exception err) {
            System.out.println("ERRORE...\n");
        }
    }
}

