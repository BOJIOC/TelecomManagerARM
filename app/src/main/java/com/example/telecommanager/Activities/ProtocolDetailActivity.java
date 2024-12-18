package com.example.telecommanager.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.telecommanager.R;

public class ProtocolDetailActivity extends AppCompatActivity {

    private TextView protocolNameTextView, protocolDescriptionTextView;
    private ImageView protocolImageView;
    private Button btnBackToMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protocol_detail);

        protocolNameTextView = findViewById(R.id.protocolNameTextView);
        protocolDescriptionTextView = findViewById(R.id.protocolDescriptionTextView);
        protocolImageView = findViewById(R.id.protocolImageView);
        btnBackToMenu = findViewById(R.id.btnBackToMenu);

        String protocolCategory = getIntent().getStringExtra("PROTOCOL_CATEGORY");

        protocolNameTextView.setText(protocolCategory);

        switch (protocolCategory) {
            case "Протоколы маршрутизации":
                protocolDescriptionTextView.setText("Протоколы маршрутизации играют ключевую роль в сетях, обеспечивая эффективный обмен маршрутной информацией между устройствами. Эти протоколы необходимы для правильного выбора наилучшего пути для передачи данных, что критично для стабильно работающих и масштабируемых сетей.\n\n"
                        + "EGP (Exterior Gateway Protocol) — протокол для обмена маршрутной информацией между автономными системами в интернете. EGP используется для поддержания связи между различными сетями в глобальной сети.\n\n"
                        + "EIGRP (Enhanced Interior Gateway Routing Protocol) — улучшенная версия IGRP. Этот протокол маршрутизации внутри автономной системы позволяет более эффективно обновлять маршруты и оптимизировать трафик.\n\n"
                        + "HSRP (Hot Standby Router Protocol) — протокол для обеспечения отказоустойчивости маршрутизаторов. Он позволяет автоматически переключать трафик на резервный маршрутизатор, если основной выходит из строя.\n\n"
                        + "IGRP (Interior Gateway Routing Protocol) — старый протокол маршрутизации, используемый для обмена маршрутной информацией внутри одной автономной системы.\n\n"
                        + "RIP (Routing Information Protocol) — один из старейших протоколов маршрутизации. RIP используется для обмена маршрутами на основе количества хопов (переходов) между устройствами в сети.\n\n"
                        + "OSPF (Open Shortest Path First) — протокол маршрутизации, который использует алгоритм кратчайшего пути для оптимизации маршрутов в больших и сложных сетях.\n\n"
                        + "BGP (Border Gateway Protocol) — используется для обмена маршрутной информацией между автономными системами в интернете. Это один из наиболее важных протоколов глобальной маршрутизации.\n\n"
                        + "NARP (Reverse ARP) — протокол, используемый для получения IP-адреса устройства, если известен его MAC-адрес.\n\n"
                        + "NHRP (Next Hop Resolution Protocol) — протокол для разрешения адреса следующего маршрута в сети, что помогает улучшить эффективность маршрутизации.\n\n"
                        + "TRIP (Transparent Interconnection of Packets) — протокол, используемый для оптимизации маршрутизации в виртуальных частных сетях (VPN).");
                protocolImageView.setImageResource(R.drawable.routing_icon);
                break;

            case "Протоколы телефонии":
                protocolDescriptionTextView.setText("Протоколы телефонии обеспечивают передачу голосовых и видеозвонков по сети. Эти протоколы контролируют процесс установления, ведения и завершения звонков, а также передачу аудио- и видеосигналов между участниками.\n\n"
                        + "SIP (Session Initiation Protocol) — протокол для установления, управления и завершения сеансов связи. SIP используется в IP-телефонии для соединения абонентов и управления звонками.\n\n"
                        + "MGCP (Media Gateway Control Protocol) — протокол для управления шлюзами в сетях телефонии, который позволяет взаимодействовать с различными типами сетей и устройствами.\n\n"
                        + "SCCP (Skinny Client Control Protocol) — протокол, используемый для связи IP-телефонов с сервером CallManager в системе Cisco, поддерживает звонки и управление ими.\n\n"
                        + "SIGTRAN (Signaling Transport) — протокол для передачи сигналов через сети. SIGTRAN применяется для соединений с традиционными телефонными сетями, например, для работы с системой SS7.\n\n"
                        + "IAX2 (Inter-Asterisk eXchange) — протокол, используемый в системе Asterisk для передачи голосовых и видеосигналов через IP-сети. IAX2 позволяет уменьшить количество передаваемых данных, обеспечивая при этом хорошее качество связи.\n\n"
                        + "Unistim — протокол для связи между IP-телефонами и серверами в телефонных системах, который также поддерживает передачу голосовых данных и управление звонками.\n\n"
                        + "Jingle — протокол, используемый в мессенджерах и системах связи для передачи аудио- и видеозвонков. Jingle был разработан на базе XMPP и применяется в таких приложениях, как Google Talk.\n\n"
                        + "Megaco/H.248 — протокол для управления шлюзами в телефонии. Megaco используется для взаимодействия между сетями и системами телефонии.\n\n"
                        + "H.323 — стандарт для передачи голосовых и видеозвонков через IP-сети. H.323 поддерживает интеграцию голосовой и видеосвязи с другими коммуникационными протоколами.");
                protocolImageView.setImageResource(R.drawable.telephony_icon);
                break;

            case "Протоколы видеонаблюдения":
                protocolDescriptionTextView.setText("Протоколы видеонаблюдения используются для передачи видео- и аудиосигналов с камер наблюдения и других устройств в системы мониторинга. Эти протоколы обеспечивают надежную передачу данных между видеокамерами, серверами и системами управления.\n\n"
                        + "IPv4 и IPv6 — два протокола, которые обеспечивают передачу данных через интернет и локальные сети. IPv6 является улучшенной версией IPv4, предлагающей больше адресов и улучшенную производительность.\n\n"
                        + "HTTP и HTTPS — протоколы, используемые для передачи данных через интернет. HTTPS является зашифрованной версией HTTP, обеспечивая безопасность передачи видеоинформации.\n\n"
                        + "FTP (File Transfer Protocol) — используется для передачи файлов между устройствами. В видеонаблюдении FTP часто применяется для загрузки записей с видеокамер на сервер.\n\n"
                        + "TCP и UDP — два основных протокола транспортного уровня, которые используются для передачи данных. TCP предоставляет гарантированную доставку данных, а UDP используется для потоковой передачи видео и аудио без гарантии доставки.\n\n"
                        + "DNS и DHCP — протоколы, которые обеспечивают разрешение имен и автоматическую настройку IP-адресов для устройств в сети видеонаблюдения.\n\n"
                        + "SMTP — протокол для отправки электронных писем. В видеонаблюдении используется для отправки уведомлений о событиях (например, детектирование движения) на электронную почту.\n\n"
                        + "RTP и RTSP — протоколы, используемые для передачи аудио и видео в реальном времени. RTP часто используется вместе с RTSP для обеспечения потоковой передачи медиа в системах видеонаблюдения.\n\n"
                        + "NTP — протокол для синхронизации времени. Важно для видеонаблюдения, чтобы все камеры имели одинаковое время для точного хронологического отображения событий.");
                protocolImageView.setImageResource(R.drawable.surveillance_icon);
                break;

            default:
                protocolDescriptionTextView.setText("Описание недоступно.");
                protocolImageView.setImageResource(R.drawable.ic_back_arrow);
                break;
        }

        btnBackToMenu.setOnClickListener(v -> finish());
    }
}
