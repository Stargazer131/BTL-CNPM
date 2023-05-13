/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import controller.MuonSachControl;
import entity.DocGia;
import entity.PhieuMuon;
import entity.Sach;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hao
 */
public class MuonSachForm extends javax.swing.JFrame {

    private ArrayList<PhieuMuon> phieuMuons;
    /**
     * Creates new form MuonSachForm
     */
    public MuonSachForm() {
        initComponents();
    }

    private void displayThongTinDocGia() {
        String maDocGia = txtMaDocGia.getText();
        Object[] data = MuonSachControl.quetTheDocGia(maDocGia);
        // neu khong tim ra khong tin doc gia
        if(data[0] == null) {
            phieuMuons = null;
            txtMaVachDocGia.setText("");
            txtTenDocGia.setText("");
            txtNgaySinh.setText("");
            txtDiaChi.setText("");
            txtSoDienThoai.setText("");
            return;
        }
        
        DocGia docGia = (DocGia)data[0];
        phieuMuons = (ArrayList<PhieuMuon>)data[1];
        
        DefaultTableModel modelDaMuon = (DefaultTableModel)tableDaMuon.getModel();
        DefaultTableModel modelDangMuon = (DefaultTableModel)tableDangMuon.getModel();
        modelDaMuon.setRowCount(0);
        modelDangMuon.setRowCount(0);
        for(PhieuMuon phieuMuon : phieuMuons) {
            ArrayList<Sach> sach = phieuMuon.getSach();
            ArrayList<Date> ngayTra = phieuMuon.getNgayTra();
            for(int i = 0; i < ngayTra.size(); i++) {
                // neu ngay tra la null -> dang muon
                if(ngayTra.get(i) == null) {
                    Object[] objects = {sach.get(i).getMaSach(), sach.get(i).getTenSach(), dateToString(phieuMuon.getNgayMuon()), dateToString(phieuMuon.getNgayPhaiTra())};
                    modelDangMuon.addRow(objects);
                }
                // neu ngay tra khong la null -> da muon
                else {
                    Object[] objects = {sach.get(i).getMaSach(), sach.get(i).getTenSach(), dateToString(phieuMuon.getNgayMuon()), dateToString(ngayTra.get(i))};
                    modelDaMuon.addRow(objects);
                }
            }
        }
        
        txtMaVachDocGia.setText(docGia.getMaVachDocGia());
        txtTenDocGia.setText(docGia.getTenDocGia());
        txtNgaySinh.setText(dateToString(docGia.getNgaySinh()));
        txtDiaChi.setText(docGia.getDiaChi());
        txtSoDienThoai.setText(docGia.getSoDienThoai());
       
        // them phieu muon moi vao cuoi phieuMuons
        PhieuMuon temp = new PhieuMuon();
        temp.setDocGia(docGia);
        Calendar calendar = Calendar.getInstance();
        Date ngayMuon = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        Date ngayPhaiTra = calendar.getTime();
        temp.setNgayMuon(ngayMuon);
        temp.setNgayPhaiTra(ngayPhaiTra);
        phieuMuons.add(temp);
    }
    
    private void quetSachMuon() {        
        DefaultTableModel modelDangMuon = (DefaultTableModel)tableDangMuon.getModel();
        String maSach = txtMaSach.getText();
        Sach sach = MuonSachControl.getSach(maSach);
        // khong tim thay sach
        if(sach == null ) {
            return;
        }
        // muon qua 5 quyen
        if(modelDangMuon.getRowCount() == 5) {
            JOptionPane.showMessageDialog(this, "Ban khong the muon qua 5 quyen sach");
            return;
        }
        
        // kiem tra tung phieu muon
        for(int i = 0; i < phieuMuons.size(); i++) {
            ArrayList<Sach> sachs = phieuMuons.get(i).getSach();
            ArrayList<Date> ngayTras = phieuMuons.get(i).getNgayTra();
            // kiem tra tung sach
            for(int j = 0; j < sachs.size(); j++) {
                if(sachs.get(j).getMaSach().equals(sach.getMaSach())) {
                    // neu doc gia dang muon quyen sach nay
                    if(ngayTras.get(j) == null) {
                        JOptionPane.showMessageDialog(this, "Doc gia dang muon quyen sach nay roi");
                        return;
                    }
                }
            }
        }
        
        // them sach vao phieu muon cuoi cung va them sach vao bang dang muon
        int index = phieuMuons.size()-1;
        phieuMuons.get(index).getSach().add(sach);
        phieuMuons.get(index).getNgayTra().add(null);
        phieuMuons.get(index).getTrangThai().add(null);
        Object[] objects = {sach.getMaSach(), sach.getTenSach(), dateToString(phieuMuons.get(index).getNgayMuon()), dateToString(phieuMuons.get(index).getNgayPhaiTra())};
        modelDangMuon.addRow(objects);
    }
    
