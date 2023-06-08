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
import dao.ChiTietMonAnDAO;
import dao.ChiTiet_DV_ThanhToanDAO;
import dao.DichVuDAO;
import dao.HoaDonDAO;
import dao.LoaiMonAnDAO;
import dao.MonAnDAO;
import dao.SanhDAO;
import dao.PhieuDatTiecCuoiDAO;
import dao.ThamSoDAO;
import java.io.File;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.swing.JComboBox;
import model.Ca;
import model.Sanh;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.ChiTietDichVu;
import model.ChiTietMonAn;
import model.ChiTiet_DV_ThanhToan;
import model.DichVu;
import model.HoaDon;
import model.LoaiMonAn;
import model.MonAn;
import model.ThamSo;
import java.sql.Connection;
import database.JDBCUtil;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


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
    private NumberFormat currencyFormatVN = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    private int intKieuThanhToan;
        

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
    
    public int getSelectRow() {
        return DatTiecTable.getSelectedRow();
    }

    // --Code danh cho lay du lieu len bang XNDV (Chuc nang Thanh Toan)
    private DefaultTableModel defaultTableXNDV;
    private DefaultTableModel defaultTableDV;
    private DefaultTableModel defaultTableXNTTHD;
    private DefaultTableModel defaultTableDVCT;
    private DefaultTableModel defaultTableMACT;
    private ArrayList<ChiTietDichVu> lstDetailServices = ChiTietDichVuDAO.getInstance().SelectAll();
    private ArrayList<HoaDon> lstHoaDon = HoaDonDAO.getInstance().SelectAll();
    private ArrayList<DichVu> lstDichVu = DichVuDAO.getInstance().SelectAll();
    private ArrayList<ThamSo> lstThamSo = ThamSoDAO.getInstance().SelectAll();
    private String idTiecCuoi = "";
    
    private ArrayList<Ca> lstCa = CaDAO.getInstance().SelectAll();
    private ArrayList<Sanh> lstSanh = SanhDAO.getInstance().SelectAll();
    private ArrayList<ChiTietMonAn> lstDetailFoods = ChiTietMonAnDAO.getInstance().SelectAll();
    private ArrayList<LoaiMonAn> lstLoaiMonAn = LoaiMonAnDAO.getInstance().SelectAll();
    
    public void CreateTableXNDV_ADD() {
        defaultTableDV = (DefaultTableModel) tblSelectService.getModel();
        int row = getSelectRow();
        String maPDTC = String.valueOf(DatTiecTable.getValueAt(row, 1));
        ArrayList<String> tmp = new ArrayList<String>();
        for (ChiTietDichVu ct : lstDetailServices) {
            if (ct.getMaTiecCuoi().equals(maPDTC)) {
                tmp.add(ct.getMaDichVu());
            }
        }
        int i = 0;
        for (DichVu dv : lstDichVu) {
            if (!tmp.contains(dv.getMaDichVu())) {
                defaultTableDV.addRow(new Object[]{++i, dv.getMaDichVu(), dv.getTenDichVu(), currencyFormatVN.format(dv.getDonGia())});
            }
        }
    }
    
    public void ReloadTableXNDV_ADD() {
        defaultTableDV.setRowCount(0);
        CreateTableXNDV_ADD();
    }
    
    public void CreateTableXNDV() {
        int selectRow = getSelectRow();
        String maPDTC = String.valueOf(DatTiecTable.getValueAt(selectRow, 1));
        defaultTableXNDV = (DefaultTableModel) DVSVTable.getModel();
        int i = 0;
        
        for (ChiTietDichVu ct : lstDetailServices) {
            if (ct.getMaTiecCuoi().equals(maPDTC)) {
                defaultTableXNDV.addRow(new Object[]{++i, ct.getMaDichVu(), ct.getTenDichVu(), ct.getSoLuong(), currencyFormatVN.format(ct.getDonGiaDichVu())});
            }
        }
    }
    
    public void ReloadDataXNDV() {
        lstDetailServices = ChiTietDichVuDAO.getInstance().SelectAll();
        defaultTableXNDV.setRowCount(0);
        CreateTableXNDV();
    }
    
    public void CreateTableXNTTHD() {
        int selectRow = getSelectRow();
        String maPDTC = String.valueOf(DatTiecTable.getValueAt(selectRow, 1));
        defaultTableXNTTHD = (DefaultTableModel) DVHHTable.getModel();
        int i = 0;
        for (ChiTietDichVu ctdvtt : lstDetailServices) {
            if (ctdvtt.getMaTiecCuoi().equals(maPDTC)) {
                defaultTableXNTTHD.addRow(new Object[]{++i, ctdvtt.getMaDichVu(), ctdvtt.getTenDichVu(), ctdvtt.getSoLuong(),
                    currencyFormatVN.format(ctdvtt.getDonGiaDichVu()), currencyFormatVN.format((long) ctdvtt.getThanhTien())});
            }
        }
    }
    
    public void SetTienDu(String a) {
        double t = Double.parseDouble(lblSoTienThanhToan.getText());
        double p = Double.parseDouble(a);
        double du = p - t;
        lblTienDuTT.setText((long) du + "");
    }
    
    public void CreateTableDVCT() {
        int selectRow = getSelectRow();
        String maPDTC = String.valueOf(DatTiecTable.getValueAt(selectRow, 1));
        defaultTableDVCT = (DefaultTableModel) DichVuTable.getModel();
        int i = 0;
        
        for (ChiTietDichVu ct : lstDetailServices) {
            if (ct.getMaTiecCuoi().equals(maPDTC)) {
                defaultTableDVCT.addRow(new Object[]{++i, ct.getMaDichVu(), ct.getTenDichVu(), ct.getSoLuong(), currencyFormatVN.format(ct.getDonGiaDichVu()) });
            }
        }
    }
    
    public void CreateTableMACT() {
        int selectRow = getSelectRow();
        String maPDTC = String.valueOf(DatTiecTable.getValueAt(selectRow, 1));
        defaultTableMACT = (DefaultTableModel) MonAnTable.getModel();
        int i = 0;
        
        for (ChiTietMonAn ct : lstDetailFoods) {
            if (ct.getMaTiecCuoi().equals(maPDTC)) {
                String loai = "";
                for (LoaiMonAn l : lstLoaiMonAn) {
                    if (l.getMaLoaiMonAn().equals(ct.getMaLoaiMonAn())) {
                        loai = l.getTenLoaiMonAn();
                    }
                }
                defaultTableMACT.addRow(new Object[]{++i, ct.getTenMonAn(), loai, currencyFormatVN.format(ct.getDonGia()), ct.getSoLuong(), ct.getGhiChu()});
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

        AddSelectServices = new javax.swing.JDialog();
        jPanel28 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblSelectService = new javax.swing.JTable();
        btnXacNhanDV = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        TienMatForm = new javax.swing.JDialog();
        jPanel29 = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        txtTienNhan = new javax.swing.JTextField();
        lblTongTienTT = new javax.swing.JLabel();
        lblTienDuTT = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        btnThanhToanTT = new javax.swing.JButton();
        ChuyenKhoanForm = new javax.swing.JDialog();
        jPanel30 = new javax.swing.JPanel();
        ThanhToanTienMat = new javax.swing.JDialog();
        jLabel64 = new javax.swing.JLabel();
        inputSoTienDaNhan = new javax.swing.JTextField();
        btnmuoiTrieu = new javax.swing.JButton();
        btnhaiMuoiTrieu = new javax.swing.JButton();
        btn50tr = new javax.swing.JButton();
        btn70tr = new javax.swing.JButton();
        btn90tr = new javax.swing.JButton();
        btn100tr = new javax.swing.JButton();
        btn200tr = new javax.swing.JButton();
        btn300tr = new javax.swing.JButton();
        btn400tr = new javax.swing.JButton();
        btn500tr = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnXacNhan = new javax.swing.JButton();
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
        jLabel52 = new javax.swing.JLabel();
        HallNameCbBox = new javax.swing.JComboBox<>();
        jLabel54 = new javax.swing.JLabel();
        TimeCbBox = new javax.swing.JComboBox<>();
        jLabel56 = new javax.swing.JLabel();
        SortCbBox = new javax.swing.JComboBox<>();
        SearchBt = new javax.swing.JButton();
        RetypeBt = new javax.swing.JButton();
        PageThongTinDT = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        MaDTValue = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TenCRValue = new javax.swing.JLabel();
        TenCDValue = new javax.swing.JLabel();
        SDTValue = new javax.swing.JLabel();
        lbltenChuRe = new javax.swing.JLabel();
        lbltenCoDau = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        NgayDTValue = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblsoLuongBan = new javax.swing.JPanel();
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
        lblngayDaiTiec = new javax.swing.JLabel();
        lblSLBan = new javax.swing.JLabel();
        lbltongTienBan = new javax.swing.JLabel();
        lbltongTienHoaDon = new javax.swing.JLabel();
        lblCa = new javax.swing.JLabel();
        lblSoLuongBanDuTru = new javax.swing.JLabel();
        lbltongTienDichVu = new javax.swing.JLabel();
        lbltienConLai = new javax.swing.JLabel();
        lblSanh = new javax.swing.JLabel();
        lbldonGiaBan = new javax.swing.JLabel();
        lbltienCoc = new javax.swing.JLabel();
        BackPageTTDT = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        MonAnTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        DichVuTable = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        lblmaPDTC = new javax.swing.JLabel();
        lblngayDatTiec = new javax.swing.JLabel();
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
        lblNgayThanhToan = new javax.swing.JLabel();
        lblSoLuongBan = new javax.swing.JLabel();
        lblTenChuRe = new javax.swing.JLabel();
        lblNgayDaiTiec = new javax.swing.JLabel();
        lblDonGiaBan = new javax.swing.JLabel();
        lblTenCoDau = new javax.swing.JLabel();
        lblTongTienBan = new javax.swing.JLabel();
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
        jl_TienPhat = new javax.swing.JLabel();
        TienCocValueTT = new javax.swing.JLabel();
        ConLaiValueTT = new javax.swing.JLabel();
        lblTongTienDichVu = new javax.swing.JLabel();
        lblTienPhat = new javax.swing.JLabel();
        lblTongTienHoaDon = new javax.swing.JLabel();
        lblTienCoc = new javax.swing.JLabel();
        lblSoTienThanhToan = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        cbPhuongThucTT = new javax.swing.JComboBox<>();
        NextPageXNTT = new javax.swing.JButton();
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
        jl_SoNgayHuySom = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        TienCocValueH = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        ConLaiValueH = new javax.swing.JLabel();
        TongTenHHValueH = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jcb_PhuongThucTT = new javax.swing.JComboBox<>();
        NextPageXNDV1 = new javax.swing.JButton();
        BackPageTTHDH = new javax.swing.JButton();

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

        TienMatForm.setMinimumSize(new java.awt.Dimension(520, 435));
        TienMatForm.setModal(true);

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel48.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel48.setText("Tiền nhận ");

        jLabel58.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel58.setText("Trả lại khách");

        jLabel60.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel60.setText("Tổng thanh toán");

        txtTienNhan.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txtTienNhan.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTienNhan.setText("0");
        txtTienNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienNhanActionPerformed(evt);
            }
        });

        lblTongTienTT.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblTongTienTT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTongTienTT.setText("0");

        lblTienDuTT.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblTienDuTT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTienDuTT.setText("0");

        jLabel65.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(69, 96, 134));
        jLabel65.setText("Thanh toán tiền mặt");

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setAlignmentX(3.0F);
        jSeparator1.setAlignmentY(3.0F);

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setAlignmentX(3.0F);
        jSeparator2.setAlignmentY(3.0F);

        btnThanhToanTT.setBackground(new java.awt.Color(132, 70, 133));
        btnThanhToanTT.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnThanhToanTT.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToanTT.setText("Thanh toán");
        btnThanhToanTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanTTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnThanhToanTT, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel29Layout.createSequentialGroup()
                            .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblTongTienTT, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblTienDuTT, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTienNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(34, 34, 34))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTongTienTT, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTienDuTT, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(btnThanhToanTT, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(157, 157, 157))
        );

        txtTienNhan.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                SetTienDu(txtTienNhan.getText());
            }
            public void removeUpdate(DocumentEvent e) {
                if(txtTienNhan.getText().equals("")){
                    lblTienDuTT.setText("-"+lblTongTienTT.getText());
                }else{
                    SetTienDu(txtTienNhan.getText());
                }
            }
            public void insertUpdate(DocumentEvent e) {
                SetTienDu(txtTienNhan.getText());
            }
        });
        //if(txtTienNhan.getText().equals("")){
            //    lblTienDuTT.setText("-"+lblTongTienTT.getText());

            javax.swing.GroupLayout TienMatFormLayout = new javax.swing.GroupLayout(TienMatForm.getContentPane());
            TienMatForm.getContentPane().setLayout(TienMatFormLayout);
            TienMatFormLayout.setHorizontalGroup(
                TienMatFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            );
            TienMatFormLayout.setVerticalGroup(
                TienMatFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
            );

            ChuyenKhoanForm.setModal(true);

            jPanel30.setBackground(new java.awt.Color(255, 255, 255));
            jPanel30.setMinimumSize(new java.awt.Dimension(450, 350));

            javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
            jPanel30.setLayout(jPanel30Layout);
            jPanel30Layout.setHorizontalGroup(
                jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 450, Short.MAX_VALUE)
            );
            jPanel30Layout.setVerticalGroup(
                jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 350, Short.MAX_VALUE)
            );

            javax.swing.GroupLayout ChuyenKhoanFormLayout = new javax.swing.GroupLayout(ChuyenKhoanForm.getContentPane());
            ChuyenKhoanForm.getContentPane().setLayout(ChuyenKhoanFormLayout);
            ChuyenKhoanFormLayout.setHorizontalGroup(
                ChuyenKhoanFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            ChuyenKhoanFormLayout.setVerticalGroup(
                ChuyenKhoanFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            ThanhToanTienMat.setBackground(new java.awt.Color(255, 255, 255));
            ThanhToanTienMat.setSize(new java.awt.Dimension(427, 400));

            jLabel64.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
            jLabel64.setText("Số tiền đã nhận:");

            inputSoTienDaNhan.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
            inputSoTienDaNhan.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            inputSoTienDaNhan.setText("0");
            inputSoTienDaNhan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

            btnmuoiTrieu.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
            btnmuoiTrieu.setText("10.000.000");
            btnmuoiTrieu.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnmuoiTrieuActionPerformed(evt);
                }
            });

            btnhaiMuoiTrieu.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
            btnhaiMuoiTrieu.setText("20.000.000");
            btnhaiMuoiTrieu.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnhaiMuoiTrieuActionPerformed(evt);
                }
            });

            btn50tr.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
            btn50tr.setText("50.000.000");
            btn50tr.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btn50trActionPerformed(evt);
                }
            });

            btn70tr.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
            btn70tr.setText("70.000.000");
            btn70tr.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btn70trActionPerformed(evt);
                }
            });

            btn90tr.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
            btn90tr.setText("90.000.000");
            btn90tr.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btn90trActionPerformed(evt);
                }
            });

            btn100tr.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
            btn100tr.setText("100.000.000");
            btn100tr.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btn100trActionPerformed(evt);
                }
            });

            btn200tr.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
            btn200tr.setText("200.000.000");
            btn200tr.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btn200trActionPerformed(evt);
                }
            });

            btn300tr.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
            btn300tr.setText("300.000.000");
            btn300tr.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btn300trActionPerformed(evt);
                }
            });

            btn400tr.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
            btn400tr.setText("400.000.000");
            btn400tr.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btn400trActionPerformed(evt);
                }
            });

            btn500tr.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
            btn500tr.setText("500.000.000");
            btn500tr.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btn500trActionPerformed(evt);
                }
            });

            btnXoa.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
            btnXoa.setText("C");
            btnXoa.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnXoaActionPerformed(evt);
                }
            });

            btnXacNhan.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
            btnXacNhan.setText("Xác nhận");
            btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnXacNhanActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout ThanhToanTienMatLayout = new javax.swing.GroupLayout(ThanhToanTienMat.getContentPane());
            ThanhToanTienMat.getContentPane().setLayout(ThanhToanTienMatLayout);
            ThanhToanTienMatLayout.setHorizontalGroup(
                ThanhToanTienMatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ThanhToanTienMatLayout.createSequentialGroup()
                    .addGap(56, 56, 56)
                    .addGroup(ThanhToanTienMatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(inputSoTienDaNhan)
                        .addComponent(jLabel64)
                        .addGroup(ThanhToanTienMatLayout.createSequentialGroup()
                            .addGroup(ThanhToanTienMatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnmuoiTrieu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn70tr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn200tr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(ThanhToanTienMatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(ThanhToanTienMatLayout.createSequentialGroup()
                                    .addGap(5, 5, 5)
                                    .addComponent(btn90tr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(ThanhToanTienMatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btn50tr, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn100tr, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(ThanhToanTienMatLayout.createSequentialGroup()
                                    .addGap(5, 5, 5)
                                    .addComponent(btnhaiMuoiTrieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(107, 107, 107))
                                .addGroup(ThanhToanTienMatLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(ThanhToanTienMatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(ThanhToanTienMatLayout.createSequentialGroup()
                                            .addComponent(btn500tr)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btnXacNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(ThanhToanTienMatLayout.createSequentialGroup()
                                            .addComponent(btn300tr)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btn400tr)))))))
                    .addGap(56, 56, 56))
            );
            ThanhToanTienMatLayout.setVerticalGroup(
                ThanhToanTienMatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ThanhToanTienMatLayout.createSequentialGroup()
                    .addGap(56, 56, 56)
                    .addComponent(jLabel64)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(inputSoTienDaNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addGroup(ThanhToanTienMatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnmuoiTrieu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnhaiMuoiTrieu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn50tr, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(ThanhToanTienMatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn70tr, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn90tr, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn100tr, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(ThanhToanTienMatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn200tr, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn300tr, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn400tr, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(ThanhToanTienMatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(ThanhToanTienMatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn500tr, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 50, Short.MAX_VALUE))
            );

            setPreferredSize(new java.awt.Dimension(1170, 730));

            Page1.setBackground(new java.awt.Color(255, 255, 255));

            DatTiecTable.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
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
                DatTiecTable.getColumnModel().getColumn(0).setPreferredWidth(18);
                DatTiecTable.getColumnModel().getColumn(1).setMinWidth(100);
                DatTiecTable.getColumnModel().getColumn(1).setPreferredWidth(100);
                DatTiecTable.getColumnModel().getColumn(1).setMaxWidth(100);
                DatTiecTable.getColumnModel().getColumn(4).setPreferredWidth(50);
                DatTiecTable.getColumnModel().getColumn(5).setMinWidth(100);
                DatTiecTable.getColumnModel().getColumn(5).setPreferredWidth(100);
                DatTiecTable.getColumnModel().getColumn(5).setMaxWidth(100);
                DatTiecTable.getColumnModel().getColumn(6).setPreferredWidth(70);
                DatTiecTable.getColumnModel().getColumn(7).setMinWidth(110);
                DatTiecTable.getColumnModel().getColumn(7).setPreferredWidth(110);
                DatTiecTable.getColumnModel().getColumn(7).setMaxWidth(110);
                DatTiecTable.getColumnModel().getColumn(8).setPreferredWidth(60);
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

            jLabel23.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
            jLabel23.setText("Mã tiệc cưới:");

            jLabel24.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
            jLabel24.setText("Tên chú rể:");

            jLabel41.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
            jLabel41.setText("Tên cô dâu:");

            GroomNameText.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
            GroomNameText.setMinimumSize(new java.awt.Dimension(64, 33));
            GroomNameText.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    GroomNameTextActionPerformed(evt);
                }
            });

            BrideNameText.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
            BrideNameText.setMinimumSize(new java.awt.Dimension(64, 33));
            BrideNameText.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    BrideNameTextActionPerformed(evt);
                }
            });

            jLabel50.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
            jLabel50.setText("Ngày đãi tiệc:");

            IdWeddingText.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
            IdWeddingText.setMinimumSize(new java.awt.Dimension(64, 33));
            IdWeddingText.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    IdWeddingTextActionPerformed(evt);
                }
            });

            jLabel52.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
            jLabel52.setText("Sảnh:");

            HallNameCbBox.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
            HallNameCbBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

            jLabel54.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
            jLabel54.setText("Giờ:");

            TimeCbBox.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
            TimeCbBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

            jLabel56.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
            jLabel56.setText("Số lượng bàn:");

            SortCbBox.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
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
                                .addComponent(HallNameCbBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(TimeCbBox, 0, 160, Short.MAX_VALUE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(SortCbBox, 0, 160, Short.MAX_VALUE)
                    .addGap(19, 19, 19))
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
                            .addGap(15, 15, 15)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ReceptionDate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(15, 15, 15)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                                .addComponent(TimeCbBox, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)))
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

            SearchBt.setBackground(new java.awt.Color(255, 167, 149));
            SearchBt.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
            SearchBt.setForeground(new java.awt.Color(255, 255, 255));
            SearchBt.setText("Tra Cứu");
            SearchBt.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    SearchBtActionPerformed(evt);
                }
            });

            RetypeBt.setBackground(new java.awt.Color(69, 96, 134));
            RetypeBt.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
            RetypeBt.setForeground(new java.awt.Color(255, 255, 255));
            RetypeBt.setText("Nhập lại");
            RetypeBt.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    RetypeBtActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout Page1Layout = new javax.swing.GroupLayout(Page1);
            Page1.setLayout(Page1Layout);
            Page1Layout.setHorizontalGroup(
                Page1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(Page1Layout.createSequentialGroup()
                    .addGroup(Page1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(Page1Layout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(SearchBt, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(RetypeBt, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                    .addContainerGap(68, Short.MAX_VALUE))
            );

            Page1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnHuy, btnThanhToan});

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
                        .addComponent(btnXemCT, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SearchBt, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(RetypeBt, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
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

            jLabel63.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
            jLabel63.setText("Mã phiếu đặt tiệc:");

            javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
            jPanel9.setLayout(jPanel9Layout);
            jPanel9Layout.setHorizontalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel63)
                    .addGap(16, 16, 16)
                    .addComponent(MaDTValue)
                    .addContainerGap())
            );
            jPanel9Layout.setVerticalGroup(
                jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel63)
                        .addComponent(MaDTValue))
                    .addContainerGap())
            );

            jLabel3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
            jLabel3.setText("Thông tin khách hàng:");

            jLabel7.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
            jLabel7.setText("Tên chú rể:");

            jLabel8.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
            jLabel8.setText("Tên cô dâu:");

            TenCRValue.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

            TenCDValue.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

            SDTValue.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

            lbltenChuRe.setFont(new java.awt.Font("Arial", 2, 16)); // NOI18N
            lbltenChuRe.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            lbltenChuRe.setText("Tên chú rể:");

            lbltenCoDau.setFont(new java.awt.Font("Arial", 2, 16)); // NOI18N
            lbltenCoDau.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            lbltenCoDau.setText("Tên chú rể:");

            jLabel9.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
            jLabel9.setText("SĐT:");

            lblSDT.setFont(new java.awt.Font("Arial", 2, 16)); // NOI18N
            lblSDT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            lblSDT.setText("SĐT:");

            javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
            jPanel10.setLayout(jPanel10Layout);
            jPanel10Layout.setHorizontalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel7)
                    .addGap(58, 58, 58)
                    .addComponent(lbltenChuRe, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(TenCRValue)
                    .addGap(102, 102, 102)
                    .addComponent(jLabel8)
                    .addGap(42, 42, 42)
                    .addComponent(lbltenCoDau, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(84, 84, 84)
                    .addComponent(TenCDValue)
                    .addGap(65, 65, 65)
                    .addComponent(jLabel9)
                    .addGap(30, 30, 30)
                    .addComponent(lblSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SDTValue)
                    .addContainerGap())
            );
            jPanel10Layout.setVerticalGroup(
                jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(TenCRValue)
                        .addComponent(TenCDValue)
                        .addComponent(SDTValue)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbltenChuRe)
                        .addComponent(lbltenCoDau)
                        .addComponent(jLabel9)
                        .addComponent(lblSDT))
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
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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

            lblngayDaiTiec.setFont(new java.awt.Font("Arial", 2, 16)); // NOI18N
            lblngayDaiTiec.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            lblngayDaiTiec.setText("Ngày đãi tiệc:");

            lblSLBan.setFont(new java.awt.Font("Arial", 2, 16)); // NOI18N
            lblSLBan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            lblSLBan.setText("Ngày đãi tiệc:");

            lbltongTienBan.setFont(new java.awt.Font("Arial", 2, 16)); // NOI18N
            lbltongTienBan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            lbltongTienBan.setText("Ngày đãi tiệc:");

            lbltongTienHoaDon.setFont(new java.awt.Font("Arial", 2, 16)); // NOI18N
            lbltongTienHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            lbltongTienHoaDon.setText("Ngày đãi tiệc:");

            lblCa.setFont(new java.awt.Font("Arial", 2, 16)); // NOI18N
            lblCa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            lblCa.setText("Ca:");

            lblSoLuongBanDuTru.setFont(new java.awt.Font("Arial", 2, 16)); // NOI18N
            lblSoLuongBanDuTru.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            lblSoLuongBanDuTru.setText("Ca:");

            lbltongTienDichVu.setFont(new java.awt.Font("Arial", 2, 16)); // NOI18N
            lbltongTienDichVu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            lbltongTienDichVu.setText("Ca:");

            lbltienConLai.setFont(new java.awt.Font("Arial", 2, 16)); // NOI18N
            lbltienConLai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            lbltienConLai.setText("Ca:");

            lblSanh.setFont(new java.awt.Font("Arial", 2, 16)); // NOI18N
            lblSanh.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            lblSanh.setText("Sảnh:");

            lbldonGiaBan.setFont(new java.awt.Font("Arial", 2, 16)); // NOI18N
            lbldonGiaBan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            lbldonGiaBan.setText("Sảnh:");

            lbltienCoc.setFont(new java.awt.Font("Arial", 2, 16)); // NOI18N
            lbltienCoc.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            lbltienCoc.setText("Sảnh:");

            javax.swing.GroupLayout lblsoLuongBanLayout = new javax.swing.GroupLayout(lblsoLuongBan);
            lblsoLuongBan.setLayout(lblsoLuongBanLayout);
            lblsoLuongBanLayout.setHorizontalGroup(
                lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lblsoLuongBanLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel33)
                        .addGroup(lblsoLuongBanLayout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addGroup(lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel27)
                                .addComponent(SoLuongBanValue)
                                .addComponent(jLabel13))))
                    .addGroup(lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(lblsoLuongBanLayout.createSequentialGroup()
                            .addGap(32, 32, 32)
                            .addComponent(lblngayDaiTiec))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lblsoLuongBanLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblSLBan, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lbltongTienBan, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lbltongTienHoaDon, javax.swing.GroupLayout.Alignment.TRAILING))))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(TongTienHDValue)
                        .addComponent(TongTienBanValue)
                        .addComponent(jLabel22)
                        .addComponent(NgayDaiTiecValue))
                    .addGap(118, 118, 118)
                    .addGroup(lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(SoLuongBanDTValue)
                        .addComponent(jLabel29)
                        .addComponent(jLabel14)
                        .addComponent(jLabel35))
                    .addGap(29, 29, 29)
                    .addGroup(lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(lblsoLuongBanLayout.createSequentialGroup()
                            .addGroup(lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lbltienConLai, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                                .addComponent(lbltongTienDichVu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblSoLuongBanDuTru, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(128, 128, 128)
                            .addGroup(lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(ConLaiValue)
                                .addComponent(TongTienDVValue)
                                .addComponent(jLabel25)))
                        .addGroup(lblsoLuongBanLayout.createSequentialGroup()
                            .addComponent(lblCa, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(CaValue)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel31)
                        .addGroup(lblsoLuongBanLayout.createSequentialGroup()
                            .addGroup(lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel21)
                                .addComponent(jLabel15))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblSanh, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                                .addComponent(lbldonGiaBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbltienCoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(2, 2, 2)
                            .addGroup(lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(lblsoLuongBanLayout.createSequentialGroup()
                                    .addGap(108, 108, 108)
                                    .addGroup(lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(SanhValue)
                                        .addComponent(DonGiaBanValue)))
                                .addComponent(TienCocValue, javax.swing.GroupLayout.Alignment.TRAILING))))
                    .addContainerGap())
            );
            lblsoLuongBanLayout.setVerticalGroup(
                lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(lblsoLuongBanLayout.createSequentialGroup()
                    .addGroup(lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(lblsoLuongBanLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(lblSanh)
                                    .addComponent(lblCa)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(lblsoLuongBanLayout.createSequentialGroup()
                                    .addGroup(lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel25)
                                        .addComponent(DonGiaBanValue)
                                        .addComponent(jLabel22))
                                    .addGap(50, 50, 50)
                                    .addGroup(lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(TongTienHDValue)
                                        .addComponent(ConLaiValue)))
                                .addGroup(lblsoLuongBanLayout.createSequentialGroup()
                                    .addGroup(lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(SoLuongBanDTValue)
                                            .addComponent(lblSoLuongBanDuTru))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel21)
                                            .addComponent(lbldonGiaBan)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel29)
                                            .addComponent(lbltongTienDichVu))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel31)
                                            .addComponent(lbltienCoc)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel35)
                                        .addComponent(lbltienConLai)))))
                        .addGroup(lblsoLuongBanLayout.createSequentialGroup()
                            .addGap(62, 62, 62)
                            .addComponent(TienCocValue))
                        .addGroup(lblsoLuongBanLayout.createSequentialGroup()
                            .addGap(62, 62, 62)
                            .addComponent(TongTienDVValue))
                        .addGroup(lblsoLuongBanLayout.createSequentialGroup()
                            .addGap(62, 62, 62)
                            .addComponent(TongTienBanValue))
                        .addGroup(lblsoLuongBanLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(lblngayDaiTiec))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(SoLuongBanValue)
                                .addComponent(lblSLBan))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel27)
                                .addComponent(lbltongTienBan))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel33)
                                .addComponent(lbltongTienHoaDon)))
                        .addGroup(lblsoLuongBanLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addGroup(lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(lblsoLuongBanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(NgayDaiTiecValue)
                                    .addComponent(CaValue))
                                .addComponent(SanhValue))))
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

                },
                new String [] {
                    "STT", "Tên món ăn", "Loại", "Đơn giá", "Số lượng", "Ghi chú"
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
                            .addComponent(jScrollPane3)
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

            lblmaPDTC.setFont(new java.awt.Font("Arial", 2, 16)); // NOI18N
            lblmaPDTC.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            lblmaPDTC.setText("Mã phiếu đặt tiệc:");

            lblngayDatTiec.setFont(new java.awt.Font("Arial", 2, 16)); // NOI18N
            lblngayDatTiec.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            lblngayDatTiec.setText("Mã phiếu đặt tiệc:");

            javax.swing.GroupLayout PageThongTinDTLayout = new javax.swing.GroupLayout(PageThongTinDT);
            PageThongTinDT.setLayout(PageThongTinDTLayout);
            PageThongTinDTLayout.setHorizontalGroup(
                PageThongTinDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PageThongTinDTLayout.createSequentialGroup()
                    .addGap(56, 56, 56)
                    .addGroup(PageThongTinDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PageThongTinDTLayout.createSequentialGroup()
                            .addGroup(PageThongTinDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblsoLuongBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(PageThongTinDTLayout.createSequentialGroup()
                                    .addGroup(PageThongTinDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addGroup(PageThongTinDTLayout.createSequentialGroup()
                                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(38, 38, 38)
                                            .addComponent(lblmaPDTC)
                                            .addGap(125, 125, 125)
                                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(57, 57, 57)
                                            .addComponent(lblngayDatTiec)))
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addGap(56, 56, 56))
                        .addGroup(PageThongTinDTLayout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(PageThongTinDTLayout.createSequentialGroup()
                    .addGap(535, 535, 535)
                    .addComponent(BackPageTTDT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            PageThongTinDTLayout.setVerticalGroup(
                PageThongTinDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PageThongTinDTLayout.createSequentialGroup()
                    .addGap(56, 56, 56)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(PageThongTinDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblmaPDTC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblngayDatTiec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel12)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(lblsoLuongBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(0, 30, Short.MAX_VALUE)
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
            jLabel26.setText("Số lượng bàn");

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

            lblNgayThanhToan.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
            lblNgayThanhToan.setText("s");

            lblSoLuongBan.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
            lblSoLuongBan.setText("s");

            lblTenChuRe.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
            lblTenChuRe.setText("s");

            lblNgayDaiTiec.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
            lblNgayDaiTiec.setText("s");

            lblDonGiaBan.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
            lblDonGiaBan.setText("s");

            lblTenCoDau.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
            lblTenCoDau.setText("s");

            lblTongTienBan.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
            lblTongTienBan.setText("s");

            javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
            jPanel11.setLayout(jPanel11Layout);
            jPanel11Layout.setHorizontalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel18)
                        .addComponent(jLabel32)
                        .addComponent(jLabel36))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblTenCoDau, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblNgayThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                            .addComponent(lblTenChuRe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGap(12, 12, 12)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(TenCRValueTT)
                        .addComponent(SoLuongBanValueTT)
                        .addComponent(NgayTTValueTT))
                    .addGap(77, 77, 77)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addComponent(jLabel28)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(lblNgayDaiTiec, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addComponent(jLabel38)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblDonGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addComponent(jLabel26)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lblSoLuongBan, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(25, 25, 25)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel11Layout.createSequentialGroup()
                                    .addComponent(TenCDValueTT)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel40)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(lblTongTienBan, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(DonGiaBanValueTT))))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(NgayDTValueTT)
                        .addComponent(TongTienBanValueTT, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addContainerGap())
            );

            jPanel11Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblDonGiaBan, lblNgayDaiTiec, lblSoLuongBan});

            jPanel11Layout.setVerticalGroup(
                jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel32)
                        .addComponent(NgayTTValueTT)
                        .addComponent(lblNgayThanhToan)
                        .addComponent(jLabel28)
                        .addComponent(lblNgayDaiTiec))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(TenCRValueTT)
                                    .addComponent(TenCDValueTT)
                                    .addComponent(NgayDTValueTT)
                                    .addComponent(lblSoLuongBan)
                                    .addComponent(lblTongTienBan)
                                    .addComponent(jLabel40))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblTenChuRe)
                                    .addComponent(jLabel18)))
                            .addGap(7, 7, 7))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                            .addComponent(jLabel26)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel36)
                        .addComponent(SoLuongBanValueTT)
                        .addComponent(jLabel38)
                        .addComponent(DonGiaBanValueTT)
                        .addComponent(TongTienBanValueTT)
                        .addComponent(lblDonGiaBan)
                        .addComponent(lblTenCoDau))
                    .addContainerGap(10, Short.MAX_VALUE))
            );

            DVHHTable.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
            DVHHTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

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

            jPanel15.setPreferredSize(new java.awt.Dimension(1057, 131));

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

            jl_TienPhat.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
            jl_TienPhat.setText("Tiền phạt (1%):");

            TienCocValueTT.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

            ConLaiValueTT.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N

            lblTongTienDichVu.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
            lblTongTienDichVu.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            lblTongTienDichVu.setText("Tổng tiền dịch vụ:");

            lblTienPhat.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
            lblTienPhat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            lblTienPhat.setText("Tổng tiền dịch vụ:");

            lblTongTienHoaDon.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
            lblTongTienHoaDon.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            lblTongTienHoaDon.setText("Tổng tiền dịch vụ:");

            lblTienCoc.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
            lblTienCoc.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            lblTienCoc.setText("Tổng tiền dịch vụ:");

            lblSoTienThanhToan.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
            lblSoTienThanhToan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
            lblSoTienThanhToan.setText("Tổng tiền dịch vụ:");

            javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
            jPanel15.setLayout(jPanel15Layout);
            jPanel15Layout.setHorizontalGroup(
                jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addComponent(jLabel42)
                            .addGap(60, 60, 60)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(TongTienHDValueTT)
                                .addComponent(TienCocValueTT)))
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addComponent(jLabel46)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(ConLaiValueTT))
                        .addComponent(jLabel44)
                        .addGroup(jPanel15Layout.createSequentialGroup()
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel34)
                                .addComponent(jl_TienPhat))
                            .addGap(68, 68, 68)
                            .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(TongTienDVValueTT)
                                .addComponent(TienPhatValueTT))))
                    .addGap(40, 40, 40)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTienPhat)
                            .addComponent(lblTongTienHoaDon)
                            .addComponent(lblTongTienDichVu))
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblSoTienThanhToan)
                            .addComponent(lblTienCoc)))
                    .addContainerGap(701, Short.MAX_VALUE))
            );
            jPanel15Layout.setVerticalGroup(
                jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel15Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel34)
                        .addComponent(TongTienDVValueTT)
                        .addComponent(lblTongTienDichVu))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jl_TienPhat)
                        .addComponent(TienPhatValueTT)
                        .addComponent(lblTienPhat))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel42)
                        .addComponent(TongTienHDValueTT)
                        .addComponent(lblTongTienHoaDon))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel44)
                        .addComponent(TienCocValueTT)
                        .addComponent(lblTienCoc))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel46)
                        .addComponent(ConLaiValueTT)
                        .addComponent(lblSoTienThanhToan))
                    .addContainerGap())
            );

            jLabel19.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
            jLabel19.setText("Phương thức thanh toán: ");

            cbPhuongThucTT.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

            javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
            jPanel16.setLayout(jPanel16Layout);
            jPanel16Layout.setHorizontalGroup(
                jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel16Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel19)
                    .addGap(18, 18, 18)
                    .addComponent(cbPhuongThucTT, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );
            jPanel16Layout.setVerticalGroup(
                jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel16Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19)
                        .addComponent(cbPhuongThucTT, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
            );

            cbPhuongThucTT.getAccessibleContext().setAccessibleDescription("");

            NextPageXNTT.setBackground(new java.awt.Color(132, 70, 133));
            NextPageXNTT.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
            NextPageXNTT.setForeground(new java.awt.Color(255, 255, 255));
            NextPageXNTT.setText("Tiếp tục");
            NextPageXNTT.setPreferredSize(new java.awt.Dimension(90, 40));
            NextPageXNTT.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    NextPageXNTTActionPerformed(evt);
                }
            });

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
                            .addComponent(NextPageXNTT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PageTTHDTTLayout.createSequentialGroup()
                            .addGroup(PageTTHDTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane4)
                                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, 1061, Short.MAX_VALUE)
                                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                    .addGroup(PageTTHDTTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(NextPageXNTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

            jl_SoNgayHuySom.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
            jl_SoNgayHuySom.setText("Số ngày hủy sớm:");

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
                        .addGroup(jPanel25Layout.createSequentialGroup()
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel47)
                                .addComponent(jLabel53))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(DonGiaBanValueH)
                                .addComponent(TenCoDauValueH))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 522, Short.MAX_VALUE)
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel55)
                                .addComponent(jLabel49))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(NgayDTValueH)
                                .addComponent(TongTienBanValueH, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addGroup(jPanel25Layout.createSequentialGroup()
                            .addComponent(jl_SoNgayHuySom, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap())
            );
            jPanel25Layout.setVerticalGroup(
                jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel25Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel37)
                        .addComponent(NgayHuyValueH)
                        .addComponent(jl_SoNgayHuySom))
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
            TienCocValueH.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

            jLabel57.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
            jLabel57.setText("Tổng tiền hoá đơn:");

            jLabel61.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
            jLabel61.setText("Số tiền cần thanh toán:");

            ConLaiValueH.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
            ConLaiValueH.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

            TongTenHHValueH.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
            TongTenHHValueH.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

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
                            .addGap(35, 35, 35)
                            .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(TongTenHHValueH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TienCocValueH, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)))
                        .addGroup(jPanel26Layout.createSequentialGroup()
                            .addComponent(jLabel61)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(ConLaiValueH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGap(6, 6, 6))
            );
            jPanel26Layout.setVerticalGroup(
                jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel26Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TongTenHHValueH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(TienCocValueH, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel59))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel26Layout.createSequentialGroup()
                            .addComponent(jLabel61)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addComponent(ConLaiValueH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );

            jLabel62.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
            jLabel62.setText("Phương thức thanh toán:");

            jcb_PhuongThucTT.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
            jcb_PhuongThucTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoảng" }));

            javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
            jPanel27.setLayout(jPanel27Layout);
            jPanel27Layout.setHorizontalGroup(
                jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel27Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel62)
                    .addGap(18, 18, 18)
                    .addComponent(jcb_PhuongThucTT, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap())
            );
            jPanel27Layout.setVerticalGroup(
                jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel27Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel62)
                        .addComponent(jcb_PhuongThucTT, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
            );

            NextPageXNDV1.setBackground(new java.awt.Color(132, 70, 133));
            NextPageXNDV1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
            NextPageXNDV1.setForeground(new java.awt.Color(255, 255, 255));
            NextPageXNDV1.setText("Tiếp tục");
            NextPageXNDV1.setPreferredSize(new java.awt.Dimension(90, 40));
            NextPageXNDV1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    NextPageXNDV1ActionPerformed(evt);
                }
            });

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
                            .addComponent(BackPageTTHDH, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(NextPageXNDV1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PageTTHDHLayout.createSequentialGroup()
                            .addGroup(PageTTHDHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(0, 0, Short.MAX_VALUE)))
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
                        .addContainerGap()
                        .addComponent(PageTTHDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                        .addContainerGap()
                        .addComponent(PageTTHDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents
    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        intKieuThanhToan = 1;
        int selectRow = getSelectRow();
        System.out.println("adfas: " + "");
        if (selectRow < 0) {
            Message("Vui lòng chọn dữ liệu muốn chỉnh sửa!", JOptionPane.INFORMATION_MESSAGE);
        } 
        else 
        {
            int row = getSelectRow();
            String ngayDaiTiec = String.valueOf(DatTiecTable.getValueAt(row, 5));
            
            Date currentDate = new Date();
        
        // Định dạng ngày thành chuỗi theo định dạng "yyyy-MM-dd"
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String stringNgayThanhToanHienTai = dateFormat.format(currentDate);
            
            int comparison = stringNgayThanhToanHienTai.compareTo(ngayDaiTiec);
            System.out.println("adfas: " + comparison);

            String maPDTC = String.valueOf(DatTiecTable.getValueAt(row, 1));
            System.out.println("ma tiec cuoi da chon:" + maPDTC);
//                ThanhToanJDialog.setVisible(false);
            Page1.setVisible(false);
            PageTTHDH.setVisible(false);
            PageXNDV.setVisible(true);

                    
        }
        CreateTableXNDV();
        CreateTableXNDV_ADD();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
//        HuyDatTiecJDialog.setLocationRelativeTo(null);
//        HuyDatTiecJDialog.setModal(true);
//        HuyDatTiecJDialog.setVisible(true);
        if (getSelectRow() < 0) {
            Message("Vui lòng chọn dữ liệu muốn chỉnh sửa!", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            intKieuThanhToan = 2;
            Date currentDate = new Date();

            // Định dạng ngày thành chuỗi theo định dạng "yyyy-MM-dd"
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String stringNgayHuy = dateFormat.format(currentDate);

            NumberFormat currencyFormatVN = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            int row = getSelectRow();
            String maPDTC = String.valueOf(DatTiecTable.getValueAt(row, 1));
    //        Date date = jDateChooser4.getDate();
    //        String ngayHuy = YYYY_MM_DD((date.getYear() + 1900), (date.getMonth() + 1), date.getDate());
            NgayHuyValueH.setText(stringNgayHuy);
            System.out.println("Mã tiệc cới: " + maPDTC);
            ArrayList<PhieuDatTiecCuoi> huyTC = PhieuDatTiecCuoiDAO.getInstance().SelectById(maPDTC);
            for(PhieuDatTiecCuoi htc : huyTC)
            {
                String ngayDai = htc.getNgayDaiTiec();
                String ngayDat = htc.getNgayDat();
                long tongTienBan0 = htc.getTongTienBan();
                long tienCoc = htc.getTienDatCoc();

    //            idTiecCuoi = htc.getMaTiecCuoi();
    //logic
                int comparison = ngayDat.compareTo(stringNgayHuy);
                int comparison1 = stringNgayHuy.compareTo(ngayDai);
                System.out.println("Ngay dat: "+ ngayDat +" ; Ngay huy: "+ stringNgayHuy);
                System.out.println("Ngay dai: "+ ngayDai +" ; Ngay huy: "+ stringNgayHuy);
                if(comparison <= 0 && comparison1 < 0)
                {
                    //đc hủy
                    PageTTHDH.setVisible(true);
                    PageTTHDTT.setVisible(false);
                    PageThongTinDT.setVisible(false);
                    PageXNDV.setVisible(false);
                    Page1.setVisible(false);
    //                HuyDatTiecJDialog.setVisible(false);
    //                Page1.setVisible(false);
    //                PageTTHDH.setVisible(true);

                    //Thong tin tiec Cuoi
                    TenChuReValueH.setText(htc.getTenChuRe());
                    TenCoDauValueH.setText(htc.getTenCoDau());
                    SoLuongBanValueH.setText(htc.getSoLuongBan() + "");
                    DonGiaBanValueH.setText(String.valueOf(currencyFormatVN.format(htc.getDonGiaBan())));
                    NgayDTValueH.setText(htc.getNgayDaiTiec());
                    long tongTienBan = htc.getTongTienBan();
                    TongTienBanValueH.setText(String.valueOf(currencyFormatVN.format(tongTienBan)));
                    //               

                    LocalDate dateNgayDai = LocalDate.parse(ngayDai);
                    LocalDate dateNgayHuy = LocalDate.parse(stringNgayHuy);

                    String stringSoNgayHuySom = "Số ngày hủy sớm: "+ ChronoUnit.DAYS.between(dateNgayHuy, dateNgayDai) + " ngày";
                    System.out.println(stringSoNgayHuySom);
                    jl_SoNgayHuySom.setText(stringSoNgayHuySom);
                    int tgPhat = 7;
                    for (ThamSo ts : lstThamSo) {
                        tgPhat = ts.getTgPhatHuyTiec();
                    }
                    if(ChronoUnit.DAYS.between(dateNgayHuy, dateNgayDai) >= tgPhat)
                    {              
                        //Thong Tin tien Thanh Toan
                        TongTenHHValueH.setText(String.valueOf(currencyFormatVN.format(tienCoc)));
                        TienCocValueH.setText(String.valueOf(currencyFormatVN.format(tienCoc)));
                        ConLaiValueH.setText(String.valueOf(currencyFormatVN.format(0)));
                    }
                    else
                    {
                        TongTenHHValueH.setText(String.valueOf(currencyFormatVN.format(tongTienBan0 )));
                        TienCocValueH.setText(String.valueOf(currencyFormatVN.format(tienCoc)));
                        ConLaiValueH.setText(String.valueOf(currencyFormatVN.format( tongTienBan0 - tienCoc)));
                    }
                }
                else
                {
                    PageTTHDH.setVisible(false);
                    PageTTHDTT.setVisible(false);
                    PageThongTinDT.setVisible(false);
                    PageXNDV.setVisible(false);
                    if(comparison1 >= 0)
                        Message("Không thể hủy khi ngày đãi tiệc là hôm nay, hoặc tiệc cưới đã diễn ra", JOptionPane.INFORMATION_MESSAGE);
                    else
                        Message("Không thể hủy trước ngày đặt tiệc!", JOptionPane.INFORMATION_MESSAGE);
                }   


            }
        }
        
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnXemCTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemCTActionPerformed
        // TODO add your handling code here:
        int row = getSelectRow();
        if (row < 0) {
            Message("Vui lòng chọn dữ liệu muốn chỉnh sửa!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            Page1.setVisible(false);
            PageTTHDH.setVisible(false);
            PageThongTinDT.setVisible(true);
            String maPDTC = String.valueOf(DatTiecTable.getValueAt(row, 1));
            lblmaPDTC.setText(maPDTC);
            for (PhieuDatTiecCuoi pd : pdtcs) {
                if (pd.getMaTiecCuoi().equals(maPDTC)) {
                    lblngayDatTiec.setText(pd.getNgayDat());
                    lbltenChuRe.setText(pd.getTenChuRe());
                    lbltenCoDau.setText(pd.getTenCoDau());
                    lblSDT.setText(pd.getSdt());
                    lblngayDaiTiec.setText(pd.getNgayDaiTiec());
                    for (Ca c : lstCa) {
                        if (c.getMaCa().equals(pd.getMaCa())) {
                            lblCa.setText(c.getTenCa());
                        }
                    }
                    for (Sanh s : lstSanh) {
                        if (s.getMaSanh().equals(pd.getMaSanh())) {
                            lblSanh.setText(s.getTenSanh());
                        }
                    }
                    lblSLBan.setText(pd.getSoLuongBan() + "");
                    lblSoLuongBanDuTru.setText(pd.getSoLuongBanDuTru() + "");
                    lbldonGiaBan.setText(pd.getDonGiaBan() + "");
                    lbltongTienBan.setText(pd.getTongTienBan() + "");
                    lbltongTienDichVu.setText(pd.getTongTienDichVu() + "");
                    lbltienCoc.setText(pd.getTienDatCoc() + "");
                    lbltongTienHoaDon.setText(pd.getTongTienDatTiec() + "");
                    lbltienConLai.setText(pd.getConLai() + "");
                }
            }
            CreateTableDVCT();
            CreateTableMACT();
        }
    }//GEN-LAST:event_btnXemCTActionPerformed

    private void BackPageTTDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackPageTTDTActionPerformed
        // TODO add your handling code here:
        Page1.setVisible(true);
        PageThongTinDT.setVisible(false);
        defaultTableDVCT.setRowCount(0);
        defaultTableMACT.setRowCount(0);
    }//GEN-LAST:event_BackPageTTDTActionPerformed

    private void BackPageXNDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackPageXNDVActionPerformed
        // TODO add your handling code here:
        Page1.setVisible(true);
        PageXNDV.setVisible(false);
        defaultTableXNDV.setRowCount(0);
        defaultTableDV.setRowCount(0);

    }//GEN-LAST:event_BackPageXNDVActionPerformed

    private void NextPageXNDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextPageXNDVActionPerformed
        // TODO add your handling code here:
        
        NumberFormat currencyFormatVN = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        PageXNDV.setVisible(false);
        PageTTHDTT.setVisible(true);
        CreateTableXNTTHD();
        int row = getSelectRow();
        String maPDTC = String.valueOf(DatTiecTable.getValueAt(row, 1));
//        System.out.println("adfadsfadsfadf");
        LocalDate currentDate = LocalDate.now();
        
        // Định dạng ngày thành chuỗi theo định dạng "yyyy-MM-dd"
        String ngayHienTai = currentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        lblNgayThanhToan.setText(ngayHienTai);
        String strNgayDaiTiec = "";
//        System.out.println("Test ngay thanh toán: "+ngayHienTai+" , maPDTC: " + maPDTC);
        double tongTienBan = 0;
        for (PhieuDatTiecCuoi pdtc : pdtcs) {
            if (pdtc.getMaTiecCuoi().equals(maPDTC)) 
            {
                lblTenChuRe.setText(pdtc.getTenChuRe());
                lblTenCoDau.setText(pdtc.getTenCoDau());
                lblSoLuongBan.setText(pdtc.getSoLuongBan()+"");
                lblDonGiaBan.setText(currencyFormatVN.format(pdtc.getDonGiaBan()));
                lblNgayDaiTiec.setText(pdtc.getNgayDaiTiec());
                tongTienBan = pdtc.getTongTienBan();
                lblTongTienBan.setText(currencyFormatVN.format(tongTienBan));
                idTiecCuoi = pdtc.getMaTiecCuoi();
                strNgayDaiTiec = pdtc.getNgayDaiTiec();
            }
        }
        
        double sumDV = 0;
        for (ChiTietDichVu ctdv : lstDetailServices) {
            if (ctdv.getMaTiecCuoi().equals(maPDTC)) {
                sumDV += ctdv.getThanhTien();
            }
        }
                        

        lblTongTienDichVu.setText(currencyFormatVN.format((long) sumDV));
        
        double tiLePhat = 0;
        double tiLeDatCoc = 0;
        for (ThamSo ts : lstThamSo) {
            if (ts.getKiemTraPhat() == 1) {
                tiLePhat = ts.getTiLePhat();
            }
            tiLeDatCoc = ts.getTienDatCoc();
        }
        System.out.println(tiLePhat);
        System.out.println(tiLeDatCoc);
        
        LocalDate dateNgayThanhToan = LocalDate.parse(ngayHienTai);
        LocalDate dateNgayDaiTiec = LocalDate.parse(strNgayDaiTiec);

        long ngayTre = ChronoUnit.DAYS.between(dateNgayDaiTiec, dateNgayThanhToan);
        if(ngayTre > 0)
            tiLePhat = ngayTre;
        else
            tiLePhat = 0;
        jl_TienPhat.setText("Tiền phạt: ("+(long) tiLePhat+"%):");
        double tienPhat = (tiLePhat * sumDV)/100;
        double tongTienHD = sumDV + tongTienBan + tienPhat;
        double tienCoc = tongTienBan * tiLeDatCoc / 100;
        
        lblTienPhat.setText(currencyFormatVN.format((long) tienPhat));
        lblTongTienHoaDon.setText(currencyFormatVN.format((long) tongTienHD));
        lblTienCoc.setText(currencyFormatVN.format((long) tienCoc));
        lblSoTienThanhToan.setText(currencyFormatVN.format((long) tongTienHD - (long) tienCoc));
        cbPhuongThucTT.removeAll();
        cbPhuongThucTT.addItem("Tiền mặt");
        cbPhuongThucTT.addItem("Chuyển khoản");
        cbPhuongThucTT.setSelectedItem("Tiền mặt");
        lblTongTienTT.setText(lblSoTienThanhToan.getText());
        lblTienDuTT.setText("-" + lblTongTienTT.getText());
        System.out.println("abc" + lblSoTienThanhToan.getText());

    }//GEN-LAST:event_NextPageXNDVActionPerformed

    private void BackPageTTHDTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackPageTTHDTTActionPerformed
        // TODO add your handling code here:
        PageXNDV.setVisible(true);
        PageTTHDTT.setVisible(false);
        defaultTableXNTTHD.setRowCount(0);
    }//GEN-LAST:event_BackPageTTHDTTActionPerformed

    private void BackPageTTHDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackPageTTHDHActionPerformed
        // TODO add your handling code here:
        PageTTHDH.setVisible(false);
        Page1.setVisible(true);
    }//GEN-LAST:event_BackPageTTHDHActionPerformed

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
        int selectRow = getSelectRow();
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
                    ReloadTableXNDV_ADD();
                } else {
                    Message("Xoá dữ liệu thất bại!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnDeleteXNDVActionPerformed

    private void btnXacNhanDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanDVActionPerformed
        // TODO add your handling code here:
        int selectRow = getSelectRow();
        String maPDTC = String.valueOf(DatTiecTable.getValueAt(selectRow, 1));
        System.out.println(maPDTC);
        
        Number a;
        
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
                    int donGia = 0;
                    try {
                        donGia = currencyFormatVN.parse(String.valueOf(tblSelectService.getValueAt(row, 3))).intValue();
                    } catch (ParseException ex) {
                        Logger.getLogger(WeddingPartyLookup.class.getName()).log(Level.SEVERE, null, ex);
                    }
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
                        int donGia = 0;
                        try {
                            donGia = currencyFormatVN.parse(String.valueOf(tblSelectService.getValueAt(r, 3))).intValue();
                        } catch (ParseException ex) {
                            Logger.getLogger(WeddingPartyLookup.class.getName()).log(Level.SEVERE, null, ex);
                        }
//                        currencyFormatVN.parse(String.valueOf(tblSelectService.getValueAt(r, 3)).intValue();
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
                    ReloadTableXNDV_ADD();
                } else {
                    Message("Thêm dịch vụ thất bại!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnXacNhanDVActionPerformed

    private void NextPageXNTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextPageXNTTActionPerformed
        // TODO add your handling code here:
        if (cbPhuongThucTT.getSelectedItem().equals("Tiền mặt")) {
//            TienMatForm.setModal(true);
//            TienMatForm.setLocationRelativeTo(null);
//            TienMatForm.setVisible(true);
            ThanhToanTienMat.setVisible(true);
            long a = 0;
            try {
                a = currencyFormatVN.parse(lblSoTienThanhToan.getText()).longValue();
            } catch (ParseException ex) {
                Logger.getLogger(WeddingPartyLookup.class.getName()).log(Level.SEVERE, null, ex);
            }
            inputSoTienDaNhan.setText(a+"");
        } else {
            ChuyenKhoanForm.setModal(true);
            ChuyenKhoanForm.setLocationRelativeTo(null);
            ChuyenKhoanForm.setVisible(true);
        }
    }//GEN-LAST:event_NextPageXNTTActionPerformed

    private void btnThanhToanTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanTTActionPerformed
        // TODO add your handling code here:
        if (Long.parseLong(String.valueOf(lblTienDuTT.getText())) >= 0) {
            String maHD = String.valueOf(HoaDonDAO.getInstance().GetID() + 1);
            switch (maHD.length()) {
                case 1:
                    maHD = "LS000" + maHD;
                    break;
                case 2:
                    maHD = "LS00" + maHD;
                    break;
                case 3:
                    maHD = "LS0" + maHD;
                    break;
                case 4:
                    maHD = "LS" + maHD;
                    break;
            }
            Date ngayTT = new Date();
            try {
                ngayTT = new SimpleDateFormat("yyyy-MM-dd").parse(lblNgayThanhToan.getText());
            } catch (ParseException ex) {
                Logger.getLogger(WeddingPartyLookup.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            int kq = 0;
            try {
                kq = HoaDonDAO.getInstance().Insert(new HoaDon(maHD, idTiecCuoi, ngayTT, Double.parseDouble(lblTongTienDichVu.getText()), Double.parseDouble(lblTienPhat.getText()),
                        Double.parseDouble(lblTongTienHoaDon.getText()), Double.parseDouble(lblSoTienThanhToan.getText()), "sontran"));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (kq != 0) {
                Message("Thanh toán thành công.", JOptionPane.CLOSED_OPTION);
                Page1.setVisible(true);
                ReloadDataTable();
                PageTTHDTT.setVisible(false);
                PageTTHDH.setVisible(false);
                PageThongTinDT.setVisible(false);
                PageXNDV.setVisible(false);
                TienMatForm.setVisible(false);
            } else {
                Message("Lỗi!", JOptionPane.ERROR_MESSAGE);
            }
        } else
            Message("Lỗi! Không đủ tiền thanh toán! Vui lòng thanh toán lại", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_btnThanhToanTTActionPerformed

    private void txtTienNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienNhanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienNhanActionPerformed

    private void NextPageXNDV1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextPageXNDV1ActionPerformed
        // TODO add your handling code here:
//        if (jcb_PhuongThucTT.getSelectedItem().equals("Tiền mặt")) {
//
//            long TongTienCanTT = 0;
//            try {
//            NumberFormat currencyFormatVN = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
//            Number amount = currencyFormatVN.parse(String.valueOf(ConLaiValueH.getText()));
//            TongTienCanTT = amount.longValue();
//            System.out.println("Numeric amount: " + 0);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
////            long numericAmount = amount.longValue();
//            System.out.println("Tessdfasf:" + ConLaiValueH.getText());
//            lblTongTienTT.setText(String.valueOf(TongTienCanTT));
//            lblTienDuTT.setText("-" + lblTongTienTT.getText());
//            TienMatForm.setModal(true);
//            TienMatForm.setLocationRelativeTo(null);
//            TienMatForm.setVisible(true);
//        } else {
//            ChuyenKhoanForm.setModal(true);
//            ChuyenKhoanForm.setLocationRelativeTo(null);
//            ChuyenKhoanForm.setVisible(true);
//        }
        if (jcb_PhuongThucTT.getSelectedItem().equals("Tiền mặt")) {
//            TienMatForm.setModal(true);
//            TienMatForm.setLocationRelativeTo(null);
//            TienMatForm.setVisible(true);
            ThanhToanTienMat.setVisible(true);
            long a = 0;
            try {
                a = currencyFormatVN.parse(ConLaiValueH.getText()).longValue();
            } catch (ParseException ex) {
                Logger.getLogger(WeddingPartyLookup.class.getName()).log(Level.SEVERE, null, ex);
            }
            inputSoTienDaNhan.setText(a+"");
        } else {
            ChuyenKhoanForm.setModal(true);
            ChuyenKhoanForm.setLocationRelativeTo(null);
            ChuyenKhoanForm.setVisible(true);
        }
    }//GEN-LAST:event_NextPageXNDV1ActionPerformed

    private void btnmuoiTrieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmuoiTrieuActionPerformed
        // TODO add your handling code here:
        inputSoTienDaNhan.setText("10000000");
    }//GEN-LAST:event_btnmuoiTrieuActionPerformed

    private void btnhaiMuoiTrieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhaiMuoiTrieuActionPerformed
        // TODO add your handling code here:
        inputSoTienDaNhan.setText("20000000");
    }//GEN-LAST:event_btnhaiMuoiTrieuActionPerformed

    private void btn50trActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn50trActionPerformed
        // TODO add your handling code here:
        inputSoTienDaNhan.setText("50000000");
    }//GEN-LAST:event_btn50trActionPerformed

    private void btn70trActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn70trActionPerformed
        // TODO add your handling code here:
        inputSoTienDaNhan.setText("70000000");
    }//GEN-LAST:event_btn70trActionPerformed

    private void btn90trActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn90trActionPerformed
        // TODO add your handling code here:
        inputSoTienDaNhan.setText("90000000");
    }//GEN-LAST:event_btn90trActionPerformed

    private void btn100trActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn100trActionPerformed
        // TODO add your handling code here:
        inputSoTienDaNhan.setText("100000000");
    }//GEN-LAST:event_btn100trActionPerformed

    private void btn200trActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn200trActionPerformed
        // TODO add your handling code here:
        inputSoTienDaNhan.setText("200000000");
    }//GEN-LAST:event_btn200trActionPerformed

    private void btn300trActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn300trActionPerformed
        // TODO add your handling code here:
        inputSoTienDaNhan.setText("300000000");
    }//GEN-LAST:event_btn300trActionPerformed

    private void btn400trActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn400trActionPerformed
        // TODO add your handling code here:
        inputSoTienDaNhan.setText("400000000");
    }//GEN-LAST:event_btn400trActionPerformed

    private void btn500trActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn500trActionPerformed
        // TODO add your handling code here:
        inputSoTienDaNhan.setText("500000000");
    }//GEN-LAST:event_btn500trActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        inputSoTienDaNhan.setText("0");
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        // TODO add your handling code here:
        if(intKieuThanhToan == 1)
        {
            //loại thanh toán tiệc cưới
            String maHoaDon = String.valueOf(HoaDonDAO.getInstance().GetID() + 1);
            switch (maHoaDon.length()) {
                case 1:
                maHoaDon = "HD000" + maHoaDon;
                break;
                case 2:
                maHoaDon = "HD00" + maHoaDon;
                break;
                case 3:
                maHoaDon = "HD0" + maHoaDon;
                break;
                case 4:
                maHoaDon = "HD" + maHoaDon;
                break;
            }
    //        LocalDate ngayTT = LocalDate.now();
    //        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    //        String strNgayTT = ngayTT.format(formatter);
            Calendar calendar = Calendar.getInstance();
            Date ngayTT = calendar.getTime();
            long tongTienDV = 0;
            long tienPhat = 0;
            long tongTienHoaDon = 0;
            long conLai = 0;
            try {
                tongTienDV = currencyFormatVN.parse(String.valueOf(lblTongTienDichVu.getText())).intValue();
                tienPhat = currencyFormatVN.parse(String.valueOf(lblTienPhat.getText())).intValue();
                tongTienHoaDon = currencyFormatVN.parse(String.valueOf(lblTongTienHoaDon.getText())).intValue();
                conLai = currencyFormatVN.parse(String.valueOf(lblSoTienThanhToan.getText())).intValue();
            } catch (ParseException ex) {
                Logger.getLogger(WeddingPartyLookup.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(Long.parseLong(inputSoTienDaNhan.getText()) >= conLai)
            {
                String userName = "taitai";
                    String maPDTC = String.valueOf(DatTiecTable.getValueAt(getSelectRow(), 1));
                int kq = 0;

                try {
                    kq = HoaDonDAO.getInstance().Insert(new HoaDon(maHoaDon, maPDTC, ngayTT, tongTienDV, tienPhat, tongTienHoaDon, conLai, userName));
                    if(kq != 0)
                    {
                        Message("Thanh toán thành công!", JOptionPane.INFORMATION_MESSAGE);
                        ThanhToanTienMat.setVisible(false);
                        Page1.setVisible(true);
                        PageTTHDH.setVisible(false);
                        PageTTHDTT.setVisible(false);
                        PageThongTinDT.setVisible(false);
                        ReloadDataTable();
                    }
                    else
                    {
                        System.out.println("Thêm hóa đơn lỗi");
                        Message("Thanh toán không thành công!", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("Cũng là lỗi! Thêm hóa đơn lỗi");
                    Message("Thanh toán không thành công nha!", JOptionPane.ERROR_MESSAGE);
                }

            }
            else
            {
                 Message("Quy khách không đủ tiền để thanh toán!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else
        {
            //loại hủy tiệc cưới
            String maHoaDon = String.valueOf(HoaDonDAO.getInstance().GetID() + 1);
            switch (maHoaDon.length()) {
                case 1:
                maHoaDon = "HD000" + maHoaDon;
                break;
                case 2:
                maHoaDon = "HD00" + maHoaDon;
                break;
                case 3:
                maHoaDon = "HD0" + maHoaDon;
                break;
                case 4:
                maHoaDon = "HD" + maHoaDon;
                break;
            }
    //        LocalDate ngayTT = LocalDate.now();
    //        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    //        String strNgayTT = ngayTT.format(formatter);
            Calendar calendar = Calendar.getInstance();
            Date ngayTT = calendar.getTime();
            long tongTienDV = 0;
            long tienPhat = 0;
            long tongTienHoaDon = 0;
            long conLai = 0;
            try {
//                tienPhat = currencyFormatVN.parse(String.valueOf(lblTienPhat.getText())).longValue();
                tongTienHoaDon = currencyFormatVN.parse(String.valueOf(TongTenHHValueH.getText())).longValue();
                conLai = currencyFormatVN.parse(String.valueOf(ConLaiValueH.getText())).longValue();
            } catch (ParseException ex) {
                Logger.getLogger(WeddingPartyLookup.class.getName()).log(Level.SEVERE, null, ex);
            }

            if(Long.parseLong(inputSoTienDaNhan.getText()) >= conLai)
            {
                String userName = "taitai";
                    String maPDTC = String.valueOf(DatTiecTable.getValueAt(getSelectRow(), 1));
                int kq = 0;

                try {
                    kq = HoaDonDAO.getInstance().Insert(new HoaDon(maHoaDon, maPDTC, ngayTT, tongTienDV, tienPhat, tongTienHoaDon, conLai, userName));
                    if(kq != 0)
                    {
                        Message("Thanh toán thành công!", JOptionPane.INFORMATION_MESSAGE);
                        ThanhToanTienMat.setVisible(false);
                        Page1.setVisible(true);
                        PageTTHDH.setVisible(false);
                        PageTTHDTT.setVisible(false);
                        PageThongTinDT.setVisible(false);
                        ReloadDataTable();
                    }
                    else
                    {
                        System.out.println("Thêm hóa đơn lỗi");
                        Message("Thanh toán không thành công!", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("Cũng là lỗi! Thêm hóa đơn lỗi");
                    Message("Thanh toán không thành công nha!", JOptionPane.ERROR_MESSAGE);
                }

            }
            else
            {
                 Message("Quy khách không đủ tiền để thanh toán!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        

//        try {
//            ngayTT = new SimpleDateFormat("yyyy-MM-dd").parse(lblNgayThanhToan.getText());
//        } catch (ParseException ex) {
//            Logger.getLogger(WeddingPartyLookup.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        int kq1 = 0;
//        try {
//            kq1 = HoaDonDAO.getInstance().Insert(new HoaDon( maHoaDon, idTiecCuoi, ngayTT, Double.parseDouble(lblTongTienDichVu.getText()), Double.parseDouble(lblTienPhat.getText()),
//                   Double.parseDouble(lblTongTienHoaDon.getText()),  Double.parseDouble(lblSoTienThanhToan.getText()), userName));
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        int selectRow = getSelectRow();
//        String maPDTC = String.valueOf(DatTiecTable.getValueAt(selectRow, 1));
//
//        int flag = 1;
//        if (kq1 > 0) {
//            int kq2 = 0;
//    //        for (ChiTietDichVu x : CTDichVus) {
//    //            int temp = x.getSoLuong() * x.getDonGia();
//    //            kq3 = ChiTietDichVuDAO.getInstance().Insert(new ChiTietDichVu(maTiecCuoi, x.getMaDichVu(), x.getSoLuong(), x.getDonGia(), temp));
//    //            if (kq3 == 0) {
//    //                flag = 0;
//    //                break;
//    //            }
//    //        }
//            
//
//            for (ChiTietDichVu x : lstDetailServices) {
//                if (x.getMaTiecCuoi().equals(maPDTC)) {
//    //                defaultTableXNTTHD.addRow(new Object[]{++i, ctdvtt.getMaDichVu(), ctdvtt.getTenDichVu(), ctdvtt.getSoLuong(),
//    //                    ctdvtt.getDonGiaDichVu(), (long) ctdvtt.getThanhTien()});
//                    int temp = x.getSoLuong() * x.getDonGia();
//                    kq2 = ChiTietDichVuDAO.getInstance().Insert(new ChiTietDichVu(maPDTC,  x.getMaDichVu(), x.getSoLuong(), x.getDonGia(), temp));
//                }
//                if (kq2 == 0) {
//                    flag = 0;
//                    break;
//                }
//            }
//        }
//
//        if (flag > 0) {
//            File file = new File("src/report/rptPhieuDatTiec.jasper");
//            String absolutePath = file.getAbsolutePath();
//            try {
//                HashMap<String, Object> map = new HashMap<>();
//                Connection con = JDBCUtil.getConnection();
//                map.put("maTiecCuoi", maPDTC);
//                map.put("tienKhachTra", Double.parseDouble(inputSoTienDaNhan.getText()));
//                map.put("tienThua", Double.parseDouble(inputSoTienDaNhan.getText()) - Double.parseDouble(lblTienCoc.getText()));
//                map.put("tyLePhat", 1);
//                map.put("thoiGianPhat", 7);
////                System.out.println(maTiecCuoi);
////                System.out.println(Double.parseDouble(inputSoTienDaNhan.getText()));
////                System.out.println(Double.parseDouble(inputSoTienDaNhan.getText()) - tienCoc);
////                System.out.println(tyLePhat);
////                System.out.println(thoiGianPhat);
//                JasperPrint p = JasperFillManager.fillReport(absolutePath, map, con);
//                JasperViewer v = new JasperViewer(p, false);
//                v.setVisible(true);
//
//            } catch (JRException ex) {
//                System.out.println(ex);
//
//            }
//        }

    }//GEN-LAST:event_btnXacNhanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog AddSelectServices;
    private javax.swing.JButton BackPageTTDT;
    private javax.swing.JButton BackPageTTHDH;
    private javax.swing.JButton BackPageTTHDTT;
    private javax.swing.JButton BackPageXNDV;
    private javax.swing.JTextField BrideNameText;
    private javax.swing.JLabel CaValue;
    private javax.swing.JDialog ChuyenKhoanForm;
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
    private javax.swing.JTextField IdWeddingText;
    private javax.swing.JLabel MaDTValue;
    private javax.swing.JTable MonAnTable;
    private javax.swing.JButton NextPageXNDV;
    private javax.swing.JButton NextPageXNDV1;
    private javax.swing.JButton NextPageXNTT;
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
    private javax.swing.JDialog ThanhToanTienMat;
    private javax.swing.JLabel TienCocValue;
    private javax.swing.JLabel TienCocValueH;
    private javax.swing.JLabel TienCocValueTT;
    private javax.swing.JDialog TienMatForm;
    private javax.swing.JLabel TienPhatValueTT;
    private javax.swing.JComboBox<String> TimeCbBox;
    private javax.swing.JLabel TongTenHHValueH;
    private javax.swing.JLabel TongTienBanValue;
    private javax.swing.JLabel TongTienBanValueH;
    private javax.swing.JLabel TongTienBanValueTT;
    private javax.swing.JLabel TongTienDVValue;
    private javax.swing.JLabel TongTienDVValueTT;
    private javax.swing.JLabel TongTienHDValue;
    private javax.swing.JLabel TongTienHDValueTT;
    private javax.swing.JButton btn100tr;
    private javax.swing.JButton btn200tr;
    private javax.swing.JButton btn300tr;
    private javax.swing.JButton btn400tr;
    private javax.swing.JButton btn500tr;
    private javax.swing.JButton btn50tr;
    private javax.swing.JButton btn70tr;
    private javax.swing.JButton btn90tr;
    private javax.swing.JButton btnAddXNDV;
    private javax.swing.JButton btnDeleteXNDV;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThanhToanTT;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JButton btnXacNhanDV;
    private javax.swing.JButton btnXemCT;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnhaiMuoiTrieu;
    private javax.swing.JButton btnmuoiTrieu;
    private javax.swing.JComboBox<String> cbPhuongThucTT;
    private javax.swing.JTextField inputSoTienDaNhan;
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
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JComboBox<String> jcb_PhuongThucTT;
    private javax.swing.JLabel jl_SoNgayHuySom;
    private javax.swing.JLabel jl_TienPhat;
    private javax.swing.JLabel lblCa;
    private javax.swing.JLabel lblDonGiaBan;
    private javax.swing.JLabel lblNgayDaiTiec;
    private javax.swing.JLabel lblNgayThanhToan;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblSLBan;
    private javax.swing.JLabel lblSanh;
    private javax.swing.JLabel lblSoLuongBan;
    private javax.swing.JLabel lblSoLuongBanDuTru;
    private javax.swing.JLabel lblSoTienThanhToan;
    private javax.swing.JLabel lblTenChuRe;
    private javax.swing.JLabel lblTenCoDau;
    private javax.swing.JLabel lblTienCoc;
    private javax.swing.JLabel lblTienDuTT;
    private javax.swing.JLabel lblTienPhat;
    private javax.swing.JLabel lblTongTienBan;
    private javax.swing.JLabel lblTongTienDichVu;
    private javax.swing.JLabel lblTongTienHoaDon;
    private javax.swing.JLabel lblTongTienTT;
    private javax.swing.JLabel lbldonGiaBan;
    private javax.swing.JLabel lblmaPDTC;
    private javax.swing.JLabel lblngayDaiTiec;
    private javax.swing.JLabel lblngayDatTiec;
    private javax.swing.JPanel lblsoLuongBan;
    private javax.swing.JLabel lbltenChuRe;
    private javax.swing.JLabel lbltenCoDau;
    private javax.swing.JLabel lbltienCoc;
    private javax.swing.JLabel lbltienConLai;
    private javax.swing.JLabel lbltongTienBan;
    private javax.swing.JLabel lbltongTienDichVu;
    private javax.swing.JLabel lbltongTienHoaDon;
    private javax.swing.JTable tblSelectService;
    private javax.swing.JTextField txtTienNhan;
    // End of variables declaration//GEN-END:variables

    private JComboBox<String> JcomboBox() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
