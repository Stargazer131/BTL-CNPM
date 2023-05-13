/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlythuvien;

import entity.DocGia;
import entity.PhieuMuon;
import entity.Sach;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Hao
 */
public class TestMuonSach {
    public static void main(String[] args) {
        addBook();
        addDocGia();
        addPhieuMuon();
    }
    
    private static void addPhieuMuon() {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("phieumuon.dat"))) {
            ArrayList<PhieuMuon> phieuMuons = new ArrayList<>();
            Calendar calendar = Calendar.getInstance();
            DocGia docGia = DocGia.getDocGia("1");
            
            ////////////////////////////////////
            Sach sach1 = Sach.getSach("B001");
            Sach sach2 = Sach.getSach("B002");
            Sach sach3 = Sach.getSach("B003");
            ArrayList<Sach> sachs1 = new ArrayList<>();
            sachs1.add(sach1);
            sachs1.add(sach2);
            sachs1.add(sach3);
            
            calendar.set(2023, 3, 25);
            Date ngayMuon = calendar.getTime();
            calendar.add(Calendar.DAY_OF_MONTH, 30);
            Date ngayPhaiTra = calendar.getTime();
            
            ArrayList<Date> ngayTra = new ArrayList<>();
            calendar.set(2023, 4, 1);
            Date date1 = calendar.getTime();
            
            calendar.set(2023, 4, 5);
            Date date2 = calendar.getTime();
            
            calendar.set(2023, 3, 30);
            Date date3 = calendar.getTime();
            
            ngayTra.add(date1);
            ngayTra.add(date2);
            ngayTra.add(date3); 
            
            ArrayList<String> trangThai = new ArrayList<>();
            trangThai.add(null);
            trangThai.add(null);
            trangThai.add(null);
            
            PhieuMuon phieuMuon1 = new PhieuMuon("1", docGia, sachs1, ngayMuon, ngayPhaiTra, ngayTra, trangThai);
            
            /////////////////////////////////////
            Sach sach4 = Sach.getSach("B014");
            Sach sach5 = Sach.getSach("B015");
            ArrayList<Sach> sachs2 = new ArrayList<>();
            sachs2.add(sach4);
            sachs2.add(sach5);

            calendar.set(2023, 3, 27);
            Date ngayMuon2 = calendar.getTime();
            calendar.add(Calendar.DAY_OF_MONTH, 30);
            Date ngayPhaiTra2 = calendar.getTime();
            
            ArrayList<Date> ngayTra2 = new ArrayList<>();
            ngayTra2.add(null);
            ngayTra2.add(null);
            
            ArrayList<String> trangThai2 = new ArrayList<>();
            trangThai2.add(null);
            trangThai2.add(null);
            
            PhieuMuon phieuMuon2 = new PhieuMuon("2", docGia, sachs2, ngayMuon2, ngayPhaiTra2, ngayTra2, trangThai2);
            ////////
            
            phieuMuons.add(phieuMuon1);
            phieuMuons.add(phieuMuon2);
            
            oos.writeObject(phieuMuons);
        }
        catch(Exception e) {

        }
    }
    
    private static void addDocGia() {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("docgia.dat"))) {
            ArrayList<DocGia> docGias = new ArrayList<>();
            Calendar calendar = Calendar.getInstance();
            
            calendar.set(2002, 1, 13);
            DocGia docGia1 = new DocGia("1", "HN001", "Vu Ngoc Hao", calendar.getTime(), "Ha Noi", "0397267912");
            calendar.set(1996, 1, 96);
            DocGia docGia2 = new DocGia("2", "HN002", "Tran Van A", calendar.getTime(), "Ha Tay", "0397267912");
            
            docGias.add(docGia1);
            docGias.add(docGia2);
            
            oos.writeObject(docGias);
        }
        catch(Exception e) {

        }
    }
    
    private static void addBook() {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("sach.dat"))) {
            ArrayList<Sach> sachs = new ArrayList<>();
            Sach sach1 = new Sach("B011", "9780141187761", "1984", "George Orwell", 1949, 8.99, 5, "A dystopian novel set in a totalitarian society.");
            Sach sach2 = new Sach("B012", "9780061120084", "To Kill a Mockingbird", "Harper Lee", 1960, 9.99, 3, "A Pulitzer Prize-winning novel set in the Deep South.");
            Sach sach3 = new Sach("B013", "9780441172719", "Dune", "Frank Herbert", 1965, 10.99, 2, "A science fiction novel set on a desert planet.");
            Sach sach4 = new Sach("B014", "9780812977149", "The Road", "Cormac McCarthy", 2006, 14.99, 1, "A post-apocalyptic novel set in a barren wasteland.");
            Sach sach5 = new Sach("B015","9780060256654", "Where the Wild Things Are", "Maurice Sendak", 1963, 5.99, 8, "A children's picture book about a young boy's journey to a fantastical land.");
            Sach sach6 = new Sach("B016","9780545010221", "The Hunger Games", "Suzanne Collins", 2008, 7.99, 4, "A young adult dystopian novel about a televised fight to the death.");
            Sach sach7 = new Sach("B017","9780525478812", "The Very Hungry Caterpillar", "Eric Carle", 1969, 6.99, 10, "A children's picture book about a caterpillar's journey to becoming a butterfly.");
            Sach sach8 = new Sach("B018","9780446310789", "All you need is kill", "Takeuchi Ryousuke", 2000, 19.99, 6, "A soldier who, after dying in a battle, is caught in a time loop that makes him live the same day repeatedly");
            Sach sach9 = new Sach("B019","9780375714832", "The Catcher in the Rye", "J.D. Salinger", 1951, 7.99, 2, "A coming-of-age novel about a young man in New York City.");
            Sach sach10 = new Sach("B020","9780064404990", "Bridge to Terabithia", "Katherine Paterson", 1977, 6.99, 4, "A children's novel about two friends who create a magical world in the woods.");
            Sach sach11 = new Sach("B001", "9780140283330", "The Alchemist", "Paulo Coelho", 1988, 7.99, 20, "A novel about a shepherd's journey to find a treasure");
            Sach sach12 = new Sach("B002", "9780747532743", "Harry Potter and the Philosopher's Stone", "J.K. Rowling", 1997, 10.99, 50, "The first book in the Harry Potter series");
            Sach sach13 = new Sach("B003", "9780747538493", "Harry Potter and the Chamber of Secrets", "J.K. Rowling", 1998, 11.99, 45, "The second book in the Harry Potter series");
            Sach sach14 = new Sach("B004", "9780747545729", "Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", 1999, 12.99, 42, "The third book in the Harry Potter series");
            Sach sach15 = new Sach("B005", "9780747551003", "Harry Potter and the Goblet of Fire", "J.K. Rowling", 2000, 13.99, 38, "The fourth book in the Harry Potter series");
            Sach sach16 = new Sach("B006", "9780439554930", "Harry Potter and the Order of Phoenix", "J.K. Rowling", 2003, 14.99, 35, "The fifth book in the Harry Potter series");
            Sach sach17 = new Sach("B007", "9780439064873", "Harry Potter and the Half-Blood Prince", "J.K. Rowling", 2005, 15.99, 32, "The sixth book in the Harry Potter series");
            Sach sach18 = new Sach("B008", "9780545010221", "Harry Potter and the Deathly Hallows", "J.K. Rowling", 2007, 16.99, 30, "The final book in the Harry Potter series");
            Sach sach19 = new Sach("B009", "9780671528086", "Josee the tiger and the fish", "Seiko Tanabe", 2020, 9.99, 25, "A romance story about a disabled young woman and an university student");
            Sach sach20 = new Sach("B010", "9780141187761", "1984", "George Orwell", 1949, 8.99, 28, "A dystopian novel about a totalitarian regime");

            sachs.add(sach1);
            sachs.add(sach2);
            sachs.add(sach3);
            sachs.add(sach4);
            sachs.add(sach5);
            sachs.add(sach6);
            sachs.add(sach7);
            sachs.add(sach8);
            sachs.add(sach9);
            sachs.add(sach10);
            sachs.add(sach11);
            sachs.add(sach12);
            sachs.add(sach13);
            sachs.add(sach14);
            sachs.add(sach15);
            sachs.add(sach16);
            sachs.add(sach17);
            sachs.add(sach18);
            sachs.add(sach19);
            sachs.add(sach20);

            oos.writeObject(sachs);
        }
        catch(Exception e) {

        }
    }
}
