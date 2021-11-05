import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.StringWriter;
import java.util.ArrayList;

@XmlRootElement
public class PublishingBrand implements IPublishingArtifact {
    @XmlElement
    private final int ID;
    @XmlElement
    private final String name;
    @XmlElement
    private ArrayList<Book> books;

    public PublishingBrand() {
        this(1, "PublishingBrand");
    }

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

    public String publish() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(PublishingBrand.class);

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        StringWriter sw = new StringWriter();

        jaxbMarshaller.marshal(this, sw);

        return sw.toString();
    }
}
