package com.example.telecommanager;

public interface UpdateStatisticsListener {
    // Метод для обновления количества сбоев и элементов
    void onStatisticsUpdated(int faultReportsCount, int networkElementsCount);
}
