package com.example.telecommanager.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.telecommanager.R;

public class ReferenceActivity extends AppCompatActivity {

    private Button btnToolsForMounting, btnMountingWorks, btnLinesOfCommunication, btnNetworkProtocols, btnBackToMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reference);

        btnToolsForMounting = findViewById(R.id.btnToolsForMounting);
        btnMountingWorks = findViewById(R.id.btnMountingWorks);
        btnLinesOfCommunication = findViewById(R.id.btnLinesOfCommunication);
        btnNetworkProtocols = findViewById(R.id.btnNetworkProtocols);
        btnBackToMainMenu = findViewById(R.id.btnBackToMainMenu);

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

        btnNetworkProtocols.setOnClickListener(v -> {
            Intent intent = new Intent(ReferenceActivity.this, ProtocolsListActivity.class);
            startActivity(intent);
        });

        btnBackToMainMenu.setOnClickListener(v -> {
            Intent intent = new Intent(ReferenceActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
