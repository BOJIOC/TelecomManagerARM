package com.example.telecommanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ToolsListActivity extends AppCompatActivity {

    private Button btnCrimper, btnStripper, btnScrewdrivers, btnPliers, btnMultimeter, btnCableTester, btnHammerDrill;
    private Button btnBackToSections, btnBackToMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools_list);

        btnCrimper = findViewById(R.id.btnCrimper);
        btnStripper = findViewById(R.id.btnStripper);
        btnScrewdrivers = findViewById(R.id.btnScrewdrivers);
        btnPliers = findViewById(R.id.btnPillers);
        btnMultimeter = findViewById(R.id.btnMultimeter);
        btnCableTester = findViewById(R.id.btnCabletester);
        btnHammerDrill = findViewById(R.id.btnHammerDrill);
        btnBackToSections = findViewById(R.id.btnBackToSections);
        btnBackToMainMenu = findViewById(R.id.btnBackToMainMenu);

        btnCrimper.setOnClickListener(v -> openToolDetailActivity("Кримпер"));
        btnStripper.setOnClickListener(v -> openToolDetailActivity("Стриппер"));
        btnScrewdrivers.setOnClickListener(v -> openToolDetailActivity("Отвертки"));
        btnPliers.setOnClickListener(v -> openToolDetailActivity("Пассатижи"));
        btnMultimeter.setOnClickListener(v -> openToolDetailActivity("Мультиметр"));
        btnCableTester.setOnClickListener(v -> openToolDetailActivity("Кабельный тестер"));
        btnHammerDrill.setOnClickListener(v -> openToolDetailActivity("Перфоратор"));

        btnBackToSections.setOnClickListener(v -> {
            Intent intent = new Intent(ToolsListActivity.this, ReferenceActivity.class);
            startActivity(intent);
            finish();
        });

        btnBackToMainMenu.setOnClickListener(v -> {
            Intent intent = new Intent(ToolsListActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void openToolDetailActivity(String toolName) {
        Intent intent = new Intent(ToolsListActivity.this, ToolDetailActivity.class);
        intent.putExtra("TOOL_NAME", toolName);
        startActivity(intent);
    }
}
