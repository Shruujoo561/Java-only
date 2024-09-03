import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// Book class to represent a book
class Book {
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void borrowBook() {
        isAvailable = false;
    }

    public void returnBook() {
        isAvailable = true;
    }
}

// User class to represent a user
class User {
    private String name;
    private ArrayList<Book> borrowedBooks;

    public User(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        book.borrowBook();
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.returnBook();
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}

// Library class to manage the library
class Library {
    private HashMap<String, Book> books;
    private HashMap<String, User> users;

    public Library() {
        books = new HashMap<>();
        users = new HashMap<>();
    }

    public void addBook(Book book) {
        books.put(book.getTitle(), book);
    }

    public void addUser(User user) {
        users.put(user.getName(), user);
    }

    public void borrowBook(String userName, String bookTitle) {
        User user = users.get(userName);
        Book book = books.get(bookTitle);

        if (user != null && book != null && book.isAvailable()) {
            user.borrowBook(book);
            System.out.println(userName + " borrowed " + bookTitle);
        } else {
            System.out.println("Cannot borrow the book.");
        }
    }

    public void returnBook(String userName, String bookTitle) {
        User user = users.get(userName);
        Book book = books.get(bookTitle);

        if (user != null && book != null && user.getBorrowedBooks().contains(book)) {
            user.returnBook(book);
            System.out.println(userName + " returned " + bookTitle);
        } else {
            System.out.println("Cannot return the book.");
        }
    }

    public void listAvailableBooks() {
        for (Book book : books.values()) {
            if (book.isAvailable()) {
                System.out.println("Title: " + book.getTitle() + ", Author: " + book.getAuthor());
            }
        }
    }
}

// Main class
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        // Adding some books
        library.addBook(new Book("Java Basics", "Author A"));
        library.addBook(new Book("Advanced Java", "Author B"));
        library.addBook(new Book("Data Structures", "Author C"));

        // Adding some users
        library.addUser(new User("Shruti"));
        library.addUser(new User("Amit"));

        while (true) {
            System.out.println("1. List available books");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (choice == 1) {
                library.listAvailableBooks();
            } else if (choice == 2) {
                System.out.print("Enter your name: ");
                String userName = scanner.nextLine();
                System.out.print("Enter the book title: ");
                String bookTitle = scanner.nextLine();
                library.borrowBook(userName, bookTitle);
            } else if (choice == 3) {
                System.out.print("Enter your name: ");
                String userName = scanner.nextLine();
                System.out.print("Enter the book title: ");
                String bookTitle = scanner.nextLine();
                library.returnBook(userName, bookTitle);
            } else if (choice == 4) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }

        scanner.close();
    }
}
