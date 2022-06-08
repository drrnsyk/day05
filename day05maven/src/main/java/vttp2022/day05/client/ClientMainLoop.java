package vttp2022.day05.client;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


public class ClientMainLoop {
    public static void main(String[] args) throws IOException, EOFException {
         
        // connect to the server
        // 127.0.0.1 - local host - to connect to same server computer
        System.out.println("Connecting to localhost at port 3000..");

        Socket sock = new Socket("localhost",3000);

        System.out.println("Connected..");

        // get the input and output stream - data sent by bytes hence use input and output streams and not reader

        while (true) {
            InputStream is = sock.getInputStream();
            // use datainputstream to convert from bytes to a data that is readable on the read side
            DataInputStream dis = new DataInputStream(is);
    
            // get the output stream
            OutputStream os = sock.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            
            Console cons = System.console();
            String input = cons.readLine("Say something to the server\n");
            
            if (!input.equalsIgnoreCase("exit")) {
                // write to the server
                dos.writeUTF(input);
                dos.flush();
    
                // wait for response from server
                String response = dis.readUTF();
                System.out.printf("SERVER RESPONSE>>> %s\n", response);
            }
            else
            {
                System.out.printf("CONNECTION WILL NOW BE CLOSE\n");
                // close the stream
                is.close();
                os.close();
    
                // close the socket
                sock.close();
                break;
            }


            
        }
    }
}
