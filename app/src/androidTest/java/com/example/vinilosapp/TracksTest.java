package com.example.vinilosapp;

import static com.example.vinilosapp.VinilosAppTest.withIndex;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.vinilosapp.view.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

public class TracksTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testNavigateToTracksList() throws InterruptedException {
        // Esperar a que se cargue la lista de álbumes
        Thread.sleep(2000);

        // Hacer clic en el botón de navegación de canciones
        Espresso.onView(ViewMatchers.withId(R.id.trackFragment))
                .perform(ViewActions.click());

        Thread.sleep(2000);
    }

    @Test
    public void testViewTracksList() throws InterruptedException {
        // Navegar a la lista de canciones
        testNavigateToTracksList();

        // Verificar que el RecyclerView de canciones se muestra
        Espresso.onView(ViewMatchers.withId(R.id.tracksRv))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Desplazarse al primer elemento y verificar su contenido
        Espresso.onView(ViewMatchers.withId(R.id.tracksRv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.scrollTo()));

        Espresso.onView(withIndex(ViewMatchers.withId(R.id.trackName), 0))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(withIndex(ViewMatchers.withId(R.id.headerImage), 0))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
