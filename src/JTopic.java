import java.util.List;

public class JTopic extends JContent implements Comparable {

    private int id;

    public JTopic(String title, String description, int id) {
        super(title, description);

        if (id < 0) throw new IllegalArgumentException("ID cant be lower than 0! Input was: " + id);

        this.id = id;
    }


    public int getId() {
        return id;
    }

    @Override
    public String toString() {

        return "Topic: " + this.getTitle() + "\n" + this.getDescription();
    }

    public void setTitle(String title) {

        super.setTitle(title);

        setChanged();
        notifyObservers(this);
    }

    public void setDescription(String description) {

        super.setDescription(description);

        setChanged();
        notifyObservers(this);
    }

    @Override
    public int compareTo(Object o) {

        JTopic topic;

        if (o instanceof JTopic) {

            topic = (JTopic) o;

            if (this.id == topic.getId()) {

                return 0;

            } else if (this.id < topic.getId()) {

                return -1;

            } else {

                return 1;
            }

        } else {

            return -1;
        }
    }
}
