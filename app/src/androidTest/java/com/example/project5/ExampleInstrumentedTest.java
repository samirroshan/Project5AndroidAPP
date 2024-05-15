package com.example.project5;
/**
 * The {@code ExampleInstrumentedTest} class is an instrumented test designed to run on an Android device.
 * It verifies the correctness of the app's package name by comparing it with the expected package name.
 * <p>
 * To execute this test, use the AndroidJUnit4 test runner.
 * </p>
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 * @see android.content.Context
 * @see androidx.test.platform.app.InstrumentationRegistry
 * @see androidx.test.ext.junit.runners.AndroidJUnit4
 * @see org.junit.Test
 * @see org.junit.runner.RunWith
 * @see static org.junit.Assert
 */
@RunWith(AndroidJUnit4.class)

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    /**
     * Test method to ensure that the app under test has the correct package name.
     * <p>
     * It retrieves the target context using {@link InstrumentationRegistry} and asserts that the package name
     * matches the expected value.
     * </p>
     *
     * @see InstrumentationRegistry
     * @see Context
     * @see org.junit.Test
     */
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.project5", appContext.getPackageName());
    }
}
