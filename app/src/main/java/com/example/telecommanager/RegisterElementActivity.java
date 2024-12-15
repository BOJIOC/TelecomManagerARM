package com.example.telecommanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterElementActivity extends AppCompatActivity {

    private EditText nameEditText;
    private Spinner typeSpinner, statusSpinner;
    private Button saveButton, backToMenuButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_element);

        // Инициализация элементов
        nameEditText = findViewById(R.id.nameEditText);
        typeSpinner = findViewById(R.id.typeSpinner);
        statusSpinner = findViewById(R.id.statusSpinner);
        saveButton = findViewById(R.id.saveButton);
        backToMenuButton = findViewById(R.id.backToMenuButton);

        // Инициализация базы данных
        dbHelper = new DatabaseHelper(this);

        // Настройка адаптера для спиннера "Тип элемента"
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this,
                R.array.type_items, android.R.layout.simple_spinner_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);

        // Настройка адаптера для спиннера "Статус элемента"
        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this,
                R.array.status_items, android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(statusAdapter);

        // Устанавливаем подсказку как первый элемент
        typeSpinner.setSelection(0);
        statusSpinner.setSelection(0);

        // Обработчик нажатия на кнопку "Сохранить"
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String type = typeSpinner.getSelectedItem().toString();
                String status = statusSpinner.getSelectedItem().toString();

                // Проверка на пустые поля
                if (name.isEmpty()) {
                    Toast.makeText(RegisterElementActivity.this, "Пожалуйста, введите имя элемента.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Проверка на валидность выбора спиннеров
                if (type.equals("Выберите элемент")) {
                    Toast.makeText(RegisterElementActivity.this, "Пожалуйста, выберите тип элемента.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (status.equals("Выберите статус")) {
                    Toast.makeText(RegisterElementActivity.this, "Пожалуйста, выберите статус элемента.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Создание объекта сетевого элемента
                NetworkElement element = new NetworkElement(name, type, status);
                dbHelper.addNetworkElement(element); // Добавление в базу данных
                Toast.makeText(RegisterElementActivity.this, "Сетевой элемент добавлен", Toast.LENGTH_SHORT).show();
                finish(); // Закрытие активности
            }
        });

        // Обработчик нажатия на кнопку "Назад в главное меню"
        backToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterElementActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Закрытие текущей активности
            }
        });
    }
}
