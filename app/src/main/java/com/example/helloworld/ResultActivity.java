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
//    TextView normTimeText;
//    TextView establishmentText;
//    TextView addOnText;
//    TextView totalTimeText;
//    TextView hourlyPriceText;
//    TextView totalNormTimeText;
//    TextView addOnExtraPersonnelText;
//    TextView totalLaborCostText;

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
    }
}
