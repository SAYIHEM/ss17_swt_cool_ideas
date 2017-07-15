
import java.util.ArrayList;
import java.util.List;

public class JIdea extends JContent implements Comparable {

    private List<JAttachment> attachments;
    private JState state;

    public JIdea(String title, String description) {
        super(title, description);

        this.attachments = new ArrayList<>();
        this.state = new Draft();
    }

    public void discuss(String text) {

        if (text == null) throw new NullPointerException("Text to discuss cant be NULL!");
        if (text.isEmpty()) throw new IllegalArgumentException("Text to discuss cant be empty!");

        this.state.discuss(text);
    }

    public void evaluate(JValuation valuation) {

        if (valuation == null) throw new NullPointerException("Valuation to evaluate cant be NULL!");

        this.state.evaluate(valuation);
    }

    public void hold() {

        this.state.hold();
    }

    public void release() {

        this.state.release();
    }

    public void decline() {

        this.state.decline();
    }

    public boolean isDeclined() {

        return this.state instanceof DeclinedIdea;
    }

    public boolean isReleased() {

        return this.state instanceof ReleasedIdea;
    }

    public String getCurrentDiscussion() {

        return this.state.getCurrentDiscussion();
    }

    public JValuation getValuation() {

        return this.state.getValuation();
    }

    public void addAttachment(JAttachment attachment) {

        if (attachment == null) throw new NullPointerException("Attachment to add cant be NULL!");

        this.attachments.add(attachment);
    }

    public List<JAttachment> getAttachments() {

        return this.attachments;
    }

    public boolean removeAttachment(JAttachment attachment) {

        if (attachment == null) throw new NullPointerException("Attachment to remove cant be NULL!");

        return this.attachments.remove(attachment);
    }

    @Override
    public String toString() {

        return this.getTitle() + "\n" + this.getDescription();
    }

    @Override
    public int compareTo(Object o) {

        JIdea idea;

        if (o instanceof JIdea) {

            idea = (JIdea) o;

            if (this.getTitle().equals(idea.getTitle())) {  // ... && this.getDescription().equals(idea.getDescription())

                return 0;

            } else {

                return -1;
            }
        }

        return -1;
    }


    /// --------------------- Inner Classes --------------------- ///

    public abstract class JState {

        private String currentDiscussion;
        private JValuation valuation;

        public JState() {

            this.currentDiscussion = "";
        }

        public void discuss(String text) {

        }

        public void evaluate(JValuation valuation) {

        }

        public void hold() {

        }

        public void release() {

        }

        public void decline() {

        }

        public String getCurrentDiscussion() {

            return currentDiscussion;
        }

        public void setCurrentDiscussion(String currentDiscussion) {

            if (currentDiscussion == null) throw new NullPointerException("CurrentDiscussion can´t be NULL!");
            if (currentDiscussion.equals("")) throw new IllegalArgumentException("CurrentDiscussion can´t be empty!");


            this.currentDiscussion = currentDiscussion;
        }

        public JValuation getValuation() {

            return valuation;
        }

        public void setValuation(JValuation valuation) {

            if (valuation == null) throw new NullPointerException("Valuation can´t be NULL!");

            this.valuation = valuation;
        }
    }

    public class ApprovedIdea extends JState {

        public void discuss(String text) {

            throw new IllegalStateException("");
        }

        public void evaluate(JValuation valuation) {

            throw new IllegalStateException("");
        }

        public void hold() {

            throw new IllegalStateException("");
        }

        public void release() {

            state = new ReleasedIdea();
        }

        public void decline() {

            throw new IllegalStateException("");
        }
    }

    public class DeclinedIdea extends JState {

        public void discuss(String text) {

            throw new IllegalStateException("");
        }

        public void evaluate(JValuation valuation) {

            throw new IllegalStateException("");
        }

        public void hold() {

            throw new IllegalStateException("");
        }

        public void release() {

            throw new IllegalStateException("");
        }

        public void decline() {

            throw new IllegalStateException("");
        }
    }

    public class Draft extends JState {

        public void discuss(String text) {

            throw new IllegalStateException("");
        }

        public void evaluate(JValuation valuation) {

            throw new IllegalStateException("");

        }

        public void hold() {

            state = new OpenDraft();
        }

        public void release() {

            throw new IllegalStateException("");

        }

        public void decline() {

            state = new DeclinedIdea();
        }
    }

    public class OpenDraft extends JState {

        public void discuss(String text) {

            setCurrentDiscussion(getCurrentDiscussion() + text + "\n");
        }

        public void evaluate(JValuation valuation) {

            setValuation(valuation);
        }

        public void hold() {

            state = new ApprovedIdea();
        }

        public void release() {

            throw new IllegalStateException("");

        }

        public void decline() {

            state = new DeclinedIdea();
        }
    }

    public class ReleasedIdea extends JState {

        public void discuss(String text) {

            throw new IllegalStateException("");
        }

        public void evaluate(JValuation valuation) {

            throw new IllegalStateException("");
        }

        public void hold() {

            throw new IllegalStateException("");
        }

        public void release() {

            throw new IllegalStateException("");
        }

        public void decline() {

            throw new IllegalStateException("");
        }
    }

}
