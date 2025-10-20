public class Main {
    public static void main(String[] args) {
        Book novel = new Novel("Laskar pelangi", "Adrea Hirata", "Drama");
        Book magazine = new Magazine("National Geographic", "Varioust Author", "Science");
        Book textbook = new Textbook("Pemrograman Java", "Anonimous", "Informatika");

        User user = new User();

        //menampilkan
        System.out.println("=== Detail Buku ===");
        user.viewBookDetails(novel);
        System.out.println();
        user.viewBookDetails(magazine);
        System.out.println();
        user.viewBookDetails(textbook);
        System.out.println();
        //minjam
        System.out.println("=== proses peminjaman buku ===");
        user.borrowBook(novel);
        user.borrowBook(magazine);

        //status ketersediaan
        System.out.println("\nStatus buku setelah dipinjam : ");
        System.out.println(novel.getTitle() + " tersedia "+ novel.isAvaible());
        System.out.println(magazine.getTitle()+" tersedia "+magazine.isAvaible());

        //mengembalikan buku
        System.out.println("\n=== Proses Pengembalian Buku ===");
        user.returnBook(novel);

        //menampilkan status
        System.out.println("\nStatus buku setelah dikembalikan: ");
        System.out.println(novel.getTitle()+" tersedia: "+ novel.isAvaible());
    }

    
}
