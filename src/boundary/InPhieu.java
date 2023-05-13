/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import entity.PhieuMuon;
import entity.Sach;
import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Hao
 */
public class InPhieu {
    public static void inPhieuMuon(ArrayList<PhieuMuon> phieuMuons) {
        try(PrintWriter writer = new PrintWriter(new File("phieumuon.txt"))) {
            // String mau
            System.out.println("Cac phieu muon:");
            String form = "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                        "| Ma phieu muon: %s 																				        \n" +
                        "| Ma doc gia: %s 																					\n" +
                        "| Ma vach doc gia: %s                                                                                                                                                                  \n" +
                        "| Ten doc gia: %s																					\n" +
                        "| Cac sach con muon:																					\n" +
                        "| %s  %s  %s  %s  %s  %s																				\n" +
                        "| %s  %s  %s  %s  %s  %s                                                                                                                                                               \n" +
                        "| %s  %s  %s  %s  %s  %s																				\n" +
                        "| %s  %s  %s  %s  %s  %s																				\n" +
                        "| %s  %s  %s  %s  %s  %s																				\n" +
                        "| Tong sach dang muon: %d 																				\n" +
                        "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
            
            // String de in ra file txt
            String data = "";
            for(PhieuMuon phieuMuon : phieuMuons) {
                // khoi tao cac tham so duoc truyen vao STRING MAU
                ArrayList<Object> parameters = new ArrayList<>();
                parameters.add(phieuMuon.getMaPhieuMuon());
                parameters.add(phieuMuon.getDocGia().getMaDocGia());
                parameters.add(phieuMuon.getDocGia().getMaVachDocGia());
                parameters.add(phieuMuon.getDocGia().getTenDocGia());
                
                int count = 0;
                for(int i = 0; i < phieuMuon.getNgayTra().size(); i++) {
                    // neu sach nay chua duoc tra
                    if(phieuMuon.getNgayTra().get(i) == null) {
                        Sach sach = phieuMuon.getSach().get(i);
                        parameters.add(sach.getMaSach());
                        parameters.add(sach.getTenSach());
                        parameters.add(sach.getTacGia());
                        parameters.add(sach.getMaVachSach());
                        parameters.add(dateToString(phieuMuon.getNgayMuon()));
                        parameters.add(dateToString(phieuMuon.getNgayPhaiTra()));
                        count++;
                    }
                }
                
                // neu khong tim thay sach nao chua duoc tra trong phieu muon nay -> bo qua
                if(count == 0) {
                    continue;
                }
                
                
                // dien not cac parameter con lai
                while(parameters.size() < 34) {
                    parameters.add("");
                }       
                parameters.add(count);
                
                Object[] args = new Object[35];
                for (int i = 0; i < 35; i++) {
                    args[i] = parameters.get(i);
                }
                
                String formattedString = String.format(form, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18], args[19], args[20], args[21], args[22], args[23], args[24], args[25], args[26], args[27], args[28], args[29], args[30], args[31], args[32], args[33], args[34]);
                data += formattedString + "\n";
                System.out.println(formattedString);
            }
            System.out.println();
            writer.write(data);
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
    
    public static void inPhieuPhat(ArrayList<PhieuMuon> phieuPhats) { 
        try(PrintWriter writer = new PrintWriter(new File("phieuphat.txt"))) {
            System.out.println("Cac phieu phat:");
            // String mau
            String form = "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n" +
                        "| Ma phieu muon: %s 																				        \n" +
                        "| Ma doc gia: %s 																					\n" +
                        "| Ma vach doc gia: %s                                                                                                                                                                  \n" +
                        "| Ten doc gia: %s																					\n" +
                        "| Cac sach bi phat:																					\n" +
                        "| %s  %s  %s  %s  %s  %s  %s  %s																				\n" +
                        "| %s  %s  %s  %s  %s  %s  %s  %s                                                                                                                                                             \n" +
                        "| %s  %s  %s  %s  %s  %s  %s  %s																				\n" +
                        "| %s  %s  %s  %s  %s  %s  %s  %s																				\n" +
                        "| %s  %s  %s  %s  %s  %s  %s  %s																				\n" +
                        "| Tong tien phat: %s 																				\n" +
                        "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
            
            // String de in ra file txt
            String data = "";
            for(PhieuMuon phieuMuon : phieuPhats) {
                // khoi tao cac tham so duoc truyen vao STRING MAU
                ArrayList<Object> parameters = new ArrayList<>();
                parameters.add(phieuMuon.getMaPhieuMuon());
                parameters.add(phieuMuon.getDocGia().getMaDocGia());
                parameters.add(phieuMuon.getDocGia().getMaVachDocGia());
                parameters.add(phieuMuon.getDocGia().getTenDocGia());
                
                double tongTienPhat = 0;
                for(int i = 0; i < phieuMuon.getTrangThai().size(); i++) {
                    // neu trang thai la null -> khong bi phat
                    // neu bi phat -> kiem tra xem da tra tien hay chua
                    String trangThai = phieuMuon.getTrangThai().get(i);
                    if(trangThai != null  && trangThai.equals("ChuaTraTienPhat")) {
                        Sach sach = phieuMuon.getSach().get(i);
                        Date ngayTra = phieuMuon.getNgayTra().get(i);
                        parameters.add(sach.getMaSach());
                        parameters.add(sach.getTenSach());
                        parameters.add(sach.getTacGia());
                        parameters.add(sach.getMaVachSach());
                        parameters.add(dateToString(phieuMuon.getNgayMuon()));
                        parameters.add(dateToString(phieuMuon.getNgayPhaiTra()));
                        parameters.add(dateToString(ngayTra));
                        double tienPhat = sach.getGiaBia()/5;
                        parameters.add(String.format("%.2f$", tienPhat));
                        tongTienPhat += sach.getGiaBia()/5;
                    }
                }
                
                // neu khong tim thay sach nao bi phat trong phieu muon nay -> bo qua
                if(tongTienPhat == 0) {
                    continue;
                }
                
                // dien not cac parameter con lai
                while(parameters.size() < 44) {
                    parameters.add("");
                }       
                tongTienPhat += 0.001;
                parameters.add(String.format("%.2f$", tongTienPhat));
                
                Object[] args = new Object[45];
                for (int i = 0; i < 45; i++) {
                    args[i] = parameters.get(i);
                }
                
                String formattedString = String.format(form, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9], args[10], args[11], args[12], args[13], args[14], args[15], args[16], args[17], args[18], args[19], args[20], args[21], args[22], args[23], args[24], args[25], args[26], args[27], args[28], args[29], args[30], args[31], args[32], args[33], args[34], args[35], args[36], args[37], args[38], args[39], args[40], args[41], args[42], args[43], args[44]);
                data += formattedString + "\n";
                System.out.println(formattedString);
            }
            System.out.println();
            writer.write(data);
        }
        catch(Exception e) {
            System.out.println(e);
        }       
    }
    
    private static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }
}
