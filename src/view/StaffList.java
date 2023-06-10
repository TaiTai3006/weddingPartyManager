/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import dao.ChucVuDAO;
import dao.NhanVienDAO;
import java.awt.Color;
import java.awt.Font;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import model.ChucVu;
import model.TaiKhoan;

/**
 *
 * @author macbookpro
 */
public class StaffList extends javax.swing.JInternalFrame {

    private DefaultTableModel defaultTableStaff;
    private ArrayList<TaiKhoan> lstStaff = NhanVienDAO.getInstance().SelectAll();
    private ArrayList<ChucVu> lstChucVu = ChucVuDAO.getInstance().SelectAll();
    Map<String, String> mapTenChucVu = new HashMap();
    Map<String, String> mapTenTaiKhoan = new HashMap();

    /**
     * Creates new form StaffList
     */
    public StaffList() {

        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        CreatDataTable();

        for (TaiKhoan nv : lstStaff) {
            mapTenTaiKhoan.put(nv.getUserName(), nv.getPassWord());
        }
        for (ChucVu cv : lstChucVu) {
            mapTenChucVu.put(cv.getTenChucVu(), cv.getMaChucVu());
            cbQuyenAdd.addItem(cv.getTenChucVu());
            cbQuyenUD.addItem(cv.getTenChucVu());

        }
    }

    public void CreatDataTable() {
        defaultTableStaff = (DefaultTableModel) tblStaff.getModel();
        int i = 0;
        for (TaiKhoan nv : lstStaff) {
            defaultTableStaff.addRow(new Object[]{++i, nv.getUserName(), nv.getTenChucVu()});
        }
    }

    public void SearchTable() {
        defaultTableStaff.setRowCount(0);
        defaultTableStaff = (DefaultTableModel) tblStaff.getModel();
        int i = 0;
        String value = txtSearch.getText();
        for (TaiKhoan x : lstStaff) {
            System.out.println();
            if (x.getUserName().toLowerCase().contains(value.toLowerCase()) || x.getTenChucVu().toLowerCase().contains(value.toLowerCase())) {
                defaultTableStaff.addRow(new Object[]{++i, x.getUserName(), x.getTenChucVu()});
            }
        }
    }

    public void ReloadDataTable() {
        lstStaff = NhanVienDAO.getInstance().SelectAll();
        defaultTableStaff.setRowCount(0);
        CreatDataTable();
    }

