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
    TextView totalPriceText;
    TextView minimumPriceText;
    TextView totalCostNormTime;
    TextView totalLaborCostText;
    TextView additionalStaffingText;
    TextView hourlyRateText;

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
        hourlyRateText = findViewById(R.id.hourly_price_value);
        hourlyRateText.setText(intent.getStringExtra("hourlyRate"));
        totalPriceText = findViewById(R.id.total_price_value);
        totalPriceText.setText(intent.getStringExtra("totalPrice"));
        minimumPriceText = findViewById(R.id.minimum_price_value);
        minimumPriceText.setText(intent.getStringExtra("minimumPrice"));
        totalLaborCostText = findViewById(R.id.total_labor_cost_value);
        totalLaborCostText.setText(intent.getStringExtra("totalLaborCost"));
        totalCostNormTime = findViewById(R.id.total_cost_norm_time_value);
        totalCostNormTime.setText(intent.getStringExtra("totalCostNormTime"));
        additionalStaffingText = findViewById(R.id.additional_staff_value);
        additionalStaffingText.setText(intent.getStringExtra("additionalStaffing"));
    }
}
