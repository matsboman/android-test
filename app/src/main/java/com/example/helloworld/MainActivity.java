package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    private Button btnSubmit;
    Map<String, Integer> articlesMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Application");

        addListenerOnButton();
        addListenerOnSpinnerItemSelection();
        initArticlesMap();
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {
        spinner = findViewById(R.id.spinner);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivityß.this,
                        "OnClickListener : " +ß
                                "\nSpinner: " + String.valueOf(spinner.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void initArticlesMap() {
        String[] keys = this.getResources().getStringArray(R.array.articles);
        String[] values = this.getResources().getStringArray(R.array.article_prices);
        for (int i = 0; i < keys.length; i++) {
            Log.d("DEBUG", keys[i]);
            Log.d("DEBUG", values[i]);
            articlesMap.put(keys[i], Integer.valueOf(values[i]));
        }
    }

//    public void sendMessage(View view) {
//        EditText editText = findViewById(R.id.txt_message);
//        String message = editText.getText().toString();
//        Intent intent = new Intent(this, MessageActivity.class);
//        intent.putExtra("message", message);
//        startActivity(intent);
//    }

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
