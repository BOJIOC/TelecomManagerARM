package com.example.telecommanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ReferenceActivity extends AppCompatActivity {

    private Button btnToolsForMounting, btnMountingWorks, btnLinesOfCommunication, btnNetworkProtocols, btnBackToMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference);

        // Инициализация кнопок
        btnToolsForMounting = findViewById(R.id.btnToolsForMounting);
        btnMountingWorks = findViewById(R.id.btnMountingWorks);
        btnLinesOfCommunication = findViewById(R.id.btnLinesOfCommunication);
        btnNetworkProtocols = findViewById(R.id.btnNetworkProtocols);
        btnBackToMainMenu = findViewById(R.id.btnBackToMainMenu); // Кнопка для возврата

        // Обработчики нажатий на кнопки
        btnToolsForMounting.setOnClickListener(v -> {
            Intent intent = new Intent(ReferenceActivity.this, ToolsListActivity.class);
            startActivity(intent);
        });

        btnMountingWorks.setOnClickListener(v -> {
            Intent intent = new Intent(ReferenceActivity.this, MountingWorksListActivity.class);
            startActivity(intent);
        });

        btnLinesOfCommunication.setOnClickListener(v -> {
            Intent intent = new Intent(ReferenceActivity.this, LinesOfCommunicationListActivity.class);
            startActivity(intent);
        });

        // Обработчики для новых кнопок
        btnNetworkProtocols.setOnClickListener(v -> {
            Intent intent = new Intent(ReferenceActivity.this, ProtocolsListActivity.class);
            startActivity(intent);
        });

        // Обработчик для кнопки возврата в главное меню
        btnBackToMainMenu.setOnClickListener(v -> {
            Intent intent = new Intent(ReferenceActivity.this, MainActivity.class); // Переход к главному меню
            startActivity(intent);
            finish();  // Закрываем текущую активность
        });
    }
}
