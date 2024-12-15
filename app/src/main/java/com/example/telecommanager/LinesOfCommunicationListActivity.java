package com.example.telecommanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class LinesOfCommunicationListActivity extends AppCompatActivity {

    private Button btnCopperLine, btnOpticalLine, btnWirelessLine;
    private Button btnBackToSections, btnBackToMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lines_of_communication_list);

        // Инициализация кнопок
        btnCopperLine = findViewById(R.id.btnCopperLine);
        btnOpticalLine = findViewById(R.id.btnOpticalLine);
        btnWirelessLine = findViewById(R.id.btnWirelessLine);
        btnBackToSections = findViewById(R.id.btnBackToSections);
        btnBackToMainMenu = findViewById(R.id.btnBackToMainMenu);

        // Обработчики кнопок для типов линий связи
        btnCopperLine.setOnClickListener(v -> openLineDetailActivity("Медная линия"));
        btnOpticalLine.setOnClickListener(v -> openLineDetailActivity("Оптическая линия"));
        btnWirelessLine.setOnClickListener(v -> openLineDetailActivity("Беспроводная линия"));

        // Обработчик для кнопки "Вернуться к выбору раздела"
        btnBackToSections.setOnClickListener(v -> {
            Intent intent = new Intent(LinesOfCommunicationListActivity.this, ReferenceActivity.class);
            startActivity(intent);
            finish();
        });

        // Обработчик для кнопки "Вернуться в главное меню"
        btnBackToMainMenu.setOnClickListener(v -> {
            Intent intent = new Intent(LinesOfCommunicationListActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    // Метод для открытия детальной информации о линии связи
    private void openLineDetailActivity(String lineType) {
        Intent intent = new Intent(LinesOfCommunicationListActivity.this, LineDetailActivity.class);
        intent.putExtra("LINE_TYPE", lineType); // Передаем тип линии
        startActivity(intent);
    }
}
