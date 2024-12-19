package com.example.telecommanager;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DatabaseHelperPerformanceTest {

    private DatabaseHelper databaseHelper;

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        databaseHelper = new DatabaseHelper(context);
    }

    @Test
    public void testDatabaseInsertionPerformance() {
        long startTime = System.currentTimeMillis();

        databaseHelper.getWritableDatabase().beginTransaction();
        try {
            for (int i = 0; i < 1000; i++) {
                databaseHelper.addNetworkElement(new NetworkElement("Device " + i, "active", "type"));
            }
            databaseHelper.getWritableDatabase().setTransactionSuccessful();
        } finally {
            databaseHelper.getWritableDatabase().endTransaction();
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;


        assertTrue("Insert time should be less than 2 seconds", duration < 2000);
    }
}