/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import dao.ChiTietDichVuDAO;
import dao.ChiTietMonAnDAO;
import dao.PhieuDatTiecCuoiDAO;
import java.awt.Color;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import model.ChiTietDichVu;
import model.ChiTietMonAn;
import model.PhieuDatTiecCuoi;

/**
 *
 * @author Asus
 */
public class WeddingPartyList extends javax.swing.JInternalFrame {

    /**
     * Creates new form WeddingPartyList
     */
    private DefaultTableModel defaulTablePDTC;
    private DefaultTableModel defaultTableCTDV;
    private DefaultTableModel defaultTableCTMA;
    private ArrayList<PhieuDatTiecCuoi> lstPDTC = PhieuDatTiecCuoiDAO.getInstance().SelectAllTC();
    private ArrayList<ChiTietDichVu> lstCTDV = ChiTietDichVuDAO.getInstance().SelectAllDV();
    private ArrayList<ChiTietMonAn> lstCTMA = ChiTietMonAnDAO.getInstance().SelectAllMA();

    public WeddingPartyList() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        DatTiecTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        DatTiecTable.getTableHeader().setOpaque(false);
        DatTiecTable.getTableHeader().setBackground(new Color(243,246,249));
        
        tblCTDV.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        tblCTDV.getTableHeader().setOpaque(false);
        tblCTDV.getTableHeader().setBackground(new Color(243,246,249));
        
