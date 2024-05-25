package com.example.vinilosapp;
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
public class TrackAssociateTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testNavigateToTrackAssociate() throws InterruptedException {
        Thread.sleep(2000);

        // Iniciar la prueba asegurando que estamos en la lista de álbumes
        Espresso.onView(ViewMatchers.withId(R.id.albumsRv))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Desplazarse al primer álbum y hacer clic
        Espresso.onView(ViewMatchers.withId(R.id.albumsRv))
                .perform(RecyclerViewActions.scrollToPosition(0));
        Espresso.onView(ViewMatchers.withId(R.id.albumsRv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

        Thread.sleep(2000);
        // Hacer clic en el botón de asociar track
        Espresso.onView(ViewMatchers.withId(R.id.associate_track_album_button))
                .perform(ViewActions.click());
    }

    @Test
    public void testAssociateTrack() throws InterruptedException {
        // Navegar a la pantalla de asociar track
        testNavigateToTrackAssociate();

        // Ingresar los datos del track
        Espresso.onView(ViewMatchers.withId(R.id.txt_track_name))
                .perform(ViewActions.typeText("Nombre del track"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.txt_track_duration))
                .perform(ViewActions.typeText("3:45"), ViewActions.closeSoftKeyboard());

        // Hacer clic en el botón de asociar track
        Espresso.onView(ViewMatchers.withId(R.id.associate_track_button))
                .perform(ViewActions.click());

        // Verificar que se muestra el mensaje de éxito
        Espresso.onView(ViewMatchers.withText("Track asociado exitosamente"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
