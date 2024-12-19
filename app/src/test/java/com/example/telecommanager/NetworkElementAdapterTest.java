package com.example.telecommanager;

import android.content.Context;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class NetworkElementAdapterTest {

    private NetworkElementAdapter adapter;
    private List<NetworkElement> networkElements;
    private DatabaseHelper databaseHelper;
    private UpdateStatisticsListener statisticsListener;
    private Context context;

    @Before
    public void setUp() {
        context = mock(Context.class);

        databaseHelper = new DatabaseHelper(context);
        statisticsListener = new UpdateStatisticsListener() {
            @Override
            public void onStatisticsUpdated(int faultReportsCount, int networkElementsCount) {
            }
        };

        networkElements = new ArrayList<>();
        networkElements.add(new NetworkElement("Router", "active", "router_type"));
        networkElements.add(new NetworkElement("Switch", "inactive", "switch_type"));

        adapter = new NetworkElementAdapter(context, networkElements, databaseHelper, statisticsListener);
    }

    @Test
    public void testAdapterInitialization() {
        assertNotNull("Adapter should not be null", adapter);
        assertEquals("Adapter should have the correct number of elements", 2, adapter.getItemCount());
    }
}
