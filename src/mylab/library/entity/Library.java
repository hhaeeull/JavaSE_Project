package mylab.library.entity;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private String name;
    private List<Book> books;

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null; // 해당 제목의 도서를 찾을 수 없음
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    public Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equalsIgnoreCase(isbn)) {
                return book;
            }
        }
        return null; // 해당 ISBN의 도서를 찾을 수 없음
    }

    public boolean checkOutBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equalsIgnoreCase(isbn)) {
                return book.checkOut();
            }
        }
        return false; // 해당 ISBN의 도서를 찾을 수 없음
    }

    public boolean returnBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equalsIgnoreCase(isbn)) {
                return book.returnBook();
            }
        }
        return false; // 해당 ISBN의 도서를 찾을 수 없음
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    public List<Book> getAllBooks() {
        System.out.println("전체 도서 목록:");
        for (Book book : books) {
            System.out.println(book.toString());
        }
        return books;
    }
    
    public int getTotalBooks() {
        return books.size();
    }

    public int getAvailableBooksCount() {
        return (int) books.stream().filter(Book::isAvailable).count();
    }

    public int getBorrowedBooksCount() {
        return (int) books.stream().filter(book -> !book.isAvailable()).count();
    }

}
