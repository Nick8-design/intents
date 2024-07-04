package com.nickdieda.intents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class RejectActivity extends AppCompatActivity {
    EditText edRevoke;
    Button btn_Revoke,homea;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reject);


        edRevoke=findViewById(R.id.edt_revoke);
        btn_Revoke=findViewById(R.id.btn_revoke);
        homea=findViewById(R.id.home);

        DBModule db=new DBModule(this);

        btn_Revoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String regno=edRevoke.getText().toString().trim();
                if(regno.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please enter the registration number",Toast.LENGTH_SHORT).show();
                }else{
                    boolean isRevoked=db.deleteData(regno);
                    if(isRevoked){
                        Toast.makeText(getApplicationContext(),"Loan application revoked successfully",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"Failed! Could not revoke reg no does not exist",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        homea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getApplicationContext(), MainActivity.class));
            }
        });


    }
}