package com.example.telecommanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkSpeedMeasurementActivity extends AppCompatActivity {

    private TextView tvDownloadSpeed, tvErrorMessage;
    private ProgressBar progressBar;
    private Button btnMeasureSpeed;

    private Button btnBackToMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_speed_measurement);

        tvDownloadSpeed = findViewById(R.id.tvDownloadSpeed);
        tvErrorMessage = findViewById(R.id.tvErrorMessage);
        progressBar = findViewById(R.id.progressBar);
        btnMeasureSpeed = findViewById(R.id.btnMeasureSpeed);
        btnBackToMain = findViewById(R.id.btnBackToMain);
        btnMeasureSpeed.setOnClickListener(v -> {
            tvErrorMessage.setVisibility(TextView.GONE);
            tvDownloadSpeed.setText("Проверка скорости...");
            progressBar.setVisibility(ProgressBar.VISIBLE);

            new Thread(() -> {
                String speedResult = measureDownloadSpeed();
                runOnUiThread(() -> {
                    progressBar.setVisibility(ProgressBar.GONE);
                    if (speedResult.startsWith("Ошибка")) {
                        tvErrorMessage.setText(speedResult);
                        tvErrorMessage.setVisibility(TextView.VISIBLE);
                    } else {
                        tvDownloadSpeed.setText(speedResult);
                    }
                });
            }).start();
        });

        btnBackToMain.setOnClickListener(v -> {
            Intent intent = new Intent(NetworkSpeedMeasurementActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private String measureDownloadSpeed() {
        long startTime, endTime, totalBytes = 0;
        HttpURLConnection connection = null;

        try {
            URL url = new URL("https://www.google.com/images/branding/googlelogo/2x/googlelogo_light_color_92x30dp.png");
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(5000);
            connection.connect();

            startTime = System.currentTimeMillis();

            try (BufferedInputStream inputStream = new BufferedInputStream(connection.getInputStream())) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    totalBytes += bytesRead;
                }
            }

            endTime = System.currentTimeMillis();
            long timeTakenMillis = endTime - startTime;

            // Рассчитать скорость загрузки
            double speedKbps = (totalBytes * 8.0) / timeTakenMillis;
            double speedMbps = speedKbps / 1024;

            return String.format("%.2f Mb/s", speedMbps);
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка: Не удалось измерить скорость.";
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
