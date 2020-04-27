package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Application");
    }

    public void sendMessage(View view) {
        EditText editText = findViewById(R.id.txt_message);
        String message = editText.getText().toString();
        Intent intent = new Intent(this, MessageActivity.class);
        intent.putExtra("message", message);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                displayInfo(("Search option is selected"));
                return true;
            case R.id.action_share:
                displayInfo(("Share option is selected"));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void displayInfo(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
