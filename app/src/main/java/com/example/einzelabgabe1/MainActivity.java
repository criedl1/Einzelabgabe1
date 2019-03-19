package com.example.einzelabgabe1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView txtMatNum;
    TextView txtServerAnswer;
    EditText edtxtMatNum;
    Button btnSend;


    public void Send(View view) {

        String Input = edtxtMatNum.getText().toString();
        //TcpServer instanzieren mit der Matrikelnummer
        TCPServer Tcpserver = new TCPServer(Input);
        //Thread erstellen und starten
        Thread serverSend = new Thread(Tcpserver);
        serverSend.start();


        try {
            serverSend.join();
            if (Tcpserver.fromServer != null) {
                txtServerAnswer.setText(Tcpserver.fromServer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void calculate(View view) {
        int quersumme = 0;
        ArrayList<Integer> binär = new ArrayList<Integer>();
        char[] arr = (edtxtMatNum.getText().toString()).toCharArray();

        for (int i = 0; i < arr.length; i++) {
            quersumme += arr[i] - 48;
        }
        int counter = 0;
        while (quersumme > 0) {
            binär.add(quersumme % 2);
            quersumme /= 2;
        }
        String sbinär = "";

        for (Integer s : binär) {
            sbinär += s;
        }
        String sbinärreverse = "";

        char[] sbinär1 = sbinär.toCharArray();

        for (int i = 0; i < sbinär1.length; i++) {
            sbinärreverse += sbinär1[sbinär1.length - i - 1];
        }
        edtxtMatNum.setText(sbinärreverse, TextView.BufferType.EDITABLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMatNum = findViewById(R.id.txtMatNum);
        txtServerAnswer = findViewById(R.id.txtServerAnswer);
        edtxtMatNum = findViewById(R.id.edtxtMatNum);
        btnSend = findViewById(R.id.btnSend);
    }
}
