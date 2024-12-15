package com.example.telecommanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "telecom_manager.db";
    private static final int DATABASE_VERSION = 3;

    // Таблица для сетевых элементов
    public static final String TABLE_NETWORK_ELEMENTS = "network_elements";
    public static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_STATUS = "status";

    // Таблица для отчетов о сбоях
    public static final String TABLE_FAULT_REPORTS = "fault_reports";
    public static final String COLUMN_FAULT_ID = "id";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_FAULT_STATUS = "status";
    private static final String COLUMN_TIMESTAMP = "timestamp";

    // SQL-запросы для создания таблиц
    private static final String TABLE_NETWORK_ELEMENTS_CREATE =
            "CREATE TABLE " + TABLE_NETWORK_ELEMENTS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_TYPE + " TEXT, " +
                    COLUMN_STATUS + " TEXT);";

    private static final String TABLE_FAULT_REPORTS_CREATE =
            "CREATE TABLE " + TABLE_FAULT_REPORTS + " (" +
                    COLUMN_FAULT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    COLUMN_FAULT_STATUS + " TEXT, " +
                    COLUMN_TIMESTAMP + " INTEGER);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_NETWORK_ELEMENTS_CREATE);
        db.execSQL(TABLE_FAULT_REPORTS_CREATE);
        Log.d("DatabaseHelper", "Tables created.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NETWORK_ELEMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAULT_REPORTS);
        onCreate(db);
        Log.d("DatabaseHelper", "Database upgraded.");
    }

    // Метод для добавления сетевого элемента
    public void addNetworkElement(NetworkElement networkElement) {
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();  // Открываем базу данных
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, networkElement.getName());
            values.put(COLUMN_TYPE, networkElement.getType());
            values.put(COLUMN_STATUS, networkElement.getStatus());

            long result = db.insert(TABLE_NETWORK_ELEMENTS, null, values);
            if (result == -1) {
                Log.e("DatabaseHelper", "Ошибка при добавлении сетевого элемента");
            } else {
                Log.d("DatabaseHelper", "Сетевой элемент успешно добавлен, ID: " + result);
            }
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Ошибка при работе с базой данных", e);
        } finally {
            // Закрытие базы данных будет выполнено в другом месте, например, в onDestroy
        }
    }

    // Метод для удаления сетевого элемента
    public void deleteNetworkElement(int elementId) {
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();  // Открываем базу данных
            int rowsDeleted = db.delete(TABLE_NETWORK_ELEMENTS, COLUMN_ID + " = ?", new String[]{String.valueOf(elementId)});
            if (rowsDeleted > 0) {
                Log.d("DatabaseHelper", "Deleted network element with ID: " + elementId);
            } else {
                Log.d("DatabaseHelper", "No network element found with ID: " + elementId);
            }
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Ошибка при удалении сетевого элемента", e);
        } finally {
            // Закрытие базы данных будет выполнено в другом месте, например, в onDestroy
        }
    }

    // Получение всех сетевых элементов
    public List<NetworkElement> getAllNetworkElements() {
        List<NetworkElement> elements = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = this.getReadableDatabase();  // Открываем базу данных
            cursor = db.query(TABLE_NETWORK_ELEMENTS, null, null, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    NetworkElement element = new NetworkElement(
                            cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_STATUS))
                    );
                    element.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                    elements.add(element);
                } while (cursor.moveToNext());
            }
            Log.d("DatabaseHelper", "Fetched " + elements.size() + " network elements.");
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Ошибка при получении сетевых элементов", e);
        } finally {
            if (cursor != null) {
                cursor.close();  // Закрываем курсор
            }
            // Закрытие базы данных будет выполнено в другом месте, например, в onDestroy
        }
        return elements;
    }

    // Метод для добавления отчета о сбое
    public void addFaultReport(FaultReport faultReport) {
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();  // Открываем базу данных
            ContentValues values = new ContentValues();
            values.put(COLUMN_DESCRIPTION, faultReport.getDescription());
            values.put(COLUMN_FAULT_STATUS, faultReport.getStatus());
            values.put(COLUMN_TIMESTAMP, faultReport.getTimestamp());

            // Вставляем данные в таблицу отчетов о сбоях
            db.insert(TABLE_FAULT_REPORTS, null, values);
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Ошибка при добавлении отчета о сбое", e);
        } finally {
            // Закрытие базы данных будет выполнено в другом месте, например, в onDestroy
        }
    }

    // Метод для удаления отчета о сбое
    public void deleteFaultReport(int faultReportId) {
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();  // Открываем базу данных
            db.delete(TABLE_FAULT_REPORTS, COLUMN_FAULT_ID + " = ?", new String[]{String.valueOf(faultReportId)});
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Ошибка при удалении отчета о сбое", e);
        } finally {
            // Закрытие базы данных будет выполнено в другом месте, например, в onDestroy
        }
    }

    // Получение всех отчетов о сбоях
    public List<FaultReport> getAllFaultReports() {
        List<FaultReport> reports = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = this.getReadableDatabase();  // Открываем базу данных
            cursor = db.query(TABLE_FAULT_REPORTS, null, null, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                do {
                    FaultReport report = new FaultReport(
                            cursor.getInt(cursor.getColumnIndex(COLUMN_FAULT_ID)), // ID
                            cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_FAULT_STATUS)),
                            cursor.getLong(cursor.getColumnIndex(COLUMN_TIMESTAMP))
                    );
                    reports.add(report);
                } while (cursor.moveToNext());
            }
            Log.d("DatabaseHelper", "Fetched " + reports.size() + " fault reports.");
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Ошибка при получении отчетов о сбоях", e);
        } finally {
            if (cursor != null) {
                cursor.close();  // Закрываем курсор
            }
            // Закрытие базы данных будет выполнено в другом месте, например, в onDestroy
        }
        return reports;
    }
}
