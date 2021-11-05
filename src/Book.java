import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringWriter;
import java.util.ArrayList;

@XmlRootElement
public class Book implements IPublishingArtifact {
    private int ID;
    private String name;
    private String subtitle;
    private String ISBN;
    private int pageCount;
    private ArrayList<String> keywords;
    private int languageID;
    private String createdOn;
    private ArrayList<Integer> authors;

    public Book() {
        this(1, "Test", "TestSubtitle", "1234567", 333, 1, "astazi");
    }

    public Book(int ID, String name, String subtitle, String ISBN, int pageCount, int languageID, String createdOn) {
        this.ID = ID;
        this.name = name;
        this.subtitle = subtitle;
        this.ISBN = ISBN;
        this.pageCount = pageCount;
        this.keywords = new ArrayList<String>();
        this.languageID = languageID;
        this.createdOn = createdOn;
        this.authors = new ArrayList<Integer>();
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getISBN() {
        return ISBN;
    }

    public int getPageCount() {
        return pageCount;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public int getLanguageID() {
        return languageID;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public ArrayList<Integer> getAuthors() {
        return authors;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setLanguageID(int languageID) {
        this.languageID = languageID;
    }

    public void addKeyword(String keyword) {
        this.keywords.add(keyword);
    }

    public void addAuthor(int authorID) {
        this.authors.add(authorID);
    }

    public String publish() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Book.class);

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter sw = new StringWriter();

        jaxbMarshaller.marshal(this, sw);

        return sw.toString();
    }
}
