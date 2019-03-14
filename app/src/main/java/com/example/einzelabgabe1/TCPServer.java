package com.example.einzelabgabe1;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer implements Runnable {

    String MatNum = null;
    String fromServer = null;


    TCPServer(String MatNum){
        this.MatNum = MatNum;
    }

    @Override
    public void run() {
        if (MatNum != null) {
            try {

                //Socket erstellen
                Socket clientSocket = new Socket("se2-isys.aau.at", 53212);

                //Outputstream zum Server erstellen
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
                //Inputstream vom Server erstellen
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                //MatNum in den Outputstream geben
                outToServer.writeBytes(MatNum + '\n');

                //in "fromServer" den Inputstream vom Server speichern
                fromServer = inFromServer.readLine();

                //Socket wieder schließen
                clientSocket.close();
            } catch (Exception x) {
                x.printStackTrace();
            }


        }
    }

}


