public class Book {
    private final  String title;
    private final String author;
    private boolean isAvaible;

    public Book (String title, String author){
        this.title = title;
        this.author = author;
        this.isAvaible = true;
    }

    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public boolean isAvaible(){
        return isAvaible;
    }
    public  void borrowBook(){
        this.isAvaible = false;
    }
    public void returnBook(){
        this.isAvaible=true;
    }

}
