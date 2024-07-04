package com.nickdieda.intents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewActivity extends AppCompatActivity {
EditText edtView;
Button btnView,home;

FirebaseDatabase database;
DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view);
      edtView=findViewById(R.id.edt_view);
      btnView=findViewById(R.id.btn_view);
      home=findViewById(R.id.home);

      database=FirebaseDatabase.getInstance();
      dbRef=database.getReference("loans");

      btnView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            String name =edtView.getText().toString().trim();
            if(name.isEmpty()){
                Toast.makeText(getApplicationContext(),"Please enter your name ",Toast.LENGTH_SHORT).show();
            }else {
                    dbRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                           if(snapshot.hasChild(name)){
                               String institution=snapshot.child(name).child("institute").getValue().toString();
                               String regno=snapshot.child(name).child("regno").getValue().toString();
                               int loanAmount=Integer.parseInt(snapshot.child(name).child("loanAmount").getValue().toString());

                               String appinfo="Loan Application information\nName: "+name+"\n"+"Institution: "+institution+"\n"+"Registration Number: "+regno+"\n"+"Loan Amount: "+loanAmount;
                               Toast.makeText(getApplicationContext(),appinfo,Toast.LENGTH_LONG).show();

                               Toast.makeText(ViewActivity.this,"Sending application info to your email",Toast.LENGTH_SHORT).show();



                               //SAVE TO EMAIL
                               String recipient="musyokiagnes2002@gmail.com" ;
                               String subject="Loan Application Summary";


                               Intent email=new Intent(Intent.ACTION_SEND);
                               email.putExtra(Intent.EXTRA_EMAIL,new String[]{recipient});
                               email.putExtra(Intent.EXTRA_SUBJECT,subject);
                               email.putExtra(Intent.EXTRA_TEXT,appinfo);

                               email.setType("message/rfc822");

                               startActivity(Intent.createChooser(email,"Choose the desired mail app"));

                           }
                           else{
                               Toast.makeText(getApplicationContext(),"User has not made the application",Toast.LENGTH_SHORT).show();
                           }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getApplicationContext(),"User has not made the application",Toast.LENGTH_SHORT).show();
                        }
                    });

            }
          }
      });

      home.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity( new Intent(getApplicationContext(), ViewHome.class));
          }
      });

    }
}