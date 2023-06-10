/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import dao.LoaiSanhDAO;
import java.awt.Color;
import java.awt.Font;
import java.awt.TrayIcon;
import java.util.ArrayList;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import model.LoaiSanh;

/**
 *
 * @author macbookpro
 */
public class PartyHallTypeList extends javax.swing.JInternalFrame {

    private DefaultTableModel defaultTableModelHallType;
    private ArrayList<LoaiSanh> loaiSanhs = LoaiSanhDAO.getInstance().SelectAll();

    /**
     * Creates new form WorkingTimeList
     */
    public PartyHallTypeList() {

        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        CreateDataTable();
    }

    public void CreateDataTable() {
        defaultTableModelHallType = (DefaultTableModel) Table_Hall_Type.getModel();
        int i = 0;
        for (LoaiSanh x : loaiSanhs) {
            defaultTableModelHallType.addRow(new Object[]{++i, x.getMaLoaiSanh(), x.getTenLoaiSanh(), x.getDonGiaBanToiThieu()});
        }
    }

    public void SearchTable(String value) {
        defaultTableModelHallType.setRowCount(0);
        defaultTableModelHallType = (DefaultTableModel) Table_Hall_Type.getModel();
        int i = 0;

        for (LoaiSanh x : loaiSanhs) {
            System.out.println();
            if (x.getMaLoaiSanh().toLowerCase().contains(value.toLowerCase()) || x.getTenLoaiSanh().toLowerCase().contains(value.toLowerCase())) {
                defaultTableModelHallType.addRow(new Object[]{++i, x.getMaLoaiSanh(), x.getTenLoaiSanh(), x.getDonGiaBanToiThieu()});
            }
        }
    }

