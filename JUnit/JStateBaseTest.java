import static org.junit.Assert.fail;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;

@Ignore
public class JStateBaseTest {
    protected static Class<?> classDraft;
    protected static Class<?> classOpenDraft;
    protected static Class<?> classApprovedIdea;
    protected static Class<?> classReleasedIdea;
    protected static Class<?> classDeclinedIdea;

    protected JIdea i;

    @BeforeClass
    public static void setUpStateClassVariables() {
        searchForInnerClass("JState");
        classDraft = searchForInnerClass("Draft");
        classOpenDraft = searchForInnerClass("OpenDraft");
        classApprovedIdea = searchForInnerClass("ApprovedIdea");
        classReleasedIdea = searchForInnerClass("ReleasedIdea");
        classDeclinedIdea = searchForInnerClass("DeclinedIdea");
    }

    @Before
    public void setUp() {
        i = new JIdea("title", "description");
    }

    /* Return the Content of the Attribute "JIdea.state" */
    protected Object getState() {
        /* Get attribute state, set the mode to accessible and return the content */
        try {
            Field myField = JIdea.class.getDeclaredField("state");
            myField.setAccessible(true);
            return myField.get(i);
        } catch (NoSuchFieldException e) {
            fail("JIdea should have an attribute named state!");
        } catch (IllegalArgumentException e) {
            fail("An unexpected error occurred!");
        } catch (IllegalAccessException e) {
            fail("An unexpected error occurred!");
        }
        throw new AssertionError("An unexpected error occurred!");
    }

    private static Class<?> searchForInnerClass(String name) {
        for (Class<?> clazz : JIdea.class.getDeclaredClasses()) {
            if (clazz.getSimpleName().equals(name)) {
                if ("JState".equals(clazz.getSimpleName()) || "JState".equals(clazz.getSuperclass().getSimpleName())) {
                    return clazz;
                } else {
                    throw new AssertionError(name + " should be a sub-class of JState!");
                }
            }
        }
        throw new AssertionError("JIdea should have an inner class named " + name + "!");
    }
}
