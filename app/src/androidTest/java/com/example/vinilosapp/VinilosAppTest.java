package com.example.vinilosapp;
import android.app.Instrumentation;
import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.vinilosapp.view.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class VinilosAppTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void setup() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        Espresso.setFailureHandler(new CustomFailureHandler(instrumentation));
    }

    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }

    @Test
    public void testViewAlbumsList() throws InterruptedException {
        Thread.sleep(2000);
        // Verificar que el RecyclerView de álbumes se muestra
        Espresso.onView(ViewMatchers.withId(R.id.albumsRv))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Desplazarse al primer elemento y verificar su contenido
        Espresso.onView(ViewMatchers.withId(R.id.albumsRv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.scrollTo()));

        Espresso.onView(withIndex(ViewMatchers.withId(R.id.albumName), 0))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(withIndex(ViewMatchers.withId(R.id.headerImage), 0))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Simular clic para ver detalle del álbum
        Espresso.onView(ViewMatchers.withId(R.id.albumsRv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

        // Verificar que estamos en la pantalla de detalle
        Espresso.onView(ViewMatchers.withId(R.id.albumDescription))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testViewAlbumDetail() throws InterruptedException {
        Thread.sleep(2000);

        // Iniciar la prueba asegurando que estamos en la lista de álbumes
        Espresso.onView(ViewMatchers.withId(R.id.albumsRv))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Desplazarse al primer álbum y hacer clic
        Espresso.onView(ViewMatchers.withId(R.id.albumsRv))
                .perform(RecyclerViewActions.scrollToPosition(0));
        Espresso.onView(ViewMatchers.withId(R.id.albumsRv))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

        // Verificar que la información detallada se muestra
        Espresso.onView(ViewMatchers.withId(R.id.albumDescription))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Verificar si el botón "back" está visible
        Espresso.onView(ViewMatchers.withContentDescription(R.string.abc_action_bar_up_description))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Simular un clic en el botón "back"
        Espresso.onView(ViewMatchers.withContentDescription(R.string.abc_action_bar_up_description))
                .perform(ViewActions.click());

        // Asegurarse de que regresamos a la lista de álbumes
        Espresso.onView(ViewMatchers.withId(R.id.albumsRv))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testArtists() throws InterruptedException {
        ArtistsTest artistsTest = new ArtistsTest();
        artistsTest.testViewArtistsList();
        artistsTest.testNavigateToArtistDetail();
    }

    @Test
    public void testCollectors() throws InterruptedException {
        CollectorsTest collectorsTest = new CollectorsTest();
        collectorsTest.testViewCollectorsList();
        collectorsTest.testNavigateToCollectorDetail();
        collectorsTest.testCollectorDetailInformation();
    }

    @Test
    public void testTracks() throws InterruptedException {
        TracksTest tracksTest = new TracksTest();
        tracksTest.testViewTracksList();
        tracksTest.testNavigateToTrackDetail();
        tracksTest.testTrackDetailInformation();
    }

    @Test
    public void testCreateAlbum() {
        AlbumCreateTest albumCreateTest = new AlbumCreateTest();
        albumCreateTest.testCreateAlbum();
    }
}
