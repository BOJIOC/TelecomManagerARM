package com.example.telecommanager.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.telecommanager.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button btnRegister, btnViewStats, btnViewFaults, btnNetworkDiagnostic, btnReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegister = findViewById(R.id.btnRegister);
        btnViewStats = findViewById(R.id.btnViewStats);
        btnViewFaults = findViewById(R.id.btnViewFaults);
        btnNetworkDiagnostic = findViewById(R.id.btnNetworkDiagnostic);
        btnReference = findViewById(R.id.btnReference);

        btnRegister.setOnClickListener(v -> navigateToActivity(RegisterElementActivity.class));
        btnViewStats.setOnClickListener(v -> navigateToActivity(StatisticsActivity.class));
        btnViewFaults.setOnClickListener(v -> navigateToActivity(FaultReportActivity.class));
        btnNetworkDiagnostic.setOnClickListener(v -> startNetworkDiagnosisActivity());
        btnReference.setOnClickListener(v -> navigateToActivity(ReferenceActivity.class));
    }

    /**
     * Переход на указанную активность.
     * @param targetActivity класс активности для перехода
     */
    private void navigateToActivity(Class<?> targetActivity) {
        try {
            Intent intent = new Intent(MainActivity.this, targetActivity);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Ошибка при переходе к активности", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Запуск активности диагностики сети.
     */
    private void startNetworkDiagnosisActivity() {
        Intent intent = new Intent(MainActivity.this, NetworkSpeedMeasurementActivity.class);
        startActivity(intent);
    }
}
