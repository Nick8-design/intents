package com.nickdieda.intents;

import static android.content.Intent.createChooser;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nickdieda.intents.databinding.ActivitySummaryBinding;

public class SummaryActivity extends AppCompatActivity {
    Button pre,submit;
    TextView tvLoanSummary;


    FirebaseDatabase firebasedb;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary);

        DBModule db=new DBModule(this);

        pre=findViewById(R.id.previous);
        submit=findViewById(R.id.submit);
        tvLoanSummary=findViewById(R.id.tv_loan_summary);


        firebasedb=FirebaseDatabase.getInstance();
        dbRef=firebasedb.getReference("loans");



        String name=getIntent().getStringExtra("name");
        String inst=getIntent().getStringExtra("inst");
        String regno=getIntent().getStringExtra("reg");
        int loan=getIntent().getIntExtra("loan",0);

        String message= "Name:  "+name
                +"\n"+"Institute:  "+inst+
                "\nRegistration Number:  "+regno+
                "\nLoan Amount:  "+loan;

        tvLoanSummary.setText(message);
        pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SummaryActivity.this, ApplicationActivity.class);
                startActivity(intent);


            }
        });

submit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        // save data to firebase
        LoanApplication application=new LoanApplication(name,inst,regno,loan);
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dbRef.child(name).setValue(application);
                Toast.makeText(getApplicationContext(), "Loan application submitted successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Failed to submit loan application", Toast.LENGTH_SHORT).show();

            }
        });





        //save data to sqllite db
        boolean results=db.insertData(name,inst,regno,loan);
        if(results){
            Toast.makeText(getApplicationContext(),
                    "Record inserted  successfully",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),
                    "Error! Counld not insert record",
                    Toast.LENGTH_SHORT).show();
        }
        //send email
        String recipient="musyokiagnes2002@gmail.com" ;
        String subject="Loan Application Summary";


        Intent email=new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL,new String[]{recipient});
        email.putExtra(Intent.EXTRA_SUBJECT,subject);
        email.putExtra(Intent.EXTRA_TEXT,message);

        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email,"Choose the desired mail app"));

    }
});

    }

}