        tblCTMA.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        tblCTMA.getTableHeader().setOpaque(false);
        tblCTMA.getTableHeader().setBackground(new Color(243,246,249));
        CreateTablePDTC();
        DatTiecTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        DatTiecTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        DatTiecTable.getColumnModel().getColumn(1).setPreferredWidth(80);
        DatTiecTable.getColumnModel().getColumn(2).setPreferredWidth(165);
        DatTiecTable.getColumnModel().getColumn(3).setPreferredWidth(165);
        DatTiecTable.getColumnModel().getColumn(4).setPreferredWidth(165);
        DatTiecTable.getColumnModel().getColumn(5).setPreferredWidth(165);
        DatTiecTable.getColumnModel().getColumn(6).setPreferredWidth(165);
        DatTiecTable.getColumnModel().getColumn(7).setPreferredWidth(165);
        DatTiecTable.getColumnModel().getColumn(8).setPreferredWidth(165);
        DatTiecTable.getColumnModel().getColumn(9).setPreferredWidth(165);
        DatTiecTable.getColumnModel().getColumn(10).setPreferredWidth(165);
        DatTiecTable.getColumnModel().getColumn(11).setPreferredWidth(165);
        DatTiecTable.getColumnModel().getColumn(12).setPreferredWidth(165);
        DatTiecTable.getColumnModel().getColumn(13).setPreferredWidth(165);
        DatTiecTable.getColumnModel().getColumn(14).setPreferredWidth(165);
        DatTiecTable.getColumnModel().getColumn(15).setPreferredWidth(165);
        DatTiecTable.getColumnModel().getColumn(16).setPreferredWidth(165);
        DatTiecTable.getColumnModel().getColumn(17).setPreferredWidth(165);

    }

    public void CreateTablePDTC() {
        NumberFormat currencyFormatVN = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        defaulTablePDTC = (DefaultTableModel) DatTiecTable.getModel();
        String[] tieuDe = {"STT", "Mã tiệc cưới", "Ngày đặt", "Ngày đãi tiệc", "Số lượng bàn", "Số bàn dự trữ",
            "Đơn giá bàn", "Tổng tiền bàn", "Tổng tiền dịch vụ", "Tổng tiền đặt tiệc", "Tiền đặt cọc", "Còn lại", "Tên cô dâu", "Tên chú rể",
            "SĐT", "Mã ca", "Mã sảnh", "Username"};

        defaulTablePDTC.setColumnIdentifiers(tieuDe);
        
        int i = 0;
        for (PhieuDatTiecCuoi pd : lstPDTC) {
            defaulTablePDTC.addRow(new Object[]{++i, pd.getMaTiecCuoi(), pd.getNgayDat(), pd.getNgayDaiTiec(), pd.getSoLuongBan(), pd.getSoLuongBanDuTru(),
                currencyFormatVN.format(pd.getDonGiaBan()), currencyFormatVN.format(pd.getTongTienBan()), currencyFormatVN.format(pd.getTongTienDichVu()), currencyFormatVN.format(pd.getTongTienDatTiec()), currencyFormatVN.format(pd.getTienDatCoc()), currencyFormatVN.format(pd.getConLai()), pd.getTenCoDau(), pd.getTenChuRe(),
                pd.getSdt(), pd.getMaCa(), pd.getMaSanh(), pd.getUserName()});
        }
    }

    public void ReloadTablePDTC() {
        lstPDTC = PhieuDatTiecCuoiDAO.getInstance().SelectAllTC();
        defaulTablePDTC.setRowCount(0);
        CreateTablePDTC();
    }

    public void CreateTableCTDV() {
        NumberFormat currencyFormatVN = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        int row = DatTiecTable.getSelectedRow();
        String maPDTC = String.valueOf(DatTiecTable.getValueAt(row, 1));
        defaultTableCTDV = (DefaultTableModel) tblCTDV.getModel();
        int i = 0;
        for (ChiTietDichVu ctdv : lstCTDV) {
            if (ctdv.getMaTiecCuoi().equals(maPDTC)) {
                defaultTableCTDV.addRow(new Object[]{++i, ctdv.getMaDichVu(), ctdv.getSoLuong(), currencyFormatVN.format(ctdv.getDonGiaDichVu()), currencyFormatVN.format(ctdv.getThanhTien())});
            }
        }
    }

    public void CreateTableCTMA() {
        NumberFormat currencyFormatVN = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        int row = DatTiecTable.getSelectedRow();
        String maPDTC = String.valueOf(DatTiecTable.getValueAt(row, 1));
        defaultTableCTMA = (DefaultTableModel) tblCTMA.getModel();
        int i = 0;
        for (ChiTietMonAn ctma : lstCTMA) {
            if (ctma.getMaTiecCuoi().equals(maPDTC)) {
                defaultTableCTMA.addRow(new Object[]{++i, ctma.getMaMonAn(), currencyFormatVN.format(ctma.getDonGiaMonAn()), ctma.getSoLuong(), ctma.getGhiChu()});
            }
        }
    }

    public void SearchTable() {
        defaulTablePDTC.setRowCount(0);
        defaulTablePDTC = (DefaultTableModel) DatTiecTable.getModel();
        int i = 0;
        String value = txtSearch.getText();
        for (PhieuDatTiecCuoi pd : lstPDTC) {
            System.out.println();
            if (pd.getUserName().toLowerCase().contains(value.toLowerCase()) || pd.getTenChuRe().toLowerCase().contains(value.toLowerCase()) || pd.getTenCoDau().toLowerCase().contains(value.toLowerCase())) {
                defaulTablePDTC.addRow(new Object[]{++i, pd.getMaTiecCuoi(), pd.getNgayDat(), pd.getNgayDaiTiec(), pd.getSoLuongBan(), pd.getSoLuongBanDuTru(),
                    pd.getDonGiaBan(), pd.getTongTienBan(), pd.getTongTienDichVu(), pd.getTongTienDatTiec(), pd.getTienDatCoc(), pd.getConLai(), pd.getTenCoDau(), pd.getTenChuRe(),
                    pd.getSdt(), pd.getMaCa(), pd.getMaSanh(), pd.getUserName()});
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
        DatTiecTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCTMA = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCTDV = new javax.swing.JTable();

        Page1.setBackground(new java.awt.Color(255, 255, 255));
        Page1.setPreferredSize(new java.awt.Dimension(1170, 730));

        DatTiecTable.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        DatTiecTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã tiệc cưới", "Tên chú rể", "Tên cô dâu", "Tên Sảnh", "Ngày đãi tiệc", "Giờ", "Số lượng bàn", "UserName"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        DatTiecTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        DatTiecTable.setFocusable(false);
        DatTiecTable.setRowHeight(25);
        DatTiecTable.setSelectionBackground(new java.awt.Color(69, 96, 134));
        DatTiecTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DatTiecTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(DatTiecTable);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(69, 96, 134));
        jLabel1.setText("DANH SÁCH ĐẶT TIỆC");

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
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                SearchTable();
            }
            public void removeUpdate(DocumentEvent e) {
                if(txtSearch.getText().equals("")){
                    defaulTablePDTC.setRowCount(0);
                    CreateTablePDTC();
                }else{
                    SearchTable();
                }
            }
            public void insertUpdate(DocumentEvent e) {
                SearchTable();
            }
        });

        tblCTMA.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblCTMA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã món ăn ", "Đơn giá món ăn", "Số lượng ", "Ghi chú"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCTMA.setRowHeight(25);
        jScrollPane2.setViewportView(tblCTMA);

        tblCTDV.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblCTDV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã dịch vụ", "Số lượng ", "Đơn giá dịch vụ", "Thành tiền "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCTDV.setRowHeight(25);
        jScrollPane3.setViewportView(tblCTDV);

        javax.swing.GroupLayout Page1Layout = new javax.swing.GroupLayout(Page1);
        Page1.setLayout(Page1Layout);
        Page1Layout.setHorizontalGroup(
            Page1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Page1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Page1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Page1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Page1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Page1Layout.createSequentialGroup()
                        .addGap(0, 43, Short.MAX_VALUE)
                        .addGroup(Page1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1082, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(Page1Layout.createSequentialGroup()
                                .addComponent(btnDelete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(51, 51, 51))))
        );
        Page1Layout.setVerticalGroup(
            Page1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Page1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(Page1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Page1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(108, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1182, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Page1, javax.swing.GroupLayout.DEFAULT_SIZE, 1182, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 775, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Page1, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DatTiecTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DatTiecTableMouseClicked
        // TODO add your handling code here:
        defaultTableCTDV = (DefaultTableModel) tblCTDV.getModel();
        defaultTableCTMA = (DefaultTableModel) tblCTMA.getModel();
        defaultTableCTDV.setRowCount(0);
        defaultTableCTMA.setRowCount(0);
        CreateTableCTDV();
        CreateTableCTMA();

    }//GEN-LAST:event_DatTiecTableMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int row = DatTiecTable.getSelectedRow();
        int[] rows = DatTiecTable.getSelectedRows();
        if (row < 0) {
            Message("Vui lòng chọn dữ liệu muốn xoá!", JOptionPane.INFORMATION_MESSAGE);
        } else {

            String mess = "";
            if (rows.length == 1) {
                mess = String.valueOf(DatTiecTable.getValueAt(row, 1)) + " ";
            } else {
                for (int r : rows) {
                    mess += String.valueOf(DatTiecTable.getValueAt(r, 1)) + " ";
                }
            }

            int x = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá " + mess + "hay không?");
            if (x == JOptionPane.YES_OPTION) {
                int kq = 0;
                if (rows.length == 1) {
                    String tmp1 = String.valueOf(DatTiecTable.getValueAt(row, 1));
                    String tmp2 = String.valueOf(DatTiecTable.getValueAt(row, 2));
                    String tmp3 = String.valueOf(DatTiecTable.getValueAt(row, 3));
                    int tmp4 = Integer.parseInt(String.valueOf(DatTiecTable.getValueAt(row, 4)));
                    int tmp5 = Integer.parseInt(String.valueOf(DatTiecTable.getValueAt(row, 5)));
                    long tmp6 = Long.parseLong(String.valueOf(DatTiecTable.getValueAt(row, 6)));
                    long tmp7 = Long.parseLong(String.valueOf(DatTiecTable.getValueAt(row, 7)));
                    long tmp8 = Long.parseLong(String.valueOf(DatTiecTable.getValueAt(row, 8)));
                    long tmp9 = Long.parseLong(String.valueOf(DatTiecTable.getValueAt(row, 9)));
                    long tmp10 = Long.parseLong(String.valueOf(DatTiecTable.getValueAt(row, 10)));
                    long tmp11 = Long.parseLong(String.valueOf(DatTiecTable.getValueAt(row, 11)));
                    String tmp12 = String.valueOf(DatTiecTable.getValueAt(row, 12));
                    String tmp13 = String.valueOf(DatTiecTable.getValueAt(row, 13));
                    String tmp14 = String.valueOf(DatTiecTable.getValueAt(row, 14));
                    String tmp15 = String.valueOf(DatTiecTable.getValueAt(row, 15));
                    String tmp16 = String.valueOf(DatTiecTable.getValueAt(row, 16));
                    String tmp17 = String.valueOf(DatTiecTable.getValueAt(row, 17));

                    int kq1 = 0;
                    for (ChiTietDichVu ctdv : lstCTDV) {
                        if (ctdv.getMaTiecCuoi().equals(tmp1)) {
                            kq1 = ChiTietDichVuDAO.getInstance().Delete(ctdv);
                        }
                    }

                    int kq2 = 0;
                    for (ChiTietMonAn ctma : lstCTMA) {
                        if (ctma.getMaTiecCuoi().equals(tmp1)) {
                            kq2 = ChiTietMonAnDAO.getInstance().Delete(ctma);
                        }
                    }

                    try {
                        kq = PhieuDatTiecCuoiDAO.getInstance().Delete(new PhieuDatTiecCuoi(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, tmp7,
                                tmp8, tmp9, tmp10, tmp11, tmp12, tmp13, tmp14, tmp15, tmp16, tmp17));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    for (int r : rows) {
                        String tmp1 = String.valueOf(DatTiecTable.getValueAt(r, 1));
                        String tmp2 = String.valueOf(DatTiecTable.getValueAt(r, 2));
                        String tmp3 = String.valueOf(DatTiecTable.getValueAt(r, 3));
                        int tmp4 = Integer.parseInt(String.valueOf(DatTiecTable.getValueAt(r, 4)));
                        int tmp5 = Integer.parseInt(String.valueOf(DatTiecTable.getValueAt(r, 5)));
                        long tmp6 = Long.parseLong(String.valueOf(DatTiecTable.getValueAt(r, 6)));
                        long tmp7 = Long.parseLong(String.valueOf(DatTiecTable.getValueAt(r, 7)));
                        long tmp8 = Long.parseLong(String.valueOf(DatTiecTable.getValueAt(r, 8)));
                        long tmp9 = Long.parseLong(String.valueOf(DatTiecTable.getValueAt(r, 9)));
                        long tmp10 = Long.parseLong(String.valueOf(DatTiecTable.getValueAt(r, 10)));
                        long tmp11 = Long.parseLong(String.valueOf(DatTiecTable.getValueAt(r, 11)));
                        String tmp12 = String.valueOf(DatTiecTable.getValueAt(r, 12));
                        String tmp13 = String.valueOf(DatTiecTable.getValueAt(r, 13));
                        String tmp14 = String.valueOf(DatTiecTable.getValueAt(r, 14));
                        String tmp15 = String.valueOf(DatTiecTable.getValueAt(r, 15));
                        String tmp16 = String.valueOf(DatTiecTable.getValueAt(r, 16));
                        String tmp17 = String.valueOf(DatTiecTable.getValueAt(r, 17));

                        int kq1 = 0;
                        for (ChiTietDichVu ctdv : lstCTDV) {
                            if (ctdv.getMaTiecCuoi().equals(tmp1)) {
                                kq1 = ChiTietDichVuDAO.getInstance().Delete(ctdv);
                            }
                        }

                        int kq2 = 0;
                        for (ChiTietMonAn ctma : lstCTMA) {
                            if (ctma.getMaTiecCuoi().equals(tmp1)) {
                                kq2 = ChiTietMonAnDAO.getInstance().Delete(ctma);
                            }
                        }

                        try {
                            kq = PhieuDatTiecCuoiDAO.getInstance().Delete(new PhieuDatTiecCuoi(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6, tmp7,
                                    tmp8, tmp9, tmp10, tmp11, tmp12, tmp13, tmp14, tmp15, tmp16, tmp17));

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
                    ReloadTablePDTC();
                    defaultTableCTDV.setRowCount(0);
                    defaultTableCTMA.setRowCount(0);
                } else {
                    Message("Xoá dữ liệu thất bại!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable DatTiecTable;
    private javax.swing.JPanel Page1;
    private javax.swing.JButton btnDelete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblCTDV;
    private javax.swing.JTable tblCTMA;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
