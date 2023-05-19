/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import model.PhieuDatTiecCuoi;
import java.util.ArrayList;
import dao.CaDAO;
import dao.ChiTietDichVuDAO;
import dao.DichVuDAO;
import dao.HoaDonDAO;
import dao.SanhDAO;
import dao.PhieuDatTiecCuoiDAO;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;
import model.Ca;
import model.Sanh;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import model.ChiTietDichVu;
import model.DichVu;
import model.HoaDon;

/**
 *
 * @author macbookpro
 */
public class WeddingPartyLookup extends javax.swing.JInternalFrame {

    private DefaultTableModel defaultTableModelSearch;
    private ArrayList<PhieuDatTiecCuoi> pdtcs = PhieuDatTiecCuoiDAO.getInstance().SelectAll();
    private ArrayList<Sanh> sanhcbb = SanhDAO.getInstance().SelectAll();
    private ArrayList<Ca> cacbb = CaDAO.getInstance().SelectAll();
    private Map<String, String> mapTenLoaiSanh = new HashMap<>();
    private Map<String, String> mapGio = new HashMap<>();

    /**
     * Creates new form WeddingPartyLookup
     */
    public WeddingPartyLookup() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        PageTTHDTT.setVisible(false);
        PageThongTinDT.setVisible(false);
        PageXNDV.setVisible(false);
        String arr[];
        ArrayList<String> arrStr = new ArrayList<>();
        arrStr.add("Không");
        for (Sanh x : sanhcbb) {
            mapTenLoaiSanh.put(x.getMaSanh(), x.getTenSanh());
            arrStr.add(x.getTenSanh());
        }
        arr = arrStr.toArray(new String[0]);
        HallNameCbBox.setModel(new javax.swing.DefaultComboBoxModel<>(arr));
        ArrayList<String> arrStr0 = new ArrayList<>();
        arrStr0.add("Không");
        for (Ca x : cacbb) {
            mapGio.put(x.getMaCa(), String.valueOf(x.getGioBatDau()));
            arrStr0.add(String.valueOf(x.getGioBatDau()));
        }
        arr = arrStr0.toArray(new String[0]);
        TimeCbBox.setModel(new javax.swing.DefaultComboBoxModel<>(arr));

