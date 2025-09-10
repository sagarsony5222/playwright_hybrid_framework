package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ReportUtil;
import utils.LoggerUtil;

public class TestListener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        LoggerUtil.getLogger(TestListener.class).info("Test suite started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        LoggerUtil.getLogger(TestListener.class).info("Test suite finished: " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        LoggerUtil.getLogger(result.getTestClass().getRealClass())
                .info("Test started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LoggerUtil.getLogger(result.getTestClass().getRealClass())
                .info("Test passed: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LoggerUtil.getLogger(result.getTestClass().getRealClass())
                .error("Test failed: " + result.getMethod().getMethodName(), result.getThrowable());
        ReportUtil.onTestFailure(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LoggerUtil.getLogger(result.getTestClass().getRealClass())
                .warn("Test skipped: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Not used
    }
}
