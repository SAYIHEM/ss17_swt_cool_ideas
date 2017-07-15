import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class StateOpenDraftTest extends JStateBaseTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
        i.hold();
    }

    @Test
    public void testDiscuss() {
        i.discuss("discussion");
        assertEquals("OpenState.discuss() should not change the state!", classOpenDraft, getState().getClass());
        assertEquals(
                "OpenState.discuss() should add the new discussion text and a line break to the previous discussion!",
                "discussion\n", i.getCurrentDiscussion());
        i.discuss("second discussion");
        assertEquals("OpenState.discuss() should add the new discussion text and a line break to the previous texts!",
                "discussion\nsecond discussion\n", i.getCurrentDiscussion());
    }

    @Test
    public void testEvaluate() {
        JValuation v = new JValuation("title", "description");
        JValuation v2 = new JValuation("title2", "second valuation");
        i.evaluate(v);
        assertEquals("OpenDraft.evaluate() should not change the state!", classOpenDraft, getState().getClass());
        assertEquals("OpenState.evaluate() should replace the current valuation with the new one!", v,
                i.getValuation());
        i.evaluate(v2);
        assertEquals("OpenState.evaluate() should replace the current valuation with the new one!", v2,
                i.getValuation());
    }

    @Test
    public void testHold() {
        i.hold();
        assertEquals("OpenDraft.hold() should change the state to ApprovedIdea!", classApprovedIdea, getState()
                .getClass());
    }

    @Test
    public void testDecline() {
        i.decline();
        assertEquals("OpenDraft.decline() should change the state to DeclinedIdea!", classDeclinedIdea, getState()
                .getClass());
    }

    @Test
    public void testIllegalStateExceptions() {
        try {
            i.release();
            fail("OpenDraft.release() should throw an IllegalStateException if called!");
        } catch (IllegalStateException e) {
        }
    }
}
