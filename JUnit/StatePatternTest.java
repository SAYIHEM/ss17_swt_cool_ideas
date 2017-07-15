import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StatePatternTest extends JStateBaseTest {
    private String methodDiscuss = "discuss";
    private String methodEvaluate = "evaluate";
    private String methodHold = "hold";
    private String methodRelease = "release";
    private String methodDecline = "decline";

    @Test
    public void testStateClassesExist() {
    }

    @Test
    public void testJIdeaClasses() {
        assertTrue("JState should have a method named " + methodDiscuss + "!",
                searchForMethod("JState", methodDiscuss, String.class));
        assertTrue("JState should have a method named " + methodEvaluate + "!",
                searchForMethod("JState", methodEvaluate, JValuation.class));
        assertTrue("JState should have a method named " + methodHold + "!", searchForMethod("JState", methodHold));
        assertTrue("JState should have a method named " + methodRelease + "!",
                searchForMethod("JState", methodRelease));
        assertTrue("JState should have a method named " + methodDecline + "!",
                searchForMethod("JState", methodDecline));
    }

    private boolean searchForMethod(String name, String method, Class<?>... args) {
        for (Class<?> c : JIdea.class.getDeclaredClasses()) {
            if (c.getSimpleName().equals(name)) {
                try {
                    c.getMethod(method, args);
                    return true;
                } catch (NoSuchMethodException ex) {
                    return false;
                }
            }
        }
        return false;
    }
}
