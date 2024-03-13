package FinalProject.ChatsSys;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server2 implements Runnable {

    private ArrayList<ConnectionHandler> connections;
    private ServerSocket server;
    private boolean done;
    private ExecutorService pool;

    public Server2(){
        connections = new ArrayList<>();
        done = false;
    }
    @Override
    public void run() {
        try{
            server = new ServerSocket(1234);
            pool = Executors.newCachedThreadPool();
            while (!done) {
                Socket client = server.accept();
                ConnectionHandler handler = new ConnectionHandler(client);
                connections.add(handler);
                pool.execute(handler);
            }
        }catch(Exception e){
            shutdown();
        }
    }


    public void broadcast(String message){
        for(ConnectionHandler ch : connections){
            if (ch != null){
                ch.sendMessage(message);
                
            }
        }
    }

    public void shutdown(){
        try {
            done = true;
            pool.shutdownNow();
            if (!server.isClosed()) {
            server.close();
            }
            for(ConnectionHandler ch : connections){
                ch.shutdown();
            }
        }catch(Exception e){
            
        }
    }

    class ConnectionHandler implements Runnable{

        private Socket client; // socket of the client
        private BufferedReader in; // input stream
        private PrintWriter out; // output stream

        public ConnectionHandler(Socket client){
            this.client = client;
        }
        @Override
        public void run() {
            try{
                out = new PrintWriter(client.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out.println("Enter your nickname: ");
                String nickname = in.readLine();
                System.out.println(nickname + " connected");
                broadcast(nickname + " joined the chat");
                String message;
                while ((message = in.readLine()) != null) {
                    if (message.startsWith("/nick ")) {
                        String[] messageSplit = message.split(" ", 2);
                        if (messageSplit.length == 2) {
                            broadcast(nickname + " changed their nickname to " + messageSplit[1]);
                            System.out.println(nickname + " changed their nickname to " + messageSplit[1]);
                            nickname = messageSplit[1];
                            out.println("Successfully changed nickname to " + nickname);
                        }else{
                            out.println("No nickname specified");
                        }
                    }else if (message.startsWith("/quit")){ 
                        broadcast(nickname + " has left the chat");
                        shutdown();
                    }else{
                        broadcast(nickname + ": " + message);
                    }
                }
            }catch(Exception e){
                shutdown();
            }
        }

        public void sendMessage(String message){
            out.println(message);
        }

        public void shutdown(){
            try{
                in.close();
                out.close();
                if (!client.isClosed()) {
                client.close();
                }
            }catch(Exception e){
                
            }
        }
    }

    public void start() {
        new Thread(this).start();
    }

    // public static void main(String[] args) {
    //     Server2 server = new Server2();
    //     server.run();
    // }

}
