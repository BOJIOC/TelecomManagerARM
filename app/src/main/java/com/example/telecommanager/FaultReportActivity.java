package com.example.telecommanager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FaultReportActivity extends AppCompatActivity {

    private TextView titleTextView;
    private EditText issueDescriptionEditText;
    private Button reportButton, btnBackToMain;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fault_report);

        // Инициализация элементов
        titleTextView = findViewById(R.id.titleTextView);
        issueDescriptionEditText = findViewById(R.id.issueDescriptionEditText);
        reportButton = findViewById(R.id.reportButton);
        btnBackToMain = findViewById(R.id.btnBackToMain); // Кнопка возврата на главный экран
        dbHelper = new DatabaseHelper(this);

        // Обработчик клика на кнопку "Отправить отчет"
        reportButton.setOnClickListener(v -> {
            String description = issueDescriptionEditText.getText().toString();
            if (!description.isEmpty()) {
                // Текущее время в миллисекундах
                long timestamp = System.currentTimeMillis();
                String status = "Новый"; // Статус может быть изменен по необходимости

                // Создаем объект FaultReport
                FaultReport faultReport = new FaultReport(description, status, timestamp);
                dbHelper.addFaultReport(faultReport); // Добавляем в базу данных

                Toast.makeText(FaultReportActivity.this, "Отчет отправлен!", Toast.LENGTH_SHORT).show();
                finish(); // Закрытие активности
            } else {
                titleTextView.setText("Описание неисправности не может быть пустым.");
            }
        });

        // Обработчик клика на кнопку "Назад в главное меню"
        btnBackToMain.setOnClickListener(v -> {
            Intent intent = new Intent(FaultReportActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Закрытие текущей активности
        });
    }
}
