package Networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    // 1. Thread that sends data continuously
    // 2. Thread that receives data continuously

    public void serverSetting() {
        try {
            // localhost, 10003
            serverSocket = new ServerSocket(10003); // bind
            System.out.println("Creating Server");
            clientSocket = serverSocket.accept(); // accept
            // Client Socket is connected
            System.out.println("Client Socket is connected");
        } catch (Exception e) {
        }
    }

    public void closeAll(){
        try {
            serverSocket.close();
            clientSocket.close();
            dataInputStream.close();
            dataOutputStream.close();
        } catch (Exception e) {
        }
    }

    public void streamSetting() {
        try {
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
        } catch (Exception e) {
        }
    }

    public void dataRecv() {
        new Thread(new Runnable() {
            boolean isThread = true;
            @Override
            public void run() {
                while(isThread) {
                    try {
                        String recvData = dataInputStream.readUTF();

                        if(recvData.equals("/quit"))
                            isThread = false;
                        else
                            System.out.println("Client : "+recvData);

                    } catch (Exception e) {
                    }
                }
            }
        }).start();

    }

    public void dataSend() {
        new Thread(new Runnable() {
            Scanner in = new Scanner(System.in);
            boolean isThread = true;
            @Override
            public void run() {
                while(isThread){
                    try {
                        String sendData = in.nextLine();
                        if(sendData.equals("/quit"))
                            isThread = false;
                        else
                            dataOutputStream.writeUTF(sendData);
                    } catch (Exception e) {
                    }
                }
            }
        }).start();

    }

    public Server() {
        serverSetting();
        streamSetting();
        dataRecv();
        dataSend();
    }

    public static void main(String[] args) {
        new Server();
    }
}