    public void Message(String message, int messageType) {
        JOptionPane jOptionPane = new JOptionPane(message, messageType);
        JDialog dialog = jOptionPane.createDialog(null, "Message");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    public static byte[] hashPW(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedPassword = password.getBytes(StandardCharsets.UTF_8);
        byte[] hashedPassword = digest.digest(encodedPassword);
        return hashedPassword;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addForm = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUserNameAdd = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        cbQuyenAdd = new javax.swing.JComboBox<>();
        updateForm = new javax.swing.JDialog();
        jPanel8 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtUserNameUD = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        btnCancelUD = new javax.swing.JButton();
        btnUpdateUD = new javax.swing.JButton();
        cbQuyenUD = new javax.swing.JComboBox<>();
        chkbReset = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStaff = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        btnAddStaff = new javax.swing.JButton();
        btnDeleteStaff = new javax.swing.JButton();
        BackPage3 = new javax.swing.JButton();
        btnUpdateStaff = new javax.swing.JButton();

        addForm.setMinimumSize(new java.awt.Dimension(453, 383));
        addForm.setModal(true);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(69, 96, 134));
        jLabel3.setText("Thêm tài khoản ");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel5.setText("Tên tài khoản");

        txtUserNameAdd.setBackground(new java.awt.Color(242, 242, 242));
        txtUserNameAdd.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel7.setText("Phân quyền");

        btnCancel.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnCancel.setText("Hủy");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnAdd.setBackground(new java.awt.Color(132, 70, 133));
        btnAdd.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Thêm ");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        cbQuyenAdd.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtUserNameAdd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbQuyenAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUserNameAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbQuyenAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout addFormLayout = new javax.swing.GroupLayout(addForm.getContentPane());
        addForm.getContentPane().setLayout(addFormLayout);
        addFormLayout.setHorizontalGroup(
            addFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        addFormLayout.setVerticalGroup(
            addFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        updateForm.setMinimumSize(new java.awt.Dimension(440, 400));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(69, 96, 134));
        jLabel18.setText("Cập nhật nhân viên");

        jLabel19.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel19.setText("Tên tài khoản");

        txtUserNameUD.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel21.setText("Phân quyền");

        btnCancelUD.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnCancelUD.setText("Hủy");
        btnCancelUD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelUDActionPerformed(evt);
            }
        });

        btnUpdateUD.setBackground(new java.awt.Color(132, 70, 133));
        btnUpdateUD.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnUpdateUD.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateUD.setText("Cập nhật");
        btnUpdateUD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateUDActionPerformed(evt);
            }
        });

        cbQuyenUD.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        chkbReset.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        chkbReset.setText("Đặt lại mật khẩu");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(cbQuyenUD, 0, 146, Short.MAX_VALUE)
                        .addGap(54, 54, 54)
                        .addComponent(chkbReset)
                        .addGap(61, 61, 61))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(txtUserNameUD)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(btnCancelUD, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdateUD, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUserNameUD, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbQuyenUD, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkbReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateUD, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelUD, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout updateFormLayout = new javax.swing.GroupLayout(updateForm.getContentPane());
        updateForm.getContentPane().setLayout(updateFormLayout);
        updateFormLayout.setHorizontalGroup(
            updateFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        updateFormLayout.setVerticalGroup(
            updateFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setPreferredSize(new java.awt.Dimension(1170, 730));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1180, 730));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Account.png"))); // NOI18N

        tblStaff.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        tblStaff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên tài khoản", "Chức vụ"
            }
        ));
        tblStaff.setFocusable(false);
        tblStaff.setRowHeight(25);
        tblStaff.setSelectionBackground(new java.awt.Color(69, 96, 134));
        tblStaff.setShowGrid(false);
        jScrollPane1.setViewportView(tblStaff);
        tblStaff.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        tblStaff.getTableHeader().setOpaque(false);
        tblStaff.getTableHeader().setBackground(new Color(243,246,249));
        tblStaff.setDefaultEditor(Object.class, null);

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
                    defaultTableStaff.setRowCount(0);
                    CreatDataTable();
                }else{
                    SearchTable();
                }
            }
            public void insertUpdate(DocumentEvent e) {
                SearchTable();
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(1158, 50));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1158, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(69, 96, 134));
        jLabel13.setText("DANH SÁCH TÀI KHOẢN");
        jPanel5.add(jLabel13);

        btnAddStaff.setBackground(new java.awt.Color(99, 122, 48));
        btnAddStaff.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        btnAddStaff.setForeground(new java.awt.Color(255, 255, 255));
        btnAddStaff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plus.png"))); // NOI18N
        btnAddStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddStaffActionPerformed(evt);
            }
        });

        btnDeleteStaff.setBackground(new java.awt.Color(132, 70, 133));
        btnDeleteStaff.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        btnDeleteStaff.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteStaff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Delete.png"))); // NOI18N
        btnDeleteStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteStaffActionPerformed(evt);
            }
        });

        BackPage3.setBackground(new java.awt.Color(69, 96, 134));
        BackPage3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        BackPage3.setForeground(new java.awt.Color(255, 255, 255));
        BackPage3.setText("Quay lại");
        BackPage3.setPreferredSize(new java.awt.Dimension(90, 40));

        btnUpdateStaff.setBackground(new java.awt.Color(248, 189, 141));
        btnUpdateStaff.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        btnUpdateStaff.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateStaff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Edit.png"))); // NOI18N
        btnUpdateStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateStaffActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BackPage3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnAddStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDeleteStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdateStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(100, 100, 100))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdateStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BackPage3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1158, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        addForm.setVisible(false);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnAddStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddStaffActionPerformed
        // TODO add your handling code here: 
        addForm.setLocationRelativeTo(null);
        addForm.setVisible(true);
    }//GEN-LAST:event_btnAddStaffActionPerformed

    private void btnDeleteStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteStaffActionPerformed
        // TODO add your handling code here:
        int row = tblStaff.getSelectedRow();
        int[] rows = tblStaff.getSelectedRows();
        if (row < 0) {
            Message("Vui lòng chọn dữ liệu muốn xoá!", JOptionPane.INFORMATION_MESSAGE);
        } else {

            String mess = "";
            if (rows.length == 1) {
                mess = String.valueOf(tblStaff.getValueAt(row, 1)) + " ";
            } else {
                for (int r : rows) {
                    mess += String.valueOf(tblStaff.getValueAt(r, 1)) + " ";
                }
            }

            int x = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá " + mess + "hay không?");
            if (x == JOptionPane.YES_OPTION) {
                int kq = 0;
                if (rows.length == 1) {
                    String tenTaiKhoan = String.valueOf(tblStaff.getValueAt(row, 1));
                    String matKhau = mapTenTaiKhoan.get(tenTaiKhoan);
                    String tenChucVu = String.valueOf(tblStaff.getValueAt(row, 2));
                    String maChucVu = mapTenChucVu.get(tenChucVu);
                    try {
                        kq = NhanVienDAO.getInstance().Delete(new TaiKhoan(tenTaiKhoan, matKhau, maChucVu, tenChucVu));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    for (int r : rows) {
                        String tenTaiKhoan = String.valueOf(tblStaff.getValueAt(r, 1));
                        String matKhau = mapTenTaiKhoan.get(tenTaiKhoan);
                        String tenChucVu = String.valueOf(tblStaff.getValueAt(r, 2));
                        String maChucVu = mapTenChucVu.get(tenChucVu);

                        try {
                            kq = NhanVienDAO.getInstance().Delete(new TaiKhoan(tenTaiKhoan, matKhau, maChucVu, tenChucVu));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (kq < 0) {
                            Message("Xoá dữ liệu " + tenTaiKhoan + " thất bại!", JOptionPane.ERROR_MESSAGE);
                        }
                        kq = 1;
                    }
                }
                if (kq > 0) {
                    ReloadDataTable();
                } else {
                    Message("Xoá dữ liệu thất bại!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnDeleteStaffActionPerformed

    private void btnUpdateStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateStaffActionPerformed
        // TODO add your handling code here:
        int row = tblStaff.getSelectedRow();
        if (row < 0) {
            Message("Vui lòng chọn dữ liệu muốn chỉnh sửa!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String tenTaiKhoan = String.valueOf(tblStaff.getValueAt(row, 1));
            txtUserNameUD.setText(tenTaiKhoan);
            String tenChucVu = String.valueOf(tblStaff.getValueAt(row, 2));
            cbQuyenUD.setSelectedItem(tenChucVu);
            txtUserNameUD.enable(false);
            updateForm.setLocationRelativeTo(null);
            updateForm.setVisible(true);
        }
    }//GEN-LAST:event_btnUpdateStaffActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        if (txtUserNameAdd.getText().equals("")) {
            Message("Vui lòng nhập đầy đủ dữ liệu!", JOptionPane.WARNING_MESSAGE);
        } else {
            int kq = 0;
            try {
                String tenTaiKhoan = txtUserNameAdd.getText();
                String tenChucVu = cbQuyenAdd.getSelectedItem().toString();
                String maChucVu = mapTenChucVu.get(tenChucVu);

                boolean check = true;
                for (TaiKhoan nv : lstStaff) {
                    if (nv.getUserName().equals(tenTaiKhoan)) {
                        check = false;
                    }
                }
                if (!check) {
                    Message("Tài khoản đã tồn tại, vui lòng nhập lại!", JOptionPane.WARNING_MESSAGE);
                    txtUserNameAdd.setText("");
                    return;
                } else {
                    kq = NhanVienDAO.getInstance().Insert(new TaiKhoan(tenTaiKhoan, "", maChucVu, tenChucVu));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (kq != 0) {
                addForm.setVisible(false);
                ReloadDataTable();
                txtUserNameAdd.setText("");
            } else {
                Message("Lỗi! Thêm dữ liệu thất bại. Vui lòng nhập lại dữ liệu.", JOptionPane.ERROR_MESSAGE);
                txtUserNameAdd.setText("");
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnCancelUDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelUDActionPerformed
        // TODO add your handling code here:
        updateForm.setVisible(false);
    }//GEN-LAST:event_btnCancelUDActionPerformed

    private void btnUpdateUDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateUDActionPerformed
        // TODO add your handling code here:
        int row = tblStaff.getSelectedRow();

        int kq = 0;
        String tenTaiKhoan = txtUserNameUD.getText();
        String tenChucVu = cbQuyenUD.getSelectedItem().toString();
        String maChucVu = mapTenChucVu.get(tenChucVu);
        String matKhau = mapTenTaiKhoan.get(tenTaiKhoan);
        if (chkbReset.isSelected()) {
            String pw = "uit123";
            try {
                byte[] hash = hashPW(pw);
                matKhau = new String(hash);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(StaffList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        kq = NhanVienDAO.getInstance().Update(new TaiKhoan(tenTaiKhoan, matKhau, maChucVu, tenChucVu));

        if (kq > 0) {
            updateForm.setVisible(false);
            ReloadDataTable();
        } else {
            updateForm.setVisible(false);
            Message("Chỉnh sửa dữ liệu thất bại!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnUpdateUDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackPage3;
    private javax.swing.JDialog addForm;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddStaff;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCancelUD;
    private javax.swing.JButton btnDeleteStaff;
    private javax.swing.JButton btnUpdateStaff;
    private javax.swing.JButton btnUpdateUD;
    private javax.swing.JComboBox<String> cbQuyenAdd;
    private javax.swing.JComboBox<String> cbQuyenUD;
    private javax.swing.JCheckBox chkbReset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblStaff;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtUserNameAdd;
    private javax.swing.JTextField txtUserNameUD;
    private javax.swing.JDialog updateForm;
    // End of variables declaration//GEN-END:variables
}
