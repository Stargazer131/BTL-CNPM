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
import java.util.Date;

/**
 *
 * @author Hao
 */
public class DocGia implements Serializable {
    private String maDocGia;
    private String maVachDocGia;
    private String tenDocGia;
    private Date ngaySinh;
    private String diaChi;
    private String soDienThoai;
    private final long serialVersionUID = 130120022L;

    public DocGia(String maDocGia, String maVachDocGia, String tenDocGia, Date ngaySinh, String diaChi, String soDienThoai) {
        this.maDocGia = maDocGia;
        this.maVachDocGia = maVachDocGia;
        this.tenDocGia = tenDocGia;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
    }

    public DocGia() {
    
    }
    
    public static ArrayList<DocGia> getAllDocGia() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("docgia.dat"))) {
            ArrayList<DocGia> docGias = (ArrayList<DocGia>)ois.readObject();
            return docGias;
        }
        catch(Exception e) {
            return new ArrayList<>();
        }
    }
    
    public static boolean addDocGia(DocGia docGia) {
        return true;
    }
    
    public static DocGia getDocGia(String maDocGia) {
        ArrayList<DocGia> docGias = getAllDocGia();
        for(DocGia docGia : docGias) {
            if(docGia.getMaDocGia().equals(maDocGia)) {
                return docGia;
            }
        }
        return null;
    }
    
    public static boolean updateDocGia(DocGia docGia) {
        return true;
    }
    
    public static boolean deleteDocGia(String maDocGia) {
        return true;
    }
    
    public String getMaDocGia() {
        return maDocGia;
    }

    public void setMaDocGia(String maDocGia) {
        this.maDocGia = maDocGia;
    }

    public String getMaVachDocGia() {
        return maVachDocGia;
    }

    public void setMaVachDocGia(String maVachDocGia) {
        this.maVachDocGia = maVachDocGia;
    }

    public String getTenDocGia() {
        return tenDocGia;
    }

    public void setTenDocGia(String tenDocGia) {
        this.tenDocGia = tenDocGia;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    
    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    @Override
    public String toString() {
        return "DocGia{" + "maDocGia=" + maDocGia + ", maVachDocGia=" + maVachDocGia + ", tenDocGia=" + tenDocGia + ", ngaySinh=" + ngaySinh + ", diaChi=" + diaChi + ", soDienThoai=" + soDienThoai + ", serialVersionUID=" + serialVersionUID + '}';
    }
    
    
}
