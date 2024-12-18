package com.example.telecommanager.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.telecommanager.R;

public class LineDetailActivity extends AppCompatActivity {

    private TextView lineTitleTextView, lineDescriptionTextView;
    private ImageView lineImageView;
    private Button btnBackToSections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_detail);

        lineTitleTextView = findViewById(R.id.lineTitleTextView);
        lineDescriptionTextView = findViewById(R.id.lineDescriptionTextView);
        lineImageView = findViewById(R.id.lineImageView);
        btnBackToSections = findViewById(R.id.btnBackToSections);
        String lineType = getIntent().getStringExtra("LINE_TYPE");
        lineTitleTextView.setText(lineType);

        switch (lineType) {
            case "Медная линия":
                lineDescriptionTextView.setText("Медные линии связи используются для передачи электрических сигналов.\n\n" +
                        "Медные кабели имеют хорошие электрические свойства, но ограничены относительно скорости и дальности передачи. Сигнал в медных линиях со временем теряет силу, что ограничивает их использование на больших расстояниях.\n\n" +
                        "Применения:\n" +
                        "• Телефонная связь.\n" +
                        "• Интернет (DSL, ADSL).\n" +
                        "• Видеонаблюдение на малые расстояния.\n\n" +
                        "Преимущества медных линий: низкая стоимость, простота монтажа, широкое распространение. Недостатки: ограниченная пропускная способность и чувствительность к помехам.");
                lineImageView.setImageResource(R.drawable.copper_line);
                break;

            case "Оптическая линия":
                lineDescriptionTextView.setText("Оптические линии связи используют световые волны для передачи информации, что обеспечивает крайне высокую скорость передачи и отсутствие потерь качества на больших расстояниях.\n\n" +
                        "Оптоволокно может передавать данные на расстояния до нескольких тысяч километров без значительных потерь сигнала, что делает его идеальным для межконтинентальных связей.\n\n" +
                        "Применения:\n" +
                        "• Высокоскоростной интернет.\n" +
                        "• Телевизионные и радиосигналы (в том числе спутниковая передача).\n" +
                        "• Сети передачи данных (например, для дата-центров и крупных вычислительных комплексов).\n\n" +
                        "Преимущества оптических линий: высокая скорость передачи, большая пропускная способность, устойчивость к помехам. Недостатки: высокая стоимость установки и монтажа, требование к точности подключения.");
                lineImageView.setImageResource(R.drawable.optical_fiber_line);
                break;

            case "Беспроводная линия":
                lineDescriptionTextView.setText("Беспроводные линии связи используют радиоволны, микроволновые и спутниковые технологии для передачи данных без использования проводных соединений. Это позволяет обеспечить мобильность и гибкость в системах связи.\n\n" +
                        "Беспроводные системы являются неотъемлемой частью мобильных сетей, Wi-Fi, спутниковых и радиосвязных систем. Они подходят для передачи данных в условиях, где прокладка проводных линий невозможна или экономически нецелесообразна.\n\n" +
                        "Применения:\n" +
                        "• Мобильная связь (GSM, LTE, 5G).\n" +
                        "• Радио- и телевизионное вещание.\n" +
                        "• Спутниковая связь.\n" +
                        "• Беспроводные локальные сети (Wi-Fi).\n\n" +
                        "Преимущества: высокая мобильность, отсутствие необходимости в физической инфраструктуре. Недостатки: ограниченная пропускная способность, восприимчивость к внешним помехам, ограниченная дальность передачи.");
                lineImageView.setImageResource(R.drawable.wireless_line);
                break;

            default:
                lineDescriptionTextView.setText("Описание для выбранной линии недоступно.");
                lineImageView.setImageResource(R.drawable.ic_back_arrow);
                break;
        }

        btnBackToSections.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}