        //
        CreateTable();
        CreateTableADD_XNDV();
    }

    public void CreateTable() {
        defaultTableModelSearch = (DefaultTableModel) DatTiecTable.getModel();
        int i = 0;
        for (PhieuDatTiecCuoi x : pdtcs) {
//            Sanh sanh0 = SanhDAO.getInstance().SelectBy_Id(x.getMaSanh());
//            Ca ca0 = CaDAO.getInstance().SelectBy_Id(x.getMaCa());
            defaultTableModelSearch.addRow(new Object[]{++i, x.getMaTiecCuoi(), x.getTenChuRe(), x.getTenCoDau(), mapTenLoaiSanh.get(x.getMaSanh()), x.getNgayDaiTiec(), mapGio.get(x.getMaCa()), x.getSoLuongBan(), x.getUserName()});
        }
    }

    public void SearchTable(String groomName, String brideName, String hallName, String idWedding, String ReceptionDate, String time, int numberType) {
        defaultTableModelSearch.setRowCount(0);
        defaultTableModelSearch = (DefaultTableModel) DatTiecTable.getModel();
        int i = 0;
//        System.out.println("ten ch re:\n"+groomName +"co dau\n"+brideName +"ten sanh\n "+hallName +"id\n "+idWedding +"ngay\n "+ReceptionDate +"h\n "+time +" type\n"+numberType);
        if (numberType == 2) {
            ArrayList<PhieuDatTiecCuoi> pdtcDesc = PhieuDatTiecCuoiDAO.getInstance().SelectDesc();
            for (PhieuDatTiecCuoi x : pdtcDesc) {
                if (x.getMaTiecCuoi().toLowerCase().contains(idWedding.toLowerCase()) && x.getTenCoDau().toLowerCase().contains(brideName.toLowerCase())
                        && x.getTenChuRe().toLowerCase().contains(groomName.toLowerCase()) && x.getNgayDaiTiec().toLowerCase().contains(ReceptionDate.toLowerCase())) {
                    System.out.println(i);
//                    Sanh sanh0 = SanhDAO.getInstance().SelectBy_Id(x.getMaSanh());
//                    Ca ca0 = CaDAO.getInstance().SelectBy_Id(x.getMaCa());
                    System.out.println(x.getMaSanh());
                    System.out.println(x.getMaCa());
                    if (mapTenLoaiSanh.get(x.getMaSanh()).toLowerCase().contains(hallName.toLowerCase())
                            && mapGio.get(x.getMaCa()).toLowerCase().contains(time.toLowerCase())) {
                        defaultTableModelSearch.addRow(new Object[]{++i, x.getMaTiecCuoi(), x.getTenChuRe(), x.getTenCoDau(), mapTenLoaiSanh.get(x.getMaSanh()), x.getNgayDaiTiec(), mapGio.get(x.getMaCa()), x.getSoLuongBan(), x.getUserName()});
                    }
                }
            }
        }
        if (numberType == 1) {
            ArrayList<PhieuDatTiecCuoi> pdtcAsc = PhieuDatTiecCuoiDAO.getInstance().SelectAsc();
            for (PhieuDatTiecCuoi x : pdtcAsc) {
                if (x.getMaTiecCuoi().toLowerCase().contains(idWedding.toLowerCase()) && x.getTenCoDau().toLowerCase().contains(brideName.toLowerCase())
                        && x.getTenChuRe().toLowerCase().contains(groomName.toLowerCase()) && x.getNgayDaiTiec().toLowerCase().contains(ReceptionDate.toLowerCase())) {
//                    Sanh sanh0 = SanhDAO.getInstance().SelectBy_Id(x.getMaSanh());
//                    Ca ca0 = CaDAO.getInstance().SelectBy_Id(x.getMaCa());
                    if (mapTenLoaiSanh.get(x.getMaSanh()).toLowerCase().contains(hallName.toLowerCase())
                            && mapGio.get(x.getMaCa()).toLowerCase().contains(time.toLowerCase())) {
                        defaultTableModelSearch.addRow(new Object[]{++i, x.getMaTiecCuoi(), x.getTenChuRe(), x.getTenCoDau(), mapTenLoaiSanh.get(x.getMaSanh()), x.getNgayDaiTiec(), mapGio.get(x.getMaCa()), x.getSoLuongBan(), x.getUserName()});
                    }
                }
            }
        }
        if (numberType == 0) {
            for (PhieuDatTiecCuoi x : pdtcs) {
                if (x.getMaTiecCuoi().toLowerCase().contains(idWedding.toLowerCase()) && x.getTenCoDau().toLowerCase().contains(brideName.toLowerCase())
                        && x.getTenChuRe().toLowerCase().contains(groomName.toLowerCase()) && x.getNgayDaiTiec().toLowerCase().contains(ReceptionDate.toLowerCase())) {
//                    Sanh sanh0 = SanhDAO.getInstance().SelectBy_Id(x.getMaSanh());
//                    Ca ca0 = CaDAO.getInstance().SelectBy_Id(x.getMaCa());
                    if (mapTenLoaiSanh.get(x.getMaSanh()).toLowerCase().contains(hallName.toLowerCase())
                            && mapGio.get(x.getMaCa()).toLowerCase().contains(time.toLowerCase())) {
                        defaultTableModelSearch.addRow(new Object[]{++i, x.getMaTiecCuoi(), x.getTenChuRe(), x.getTenCoDau(), mapTenLoaiSanh.get(x.getMaSanh()), x.getNgayDaiTiec(), mapGio.get(x.getMaCa()), x.getSoLuongBan(), x.getUserName()});
                    }
                }
            }
        }
//        for(PhieuDatTiecCuoi x: pdtcs){
//            if(x.getMaTiecCuoi().toLowerCase().contains(idWedding.toLowerCase()) && x.getTenCoDau().toLowerCase().contains(brideName.toLowerCase()) &&
//                    x.getTenChuRe().toLowerCase().contains(groomName.toLowerCase()) && x.getSdt().toLowerCase().contains(phoneNumber.toLowerCase()) &&
//                    x.getNgayDat().toLowerCase().contains(bookingDate.toLowerCase()) && x.getNgayDaiTiec().toLowerCase().contains(ReceptionDate.toLowerCase()))
//            {
//                Sanh sanh0 = SanhDAO.getInstance().SelectBy_Id(x.getMaSanh());
//                Ca ca0 = CaDAO.getInstance().SelectBy_Id(x.getMaCa());
//                defaultTableModelSearch.addRow(new Object[]{++i, x.getMaTiecCuoi(), x.getTenChuRe(),x.getTenCoDau(), x.getMaSanh(), x.getNgayDat(), x.getNgayDaiTiec()});
//            }
//        }
    }

    public void Message(String message, int messageType) {
        JOptionPane jOptionPane = new JOptionPane(message, messageType);
        JDialog dialog = jOptionPane.createDialog(null, "Message");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    public String YYYY_MM_DD(int year, int month, int day) {
        String m;
        String d;
        if (month < 10) {
            m = "0" + month;
        } else {
            m = String.valueOf(month);
        }
        if (day < 10) {
            d = "0" + day;
        } else {
            d = String.valueOf(day);
        }
        return year + "-" + m + "-" + d;
    }

    public void ReloadDataTable() {
        pdtcs = PhieuDatTiecCuoiDAO.getInstance().SelectAll();
        defaultTableModelSearch.setRowCount(0);
        CreateTable();
    }

    public int getSelectedRow() {
        return DatTiecTable.getSelectedRow();
    }
    // Code danh cho lay du lieu len bang XNDV (Chuc nang Thanh Toan)
    private DefaultTableModel defaultTableXNDV;
    private DefaultTableModel defaultTableDV;
    private ArrayList<ChiTietDichVu> lstDetailServices = ChiTietDichVuDAO.getInstance().SelectAll();
    private ArrayList<HoaDon> lstHoaDon = HoaDonDAO.getInstance().SelectAll();
    private ArrayList<DichVu> lstDichVu = DichVuDAO.getInstance().SelectXNDV();

    public void CreateTableADD_XNDV() {
        defaultTableDV = (DefaultTableModel) tblSelectService.getModel();
        int i = 0;
        for (DichVu dv : lstDichVu) {
            defaultTableDV.addRow(new Object[]{++i, dv.getMaDichVu(), dv.getTenDichVu(), dv.getDonGia()});
        }
    }

    public void ReloadDataAdd_XNDV() {
        lstDichVu = DichVuDAO.getInstance().SelectXNDV();
        defaultTableDV.setRowCount(0);
        defaultTableDV = (DefaultTableModel) tblSelectService.getModel();
        int i = 0;

        for (DichVu dv : lstDichVu) {
            defaultTableDV.addRow(new Object[]{++i, dv.getMaDichVu(), dv.getTenDichVu(), dv.getDonGia()});
        }
    }

    public void CreateTableXNDV() {
        int selectRow = getSelectedRow();
        String maPDTC = String.valueOf(DatTiecTable.getValueAt(selectRow, 1));
        defaultTableXNDV = (DefaultTableModel) DVSVTable.getModel();
        int i = 0;

        for (ChiTietDichVu ct : lstDetailServices) {
            if (ct.getMaTiecCuoi().equals(maPDTC)) {
                defaultTableXNDV.addRow(new Object[]{++i, ct.getMaDichVu(), ct.getTenDichVu(), ct.getSoLuong(), ct.getDonGiaDichVu()});
            }
        }
    }

    public void ReloadDataXNDV() {
        lstDetailServices = ChiTietDichVuDAO.getInstance().SelectAll();
        defaultTableXNDV.setRowCount(0);
        int selectRow = getSelectedRow();
        String maPDTC = String.valueOf(DatTiecTable.getValueAt(selectRow, 1));
        defaultTableXNDV = (DefaultTableModel) DVSVTable.getModel();
        int i = 0;

        for (ChiTietDichVu ct : lstDetailServices) {
            if (ct.getMaTiecCuoi().equals(maPDTC)) {
                defaultTableXNDV.addRow(new Object[]{++i, ct.getMaDichVu(), ct.getTenDichVu(), ct.getSoLuong(), ct.getDonGiaDichVu()});
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ThanhToanJDialog = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jdNgayThanhToan = new com.toedter.calendar.JDateChooser();
        HuyTTJDialog = new javax.swing.JButton();
        TiepTucTTJDialog = new javax.swing.JButton();
        HuyDatTiecJDialog = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        TiepTucHJDialog = new javax.swing.JButton();
        HuyHJDialog = new javax.swing.JButton();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        AddSelectServices = new javax.swing.JDialog();
        jPanel28 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblSelectService = new javax.swing.JTable();
        btnXacNhanDV = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        Page1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DatTiecTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnXemCT = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        GroomNameText = new javax.swing.JTextField();
        BrideNameText = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        ReceptionDate = new com.toedter.calendar.JDateChooser();
        IdWeddingText = new javax.swing.JTextField();
        SearchBt = new javax.swing.JButton();
        RetypeBt = new javax.swing.JButton();
        jLabel52 = new javax.swing.JLabel();
        HallNameCbBox = new javax.swing.JComboBox<>();
        jLabel54 = new javax.swing.JLabel();
        TimeCbBox = new javax.swing.JComboBox<>();
        jLabel56 = new javax.swing.JLabel();
        SortCbBox = new javax.swing.JComboBox<>();
        PageThongTinDT = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        MaDTValue = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        TenCRValue = new javax.swing.JLabel();
        TenCDValue = new javax.swing.JLabel();
        SDTValue = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        NgayDTValue = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        NgayDaiTiecValue = new javax.swing.JLabel();
        CaValue = new javax.swing.JLabel();
        SanhValue = new javax.swing.JLabel();
        SoLuongBanValue = new javax.swing.JLabel();
        SoLuongBanDTValue = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        DonGiaBanValue = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        TongTienBanValue = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        TongTienDVValue = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        TienCocValue = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        TongTienHDValue = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        ConLaiValue = new javax.swing.JLabel();
        BackPageTTDT = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        MonAnTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        DichVuTable = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        PageTTHDTT = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        TenCRValueTT = new javax.swing.JLabel();
        TenCDValueTT = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        NgayDTValueTT = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        NgayTTValueTT = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        SoLuongBanValueTT = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        DonGiaBanValueTT = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        TongTienBanValueTT = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        DVHHTable = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        TongTienDVValueTT = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        TongTienHDValueTT = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        TienPhatValueTT = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        TienCocValueTT = new javax.swing.JLabel();
        ConLaiValueTT = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        BackPageTTDT1 = new javax.swing.JButton();
        BackPageTTHDTT = new javax.swing.JButton();
        PageXNDV = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        btnAddXNDV = new javax.swing.JButton();
        btnDeleteXNDV = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        DVSVTable = new javax.swing.JTable();
        BackPageXNDV = new javax.swing.JButton();
        NextPageXNDV = new javax.swing.JButton();
        PageTTHDH = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        NgayHuyValueH = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        TenChuReValueH = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        TenCoDauValueH = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        NgayDTValueH = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        SoLuongBanValueH = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        DonGiaBanValueH = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        TongTienBanValueH = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        TienCocValueH = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        ConLaiValueH = new javax.swing.JLabel();
        TongTenHHValueH = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        NextPageXNDV1 = new javax.swing.JButton();
        BackPageTTHDH = new javax.swing.JButton();

        ThanhToanJDialog.setBackground(new java.awt.Color(255, 255, 255));
        ThanhToanJDialog.setModal(true);
        ThanhToanJDialog.setSize(new java.awt.Dimension(400, 300));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 300));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel4.setText("Ngày thanh toan");

        jdNgayThanhToan.setDateFormatString("yyyy-MM-dd");
        jdNgayThanhToan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        HuyTTJDialog.setBackground(new java.awt.Color(69, 96, 134));
        HuyTTJDialog.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        HuyTTJDialog.setForeground(new java.awt.Color(255, 255, 255));
        HuyTTJDialog.setText("Huỷ");
        HuyTTJDialog.setPreferredSize(new java.awt.Dimension(90, 40));
        HuyTTJDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HuyTTJDialogActionPerformed(evt);
            }
        });

        TiepTucTTJDialog.setBackground(new java.awt.Color(132, 70, 133));
        TiepTucTTJDialog.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        TiepTucTTJDialog.setForeground(new java.awt.Color(255, 255, 255));
        TiepTucTTJDialog.setText("Tiếp tục");
        TiepTucTTJDialog.setPreferredSize(new java.awt.Dimension(90, 40));
        TiepTucTTJDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TiepTucTTJDialogActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdNgayThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(HuyTTJDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                        .addComponent(TiepTucTTJDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(56, 56, 56))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jdNgayThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HuyTTJDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TiepTucTTJDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68))
        );

        javax.swing.GroupLayout ThanhToanJDialogLayout = new javax.swing.GroupLayout(ThanhToanJDialog.getContentPane());
        ThanhToanJDialog.getContentPane().setLayout(ThanhToanJDialogLayout);
        ThanhToanJDialogLayout.setHorizontalGroup(
            ThanhToanJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(ThanhToanJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ThanhToanJDialogLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ThanhToanJDialogLayout.setVerticalGroup(
            ThanhToanJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(ThanhToanJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ThanhToanJDialogLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        HuyDatTiecJDialog.setModal(true);
        HuyDatTiecJDialog.setSize(new java.awt.Dimension(400, 300));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(400, 300));

        TiepTucHJDialog.setBackground(new java.awt.Color(132, 70, 133));
        TiepTucHJDialog.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        TiepTucHJDialog.setForeground(new java.awt.Color(255, 255, 255));
        TiepTucHJDialog.setText("Tiếp tục");
        TiepTucHJDialog.setPreferredSize(new java.awt.Dimension(90, 40));
        TiepTucHJDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TiepTucHJDialogActionPerformed(evt);
            }
        });

        HuyHJDialog.setBackground(new java.awt.Color(69, 96, 134));
        HuyHJDialog.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        HuyHJDialog.setForeground(new java.awt.Color(255, 255, 255));
        HuyHJDialog.setText("Huỷ");
        HuyHJDialog.setPreferredSize(new java.awt.Dimension(90, 40));
        HuyHJDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HuyHJDialogActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel5.setText("Ngày huỷ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(HuyHJDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                        .addComponent(TiepTucHJDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(56, 56, 56))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HuyHJDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TiepTucHJDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68))
        );

        javax.swing.GroupLayout HuyDatTiecJDialogLayout = new javax.swing.GroupLayout(HuyDatTiecJDialog.getContentPane());
        HuyDatTiecJDialog.getContentPane().setLayout(HuyDatTiecJDialogLayout);
        HuyDatTiecJDialogLayout.setHorizontalGroup(
            HuyDatTiecJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(HuyDatTiecJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(HuyDatTiecJDialogLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        HuyDatTiecJDialogLayout.setVerticalGroup(
            HuyDatTiecJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(HuyDatTiecJDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(HuyDatTiecJDialogLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        AddSelectServices.setMinimumSize(new java.awt.Dimension(900, 530));
        AddSelectServices.setModal(true);

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));

        tblSelectService.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        tblSelectService.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã dịch vụ ", "Tên dịch vụ", "Đơn giá "
            }
        ));
        tblSelectService.setFocusable(false);
        tblSelectService.setRowHeight(25);
        tblSelectService.setSelectionBackground(new java.awt.Color(69, 96, 134));
        tblSelectService.setShowGrid(false);
        jScrollPane6.setViewportView(tblSelectService);
        if (tblSelectService.getColumnModel().getColumnCount() > 0) {
            tblSelectService.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblSelectService.getColumnModel().getColumn(2).setPreferredWidth(150);
        }

        btnXacNhanDV.setBackground(new java.awt.Color(132, 70, 133));
        btnXacNhanDV.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnXacNhanDV.setForeground(new java.awt.Color(255, 255, 255));
        btnXacNhanDV.setText("Xác nhận");
        btnXacNhanDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanDVActionPerformed(evt);
            }
        });

        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(69, 96, 134));
        jLabel45.setText("Danh sách lựa chọn dịch vụ ");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnXacNhanDV, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(224, 224, 224))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXacNhanDV, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout AddSelectServicesLayout = new javax.swing.GroupLayout(AddSelectServices.getContentPane());
        AddSelectServices.getContentPane().setLayout(AddSelectServicesLayout);
        AddSelectServicesLayout.setHorizontalGroup(
            AddSelectServicesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddSelectServicesLayout.createSequentialGroup()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        AddSelectServicesLayout.setVerticalGroup(
            AddSelectServicesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddSelectServicesLayout.createSequentialGroup()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setPreferredSize(new java.awt.Dimension(1170, 730));

        Page1.setBackground(new java.awt.Color(255, 255, 255));

        DatTiecTable.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        DatTiecTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã tiệc cưới", "Tên chú rể", "Tên cô dâu", "Tên Sảnh", "Ngày đãi tiệc", "Giờ", "Số lượng bàn", "UserName"
            }
        ));
        DatTiecTable.setFocusable(false);
        DatTiecTable.setRowHeight(25);
        DatTiecTable.setSelectionBackground(new java.awt.Color(69, 96, 134));
        jScrollPane1.setViewportView(DatTiecTable);
        if (DatTiecTable.getColumnModel().getColumnCount() > 0) {
            DatTiecTable.getColumnModel().getColumn(0).setMinWidth(40);
            DatTiecTable.getColumnModel().getColumn(0).setPreferredWidth(40);
            DatTiecTable.getColumnModel().getColumn(0).setMaxWidth(40);
            DatTiecTable.getColumnModel().getColumn(1).setMinWidth(100);
            DatTiecTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            DatTiecTable.getColumnModel().getColumn(1).setMaxWidth(100);
            DatTiecTable.getColumnModel().getColumn(5).setMinWidth(100);
            DatTiecTable.getColumnModel().getColumn(5).setPreferredWidth(100);
            DatTiecTable.getColumnModel().getColumn(5).setMaxWidth(100);
            DatTiecTable.getColumnModel().getColumn(6).setMinWidth(70);
            DatTiecTable.getColumnModel().getColumn(6).setPreferredWidth(70);
            DatTiecTable.getColumnModel().getColumn(6).setMaxWidth(70);
            DatTiecTable.getColumnModel().getColumn(7).setMinWidth(110);
            DatTiecTable.getColumnModel().getColumn(7).setPreferredWidth(110);
            DatTiecTable.getColumnModel().getColumn(7).setMaxWidth(110);
            DatTiecTable.getColumnModel().getColumn(8).setMinWidth(100);
            DatTiecTable.getColumnModel().getColumn(8).setPreferredWidth(100);
            DatTiecTable.getColumnModel().getColumn(8).setMaxWidth(100);
        }
        DatTiecTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        DatTiecTable.getTableHeader().setOpaque(false);
        DatTiecTable.getTableHeader().setBackground(new Color(243,246,249));
        DatTiecTable.setDefaultEditor(Object.class, null);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(69, 96, 134));
        jLabel1.setText("DANH SÁCH ĐẶT TIỆC");

        btnXemCT.setBackground(new java.awt.Color(248, 189, 141));
        btnXemCT.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnXemCT.setForeground(new java.awt.Color(255, 255, 255));
        btnXemCT.setText("Xem chi tiết");
        btnXemCT.setToolTipText("");
        btnXemCT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemCTActionPerformed(evt);
            }
        });

        btnHuy.setBackground(new java.awt.Color(132, 70, 133));
        btnHuy.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setText("Huỷ");
        btnHuy.setToolTipText("");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        btnThanhToan.setBackground(new java.awt.Color(99, 122, 48));
        btnThanhToan.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.setToolTipText("");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel23.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel23.setText("Mã tiệc cưới:");

        jLabel24.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel24.setText("Tên chú rể:");

        jLabel41.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel41.setText("Tên cô dâu:");

        GroomNameText.setMinimumSize(new java.awt.Dimension(64, 33));
        GroomNameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GroomNameTextActionPerformed(evt);
            }
        });

        BrideNameText.setMinimumSize(new java.awt.Dimension(64, 33));
        BrideNameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BrideNameTextActionPerformed(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel50.setText("Ngày đãi tiệc:");

        IdWeddingText.setMinimumSize(new java.awt.Dimension(64, 33));
        IdWeddingText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IdWeddingTextActionPerformed(evt);
            }
        });

        SearchBt.setBackground(new java.awt.Color(255, 204, 204));
        SearchBt.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        SearchBt.setText("Tra Cứu");
        SearchBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBtActionPerformed(evt);
            }
        });

        RetypeBt.setBackground(new java.awt.Color(0, 51, 255));
        RetypeBt.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        RetypeBt.setForeground(new java.awt.Color(255, 255, 255));
        RetypeBt.setText("Nhập lại");
        RetypeBt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RetypeBtActionPerformed(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel52.setText("Sảnh:");

        HallNameCbBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel54.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel54.setText("Giờ:");

        TimeCbBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel56.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel56.setText("Số lượng bàn:");

        SortCbBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Không sắp xếp", "Sắp xếp tăng dần", "Sắp xếp giảm dần" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(GroomNameText, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                        .addComponent(BrideNameText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(IdWeddingText, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel50))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ReceptionDate, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(HallNameCbBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(TimeCbBox, 0, 160, Short.MAX_VALUE)
                        .addGap(99, 99, 99)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(SearchBt, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(RetypeBt, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SortCbBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(19, 19, 19))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(HallNameCbBox, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(SortCbBox, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ReceptionDate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(TimeCbBox, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(RetypeBt, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(SearchBt, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(GroomNameText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BrideNameText, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IdWeddingText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
        );

        javax.swing.GroupLayout Page1Layout = new javax.swing.GroupLayout(Page1);
        Page1.setLayout(Page1Layout);
        Page1Layout.setHorizontalGroup(
            Page1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Page1Layout.createSequentialGroup()
                .addGroup(Page1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(Page1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXemCT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThanhToan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnHuy))
                    .addGroup(Page1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(Page1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(Page1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(Page1Layout.createSequentialGroup()
                                    .addGap(50, 50, 50)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1058, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Page1Layout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(336, 336, 336)))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        Page1Layout.setVerticalGroup(
            Page1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Page1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(Page1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXemCT, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        PageThongTinDT.setBackground(new java.awt.Color(255, 255, 255));
        PageThongTinDT.setPreferredSize(new java.awt.Dimension(1170, 730));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(69, 96, 134));
        jLabel2.setText("THÔNG TIN ĐẶT TIỆC");
        jPanel8.add(jLabel2);

        MaDTValue.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel6.setText("Mã phiếu đặt tiệc:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(MaDTValue)
                .addContainerGap())
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                    .addContainerGap(16, Short.MAX_VALUE)
                    .addComponent(jLabel6)
                    .addGap(14, 14, 14)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MaDTValue)
                .addContainerGap())
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel6)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jLabel3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel3.setText("Thông tin khách hàng:");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel7.setText("Tên chú rể:");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel8.setText("Tên cô dâu:");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel9.setText("SĐT:");

        TenCRValue.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        TenCDValue.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        SDTValue.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(TenCRValue)
                .addGap(292, 292, 292)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(TenCDValue)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(SDTValue)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(TenCRValue)
                    .addComponent(TenCDValue)
                    .addComponent(SDTValue))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        NgayDTValue.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel11.setText("Ngày đặt tiệc:");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(NgayDTValue)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(NgayDTValue))
                .addContainerGap())
        );

        jLabel12.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel12.setText("Thông tin tiêc cưới:");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel13.setText("Ngày đãi tiệc:");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel14.setText("Ca:");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel15.setText("Sảnh:");

        NgayDaiTiecValue.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        CaValue.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        SanhValue.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        SoLuongBanValue.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        SoLuongBanValue.setText("Số lượng bàn:");

        SoLuongBanDTValue.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        SoLuongBanDTValue.setText("Số lượng bàn dự trữ:");

        jLabel21.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel21.setText("Đơn giá bàn:");

        jLabel22.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        DonGiaBanValue.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel27.setText("Tổng tiền bàn:");

        TongTienBanValue.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel29.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel29.setText("Tổng tiền dịch vụ:");

        TongTienDVValue.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel31.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel31.setText("Tiền cọc:");

        TienCocValue.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel33.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel33.setText("Tổng tiền hoá đơn:");

        TongTienHDValue.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel35.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel35.setText("Tiền còn lại:");

        ConLaiValue.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SoLuongBanValue)
                            .addComponent(jLabel13))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(NgayDaiTiecValue)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addGap(50, 50, 50)
                        .addComponent(TongTienBanValue))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(18, 18, 18)
                        .addComponent(TongTienHDValue)))
                .addGap(142, 142, 142)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addGap(81, 81, 81)
                        .addComponent(ConLaiValue))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(42, 42, 42)
                        .addComponent(TongTienDVValue))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(143, 143, 143)
                        .addComponent(CaValue))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(SoLuongBanDTValue)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel25)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SanhValue)
                            .addComponent(DonGiaBanValue)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TienCocValue)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(jLabel14)
                        .addComponent(NgayDaiTiecValue)
                        .addComponent(CaValue))
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SanhValue)
                        .addComponent(jLabel15)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SoLuongBanValue)
                        .addComponent(jLabel22))
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SoLuongBanDTValue)
                        .addComponent(jLabel25))
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(DonGiaBanValue)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TongTienBanValue)
                    .addComponent(jLabel27)
                    .addComponent(jLabel29)
                    .addComponent(TongTienDVValue)
                    .addComponent(jLabel31)
                    .addComponent(TienCocValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel33)
                        .addComponent(TongTienHDValue))
                    .addComponent(jLabel35)
                    .addComponent(ConLaiValue))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BackPageTTDT.setBackground(new java.awt.Color(69, 96, 134));
        BackPageTTDT.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        BackPageTTDT.setForeground(new java.awt.Color(255, 255, 255));
        BackPageTTDT.setText("Quay lại");
        BackPageTTDT.setPreferredSize(new java.awt.Dimension(90, 40));
        BackPageTTDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackPageTTDTActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel16.setText("Danh sách món ăn");

        MonAnTable.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        MonAnTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên món ăn", "Loại", "Đơn giá", "Ghi chú", "Thành tiền"
            }
        ));
        MonAnTable.setFocusable(false);
        MonAnTable.setRowHeight(25);
        MonAnTable.setSelectionBackground(new java.awt.Color(69, 96, 134));
        jScrollPane2.setViewportView(MonAnTable);
        if (MonAnTable.getColumnModel().getColumnCount() > 0) {
            MonAnTable.getColumnModel().getColumn(0).setMinWidth(50);
            MonAnTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            MonAnTable.getColumnModel().getColumn(0).setMaxWidth(20);
        }
        MonAnTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        MonAnTable.getTableHeader().setOpaque(false);
        MonAnTable.getTableHeader().setBackground(new Color(243,246,249));
        MonAnTable.setDefaultEditor(Object.class, null);

        DichVuTable.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        DichVuTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên dịch vụ", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        DichVuTable.setFocusable(false);
        DichVuTable.setRowHeight(25);
        DichVuTable.setSelectionBackground(new java.awt.Color(69, 96, 134));
        jScrollPane3.setViewportView(DichVuTable);
        if (DichVuTable.getColumnModel().getColumnCount() > 0) {
            DichVuTable.getColumnModel().getColumn(0).setMinWidth(50);
            DichVuTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            DichVuTable.getColumnModel().getColumn(0).setMaxWidth(20);
        }
        DichVuTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        DichVuTable.getTableHeader().setOpaque(false);
        DichVuTable.getTableHeader().setBackground(new Color(243,246,249));
        DichVuTable.setDefaultEditor(Object.class, null);

        jLabel17.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel17.setText("Danh sách dịch vụ");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout PageThongTinDTLayout = new javax.swing.GroupLayout(PageThongTinDT);
        PageThongTinDT.setLayout(PageThongTinDTLayout);
        PageThongTinDTLayout.setHorizontalGroup(
            PageThongTinDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PageThongTinDTLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(PageThongTinDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PageThongTinDTLayout.createSequentialGroup()
                        .addGroup(PageThongTinDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(PageThongTinDTLayout.createSequentialGroup()
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(225, 225, 225)
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel12))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(56, 56, 56))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PageThongTinDTLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BackPageTTDT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(520, 520, 520))
        );
        PageThongTinDTLayout.setVerticalGroup(
            PageThongTinDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PageThongTinDTLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PageThongTinDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BackPageTTDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        PageTTHDTT.setBackground(new java.awt.Color(255, 255, 255));
        PageTTHDTT.setPreferredSize(new java.awt.Dimension(1170, 730));

        jPanel4.setBackground(new java.awt.Color(255, 204, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(255, 204, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(255, 204, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(69, 96, 134));
        jLabel10.setText("XÁC NHẬN THÔNG TIN HOÁ ĐƠN");
        jPanel7.add(jLabel10);

        jLabel18.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel18.setText("Tên chú rể:");

        TenCRValueTT.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        TenCDValueTT.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel26.setText("Tên cô dâu:");

        jLabel28.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel28.setText("Ngày đãi tiêc:");

        NgayDTValueTT.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel32.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel32.setText("Ngày thanh toán:");

        NgayTTValueTT.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel36.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel36.setText("Số lượng bàn:");

        SoLuongBanValueTT.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel38.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel38.setText("Đơn giá bàn:");

        DonGiaBanValueTT.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel40.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel40.setText("Tổng tiền bàn:");

        TongTienBanValueTT.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addComponent(jLabel18)
                    .addComponent(jLabel36))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NgayTTValueTT)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TenCRValueTT)
                            .addComponent(SoLuongBanValueTT))
                        .addGap(228, 228, 228)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addComponent(jLabel26))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TenCDValueTT)
                            .addComponent(DonGiaBanValueTT))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 456, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel40)
                    .addComponent(jLabel28))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NgayDTValueTT)
                    .addComponent(TongTienBanValueTT, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(NgayTTValueTT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(TenCRValueTT)
                    .addComponent(jLabel26)
                    .addComponent(TenCDValueTT)
                    .addComponent(jLabel28)
                    .addComponent(NgayDTValueTT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(SoLuongBanValueTT)
                    .addComponent(jLabel38)
                    .addComponent(DonGiaBanValueTT)
                    .addComponent(jLabel40)
                    .addComponent(TongTienBanValueTT))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        DVHHTable.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        DVHHTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã dịch vụ", "Tên dịch vụ", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        DVHHTable.setFocusable(false);
        DVHHTable.setRowHeight(25);
        DVHHTable.setSelectionBackground(new java.awt.Color(69, 96, 134));
        jScrollPane4.setViewportView(DVHHTable);
        if (DVHHTable.getColumnModel().getColumnCount() > 0) {
            DVHHTable.getColumnModel().getColumn(0).setMinWidth(100);
            DVHHTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            DVHHTable.getColumnModel().getColumn(0).setMaxWidth(20);
        }
        DVHHTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        DVHHTable.getTableHeader().setOpaque(false);
        DVHHTable.getTableHeader().setBackground(new Color(243,246,249));
        DVHHTable.setDefaultEditor(Object.class, null);

        TongTienDVValueTT.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel34.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel34.setText("Tổng tiền dịch vụ:");

        TongTienHDValueTT.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel42.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel42.setText("Tổng tiền hoá đơn:");

        TienPhatValueTT.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel46.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel46.setText("Số tiền cần thanh toán:");

        jLabel44.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel44.setText("Tiền cọc (50%):");

        jLabel39.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel39.setText("Tiền phạt (1%):");

        TienCocValueTT.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        ConLaiValueTT.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addComponent(jLabel39))
                        .addGap(68, 68, 68)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TienPhatValueTT)
                            .addComponent(TongTienDVValueTT)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42)
                            .addComponent(jLabel44))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TienCocValueTT)
                            .addComponent(TongTienHDValueTT)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel46)
                        .addGap(18, 18, 18)
                        .addComponent(ConLaiValueTT)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(TongTienDVValueTT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(TienPhatValueTT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(TongTienHDValueTT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(TienCocValueTT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(ConLaiValueTT))
                .addContainerGap())
        );

        jLabel19.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel19.setText("Phương thức thanh toán: ");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addGap(18, 18, 18)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        BackPageTTDT1.setBackground(new java.awt.Color(132, 70, 133));
        BackPageTTDT1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        BackPageTTDT1.setForeground(new java.awt.Color(255, 255, 255));
        BackPageTTDT1.setText("Tiếp tục");
        BackPageTTDT1.setPreferredSize(new java.awt.Dimension(90, 40));

        BackPageTTHDTT.setBackground(new java.awt.Color(69, 96, 134));
        BackPageTTHDTT.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        BackPageTTHDTT.setForeground(new java.awt.Color(255, 255, 255));
        BackPageTTHDTT.setText("Quay lại");
        BackPageTTHDTT.setPreferredSize(new java.awt.Dimension(90, 40));
        BackPageTTHDTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackPageTTHDTTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PageTTHDTTLayout = new javax.swing.GroupLayout(PageTTHDTT);
        PageTTHDTT.setLayout(PageTTHDTTLayout);
        PageTTHDTTLayout.setHorizontalGroup(
            PageTTHDTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PageTTHDTTLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PageTTHDTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PageTTHDTTLayout.createSequentialGroup()
                        .addComponent(BackPageTTHDTT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BackPageTTDT1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4)
                    .addGroup(PageTTHDTTLayout.createSequentialGroup()
                        .addGroup(PageTTHDTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PageTTHDTTLayout.setVerticalGroup(
            PageTTHDTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PageTTHDTTLayout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(PageTTHDTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BackPageTTDT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BackPageTTHDTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        PageXNDV.setBackground(new java.awt.Color(255, 255, 255));
        PageXNDV.setPreferredSize(new java.awt.Dimension(1170, 730));

        jPanel17.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel18.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel19.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jLabel20.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(69, 96, 134));
        jLabel20.setText("XÁC NHẬN DỊCH VỤ ĐÃ SỬ DỤNG");
        jPanel20.add(jLabel20);

        btnAddXNDV.setBackground(new java.awt.Color(99, 122, 48));
        btnAddXNDV.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        btnAddXNDV.setForeground(new java.awt.Color(255, 255, 255));
        btnAddXNDV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plus.png"))); // NOI18N
        btnAddXNDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddXNDVActionPerformed(evt);
            }
        });

        btnDeleteXNDV.setBackground(new java.awt.Color(132, 70, 133));
        btnDeleteXNDV.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        btnDeleteXNDV.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Delete.png"))); // NOI18N
        btnDeleteXNDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteXNDVActionPerformed(evt);
            }
        });

        DVSVTable.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        DVSVTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã dịch vụ", "Tên dịch vụ", "Số lượng", "Đơn giá"
            }
        ));
        DVSVTable.setFocusable(false);
        DVSVTable.setRowHeight(25);
        DVSVTable.setSelectionBackground(new java.awt.Color(69, 96, 134));
        jScrollPane5.setViewportView(DVSVTable);
        if (DVSVTable.getColumnModel().getColumnCount() > 0) {
            DVSVTable.getColumnModel().getColumn(0).setMinWidth(100);
            DVSVTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            DVSVTable.getColumnModel().getColumn(0).setMaxWidth(20);
        }
        DVSVTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        DVSVTable.getTableHeader().setOpaque(false);
        DVSVTable.getTableHeader().setBackground(new Color(243,246,249));
        DVSVTable.setDefaultEditor(Object.class, null);

        BackPageXNDV.setBackground(new java.awt.Color(69, 96, 134));
        BackPageXNDV.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        BackPageXNDV.setForeground(new java.awt.Color(255, 255, 255));
        BackPageXNDV.setText("Quay lại");
        BackPageXNDV.setPreferredSize(new java.awt.Dimension(90, 40));
        BackPageXNDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackPageXNDVActionPerformed(evt);
            }
        });

        NextPageXNDV.setBackground(new java.awt.Color(132, 70, 133));
        NextPageXNDV.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        NextPageXNDV.setForeground(new java.awt.Color(255, 255, 255));
        NextPageXNDV.setText("Tiếp tục");
        NextPageXNDV.setPreferredSize(new java.awt.Dimension(90, 40));
        NextPageXNDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextPageXNDVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PageXNDVLayout = new javax.swing.GroupLayout(PageXNDV);
        PageXNDV.setLayout(PageXNDVLayout);
        PageXNDVLayout.setHorizontalGroup(
            PageXNDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PageXNDVLayout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PageXNDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, 1058, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PageXNDVLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAddXNDV, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeleteXNDV, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PageXNDVLayout.createSequentialGroup()
                        .addComponent(BackPageXNDV, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(NextPageXNDV, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PageXNDVLayout.setVerticalGroup(
            PageXNDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PageXNDVLayout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PageXNDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAddXNDV, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteXNDV, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addGroup(PageXNDVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NextPageXNDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BackPageXNDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        PageTTHDH.setBackground(new java.awt.Color(255, 255, 255));
        PageTTHDH.setPreferredSize(new java.awt.Dimension(1170, 730));

        jPanel21.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel22.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel23.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 51, Short.MAX_VALUE)
        );

        jLabel30.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(69, 96, 134));
        jLabel30.setText("XÁC NHẬN THÔNG TIN HOÁ ĐƠN");
        jPanel24.add(jLabel30);

        jLabel37.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel37.setText("Ngày huỷ:");

        NgayHuyValueH.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel43.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel43.setText("Tên chú rể:");

        TenChuReValueH.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel47.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel47.setText("Đơn giá bàn:");

        TenCoDauValueH.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel49.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel49.setText("Ngày đãi tiệc:");

        NgayDTValueH.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel51.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel51.setText("Số lượng bàn:");

        SoLuongBanValueH.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel53.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel53.setText("Tên cô dâu:");

        DonGiaBanValueH.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel55.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel55.setText("Tổng tiền bàn:");

        TongTienBanValueH.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel51)
                            .addComponent(jLabel37))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NgayHuyValueH)
                            .addComponent(TenChuReValueH)
                            .addComponent(SoLuongBanValueH))))
                .addGap(180, 180, 180)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel47)
                    .addComponent(jLabel53))
                .addGap(18, 18, 18)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DonGiaBanValueH)
                    .addComponent(TenCoDauValueH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel55)
                    .addComponent(jLabel49))
                .addGap(18, 18, 18)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NgayDTValueH)
                    .addComponent(TongTienBanValueH, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(NgayHuyValueH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel49)
                                .addComponent(NgayDTValueH))
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel43)
                                .addComponent(TenChuReValueH)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel51)
                                .addComponent(SoLuongBanValueH))
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(TongTienBanValueH)
                                .addComponent(jLabel55))))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel53)
                            .addComponent(TenCoDauValueH))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47)
                            .addComponent(DonGiaBanValueH))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TienCocValueH.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel57.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel57.setText("Tổng tiền hoá đơn:");

        jLabel61.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel61.setText("Số tiền cần thanh toán:");

        ConLaiValueH.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        TongTenHHValueH.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel59.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel59.setText("Tiền cọc (50%):");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel57)
                            .addComponent(jLabel59))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TienCocValueH)
                            .addComponent(TongTenHHValueH)))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel61)
                        .addGap(18, 18, 18)
                        .addComponent(ConLaiValueH)))
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(TongTenHHValueH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(TienCocValueH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(ConLaiValueH))
                .addContainerGap())
        );

        jLabel62.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel62.setText("Phương thức thanh toán:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel62)
                .addGap(18, 18, 18)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        NextPageXNDV1.setBackground(new java.awt.Color(132, 70, 133));
        NextPageXNDV1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        NextPageXNDV1.setForeground(new java.awt.Color(255, 255, 255));
        NextPageXNDV1.setText("Tiếp tục");
        NextPageXNDV1.setPreferredSize(new java.awt.Dimension(90, 40));

        BackPageTTHDH.setBackground(new java.awt.Color(69, 96, 134));
        BackPageTTHDH.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        BackPageTTHDH.setForeground(new java.awt.Color(255, 255, 255));
        BackPageTTHDH.setText("Quay lại");
        BackPageTTHDH.setPreferredSize(new java.awt.Dimension(90, 40));
        BackPageTTHDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackPageTTHDHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PageTTHDHLayout = new javax.swing.GroupLayout(PageTTHDH);
        PageTTHDH.setLayout(PageTTHDHLayout);
        PageTTHDHLayout.setHorizontalGroup(
            PageTTHDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PageTTHDHLayout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PageTTHDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, 1058, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PageTTHDHLayout.createSequentialGroup()
                        .addGroup(PageTTHDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PageTTHDHLayout.createSequentialGroup()
                        .addComponent(BackPageTTHDH, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(NextPageXNDV1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PageTTHDHLayout.setVerticalGroup(
            PageTTHDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PageTTHDHLayout.createSequentialGroup()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 325, Short.MAX_VALUE)
                .addGroup(PageTTHDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NextPageXNDV1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BackPageTTHDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Page1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PageThongTinDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PageTTHDTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PageXNDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PageTTHDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Page1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PageThongTinDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PageTTHDTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PageXNDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PageTTHDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        int selectRow = getSelectedRow();

        if (selectRow < 0) {
            Message("Vui lòng chọn dữ liệu muốn chỉnh sửa!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            ThanhToanJDialog.setLocationRelativeTo(null);
            ThanhToanJDialog.setModal(true);
            ThanhToanJDialog.setVisible(true);
        }
        String maPDTC = String.valueOf(DatTiecTable.getValueAt(selectRow, 1));
        defaultTableXNDV = (DefaultTableModel) DVSVTable.getModel();
        int i = 0;

        for (ChiTietDichVu ct : lstDetailServices) {
            if (ct.getMaTiecCuoi().equals(maPDTC)) {
                defaultTableXNDV.addRow(new Object[]{++i, ct.getMaDichVu(), ct.getTenDichVu(), ct.getSoLuong(), ct.getDonGiaDichVu()});
            }
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void HuyTTJDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HuyTTJDialogActionPerformed
        // TODO add your handling code here:
        ThanhToanJDialog.setVisible(false);
    }//GEN-LAST:event_HuyTTJDialogActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        HuyDatTiecJDialog.setLocationRelativeTo(null);
        HuyDatTiecJDialog.setModal(true);
        HuyDatTiecJDialog.setVisible(true);
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnXemCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemCTActionPerformed
        // TODO add your handling code here:
        Page1.setVisible(false);
        PageThongTinDT.setVisible(true);
    }//GEN-LAST:event_btnXemCTActionPerformed

    private void BackPageTTDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackPageTTDTActionPerformed
        // TODO add your handling code here:
        Page1.setVisible(true);
        PageThongTinDT.setVisible(false);
    }//GEN-LAST:event_BackPageTTDTActionPerformed

    private void TiepTucTTJDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TiepTucTTJDialogActionPerformed
        // TODO add your handling code here:
        //-----
        int row = getSelectedRow();
        String maPDTC = String.valueOf(DatTiecTable.getValueAt(row, 1));

        int kq = 0;
        Date tmp = jdNgayThanhToan.getDate();
        String ngayThanhToan = "";
        if (tmp != null) {
            ngayThanhToan = YYYY_MM_DD((tmp.getYear() + 1900), (tmp.getMonth() + 1), tmp.getDate());
        }
        Date ngayTT = new Date();
        try {
            ngayTT = new SimpleDateFormat("yyyy-MM-dd").parse(ngayThanhToan);
        } catch (ParseException ex) {
            Logger.getLogger(WeddingPartyLookup.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (HoaDon hd : lstHoaDon) {
            if (hd.getMaTiecCuoi().equals(maPDTC)) {
                kq = HoaDonDAO.getInstance().Update(new HoaDon(hd.getMaHoaDon(), hd.getMaTiecCuoi(), ngayTT, hd.getTongTienDichVu(), hd.getTienPhat(),
                        hd.getTongTienHoaDon(), hd.getConLai(), hd.getUserName()));
            }
        }

        if (kq > 0) {
            ThanhToanJDialog.setVisible(false);
        } else {
            ThanhToanJDialog.setVisible(false);
            Message("Cập nhật ngày thanh toán thất bại!", JOptionPane.ERROR_MESSAGE);
        }
        Page1.setVisible(false);
        PageTTHDH.setVisible(false);
        PageXNDV.setVisible(true);
    }//GEN-LAST:event_TiepTucTTJDialogActionPerformed

    private void BackPageXNDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackPageXNDVActionPerformed
        // TODO add your handling code here:
        Page1.setVisible(true);
        PageXNDV.setVisible(false);
        defaultTableXNDV.setRowCount(0);
    }//GEN-LAST:event_BackPageXNDVActionPerformed

    private void NextPageXNDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextPageXNDVActionPerformed
        // TODO add your handling code here:
        PageXNDV.setVisible(false);
        PageTTHDTT.setVisible(true);
    }//GEN-LAST:event_NextPageXNDVActionPerformed

    private void BackPageTTHDTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackPageTTHDTTActionPerformed
        // TODO add your handling code here:
        PageXNDV.setVisible(true);
        PageTTHDTT.setVisible(false);
    }//GEN-LAST:event_BackPageTTHDTTActionPerformed

    private void BackPageTTHDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackPageTTHDHActionPerformed
        // TODO add your handling code here:
        PageTTHDH.setVisible(false);
        Page1.setVisible(true);
    }//GEN-LAST:event_BackPageTTHDHActionPerformed

    private void HuyHJDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HuyHJDialogActionPerformed
        // TODO add your handling code here:
        HuyDatTiecJDialog.setVisible(false);
    }//GEN-LAST:event_HuyHJDialogActionPerformed

    private void TiepTucHJDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TiepTucHJDialogActionPerformed
        // TODO add your handling code here:
        HuyDatTiecJDialog.setVisible(false);
        Page1.setVisible(false);
        PageTTHDH.setVisible(true);
    }//GEN-LAST:event_TiepTucHJDialogActionPerformed

    private void GroomNameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GroomNameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GroomNameTextActionPerformed

    private void BrideNameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BrideNameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BrideNameTextActionPerformed

    private void IdWeddingTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IdWeddingTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IdWeddingTextActionPerformed

    private void RetypeBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RetypeBtActionPerformed
        // TODO add your handling code here:
        GroomNameText.setText("");
        BrideNameText.setText("");
        IdWeddingText.setText("");
        HallNameCbBox.setSelectedIndex(0);
        TimeCbBox.setSelectedIndex(0);
        SortCbBox.setSelectedIndex(0);
        ReceptionDate.setDate(null);
        ReloadDataTable();
