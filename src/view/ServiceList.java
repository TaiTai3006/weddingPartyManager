/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import java.util.ArrayList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author macbookpro
 */
public class ServiceList extends javax.swing.JInternalFrame {
    class DichVu{
        String MaDV;
        String TenDichVu;
        String DonGia;
        String TuyChon;

        public DichVu(String MaDV, String TenDichVu, String DonGia, String TuyChon) {
            this.MaDV = MaDV;
            this.TenDichVu = TenDichVu;
            this.DonGia = DonGia;
            this.TuyChon = TuyChon;
        }

        public String getMaDV() {
            return MaDV;
        }

        public String getTenDichVu() {
            return TenDichVu;
        }

        public String getDonGia() {
            return DonGia;
        }

        public String getTuyChon() {
            return TuyChon;
        }
        
    }
    /**
     * Creates new form ServiceList
     */
    public ServiceList() {
        initComponents();
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        ArrayList<DichVu> items = new ArrayList<>();
        items.add(new DichVu("DV01", "Ca nhac","500000",""));
        items.add(new DichVu("DV02", "Hoa cuoi","300000",""));
        items.add(new DichVu("DV03", "Don dep","200000",""));
        items.add(new DichVu("DV04", "Banh kem","500000",""));
        items.add(new DichVu("DV05", "San khau","500000",""));
        items.add(new DichVu("DV06", "Mua lua","500000",""));
        ArrayList<DichVu> filteredItems = new ArrayList<>(items);
        DefaultTableModel defaulttable = new DefaultTableModel();
        table_service_list.setModel(defaulttable);
        defaulttable.addColumn("STT");
        defaulttable.addColumn("Ma dich vu");
        defaulttable.addColumn("Ten dich vu");
        defaulttable.addColumn("Don gia");
        defaulttable.addColumn("Tuy chon");
        int count = 1;
        for(DichVu row : items){
            defaulttable.addRow(new Object[]{count++, row.getMaDV(),row.getTenDichVu(),row.getDonGia(),row.getTuyChon()});
        }
            search_field.getDocument().addDocumentListener(new DocumentListener() {
            int count1 = 1;
            @Override
            public void insertUpdate(DocumentEvent e) {
                String filterText = search_field.getText().toLowerCase();
                filteredItems.clear();
                for (DichVu item : items) {
                    if (item.getMaDV().toLowerCase().contains(filterText) || item.getTenDichVu().toLowerCase().contains(filterText)) {
                        filteredItems.add(item);
                    }
                }
                DefaultTableModel model = (DefaultTableModel) table_service_list.getModel();
                model.setRowCount(0);
                for (DichVu row : filteredItems) {
                    model.addRow(new Object[]{count1++, row.getMaDV(),row.getTenDichVu(),row.getDonGia(),row.getTuyChon()});
                } 
            }
            int count2 = 1;
            @Override
            public void removeUpdate(DocumentEvent e) {
                String filterText = search_field.getText().toLowerCase();
                filteredItems.clear();
                for (DichVu item : items) {
                    if (item.getMaDV().toLowerCase().contains(filterText) || item.getTenDichVu().toLowerCase().contains(filterText)) {
                        filteredItems.add(item);
                    }
                }
                DefaultTableModel model = (DefaultTableModel) table_service_list.getModel();
                model.setRowCount(0);
                for (DichVu row : filteredItems) {
                    model.addRow(new Object[]{count2++, row.getMaDV(),row.getTenDichVu(),row.getDonGia(),row.getTuyChon()});
                }  
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        service_list_dialog = new javax.swing.JDialog();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        delete_dichvu_dialog_btn = new javax.swing.JButton();
        add_dich_dialog_btn = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        add_dichvu_field = new javax.swing.JTextField();
        add_dongia_field = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        search_field = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        add_service_btn = new javax.swing.JButton();
        delete_service_btn = new javax.swing.JButton();
        edit_service_btn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        back_button = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_service_list = new javax.swing.JTable();

        service_list_dialog.setLocation(new java.awt.Point(400, 400));

        jPanel13.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel13.add(jPanel14, java.awt.BorderLayout.PAGE_START);

        delete_dichvu_dialog_btn.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        delete_dichvu_dialog_btn.setText("Hủy");
        delete_dichvu_dialog_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_dichvu_dialog_btnActionPerformed(evt);
            }
        });

        add_dich_dialog_btn.setBackground(new java.awt.Color(132, 70, 133));
        add_dich_dialog_btn.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        add_dich_dialog_btn.setForeground(new java.awt.Color(255, 255, 255));
        add_dich_dialog_btn.setText("Thêm");
        add_dich_dialog_btn.setPreferredSize(new java.awt.Dimension(72, 26));
        add_dich_dialog_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_dich_dialog_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(227, Short.MAX_VALUE)
                .addComponent(delete_dichvu_dialog_btn)
                .addGap(18, 18, 18)
                .addComponent(add_dich_dialog_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(delete_dichvu_dialog_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(add_dich_dialog_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel13.add(jPanel15, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 204, Short.MAX_VALUE)
        );

        jPanel13.add(jPanel16, java.awt.BorderLayout.LINE_START);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 204, Short.MAX_VALUE)
        );

        jPanel13.add(jPanel17, java.awt.BorderLayout.LINE_END);

        jLabel6.setBackground(new java.awt.Color(69, 96, 134));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(69, 96, 134));
        jLabel6.setText("Thêm dịch vụ");

