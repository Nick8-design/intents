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

public class Updatedb extends AppCompatActivity {
EditText uname,uinstitute,uregno,uloan;
Button uback,uupdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_updatedb);

           DBModule db=new DBModule(this);
           uname=findViewById(R.id.name);
           uinstitute=findViewById(R.id.institute);
           uregno=findViewById(R.id.regno);
           uloan=findViewById(R.id.loan);
           uback=findViewById(R.id.back);
           uupdate=findViewById(R.id.Update);

       String ureg=getIntent().getStringExtra("reg");
        uregno.setText(ureg);



        uback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getApplicationContext(), UpdateLoan.class));
            }
        });

      uupdate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String na = uname.getText().toString().trim();
               String inst = uinstitute.getText().toString().trim();
               int lo = Integer.parseInt(uloan.getText().toString().trim());

               if (na.isEmpty() || inst.isEmpty() || lo <= 0) {
                   Toast.makeText(Updatedb.this, "Please fill in all fields in order to update", Toast.LENGTH_SHORT).show();
               } else {


                   boolean result = db.updateData(na, inst, ureg, lo);
                   if (result) {
                       Toast.makeText(Updatedb.this, ureg + " Updated Succefully", Toast.LENGTH_SHORT).show();
                   } else {
                       Toast.makeText(Updatedb.this, ureg + " Not Updated", Toast.LENGTH_SHORT).show();
                   }
               }
           }
       });

    }
}