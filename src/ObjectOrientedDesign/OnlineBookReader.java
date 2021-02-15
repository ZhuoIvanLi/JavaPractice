package ObjectOrientedDesign;

import java.util.HashMap;

public class OnlineBookReader {
    public class OnlineBookReaderSystem{
        private Library library;
        private UserManager userManager;
        private Display display;

        private Book activeBook;
        private User activeUser;

        public OnlineBookReaderSystem() {
            library= new Library();
            userManager = new UserManager();
            display = new Display();
        }

        public Library getLibrary() {
            return library;
        }

        public UserManager getUserManager() {
            return userManager;
        }

        public Display getDisplay() {
            return display;
        }

        public Book getActiveBook() {
            return activeBook;
        }

        public void setActiveBook(Book activeBook) {
            this.activeBook = activeBook;
            display.displayBook(activeBook);
        }

        public User getActiveUser() {
            return activeUser;
        }

        public void setActiveUser(User activeUser) {
            this.activeUser = activeUser;
            display.displayUser(activeUser);
        }
    }


    public class Library {
        private HashMap<Integer, Book> books;

        public Book addBook(int id, String detail){
            if(books.containsKey(id)){
                return null;
            }

            Book book = new Book(id, detail);
            books.put(id, book);
            return book;
        }

        public boolean removeBook(Book book){
            return removeBook(book.getId());
        }
        public boolean removeBook(int id){
            if(!books.containsKey(id)){
                return false;
            }

            books.remove(id);
            return true;
        }

        public Book findBook(int id){
            return books.get(id);
        }
    }

    public class UserManager {
        private HashMap<Integer, User> users;

        public User addUser(int id, String details, int accountType){
            if(users.containsKey(id)){ return null; }

            User user = new User(id, details, accountType);
            users.put(id, user);
            return user;
        }

        public User findUser(int id){ return users.get(id); }
        public boolean removeUser(User user){return removeUser(user.getId());}
        public boolean removeUser(int id){
            if(users.containsKey(id)){
                users.remove(id);
                return true;
            }

            return false;
        }
    }

    public class Display {
        private Book activeBook;
        private User activeUser;
        private int pageNumber=0;

        public void displayUser(User user){
            activeUser = user;
            refreshUserName();
        }

        public void displayBook(Book book){
            activeBook = book;
            pageNumber=0;

            refreshTitle();
            refreshDetail();
            refreshPage();
        }

        public void pageForward(){
            pageNumber++;
            refreshPage();
        }

        public void pageBackward(){
            pageNumber--;
            refreshPage();
        }

        // Update info
        public void refreshUserName(){}
        public void refreshTitle(){}
        public void refreshDetail(){}
        public void refreshPage(){}
    }

    public class Book {
        private int id;
        private String detail;

        public Book(int id, String detail) {
            this.id = id;
            this.detail = detail;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }


    }

    public class User {
        private int id;
        private String detail;
        private int acountType;

        public boolean renewMembership() {...}

        public User(int id, String detail, int acountType) {
            this.id = id;
            this.detail = detail;
            this.acountType = acountType;
        }
        // Then Getters and Setters
    }
}
