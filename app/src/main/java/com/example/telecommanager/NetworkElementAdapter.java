package com.example.telecommanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class NetworkElementAdapter extends RecyclerView.Adapter<NetworkElementAdapter.NetworkElementViewHolder> {

    private List<NetworkElement> networkElements;
    private DatabaseHelper databaseHelper;
    private UpdateStatisticsListener statisticsListener;
    private Context context;

    public NetworkElementAdapter(Context context, List<NetworkElement> networkElements, DatabaseHelper databaseHelper, UpdateStatisticsListener statisticsListener) {
        this.context = context;
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

        holder.deleteButton.setOnClickListener(v -> {
            new AlertDialog.Builder(v.getContext())
                    .setTitle("Удалить сетевой элемент?")
                    .setMessage("Вы уверены, что хотите удалить " + networkElement.getName() + "?")
                    .setPositiveButton("Да", (dialog, which) -> {
                        boolean isDeleted = databaseHelper.deleteNetworkElement(networkElement.getId());
                        if (isDeleted) {
                            networkElements.remove(position);
                            notifyItemRemoved(position);
                            updateStatistics();
                            Toast.makeText(v.getContext(), "Сетевой элемент успешно удален", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(v.getContext(), "Ошибка при удалении элемента", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Отмена", null)
                    .show();
        });

        holder.editButton.setOnClickListener(v -> openEditDialog(networkElement, position));
    }

    @Override
    public int getItemCount() {
        return networkElements.size();
    }

    public void updateList(List<NetworkElement> updatedList) {
        this.networkElements = updatedList;
        notifyDataSetChanged();
    }

    private void openEditDialog(NetworkElement networkElement, int position) {
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_network_element, null);
        EditText nameEditText = dialogView.findViewById(R.id.editTextName);
        Spinner typeSpinner = dialogView.findViewById(R.id.spinnerType);
        Spinner statusSpinner = dialogView.findViewById(R.id.spinnerStatus);
        nameEditText.setText(networkElement.getName());
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, getTypeOptions());
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);

        ArrayAdapter<String> statusAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, getStatusOptions());
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(statusAdapter);

        int typePosition = typeAdapter.getPosition(networkElement.getType());
        int statusPosition = statusAdapter.getPosition(networkElement.getStatus());
        typeSpinner.setSelection(typePosition);
        statusSpinner.setSelection(statusPosition);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setTitle("Редактировать сетевой элемент");

        dialogBuilder.setPositiveButton("Сохранить", (dialog, which) -> {
            String updatedName = nameEditText.getText().toString().trim();
            String updatedType = typeSpinner.getSelectedItem().toString();
            String updatedStatus = statusSpinner.getSelectedItem().toString();

            if (updatedName.isEmpty()) {
                Toast.makeText(context, "Имя элемента не может быть пустым", Toast.LENGTH_SHORT).show();
                return;
            }

            networkElement.setName(updatedName);
            networkElement.setType(updatedType);
            networkElement.setStatus(updatedStatus);

            databaseHelper.updateNetworkElement(networkElement);

            notifyItemChanged(position);
            updateStatistics();
            Toast.makeText(context, "Данные обновлены", Toast.LENGTH_SHORT).show();
        });

        dialogBuilder.setNegativeButton("Отмена", null);

        AlertDialog dialog = dialogBuilder.create();

        nameEditText.addTextChangedListener(new android.text.TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int after) {
                // Если имя не пустое, активируем кнопку "Сохранить"
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(charSequence.length() > 0);
            }

            @Override
            public void afterTextChanged(android.text.Editable editable) {}
        });

        dialog.show();
    }

    private void updateStatistics() {
        int updatedFaultReportsCount = databaseHelper.getAllFaultReports().size();
        int updatedNetworkElementsCount = networkElements.size();
        statisticsListener.onStatisticsUpdated(updatedFaultReportsCount, updatedNetworkElementsCount);
    }

    private List<String> getTypeOptions() {
        return List.of("Маршрутизатор", "Коммутатор", "Сервер", "Клиентское устройство", "Шлюз", "Система хранения");
    }

    private List<String> getStatusOptions() {
        return List.of("Активный", "Неактивный", "На обслуживании", "Неисправен");
    }

    public static class NetworkElementViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, typeTextView, statusTextView;
        Button deleteButton, editButton;

        public NetworkElementViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            typeTextView = itemView.findViewById(R.id.typeTextView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
            deleteButton = itemView.findViewById(R.id.buttonDeleteNetworkElement);
            editButton = itemView.findViewById(R.id.buttonEditNetworkElement);
        }
    }
}