//        Information.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//        Information.setText("Vui lòng nhập thông tin cần tra cứu!");
//        jScrollPane1.setVisible(false);
    }//GEN-LAST:event_RetypeBtActionPerformed

    private void SearchBtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBtActionPerformed
        // TODO add your handling code here:
        Date receptionDate = ReceptionDate.getDate();
        String hallNameText = String.valueOf(HallNameCbBox.getSelectedItem());
        String timeText = String.valueOf(TimeCbBox.getSelectedItem());
        if (HallNameCbBox.getSelectedIndex() == 0) {
            hallNameText = "";
        }
        if (TimeCbBox.getSelectedIndex() == 0) {
            timeText = "";
        }
        String receptionDateString = "";
        System.out.println("hallName: " + hallNameText);
        System.out.println("Time: " + timeText);
        if (receptionDate != null) {
            receptionDateString = YYYY_MM_DD((receptionDate.getYear() + 1900), (receptionDate.getMonth() + 1), receptionDate.getDate());
        }
        SearchTable(GroomNameText.getText(), BrideNameText.getText(), hallNameText, IdWeddingText.getText(),
                receptionDateString, timeText, SortCbBox.getSelectedIndex());
    }//GEN-LAST:event_SearchBtActionPerformed

    private void btnAddXNDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddXNDVActionPerformed
        // TODO add your handling code here:
        AddSelectServices.setLocationRelativeTo(null);
        AddSelectServices.setVisible(true);

    }//GEN-LAST:event_btnAddXNDVActionPerformed

    private void btnDeleteXNDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteXNDVActionPerformed
        // TODO add your handling code here:
        int selectRow = getSelectedRow();
        String maPDTC = String.valueOf(DatTiecTable.getValueAt(selectRow, 1));

        int row = DVSVTable.getSelectedRow();
        int[] rows = DVSVTable.getSelectedRows();
        if (row < 0) {
            Message("Vui lòng chọn dữ liệu muốn xoá!", JOptionPane.INFORMATION_MESSAGE);
        } else {

            String mess = "";
            if (rows.length == 1) {
                mess = String.valueOf(DVSVTable.getValueAt(row, 1)) + " ";
            } else {
                for (int r : rows) {
                    mess += String.valueOf(DVSVTable.getValueAt(r, 1)) + " ";
                }
            }

            int x = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá " + mess + "hay không?");
            if (x == JOptionPane.YES_OPTION) {
                int kq = 0;
                if (rows.length == 1) {
                    try {
                        String maDV = String.valueOf(DVSVTable.getValueAt(row, 1));
                        for (ChiTietDichVu ctdv : lstDetailServices) {
                            if (ctdv.getMaTiecCuoi().equals(maPDTC) && ctdv.getMaDichVu().equals(maDV)) {
                                kq = ChiTietDichVuDAO.getInstance().Delete(ctdv);
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    for (int r : rows) {
                        try {
                            String maDV = String.valueOf(DVSVTable.getValueAt(r, 1));
                            for (ChiTietDichVu ctdv : lstDetailServices) {
                                if (ctdv.getMaTiecCuoi().equals(maPDTC) && ctdv.getMaDichVu().equals(maDV)) {
                                    kq = ChiTietDichVuDAO.getInstance().Delete(ctdv);
                                }
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (kq < 0) {
                            Message("Xoá dữ liệu " + " thất bại!", JOptionPane.ERROR_MESSAGE);
                        }
                        kq = 1;
                    }
                }
                if (kq > 0) {
                    ReloadDataXNDV();
                    ReloadDataAdd_XNDV();
                } else {
                    Message("Xoá dữ liệu thất bại!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnDeleteXNDVActionPerformed

    private void btnXacNhanDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanDVActionPerformed
        // TODO add your handling code here:
        int selectRow = getSelectedRow();
        String maPDTC = String.valueOf(DatTiecTable.getValueAt(selectRow, 1));
        System.out.println(maPDTC);

        int row = tblSelectService.getSelectedRow();
        int[] rows = tblSelectService.getSelectedRows();
        if (row < 0) {
            Message("Vui lòng chọn dịch vụ muốn thêm!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String mess = "";
            if (rows.length == 1) {
                mess = String.valueOf(tblSelectService.getValueAt(row, 1)) + " ";
            } else {
                for (int r : rows) {
                    mess += String.valueOf(tblSelectService.getValueAt(r, 1)) + " ";
                }
            }

            int x = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thêm " + mess + "hay không?");

            if (x == JOptionPane.YES_OPTION) {
                int kq = 0;
                if (rows.length == 1) {
                    String maDV = String.valueOf(tblSelectService.getValueAt(row, 1));
                    String tenDV = String.valueOf(tblSelectService.getValueAt(row, 2));
                    int donGia = Integer.parseInt(String.valueOf(tblSelectService.getValueAt(row, 3)));
                    boolean check = false;
                    for (ChiTietDichVu ctdv : lstDetailServices) {
                        if (ctdv.getMaDichVu().equals(maDV) && ctdv.getMaTiecCuoi().equals(maPDTC)) {
                            int soLuong = ctdv.getSoLuong();
                            soLuong += 1;
                            double thanhTien = soLuong * ctdv.getDonGia();
                            kq = ChiTietDichVuDAO.getInstance().Update(new ChiTietDichVu(maPDTC, maDV, soLuong, donGia, thanhTien, tenDV));
                            check = true;
                        }
                    }
                    System.out.println(check);
                    try {
                        if (!check) {
                            kq = ChiTietDichVuDAO.getInstance().Insert(new ChiTietDichVu(maPDTC, maDV, 1, donGia, donGia, tenDV));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(kq);
                } else {
                    for (int r : rows) {
                        String maDV = String.valueOf(tblSelectService.getValueAt(r, 1));
                        String tenDV = String.valueOf(tblSelectService.getValueAt(r, 2));
                        int donGia = Integer.parseInt(String.valueOf(tblSelectService.getValueAt(r, 3)));
                        boolean check = false;
                        for (ChiTietDichVu ctdv : lstDetailServices) {
                            if (ctdv.getMaDichVu().equals(maDV) && ctdv.getMaTiecCuoi().equals(maPDTC)) {
                                int soLuong = ctdv.getSoLuong();
                                soLuong += 1;
                                double thanhTien = soLuong * ctdv.getDonGia();
                                kq = ChiTietDichVuDAO.getInstance().Update(new ChiTietDichVu(maPDTC, maDV, soLuong, donGia, thanhTien, tenDV));
                                check = true;
                            }
                        }
                        try {
                            if (!check) {
                                kq = ChiTietDichVuDAO.getInstance().Insert(new ChiTietDichVu(maPDTC, maDV, 1, donGia, donGia, tenDV));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (kq == 0) {
                            Message("Thêm dịch vụ " + maDV + " thất bại!", JOptionPane.ERROR_MESSAGE);
                        }
                        kq = 1;
                    }
                }
                if (kq != 0) {
                    AddSelectServices.setVisible(false);
                    ReloadDataXNDV();
                    ReloadDataAdd_XNDV();
                } else {
                    Message("Thêm dịch vụ thất bại!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnXacNhanDVActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog AddSelectServices;
    private javax.swing.JButton BackPageTTDT;
    private javax.swing.JButton BackPageTTDT1;
    private javax.swing.JButton BackPageTTHDH;
    private javax.swing.JButton BackPageTTHDTT;
    private javax.swing.JButton BackPageXNDV;
    private javax.swing.JTextField BrideNameText;
    private javax.swing.JLabel CaValue;
    private javax.swing.JLabel ConLaiValue;
    private javax.swing.JLabel ConLaiValueH;
    private javax.swing.JLabel ConLaiValueTT;
    private javax.swing.JTable DVHHTable;
    private javax.swing.JTable DVSVTable;
    private javax.swing.JTable DatTiecTable;
    private javax.swing.JTable DichVuTable;
    private javax.swing.JLabel DonGiaBanValue;
    private javax.swing.JLabel DonGiaBanValueH;
    private javax.swing.JLabel DonGiaBanValueTT;
    private javax.swing.JTextField GroomNameText;
    private javax.swing.JComboBox<String> HallNameCbBox;
    private javax.swing.JDialog HuyDatTiecJDialog;
    private javax.swing.JButton HuyHJDialog;
    private javax.swing.JButton HuyTTJDialog;
    private javax.swing.JTextField IdWeddingText;
    private javax.swing.JLabel MaDTValue;
    private javax.swing.JTable MonAnTable;
    private javax.swing.JButton NextPageXNDV;
    private javax.swing.JButton NextPageXNDV1;
    private javax.swing.JLabel NgayDTValue;
    private javax.swing.JLabel NgayDTValueH;
    private javax.swing.JLabel NgayDTValueTT;
    private javax.swing.JLabel NgayDaiTiecValue;
    private javax.swing.JLabel NgayHuyValueH;
    private javax.swing.JLabel NgayTTValueTT;
    private javax.swing.JPanel Page1;
    private javax.swing.JPanel PageTTHDH;
    private javax.swing.JPanel PageTTHDTT;
    private javax.swing.JPanel PageThongTinDT;
    private javax.swing.JPanel PageXNDV;
    private com.toedter.calendar.JDateChooser ReceptionDate;
    private javax.swing.JButton RetypeBt;
    private javax.swing.JLabel SDTValue;
    private javax.swing.JLabel SanhValue;
    private javax.swing.JButton SearchBt;
    private javax.swing.JLabel SoLuongBanDTValue;
    private javax.swing.JLabel SoLuongBanValue;
    private javax.swing.JLabel SoLuongBanValueH;
    private javax.swing.JLabel SoLuongBanValueTT;
    private javax.swing.JComboBox<String> SortCbBox;
    private javax.swing.JLabel TenCDValue;
    private javax.swing.JLabel TenCDValueTT;
    private javax.swing.JLabel TenCRValue;
    private javax.swing.JLabel TenCRValueTT;
    private javax.swing.JLabel TenChuReValueH;
    private javax.swing.JLabel TenCoDauValueH;
    private javax.swing.JDialog ThanhToanJDialog;
    private javax.swing.JLabel TienCocValue;
    private javax.swing.JLabel TienCocValueH;
    private javax.swing.JLabel TienCocValueTT;
    private javax.swing.JLabel TienPhatValueTT;
    private javax.swing.JButton TiepTucHJDialog;
    private javax.swing.JButton TiepTucTTJDialog;
    private javax.swing.JComboBox<String> TimeCbBox;
    private javax.swing.JLabel TongTenHHValueH;
    private javax.swing.JLabel TongTienBanValue;
    private javax.swing.JLabel TongTienBanValueH;
    private javax.swing.JLabel TongTienBanValueTT;
    private javax.swing.JLabel TongTienDVValue;
    private javax.swing.JLabel TongTienDVValueTT;
    private javax.swing.JLabel TongTienHDValue;
    private javax.swing.JLabel TongTienHDValueTT;
    private javax.swing.JButton btnAddXNDV;
    private javax.swing.JButton btnDeleteXNDV;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnXacNhanDV;
    private javax.swing.JButton btnXemCT;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private com.toedter.calendar.JDateChooser jdNgayThanhToan;
    private javax.swing.JTable tblSelectService;
    // End of variables declaration//GEN-END:variables

    private JComboBox<String> JcomboBox() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
