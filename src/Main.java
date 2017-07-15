public class Main {

    public static void main(String[] args) {


        JIdeaPool pool = new JIdeaPool();

        JIdea idea1 = new JIdea("t1", "d");
        JIdea idea2 = new JIdea("t1", "d");

        JTopic topic1 = new JTopic("top1", "d", 1);

        pool.add(idea1, topic1);
        pool.add(idea1, topic1);

        System.out.println("test");
    }

}
