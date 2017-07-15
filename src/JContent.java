import java.util.Observable;

public abstract class JContent extends Observable {

    private String title;
    private String description;

    public JContent(String title, String description) {

        if (title == null) throw new NullPointerException("Title can´t be NULL!");
        if (description == null) throw new NullPointerException("Description can´t be NULL!");

        if (title.equals("")) throw new IllegalArgumentException("Title can´t be empty!");
        if (description.equals("")) throw new IllegalArgumentException("Description can´t be empty!");

        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {

        if (title == null) throw new NullPointerException("Title can´t be NULL!");
        if (title.equals("")) throw new IllegalArgumentException("Title can´t be empty!");

        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {

        if (description == null) throw new NullPointerException("Description can´t be NULL!");
        if (description.equals("")) throw new IllegalArgumentException("Description can´t be empty!");

        this.description = description;
    }

    public abstract String toString();
}
