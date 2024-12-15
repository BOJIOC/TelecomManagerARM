package com.example.telecommanager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import androidx.test.core.app.ApplicationProvider;

public class FaultReportTest {

    private DatabaseHelper databaseHelper;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        databaseHelper = new DatabaseHelper(context);
    }

    @Test
    public void testAddFaultReport() {
        FaultReport faultReport = new FaultReport("Test Description", "Active", System.currentTimeMillis());
        databaseHelper.addFaultReport(faultReport);

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(DatabaseHelper.TABLE_FAULT_REPORTS, null, null, null, null, null, null);
            assertTrue(cursor != null && cursor.getCount() > 0);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    @Test
    public void testDeleteFaultReport() {
        FaultReport faultReport = new FaultReport("Test Description", "Active", System.currentTimeMillis());
        databaseHelper.addFaultReport(faultReport);

        SQLiteDatabase db = databaseHelper.getWritableDatabase(); // используем writableDatabase для записи и удаления
        Cursor cursor = null;
        int id = -1;
        try {
            // Запрос для получения последнего добавленного отчета
            cursor = db.query(DatabaseHelper.TABLE_FAULT_REPORTS, null, null, null, null, null, null);
            cursor.moveToLast();
            id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_FAULT_ID));
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        // Удаляем отчет
        databaseHelper.deleteFaultReport(id);

        // Повторный запрос после удаления
        cursor = null;
        try {
            cursor = db.query(DatabaseHelper.TABLE_FAULT_REPORTS, null, DatabaseHelper.COLUMN_FAULT_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
            assertFalse(cursor != null && cursor.getCount() > 0);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
