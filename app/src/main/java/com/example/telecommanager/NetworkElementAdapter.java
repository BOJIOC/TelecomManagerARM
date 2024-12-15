package com.example.telecommanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NetworkElementAdapter extends RecyclerView.Adapter<NetworkElementAdapter.NetworkElementViewHolder> {

    private List<NetworkElement> networkElements;
    private DatabaseHelper databaseHelper;
    private UpdateStatisticsListener statisticsListener;

    public NetworkElementAdapter(List<NetworkElement> networkElements, DatabaseHelper databaseHelper, UpdateStatisticsListener statisticsListener) {
        this.networkElements = networkElements;
        this.databaseHelper = databaseHelper;
        this.statisticsListener = statisticsListener;
    }

    @Override
    public NetworkElementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_network_element, parent, false);
        return new NetworkElementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NetworkElementViewHolder holder, int position) {
        NetworkElement networkElement = networkElements.get(position);
        holder.nameTextView.setText(networkElement.getName());
        holder.typeTextView.setText(networkElement.getType());
        holder.statusTextView.setText(networkElement.getStatus());

        // Устанавливаем обработчик для кнопки "Удалить"
        holder.deleteButton.setOnClickListener(v -> {
            // Удаляем элемент из базы данных
            databaseHelper.deleteNetworkElement(networkElement.getId());

            // Удаляем элемент из списка
            networkElements.remove(position);
            notifyItemRemoved(position);

            // Пересчитываем количество отчетов о сбоях
            int updatedFaultReportsCount = databaseHelper.getAllFaultReports().size();

            // Пересчитываем количество сетевых элементов
            int updatedNetworkElementsCount = networkElements.size();

            // Обновляем статистику
            statisticsListener.onStatisticsUpdated(updatedFaultReportsCount, updatedNetworkElementsCount);
        });
    }

    public void updateList(List<NetworkElement> newNetworkElements) {
        this.networkElements = newNetworkElements;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return networkElements.size();
    }

    public static class NetworkElementViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, typeTextView, statusTextView;
        Button deleteButton;

        public NetworkElementViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            typeTextView = itemView.findViewById(R.id.typeTextView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
            deleteButton = itemView.findViewById(R.id.buttonDeleteNetworkElement);
        }
    }
}
