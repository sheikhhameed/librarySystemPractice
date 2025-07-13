package serviceImpl;

import ch.qos.logback.core.testUtil.MockInitialContext;
import lombok.experimental.ExtensionMethod;
import org.junit.platform.commons.annotation.Testable;

@ExtensionMethod(MockInitialContext.class)
public class TestServiceImpl {

    @Testable
    public void testborrowBook(){


    }
}
