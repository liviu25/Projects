package Domain;

public class Book implements HasID<String> {
    private String bookId,name,author;
    Type type;

    public Book(String bookId, String name, String author, Type type) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.type = type;
    }

    @Override
    public String getID() {
        return bookId;
    }

    @Override
    public void setID(String s) {
        bookId=s;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", type=" + type +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
