public class User {
    private final  String name;

    public User(){
        this.name = "pengguna umum";
    }

    public User(String name){
        this.name = name;
    }

    public void viewBookDetails(Book book){
        System.out.println("judul: "+book.getTitle());
        System.out.println("penulis: "+book.getAuthor());
        System.out.println("tersedia: "+(book.isAvaible() ? "Ya" : "Tidak"));

        //polymorphm : cek tipe
        if(book instanceof Novel novel){
            System.out.println("Genre: "+ novel.getGenre());
        }
        if(book instanceof Magazine magazine){
            System.out.println("Genre: "+ magazine.getGenre());
        }
        if(book instanceof Textbook textbook){
            System.out.println("Genre: "+textbook.getGenre());
        }
    }

    public void borrowBook(Book book){
        if(book.isAvaible()){
            book.borrowBook();
            System.out.println("Buku \""+ book.getTitle() +"\" berhasil dipinjam oleh "+this.name);

        }else{
            System.out.println("Maaf "+this.name + "buku \"" + book.getTitle()+"\" sedang tidak tersedia");
        }
    }

    //mengembalikan buku
    public void returnBook(Book book){
        if(!book.isAvaible()){
            book.returnBook();
            System.out.println("Buku \""+ book.getTitle()+ "\" berhasil dikembalikan");
        }else{
            System.out.println("Buku \""+book.getTitle() +"\" sudah tersedia");
        }
    }
}
