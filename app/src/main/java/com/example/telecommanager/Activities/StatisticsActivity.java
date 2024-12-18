package com.example.telecommanager.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.telecommanager.Adapters.FaultReportAdapter;
import com.example.telecommanager.Adapters.NetworkElementAdapter;
import com.example.telecommanager.Databases.DatabaseHelper;
import com.example.telecommanager.Databases.FaultReport;
import com.example.telecommanager.Databases.NetworkElement;
import com.example.telecommanager.R;
import com.example.telecommanager.UpdateStatisticsListener;

import java.util.List;

public class StatisticsActivity extends AppCompatActivity implements UpdateStatisticsListener {

    private Button btnGoToMainMenu;
    private RecyclerView recyclerViewNetworkElements;
    private RecyclerView recyclerViewFaultReports;
    private NetworkElementAdapter networkElementAdapter;
    private FaultReportAdapter faultReportAdapter;
    private TextView totalNetworkElementsTextView;
    private TextView totalFaultReportsTextView;
    private TextView noFaultReportsTextView;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        databaseHelper = new DatabaseHelper(this);

        recyclerViewNetworkElements = findViewById(R.id.recyclerViewNetworkElements);
        recyclerViewFaultReports = findViewById(R.id.recyclerViewFaultReports);
        totalNetworkElementsTextView = findViewById(R.id.totalNetworkElementsTextView);
        totalFaultReportsTextView = findViewById(R.id.totalFaultReportsTextView);
        noFaultReportsTextView = findViewById(R.id.noFaultReportsTextView);
        btnGoToMainMenu = findViewById(R.id.btnGoToMainMenu);

        recyclerViewNetworkElements.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFaultReports.setLayoutManager(new LinearLayoutManager(this));

        loadNetworkElements();
        loadFaultReports();

        btnGoToMainMenu.setOnClickListener(v -> finish());
    }

    @Override
    public void onStatisticsUpdated(int faultReportsCount, int networkElementsCount) {
        if (faultReportsCount >= 0) {
            totalFaultReportsTextView.setText(String.valueOf(faultReportsCount));
        }

        if (networkElementsCount >= 0) {
            totalNetworkElementsTextView.setText(String.valueOf(networkElementsCount));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNetworkElements();
        loadFaultReports();
    }

    private void loadNetworkElements() {
        List<NetworkElement> networkElements = databaseHelper.getAllNetworkElements();
        totalNetworkElementsTextView.setText("" + networkElements.size());

        if (!networkElements.isEmpty()) {
            if (networkElementAdapter == null) {
                networkElementAdapter = new NetworkElementAdapter(this, networkElements, databaseHelper, this);
                recyclerViewNetworkElements.setAdapter(networkElementAdapter);
            } else {
                networkElementAdapter.updateList(networkElements);
            }
        }
    }

    private void loadFaultReports() {
        List<FaultReport> faultReports = databaseHelper.getAllFaultReports();
        totalFaultReportsTextView.setText("" + faultReports.size());

        if (!faultReports.isEmpty()) {
            if (faultReportAdapter == null) {
                faultReportAdapter = new FaultReportAdapter(faultReports, databaseHelper, this);
                recyclerViewFaultReports.setAdapter(faultReportAdapter);
            } else {
                faultReportAdapter.updateData(faultReports);
            }
            noFaultReportsTextView.setVisibility(View.GONE);
        } else {
            noFaultReportsTextView.setVisibility(View.VISIBLE);
        }
    }
}
