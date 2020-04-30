package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView sqmText;
    TextView pricePerSqmText;
    TextView totalMaterialText;
    TextView combinedMetersText;
    TextView additionText;
    TextView establishmentText;
    TextView normTimeText;
    TextView totalMinPriceText;
    TextView totalLaborCostText;
    TextView additionalStaffingText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initTextViews();
    }

    private void initTextViews() {
        Intent intent = getIntent();
        sqmText = findViewById(R.id.sqm_value);
        sqmText.setText(intent.getStringExtra("sqm"));
        pricePerSqmText = findViewById(R.id.price_per_sqm_value);
        pricePerSqmText.setText(intent.getStringExtra("pricePerSqm"));
        totalMaterialText = findViewById(R.id.total_material_value);
        totalMaterialText.setText(intent.getStringExtra("totalMaterial"));
        combinedMetersText = findViewById(R.id.combined_meters_value);
        combinedMetersText.setText(intent.getStringExtra("combinedMeters"));
        additionText = findViewById(R.id.addition_value);
        additionText.setText(intent.getStringExtra("addition"));
        establishmentText = findViewById(R.id.establishment_value);
        establishmentText.setText(intent.getStringExtra("establishment"));
        normTimeText = findViewById(R.id.norm_time_value);
        normTimeText.setText(intent.getStringExtra("normTime"));
        totalMinPriceText = findViewById(R.id.total_min_price_value);
        totalMinPriceText.setText(intent.getStringExtra("totalMinPrice"));
        totalLaborCostText = findViewById(R.id.total_labor_cost_value);
        totalLaborCostText.setText(intent.getStringExtra("totalMinPrice"));
        additionalStaffingText = findViewById(R.id.additional_staff_value);
        additionalStaffingText.setText(intent.getStringExtra("additionalStaffing"));
//        resultIntent.putExtra("numberOfMen", calc.getNumberOfMen());
//        resultIntent.putExtra("totalTime", calc.getTotalTime());
    }
}