    private void submit() {
        if(phieuMuons != null && !phieuMuons.get(phieuMuons.size()-1).getSach().isEmpty()) {
            // them ma phieu muon = ma phieu muon cuoi cung + 1
            int size = PhieuMuon.getAllPhieuMuon().size();
            phieuMuons.get(phieuMuons.size()-1).setMaPhieuMuon(String.valueOf(size+1));
            MuonSachControl.addPhieuMuon(phieuMuons.get(phieuMuons.size()-1));
            InPhieu.inPhieuMuon(phieuMuons);
            JOptionPane.showMessageDialog(this, "Xem phieu muon tren Console hoac trong file phieumuon.txt");
        }
    }
    
    private static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDangMuon = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableDaMuon = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtMaSach = new javax.swing.JTextField();
        txtMaVachDocGia = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtMaDocGia = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTenDocGia = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        btnSubmit = new javax.swing.JButton();
        btnQuetSach = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel1.setText("MUON SACH");

        tableDangMuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ma Sach", "Ten Sach", "Ngay Muon", "Ngay Phai Tra"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableDangMuon);

        tableDaMuon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ma Sach", "Ten Sach", "Ngay Muon", "Ngay Tra"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableDaMuon);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel2.setText("SACH DA MUON");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel3.setText("SACH DANG MUON");

        jLabel4.setText("MA VACH DOC GIA");

        jLabel5.setText("MA SACH");

        txtMaVachDocGia.setEditable(false);

        jLabel6.setText("MA DOC GIA");

        txtMaDocGia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtMaDocGiaFocusLost(evt);
            }
        });

        jLabel7.setText("TEN DOC GIA");

        txtTenDocGia.setEditable(false);

        jLabel8.setText("NGAY SINH");

        txtNgaySinh.setEditable(false);

        jLabel9.setText("SO DIEN THOAI");

        txtSoDienThoai.setEditable(false);

        jLabel10.setText("DIA CHI");

        txtDiaChi.setEditable(false);

        btnSubmit.setText("SUBMIT");
        btnSubmit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubmitActionPerformed(evt);
            }
        });

        btnQuetSach.setText("Quet Sach");
        btnQuetSach.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnQuetSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuetSachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(623, 623, 623)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(638, 638, 638)
                                .addComponent(jLabel2)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addComponent(jScrollPane1))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(610, 610, 610))
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaDocGia)
                    .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnQuetSach, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaVachDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenDocGia, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(114, 114, 114))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMaVachDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTenDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaDocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnQuetSach, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaSach, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1418, 847));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaDocGiaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtMaDocGiaFocusLost
        displayThongTinDocGia();
    }//GEN-LAST:event_txtMaDocGiaFocusLost

    private void btnQuetSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuetSachActionPerformed
        quetSachMuon();
    }//GEN-LAST:event_btnQuetSachActionPerformed

    private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubmitActionPerformed
        submit();
    }//GEN-LAST:event_btnSubmitActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MuonSachForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MuonSachForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MuonSachForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MuonSachForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MuonSachForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnQuetSach;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tableDaMuon;
    private javax.swing.JTable tableDangMuon;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaDocGia;
    private javax.swing.JTextField txtMaSach;
    private javax.swing.JTextField txtMaVachDocGia;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtTenDocGia;
    // End of variables declaration//GEN-END:variables
}
