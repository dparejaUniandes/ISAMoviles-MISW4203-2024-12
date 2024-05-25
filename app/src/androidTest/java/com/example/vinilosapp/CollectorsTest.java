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

@RunWith(AndroidJUnit4.class)
public class CollectorsTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testNavigateToCollectorsList() throws InterruptedException {
        // Esperar a que se cargue la lista de álbumes
        Thread.sleep(2000);

        // Hacer clic en el botón de navegación de coleccionistas
        Espresso.onView(ViewMatchers.withId(R.id.collectorsFragment))
                .perform(ViewActions.click());

        Thread.sleep(2000);
    }

    @Test
    public void testViewCollectorsList() throws InterruptedException {
        // Navegar a la lista de coleccionistas
        testNavigateToCollectorsList();

        // Verificar que el RecyclerView de coleccionistas se muestra
        Espresso.onView(ViewMatchers.withId(R.id.collectorsRv))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Desplazarse al primer elemento y verificar su contenido
        Espresso.onView(ViewMatchers.withId(R.id.collectorsRv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.scrollTo()));

        Espresso.onView(withIndex(ViewMatchers.withId(R.id.tv_collector_name), 0))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(withIndex(ViewMatchers.withId(R.id.headerImage), 0))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testNavigateToCollectorDetail() throws InterruptedException {
        // Navegar a la lista de coleccionistas
        testNavigateToCollectorsList();

        // Desplazarse al primer elemento y hacer clic
        Espresso.onView(ViewMatchers.withId(R.id.collectorsRv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

        // Verificar que se muestra la pantalla de detalles del coleccionista
        Espresso.onView(ViewMatchers.withId(R.id.collectorDetailRv))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testCollectorDetailInformation() throws InterruptedException {
        // Navegar a la lista de coleccionistas
        testNavigateToCollectorsList();

        // Desplazarse al primer elemento y hacer clic
        Espresso.onView(ViewMatchers.withId(R.id.collectorsRv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

        // Verificar que se muestra el correo electrónico del coleccionista
        Espresso.onView(ViewMatchers.withId(R.id.labelDynamicEmail))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Verificar que se muestra el teléfono del coleccionista
        Espresso.onView(ViewMatchers.withId(R.id.labelDynamicPhone))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
