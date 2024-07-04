package com.nickdieda.intents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UpdateLoan extends AppCompatActivity {
Button back,update;
EditText ureg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_loan);

        back=findViewById(R.id.back);
        update=findViewById(R.id.Update);
        ureg=findViewById(R.id.regnou);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regno = ureg.getText().toString().trim();
                Intent intent= new Intent(getApplicationContext(), Updatedb.class);
                intent.putExtra("reg",regno);
                startActivity(intent);
            }

        });


    }
}