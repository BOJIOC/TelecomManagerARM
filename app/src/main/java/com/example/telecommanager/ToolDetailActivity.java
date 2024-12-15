package com.example.telecommanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ToolDetailActivity extends AppCompatActivity {

    private TextView toolNameTextView, toolDescriptionTextView;
    private ImageView toolImageView;
    private Button btnBackToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_detail);

        // Инициализация элементов интерфейса
        toolNameTextView = findViewById(R.id.toolNameTextView);
        toolDescriptionTextView = findViewById(R.id.toolDescriptionTextView);
        toolImageView = findViewById(R.id.toolImageView);
        btnBackToMenu = findViewById(R.id.btnBackToMenu);

        // Получаем данные из Intent
        String toolName = getIntent().getStringExtra("TOOL_NAME");

        // Устанавливаем название инструмента
        toolNameTextView.setText(toolName);

        // Настроим описание и изображение инструмента в зависимости от переданных данных
        switch (toolName) {
            case "Кримпер":
                toolDescriptionTextView.setText("Кримпер — это инструмент, предназначенный для обжима разъемов, клеммников или обжимных гильз на проводах. Он используется для создания надежного электрического соединения между проводом и разъемом. Как работает кримпер:\n\n" +
                        "• Вставьте провод и контакт (например, клемму) в предназначенные для этого места на инструменте.\n" +
                        "• Сожмите ручки кримпера для обжима контакта.\n" +
                        "• Обжатое соединение обеспечивает надежный контакт, исключая вероятность плохого соединения и обрыва.");
                toolImageView.setImageResource(R.drawable.crimper);
                break;

            case "Стриппер":
                toolDescriptionTextView.setText("Стриппер — это инструмент, предназначенный для снятия изоляции с проводов. Он позволяет аккуратно удалять внешний слой изоляции, не повреждая проводник. Как работает стриппер:\n\n" +
                        "• Выберите отверстие на инструменте в зависимости от диаметра провода.\n" +
                        "• Вставьте провод в отверстие и сожмите ручки.\n" +
                        "• Лезвия инструмента аккуратно срезают изоляцию, оставляя провод целым.\n\n" +
                        "Стриппер незаменим при подготовке проводов к подключению к разъемам, клеммам или другим компонентам.");
                toolImageView.setImageResource(R.drawable.stripper);
                break;

            case "Отвертки":
                toolDescriptionTextView.setText("Отвертки — это универсальный инструмент для работы с различными винтами и крепежами. Они широко используются в телекоммуникациях для монтажа и демонтажа оборудования. Как использовать отвертки:\n\n" +
                        "• Выберите подходящий тип отвертки (плоская, крестовая или специальная).\n" +
                        "• Поместите наконечник отвертки в головку винта и начните вращение для закручивания или откручивания.\n\n" +
                        "Отвертки необходимы для сборки разъемов, крепления кабельных каналов и другой инфраструктуры.");
                toolImageView.setImageResource(R.drawable.screwdrivers);
                break;

            case "Пассатижи":
                toolDescriptionTextView.setText("Пассатижи — это универсальный инструмент для захвата, сгибания, обрезки и удержания проводов. Они незаменимы при работе с кабелями в условиях ограниченного пространства. Как использовать пассатижи:\n\n" +
                        "• Захватите провод или другой объект губками пассатижей.\n" +
                        "• Используйте инструмент для сгибания или удержания объекта.\n" +
                        "• Для обрезки проводов используйте встроенные режущие кромки.\n\n" +
                        "Пассатижи необходимы для установки кабельных соединений в сложных условиях.");
                toolImageView.setImageResource(R.drawable.pilers);
                break;

            case "Мультиметр":
                toolDescriptionTextView.setText("Мультиметр — это многофункциональный измерительный прибор, используемый для диагностики электрических цепей. Он измеряет напряжение, ток и сопротивление. Как использовать мультиметр:\n\n" +
                        "• Выберите режим работы (вольтметр, амперметр, омметр) с помощью переключателя.\n" +
                        "• Подключите щупы к цепи.\n" +
                        "• Считайте показания с дисплея прибора.\n\n" +
                        "Мультиметр необходим для проверки целостности цепей и параметров электрического оборудования.");
                toolImageView.setImageResource(R.drawable.multimeter);
                break;

            case "Кабельный тестер":
                toolDescriptionTextView.setText("Кабельный тестер — это инструмент для проверки целостности и правильности соединений в кабелях. Он помогает выявить обрывы, замыкания и перепутанные провода. Как использовать кабельный тестер:\n\n" +
                        "• Подключите оба конца кабеля к тестеру.\n" +
                        "• Запустите проверку, нажав кнопку на устройстве.\n" +
                        "• Интерпретируйте результаты по индикаторам тестера.\n\n" +
                        "Кабельный тестер необходим для монтажа и диагностики сетевых соединений.");
                toolImageView.setImageResource(R.drawable.cabletester);
                break;

            case "Перфоратор":
                toolDescriptionTextView.setText("Перфоратор — это мощный инструмент для сверления отверстий в твердых материалах, таких как бетон и кирпич. Как использовать перфоратор:\n\n" +
                        "• Выберите подходящее сверло и установите его в патрон перфоратора.\n" +
                        "• Выберите режим работы (сверление или сверление с ударом).\n" +
                        "• Направьте сверло на поверхность и включите инструмент.\n\n" +
                        "Перфоратор необходим для прокладки кабелей и установки оборудования.");
                toolImageView.setImageResource(R.drawable.hammerdrill);
                break;

            default:
                toolDescriptionTextView.setText("Описание недоступно.");
                toolImageView.setImageResource(R.drawable.ic_back_arrow);
                break;
        }

        // Обработчик для кнопки "Вернуться в меню"
        btnBackToMenu.setOnClickListener(v -> {
            // Завершаем текущую активность и возвращаемся на предыдущий экран
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Завершаем текущую активность, чтобы вернуться в список инструментов
        finish();
    }
}

