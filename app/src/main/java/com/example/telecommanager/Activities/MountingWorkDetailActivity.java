package com.example.telecommanager.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.telecommanager.R;

public class MountingWorkDetailActivity extends AppCompatActivity {

    private TextView workTitleTextView, workDescriptionTextView;
    private ImageView workImageView;
    private Button btnBackToWorkList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mounting_work_detail);

        workTitleTextView = findViewById(R.id.workTitleTextView);
        workDescriptionTextView = findViewById(R.id.workDescriptionTextView);
        workImageView = findViewById(R.id.workImageView);
        btnBackToWorkList = findViewById(R.id.btnBackToWorkList);

        String workType = getIntent().getStringExtra("WORK_TYPE");

        workTitleTextView.setText(workType);

        switch (workType) {
            case "Проволочный лоток":
                workDescriptionTextView.setText("Проволочный лоток представляет собой канал, выполненный из металлической проволоки, предназначенный для прокладки кабелей. Этот тип лотка часто используется в офисных и производственных помещениях, так как он легкий и удобный в монтаже. Он идеально подходит для установки в местах, где требуется хорошая вентиляция и охлаждение кабелей, что предотвращает перегрев. Проволочные лотки идеально подходят для использования в системах связи и электроснабжения.");
                workImageView.setImageResource(R.drawable.wire_tray);
                break;
            case "Металлический кабель-канал":
                workDescriptionTextView.setText("Металлический кабель-канал используется для защиты кабелей от внешних воздействий, таких как механическое повреждение или воздействие химических веществ. Каналы из металла могут выдерживать высокие нагрузки и часто применяются в промышленных и строительных объектах, где требуется высокая степень защиты. Металлический кабель-канал часто используется для внешней проводки и монтажа кабелей на стенах и потолках.");
                workImageView.setImageResource(R.drawable.metal_cable_channel);
                break;
            case "Пластиковый кабель-канал":
                workDescriptionTextView.setText("Пластиковый кабель-канал является отличным вариантом для установки кабелей в жилых и офисных помещениях, где требуется эстетичность и удобство монтажа. Эти каналы просты в установке, так как имеют легкий вес и можно легко соединять их между собой. Пластиковые каналы могут быть разных типов и форм, включая прямые, угловые и даже специальные каналы с крышками, что позволяет монтировать их в любом месте. Они обеспечивают отличную защиту от пыли и загрязнений.");
                workImageView.setImageResource(R.drawable.plastic_cable_channel);
                break;
            default:
                workDescriptionTextView.setText("Описание для выбранного канала недоступно.");
                workImageView.setImageResource(R.drawable.ic_back_arrow);
                break;
        }

        btnBackToWorkList.setOnClickListener(v -> finish());
    }
}

