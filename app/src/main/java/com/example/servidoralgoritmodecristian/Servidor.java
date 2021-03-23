package com.example.servidoralgoritmodecristian;

import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Servidor extends Thread {

    private final ServerSocket servidorSocket;
    public Servidor(int port) throws IOException {
        servidorSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        while (true) {
            try {
                String nomeHost = java.net.InetAddress.getLocalHost().getHostName();
                System.out.println("Nome do Servidor: " + nomeHost);
                System.out.println("Esperado cliente na porta " + servidorSocket.getLocalPort() + "...");

                Socket server = servidorSocket.accept();
                System.out.println("Conectado em: " + server.getRemoteSocketAddress());

                DataInputStream in = new DataInputStream(server.getInputStream());
                long t0 = in.readLong();
                Log.d("t0",""+t0);
                long t1 = System.currentTimeMillis();

                DataOutputStream out = new DataOutputStream(server.getOutputStream());

                long t2 = System.currentTimeMillis();

                out.writeLong(t0);      //Envia o tempo do proprio cliente
                out.writeLong(t1);      //Envia o tempo atual do servidor
                out.writeLong(t2);     //Envia o tempo da resposta para o cliente

                server.close();
            } catch (SocketTimeoutException s) {
                System.out.println("Socket expirou!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }

    }
    public long random(){
        long max = 10000;
        long min = 5000;
        double random = (Math.random()*((max-min)+1))+min;
        System.out.println("Random gerado: "+ (long) random);
        return (long) random;
    }
}