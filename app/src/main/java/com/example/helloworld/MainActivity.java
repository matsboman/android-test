package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    private EditText materialDiscount;
    private EditText width;
    private EditText height;
    private EditText hourlyRate;
    private EditText additionalMen;
    private EditText additionalHours;
    Intent resultIntent;
    Map<String, Double> articlesMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Application");
        resultIntent = new Intent(this, ResultActivity.class);

        addListenerOnButton();
        addListenerOnSpinnerItemSelection();
        initTextFields();
        initArticlesMap();
    }

    private void initTextFields() {
        materialDiscount = findViewById(R.id.material_discount);
        materialDiscount.setFilters(new InputFilter[]{ new MinMaxFilter("0", "100")});
        width = findViewById(R.id.width);
        height = findViewById(R.id.height);
        hourlyRate = findViewById(R.id.hourly_rate);
        additionalMen = findViewById(R.id.additional_men);
        additionalHours = findViewById(R.id.additional_hours);
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void addListenerOnButton() {
        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTextFieldsValid()) {
                    processResults();
                    startActivity(resultIntent);
                }
            }
        });
    }

    private void processResults() {
        Integer widthValue = Integer.parseInt(width.getText().toString());
        Integer heightValue = Integer.parseInt(height.getText().toString());
        Integer hourlyRateValue = Integer.parseInt(hourlyRate.getText().toString());
        int additionalMenValue = Integer.parseInt(additionalMen.getText().toString().equals("") ? "0" : additionalMen.getText().toString());
        int additionalHoursValue = Integer.parseInt(additionalHours.getText().toString().equals("") ? "0" : additionalHours.getText().toString());
        Double sqm = widthValue * heightValue / 1000000.0;
        Double discount = materialDiscount.getText().toString().isEmpty() ? 0 : Double.parseDouble(materialDiscount.getText().toString());
        Integer pricePerSqm = (int) Math.round(articlesMap.get(spinner.getSelectedItem().toString()) * (1.0 - discount / 100.0));
        Integer totalMaterial = (int) Math.round(sqm * pricePerSqm);
        Integer combinedMeters = (widthValue + heightValue) / 1000;

        CalculateInstallation calc = new CalculateInstallation(combinedMeters, hourlyRateValue, additionalMenValue, additionalHoursValue);

        resultIntent.putExtra("sqm", sqm.toString());
        resultIntent.putExtra("pricePerSqm", pricePerSqm.toString());
        resultIntent.putExtra("totalMaterial", totalMaterial.toString());
        resultIntent.putExtra("combinedMeters", combinedMeters.toString());
        resultIntent.putExtra("addition", calc.getAddition().toString());
        resultIntent.putExtra("establishment", calc.getEstablishment().toString());
        resultIntent.putExtra("numberOfMen", calc.getNumberOfMen().toString());
        resultIntent.putExtra("normTime", calc.getNormTime().toString());
        resultIntent.putExtra("additionalStaffing", calc.getAdditionalStaffing().toString());
        resultIntent.putExtra("totalLaborCost", calc.getTotalLaborCost().toString());
        resultIntent.putExtra("totalMinPrice", calc.getTotalMinPrice().toString());
        resultIntent.putExtra("totalTime", calc.getTotalTime().toString());
    }

    private boolean isTextFieldsValid() {
        width.setError(null);
        height.setError(null);
        additionalMen.setError(null);
        additionalHours.setError(null);
        boolean isValid = true;
        if (width.getText().toString().isEmpty()) {
            width.setError("Width is required!");
            isValid = false;
        }
        if (height.getText().toString().isEmpty()) {
            height.setError("Height is required!");
            isValid = false;
        }
        if (hourlyRate.getText().toString().isEmpty()) {
            hourlyRate.setError("Hourly rate is required!");
            isValid = false;
        }
        if (!additionalMen.getText().toString().isEmpty() &&
                additionalHours.getText().toString().isEmpty()) {
            additionalHours.setError("Additional men is required!");
            isValid = false;
        }
        if (!additionalHours.getText().toString().isEmpty() &&
                additionalMen.getText().toString().isEmpty()) {
            additionalMen.setError("Additional hours is required!");
            isValid = false;
        }
        return isValid;
    }

    private void initArticlesMap() {
        String[] keys = this.getResources().getStringArray(R.array.articles);
        String[] values = this.getResources().getStringArray(R.array.article_prices);
        for (int i = 0; i < keys.length; i++) {
            articlesMap.put(keys[i], Double.valueOf(values[i]));
        }
    }

    private void debugToaster() {
        Toast.makeText(MainActivity.this,
                "Spinner: " + spinner.getSelectedItem() +
                        "\nPrice: " + articlesMap.get(spinner.getSelectedItem()) +
                        "\nMaterial discount: " + materialDiscount.getText().toString() +
                        "\nWidth: " + width.getText().toString() +
                        "\nHeight: " + height.getText().toString() +
                        "\nAdditional men: " + additionalMen.getText().toString() +
                        "\nAdditional hours: " + additionalHours.getText().toString(),
                Toast.LENGTH_LONG).show();
    }

    private void displayInfo(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
}
