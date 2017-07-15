import sun.java2d.jules.JulesAATileGenerator;

import java.io.File;

public class JAttachment extends  JContent {

    private File file;

    public JAttachment(String title, String description, File file) {
        super(title, description);

        if (file == null) throw new NullPointerException("File to set cant be NULL!");

        this.file = file;
    }

    public File getFile() {

        return file;
    }

    public void setFile(File file) {

        if (file == null) throw new NullPointerException("File to set cant be NULL!");

        this.file = file;
    }

    @Override
    public String toString() {

        return "Attachment: " + this.getTitle() + "\n" + this.getDescription();
    }

    @Override
    public boolean equals(Object object) {

        JAttachment attachment;

        if (object instanceof JAttachment) {

            attachment = (JAttachment) object;

        } else {

            return false;
        }

        // TODO: Vergleich aktuell über Titel!
        return this.getTitle().equals(attachment.getTitle());
    }
}
