import java.io.*;
import java.util.ArrayList;

public class ReadFromFile {
    public static Book findBookByID(ArrayList<Book> books, int ID) {
        for(Book book : books) {
            if(book.getID() == ID) {
                return book;
            }
        }
        return null;
    }

    public static EditorialGroup findEditorialGroupByID(ArrayList<EditorialGroup> editorialGroups, int ID) {
        for(EditorialGroup editorialGroup : editorialGroups) {
            if(editorialGroup.getID() == ID) {
                return editorialGroup;
            }
        }
        return null;
    }

    public static PublishingBrand findPublishingBrandByID(ArrayList<PublishingBrand> publishingBrands, int ID) {
        for(PublishingBrand publishingBrand : publishingBrands) {
            if(publishingBrand.getID() == ID) {
                return publishingBrand;
            }
        }
        return null;
    }

    public static PublishingRetailer findPublishingRetailerByID(ArrayList<PublishingRetailer> publishingRetailers, int ID) {
        for(PublishingRetailer publishingRetailer : publishingRetailers) {
            if(publishingRetailer.getID() == ID) {
                return publishingRetailer;
            }
        }
        return null;
    }

    public static Countries findCountryByID(ArrayList<Countries> countries, int ID) {
        for(Countries country : countries) {
            if(country.getID() == ID) {
                return country;
            }
        }
        return null;
    }

    public static ArrayList<Book> initBooks() throws IOException {
        ArrayList<Book> books = new ArrayList<Book>();

        try(BufferedReader br = new BufferedReader(new FileReader("init/books.in"))) {
            int iter = 0;
            String line;

            while((line = br.readLine()) != null) {
                if(iter == 0) {
                    iter++;
                    continue;
                }

                String[] splittedLine = line.split("###");
                Book book = new Book(Integer.parseInt(splittedLine[0]), splittedLine[1], splittedLine[2], splittedLine[3], Integer.parseInt(splittedLine[4]), Integer.parseInt(splittedLine[6]), splittedLine[7]);
                String[] keywords = splittedLine[5].split(";");

                for(String keyword : keywords) {
                    if(keyword.charAt(0) == ' ') {
                        book.addKeyword(keyword.substring(1));
                    }
                    else book.addKeyword(keyword);
                }
                books.add(book);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return books;
    }

    public static void initBooksAuthors(ArrayList<Book> books) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("init/books-authors.in"))) {
            int iter = 0;
            String line;

            while((line = br.readLine()) != null) {
                if(iter == 0) {
                    iter++;
                    continue;
                }

                String[] splittedLine = line.split("###");
                Book book = ReadFromFile.findBookByID(books, Integer.parseInt(splittedLine[0]));
                book.addAuthor(Integer.parseInt(splittedLine[1]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Language> initLanguages() throws IOException {
        ArrayList<Language> languages = new ArrayList<Language>();

        try(BufferedReader br = new BufferedReader(new FileReader("init/languages.in"))) {
            int iter = 0;
            String line;

            while((line = br.readLine()) != null) {
                if(iter == 0) {
                    iter++;
                    continue;
                }

                String[] formattedLine = line.split("###");
                Language language = new Language(Integer.parseInt(formattedLine[0]), formattedLine[1], formattedLine[2]);
                languages.add(language);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return languages;
    }

    public static ArrayList<Author> initAuthors() throws IOException {
        ArrayList<Author> authors = new ArrayList<Author>();

        try(BufferedReader br = new BufferedReader(new FileReader("init/authors.in"))) {
            int iter = 0;
            String line;

            while((line = br.readLine()) != null) {
                if(iter == 0) {
                    iter++;
                    continue;
                }

                String[] splittedLine = line.split("###");
                Author author = new Author(Integer.parseInt(splittedLine[0]), splittedLine[1], splittedLine[2]);
                authors.add(author);
            }
        }

        return authors;
    }

    public static ArrayList<Countries> initCountries() throws IOException {
        ArrayList<Countries> countries = new ArrayList<Countries>();

        try(BufferedReader br = new BufferedReader(new FileReader("init/countries.in"))) {
            int iter = 0;
            String line;

            while((line = br.readLine()) != null) {
                if(iter == 0) {
                    iter++;
                    continue;
                }

                String[] splittedLine = line.split("###");
                Countries country = new Countries(Integer.parseInt(splittedLine[0]), splittedLine[1]);
                countries.add(country);
            }
        }

        return countries;
    }

    public static ArrayList<EditorialGroup> initEditorialGroups() throws IOException {
        ArrayList<EditorialGroup> editorialGroups = new ArrayList<EditorialGroup>();

        try(BufferedReader br = new BufferedReader(new FileReader("init/editorial-groups.in"))) {
            int iter = 0;
            String line;

            while((line = br.readLine()) != null) {
                if(iter == 0) {
                    iter++;
                    continue;
                }

                String[] splittedLine = line.split("###");
                EditorialGroup editorialGroup = new EditorialGroup(Integer.parseInt(splittedLine[0]), splittedLine[1]);
                editorialGroups.add(editorialGroup);
            }
        }

        return editorialGroups;
    }

    public static void initEditorialGroupsBooks(ArrayList<EditorialGroup> editorialGroups, ArrayList<Book> books) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("init/editorial-groups-books.in"))) {
            int iter = 0;
            String line;

            while((line = br.readLine()) != null) {
                if(iter == 0) {
                    iter++;
                    continue;
                }

                String[] splittedLine = line.split("###");
                ReadFromFile.findEditorialGroupByID(editorialGroups, Integer.parseInt(splittedLine[0])).getBooks().add(findBookByID(books, Integer.parseInt(splittedLine[1])));
            }
        }
    }

    public static ArrayList<PublishingBrand> initPublishingBrands() throws IOException {
        ArrayList<PublishingBrand> publishingBrands = new ArrayList<PublishingBrand>();

        try(BufferedReader br = new BufferedReader(new FileReader("init/publishing-brands.in"))) {
            int iter = 0;
            String line;

            while((line = br.readLine()) != null) {
                if(iter == 0) {
                    iter++;
                    continue;
                }

                String[] splittedLine = line.split("###");
                PublishingBrand publishingBrand = new PublishingBrand(Integer.parseInt(splittedLine[0]), splittedLine[1]);
                publishingBrands.add(publishingBrand);
            }
        }

        return publishingBrands;
    }

    public static void initPublishingBrandsBooks(ArrayList<PublishingBrand> publishingBrands, ArrayList<Book> books) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader("init/publishing-brands-books.in"))) {
            int iter = 0;
            String line;

            while((line = br.readLine()) != null) {
                if(iter == 0) {
                    iter++;
                    continue;
                }

                String[] splittedLine = line.split("###");
                ReadFromFile.findPublishingBrandByID(publishingBrands, Integer.parseInt(splittedLine[0])).getBooks().add(ReadFromFile.findBookByID(books, Integer.parseInt(splittedLine[1])));
            }
        }
    }

