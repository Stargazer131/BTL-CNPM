/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.DocGia;
import entity.PhieuMuon;
import entity.Sach;
import java.util.ArrayList;

/**
 *
 * @author Hao
 */
public class MuonSachControl {
    public static Object[] quetTheDocGia(String maDocGia) {
        DocGia docGia = DocGia.getDocGia(maDocGia);
        ArrayList<PhieuMuon> phieuMuons = new ArrayList<>();
        for(PhieuMuon phieuMuon : PhieuMuon.getAllPhieuMuon()) {
            if(phieuMuon.getDocGia().getMaDocGia().equals(maDocGia)) {
                phieuMuons.add(phieuMuon);
            }
        }
        Object[] data = {docGia, phieuMuons};
        return data;
    }
    
    public static Sach getSach(String maSach) {
        return Sach.getSach(maSach);
    }
    
    public static boolean addPhieuMuon(PhieuMuon phieuMuon) {
        return PhieuMuon.addPhieuMuon(phieuMuon);
    }
}
