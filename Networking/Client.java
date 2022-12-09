package Networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket clientSocket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    // 1. Thread that sends data continuously
    // 2. Thread that receives data continuously

    public void connect() {
        try {
            System.out.println("Trying to connect.");
            clientSocket = new Socket("172.22.7.221", 10003); //enter your ip address.
            System.out.println("Connected");
        } catch (Exception e) {
            e.printStackTrace();
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

    public void streamSetting() {
        try {
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
        } catch (Exception e) {
        }
    }

    public Client() {
        connect();
        streamSetting();
        dataSend();
        dataRecv();
    }

    public static void main(String[] args) {
        new Client();
    }
}