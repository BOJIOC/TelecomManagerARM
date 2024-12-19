package com.example.telecommanager;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FaultReportTest {

    private FaultReport faultReport;

    @Before
    public void setUp() {
        // Используем конструктор без параметров для создания экземпляра
        faultReport = new FaultReport();
    }

    @Test
    public void testFaultReportStatus() {
        // Проверка начального статуса по умолчанию
        assertEquals("NEW", faultReport.getStatus());
    }

    @Test
    public void testFaultReportSetStatus() {
        // Устанавливаем новый статус и проверяем его
        faultReport.setStatus("IN_PROGRESS");
        assertEquals("IN_PROGRESS", faultReport.getStatus());
    }

    @Test
    public void testFaultReportDescription() {
        // Устанавливаем описание и проверяем его
        faultReport.setDescription("Ошибка в сети");
        assertEquals("Ошибка в сети", faultReport.getDescription());
    }

    @Test
    public void testFaultReportTimestamp() {
        // Проверяем, что метка времени установлена
        long timestamp = System.currentTimeMillis();
        faultReport.setTimestamp(timestamp);
        assertEquals(timestamp, faultReport.getTimestamp());
    }

    @Test
    public void testFaultReportDate() {
        // Проверяем, что дата правильно конвертируется в строку
        String expectedDate = faultReport.getDate();
        assertNotNull(expectedDate);
    }
}
