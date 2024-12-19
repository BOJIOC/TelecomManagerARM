package com.example.telecommanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ProtocolsListActivity extends AppCompatActivity {

    private Button btnRoutingProtocols, btnTelephonyProtocols, btnSurveillanceProtocols;
    private Button btnBackToSections, btnBackToMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protocols_list);

        btnRoutingProtocols = findViewById(R.id.btnRoutingProtocols);
        btnTelephonyProtocols = findViewById(R.id.btnTelephonyProtocols);
        btnSurveillanceProtocols = findViewById(R.id.btnSurveillanceProtocols);
        btnBackToSections = findViewById(R.id.btnBackToSections);
        btnBackToMainMenu = findViewById(R.id.btnBackToMainMenu);
        btnRoutingProtocols.setOnClickListener(v -> openProtocolDetailActivity("Протоколы маршрутизации"));
        btnTelephonyProtocols.setOnClickListener(v -> openProtocolDetailActivity("Протоколы телефонии"));
        btnSurveillanceProtocols.setOnClickListener(v -> openProtocolDetailActivity("Протоколы видеонаблюдения"));

        btnBackToSections.setOnClickListener(v -> {
            Intent intent = new Intent(ProtocolsListActivity.this, ReferenceActivity.class);
            startActivity(intent);
            finish();
        });

        btnBackToMainMenu.setOnClickListener(v -> {
            Intent intent = new Intent(ProtocolsListActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void openProtocolDetailActivity(String protocolCategory) {
        Intent intent = new Intent(ProtocolsListActivity.this, ProtocolDetailActivity.class);
        intent.putExtra("PROTOCOL_CATEGORY", protocolCategory);
        startActivity(intent);
    }
}
