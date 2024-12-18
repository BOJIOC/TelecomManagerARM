package com.example.telecommanager.Activities;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.telecommanager.Databases.DatabaseHelper;
import com.example.telecommanager.Databases.FaultReport;
import com.example.telecommanager.R;

public class FaultReportActivity extends AppCompatActivity {

    private TextView titleTextView;
    private EditText issueDescriptionEditText;
    private Button reportButton, btnBackToMain;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fault_report);

        titleTextView = findViewById(R.id.titleTextView);
        issueDescriptionEditText = findViewById(R.id.issueDescriptionEditText);
        reportButton = findViewById(R.id.reportButton);
        btnBackToMain = findViewById(R.id.btnBackToMain);
        dbHelper = new DatabaseHelper(this);
        reportButton.setOnClickListener(v -> {
            String description = issueDescriptionEditText.getText().toString();
            if (!description.isEmpty()) {
                long timestamp = System.currentTimeMillis();
                String status = "Новый";

                // Создаем объект FaultReport
                FaultReport faultReport = new FaultReport(description, status, timestamp);
                dbHelper.addFaultReport(faultReport); // Добавляем в базу данных

                Toast.makeText(FaultReportActivity.this, "Отчет отправлен!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                titleTextView.setText("Описание неисправности не может быть пустым.");
            }
        });

        btnBackToMain.setOnClickListener(v -> {
            Intent intent = new Intent(FaultReportActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
