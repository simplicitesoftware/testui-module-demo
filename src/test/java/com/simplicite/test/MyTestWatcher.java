package com.simplicite.test;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class MyTestWatcher implements TestWatcher {

    private static boolean failedtest = true;

    public static boolean isFailedtest() {
        return failedtest;
    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
        failedtest = false;
    }
}
