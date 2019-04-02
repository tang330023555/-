package com.example.atry;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class File_new extends AppCompatActivity {

    String path;
    EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_file_new);
       edit=findViewById(R.id.file);
       String string="在上方输入文件名，无需加后缀。该txt文件的文件路径为：/sdcard。点击确定将进入输入数据界面文件输入界面共有四个输入框，输入完成后点击确定（如果为零请输入0），数据将存入该文件。";
        Button button=findViewById(R.id.button);
        TextView textView=findViewById(R.id.text);
        textView.setText(string);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                path=edit.getText().toString();
                Intent intent=new Intent(File_new.this,MainActivity.class);
                intent.putExtra("pathname",path);
                startActivity(intent);

            }
        });
    }

}
