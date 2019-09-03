package nio;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.List;

public class BufferToolsTestRunner {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(BufferToolsTest.class);
        List<Failure> failures = result.getFailures();
        for (Failure failure : failures) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}