    public void ReloadDataTable() {
        loaiSanhs = LoaiSanhDAO.getInstance().SelectAll();
        defaultTableModelHallType.setRowCount(0);
        CreateDataTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Add_HallType_List_Dialog = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txftenLoaiSanh = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txfdonGiaToiThieu = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        addHallType = new javax.swing.JButton();
        Edit_HallType_List_Dialog = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tenLoaiSanhValue = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        donGiaToiThieuValue = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        btnUpdateHallType = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnAddHallType = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_Hall_Type = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        btnDeleteHallType = new javax.swing.JButton();
        btnEditHallType = new javax.swing.JButton();
        jPanel35 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        txfSearchTable = new javax.swing.JTextField();
        Back = new javax.swing.JButton();

        Add_HallType_List_Dialog.setMinimumSize(new java.awt.Dimension(531, 490));
        Add_HallType_List_Dialog.setModal(true);
        Add_HallType_List_Dialog.setResizable(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(69, 96, 134));
        jLabel4.setText("Thêm loại sảnh");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel5.setText("Loại sảnh");

        txftenLoaiSanh.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel7.setText("Đơn giá tối thiểu");

        txfdonGiaToiThieu.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        txfdonGiaToiThieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfdonGiaToiThieuActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButton2.setText("Hủy");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        addHallType.setBackground(new java.awt.Color(132, 70, 133));
        addHallType.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        addHallType.setForeground(new java.awt.Color(255, 255, 255));
        addHallType.setText("Thêm ");
        addHallType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addHallTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(addHallType, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel7)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txftenLoaiSanh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                                    .addComponent(txfdonGiaToiThieu, javax.swing.GroupLayout.Alignment.LEADING))
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(31, 31, 31))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txftenLoaiSanh, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txfdonGiaToiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addHallType, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout Add_HallType_List_DialogLayout = new javax.swing.GroupLayout(Add_HallType_List_Dialog.getContentPane());
        Add_HallType_List_Dialog.getContentPane().setLayout(Add_HallType_List_DialogLayout);
        Add_HallType_List_DialogLayout.setHorizontalGroup(
            Add_HallType_List_DialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Add_HallType_List_DialogLayout.setVerticalGroup(
            Add_HallType_List_DialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Edit_HallType_List_Dialog.setMinimumSize(new java.awt.Dimension(531, 490));
        Edit_HallType_List_Dialog.setModal(true);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(69, 96, 134));
        jLabel9.setText("Cập nhật loại sảnh");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel10.setText("Loại sảnh");

        tenLoaiSanhValue.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel11.setText("Đơn giá tối thiểu");

        donGiaToiThieuValue.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        donGiaToiThieuValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                donGiaToiThieuValueActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButton4.setText("Hủy");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        btnUpdateHallType.setBackground(new java.awt.Color(132, 70, 133));
        btnUpdateHallType.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        btnUpdateHallType.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateHallType.setText("Cập nhật");
        btnUpdateHallType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateHallTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnUpdateHallType, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tenLoaiSanhValue, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                                    .addComponent(donGiaToiThieuValue, javax.swing.GroupLayout.Alignment.LEADING))
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(31, 31, 31))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tenLoaiSanhValue, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(donGiaToiThieuValue, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateHallType, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout Edit_HallType_List_DialogLayout = new javax.swing.GroupLayout(Edit_HallType_List_Dialog.getContentPane());
        Edit_HallType_List_Dialog.getContentPane().setLayout(Edit_HallType_List_DialogLayout);
        Edit_HallType_List_DialogLayout.setHorizontalGroup(
            Edit_HallType_List_DialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Edit_HallType_List_DialogLayout.setVerticalGroup(
            Edit_HallType_List_DialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setPreferredSize(new java.awt.Dimension(1170, 730));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1170, 730));

        btnAddHallType.setBackground(new java.awt.Color(99, 122, 48));
        btnAddHallType.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        btnAddHallType.setForeground(new java.awt.Color(255, 255, 255));
        btnAddHallType.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plus.png"))); // NOI18N
        btnAddHallType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddHallTypeActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Hall.png"))); // NOI18N

        Table_Hall_Type.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Table_Hall_Type.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã loại sảnh", "Tên loại sảnh", "Đơn giá tối thiểu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table_Hall_Type.setFocusable(false);
        Table_Hall_Type.setRowHeight(25);
        Table_Hall_Type.setSelectionBackground(new java.awt.Color(69, 96, 134));
        jScrollPane1.setViewportView(Table_Hall_Type);
        if (Table_Hall_Type.getColumnModel().getColumnCount() > 0) {
            Table_Hall_Type.getColumnModel().getColumn(0).setMinWidth(100);
            Table_Hall_Type.getColumnModel().getColumn(0).setPreferredWidth(100);
            Table_Hall_Type.getColumnModel().getColumn(0).setMaxWidth(50);
            Table_Hall_Type.getColumnModel().getColumn(1).setMinWidth(200);
            Table_Hall_Type.getColumnModel().getColumn(1).setPreferredWidth(200);
            Table_Hall_Type.getColumnModel().getColumn(1).setMaxWidth(20);
        }
        Table_Hall_Type.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        Table_Hall_Type.getTableHeader().setOpaque(false);
        Table_Hall_Type.getTableHeader().setBackground(new Color(243,246,249));
        Table_Hall_Type.setDefaultEditor(Object.class, null);
        Table_Hall_Type.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(69, 96, 134));
        jLabel13.setText("DANH SÁCH LOẠI SẢNH");
        jPanel5.add(jLabel13);

        btnDeleteHallType.setBackground(new java.awt.Color(132, 70, 133));
        btnDeleteHallType.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        btnDeleteHallType.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteHallType.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Delete.png"))); // NOI18N
        btnDeleteHallType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteHallTypeActionPerformed(evt);
            }
        });

        btnEditHallType.setBackground(new java.awt.Color(248, 189, 141));
        btnEditHallType.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        btnEditHallType.setForeground(new java.awt.Color(255, 255, 255));
        btnEditHallType.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Edit.png"))); // NOI18N
        btnEditHallType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditHallTypeActionPerformed(evt);
            }
        });

        jPanel35.setBackground(new java.awt.Color(238, 230, 226));
        jPanel35.setPreferredSize(new java.awt.Dimension(260, 50));

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Group 9.png"))); // NOI18N

        txfSearchTable.setBackground(new java.awt.Color(238, 230, 226));
        txfSearchTable.setBorder(null);
        txfSearchTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txfSearchTableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txfSearchTable, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel28)
                .addContainerGap(18, Short.MAX_VALUE))
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txfSearchTable)
                .addContainerGap())
        );

        txfSearchTable.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                SearchTable(txfSearchTable.getText());
            }
            public void removeUpdate(DocumentEvent e) {
                if(txfSearchTable.getText().equals("")){
                    defaultTableModelHallType.setRowCount(0);
                    CreateDataTable();
                }else{
                    SearchTable(txfSearchTable.getText());
                }
            }
            public void insertUpdate(DocumentEvent e) {
                SearchTable(txfSearchTable.getText());
            }
        });

        Back.setBackground(new java.awt.Color(69, 96, 134));
        Back.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        Back.setForeground(new java.awt.Color(255, 255, 255));
        Back.setText("Quay lại");
        Back.setPreferredSize(new java.awt.Dimension(90, 40));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Back, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnAddHallType, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDeleteHallType, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditHallType, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(btnDeleteHallType, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddHallType, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditHallType, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Back, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1158, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void Message(String message, int messageType) {
        JOptionPane jOptionPane = new JOptionPane(message, messageType);
        JDialog dialog = jOptionPane.createDialog(null, "Message");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    private void btnAddHallTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddHallTypeActionPerformed

        Add_HallType_List_Dialog.setLocationRelativeTo(null);
        Add_HallType_List_Dialog.setVisible(true);
    }//GEN-LAST:event_btnAddHallTypeActionPerformed

    private void btnDeleteHallTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteHallTypeActionPerformed
        // TODO add your handling code here:
        int row = Table_Hall_Type.getSelectedRow();
        int[] rows = Table_Hall_Type.getSelectedRows();
        if (row < 0) {
            Message("Vui lòng chọn dữ liệu muốn xoá!", JOptionPane.INFORMATION_MESSAGE);
        } else {

            String mess = "";
            if (rows.length == 1) {
                mess = String.valueOf(Table_Hall_Type.getValueAt(row, 1)) + " ";
            } else {
                for (int r : rows) {
                    mess += String.valueOf(Table_Hall_Type.getValueAt(r, 1)) + " ";
                }
            }

            int x = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá " + mess + "hay không?");
            if (x == JOptionPane.YES_OPTION) {
                int kq = 0;
                if (rows.length == 1) {
                    String maLoaiSanh = String.valueOf(Table_Hall_Type.getValueAt(row, 1));
                    String tenLoaiSanh = String.valueOf(Table_Hall_Type.getValueAt(row, 2));
                    int donGiaToiThieu = Integer.parseInt(String.valueOf(Table_Hall_Type.getValueAt(row, 3)));

                    try {
                        kq = LoaiSanhDAO.getInstance().Delete(new LoaiSanh(maLoaiSanh, tenLoaiSanh, donGiaToiThieu));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    for (int r : rows) {
                        String maLoaiSanh = String.valueOf(Table_Hall_Type.getValueAt(r, 1));
                        String tenLoaiSanh = String.valueOf(Table_Hall_Type.getValueAt(r, 2));
                        int donGiaToiThieu = Integer.parseInt(String.valueOf(Table_Hall_Type.getValueAt(r, 3)));

                        try {
                            kq = LoaiSanhDAO.getInstance().Delete(new LoaiSanh(maLoaiSanh, tenLoaiSanh, donGiaToiThieu));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (kq > 0) {
                    ReloadDataTable();
                } else {
                    Message("Xoá dữ liệu thất bại!", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    }//GEN-LAST:event_btnDeleteHallTypeActionPerformed

    private void btnEditHallTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditHallTypeActionPerformed

        int row = Table_Hall_Type.getSelectedRow();
        if (row < 0) {
            Message("Vui lòng chọn dữ liệu muốn chỉnh sửa!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            tenLoaiSanhValue.setText(String.valueOf(Table_Hall_Type.getValueAt(row, 2)));
            donGiaToiThieuValue.setText(String.valueOf(Table_Hall_Type.getValueAt(row, 3)));
            Edit_HallType_List_Dialog.setLocationRelativeTo(null);
            Edit_HallType_List_Dialog.setVisible(true);
        }

    }//GEN-LAST:event_btnEditHallTypeActionPerformed

    private void txfSearchTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfSearchTableActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfSearchTableActionPerformed

    private void txfdonGiaToiThieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txfdonGiaToiThieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfdonGiaToiThieuActionPerformed

    private void donGiaToiThieuValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_donGiaToiThieuValueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_donGiaToiThieuValueActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Add_HallType_List_Dialog.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnUpdateHallTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateHallTypeActionPerformed
        // TODO add your handling code here:
        int row = Table_Hall_Type.getSelectedRow();
        String maLoaiSanh = String.valueOf(Table_Hall_Type.getValueAt(row, 1));
        int kq = 0;
        if (!tenLoaiSanhValue.getText().equals(String.valueOf(Table_Hall_Type.getValueAt(row, 2)))
                || !donGiaToiThieuValue.getText().equals(String.valueOf(Table_Hall_Type.getValueAt(row, 3)))) {
            kq = LoaiSanhDAO.getInstance().Update(new LoaiSanh(maLoaiSanh, tenLoaiSanhValue.getText(), Integer.parseInt(donGiaToiThieuValue.getText())));
        }
        if (kq > 0) {
            Edit_HallType_List_Dialog.setVisible(false);
            ReloadDataTable();
            tenLoaiSanhValue.setText("");
            donGiaToiThieuValue.setText("");
        } else {
            Edit_HallType_List_Dialog.setVisible(false);
            Message("Chỉnh sửa dữ liệu thất bại!", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_btnUpdateHallTypeActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Edit_HallType_List_Dialog.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void addHallTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addHallTypeActionPerformed
        // TODO add your handling code here:
        if (txftenLoaiSanh.getText().equals("") || txfdonGiaToiThieu.getText().equals("")) {
            Message("Vui lòng nhập dữ liệu!", JOptionPane.WARNING_MESSAGE);
        } else {
            String maLS = String.valueOf(LoaiSanhDAO.getInstance().GetID() + 1);
            switch (maLS.length()) {
                case 1:
                    maLS = "LS000" + maLS;
                    break;
                case 2:
                    maLS = "LS00" + maLS;
                    break;
                case 3:
                    maLS = "LS0" + maLS;
                    break;
                case 4:
                    maLS = "LS" + maLS;
                    break;
            }
            int kq = 0;
            try {
                kq = LoaiSanhDAO.getInstance().Insert(new LoaiSanh(maLS, txftenLoaiSanh.getText(),
                        Integer.parseInt(txfdonGiaToiThieu.getText())));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (kq != 0) {
                Add_HallType_List_Dialog.setVisible(false);
                ReloadDataTable();
                txftenLoaiSanh.setText("");
                txfdonGiaToiThieu.setText("");
            } else {
                Message("Lỗi! Thêm dữ liệu thất bại. Vui lòng nhập lại dữ liệu.", JOptionPane.ERROR_MESSAGE);
                txftenLoaiSanh.setText("");
                txfdonGiaToiThieu.setText("");
            }
        }
    }//GEN-LAST:event_addHallTypeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Add_HallType_List_Dialog;
    private javax.swing.JButton Back;
    private javax.swing.JDialog Edit_HallType_List_Dialog;
    private javax.swing.JTable Table_Hall_Type;
    private javax.swing.JButton addHallType;
    private javax.swing.JButton btnAddHallType;
    private javax.swing.JButton btnDeleteHallType;
    private javax.swing.JButton btnEditHallType;
    private javax.swing.JButton btnUpdateHallType;
    private javax.swing.JTextField donGiaToiThieuValue;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tenLoaiSanhValue;
    private javax.swing.JTextField txfSearchTable;
    private javax.swing.JTextField txfdonGiaToiThieu;
    private javax.swing.JTextField txftenLoaiSanh;
    // End of variables declaration//GEN-END:variables
}
