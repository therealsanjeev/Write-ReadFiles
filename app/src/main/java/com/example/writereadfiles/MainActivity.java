package com.example.writereadfiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button btWrite,btRead;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btRead = findViewById(R.id.btRead);
        btWrite  =findViewById(R.id.btWrite);
        editText = findViewById(R.id.editText);
        textView=findViewById(R.id.textView);
        btWrite.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String text = editText.getText().toString();

                        File dataDir = ContextCompat.getDataDir(MainActivity.this);
                        File myFile = new File(dataDir,"file.text");

                        try{
                            FileOutputStream fos = new FileOutputStream(myFile);
                            fos.write(text.getBytes());
                        }catch (FileNotFoundException fnfe){
                            Toast.makeText(MainActivity.this,"File Not Fount",Toast.LENGTH_SHORT).show();
                        }catch (IOException ioe){
                            Toast.makeText(MainActivity.this,"Error While Writing File",Toast.LENGTH_SHORT).show();
                        }


                    }
                }
        );

        btRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                File dataDir = ContextCompat.getDataDir(MainActivity.this);
                File myFile = new File(dataDir,"file.text");

                try{
                    FileInputStream fis = new FileInputStream(myFile);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br =new BufferedReader(isr);

                    StringBuilder sb =  new StringBuilder();
                    String buffer = br.readLine();
                    while(buffer != null){
                        sb.append(buffer);
                        buffer =br.readLine();
                    }

                    String text =sb.toString();
                    textView.setText(text);

                }catch (FileNotFoundException fnfe){
                    Toast.makeText(MainActivity.this,"File Not Fount",Toast.LENGTH_SHORT).show();
                }catch (IOException ioe){
                    Toast.makeText(MainActivity.this,"Error While Reading File",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
