/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import dao.CaDAO;
import dao.ChiTietDichVuDAO;
import dao.ChiTietMonAnDAO;
import dao.DichVuDAO;
import dao.LoaiMonAnDAO;
import dao.MonAnDAO;
import dao.PhieuDatTiecCuoiDAO;
import dao.SanhDAO;
import dao.ThamSoDAO;
import dao.systemDAO;
import database.JDBCUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import model.Ca;
import model.ChiTietDichVu;
import model.ChiTietMonAn;
import model.DTDichVu;
import model.DTMonAn;
import model.DichVu;
import model.LoaiMonAn;
import model.MonAn;
import model.PhieuDatTiecCuoi;
import model.Sanh;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author macbookpro
 *
 */
public class BookingPartyWedding extends javax.swing.JInternalFrame {

    private NumberFormat currencyFormatVN = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
    private DefaultTableModel defaultTableModelMA;
    private DefaultTableModel defaultTableModelDV;
    private DefaultTableModel modelDV;
    private String PHONE_NUMBER_REGEX = "^\\d{10}$";
    private String websiteCode = "2CWHQAYN";
    private static final String API_URL = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html"; // Đổi thành URL môi trường thực khi tích hợp
    private static final String HASH_ALGORITHM = "SHA-256";
    private static final String SECRET_KEY = "BYQDPJGHTPBNKGRTZXDACLWAFPOBTCFS";
    private int SoLuongBanToiDa;
    private int donGiaBanToiThieu;
    private int tongDonBanHienTai = 0;
    private int tongSLB;
    private int tongtienban;
    private int tongTienDV = 0;
    private int tongtienHD;
    private double tienCoc;
    private int conLai;
    private int thoiGianDatTiec = ThamSoDAO.getInstance().GetThoiGianDatTiec();
    private int tyLePhat = ThamSoDAO.getInstance().GetTyLePhat();
    private int thoiGianPhat = ThamSoDAO.getInstance().GetThoiGianPhat();
    private int TiLeCoc = ThamSoDAO.getInstance().GetTyLeCoc();
    private Map<String, String> mapMaSanh = new HashMap<>();
    private Map<String, String> mapMaLoaiMA = new HashMap<>();
    private Map<String, String> mapMaCa = new HashMap<>();
    private ArrayList<MonAn> monAns = MonAnDAO.getInstance().SelectAll();
    private ArrayList<DTMonAn> dTMonAns = new ArrayList<>();
    private ArrayList<DichVu> dichVus = DichVuDAO.getInstance().SelectAll();
    private ArrayList<DTDichVu> dTDichVus = new ArrayList<>();
    private ArrayList<Sanh> sanhs;
    private ArrayList<DTMonAn> CTMonAns = new ArrayList<>();
    private ArrayList<DTDichVu> CTDichVus = new ArrayList<>();
    private ArrayList<Ca> Cas = CaDAO.getInstance().SelectAll();
    private ArrayList<LoaiMonAn> loaiMonAns = LoaiMonAnDAO.getInstance().SelectAll();

    /**
     * Creates new form BookingPartyWedding
     */
    public BookingPartyWedding() {

        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);

