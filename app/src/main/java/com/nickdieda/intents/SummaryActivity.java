package com.nickdieda.intents;

import static android.content.Intent.createChooser;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.nickdieda.intents.databinding.ActivitySummaryBinding;

public class SummaryActivity extends AppCompatActivity {
    Button pre,submit;
    TextView tvLoanSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary);

        DBModule db=new DBModule(this);

        pre=findViewById(R.id.previous);
        submit=findViewById(R.id.submit);
        tvLoanSummary=findViewById(R.id.tv_loan_summary);


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