        add_dongia_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_dongia_fieldActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(69, 96, 134));
        jLabel1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel1.setText("Tên dịch");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel3.setText("Đơn giá");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
            .addComponent(add_dichvu_field)
            .addComponent(add_dongia_field)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(7, 7, 7)
                .addComponent(add_dichvu_field, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(add_dongia_field, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel13.add(jPanel18, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout service_list_dialogLayout = new javax.swing.GroupLayout(service_list_dialog.getContentPane());
        service_list_dialog.getContentPane().setLayout(service_list_dialogLayout);
        service_list_dialogLayout.setHorizontalGroup(
            service_list_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        service_list_dialogLayout.setVerticalGroup(
            service_list_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setPreferredSize(new java.awt.Dimension(1180, 730));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(69, 96, 134));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("DANH SÁCH DỊCH VỤ");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 1170, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 543, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel7, java.awt.BorderLayout.LINE_START);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/service 1.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/smoke 1.png"))); // NOI18N

        jPanel3.setBackground(new java.awt.Color(238, 230, 226));

        search_field.setBackground(new java.awt.Color(238, 230, 226));
        search_field.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        search_field.setBorder(null);
        search_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_fieldActionPerformed(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Search_1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search_field, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(search_field)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
        );

        add_service_btn.setBackground(new java.awt.Color(99, 122, 48));
        add_service_btn.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        add_service_btn.setForeground(new java.awt.Color(255, 255, 255));
        add_service_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plus.png"))); // NOI18N
        add_service_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_service_btnActionPerformed(evt);
            }
        });

        delete_service_btn.setBackground(new java.awt.Color(132, 70, 133));
        delete_service_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Delete.png"))); // NOI18N
        delete_service_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_service_btnActionPerformed(evt);
            }
        });

        edit_service_btn.setBackground(new java.awt.Color(248, 189, 141));
        edit_service_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Edit.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 582, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(add_service_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(delete_service_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edit_service_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(add_service_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(delete_service_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edit_service_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel22)
                        .addComponent(jLabel2)))
                .addGap(6, 6, 6))
        );

        jPanel8.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        back_button.setBackground(new java.awt.Color(69, 96, 134));
        back_button.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        back_button.setForeground(new java.awt.Color(255, 255, 255));
        back_button.setText("Quay Lại");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(back_button, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 970, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(back_button, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 60, Short.MAX_VALUE))
        );

        jPanel8.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 329, Short.MAX_VALUE)
        );

        jPanel8.add(jPanel4, java.awt.BorderLayout.LINE_END);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        table_service_list.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Ten dich vu", "Don gia", "Tuy chon"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_service_list.setRowHeight(30);
        table_service_list.setShowGrid(true);
        jScrollPane1.setViewportView(table_service_list);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel8.add(jPanel9, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel8, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void delete_dichvu_dialog_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_dichvu_dialog_btnActionPerformed
        // TODO add your handling code here:
        service_list_dialog.setVisible(false);
    }//GEN-LAST:event_delete_dichvu_dialog_btnActionPerformed

    private void add_dich_dialog_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_dich_dialog_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add_dich_dialog_btnActionPerformed

    private void add_dongia_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_dongia_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add_dongia_fieldActionPerformed

    private void search_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search_fieldActionPerformed

    private void add_service_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_service_btnActionPerformed
        // TODO add your handling code here:
        service_list_dialog.setSize(400,400);
        service_list_dialog.setVisible(true);
    }//GEN-LAST:event_add_service_btnActionPerformed

    private void delete_service_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_service_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_delete_service_btnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_dich_dialog_btn;
    private javax.swing.JTextField add_dichvu_field;
    private javax.swing.JTextField add_dongia_field;
    private javax.swing.JButton add_service_btn;
    private javax.swing.JButton back_button;
    private javax.swing.JButton delete_dichvu_dialog_btn;
    private javax.swing.JButton delete_service_btn;
    private javax.swing.JButton edit_service_btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField search_field;
    private javax.swing.JDialog service_list_dialog;
    private javax.swing.JTable table_service_list;
    // End of variables declaration//GEN-END:variables
}