        Page1.setVisible(true);
        Page2.setVisible(false);
        Page3.setVisible(false);
        Page4.setVisible(false);
        Page1();

    }

    public void Page1() {
        inputSanh.removeAllItems();
        inputSanh.addItem("<Không có dữ liệu>");
        tongDonBanHienTai = 0;
        tongTienDV = 0;
        thoiGianDatTiec = ThamSoDAO.getInstance().GetThoiGianDatTiec();
        tyLePhat = ThamSoDAO.getInstance().GetTyLePhat();
        thoiGianPhat = ThamSoDAO.getInstance().GetThoiGianPhat();
        TiLeCoc = ThamSoDAO.getInstance().GetTyLeCoc();
        mapMaSanh = new HashMap<>();
        mapMaLoaiMA = new HashMap<>();
        monAns = MonAnDAO.getInstance().SelectAll();
        dTMonAns = new ArrayList<>();
        dichVus = DichVuDAO.getInstance().SelectAll();
        dTDichVus = new ArrayList<>();
        sanhs = new ArrayList<>();
        CTMonAns = new ArrayList<>();
        CTDichVus = new ArrayList<>();
        Cas = CaDAO.getInstance().SelectAll();
        loaiMonAns = LoaiMonAnDAO.getInstance().SelectAll();
        inputTyLeTienCoc.setText(String.valueOf(TiLeCoc + "%"));
        inputNgayDatTiec.setDateFormatString("dd/MM/yyyy");
        inputNgayDatTiec.setDate(new java.util.Date());
        inputNgayDaiTiec.setDateFormatString("dd/MM/yyyy");

        if (checkcb == 0) {
            for (Ca x : Cas) {
                inputCa.addItem(x.getTenCa() + " (" + x.getGioBatDau() + " - " + x.getGioKetThuc() + ")");
                mapMaCa.put(x.getTenCa(), x.getMaCa());
            }
        }
        checkcb++;
        for (LoaiMonAn x : loaiMonAns) {
            mapMaLoaiMA.put(x.getTenLoaiMonAn(), x.getMaLoaiMonAn());
            cbxLoaiMonAn.addItem(x.getTenLoaiMonAn());
        }

        defaultTableModelMA = (DefaultTableModel) ThucDonTable.getModel();

        for (MonAn x : monAns) {
            System.out.println(currencyFormatVN.format(x.getDonGia()));
            DTMonAn Temp = new DTMonAn(x.getMaMonAn(), x.getTenMonAn(), x.getDonGia(), x.getMaLoaiMonAn());
            dTMonAns.add(Temp);
        }
        CreateValueTableMA();

        defaultTableModelDV = (DefaultTableModel) DichVuTable.getModel();

        for (DichVu x : dichVus) {
            dTDichVus.add(new DTDichVu(x.getMaDichVu(), x.getTenDichVu(), x.getDonGia()));
        }

        CreateValueTableDV();

        defaultTableModelMA.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int row = e.getFirstRow();
                    int column = e.getColumn();

                    if (column == 4) {
                        if ((boolean) ThucDonTable.getValueAt(row, 5)) {
                            UpdateGhiChu(ThucDonTable.getValueAt(row, column).toString(), row);
                            for (DTMonAn x : CTMonAns) {
                                if (x.getMaMonAn().equals(ThucDonTable.getValueAt(row, 1).toString())) {
                                    x.setGhiChu(ThucDonTable.getValueAt(row, column).toString());
                                    return;
                                }
                            }
                        }
                    }

                }
            }
        });

        defaultTableModelDV.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int row = DichVuTable.getSelectedRow();
                    int column = DichVuTable.getSelectedColumn();
                    System.out.println(row + " " + column);
                    if (column == 3) {
                        boolean chon = (boolean) DichVuTable.getValueAt(row, 5);
                        if (chon) {
                            String soLuong = DichVuTable.getValueAt(row, column).toString();
                            if (soLuong.equals("")) {
                                defaultTableModelDV.setValueAt(1, row, 3);
                            }
                            if (Integer.parseInt(soLuong) < 0) {
                                defaultTableModelDV.setValueAt(1, row, 3);
                            }
                            for (DTDichVu X : dTDichVus) {
                                if (X.getMaDichVu().equals(DichVuTable.getValueAt(row, 1).toString())) {
                                    X.setSoLuong(Integer.parseInt(DichVuTable.getValueAt(row, 3).toString()));
                                }
                            }
                        } else {
                            String soLuong = DichVuTable.getValueAt(row, column).toString();
                            if (soLuong.equals("")) {
                                defaultTableModelDV.setValueAt(0, row, 3);
                            }
                            if (Integer.parseInt(soLuong) < 0) {
                                defaultTableModelDV.setValueAt(0, row, 3);
                            }
                            for (DTDichVu X : dTDichVus) {
                                if (X.getMaDichVu().equals(DichVuTable.getValueAt(row, 1).toString())) {
                                    X.setSoLuong(Integer.parseInt(DichVuTable.getValueAt(row, 3).toString()));
                                }
                            }
                        }
                    }

                }
            }
        });
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.RIGHT); // Set the desired horizontal alignment

        // Set the custom cell renderer to the desired column
        DichVuDatTiecTable.getColumnModel().getColumn(5).setCellRenderer(renderer); // Column index 
        DichVuDatTiecTable.getColumnModel().getColumn(4).setCellRenderer(renderer);
        DichVuTable.getColumnModel().getColumn(3).setCellRenderer(renderer);
        DichVuTable.getColumnModel().getColumn(4).setCellRenderer(renderer);
        ThucDonTable.getColumnModel().getColumn(3).setCellRenderer(renderer);
    }

    public void Page2() {
        DonBanToiThieu.setText(String.valueOf(currencyFormatVN.format(donGiaBanToiThieu)));
        TongDonBanHT.setText(String.valueOf(currencyFormatVN.format(tongDonBanHienTai)));
    }

    public void Page4() {
        NumberFormat currencyFormatVN = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        TenChuRe.setText(inputTenChuRe.getText());
        TenCoDau.setText(inputTenCoDau.getText());
        Date ngayDaiTiec = inputNgayDaiTiec.getDate();
        int day = ngayDaiTiec.getDate();
        int month = ngayDaiTiec.getMonth() + 1;
        int year = ngayDaiTiec.getYear() + 1900;
        NgayDaiTiec.setText(day + "/" + month + "/" + year);
        tongSLB = Integer.parseInt(inputSoLuongBan.getValue().toString()) + Integer.parseInt(inputSLDT.getValue().toString());
        SoLuongBan.setText(String.valueOf(tongSLB));
        DonGiaBan.setText(String.valueOf(currencyFormatVN.format(tongDonBanHienTai)));
        tongtienban = tongSLB * tongDonBanHienTai;
        TongTienBan.setText(String.valueOf(currencyFormatVN.format(tongtienban)));

        modelDV = (DefaultTableModel) DichVuDatTiecTable.getModel();
        modelDV.setRowCount(0);
        int i = 0;
        tongTienDV = 0;
        for (DTDichVu x : CTDichVus) {
            int temp = x.getSoLuong() * x.getDonGia();
            tongTienDV += temp;
            modelDV.addRow(new Object[]{++i, x.getMaDichVu(), x.getTenDichVu(), x.getSoLuong(), currencyFormatVN.format(x.getDonGia()), currencyFormatVN.format(temp)});
        }

        TongTienDV.setText(String.valueOf(currencyFormatVN.format(tongTienDV)));
        tienCocText.setText("Tiền cọc (" + TiLeCoc + "%):");
        tongtienHD = tongtienban + tongTienDV;
        TongTienHD.setText(String.valueOf(currencyFormatVN.format(tongtienHD)));
        tienCoc = (double) ((double) TiLeCoc / 100) * (double) tongtienban;
        TienCoc.setText(String.valueOf(currencyFormatVN.format((int) tienCoc)));
        conLai = tongtienHD - (int) tienCoc;
        ConLai.setText(String.valueOf(currencyFormatVN.format(conLai)));

    }
    private int checkcb = 0;

    public boolean validatePhoneNumber() {
        Pattern pattern = Pattern.compile(PHONE_NUMBER_REGEX);
        Matcher matcher = pattern.matcher(inputSDT.getText());
        return matcher.matches();
    }

    public void Message(String message, int messageType) {
        JOptionPane jOptionPane = new JOptionPane(message, messageType);
        JDialog dialog = jOptionPane.createDialog(null, "Message");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    public void CreateValueTableMA() {
        defaultTableModelMA.setRowCount(0);
        int i = 0;
        for (DTMonAn x : dTMonAns) {
            if (x.getMaLoaiMonAn().equals(mapMaLoaiMA.get(cbxLoaiMonAn.getSelectedItem()))) {
                defaultTableModelMA.addRow(new Object[]{++i, x.getMaMonAn(), x.getTenMonAn(), currencyFormatVN.format(x.getDonGia()), x.getGhiChu(), x.getChon()});
            }
        }
    }

    public void CreateValueTableDV() {
        defaultTableModelDV.setRowCount(0);
        int i = 0;
        for (DTDichVu x : dTDichVus) {
            defaultTableModelDV.addRow(new Object[]{++i, x.getMaDichVu(), x.getTenDichVu(), x.getSoLuong(), currencyFormatVN.format(x.getDonGia()), x.isChon()});
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

        FilterFood = new javax.swing.JDialog();
        SilderFood = new javax.swing.JSlider();
        jPanel18 = new javax.swing.JPanel();
        KhoangDonGia = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        ValueDGMAX = new javax.swing.JLabel();
        ExitSearchFood = new javax.swing.JButton();
        SaveSearchFood = new javax.swing.JButton();
        ThanhToanTienMat = new javax.swing.JDialog();
        jLabel17 = new javax.swing.JLabel();
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
        ChuyenKhoanForm = new javax.swing.JDialog();
        btnDaThanhToan = new javax.swing.JButton();
        btnCancelCKForm = new javax.swing.JButton();
        Page1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        inputTenChuRe = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        inputTenCoDau = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        inputSDT = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        inputNgayDatTiec = new com.toedter.calendar.JDateChooser();
        jPanel14 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        inputNgayDaiTiec = new com.toedter.calendar.JDateChooser();
        jPanel15 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        inputTyLeTienCoc = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        inputSanh = new javax.swing.JComboBox<>();
        jPanel17 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        inputSLDT = new javax.swing.JSpinner();
        jPanel19 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        inputSoLuongBan = new javax.swing.JSpinner();
        jPanel20 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        inputCa = new javax.swing.JComboBox<>();
        NextPage1 = new javax.swing.JButton();
        Page2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txfSearchFood = new javax.swing.JTextField();
        FilterFoodButton = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ThucDonTable = new javax.swing.JTable();
        jPanel22 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        cbxLoaiMonAn = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        BackPage2 = new javax.swing.JButton();
        NextPage2 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        DonBanToiThieu = new javax.swing.JLabel();
        TongDonBanHT = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        Page3 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        txfSearchService = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        DichVuTable = new javax.swing.JTable();
        jPanel39 = new javax.swing.JPanel();
        BackPage3 = new javax.swing.JButton();
        NextPage3 = new javax.swing.JButton();
        Page4 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        SoLuongBan = new javax.swing.JLabel();
        TenChuRe = new javax.swing.JLabel();
        DonGiaBan = new javax.swing.JLabel();
        TenCoDau = new javax.swing.JLabel();
        TongTienBan = new javax.swing.JLabel();
        NgayDaiTiec = new javax.swing.JLabel();
        jPanel43 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        DichVuDatTiecTable = new javax.swing.JTable();
        jPanel46 = new javax.swing.JPanel();
        jPanel49 = new javax.swing.JPanel();
        cbxPTTT = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        BackPage4 = new javax.swing.JButton();
        NextPage5 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        TongTienDV = new javax.swing.JLabel();
        TongTienHD = new javax.swing.JLabel();
        TienCoc = new javax.swing.JLabel();
        ConLai = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        tienCocText = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();

        FilterFood.setBackground(new java.awt.Color(255, 255, 255));
        FilterFood.setMinimumSize(new java.awt.Dimension(400, 300));

        SilderFood.setBackground(new java.awt.Color(255, 255, 255));
        SilderFood.setForeground(new java.awt.Color(99, 122, 48));
        SilderFood.setMajorTickSpacing(10000);
        SilderFood.setMaximum(2000000);
        SilderFood.setValue(2000000);
        SilderFood.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SilderFoodStateChanged(evt);
            }
        });

        KhoangDonGia.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        KhoangDonGia.setText("0 - 2000000");

        jLabel38.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel38.setText("Đơn giá món ăn");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
                .addComponent(KhoangDonGia)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(KhoangDonGia))
                .addContainerGap())
        );

        jLabel40.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel40.setText("0");

        ValueDGMAX.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        ValueDGMAX.setText("2000000");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ValueDGMAX)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(ValueDGMAX))
                .addContainerGap())
        );

        ExitSearchFood.setBackground(new java.awt.Color(69, 96, 134));
        ExitSearchFood.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        ExitSearchFood.setForeground(new java.awt.Color(255, 255, 255));
        ExitSearchFood.setText("Huỷ");
        ExitSearchFood.setPreferredSize(new java.awt.Dimension(90, 40));
        ExitSearchFood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitSearchFoodActionPerformed(evt);
            }
        });

        SaveSearchFood.setBackground(new java.awt.Color(132, 70, 133));
        SaveSearchFood.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        SaveSearchFood.setForeground(new java.awt.Color(255, 255, 255));
        SaveSearchFood.setText("Lưu");
        SaveSearchFood.setPreferredSize(new java.awt.Dimension(90, 40));
        SaveSearchFood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveSearchFoodActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FilterFoodLayout = new javax.swing.GroupLayout(FilterFood.getContentPane());
        FilterFood.getContentPane().setLayout(FilterFoodLayout);
        FilterFoodLayout.setHorizontalGroup(
            FilterFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FilterFoodLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FilterFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SilderFood, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(FilterFoodLayout.createSequentialGroup()
                        .addComponent(ExitSearchFood, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 188, Short.MAX_VALUE)
                        .addComponent(SaveSearchFood, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        FilterFoodLayout.setVerticalGroup(
            FilterFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FilterFoodLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(SilderFood, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addGroup(FilterFoodLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SaveSearchFood, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExitSearchFood, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        ThanhToanTienMat.setBackground(new java.awt.Color(255, 255, 255));
        ThanhToanTienMat.setSize(new java.awt.Dimension(427, 400));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel17.setText("Số tiền đã nhận:");

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
                    .addComponent(jLabel17)
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
                .addComponent(jLabel17)
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

        btnDaThanhToan.setBackground(new java.awt.Color(132, 70, 133));
        btnDaThanhToan.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnDaThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnDaThanhToan.setText("Đã thanh toán");
        btnDaThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDaThanhToanActionPerformed(evt);
            }
        });

        btnCancelCKForm.setBackground(new java.awt.Color(69, 96, 134));
        btnCancelCKForm.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnCancelCKForm.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelCKForm.setText("Cancel");
        btnCancelCKForm.setMaximumSize(new java.awt.Dimension(72, 40));
        btnCancelCKForm.setMinimumSize(new java.awt.Dimension(72, 40));
        btnCancelCKForm.setPreferredSize(new java.awt.Dimension(72, 40));

        javax.swing.GroupLayout ChuyenKhoanFormLayout = new javax.swing.GroupLayout(ChuyenKhoanForm.getContentPane());
        ChuyenKhoanForm.getContentPane().setLayout(ChuyenKhoanFormLayout);
        ChuyenKhoanFormLayout.setHorizontalGroup(
            ChuyenKhoanFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ChuyenKhoanFormLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCancelCKForm, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                .addComponent(btnDaThanhToan)
                .addContainerGap())
        );
        ChuyenKhoanFormLayout.setVerticalGroup(
            ChuyenKhoanFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ChuyenKhoanFormLayout.createSequentialGroup()
                .addContainerGap(254, Short.MAX_VALUE)
                .addGroup(ChuyenKhoanFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDaThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelCKForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setPreferredSize(new java.awt.Dimension(1170, 730));

        Page1.setBackground(new java.awt.Color(255, 255, 255));
        Page1.setPreferredSize(new java.awt.Dimension(1170, 730));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setPreferredSize(new java.awt.Dimension(1000, 100));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Group 7.png"))); // NOI18N
        jPanel3.add(jLabel2);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel1.setText("Thông tin khách hàng");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel3.setText("Tên chú rể");

        inputTenChuRe.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        inputTenChuRe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputTenChuReActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(inputTenChuRe, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputTenChuRe, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel4.setText("Tên cô dâu");

        inputTenCoDau.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(inputTenCoDau, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputTenCoDau, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel5.setText("Số điện thoại");

        inputSDT.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(inputSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(126, 126, 126))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel6.setText("Thông tin tiệc cưới");

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel7.setText("Ngày đặt tiệc");

        inputNgayDatTiec.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputNgayDatTiec, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputNgayDatTiec, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel8.setText("Ngày đãi tiệc");

        inputNgayDaiTiec.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        inputNgayDaiTiec.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                inputNgayDaiTiecPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(inputNgayDaiTiec, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputNgayDaiTiec, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel9.setText("Tỷ lệ tiền cọc");

        inputTyLeTienCoc.setEditable(false);
        inputTyLeTienCoc.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        inputTyLeTienCoc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(inputTyLeTienCoc)
                        .addContainerGap())
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputTyLeTienCoc, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel10.setText("Sảnh");

        inputSanh.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        inputSanh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Không có dữ liệu>" }));
        inputSanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputSanhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(inputSanh, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputSanh, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel11.setText("Số lượng bàn dự trữ");

        inputSLDT.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        inputSLDT.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        inputSLDT.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                inputSLDTStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(170, 170, 170))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                        .addComponent(inputSLDT)
                        .addContainerGap())))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputSLDT, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel13.setText("Số lượng bàn");

        inputSoLuongBan.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        inputSoLuongBan.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        inputSoLuongBan.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                inputSoLuongBanStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(inputSoLuongBan, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputSoLuongBan, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel14.setText("Ca");

        inputCa.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        inputCa.setToolTipText("");
        inputCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputCaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(inputCa, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputCa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        NextPage1.setBackground(new java.awt.Color(132, 70, 133));
        NextPage1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        NextPage1.setForeground(new java.awt.Color(255, 255, 255));
        NextPage1.setText("Tiếp tục");
        NextPage1.setPreferredSize(new java.awt.Dimension(90, 40));
        NextPage1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextPage1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NextPage1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(126, 126, 126))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(NextPage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout Page1Layout = new javax.swing.GroupLayout(Page1);
        Page1.setLayout(Page1Layout);
        Page1Layout.setHorizontalGroup(
            Page1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 1170, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Page1Layout.setVerticalGroup(
            Page1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Page1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Page2.setBackground(new java.awt.Color(255, 255, 255));
        Page2.setForeground(new java.awt.Color(255, 255, 255));
        Page2.setPreferredSize(new java.awt.Dimension(1170, 730));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Group 1000001481.png"))); // NOI18N
        jPanel1.add(jLabel15);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(1170, 124));

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(69, 96, 134));
        jLabel12.setText("BẢNG THỰC ĐƠN");

        jPanel5.setBackground(new java.awt.Color(238, 230, 226));
        jPanel5.setPreferredSize(new java.awt.Dimension(260, 50));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Group 9.png"))); // NOI18N

        txfSearchFood.setBackground(new java.awt.Color(238, 230, 226));
        txfSearchFood.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txfSearchFood.setBorder(null);
        txfSearchFood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfSearchFoodActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txfSearchFood, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel16)
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txfSearchFood)
                .addContainerGap())
        );

        txfSearchFood.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                SearchFood();
            }
            public void removeUpdate(DocumentEvent e) {
                if(txfSearchFood.getText().equals("")){
                    CreateValueTableMA();

                }else{
                    SearchFood();
                }
            }
            public void insertUpdate(DocumentEvent e) {
                SearchFood();
            }
        });

        FilterFoodButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/filter.png"))); // NOI18N
        FilterFoodButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FilterFoodButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(28, 28, 28)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(FilterFoodButton)
                .addGap(106, 106, 106))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(FilterFoodButton))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setForeground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setFocusable(false);

        ThucDonTable.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        ThucDonTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã món ăn", "Tên món ăn", "Đơn giá", "Ghi chú", "Chọn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        ThucDonTable.setFocusable(false);
        ThucDonTable.setGridColor(new java.awt.Color(255, 255, 255));
        ThucDonTable.setRowHeight(25);
        ThucDonTable.setSelectionBackground(new java.awt.Color(69, 96, 134));
        ThucDonTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ThucDonTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(ThucDonTable);
        if (ThucDonTable.getColumnModel().getColumnCount() > 0) {
            ThucDonTable.getColumnModel().getColumn(0).setMinWidth(100);
            ThucDonTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            ThucDonTable.getColumnModel().getColumn(0).setMaxWidth(20);
            ThucDonTable.getColumnModel().getColumn(1).setMinWidth(100);
            ThucDonTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            ThucDonTable.getColumnModel().getColumn(1).setMaxWidth(20);
            ThucDonTable.getColumnModel().getColumn(3).setMinWidth(150);
            ThucDonTable.getColumnModel().getColumn(3).setPreferredWidth(150);
            ThucDonTable.getColumnModel().getColumn(3).setMaxWidth(150);
            ThucDonTable.getColumnModel().getColumn(5).setMinWidth(100);
            ThucDonTable.getColumnModel().getColumn(5).setPreferredWidth(100);
            ThucDonTable.getColumnModel().getColumn(5).setMaxWidth(20);
        }
        ThucDonTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        ThucDonTable.getTableHeader().setOpaque(false);
        ThucDonTable.getTableHeader().setBackground(new Color(243,246,249));
        ThucDonTable.setDefaultEditor(Object.class, null);
        TableCellEditor editor = new DefaultCellEditor(new JTextField());
        ThucDonTable.getColumnModel().getColumn(4).setCellEditor(editor);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jScrollPane1)
                .addGap(106, 106, 106))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        cbxLoaiMonAn.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        cbxLoaiMonAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxLoaiMonAnActionPerformed(evt);
            }
        });

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel18.setText("Loại món ăn");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxLoaiMonAn, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxLoaiMonAn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));

        BackPage2.setBackground(new java.awt.Color(69, 96, 134));
        BackPage2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        BackPage2.setForeground(new java.awt.Color(255, 255, 255));
        BackPage2.setText("Quay lại");
        BackPage2.setPreferredSize(new java.awt.Dimension(90, 40));
        BackPage2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackPage2ActionPerformed(evt);
            }
        });

        NextPage2.setBackground(new java.awt.Color(132, 70, 133));
        NextPage2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        NextPage2.setForeground(new java.awt.Color(255, 255, 255));
        NextPage2.setText("Tiếp tục");
        NextPage2.setPreferredSize(new java.awt.Dimension(90, 40));
        NextPage2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextPage2ActionPerformed(evt);
            }
        });

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        DonBanToiThieu.setBackground(new java.awt.Color(255, 255, 255));
        DonBanToiThieu.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        TongDonBanHT.setBackground(new java.awt.Color(255, 255, 255));
        TongDonBanHT.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        TongDonBanHT.setText("0");
        TongDonBanHT.setToolTipText("");

        jLabel20.setBackground(new java.awt.Color(255, 255, 255));
        jLabel20.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel20.setText("Tổng đơn bàn hiện tại:");

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel19.setText("Giá đơn bàn tối thiểu:");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DonBanToiThieu)
                            .addComponent(TongDonBanHT))))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(DonBanToiThieu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(TongDonBanHT))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(BackPage2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 758, Short.MAX_VALUE)
                        .addComponent(NextPage2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106))))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NextPage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BackPage2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 5, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Page2Layout = new javax.swing.GroupLayout(Page2);
        Page2.setLayout(Page2Layout);
        Page2Layout.setHorizontalGroup(
            Page2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Page2Layout.setVerticalGroup(
            Page2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Page2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Page3.setBackground(new java.awt.Color(255, 255, 255));
        Page3.setPreferredSize(new java.awt.Dimension(1170, 730));

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Group 1000001482.png"))); // NOI18N
        jPanel28.add(jLabel21);

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setPreferredSize(new java.awt.Dimension(1170, 124));

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(69, 96, 134));
        jLabel22.setText("BẢNG DỊCH VỤ");

        jPanel30.setBackground(new java.awt.Color(238, 230, 226));
        jPanel30.setPreferredSize(new java.awt.Dimension(260, 50));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Group 9.png"))); // NOI18N

        txfSearchService.setBackground(new java.awt.Color(238, 230, 226));
        txfSearchService.setBorder(null);
        txfSearchService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfSearchServiceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txfSearchService, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel23)
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txfSearchService)
                .addContainerGap())
        );

        txfSearchService.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                SearchService();
            }

            public void removeUpdate(DocumentEvent e) {
                if (txfSearchFood.getText().equals("")) {
                    CreateValueTableDV();

                } else {
                    SearchService();
                }
            }

            public void insertUpdate(DocumentEvent e) {
                SearchService();
            }
        });

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/filter.png"))); // NOI18N

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap(456, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addGap(51, 51, 51)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel24)
                .addGap(106, 106, 106))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel24))
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel22)
                            .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));
        jPanel33.setForeground(new java.awt.Color(255, 255, 255));

        DichVuTable.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        DichVuTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã dịch vụ", "Tên dịch vụ", "Số lượng", "Đơn giá", "Chọn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        DichVuTable.setRowHeight(25);
        DichVuTable.setSelectionBackground(new java.awt.Color(69, 96, 134));
        DichVuTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DichVuTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(DichVuTable);
        if (DichVuTable.getColumnModel().getColumnCount() > 0) {
            DichVuTable.getColumnModel().getColumn(0).setMinWidth(100);
            DichVuTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            DichVuTable.getColumnModel().getColumn(0).setMaxWidth(20);
            DichVuTable.getColumnModel().getColumn(1).setMinWidth(100);
            DichVuTable.getColumnModel().getColumn(1).setPreferredWidth(150);
            DichVuTable.getColumnModel().getColumn(1).setMaxWidth(20);
            DichVuTable.getColumnModel().getColumn(5).setMinWidth(100);
            DichVuTable.getColumnModel().getColumn(5).setPreferredWidth(100);
            DichVuTable.getColumnModel().getColumn(5).setMaxWidth(100);
        }
        DichVuTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        DichVuTable.getTableHeader().setOpaque(false);
        DichVuTable.getTableHeader().setBackground(new Color(243,246,249));
        DichVuTable.setDefaultEditor(Object.class, null);
        TableCellEditor editor1 = new DefaultCellEditor(new JTextField());
        DichVuTable.getColumnModel().getColumn(3).setCellEditor(editor1);

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 958, Short.MAX_VALUE)
                .addGap(106, 106, 106))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel39.setBackground(new java.awt.Color(255, 255, 255));

        BackPage3.setBackground(new java.awt.Color(69, 96, 134));
        BackPage3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        BackPage3.setForeground(new java.awt.Color(255, 255, 255));
        BackPage3.setText("Quay lại");
        BackPage3.setPreferredSize(new java.awt.Dimension(90, 40));
        BackPage3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackPage3ActionPerformed(evt);
            }
        });

        NextPage3.setBackground(new java.awt.Color(132, 70, 133));
        NextPage3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        NextPage3.setForeground(new java.awt.Color(255, 255, 255));
        NextPage3.setText("Tiếp tục");
        NextPage3.setPreferredSize(new java.awt.Dimension(90, 40));
        NextPage3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextPage3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(BackPage3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 758, Short.MAX_VALUE)
                .addComponent(NextPage3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NextPage3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BackPage3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 5, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Page3Layout = new javax.swing.GroupLayout(Page3);
        Page3.setLayout(Page3Layout);
        Page3Layout.setHorizontalGroup(
            Page3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Page3Layout.setVerticalGroup(
            Page3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Page3Layout.createSequentialGroup()
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Page4.setBackground(new java.awt.Color(255, 255, 255));
        Page4.setPreferredSize(new java.awt.Dimension(1170, 730));

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/final.png"))); // NOI18N
        jPanel34.add(jLabel25);

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));

        jLabel27.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        jLabel27.setText("XÁC NHẬN THÔNG TIN ĐẶT TIỆC");
        jPanel36.add(jLabel27);

        jPanel37.setBackground(new java.awt.Color(255, 255, 255));

        jLabel26.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel26.setText("Tên chú rể:");

        jLabel28.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel28.setText("Tên cô dâu:");

        jLabel29.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel29.setText("Ngày đãi tiêc:");

        jLabel30.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel30.setText("Số lượng bàn:");

        jLabel31.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel31.setText("Đơn giá bàn:");

        jLabel32.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel32.setText("Tổng tiền bàn:");

        SoLuongBan.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        TenChuRe.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        DonGiaBan.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        TenCoDau.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        TongTienBan.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        NgayDaiTiec.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        NgayDaiTiec.setToolTipText("");

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(jLabel26))
                .addGap(18, 18, 18)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TenChuRe)
                    .addComponent(SoLuongBan))
                .addGap(277, 277, 277)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(jLabel31))
                .addGap(18, 18, 18)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DonGiaBan)
                    .addComponent(TenCoDau))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addGap(18, 18, 18)
                        .addComponent(TongTienBan))
                    .addGroup(jPanel37Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(23, 23, 23)
                        .addComponent(NgayDaiTiec)))
                .addGap(104, 104, 104))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(TenChuRe)
                    .addComponent(TenCoDau)
                    .addComponent(NgayDaiTiec))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(SoLuongBan)
                    .addComponent(DonGiaBan)
                    .addComponent(TongTienBan))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));

        DichVuDatTiecTable.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        DichVuDatTiecTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã dịch vụ", "Tên dịch vụ", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        DichVuDatTiecTable.setRowHeight(25);
        jScrollPane3.setViewportView(DichVuDatTiecTable);
        if (DichVuDatTiecTable.getColumnModel().getColumnCount() > 0) {
            DichVuDatTiecTable.getColumnModel().getColumn(0).setMinWidth(100);
            DichVuDatTiecTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            DichVuDatTiecTable.getColumnModel().getColumn(0).setMaxWidth(20);
            DichVuDatTiecTable.getColumnModel().getColumn(1).setMinWidth(100);
            DichVuDatTiecTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            DichVuDatTiecTable.getColumnModel().getColumn(1).setMaxWidth(50);
            DichVuDatTiecTable.getColumnModel().getColumn(3).setMinWidth(100);
            DichVuDatTiecTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            DichVuDatTiecTable.getColumnModel().getColumn(3).setMaxWidth(50);
        }
        DichVuDatTiecTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        DichVuDatTiecTable.getTableHeader().setOpaque(false);
        DichVuDatTiecTable.getTableHeader().setBackground(new Color(243,246,249));
        DichVuTable.setDefaultEditor(Object.class, null);

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jScrollPane3)
                .addGap(106, 106, 106))
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel46.setBackground(new java.awt.Color(255, 255, 255));

        jPanel49.setBackground(new java.awt.Color(255, 255, 255));

        cbxPTTT.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        cbxPTTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản" }));
        cbxPTTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxPTTTActionPerformed(evt);
            }
        });

        jLabel35.setBackground(new java.awt.Color(255, 255, 255));
        jLabel35.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel35.setText("Phương thức thanh toán:");

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxPTTT, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(cbxPTTT, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        BackPage4.setBackground(new java.awt.Color(69, 96, 134));
        BackPage4.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        BackPage4.setForeground(new java.awt.Color(255, 255, 255));
        BackPage4.setText("Quay lại");
        BackPage4.setPreferredSize(new java.awt.Dimension(90, 40));
        BackPage4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackPage4ActionPerformed(evt);
            }
        });

        NextPage5.setBackground(new java.awt.Color(132, 70, 133));
        NextPage5.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        NextPage5.setForeground(new java.awt.Color(255, 255, 255));
        NextPage5.setText("Tiếp tục");
        NextPage5.setPreferredSize(new java.awt.Dimension(90, 40));
        NextPage5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextPage5ActionPerformed(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        TongTienDV.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        TongTienDV.setToolTipText("");

        TongTienHD.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        TienCoc.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        ConLai.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel37.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel37.setText("Còn lại (Tạm tính):");

        tienCocText.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tienCocText.setText("Tiền cọc (50%):");

        jLabel34.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel34.setText("Tổng tiền hoá đơn:");

        jLabel33.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel33.setText("Tổng tiền dịch vụ:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(33, 33, 33)
                        .addComponent(TongTienDV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addGap(24, 24, 24)
                                .addComponent(TongTienHD))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel37)
                                    .addComponent(tienCocText))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(ConLai)
                                    .addComponent(TienCoc))))
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(TongTienDV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34)
                    .addComponent(TongTienHD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tienCocText)
                    .addComponent(TienCoc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(ConLai))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addComponent(BackPage4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(NextPage5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105))))
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NextPage5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BackPage4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Page4Layout = new javax.swing.GroupLayout(Page4);
        Page4.setLayout(Page4Layout);
        Page4Layout.setHorizontalGroup(
            Page4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, 1170, Short.MAX_VALUE)
            .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Page4Layout.setVerticalGroup(
            Page4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Page4Layout.createSequentialGroup()
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Page1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Page2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Page3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Page4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Page1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Page2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Page3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(Page4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void inputTenChuReActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputTenChuReActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputTenChuReActionPerformed

    private void SearchFood() {
        defaultTableModelMA.setRowCount(0);
        defaultTableModelMA = (DefaultTableModel) ThucDonTable.getModel();
        int i = 0;
        String value = txfSearchFood.getText();
        for (DTMonAn x : dTMonAns) {
            if (x.getMaLoaiMonAn().equals(mapMaLoaiMA.get(cbxLoaiMonAn.getSelectedItem())) && (x.getMaMonAn().toLowerCase().contains(value.toLowerCase())
                    || x.getTenMonAn().toLowerCase().contains(value.toLowerCase()))) {
                System.out.println(x.getMaLoaiMonAn().equals(mapMaLoaiMA.get(cbxLoaiMonAn.getSelectedItem())));
                defaultTableModelMA.addRow(new Object[]{++i, x.getMaMonAn(), x.getTenMonAn(), currencyFormatVN.format(x.getDonGia()), x.getGhiChu(), x.getChon()});
            }
        }
    }

    private void SearchService() {
        defaultTableModelDV.setRowCount(0);
        defaultTableModelDV = (DefaultTableModel) DichVuTable.getModel();
        int i = 0;
        String value = txfSearchService.getText();
        for (DTDichVu x : dTDichVus) {
            if (x.getMaDichVu().toLowerCase().contains(value.toLowerCase())
                    || x.getTenDichVu().toLowerCase().contains(value.toLowerCase())) {
                defaultTableModelDV.addRow(new Object[]{++i, x.getMaDichVu(), x.getTenDichVu(), x.getSoLuong(), currencyFormatVN.format(x.getDonGia()), x.isChon()});
            }
        }
    }

    public int DateDifference() {
        Date ngayDatTiec = inputNgayDatTiec.getDate();
        Date ngayDaiTiec = inputNgayDaiTiec.getDate();

        LocalDate date1 = LocalDate.of(ngayDatTiec.getYear() + 1900, ngayDatTiec.getMonth() + 1, ngayDatTiec.getDate());
        LocalDate date2 = LocalDate.of(ngayDaiTiec.getYear() + 1900, ngayDaiTiec.getMonth() + 1, ngayDaiTiec.getDate());

        long daysBetween = ChronoUnit.DAYS.between(date1, date2);
        return (int) daysBetween;
    }

    private void NextPage1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextPage1ActionPerformed
        // TODO add your handling code here:

        String mess = "";
        if (inputTenChuRe.getText().equals("")) {
            mess += "Lỗi! Mời nhập tên chú rể.\n";
        }
        if (inputTenCoDau.getText().equals("")) {
            mess += "Lỗi! Mời nhập tên cô dâu.\n";
        }
        if (!validatePhoneNumber()) {
            mess += "Lỗi! Số điện thoại không hợp lệ.\n";
        }
        if (Integer.parseInt(inputSoLuongBan.getValue().toString()) == 0) {
            mess += "Lỗi! Mời nhập số lượng bàn.\n";
        }
        if (mess.equals("")) {
            Page1.setVisible(false);
            Page2.setVisible(true);
            Page2();
        } else {
            Message(mess, JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_NextPage1ActionPerformed

    private void txfSearchFoodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfSearchFoodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfSearchFoodActionPerformed

    private void txfSearchServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfSearchServiceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfSearchServiceActionPerformed

    private void BackPage3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackPage3ActionPerformed
        // TODO add your handling code here:
        Page3.setVisible(false);
        Page2.setVisible(true);
    }//GEN-LAST:event_BackPage3ActionPerformed

    private void NextPage3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextPage3ActionPerformed
        // TODO add your handling code here:
        CTDichVus = new ArrayList<>();
        for (int row = 0; row < DichVuTable.getRowCount(); row++) {
            boolean isCheck = (boolean) DichVuTable.getValueAt(row, 5);
            String maDichVu = String.valueOf(DichVuTable.getValueAt(row, 1));
            String tenDichVu = String.valueOf(DichVuTable.getValueAt(row, 2));
            int donGia = 0;
            try {
                donGia = Integer.parseInt(String.valueOf(currencyFormatVN.parse(String.valueOf(DichVuTable.getValueAt(row, 4)))));
            } catch (ParseException ex) {
                Logger.getLogger(BookingPartyWedding.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(maDichVu);
            if (isCheck) {
                CTDichVus.add(new DTDichVu(Integer.parseInt(DichVuTable.getValueAt(row, 3).toString()), isCheck, maDichVu, tenDichVu, donGia));
            }
        }
        Page4.setVisible(true);
        Page3.setVisible(false);
        Page4();
    }//GEN-LAST:event_NextPage3ActionPerformed

    private void BackPage4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackPage4ActionPerformed
        // TODO add your handling code here:
        Page4.setVisible(false);
        Page3.setVisible(true);
    }//GEN-LAST:event_BackPage4ActionPerformed
    public BufferedImage generateQRCode(String qrData) throws WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.L;

        //    // Set QR code encoding hints
        Map<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, errorCorrectionLevel);

        // Generate QR code matrix
        BitMatrix bitMatrix = qrCodeWriter.encode(qrData, com.google.zxing.BarcodeFormat.QR_CODE, 200, 200, (Hashtable) hints);
//        System.out.print(bitMatrix);
        // Convert QR code matrix to buffered image
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage qrImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF;
                qrImage.setRGB(x, y, rgb);
            }
        }
        return qrImage;
    }

    private String getIpAddress() {
        // Lấy địa chỉ IP của người dùng
        return "127.0.0.1"; // Thay bằng địa chỉ IP của người dùng
    }

    public String hmacSHA512(String key, String data) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA512");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
        mac.init(secretKey);
        byte[] hmacData = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hmacData) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Etc/GMT+7"));
        Date now = new Date();
        return dateFormat.format(now);
    }

    public String generatePaymentUrl(String bank_code, String websiteCode, String transactionCode, String amount, String orderDescription, String returnUrl) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        Map<String, String> parameters = new HashMap<>();
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        //Add Params of 2.1.0 Version
        System.out.println(amount);
        parameters.put("vnp_ExpireDate", vnp_ExpireDate);
        parameters.put("vnp_Version", "2.1.0");
        parameters.put("vnp_Command", "pay");
        parameters.put("vnp_TmnCode", websiteCode);
        parameters.put("vnp_Amount", amount);
        parameters.put("vnp_CurrCode", "VND");
        if (bank_code != null && !bank_code.isEmpty()) {
            parameters.put("vnp_BankCode", bank_code);
        }
        parameters.put("vnp_TxnRef", transactionCode);
        parameters.put("vnp_OrderInfo", orderDescription);
        parameters.put("vnp_OrderType", "other");
        parameters.put("vnp_ReturnUrl", returnUrl);
        parameters.put("vnp_IpAddr", getIpAddress());
        parameters.put("vnp_CreateDate", getCurrentDateTime());
        parameters.put("vnp_Locale", "vn");
