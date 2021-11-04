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

    public static ArrayList<Book> readBooksWithoutAuthor() throws IOException {
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

    public static void readBooksWithAuthors(ArrayList<Book> books) throws FileNotFoundException {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Book> books = ReadFromFile.readBooksWithoutAuthor();
        ReadFromFile.readBooksWithAuthors(books);

        for(Book book : books) {
            System.out.print(book.getName() + " ");
            for(Integer authorID : book.getAuthors()) {
                System.out.print(authorID + " ");
            }
            System.out.println();
        }
    }
}
