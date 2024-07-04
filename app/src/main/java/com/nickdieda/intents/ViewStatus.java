package com.nickdieda.intents;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ViewStatus extends AppCompatActivity {
    DBModule dbModule;
    private RecyclerView recyclerView;
    private LoanAdapter adapter;

    Button back,home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_status);


        dbModule = new DBModule(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String ureg=getIntent().getStringExtra("reg");
        back=findViewById(R.id.back);
        home=findViewById(R.id.home);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getApplicationContext(), EnterRegToView.class));
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        String regnoToQuery = ureg;
        Cursor cursor = dbModule.viewData(regnoToQuery);
        adapter = new LoanAdapter(this, cursor);
        recyclerView.setAdapter(adapter);
    }



  @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the cursor and database when the activity is destroyed
        if (adapter != null && adapter.getCursor() != null) {
            adapter.getCursor().close();
        }
        dbModule.close();
    }
}


