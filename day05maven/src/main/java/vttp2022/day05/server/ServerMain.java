package vttp2022.day05.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    
    public static void main(String[] args) throws IOException {
        
        // create a server socket and listen to an open port
        ServerSocket server = new ServerSocket(3000);
        System.out.println("Waiting for connection...");
        
        Socket sock = server.accept();
        System.out.println("Connection accepted");

         // get the input and output stream - data sent by bytes hence use input and output streams and not reader
         InputStream is = sock.getInputStream();
         // use datainputstream to convert from bytes to a data that is readable on the read side
         DataInputStream dis = new DataInputStream(is);
 
         // get the output stream
         OutputStream os = sock.getOutputStream();
         DataOutputStream dos = new DataOutputStream(os);

         // receive input from client
         String request = dis.readUTF();
         request = request.toUpperCase();

         // write back to client after converting the text to uppercase
         dos.writeUTF(request);

        // close the stream
        is.close();
        os.close();

        // close the socket
        sock.close();


 
    }
}
