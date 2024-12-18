package com.example.telecommanager.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.telecommanager.R;

public class MountingWorksListActivity extends AppCompatActivity {

    private Button btnWireTray, btnMetalCableChannel, btnPlasticCableChannel;
    private Button btnBackToSections, btnBackToMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mounting_works_list);

        btnWireTray = findViewById(R.id.btnWireTray);
        btnMetalCableChannel = findViewById(R.id.btnMetalCableChannel);
        btnPlasticCableChannel = findViewById(R.id.btnPlasticCableChannel);
        btnBackToSections = findViewById(R.id.btnBackToSections);
        btnBackToMainMenu = findViewById(R.id.btnBackToMainMenu);

        btnWireTray.setOnClickListener(v -> openMountingDetailActivity("Проволочный лоток"));
        btnMetalCableChannel.setOnClickListener(v -> openMountingDetailActivity("Металлический кабель-канал"));
        btnPlasticCableChannel.setOnClickListener(v -> openMountingDetailActivity("Пластиковый кабель-канал"));

        btnBackToSections.setOnClickListener(v -> {
            Intent intent = new Intent(MountingWorksListActivity.this, ReferenceActivity.class);
            startActivity(intent);
            finish();
        });

        btnBackToMainMenu.setOnClickListener(v -> {
            Intent intent = new Intent(MountingWorksListActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void openMountingDetailActivity(String workType) {
        Intent intent = new Intent(MountingWorksListActivity.this, MountingWorkDetailActivity.class);
        intent.putExtra("WORK_TYPE", workType);
        startActivity(intent);
    }
}
