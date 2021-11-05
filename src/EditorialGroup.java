import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringWriter;
import java.util.ArrayList;

@XmlRootElement
public class EditorialGroup implements IPublishingArtifact {
    @XmlElement
    private final int ID;
    @XmlElement
    private final String name;
    @XmlElement
    private ArrayList<Book> books;

    public EditorialGroup() {
        this(1, "EditorialGroup");
    }

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

    public String publish() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(EditorialGroup.class);

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter sw = new StringWriter();

        jaxbMarshaller.marshal(this, sw);

        return sw.toString();
    }
}
