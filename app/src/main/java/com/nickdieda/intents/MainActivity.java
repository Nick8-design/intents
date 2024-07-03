package com.nickdieda.intents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import static androidx.core.splashscreen.SplashScreen.installSplashScreen;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
Button btnApply;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        // Pass 'this' (the current Activity) to installSplashScreen()
        installSplashScreen(this);
        setContentView(R.layout.activity_main);
       btnApply=findViewById(R.id.btnApply);

       btnApply.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
           //    Intent intent=new Intent(getApplicationContext(),ApplicationActivity.class);
               Intent intent=new Intent(MainActivity.this,ApplicationActivity.class);
               startActivity(intent);

           }
       });
    }
}