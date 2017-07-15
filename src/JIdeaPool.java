import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JIdeaPool {

    private Map<JTopic, Set<JIdea>> pool;

    public JIdeaPool() {

        this.pool = new HashMap<>();
    }

    public void add(JTopic topic) {

        if (topic == null) throw new NullPointerException("Topic to add cant be NULL!");

        if (!this.pool.containsKey(topic)) {

            this.pool.put(topic, new HashSet<>());
        }
    }

    public void add(JIdea idea, JTopic topic) {

        if (topic == null) throw new NullPointerException("Topic to add cant be NULL!");
        if (idea == null) throw new NullPointerException("Idea to add cant be NULL!");

        boolean ideaFound = false;

        if (this.pool.containsKey(topic)) {

            for (JIdea currIdea : this.pool.get(topic)) {

                if (currIdea.getTitle().equals(idea.getTitle())) {

                    ideaFound = true;
                    break;
                }
            }

            if (!ideaFound) this.pool.get(topic).add(idea);

        } else {

            for (JTopic currTopic : this.pool.keySet()) {

                for (JIdea currIdea : this.pool.get(currTopic)) {

                    if (idea.getTitle().equals(currIdea.getTitle()) && idea != currIdea) {

                        ideaFound = true;
                        break;
                    }
                }
            }

            if (!ideaFound) {

                Set<JIdea> newIdeaSet = new HashSet<>();
                newIdeaSet.add(idea);
                this.pool.put(topic, newIdeaSet);
            }
        }
    }

    public boolean remove(JTopic topic) {

        if (topic == null) throw new NullPointerException("Topic to remove cant be NULL!");

        if (this.pool.containsKey(topic)) {

            this.pool.remove(topic);
            return true;

        } else {

            return false;
        }
    }

    public boolean remove(JIdea idea) {

        if (idea == null) throw new NullPointerException("Idea to remove cant be NULL!");

        boolean success = false;

        for (Map.Entry<JTopic, Set<JIdea>> entry : this.pool.entrySet()) {

            if (entry.getValue().contains(idea)) {

                success = true;
            }
            entry.getValue().remove(idea);

        }

        return success;
    }

    public JIdea getIdea(String title) {

        if (title == null) throw new NullPointerException("Title for Idea to get cant be NULL!");
        if (title.isEmpty()) throw new IllegalArgumentException("Title for Idea to get cant be empty!");

        for (Map.Entry<JTopic, Set<JIdea>> entry : this.pool.entrySet()) {

            for (JIdea idea : entry.getValue()) {

                if (idea.getTitle().equals(title)) {

                    return idea;
                }
            }
        }

        return null;
    }

    public int numberOfTopics() {

        if (this.pool.isEmpty()) return 0;

        return this.pool.size();
    }

    public int numberOfIdeas() {

        if (this.pool.isEmpty()) return 0;

        int count = 0;

        Set<JIdea> ideas = new HashSet<>();

        for (Map.Entry<JTopic, Set<JIdea>> entrySet : this.pool.entrySet()) {

            for (JIdea idea : entrySet.getValue()) {

                if (!ideas.contains(idea)) {

                    count++;
                    ideas.add(idea);
                }
            }
        }

        return count;
    }

    public void removeDeclined() {

        Map<JTopic, Set<JIdea>> poolCopy = new HashMap<>(this.pool);

        for (Map.Entry<JTopic, Set<JIdea>> entrySet : poolCopy.entrySet()) {

            Set<JIdea> set = new HashSet<>(entrySet.getValue());

            for (JIdea idea : set) {

                if (idea.isDeclined()) {

                    this.pool.get(entrySet.getKey()).remove(idea);
                }
            }
        }
    }

    public void removeReleased() {

        Map<JTopic, Set<JIdea>> poolCopy = new HashMap<>(this.pool);

        for (Map.Entry<JTopic, Set<JIdea>> entrySet : poolCopy.entrySet()) {

            Set<JIdea> set = new HashSet<>(entrySet.getValue());

            for (JIdea idea : set) {

                if (idea.isReleased()) {

                    entrySet.getValue().remove(idea);
                }
            }
        }
    }

}
