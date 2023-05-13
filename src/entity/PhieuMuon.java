/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Hao
 */
public class PhieuMuon implements Serializable {
    private String maPhieuMuon;
    private DocGia docGia;
    private ArrayList<Sach> sach;
    private Date ngayMuon;
    private Date ngayPhaiTra;
    private ArrayList<Date> ngayTra;
    private ArrayList<String> trangThai;
    private final long serialVersionUID = 1130120022L;

    public PhieuMuon(String maPhieuMuon, DocGia docGia, ArrayList<Sach> sach, Date ngayMuon, Date ngayPhaiTra, ArrayList<Date> ngayTra, ArrayList<String> trangThai) {
        this.maPhieuMuon = maPhieuMuon;
        this.docGia = docGia;
        this.sach = sach;
        this.ngayMuon = ngayMuon;
        this.ngayPhaiTra = ngayPhaiTra;
        this.ngayTra = ngayTra;
        this.trangThai = trangThai;
    }
    
    public PhieuMuon() {
        sach = new ArrayList<>();
        ngayTra = new ArrayList<>();
        trangThai = new ArrayList<>();
    }
    
    public static ArrayList<PhieuMuon> getAllPhieuMuon() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("phieumuon.dat"))) {
            ArrayList<PhieuMuon> phieuMuons = (ArrayList<PhieuMuon>)ois.readObject();
            return phieuMuons;
        }
        catch(Exception e) {
            return new ArrayList<>();
        }
    }
    
    public static boolean addPhieuMuon(PhieuMuon phieuMuon) {
        ArrayList<PhieuMuon> phieuMuons = getAllPhieuMuon();
        phieuMuons.add(phieuMuon);
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("phieumuon.dat"))) {
            oos.writeObject(phieuMuons);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }
    
    public static PhieuMuon getPhieuMuon(String maPhieuMuon) {
        ArrayList<PhieuMuon> phieuMuons = getAllPhieuMuon();
        for(PhieuMuon phieuMuon : phieuMuons) {
            if(phieuMuon.getMaPhieuMuon().equals(maPhieuMuon)) {
                return phieuMuon;
            }
        }
        return null;
    }
    
    public static boolean updatePhieuMuon(PhieuMuon phieuMuon) {
        boolean success = false;
        ArrayList<PhieuMuon> phieuMuons = getAllPhieuMuon();
        for(int i = 0; i < phieuMuons.size(); i++) {
            PhieuMuon temp = phieuMuons.get(i); 
            if(temp.getMaPhieuMuon().equals(phieuMuon.maPhieuMuon)) {
                temp.setDocGia(phieuMuon.getDocGia());
                temp.setSach(phieuMuon.getSach());
                temp.setNgayMuon(phieuMuon.getNgayMuon());
                temp.setNgayPhaiTra(phieuMuon.getNgayPhaiTra());
                temp.setNgayTra(phieuMuon.getNgayTra());
                temp.setTrangThai(phieuMuon.getTrangThai());
                phieuMuons.set(i, temp);
                success = true;
            }    
        }
        if(!success) {
            return success;
        }
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("phieumuon.dat"))) {
            oos.writeObject(phieuMuons);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }
    
    public static boolean deletePhieuMuon(String maPhieuMuon) {
        return true;
    }
    
    public String getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public void setMaPhieuMuon(String maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public DocGia getDocGia() {
        return docGia;
    }

    public void setDocGia(DocGia docGia) {
        this.docGia = docGia;
    }

    public ArrayList<Sach> getSach() {
        return sach;
    }

    public void setSach(ArrayList<Sach> sach) {
        this.sach = sach;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public Date getNgayPhaiTra() {
        return ngayPhaiTra;
    }

    public void setNgayPhaiTra(Date ngayPhaiTra) {
        this.ngayPhaiTra = ngayPhaiTra;
    }

    public ArrayList<Date> getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(ArrayList<Date> ngayTra) {
        this.ngayTra = ngayTra;
    }

    public ArrayList<String> getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(ArrayList<String> trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "PhieuMuon{" + "maPhieuMuon=" + maPhieuMuon + ", docGia=" + docGia + ", sach=" + sach + ", ngayMuon=" + ngayMuon + ", ngayPhaiTra=" + ngayPhaiTra + ", ngayTra=" + ngayTra + ", trangThai=" + trangThai + ", serialVersionUID=" + serialVersionUID + '}';
    }
    
}
