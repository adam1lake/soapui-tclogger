package io.github.adam1lake.listeners;

import com.eviware.soapui.model.support.TestRunListenerAdapter;
import com.eviware.soapui.model.testsuite.TestCaseRunContext;
import com.eviware.soapui.model.testsuite.TestCaseRunner;
import com.eviware.soapui.model.testsuite.TestStep;
import com.eviware.soapui.model.testsuite.TestStepResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingTestRunListener extends TestRunListenerAdapter {

    private static final Logger log = LogManager.getLogger(LoggingTestRunListener.class);

    @Override
    public void beforeRun(TestCaseRunner testCaseRunner, TestCaseRunContext context) {
        log.info("Starting Test Case: " + testCaseRunner.getTestCase().getName());
    }

    @Override
    public void beforeStep(TestCaseRunner testCaseRunner, TestCaseRunContext context, TestStep testStep) {
        log.info("Starting Test Step: " + testStep.getName());
    }

    @Override
    public void afterStep(TestCaseRunner testCaseRunner, TestCaseRunContext context, TestStepResult result) {
        log.info("Finished Test Step: " + result.getTestStep().getName());
    }

    @Override
    public void afterRun(TestCaseRunner testCaseRunner, TestCaseRunContext context) {
        log.info("Finished Test Case: " + testCaseRunner.getTestCase().getName());
    }
}