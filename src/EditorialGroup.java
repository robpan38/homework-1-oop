import java.util.ArrayList;

public class EditorialGroup implements IPublishingArtifact {
    private final int ID;
    private final String name;
    private ArrayList<Book> books;

    public EditorialGroup(int ID, String name) {
        this.ID = ID;
        this.name = name;
        this.books = new ArrayList<Book>();
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public String publish() {
        return "book from editorial group published";
    }
}
