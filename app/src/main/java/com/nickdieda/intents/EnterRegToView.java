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

public class EnterRegToView extends AppCompatActivity {
Button back,apply,check;
EditText ureg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_enter_reg_to_view);

        back=findViewById(R.id.back);
        apply=findViewById(R.id.apply);
        check=findViewById(R.id.check);
        ureg=findViewById(R.id.regnou);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getApplicationContext(), MainActivity.class));
            }
        });


        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getApplicationContext(), ApplicationActivity.class));
            }
        });

        check.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String regno = ureg.getText().toString().trim();
                if (regno.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Registration Number", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), ViewStatus.class);
                    intent.putExtra("reg", regno);
                    startActivity(intent);
                }
            }
        });


    }
}