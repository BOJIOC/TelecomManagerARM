package com.example.telecommanager;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class NetworkElementCompatibilityTest {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    @Before
    public void setUp() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        dbHelper = new DatabaseHelper(appContext);
        database = dbHelper.getWritableDatabase();
    }

    @After
    public void tearDown() {
        if (database != null) {
            database.close();
        }
    }

    @Test
    public void testNetworkElementIntegration() {
        String name = "Router 123";
        String type = "Router";
        String status = "Active";

        long rowId = database.insert("network_elements", null, createContentValues(name, type, status));
        assertEquals("Ошибка вставки записи", rowId > 0, true);

        // 2. Чтение данных из таблицы
        Cursor cursor = database.query(
                "network_elements",
                null,
                "name = ?",
                new String[]{name},
                null,
                null,
                null
        );

        assertEquals("Элемент не найден в базе данных", cursor.getCount(), 1);

        cursor.moveToFirst();
        String retrievedName = cursor.getString(cursor.getColumnIndex("name"));
        String retrievedType = cursor.getString(cursor.getColumnIndex("type"));
        String retrievedStatus = cursor.getString(cursor.getColumnIndex("status"));

        assertEquals("Некорректное имя элемента", name, retrievedName);
        assertEquals("Некорректный тип элемента", type, retrievedType);
        assertEquals("Некорректный статус элемента", status, retrievedStatus);

        cursor.close();

        int rowsDeleted = database.delete("network_elements", "name = ?", new String[]{name});
        assertEquals("Ошибка удаления записи", rowsDeleted, 1);

        cursor = database.query(
                "network_elements",
                null,
                "name = ?",
                new String[]{name},
                null,
                null,
                null
        );

        assertEquals("Элемент все еще присутствует в базе данных после удаления", cursor.getCount(), 0);
        cursor.close();
    }

    private android.content.ContentValues createContentValues(String name, String type, String status) {
        android.content.ContentValues values = new android.content.ContentValues();
        values.put("name", name);
        values.put("type", type);
        values.put("status", status);
        return values;
    }
}
