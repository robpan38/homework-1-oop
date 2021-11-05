import java.io.IOException;
import java.util.ArrayList;

public class Administration {

    public static ArrayList<Book> getBooksForPublishingRetailerID(ArrayList<PublishingRetailer> publishingRetailers, int publishingRetailerID) {
        ArrayList<Book> books = new ArrayList<Book>();
        ArrayList<Book> booksWithDuplicates = new ArrayList<Book>();

        for(PublishingRetailer publishingRetailer : publishingRetailers) {
            for(IPublishingArtifact iPublishingArtifact : publishingRetailer.getPublishingArtifacts()) {
                if(iPublishingArtifact instanceof Book) {
                    booksWithDuplicates.add((Book) iPublishingArtifact);
                }
                if(iPublishingArtifact instanceof EditorialGroup) {
                    for(Book book : ((EditorialGroup) iPublishingArtifact).getBooks()) {
                        booksWithDuplicates.add(book);
                    }
                }
                if(iPublishingArtifact instanceof PublishingBrand) {
                    for(Book book : ((PublishingBrand) iPublishingArtifact).getBooks()) {
                        booksWithDuplicates.add(book);
                    }
                }
            }
        }

        for(int i = 0; i < booksWithDuplicates.size(); i++) {
            int duplicate = 0;
            for(int j = 0; j < i; j++) {
                if(booksWithDuplicates.get(i) == booksWithDuplicates.get(j)) {
                    duplicate = 1;
                    break;
                }
            }
            if(duplicate == 0) {
                books.add(booksWithDuplicates.get(i));
            }
        }

        return books;
    }

    /**
     * apelam getBooksForPublishingRetailerID pentru a obtine cartile publicate de un retailer
     * pentru fiecare carte, introducem limba cartii intr-o lista de limbi
     * din lista de limbi (va contine dubluri) extragem limbile unice
    */
    public static ArrayList<Language> getLanguagesForPublishingRetailerID(ArrayList<PublishingRetailer> publishingRetailers, int publishingReatilerID) {
        ArrayList<Language> languages = new ArrayList<Language>();
        ArrayList<Language> languagesWithDuplicates = new ArrayList<Language>();
        ArrayList<Book> books = Administration.getBooksForPublishingRetailerID(publishingRetailers, publishingReatilerID);

        for(Book book : books) {
            languagesWithDuplicates.add()
        }

        return languages;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Book> books = ReadFromFile.initBooks();
        ReadFromFile.initBooksAuthors(books);
        ArrayList<Language> languages = ReadFromFile.initLanguages();
        ArrayList<Author> authors =  ReadFromFile.initAuthors();
        ArrayList<Countries> countries = ReadFromFile.initCountries();
        ArrayList<EditorialGroup> editorialGroups = ReadFromFile.initEditorialGroups();
        ReadFromFile.initEditorialGroupsBooks(editorialGroups, books);
        ArrayList<PublishingBrand> publishingBrands = ReadFromFile.initPublishingBrands();
        ReadFromFile.initPublishingBrandsBooks(publishingBrands, books);
        ArrayList<PublishingRetailer> publishingRetailers = ReadFromFile.initPublishingRetailer();
        ReadFromFile.initPublishingRetailerCountries(publishingRetailers, countries);
        ReadFromFile.initPublishingRetailerBooks(publishingRetailers, books);
        ReadFromFile.initPublishingRetailerEditorialGroups(publishingRetailers, editorialGroups);
        ReadFromFile.initPublishingRetailersPublishingBrands(publishingRetailers, publishingBrands);

        ArrayList<Book> booksOfPublisherRetailer26 = Administration.getBooksForPublishingRetailerID(publishingRetailers, 26);
        for(Book book : booksOfPublisherRetailer26) {
            System.out.println(book.getName() + " " + book.getID());
        }
    }
}
