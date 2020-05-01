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
    private EditText minSqm;
    private EditText additionalMen;
    private EditText additionalHours;
    Intent resultIntent;
    Map<String, Integer> articlesMap = new HashMap<>();

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
        minSqm = findViewById(R.id.min_sqm);
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
        int widthValue = Integer.parseInt(width.getText().toString());
        int heightValue = Integer.parseInt(height.getText().toString());
        int hourlyRateValue = Integer.parseInt(hourlyRate.getText().toString());
        double minSqmValue = Double.parseDouble(minSqm.getText().toString());
        int additionalMenValue = Integer.parseInt(additionalMen.getText().toString().equals("") ? "0" : additionalMen.getText().toString());
        int additionalHoursValue = Integer.parseInt(additionalHours.getText().toString().equals("") ? "0" : additionalHours.getText().toString());
        int discount = materialDiscount.getText().toString().isEmpty() ? 0 : Integer.parseInt(materialDiscount.getText().toString());
        int price = articlesMap.get(spinner.getSelectedItem().toString());

        CalculateInstallation calc = new
                CalculateInstallation(price, discount, widthValue, heightValue, hourlyRateValue, minSqmValue, additionalMenValue, additionalHoursValue);

        resultIntent.putExtra("sqm", calc.getSqm().toString());
        resultIntent.putExtra("pricePerSqm", calc.getPricePerSqm().toString());
        resultIntent.putExtra("hourlyRate", calc.getHourlyRate().toString());
        resultIntent.putExtra("materialPrice", calc.getMaterialPrice().toString());
        resultIntent.putExtra("materialMinPrice", calc.getMaterialMinPrice().toString());
        resultIntent.putExtra("totalMaterial", calc.getTotalMaterial().toString());
        resultIntent.putExtra("combinedMeters", calc.getCombinedMeters().toString());
        resultIntent.putExtra("addition", calc.getAddition().toString());
        resultIntent.putExtra("establishment", calc.getEstablishment().toString());
        resultIntent.putExtra("numberOfMen", calc.getNumberOfMen().toString());
        resultIntent.putExtra("normTime", calc.getNormTime().toString());
        resultIntent.putExtra("additionalStaffing", calc.getAdditionalStaffing().toString());
        resultIntent.putExtra("totalLaborCost", calc.getTotalLaborCost().toString());
        resultIntent.putExtra("totalPrice", calc.getTotalPrice().toString());
        resultIntent.putExtra("minimumPrice", calc.getMinimumPrice().toString());
        resultIntent.putExtra("totalCostNormTime", calc.getTotalCostNormTime().toString());
        resultIntent.putExtra("totalLaborCost", calc.getTotalLaborCost().toString());
        resultIntent.putExtra("totalTime", calc.getTotalTime().toString());

        CalculateSum calcSum = new
                CalculateSum(price, calc.getSqm(), minSqmValue, discount, calc.getCombinedMeters(), calc.getTotalCostNormTime(), calc.getAdditionalStaffing());

        resultIntent.putExtra("sumMaterialCost", calcSum.getMaterialCost().toString());
        resultIntent.putExtra("sumRebateMaterialCost", calcSum.getRebateMaterial().toString());
        resultIntent.putExtra("sumMaterialTotalCost", calcSum.getMaterialTotalCost().toString());
        resultIntent.putExtra("sumNormTime", calcSum.getNormTime().toString());
        resultIntent.putExtra("sumWorkAdditional", calcSum.getWorkAdditional().toString());
        resultIntent.putExtra("sumWorkTotalCost", calcSum.getWorkTotalCost().toString());
        resultIntent.putExtra("sumGrandTotal", calcSum.getGrandTotal().toString());
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
        if (minSqm.getText().toString().isEmpty()) {
            minSqm.setError("Minimum sqm is required!");
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
            articlesMap.put(keys[i], Integer.valueOf(values[i]));
        }
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
