package com.nickdieda.intents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class OnlineDatabase extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LonAdapter adapter;
    private List<LoanApplication> loanList = new ArrayList<>();
    FirebaseDatabase db;
    DatabaseReference dbRef;
Button back,home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_online_database);
        back=findViewById(R.id.back);
        home=findViewById(R.id.home);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LonAdapter(this, loanList);
        recyclerView.setAdapter(adapter);


        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference("loans");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                loanList.clear(); // Clear the list before adding new data
                for (DataSnapshot loanSnapshot : dataSnapshot.getChildren()) {
        LoanApplication loan = loanSnapshot.getValue(LoanApplication.class);
        loanList.add(loan);
    }
                adapter.notifyDataSetChanged();
}

@Override
public void onCancelled(DatabaseError databaseError) {
    Toast.makeText(OnlineDatabase.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
}
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ViewHome.class));
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });


                }
                }