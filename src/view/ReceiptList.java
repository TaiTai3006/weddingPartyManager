/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import dao.ChiTiet_DV_ThanhToanDAO;
import dao.HoaDonDAO;
import java.util.ArrayList;
import java.sql.Date;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import model.ChiTiet_DV_ThanhToan;
import model.HoaDon;

/**
 *
 * @author Asus
 */
public class ReceiptList extends javax.swing.JInternalFrame {

    /**
     * Creates new form ReceiptList
     */
    public ReceiptList() {
        initComponents();
         this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        CreateTableHD();
    }

    private DefaultTableModel defaultTableHD;
    private DefaultTableModel defaultTableCTDVTT;
    private ArrayList<HoaDon> lstHoaDon = HoaDonDAO.getInstance().SelectAll();
    private ArrayList<ChiTiet_DV_ThanhToan> lstCTDVTT = ChiTiet_DV_ThanhToanDAO.getInstance().SelectAll();

    public void CreateTableHD() {
        defaultTableHD = (DefaultTableModel) HoaDonTable.getModel();
        int i = 0;
        for (HoaDon hd : lstHoaDon) {
            defaultTableHD.addRow(new Object[]{++i, hd.getMaHoaDon(), hd.getMaTiecCuoi(), hd.getNgayThanhToan(), hd.getTongTienDichVu(), hd.getTienPhat(), hd.getTongTienHoaDon(), hd.getConLai(), hd.getUserName()});
        }
    }

    public void ReloadTableHD() {
        lstHoaDon = HoaDonDAO.getInstance().SelectAll();
        defaultTableHD.setRowCount(0);
        CreateTableHD();
    }

    public void CreateTableCTDVTT() {
        int row = HoaDonTable.getSelectedRow();
        String maHD = String.valueOf(HoaDonTable.getValueAt(row, 1));
        defaultTableCTDVTT = (DefaultTableModel) tblCTDVTT.getModel();
        int i = 0;
        for (ChiTiet_DV_ThanhToan ctdvtt : lstCTDVTT) {
            if (ctdvtt.getMaHoaDon().equals(maHD)) {
                defaultTableCTDVTT.addRow(new Object[]{++i, ctdvtt.getMaHoaDon(), ctdvtt.getMaDichVu(), ctdvtt.getSoLuong(), ctdvtt.getDonGiaDichVu(), ctdvtt.getThanhTien(), ctdvtt.getGhichu()});
            }
        }
    }

    public void SearchTable() {
        defaultTableHD.setRowCount(0);
        defaultTableHD = (DefaultTableModel) HoaDonTable.getModel();
        int i = 0;
        String value = txtSearch.getText();
        for (HoaDon hd : lstHoaDon) {
            System.out.println();
            if (hd.getMaHoaDon().toLowerCase().contains(value.toLowerCase()) || hd.getMaTiecCuoi().toLowerCase().contains(value.toLowerCase())) {
                defaultTableHD.addRow(new Object[]{++i, hd.getMaHoaDon(), hd.getMaTiecCuoi(), hd.getNgayThanhToan(), hd.getTongTienDichVu(), hd.getTienPhat(), hd.getTongTienHoaDon(), hd.getConLai(), hd.getUserName()});
            }
        }
    }

