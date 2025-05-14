package client;

import client.util.Hex;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Network extends Thread {
    
    private final static Logger logger = Logger.getLogger(Network.class.getName());
    
    private Socket socket;
    private Net_Listener net_listener;
    private PrintWriter out;
    private Scanner in;
    private InputStream inStream;
    private OutputStream outStream;
    
    protected interface Net_Listener {
        public void on_Rec(String msg);
    } 
    
    protected void set_Net_Listener(Net_Listener li) {
        net_listener = li;
    }
    
    protected void Connect_to(String host, int port) throws Exception {
        socket = new Socket(host, port);
        inStream = socket.getInputStream();
        outStream = socket.getOutputStream();
        out = new PrintWriter(outStream, true);
        in = new Scanner(inStream);
        start();
    }
    
    protected void send(String msg) {
        out.println(Hex.to_Hex(msg));
        out.flush();
    }
    
    protected void Close() {
        try {
            socket.close();
        } catch (Exception e) {
            logger.log(Level.SEVERE, null, e);
        }
    }
    
    @Override
    public void run() {
        String msg;
        try {
            while(in.hasNextLine()) {
                msg = Hex.to_string(in.nextLine());
                net_listener.on_Rec(msg);
            }
            Close();
        } catch (Exception e) {
            logger.log(Level.SEVERE, null, e);
        }
    }
}