import java.util.ArrayList;

public class PublishingBrand implements IPublishingArtifact {
    private final int ID;
    private final String name;
    private ArrayList<Book> books;

    public PublishingBrand(int ID, String name) {
        this.ID = ID;
        this.name = name;
        books = new ArrayList<Book>();
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
        return "book from published brand published";
    }
}
