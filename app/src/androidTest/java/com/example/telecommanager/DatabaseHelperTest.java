package com.example.telecommanager;

import androidx.test.core.app.ApplicationProvider;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.example.telecommanager.Databases.DatabaseHelper;

public class DatabaseHelperTest {

    private DatabaseHelper databaseHelper;

    @Before
    public void setUp() {
        Context context = ApplicationProvider.getApplicationContext();
        databaseHelper = new DatabaseHelper(context);
    }

    @Test
    public void testDatabaseCreation() {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        assertNotNull(db);
    }
}