//        parameters.put("vnp_SecureHashType", "SHA256");
//        parameters.put("vnp_SecureHash", generateSecureHash(parameters));
        List fieldNames = new ArrayList(parameters.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) parameters.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = hmacSHA512(SECRET_KEY, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = API_URL + "?" + queryUrl;
//        StringBuilder urlBuilder = new StringBuilder(API_URL);
//        urlBuilder.append("?");
//        for (Map.Entry<String, String> entry : parameters.entrySet()) {
//            urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
//        }
//
//        return urlBuilder.toString();
        return paymentUrl;
    }
    private void NextPage5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextPage5ActionPerformed
        // TODO add your handling code here:
        if (cbxPTTT.getSelectedItem().equals("Tiền mặt")) {
            ThanhToanTienMat.setLocationRelativeTo(null);
            ThanhToanTienMat.setVisible(true);
            inputSoTienDaNhan.setText(String.valueOf((int) tienCoc));
        } else if (cbxPTTT.getSelectedItem().equals("Chuyển khoản")) {
            try {
                JPanel pane = new JPanel();
                String qrdata = "";
                try {
                    String paymentUrl = generatePaymentUrl("", websiteCode, getCurrentDateTime(), String.valueOf(((long) tienCoc) * 100), "Thanh toan tiec cuoi", "https://courses.uit.edu.vn/course/view.php?id=10493#section-3");
                    System.out.println(paymentUrl);
                    qrdata = paymentUrl;
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(WeddingPartyLookup.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidKeyException ex) {
                    Logger.getLogger(WeddingPartyLookup.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(WeddingPartyLookup.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.println(qrdata);
                ImageIcon qrIcon = new ImageIcon(generateQRCode(qrdata));
                System.out.println(qrIcon);
                JLabel qrLabel = new JLabel(qrIcon);
                ChuyenKhoanForm.setSize(400, 400);
                qrLabel.setHorizontalAlignment(SwingConstants.CENTER); // Set horizontal alignment to center
                pane.add(qrLabel, BorderLayout.CENTER); // Add the label to the center of the panel
                pane.add(qrLabel);

                pane.setSize(400, 400);
                pane.setBackground(Color.WHITE);
                pane.setVisible(true);
                ChuyenKhoanForm.add(pane);
                ChuyenKhoanForm.setModal(true);
                ChuyenKhoanForm.setLocationRelativeTo(null);
                ChuyenKhoanForm.setVisible(true);
            } catch (WriterException ex) {
                Logger.getLogger(WeddingPartyLookup.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        cbxLoaiMonAn.removeAllItems();
    }//GEN-LAST:event_NextPage5ActionPerformed

    private void NextPage2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextPage2ActionPerformed

        if (tongDonBanHienTai >= donGiaBanToiThieu) {
            Page2.setVisible(false);
            Page3.setVisible(true);
        } else {
            Message("Lỗi! Tổng đơn bàn hiện tại phải lớn hơn hoặc bằng đơn bàn tối thiểu.", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_NextPage2ActionPerformed

    private void BackPage2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackPage2ActionPerformed
        // TODO add your handling code here:
        Page1.setVisible(true);
        Page2.setVisible(false);
    }//GEN-LAST:event_BackPage2ActionPerformed

    private void FilterFoodButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FilterFoodButtonMouseClicked
        // TODO add your handling code here:
        FilterFood.setVisible(true);
    }//GEN-LAST:event_FilterFoodButtonMouseClicked

    private void SilderFoodStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SilderFoodStateChanged
        // TODO add your handling code here:
        KhoangDonGia.setText("0 - " + String.valueOf(SilderFood.getValue()));
        ValueDGMAX.setText(String.valueOf(SilderFood.getValue()));
    }//GEN-LAST:event_SilderFoodStateChanged

    private void SaveSearchFoodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveSearchFoodActionPerformed
        // TODO add your handling code here:
//         defaultTableModelMA = (DefaultTableModel)ThucDonTable.getModel();
//         int i = 1;
//         System.out.println(SilderFood.getValue());
//        for(test x: arr){
//            if(x.loaimon.equals(LoaiMonAn.getSelectedItem())&& x.donGia<= SilderFood.getValue())
//            defaultTableModelMA.addRow(new Object[]{i++,x.maMA, x.tenmon, x.donGia, x.ghichu, x.chon});
//        }
//        FilterFood.setVisible(false);

    }//GEN-LAST:event_SaveSearchFoodActionPerformed

    private void ExitSearchFoodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitSearchFoodActionPerformed
        // TODO add your handling code here:
        FilterFood.setVisible(false);
    }//GEN-LAST:event_ExitSearchFoodActionPerformed

    private void inputSoLuongBanStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_inputSoLuongBanStateChanged
        // TODO add your handling code here:
        if (SoLuongBanToiDa != 0) {
            if (Integer.parseInt(inputSoLuongBan.getValue().toString()) > SoLuongBanToiDa) {
                inputSoLuongBan.setValue(SoLuongBanToiDa);
            }
        } else {
            inputSoLuongBan.setValue(0);
        }

    }//GEN-LAST:event_inputSoLuongBanStateChanged

    private void inputNgayDaiTiecPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_inputNgayDaiTiecPropertyChange
        // TODO add your handling code here:
        if (inputNgayDaiTiec.getDate() != null) {
            int daysBetween = DateDifference();
            if (daysBetween >= thoiGianDatTiec) {
                Date ngayDatTiec = inputNgayDatTiec.getDate();
                Date ngayDaiTiec = inputNgayDaiTiec.getDate();
                if (ngayDatTiec.compareTo(ngayDaiTiec) < 0) {
                    String tenCa = inputCa.getSelectedItem().toString().split(" ")[0];
                    int day = ngayDaiTiec.getDate();
                    int month = ngayDaiTiec.getMonth() + 1;
                    int year = ngayDaiTiec.getYear() + 1900;

                    sanhs = SanhDAO.getInstance().SelectSanhDatTiec(mapMaCa.get(tenCa), String.valueOf(day), String.valueOf(month), String.valueOf(year));
                    for (Sanh x : sanhs) {
                        System.out.println(x.getTenSanh());
                    }

                    if (sanhs.size() != 0) {
                        inputSanh.removeAllItems();
                        for (Sanh x : sanhs) {
                            mapMaSanh.put(x.getTenSanh(), x.getMaSanh());
                            System.out.println(x.getMaSanh());
                            inputSanh.addItem(x.getTenSanh());
                        }
                    } else {
                        inputSanh.removeAllItems();
                        inputSanh.addItem("<Không có dữ liệu>");
                    }

                }

            } else if (DateDifference() < 0) {
                Message("Lỗi! Mời chọn ngày khác.", JOptionPane.WARNING_MESSAGE);
                inputNgayDaiTiec.setDate(null);
            } else {
                String mess = "Lỗi! Khoảng thời gian đặt tiệc phải trước " + thoiGianDatTiec + " ngày.";
                Message(mess, JOptionPane.WARNING_MESSAGE);
                inputNgayDaiTiec.setDate(null);
            }

        }
    }//GEN-LAST:event_inputNgayDaiTiecPropertyChange

    private void inputCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputCaActionPerformed
        // TODO add your handling code here:
        if (inputNgayDaiTiec.getDate() != null) {
            Date ngayDaiTiec = inputNgayDaiTiec.getDate();
            String tenCa = inputCa.getSelectedItem().toString().split(" ")[0];
            int day = ngayDaiTiec.getDate();
            int month = ngayDaiTiec.getMonth() + 1;
            int year = ngayDaiTiec.getYear() + 1900;

            sanhs = SanhDAO.getInstance().SelectSanhDatTiec(mapMaCa.get(tenCa), String.valueOf(day), String.valueOf(month), String.valueOf(year));

            if (sanhs.size() != 0) {
                inputSanh.removeAllItems();
                for (Sanh x : sanhs) {
                    mapMaSanh.put(x.getTenSanh(), x.getMaSanh());
                    inputSanh.addItem(x.getTenSanh());
                    System.out.println(x.getTenSanh());
                }
            } else {
                inputSanh.removeAllItems();
                inputSanh.addItem("<Không có dữ liệu>");

            }
        }
    }//GEN-LAST:event_inputCaActionPerformed

    private void inputSanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputSanhActionPerformed

        String maSanh = mapMaSanh.get(inputSanh.getSelectedItem());
        System.out.println(maSanh);
        SoLuongBanToiDa = SanhDAO.getInstance().GetSoLuongBanToiDa(maSanh);
        donGiaBanToiThieu = SanhDAO.getInstance().GetDonBanToiThieu(maSanh);
        System.out.println(donGiaBanToiThieu);
        System.out.println(SoLuongBanToiDa);


    }//GEN-LAST:event_inputSanhActionPerformed

    private void cbxLoaiMonAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxLoaiMonAnActionPerformed
        // TODO add your handling code here:
        try {
            CreateValueTableMA();
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }//GEN-LAST:event_cbxLoaiMonAnActionPerformed

    private void ThucDonTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ThucDonTableMouseClicked
        // TODO add your handling code here:
        int row = ThucDonTable.getSelectedRow();
        int col = ThucDonTable.getSelectedColumn();
        if (col == 5) {
            boolean chon = (boolean) ThucDonTable.getValueAt(row, col);
            String maMonAn = (String) ThucDonTable.getValueAt(row, 1);
            String tenMonAn = (String) ThucDonTable.getValueAt(row, 2);
            long donGia = 0;
            try {
                donGia = Integer.parseInt(String.valueOf(currencyFormatVN.parse(String.valueOf(ThucDonTable.getValueAt(row, 3)))));
            } catch (ParseException ex) {
                Logger.getLogger(BookingPartyWedding.class.getName()).log(Level.SEVERE, null, ex);
            }
            String maLoaiMonAn = mapMaLoaiMA.get(cbxLoaiMonAn.getSelectedItem());
            String ghiChu = (String) ThucDonTable.getValueAt(row, 4);
            UpdateChon(chon, ghiChu, row);
            if (chon) {

                CTMonAns.add(new DTMonAn(maMonAn, tenMonAn, donGia, maLoaiMonAn, ghiChu));
                tongDonBanHienTai += donGia;
                TongDonBanHT.setText(currencyFormatVN.format(tongDonBanHienTai));
                if (tongDonBanHienTai < donGiaBanToiThieu) {
                    TongDonBanHT.setForeground(Color.red);
                } else {
                    TongDonBanHT.setForeground(Color.black);
                }

            } else {

                for (DTMonAn x : CTMonAns) {
                    if (x.getMaMonAn().equals(maMonAn)) {
                        CTMonAns.remove(x);
                        break;
                    }
                }
                tongDonBanHienTai -= donGia;
                TongDonBanHT.setText(String.valueOf(tongDonBanHienTai));
                if (tongDonBanHienTai < donGiaBanToiThieu) {
                    TongDonBanHT.setForeground(Color.red);
                } else {
                    TongDonBanHT.setForeground(Color.black);
                }

            }
            System.out.println(CTMonAns);

        }
    }//GEN-LAST:event_ThucDonTableMouseClicked

    private void DichVuTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DichVuTableMouseClicked
        // TODO add your handling code here:
        int row = DichVuTable.getSelectedRow();
        int col = DichVuTable.getSelectedColumn();
        if (col == 5) {
            boolean chon = (boolean) DichVuTable.getValueAt(row, 5);
            if (chon) {
                if (Integer.parseInt(DichVuTable.getValueAt(row, 3).toString()) == 0) {
                    defaultTableModelDV.setValueAt(1, row, 3);
                }
                if (Integer.parseInt(DichVuTable.getValueAt(row, 3).toString()) < 0) {
                    defaultTableModelDV.setValueAt(0, row, 3);
                }
//                CTDichVus.add(new DTDichVu(Integer.parseInt(DichVuTable.getValueAt(row, 3).toString()), chon, maDichVu, tenDichVu, donGia));
            } else {
                defaultTableModelDV.setValueAt(0, row, 3);
            }
//            } else {
//                defaultTableModelDV.setValueAt(0, row, 3);
//                for (DTDichVu x : CTDichVus) {
//                    if (x.getMaDichVu().equals(maDichVu)) {
//                        CTDichVus.remove(x);
//                        break;
//                    }
//                }
//            }
//
//            int soLuong = Integer.parseInt(DichVuTable.getValueAt(row, 3).toString());
//            UpdateChonDV(chon, soLuong, row);

        }

    }//GEN-LAST:event_DichVuTableMouseClicked

    private void btn200trActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn200trActionPerformed
        // TODO add your handling code here:
        inputSoTienDaNhan.setText("200000000");
    }//GEN-LAST:event_btn200trActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        inputSoTienDaNhan.setText("0");
    }//GEN-LAST:event_btnXoaActionPerformed

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

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed

        String maTiecCuoi = String.valueOf(PhieuDatTiecCuoiDAO.getInstance().GetID() + 1);
        switch (maTiecCuoi.length()) {
            case 1:
                maTiecCuoi = "TC000" + maTiecCuoi;
                break;
            case 2:
                maTiecCuoi = "TC00" + maTiecCuoi;
                break;
            case 3:
                maTiecCuoi = "TC0" + maTiecCuoi;
                break;
            case 4:
                maTiecCuoi = "TC" + maTiecCuoi;
                break;
        }

        Date ngayDT = inputNgayDatTiec.getDate();
        Date ngayDTiec = inputNgayDaiTiec.getDate();

        String ngayDat = (ngayDT.getYear() + 1900) + "-" + (ngayDT.getMonth() + 1) + "-" + ngayDT.getDate();
        String ngayDaiTiec = (ngayDTiec.getYear() + 1900) + "-" + (ngayDTiec.getMonth() + 1) + "-" + ngayDTiec.getDate();

        int soLuongBan = Integer.parseInt(inputSoLuongBan.getValue().toString());

        int soLuongBanDuTru = Integer.parseInt(inputSLDT.getValue().toString());

        String tenCoDau = inputTenCoDau.getText();

        String tenChuRe = inputTenChuRe.getText();

        String sdt = inputSDT.getText();

        String maCa = mapMaCa.get(inputCa.getSelectedItem().toString().split(" ")[0]);

        String maSanh = mapMaSanh.get(inputSanh.getSelectedItem().toString());

        String userName = systemDAO.getInstance().getUser();
        if (Long.parseLong(inputSoTienDaNhan.getText()) >= tienCoc) {
            int kq1 = PhieuDatTiecCuoiDAO.getInstance().Insert(new PhieuDatTiecCuoi(maTiecCuoi, ngayDat, ngayDaiTiec, soLuongBan, soLuongBanDuTru, tongDonBanHienTai, tongtienban,
                    tongTienDV, tongtienHD, (int) tienCoc, conLai, tenCoDau, tenChuRe, sdt, maCa, maSanh, userName));
            int flag = 1;
            if (kq1 > 0) {
                int kq2 = 0;
                for (DTMonAn x : CTMonAns) {
                    kq2 = ChiTietMonAnDAO.getInstance().Insert(new ChiTietMonAn(maTiecCuoi, x.getMaMonAn(), x.getDonGia(), tongSLB, x.getGhiChu()));
                    if (kq2 == 0) {
                        flag = 0;
                        break;
                    }
                }
                if (kq2 > 0) {
                    int kq3 = 0;
                    for (DTDichVu x : CTDichVus) {
                        int temp = x.getSoLuong() * x.getDonGia();
                        kq3 = ChiTietDichVuDAO.getInstance().Insert(new ChiTietDichVu(maTiecCuoi, x.getMaDichVu(), x.getSoLuong(), x.getDonGia(), temp));
                        if (kq3 == 0) {
                            flag = 0;
                            break;
                        }
                    }

                }

            }

            if (flag > 0) {
                File file = new File("src/report/rptPhieuDatTiec.jasper");
                String absolutePath = file.getAbsolutePath();
                try {
                    HashMap<String, Object> map = new HashMap<>();
                    Connection con = JDBCUtil.getConnection();
                    map.put("maTiecCuoi", maTiecCuoi);
                    map.put("tienKhachTra", Double.parseDouble(inputSoTienDaNhan.getText()));
                    map.put("tienThua", Double.parseDouble(inputSoTienDaNhan.getText()) - tienCoc);
                    map.put("tyLePhat", tyLePhat);
                    map.put("thoiGianPhat", thoiGianPhat);
                    System.out.println(maTiecCuoi);
                    System.out.println(Double.parseDouble(inputSoTienDaNhan.getText()));
                    System.out.println(Double.parseDouble(inputSoTienDaNhan.getText()) - tienCoc);
                    System.out.println(tyLePhat);
                    System.out.println(thoiGianPhat);
                    JasperPrint p = JasperFillManager.fillReport(absolutePath, map, con);
                    JasperViewer v = new JasperViewer(p, false);
                    v.setVisible(true);

                } catch (JRException ex) {
                    System.out.println(ex);

                }
            }

            Page1.setVisible(true);
            Page4.setVisible(false);
            Page1();
            inputTenChuRe.setText("");
            inputTenCoDau.setText("");
            inputSDT.setText("");
            inputSoLuongBan.setValue(0);
            inputSLDT.setValue(0);
            inputNgayDaiTiec.setDate(null);
            ThanhToanTienMat.setVisible(false);
        } else {
            Message("Quy khách không đủ tiền để thanh toán!", JOptionPane.INFORMATION_MESSAGE);
        }


    }//GEN-LAST:event_btnXacNhanActionPerformed

    private void cbxPTTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxPTTTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxPTTTActionPerformed

    private void btnDaThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDaThanhToanActionPerformed
        // TODO add your handling code here:
        String maTiecCuoi = String.valueOf(PhieuDatTiecCuoiDAO.getInstance().GetID() + 1);
        switch (maTiecCuoi.length()) {
            case 1:
                maTiecCuoi = "TC000" + maTiecCuoi;
                break;
            case 2:
                maTiecCuoi = "TC00" + maTiecCuoi;
                break;
            case 3:
                maTiecCuoi = "TC0" + maTiecCuoi;
                break;
            case 4:
                maTiecCuoi = "TC" + maTiecCuoi;
                break;
        }

        Date ngayDT = inputNgayDatTiec.getDate();
        Date ngayDTiec = inputNgayDaiTiec.getDate();

        String ngayDat = (ngayDT.getYear() + 1900) + "-" + (ngayDT.getMonth() + 1) + "-" + ngayDT.getDate();
        String ngayDaiTiec = (ngayDTiec.getYear() + 1900) + "-" + (ngayDTiec.getMonth() + 1) + "-" + ngayDTiec.getDate();

        int soLuongBan = Integer.parseInt(inputSoLuongBan.getValue().toString());

        int soLuongBanDuTru = Integer.parseInt(inputSLDT.getValue().toString());

        String tenCoDau = inputTenCoDau.getText();

        String tenChuRe = inputTenChuRe.getText();

        String sdt = inputSDT.getText();

        String maCa = mapMaCa.get(inputCa.getSelectedItem().toString().split(" ")[0]);

        String maSanh = mapMaSanh.get(inputSanh.getSelectedItem().toString());

        String userName = systemDAO.getInstance().getUser();

        int kq1 = PhieuDatTiecCuoiDAO.getInstance().Insert(new PhieuDatTiecCuoi(maTiecCuoi, ngayDat, ngayDaiTiec, soLuongBan, soLuongBanDuTru, tongDonBanHienTai, tongtienban,
                tongTienDV, tongtienHD, (int) tienCoc, conLai, tenCoDau, tenChuRe, sdt, maCa, maSanh, userName));

        int flag = 1;
        if (kq1 > 0) {
            int kq2 = 0;
            for (DTMonAn x : CTMonAns) {
                kq2 = ChiTietMonAnDAO.getInstance().Insert(new ChiTietMonAn(maTiecCuoi, x.getMaMonAn(), x.getDonGia(), tongSLB, x.getGhiChu()));
                if (kq2 == 0) {
                    flag = 0;
                    break;
                }
            }
            if (kq2 > 0) {
                int kq3 = 0;
                for (DTDichVu x : CTDichVus) {
                    int temp = x.getSoLuong() * x.getDonGia();
                    kq3 = ChiTietDichVuDAO.getInstance().Insert(new ChiTietDichVu(maTiecCuoi, x.getMaDichVu(), x.getSoLuong(), x.getDonGia(), temp));
                    if (kq3 == 0) {
                        flag = 0;
                        break;
                    }
                }

            }

        }

        if (flag > 0) {
            File file = new File("src/report/rptPhieuDatTiec.jasper");
            String absolutePath = file.getAbsolutePath();
            try {
                HashMap<String, Object> map = new HashMap<>();
                Connection con = JDBCUtil.getConnection();
                System.out.println(maTiecCuoi);
                System.out.println(Double.parseDouble(inputSoTienDaNhan.getText()));
                System.out.println(Double.parseDouble(inputSoTienDaNhan.getText()) - tienCoc);
                System.out.println(tyLePhat);
                System.out.println(thoiGianPhat);
                map.put("maTiecCuoi", maTiecCuoi);
                map.put("tienKhachTra", (double) tienCoc);
                map.put("tienThua", (double) 0.0);
                map.put("tyLePhat", tyLePhat);
                map.put("thoiGianPhat", thoiGianPhat);

                JasperPrint p = JasperFillManager.fillReport(absolutePath, map, con);
                JasperViewer v = new JasperViewer(p, false);
                v.setVisible(true);

            } catch (JRException ex) {
                System.out.println(ex);

            }
        }
        Page1.setVisible(true);
        Page4.setVisible(false);
        Page1();
        inputTenChuRe.setText("");
        inputTenCoDau.setText("");
        inputSDT.setText("");
        inputSoLuongBan.setValue(0);
        inputSLDT.setValue(0);
        inputNgayDaiTiec.setDate(null);
        ChuyenKhoanForm.setVisible(false);
    }//GEN-LAST:event_btnDaThanhToanActionPerformed

    private void inputSLDTStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_inputSLDTStateChanged
        // TODO add your handling code here:
        if (SoLuongBanToiDa != 0) {
            int limit = SoLuongBanToiDa - Integer.parseInt(inputSoLuongBan.getValue().toString());
            if (Integer.parseInt(inputSLDT.getValue().toString()) > limit) {
                inputSLDT.setValue(limit);
            }
        } else {
            inputSLDT.setValue(0);
        }
    }//GEN-LAST:event_inputSLDTStateChanged

    public void UpdateChonDV(boolean chon, int soluong, int row) {
        for (DTDichVu x : dTDichVus) {
            if (x.getMaDichVu().equals(DichVuTable.getValueAt(row, 1))) {
                x.setChon(chon);
                x.setSoLuong(soluong);
            }
        }
    }

    public void UpdateChon(boolean chon, String ghiChu, int row) {
        for (DTMonAn x : dTMonAns) {
            if (x.getMaMonAn().equals(ThucDonTable.getValueAt(row, 1))) {
                x.setChon(chon);
                x.setGhiChu(ghiChu);
            }
        }
    }

    public void UpdateGhiChu(String ghiChu, int row) {
        for (DTMonAn x : dTMonAns) {
            if (x.getMaMonAn().equals(ThucDonTable.getValueAt(row, 1))) {
                x.setGhiChu(ghiChu);
            }
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackPage2;
    private javax.swing.JButton BackPage3;
    private javax.swing.JButton BackPage4;
    private javax.swing.JDialog ChuyenKhoanForm;
    private javax.swing.JLabel ConLai;
    private javax.swing.JTable DichVuDatTiecTable;
    private javax.swing.JTable DichVuTable;
    private javax.swing.JLabel DonBanToiThieu;
    private javax.swing.JLabel DonGiaBan;
    private javax.swing.JButton ExitSearchFood;
    private javax.swing.JDialog FilterFood;
    private javax.swing.JLabel FilterFoodButton;
    private javax.swing.JLabel KhoangDonGia;
    private javax.swing.JButton NextPage1;
    private javax.swing.JButton NextPage2;
    private javax.swing.JButton NextPage3;
    private javax.swing.JButton NextPage5;
    private javax.swing.JLabel NgayDaiTiec;
    private javax.swing.JPanel Page1;
    private javax.swing.JPanel Page2;
    private javax.swing.JPanel Page3;
    private javax.swing.JPanel Page4;
    private javax.swing.JButton SaveSearchFood;
    private javax.swing.JSlider SilderFood;
    private javax.swing.JLabel SoLuongBan;
    private javax.swing.JLabel TenChuRe;
    private javax.swing.JLabel TenCoDau;
    private javax.swing.JDialog ThanhToanTienMat;
    private javax.swing.JTable ThucDonTable;
    private javax.swing.JLabel TienCoc;
    private javax.swing.JLabel TongDonBanHT;
    private javax.swing.JLabel TongTienBan;
    private javax.swing.JLabel TongTienDV;
    private javax.swing.JLabel TongTienHD;
    private javax.swing.JLabel ValueDGMAX;
    private javax.swing.JButton btn100tr;
    private javax.swing.JButton btn200tr;
    private javax.swing.JButton btn300tr;
    private javax.swing.JButton btn400tr;
    private javax.swing.JButton btn500tr;
    private javax.swing.JButton btn50tr;
    private javax.swing.JButton btn70tr;
    private javax.swing.JButton btn90tr;
    private javax.swing.JButton btnCancelCKForm;
    private javax.swing.JButton btnDaThanhToan;
    private javax.swing.JButton btnXacNhan;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnhaiMuoiTrieu;
    private javax.swing.JButton btnmuoiTrieu;
    private javax.swing.JComboBox<String> cbxLoaiMonAn;
    private javax.swing.JComboBox<String> cbxPTTT;
    private javax.swing.JComboBox<String> inputCa;
    private com.toedter.calendar.JDateChooser inputNgayDaiTiec;
    private com.toedter.calendar.JDateChooser inputNgayDatTiec;
    private javax.swing.JTextField inputSDT;
    private javax.swing.JSpinner inputSLDT;
    private javax.swing.JComboBox<String> inputSanh;
    private javax.swing.JSpinner inputSoLuongBan;
    private javax.swing.JTextField inputSoTienDaNhan;
    private javax.swing.JTextField inputTenChuRe;
    private javax.swing.JTextField inputTenCoDau;
    private javax.swing.JTextField inputTyLeTienCoc;
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
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel tienCocText;
    private javax.swing.JTextField txfSearchFood;
    private javax.swing.JTextField txfSearchService;
    // End of variables declaration//GEN-END:variables
}
