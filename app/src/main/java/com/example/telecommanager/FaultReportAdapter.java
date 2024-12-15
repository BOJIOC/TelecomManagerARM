package com.example.telecommanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FaultReportAdapter extends RecyclerView.Adapter<FaultReportAdapter.FaultReportViewHolder> {

    private List<FaultReport> faultReports;
    private DatabaseHelper databaseHelper;
    private UpdateStatisticsListener statisticsListener;

    public FaultReportAdapter(List<FaultReport> faultReports, DatabaseHelper databaseHelper, UpdateStatisticsListener statisticsListener) {
        this.faultReports = faultReports;
        this.databaseHelper = databaseHelper;
        this.statisticsListener = statisticsListener;
    }

    @Override
    public FaultReportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fault_report, parent, false);
        return new FaultReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FaultReportViewHolder holder, int position) {
        FaultReport faultReport = faultReports.get(position);

        // Установка данных в элемент списка
        holder.descriptionTextView.setText(faultReport.getDescription());
        holder.statusTextView.setText(faultReport.getStatus());

        // Обработчик кнопки "Удалить"
        holder.deleteButton.setOnClickListener(v -> {
            // Удаляем отчет о сбое из базы данных
            databaseHelper.deleteFaultReport(faultReport.getId());

            // Удаляем отчет из списка
            faultReports.remove(position);
            notifyItemRemoved(position);

            // Обновляем статистику с учетом изменений
            int updatedFaultReportsCount = faultReports.size();
            int updatedNetworkElementsCount = databaseHelper.getAllNetworkElements().size(); // Пересчитываем элементы

            // Уведомляем слушателя о новом состоянии статистики
            statisticsListener.onStatisticsUpdated(updatedFaultReportsCount, updatedNetworkElementsCount);
        });
    }

    public void updateData(List<FaultReport> newFaultReports) {
        this.faultReports = newFaultReports;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return faultReports.size();
    }

    public static class FaultReportViewHolder extends RecyclerView.ViewHolder {
        TextView descriptionTextView, statusTextView;
        Button deleteButton;

        public FaultReportViewHolder(View itemView) {
            super(itemView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
            deleteButton = itemView.findViewById(R.id.buttonDeleteFault);
        }
    }
}
