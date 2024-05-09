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
public class ArtistsTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testNavigateToArtistsList() throws InterruptedException {
        // Esperar a que se cargue la lista de álbumes
        Thread.sleep(2000);

        // Hacer clic en el botón de navegación de artistas
        Espresso.onView(ViewMatchers.withId(R.id.artistFragment))
                .perform(ViewActions.click());

        // Verificar que el RecyclerView de artistas se muestra
        Espresso.onView(ViewMatchers.withId(R.id.artistsRv))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testViewArtistsList() throws InterruptedException {
        // Navegar a la lista de artistas
        testNavigateToArtistsList();
        // Esperar a que se cargue la lista de artistas
        Thread.sleep(2000);

        // Verificar que el RecyclerView de artistas se muestra
        Espresso.onView(ViewMatchers.withId(R.id.artistsRv))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Desplazarse al primer elemento y verificar su contenido
        Espresso.onView(ViewMatchers.withId(R.id.artistsRv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.scrollTo()));

        Espresso.onView(withIndex(ViewMatchers.withId(R.id.artistName), 0))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(withIndex(ViewMatchers.withId(R.id.headerImage), 0))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testNavigateToArtistDetail() throws InterruptedException {
        // Navegar a la lista de artistas
        testNavigateToArtistsList();
        // Esperar a que se cargue la lista de artistas
        Thread.sleep(2000);

        // Verificar que el RecyclerView de artistas se muestra
        Espresso.onView(ViewMatchers.withId(R.id.artistsRv))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Desplazarse al primer elemento y hacer clic
        Espresso.onView(ViewMatchers.withId(R.id.artistsRv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

        // Verificar que se muestra la pantalla de detalles del artista
        Espresso.onView(ViewMatchers.withId(R.id.artistDetailRv))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
