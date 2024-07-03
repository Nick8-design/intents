package com.nickdieda.intents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ApplicationActivity extends AppCompatActivity {
Button cancel;
Button btnNextU;
EditText uname,uinstitute,uregno,uloan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_application);

        uname=findViewById(R.id.name);
        uinstitute=findViewById(R.id.institute);
        uregno=findViewById(R.id.regno);
        uloan=findViewById(R.id.loan);
        cancel=findViewById(R.id.cancelbtn);
        btnNextU=findViewById(R.id.btnNext);

        cancel.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
           //    Intent intent=new Intent(getApplicationContext(),ApplicationActivity.class);
               Intent intent=new Intent(ApplicationActivity.this,MainActivity.class);
               startActivity(intent);
       }
    });
        btnNextU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=uname.getText().toString().trim();
                String institute=uinstitute.getText().toString().trim();
                String regno=uregno.getText().toString().trim();
                int loan=Integer.parseInt(uloan.getText().toString().trim());

                //check if all fields are field in
                if (name.isEmpty()||institute.isEmpty()||regno.isEmpty() ||loan<=0){
                    Toast.makeText(getApplicationContext(),"Please fill in all fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent=new Intent(ApplicationActivity.this,SummaryActivity.class);
                    intent.putExtra("name",name);
                    intent.putExtra("inst",institute);
                    intent.putExtra("reg",regno);
                    intent.putExtra("loan",loan);
                    startActivity(intent);
                }
        }
        });




}
}