package com.example.atry;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



public class MainActivity extends AppCompatActivity {

    private EditText edit1;
    private EditText edit2;
    private EditText edit3;
    private EditText edit4;
    private String Date_edit1;
    private String Date_edit2;
    private String Date_edit3;
    private String Data_edit4;
    private String data;

    private FileOutputStream outStream = null;
    private  File file;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= 23 && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        Button open_File=findViewById(R.id.input);
        edit1=findViewById(R.id.et1);
        edit2=findViewById(R.id.et2);
        edit3=findViewById(R.id.et3);
        edit4=findViewById(R.id.et4);


        textView=findViewById(R.id.data);


        //文件不可以写入/storage
        String path="/sdcard";
        String name=getIntent().getStringExtra("pathname")+".txt";
        file=new File(path,name);

        edit1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS)!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS},1);
                }
                edit2.requestFocus();
                return false;
            }


        });
        edit2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                edit3.requestFocus();
                return false;
            }
        });
        edit3.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                edit4.requestFocus();
                return false;
            }
        });



        open_File.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data_set();
                data_check();
                data_into_file();
                empty();
                edit1.requestFocus();
                String datas= (String) textView.getText()+data;
                textView.setText(datas);
            }
        });

    }

    public void Data_set(){
        Date_edit1=edit1.getText().toString();
        Date_edit2=edit2.getText().toString();
        Date_edit3=edit3.getText().toString();
        Data_edit4=edit4.getText().toString();
        data=Date_edit1+","+Date_edit2+","+Date_edit3+","+Data_edit4+"\r\n";
    }

    public void data_check(){

        char[] ce1 = Date_edit1.toCharArray();
        char[] ce2 = Date_edit2.toCharArray();
        char[] ce3 = Date_edit3.toCharArray();
        for(int i=0;i<ce1.length;i++){
            int a=0;
            if(ce1[i]=='.'){
                a++;
            }
            if(a==2){
                Toast.makeText(MainActivity.this,"数据输入错误",Toast.LENGTH_SHORT).show();
                edit1.setText("");
                break;
            }
        }
        for(int i=0;i<ce2.length;i++){
            int a=0;
            if(ce2[i]=='.'){
                a++;
            }
            if(a==2){
                Toast.makeText(MainActivity.this,"数据输入错误",Toast.LENGTH_SHORT).show();
                edit2.setText("");
                break;
            }
        }
        for(int i=0;i<ce3.length;i++){
            int a=0;
            if(ce3[i]=='.'){
                a++;
            }
            if(a==2){
                Toast.makeText(MainActivity.this,"数据输入错误",Toast.LENGTH_SHORT).show();
                edit3.setText("");
                break;
            }
        }
    }

    public void data_into_file() {
        try {
            outStream = new FileOutputStream(file,true);
            outStream.write(data.getBytes());
            outStream.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void empty(){
        edit1.setText("");
        edit2.setText("");
        edit3.setText("");
        edit4.setText("");
    }

    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
        switch(requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                }
        }
    }


}
