package com.example.telecommanager.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.telecommanager.Databases.DatabaseHelper;
import com.example.telecommanager.Databases.NetworkElement;
import com.example.telecommanager.R;

public class RegisterElementActivity extends AppCompatActivity {

    private EditText nameEditText;
    private Spinner typeSpinner, statusSpinner;
    private Button saveButton, backToMenuButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_element);

        nameEditText = findViewById(R.id.nameEditText);
        typeSpinner = findViewById(R.id.typeSpinner);
        statusSpinner = findViewById(R.id.statusSpinner);
        saveButton = findViewById(R.id.saveButton);
        backToMenuButton = findViewById(R.id.backToMenuButton);
        dbHelper = new DatabaseHelper(this);

        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this,
                R.array.type_items, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);
        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this,
                R.array.status_items, android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(statusAdapter);
        typeSpinner.setSelection(0);
        statusSpinner.setSelection(0);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String type = typeSpinner.getSelectedItem().toString();
                String status = statusSpinner.getSelectedItem().toString();

                if (name.isEmpty()) {
                    Toast.makeText(RegisterElementActivity.this, "Пожалуйста, введите имя устройства.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (type.equals("Выберите тип устройства") || type.isEmpty()) {
                    Toast.makeText(RegisterElementActivity.this, "Пожалуйста, выберите тип устройства.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (status.equals("Выберите статус") || status.isEmpty()) {
                    Toast.makeText(RegisterElementActivity.this, "Пожалуйста, выберите статус устройства.", Toast.LENGTH_SHORT).show();
                    return;
                }

                NetworkElement element = new NetworkElement(name, type, status);
                dbHelper.addNetworkElement(element);
                Toast.makeText(RegisterElementActivity.this, "Устройство добавлено", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        backToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterElementActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
