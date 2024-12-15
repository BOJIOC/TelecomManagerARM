package com.example.telecommanager;

import androidx.test.core.app.ApplicationProvider;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class NetworkElementTest {

    private DatabaseHelper databaseHelper;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        databaseHelper = new DatabaseHelper(context);
    }

    @Test
    public void testAddNetworkElement() {
        NetworkElement networkElement = new NetworkElement("Router", "Active", "Operational");
        databaseHelper.addNetworkElement(networkElement);

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_NETWORK_ELEMENTS, null, null, null, null, null, null);
        assertTrue(cursor != null && cursor.getCount() > 0);
        cursor.close();
    }

    @Test
    public void testDeleteNetworkElement() {
        NetworkElement networkElement = new NetworkElement("Router", "Active", "Operational");
        databaseHelper.addNetworkElement(networkElement);

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_NETWORK_ELEMENTS, null, null, null, null, null, null);
        cursor.moveToLast();
        int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
        cursor.close();

        databaseHelper.deleteNetworkElement(id);

        cursor = db.query(DatabaseHelper.TABLE_NETWORK_ELEMENTS, null, DatabaseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);
        assertFalse(cursor != null && cursor.getCount() > 0);
        cursor.close();
    }
}
