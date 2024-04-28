package com.example.vinilosapp;

import android.app.Instrumentation;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.test.espresso.FailureHandler;
import androidx.test.espresso.base.DefaultFailureHandler;

import org.hamcrest.Matcher;


public class CustomFailureHandler implements FailureHandler {

    private final FailureHandler delegate;

    public CustomFailureHandler(@NonNull Instrumentation instrumentation) {
        delegate = new DefaultFailureHandler(instrumentation.getTargetContext());
    }

    @Override
    public void handle(final Throwable error, final Matcher<View> viewMatcher) {
        Log.e("Espresso", "Error: " + error.getMessage(), error);

        // Then delegate the error handling to the default handler which will throw an exception
        delegate.handle(error, viewMatcher);
    }
}
