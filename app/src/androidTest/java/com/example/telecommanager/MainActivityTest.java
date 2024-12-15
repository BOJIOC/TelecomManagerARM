package com.example.telecommanager;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import org.junit.Before;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityTest {

    @Before
    public void setup() {
        // Запуск MainActivity перед каждым тестом
        ActivityScenario.launch(MainActivity.class);
    }

    @Test
    public void testRegisterButton() {
        // Нажимаем на кнопку "btnRegister"
        onView(withId(R.id.btnRegister)).perform(ViewActions.click());

        // Проверяем, что после клика открылась активность RegisterElementActivity
        onView(withText("Register Element Activity"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testViewStatsButton() {
        // Нажимаем на кнопку "btnViewStats"
        onView(withId(R.id.btnViewStats)).perform(ViewActions.click());

        // Проверяем, что после клика открылась активность StatisticsActivity
        onView(withText("Statistics Activity"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testViewFaultsButton() {
        // Нажимаем на кнопку "btnViewFaults"
        onView(withId(R.id.btnViewFaults)).perform(ViewActions.click());

        // Проверяем, что после клика открылась активность FaultReportActivity
        onView(withText("Fault Report Activity"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testNetworkDiagnosticButton() {
        // Нажимаем на кнопку "btnNetworkDiagnostic"
        onView(withId(R.id.btnNetworkDiagnostic)).perform(ViewActions.click());

        // Проверяем, что после клика открылась активность NetworkSpeedMeasurementActivity
        onView(withText("Network Speed Measurement Activity"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testReferenceButton() {
        // Нажимаем на кнопку "btnReference"
        onView(withId(R.id.btnReference)).perform(ViewActions.click());

        // Проверяем, что после клика открылась активность ReferenceActivity
        onView(withText("Reference Activity"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
