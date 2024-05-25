package com.example.vinilosapp;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.vinilosapp.view.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AlbumCreateTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testErrorOnCreateAlbum() {
        // Hacer clic en el botón "Agregar álbum"
        Espresso.onView(ViewMatchers.withId(R.id.create_album_button))
                .perform(ViewActions.click());

        // Verificar que se muestra el formulario de creación de álbum
        Espresso.onView(ViewMatchers.withId(R.id.main))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Ingresar los datos del álbum utilizando replaceText()
        Espresso.onView(ViewMatchers.withId(R.id.txt_post_name))
                .perform(ViewActions.replaceText("Nuevo Álbum"));
        Espresso.onView(ViewMatchers.withId(R.id.txt_post_cover_url))
                .perform(ViewActions.replaceText("https://example.com/cover.jpg"));
        Espresso.onView(ViewMatchers.withId(R.id.txt_post_date))
                .perform(ViewActions.replaceText("2023-06-08"));
        Espresso.onView(ViewMatchers.withId(R.id.txt_post_description))
                .perform(ViewActions.replaceText("Descripción del álbum"));
        Espresso.onView(ViewMatchers.withId(R.id.txt_post_genre))
                .perform(ViewActions.replaceText("Rock"));
        Espresso.onView(ViewMatchers.withId(R.id.txt_post_album_create_record_label))
                .perform(ViewActions.replaceText("Sello discográfico"));
        // Hacer clic en el botón "Crear álbum"
        Espresso.onView(ViewMatchers.withId(R.id.create_button))
                .perform(ViewActions.click());

        // Verificar que se muestra el diálogo de error con el mensaje esperado
        Espresso.onView(ViewMatchers.withText("Discografía no permitida, deben ser: [Sony Music, EMI, Discos Fuentes, Elektra, Fania Records]\n(Selecciona por fuera del diálogo para volver a crear álbum)"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testCreateAlbum() {
        // Hacer clic en el botón "Agregar álbum"
        Espresso.onView(ViewMatchers.withId(R.id.create_album_button))
                .perform(ViewActions.click());

        // Verificar que se muestra el formulario de creación de álbum
        Espresso.onView(ViewMatchers.withId(R.id.main))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Ingresar los datos del álbum utilizando replaceText()
        Espresso.onView(ViewMatchers.withId(R.id.txt_post_name))
                .perform(ViewActions.replaceText("Test Nuevo Álbum"));
        Espresso.onView(ViewMatchers.withId(R.id.txt_post_cover_url))
                .perform(ViewActions.replaceText("https://upload.wikimedia.org/wikipedia/commons/thumb/9/9c/El_lado_oscuro_de_la_luna_-_The_dark_side_of_the_moon_-_Pink_Floyd_-_Vinyl.jpg/800px-El_lado_oscuro_de_la_luna_-_The_dark_side_of_the_moon_-_Pink_Floyd_-_Vinyl.jpg"));
        Espresso.onView(ViewMatchers.withId(R.id.txt_post_date))
                .perform(ViewActions.replaceText("2023-06-08"));
        Espresso.onView(ViewMatchers.withId(R.id.txt_post_description))
                .perform(ViewActions.replaceText("Descripción del álbum"));
        Espresso.onView(ViewMatchers.withId(R.id.txt_post_genre))
                .perform(ViewActions.replaceText("Rock"));
        Espresso.onView(ViewMatchers.withId(R.id.txt_post_album_create_record_label))
                .perform(ViewActions.replaceText("Sony Music"));

        // Hacer clic en el botón "Crear álbum"
        Espresso.onView(ViewMatchers.withId(R.id.create_button))
                .perform(ViewActions.click());
        // Verificar que se muestra el diálogo de error con el mensaje esperado
        Espresso.onView(ViewMatchers.withText("Éxito"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