    public static ArrayList<PublishingRetailer> initPublishingRetailer() throws IOException {
        ArrayList<PublishingRetailer> publishingRetailers = new ArrayList<PublishingRetailer>();

        try(BufferedReader br = new BufferedReader(new FileReader("init/publishing-retailers.in"))) {
            int iter = 0;
            String line;

            while((line = br.readLine()) != null) {
                if(iter == 0) {
                    iter++;
                    continue;
                }

                String[] splittedLine = line.split("###");
                PublishingRetailer publishingRetailer = new PublishingRetailer(Integer.parseInt(splittedLine[0]), splittedLine[1]);
                publishingRetailers.add(publishingRetailer);
            }
        }

        return publishingRetailers;
    }

    public static void initPublishingRetailerCountries(ArrayList<PublishingRetailer> publishingRetailers, ArrayList<Countries> countries) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("init/publishing-retailers-countries.in"))) {
            int iter = 0;
            String line;

            while((line = br.readLine()) != null) {
                if(iter == 0) {
                    iter++;
                    continue;
                }

                String[] splittedLine = line.split("###");
                ReadFromFile.findPublishingRetailerByID(publishingRetailers, Integer.parseInt(splittedLine[0])).getCountries().add(ReadFromFile.findCountryByID(countries, Integer.parseInt(splittedLine[1])));
            }
        }
    }

    public static void initPublishingRetailerBooks(ArrayList<PublishingRetailer> publishingRetailers, ArrayList<Book> books) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("init/publishing-retailers-books.in"))) {
            int iter = 0;
            String line;

            while((line = br.readLine()) != null) {
                if(iter == 0) {
                    iter++;
                    continue;
                }

                String[] splittedLine = line.split("###");
                ReadFromFile.findPublishingRetailerByID(publishingRetailers, Integer.parseInt(splittedLine[0])).getPublishingArtifacts().add(ReadFromFile.findBookByID(books, Integer.parseInt(splittedLine[1])));
            }
        }
    }

    public static void initPublishingRetailerEditorialGroups(ArrayList<PublishingRetailer> publishingRetailers, ArrayList<EditorialGroup> editorialGroups) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("init/publishing-retailers-editorial-groups.in"))) {

        }
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

        // test initializare books
//        for(Book book : books) {
//            System.out.print(book.getName() + " ");
//            for(Integer authorID : book.getAuthors()) {
//                System.out.print(authorID + " ");
//            }
//            System.out.println();
//        }

        // test initializare languages
//        for(Language language : languages) {
//            System.out.println(language.getID() + " " + language.getCode() + " " + language.getName());
//        }

        // test initializare authors
//        for(Author author : authors) {
//            System.out.println(author.getID() + " " + author.getFirstName() + " " + author.getLastName());
//        }

        // test initializare countries
//        for(Countries country : countries) {
//            System.out.println(country.getID() + " " + country.getCountryCode());
//        }

        // test initializare editorial groups
//        for(EditorialGroup editorialGroup : editorialGroups) {
//            System.out.print(editorialGroup.getID() + " " + editorialGroup.getName() + " ");
//            for(Book book : editorialGroup.getBooks()) {
//                System.out.print(book.getName() + " ");
//            }
//            System.out.println();
//        }

        // test initializare publishing brands
//        for(PublishingBrand publishingBrand : publishingBrands) {
//            System.out.print(publishingBrand.getID() + " " + publishingBrand.getName() + " ");
//            for(Book book : publishingBrand.getBooks()) {
//                System.out.print(book.getName() + " " + book.getID());
//            }
//            System.out.println();
//        }

        // test initializare publishing retailers
        for(PublishingRetailer publishingRetailer : publishingRetailers) {
            System.out.print(publishingRetailer.getID() + " " + publishingRetailer.getName() + " ");
            for(Countries country : publishingRetailer.getCountries()) {
                System.out.print(country.getID() + " ");
            }
            for(IPublishingArtifact iPublishingArtifact : publishingRetailer.getPublishingArtifacts()) {
//                if(iPublishingArtifact instanceof Book) {
//                    System.out.print(((Book) iPublishingArtifact).getName() + "; ");
//                }
            }
            System.out.println();
        }
    }
}