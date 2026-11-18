package mylab.library.entity;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private int publishYear;
    private boolean isAvailable;

    // 기본 생성자
    public Book() {
    }

    public Book(String title, String author, String isbn, int publishYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishYear = publishYear;
        this.isAvailable = true; // 초기에는 도서가 대출 가능 상태로 설정
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // checkOut 메서드: 도서를 대출할 때 호출
    public boolean checkOut() {
        if (isAvailable) {
            isAvailable = false; // 도서 상태를 대출 중으로 변경
            return true; // 대출 성공
        }
        return false; // 대출 실패
    }

    // returnBook 메서드: 도서를 반납할 때 호출
    public boolean returnBook() {
        if (!isAvailable) {
            isAvailable = true; // 도서 상태를 대출 가능으로 변경
            return true; // 반납 성공
        }
        return false; // 반납 실패
        }
        
    @Override
    public String toString() {
        return "책 제목: " + title + '\t' +
                "저자: " + author + '\t' +
                "ISBN: " + isbn + '\t' +
                "출판 연도: " + publishYear + '\t' +
                "대출 가능 여부: " + (isAvailable ? "가능" : "대출 중") + '\t';
    }
}