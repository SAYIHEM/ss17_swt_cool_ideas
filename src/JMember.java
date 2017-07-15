import java.util.*;

public class JMember implements Observer {

    private Set<JTopic> topics;

    public JMember() {

        this.topics = new HashSet<>();
    }

    @Override
    public void update(Observable observable, Object o) {

        JTopic topic;

        if (observable instanceof JTopic) {

            topic = (JTopic) observable;

            if (this.topics.contains(topic)) {

                System.out.println("The topic " + topic.getId() + " has been updated!");
            }
        }
    }


    public void subscribe(JTopic topic) {

        if (topic == null) throw new NullPointerException("Topic to subscribe cant be NULL!");

        topic.addObserver(this);
        this.topics.add(topic);
    }

    public void unsubscribe(JTopic topic) {

        if (topic == null) throw new NullPointerException("Topic to unsubscribe cant be NULL!");

        topic.deleteObserver(this);
        this.topics.remove(topic);
    }

    public Set<JTopic> getSubscribedTopics() {

        return this.topics;
    }

}
