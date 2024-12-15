package com.example.telecommanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MountingWorksListActivity extends AppCompatActivity {

    private Button btnWireTray, btnMetalCableChannel, btnPlasticCableChannel;
    private Button btnBackToSections, btnBackToMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mounting_works_list);

        // Инициализация кнопок
        btnWireTray = findViewById(R.id.btnWireTray);
        btnMetalCableChannel = findViewById(R.id.btnMetalCableChannel);
        btnPlasticCableChannel = findViewById(R.id.btnPlasticCableChannel);
        btnBackToSections = findViewById(R.id.btnBackToSections);
        btnBackToMainMenu = findViewById(R.id.btnBackToMainMenu);

        // Обработчики кнопок для разных типов монтажных работ
        btnWireTray.setOnClickListener(v -> openMountingDetailActivity("Проволочный лоток"));
        btnMetalCableChannel.setOnClickListener(v -> openMountingDetailActivity("Металлический кабель-канал"));
        btnPlasticCableChannel.setOnClickListener(v -> openMountingDetailActivity("Пластиковый кабель-канал"));

        // Обработчик для кнопки "Вернуться к выбору раздела"
        btnBackToSections.setOnClickListener(v -> {
            Intent intent = new Intent(MountingWorksListActivity.this, ReferenceActivity.class);
            startActivity(intent);
            finish();
        });

        // Обработчик для кнопки "Вернуться в главное меню"
        btnBackToMainMenu.setOnClickListener(v -> {
            Intent intent = new Intent(MountingWorksListActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    // Метод для открытия детальной информации о монтаже
    private void openMountingDetailActivity(String workType) {
        Intent intent = new Intent(MountingWorksListActivity.this, MountingWorkDetailActivity.class);
        intent.putExtra("WORK_TYPE", workType); // Передаем тип работы
        startActivity(intent);
    }
}