    public void Message(String message, int messageType) {
        JOptionPane jOptionPane = new JOptionPane(message, messageType);
        JDialog dialog = jOptionPane.createDialog(null, "Message");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Page1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        HoaDonTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCTDVTT = new javax.swing.JTable();

        Page1.setBackground(new java.awt.Color(255, 255, 255));
        Page1.setPreferredSize(new java.awt.Dimension(1170, 730));

        HoaDonTable.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        HoaDonTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã hóa hơn", "Mã tiệc cưới", "Ngày thanh toán", "Tổng tiền dịch vụ", "Tiền phạt", "Tổng tiền hóa đơn", "Còn lại", "Username"
            }
        ));
        HoaDonTable.setFocusable(false);
        HoaDonTable.setRowHeight(25);
        HoaDonTable.setSelectionBackground(new java.awt.Color(69, 96, 134));
        HoaDonTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HoaDonTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(HoaDonTable);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(69, 96, 134));
        jLabel1.setText("DANH SÁCH HÓA ĐƠN");

        btnDelete.setBackground(new java.awt.Color(132, 70, 133));
        btnDelete.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(238, 230, 226));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Group 9.png"))); // NOI18N

        txtSearch.setBackground(new java.awt.Color(238, 230, 226));
        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtSearch.setBorder(null);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch)
                .addContainerGap())
        );

        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                SearchTable();
            }
            public void removeUpdate(DocumentEvent e) {
                if(txtSearch.getText().equals("")){
                    defaultTableHD.setRowCount(0);
                    CreateTableHD();
                }else{
                    SearchTable();
                }
            }
            public void insertUpdate(DocumentEvent e) {
                SearchTable();
            }
        });

        tblCTDVTT.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblCTDVTT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã hóa đơn ", "Mã dịch vụ", "Số lượng", "Đơn giá dịch vụ", "Thành tiền ", "Ghi chú"
            }
        ));
        jScrollPane3.setViewportView(tblCTDVTT);

        javax.swing.GroupLayout Page1Layout = new javax.swing.GroupLayout(Page1);
        Page1.setLayout(Page1Layout);
        Page1Layout.setHorizontalGroup(
            Page1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Page1Layout.createSequentialGroup()
                .addGap(333, 386, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(387, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Page1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Page1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Page1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))
                    .addGroup(Page1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Page1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(243, 243, 243))
        );
        Page1Layout.setVerticalGroup(
            Page1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Page1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(Page1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1182, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Page1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 787, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(Page1, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void HoaDonTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HoaDonTableMouseClicked
        // TODO add your handling code here:
        defaultTableCTDVTT = (DefaultTableModel) tblCTDVTT.getModel();
        defaultTableCTDVTT.setRowCount(0);
        CreateTableCTDVTT();
    }//GEN-LAST:event_HoaDonTableMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int row = HoaDonTable.getSelectedRow();
        int[] rows = HoaDonTable.getSelectedRows();
        if (row < 0) {
            Message("Vui lòng chọn dữ liệu muốn xoá!", JOptionPane.INFORMATION_MESSAGE);
        } else {

            String mess = "";
            if (rows.length == 1) {
                mess = String.valueOf(HoaDonTable.getValueAt(row, 1)) + " ";
            } else {
                for (int r : rows) {
                    mess += String.valueOf(HoaDonTable.getValueAt(r, 1)) + " ";
                }
            }

            int x = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá " + mess + "hay không?");
            if (x == JOptionPane.YES_OPTION) {
                int kq = 0;
                if (rows.length == 1) {
                    String tmp1 = String.valueOf(HoaDonTable.getValueAt(row, 1));
                    String tmp2 = String.valueOf(HoaDonTable.getValueAt(row, 2));
                    Date tmp3 = Date.valueOf(String.valueOf(HoaDonTable.getValueAt(row, 3)));
                    double tmp4 = Double.parseDouble(String.valueOf(HoaDonTable.getValueAt(row, 4)));
                    double tmp5 = Double.parseDouble(String.valueOf(HoaDonTable.getValueAt(row, 5)));
                    double tmp6 = Double.parseDouble(String.valueOf(HoaDonTable.getValueAt(row, 6)));
                    double tmp7 = Double.parseDouble(String.valueOf(HoaDonTable.getValueAt(row, 7)));
                    String tmp8 = String.valueOf(HoaDonTable.getValueAt(row, 8));

                    int kq1 = 0;
                    for (ChiTiet_DV_ThanhToan ctdvtt : lstCTDVTT) {
                        if (ctdvtt.getMaHoaDon().equals(tmp1)) {
                            kq1 = ChiTiet_DV_ThanhToanDAO.getInstance().Delete(ctdvtt);
                        }
                    }

                    try {
                        kq = HoaDonDAO.getInstance().Delete(new HoaDon(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, tmp7, tmp8));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    for (int r : rows) {
                        String tmp1 = String.valueOf(HoaDonTable.getValueAt(r, 1));
                        String tmp2 = String.valueOf(HoaDonTable.getValueAt(r, 2));
                        Date tmp3 = Date.valueOf(String.valueOf(HoaDonTable.getValueAt(r, 3)));
                        double tmp4 = Double.parseDouble(String.valueOf(HoaDonTable.getValueAt(r, 4)));
                        double tmp5 = Double.parseDouble(String.valueOf(HoaDonTable.getValueAt(r, 5)));
                        double tmp6 = Double.parseDouble(String.valueOf(HoaDonTable.getValueAt(r, 6)));
                        double tmp7 = Double.parseDouble(String.valueOf(HoaDonTable.getValueAt(r, 7)));
                        String tmp8 = String.valueOf(HoaDonTable.getValueAt(r, 8));

                        int kq1 = 0;
                        for (ChiTiet_DV_ThanhToan ctdvtt : lstCTDVTT) {
                            if (ctdvtt.getMaHoaDon().equals(tmp1)) {
                                kq1 = ChiTiet_DV_ThanhToanDAO.getInstance().Delete(ctdvtt);
                            }
                        }

                        try {
                            kq = HoaDonDAO.getInstance().Delete(new HoaDon(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, tmp7, tmp8));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (kq < 0) {
                            Message("Xoá dữ liệu " + tmp1 + " thất bại!", JOptionPane.ERROR_MESSAGE);
                        }
                        kq = 1;
                    }
                }
                if (kq > 0) {
                    ReloadTableHD();
                    defaultTableCTDVTT.setRowCount(0);
                } else {
                    Message("Xoá dữ liệu thất bại!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable HoaDonTable;
    private javax.swing.JPanel Page1;
    private javax.swing.JButton btnDelete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblCTDVTT;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
