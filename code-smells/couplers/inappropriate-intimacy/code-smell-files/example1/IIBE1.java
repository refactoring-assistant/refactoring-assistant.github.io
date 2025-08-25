import java.util.HashMap;
import java.util.Map;

class Book {
    public String name;
    public String genre;
    public boolean isIssued;

    public Book(String name, String genre) {
        this.name = name;
        this.genre = genre;
        this.isIssued = false;
    }

    public void displayDetails() {
        System.out.println("Name: " + this.name);
        System.out.println("Genre: " + this.genre);
        System.out.println("Is issued: " + this.isIssued);
    }
}

class Library {
    Map<Integer, Book> books;

    public Library() {
        this.books = new HashMap<>();
    }

    public void addBook(int id, Book book) {
        this.books.put(id, book);
    }

    public void removeBook(int id) {
        this.books.remove(id);
    }

    public void issueBook(int id) {
        books.get(id).isIssued = true;
    }

    public void viewNonIssuedBooks() {
        for(Map.Entry<Integer, Book> book: books.entrySet()) {
            if(book.getValue().isIssued == false) {
                System.out.println(book.getValue().name + " - " + book.getValue().genre);
            }
        }
    }
}

public class IIBE1 {
    public static void main(String[] args) {
        Library snell = new Library();
        Book animalFarm = new Book("Animal Farm", "Satire");
        Book ofMiceAndMen = new Book("Of Mice and Men", "Theatre");
        snell.addBook(1, animalFarm);
        snell.addBook(2, ofMiceAndMen);
        snell.issueBook(1);
        snell.viewNonIssuedBooks();
    }
}