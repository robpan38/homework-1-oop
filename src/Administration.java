import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;

public class Administration {

    public static ArrayList<Book> getBooksForPublishingRetailerID(ArrayList<PublishingRetailer> publishingRetailers, int publishingRetailerID) {
        ArrayList<Book> books = new ArrayList<Book>();
        ArrayList<Book> booksWithDuplicates = new ArrayList<Book>();

        for(PublishingRetailer publishingRetailer : publishingRetailers) {
            if(publishingRetailer.getID() == publishingRetailerID) {
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
                break;
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
    public static ArrayList<Language> getLanguagesForPublishingRetailerID(ArrayList<PublishingRetailer> publishingRetailers, ArrayList<Language> languages, int publishingReatilerID) {
        ArrayList<Language> languagesResult = new ArrayList<Language>();
        ArrayList<Language> languagesWithDuplicates = new ArrayList<Language>();
        ArrayList<Book> books = Administration.getBooksForPublishingRetailerID(publishingRetailers, publishingReatilerID);

        for(Book book : books) {
            languagesWithDuplicates.add(ReadFromFile.findLanguageByID(languages, book.getLanguageID()));
        }

        for(int i = 0; i < languagesWithDuplicates.size(); i++) {
            int duplicated = 0;
            for(int j = 0; j < i; j++) {
                if(languagesWithDuplicates.get(i) == languagesWithDuplicates.get(j)) {
                    duplicated = 1;
                    break;
                }
            }

            if(duplicated == 0) {
                languagesResult.add(languagesWithDuplicates.get(i));
            }
        }

        return languagesResult;
    }

    /**
     * parcurgem lista de publishing retailers
     * pentru fiecare publishing retailer verificam lista de IPublishingArtifact
     *      daca un IPublishingArtifact este instanta de Book, verificam daca ID-ul coincide cu ID-ul dat ca parametru
     *      daca un IPublishingArtifact este instanta de EditorialGroup, parcurgem lista de carti a editorialGroup-ului cautand cartea dupa ID
     *      daca un IPublishingArtifact este instanta de PublishingBrand, parcurgem lista de carti a publishingBrand-ului cautand cartea dupa ID
     *      daca gasim cartea dupa ID, se introduc tarile corespunzatoare publishingRetailer-ului in lista de tari cu duplicate
     * la final scoatem tarile duplicate din lista si returna lista cu tari unice
    */
    public static ArrayList<Countries> getCountriesForBookID(ArrayList<PublishingRetailer> publishingRetailers, ArrayList<Countries> countries, int bookID) {
        ArrayList<Countries> countriesResult = new ArrayList<Countries>();
        ArrayList<Countries> countriesWithDuplicates = new ArrayList<Countries>();

        for(PublishingRetailer publishingRetailer : publishingRetailers) {
            for(IPublishingArtifact iPublishingArtifact : publishingRetailer.getPublishingArtifacts()) {
                if(iPublishingArtifact instanceof Book) {
                    if(((Book) iPublishingArtifact).getID() == bookID) {
                        for(Countries country : publishingRetailer.getCountries()) {
                            countriesWithDuplicates.add(country);
                        }
                    }
                }
                else if(iPublishingArtifact instanceof EditorialGroup) {
                    for(Book editorialGroupBook : ((EditorialGroup) iPublishingArtifact).getBooks()) {
                        if(editorialGroupBook.getID() == bookID) {
                            for(Countries country : publishingRetailer.getCountries()) {
                                countriesWithDuplicates.add(country);
                            }
                        }
                    }
                }
                else {
                    for(Book publishingBrandBook : ((PublishingBrand) iPublishingArtifact).getBooks()) {
                        if(publishingBrandBook.getID() == bookID) {
                            for(Countries country : publishingRetailer.getCountries()) {
                                countriesWithDuplicates.add(country);
                            }
                        }
                    }
                }
            }
        }

        for(int i = 0; i < countriesWithDuplicates.size(); i++) {
            int duplicated = 0;
            for(int j = 0; j < i; j++) {
                if(countriesWithDuplicates.get(i) == countriesWithDuplicates.get(j)) {
                    duplicated = 1;
                    break;
                }
            }

            if(duplicated == 0) {
                countriesResult.add(countriesWithDuplicates.get(i));
            }
        }

        return countriesResult;
    }

    /**
     * gasim lista de carti a retailer-ului cu id1, gasim lista de carti a retailer-ului cu id2
     * parcurgem lista de carti a retailer-ului cu numarul mai mic de carti, iar pentru fiecare carte, verificam daca aceasta se afla in lista de carti a celuilalt retailer
     * daca se afla, o adaugam in lista de carti comune
    */
    public static ArrayList<Book> getCommonBooksForRetailerIDs(ArrayList<PublishingRetailer> publishingRetailers, int retailerID1, int retailerID2) {
        ArrayList<Book> commonBooks = new ArrayList<Book>();
        ArrayList<Book> retailer1Books = getBooksForPublishingRetailerID(publishingRetailers, retailerID1);
        ArrayList<Book> retailer2Books = getBooksForPublishingRetailerID(publishingRetailers, retailerID2);

        if(retailer1Books.size() < retailer2Books.size()) {
            for(int i = 0; i < retailer1Books.size(); i++) {
                for(int j = 0; j < retailer2Books.size(); j++) {
                    if(retailer1Books.get(i) == retailer2Books.get(j)) {
                        commonBooks.add(retailer1Books.get(i));
                        break;
                    }
                }
            }
        }
        else {
            for(int i = 0; i < retailer2Books.size(); i++) {
                for(int j = 0; j < retailer1Books.size(); j++) {
                    if(retailer2Books.get(i) == retailer1Books.get(j)) {
                        commonBooks.add(retailer2Books.get(i));
                        break;
                    }
                }
            }
        }

        return commonBooks;
    }

    /**
     * se determina lista de carti a primului retailer
     * se parcurge lista de carti a celui de-al doilea retailer si se adauga cartile care nu sunt deja in prima lista
    */
    public static ArrayList<Book> getAllBooksForRetailerIDs (ArrayList<PublishingRetailer> publishingRetailers, int retailerID1, int retailerID2) {
        ArrayList<Book> allBooks = getBooksForPublishingRetailerID(publishingRetailers, retailerID1);
        ArrayList<Book> booksRetailer2 = getBooksForPublishingRetailerID(publishingRetailers, retailerID2);

        for(int i = 0; i < booksRetailer2.size(); i++) {
            int found = 0;
            for(int j = 0; j < allBooks.size(); j++) {
                if(booksRetailer2.get(i) == allBooks.get(j)) {
                    found = 1;
                    break;
                }
            }

            if(found == 0) {
                allBooks.add(booksRetailer2.get(i));
            }
        }

        return allBooks;
    }

    public static void main(String[] args) throws IOException, JAXBException {
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

        // EXEMPLU 1 <==================================================================>

        ArrayList<Book> booksOfPublisherRetailer26 = Administration.getBooksForPublishingRetailerID(publishingRetailers, 26);
        ArrayList<Book> booksOfPublisherRetailer27 = Administration.getBooksForPublishingRetailerID(publishingRetailers, 27);
//        for(Book book : booksOfPublisherRetailer26) {
//            System.out.println(book.getName() + " " + book.getID());
//        }

        ArrayList<Language> languagesOfPublisherRetailer26 = Administration.getLanguagesForPublishingRetailerID(publishingRetailers, languages, 26);
//        for(Language language : languagesOfPublisherRetailer26) {
//            System.out.println(language.getName() + " ");
//        }

        ArrayList<Countries> countriesOfBook204 = Administration.getCountriesForBookID(publishingRetailers, countries, 204);
//        for(Countries country : countriesOfBook204) {
//            System.out.println(country.getCountryCode() + " ");
//        }

        ArrayList<Book> commonBooksForRetailers26and27 = getCommonBooksForRetailerIDs(publishingRetailers, 26, 27);
        ArrayList<Book> allBooksForRetailers26and27 = getAllBooksForRetailerIDs(publishingRetailers, 26, 27);
//        for(Book book : booksOfPublisherRetailer26) {
//            System.out.print(book.getName() + "; ");
//        }
//        System.out.println();
//        for(Book book : booksOfPublisherRetailer27) {
//            System.out.print(book.getName() + "; ");
//        }
//        System.out.println();
//        for(Book book : commonBooksForRetailers26and27) {
//            System.out.print(book.getName() + "; ");
//        }
//        System.out.println();
//        for(Book book : allBooksForRetailers26and27) {
//            System.out.print(book.getName() + "; ");
//        }

        // EXEMPLU 2 <==================================================================>

        ArrayList<Book> booksOfPublisherRetailer1 = Administration.getBooksForPublishingRetailerID(publishingRetailers, 1);
        ArrayList<Book> booksOfPublisherRetailer2 = Administration.getBooksForPublishingRetailerID(publishingRetailers, 2);
//        for(Book book : booksOfPublisherRetailer1) {
//            System.out.println(book.getName() + " " + book.getID());
//        }

        ArrayList<Language> languagesOfPublisherRetailer1 = Administration.getLanguagesForPublishingRetailerID(publishingRetailers, languages, 1);
//        for(Language language : languagesOfPublisherRetailer1) {
//            System.out.println(language.getName() + " ");
//        }

        ArrayList<Countries> countriesOfBook224 = Administration.getCountriesForBookID(publishingRetailers, countries, 224);
//        for(Countries country : countriesOfBook224) {
//            System.out.println(country.getCountryCode() + " ");
//        }

        ArrayList<Book> commonBooksForRetailers1and2 = getCommonBooksForRetailerIDs(publishingRetailers, 1, 2);
        ArrayList<Book> allBooksForRetailers1and2 = getAllBooksForRetailerIDs(publishingRetailers, 1, 2);
//        for(Book book : booksOfPublisherRetailer1) {
//            System.out.print(book.getName() + "; ");
//        }
//        System.out.println();
//        for(Book book : booksOfPublisherRetailer2) {
//            System.out.print(book.getName() + "; ");
//        }
//        System.out.println();
//        for(Book book : commonBooksForRetailers1and2) {
//            System.out.print(book.getName() + "; ");
//        }
//        System.out.println();
//        for(Book book : allBooksForRetailers1and2) {
//            System.out.print(book.getName() + "; ");
//        }

        // EXEMPLU PENTRU FUNCTIILE PUBLISH <==================================================================>

        // test publish book + editorial group + publishing brand
//        for(IPublishingArtifact iPublishingArtifact : publishingRetailers.get(0).getPublishingArtifacts()) {
//            if(iPublishingArtifact instanceof Book) {
//                System.out.println(((Book) iPublishingArtifact).publish());
//            }
//            else if(iPublishingArtifact instanceof EditorialGroup) {
//                System.out.println(((EditorialGroup) iPublishingArtifact).publish());
//            }
//            else {
//                System.out.println(((PublishingBrand) iPublishingArtifact).publish());
//            }
//        }
    }
}
