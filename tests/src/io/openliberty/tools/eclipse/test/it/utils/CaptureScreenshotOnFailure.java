package io.openliberty.tools.eclipse.test.it.utils;

import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

public class CaptureScreenshotOnFailure implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        String displayName = context.getDisplayName();
        new SWTWorkbenchBot().captureScreenshot("./" + displayName + ".png");
    }
}
