/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Hao
 */
public class Sach implements Serializable {
    private String maSach;
    private String maVachSach;
    private String tenSach;
    private String tacGia;
    private int namXuatBan;
    private double giaBia;
    private int soLuong;
    private String moTa;
    private final long serialVersionUID = 13012002L;

    public Sach(String maSach, String maVachSach, String tenSach, String tacGia, int namXuatBan, double giaBia, int soLuong, String moTa) {
        this.maSach = maSach;
        this.maVachSach = maVachSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.namXuatBan = namXuatBan;
        this.giaBia = giaBia;
        this.soLuong = soLuong;
        this.moTa = moTa;
    }

    public Sach() {
    
    }

    public static ArrayList<Sach> getAllSach() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("sach.dat"))) {
            ArrayList<Sach> sachs = (ArrayList<Sach>)ois.readObject();
            return sachs;
        }
        catch(Exception e) {
            return new ArrayList<>();
        }
    }
    
    public static boolean addSach(Sach sach) {
        return true;
    }
    
    public static Sach getSach(String maSach) {
        ArrayList<Sach> sachs = getAllSach();
        for(Sach sach : sachs) {
            if(sach.getMaSach().equals(maSach)) {
                return sach;
            }
        }
        return null;
    }
    
    public static boolean updateSach(Sach sach) {
        return true;
    }
    
    public static boolean deleteSach(String maSach) {
        return true;
    }
    
    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getMaVachSach() {
        return maVachSach;
    }

    public void setMaVachSach(String maVachSach) {
        this.maVachSach = maVachSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public int getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public double getGiaBia() {
        return giaBia;
    }

    public void setGiaBia(double giaBia) {
        this.giaBia = giaBia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public String toString() {
        return "Sach{" + "maSach=" + maSach + ", maVachSach=" + maVachSach + ", tenSach=" + tenSach + ", tacGia=" + tacGia + ", namXuatBan=" + namXuatBan + ", giaBia=" + giaBia + ", soLuong=" + soLuong + ", moTa=" + moTa + ", serialVersionUID=" + serialVersionUID + '}';
    }
    
}
