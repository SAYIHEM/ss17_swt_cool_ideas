import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ObserverTest {
    private JMember m1, m2;
    private JTopic t1, t2;
    private PrintStream backOut, backErr;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        backOut = System.out;
        backErr = System.err;

        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));

        m1 = new JMember();
        m2 = new JMember();
        t1 = new JTopic("title1", "description1", 1);
        t2 = new JTopic("title2", "description2", 2);
    }

    @Test
    public void testStructure() {
        assertSame("JContent should be a subclass of java.util.Observable!", java.util.Observable.class,
                JContent.class.getSuperclass());
        assertTrue("JMember should implement java.util.Observer!",
                java.util.Observer.class.isAssignableFrom(JMember.class));
    }

    @Test
    public void testSubscribe() {
        m1.subscribe(t1);
        assertTrue("JMember.subscribe() should add the topic to the member's subscribed topics!", m1
                .getSubscribedTopics().contains(t1));
        assertEquals("JMember.subscribe() should add the JMember as an Observer of the topic!", 1, t1.countObservers());

        m1.subscribe(t2);
        assertTrue("JMember.subscribe() should add the topic to the member's subscribed topics!", m1
                .getSubscribedTopics().contains(t2));
        assertEquals("JMember.subscribe() should add the JMember as an Observer of the topic!", 1, t2.countObservers());

        m2.subscribe(t2);
        assertTrue("JMember.subscribe() should add the topic to the member's subscribed topics!", m2
                .getSubscribedTopics().contains(t2));
        assertEquals("JMember.subscribe() should add the JMember as an Observer of the topic!", 2, t2.countObservers());

        m1.subscribe(t1);
        assertEquals("JMember.subscribe() should not add the JMember as an Observer of a topic more than once!", 1,
                t1.countObservers());

        m1.subscribe(t2);
        assertEquals("JMember.subscribe() should not add the JMember as an Observer of a topic more than once!", 2,
                t2.countObservers());
    }

    @Test
    public void testUnsubscribe() {
        m1.subscribe(t1);
        m1.subscribe(t2);
        m2.subscribe(t2);

        m1.unsubscribe(t2);
        assertFalse("JMember.unsubscribe() should remove the topic from the member's subscribed topics!", m1
                .getSubscribedTopics().contains(t2));
        assertEquals("JMember.unsubscribe() should remove the JMember as an Observer of the topic!", 1,
                t2.countObservers());

        m1.unsubscribe(t1);
        assertFalse("JMember.unsubscribe() should add the topic to the member's subscribed topics!", m1
                .getSubscribedTopics().contains(t1));
        assertEquals("JMember.unsubscribe() should add the JMember as an Observer of the topic!", 0,
                t1.countObservers());

        m2.unsubscribe(t1);
        assertTrue("JMember.unsubscribe() should only remove topics subscribed by the member!", m2
                .getSubscribedTopics().contains(t2));
        assertEquals("JMember.unsubscribe() should add the JMember as an Observer of the topic!", 1,
                t2.countObservers());

        m2.unsubscribe(t2);
        assertFalse("JMember.unsubscribe() should add the topic to the member's subscribed topics!", m2
                .getSubscribedTopics().contains(t2));
        assertEquals("JMember.unsubscribe() should add the JMember as an Observer of the topic!", 0,
                t2.countObservers());
    }

    @Test
    public void testNotifications() {
        m1.subscribe(t1);
        m1.subscribe(t2);
        m2.subscribe(t2);

        t1.setTitle("newTitle1");
        testStreams("JMember.update() should print the correct log message when a topic is updated!", "The topic "
                + t1.getId() + " has been updated!");
        t1.setDescription("newDescription1");
        testStreams("JMember.update() should print the correct log message when a topic is updated!", "The topic "
                + t1.getId() + " has been updated!");

        t2.setTitle("newTitle2");
        testStreams("JMember.update() should print the correct log message when a topic is updated!", "The topic "
                + t2.getId() + " has been updated!" + System.getProperty("line.separator") + "The topic " + t2.getId()
                + " has been updated!");
        t2.setDescription("newDescription2");
        testStreams("JMember.update() should print the correct log message when a topic is updated!", "The topic "
                + t2.getId() + " has been updated!" + System.getProperty("line.separator") + "The topic " + t2.getId()
                + " has been updated!");
    }

    @After
    public void cleanUpStreams() {
        System.setOut(backOut);
        System.setErr(backErr);
    }

    // A method comparing the String streamOutput with the actual streams
    private void testStreams(String message, String streamOutput) {
        assertEquals(message, streamOutput + System.getProperty("line.separator"), outContent.toString());
        outContent.reset();
        errContent.reset();
    }
}
