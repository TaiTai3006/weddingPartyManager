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
public class DishList extends javax.swing.JInternalFrame {
    class MonAn{
        String ma;
        String Ten;
        String DonGia;
        String LoaiMon;
        String TuyChon;

        public MonAn(String ma, String Ten, String DonGia, String LoaiMon, String TuyChon) {
            this.ma = ma;
            this.Ten = Ten;
            this.DonGia = DonGia;
            this.LoaiMon = LoaiMon;
            this.TuyChon = TuyChon;
        }
        
        public String getMa() {
            return ma;
        }

        public String getTen() {
            return Ten;
        }

        public String getDonGia() {
            return DonGia;
        }

        public String getLoaiMon() {
            return LoaiMon;
        }

        public String getTuyChon() {
            return TuyChon;
        }
        
        
    }

    /**
     * Creates new form DishList
     */
    public DishList() {
        initComponents();
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        ArrayList<MonAn> items = new ArrayList<>();

        items.add(new MonAn("ma01","goi cuon", "mon chinh", "20000", ""));
        items.add(new MonAn("ma02","banh bao", "mon chinh", "20000", ""));
        items.add(new MonAn("ma03","nho", "trang mieng", "20000", ""));
        items.add(new MonAn("ma04","sup cua", "Khai vi", "20000", ""));
        items.add(new MonAn("ma05","ga nau nam", "mon chinh", "20000", ""));
        items.add(new MonAn("ma06","heo quay", "mon chinh", "50000", ""));
        ArrayList<MonAn> filteredItems = new ArrayList<>(items);
        
        DefaultTableModel defaulttable = new DefaultTableModel();
        table_dish_lish.setModel(defaulttable);
        defaulttable.addColumn("STT");
        defaulttable.addColumn("Ten mon an");
        defaulttable.addColumn("Loai mon an");
        defaulttable.addColumn("Don gia");
        defaulttable.addColumn("Tuy chon");
        int count = 1;
        for(MonAn row : items){
            defaulttable.addRow(new Object[]{count++,row.getMa(),row.getTen(),row.getLoaiMon(),row.getDonGia(),row.getTuyChon()});
        }
            search_field.getDocument().addDocumentListener(new DocumentListener() {
            int count1 = 1;
            @Override
            public void insertUpdate(DocumentEvent e) {
                String filterText = search_field.getText().toLowerCase();
                filteredItems.clear();
                for (MonAn item : items) {
                    if (item.getMa().toLowerCase().contains(filterText) || item.getTen().toLowerCase().contains(filterText)) {
                        filteredItems.add(item);
                    }
                }
                DefaultTableModel model = (DefaultTableModel) table_dish_lish.getModel();
                model.setRowCount(0);
                for (MonAn row : filteredItems) {
                    model.addRow(new Object[]{count1++,row.getMa(),row.getTen(),row.getLoaiMon(),row.getDonGia(),row.getTuyChon()});
                } 
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String filterText = search_field.getText().toLowerCase();
                filteredItems.clear();
                for (MonAn item : items) {
                    if (item.getMa().toLowerCase().contains(filterText) || item.getTen().toLowerCase().contains(filterText)) {
                        filteredItems.add(item);
                    }
                }
                DefaultTableModel model = (DefaultTableModel) table_dish_lish.getModel();
                model.setRowCount(0);
                for (MonAn row : filteredItems) {
                    model.addRow(new Object[]{count1++,row.getMa(),row.getTen(),row.getLoaiMon(),row.getDonGia(),row.getTuyChon()});
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

        dish_list_dialog = new javax.swing.JDialog();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        delete_dialog_btn = new javax.swing.JButton();
        add_dialog_btn = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        add_monan_field = new javax.swing.JTextField();
        add_loaimonan_field = new javax.swing.JTextField();
        dongia_monan_field = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        add_btn = new javax.swing.JButton();
        edit_dish_btn = new javax.swing.JButton();
        delete_dish_btn = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        search_field = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        back_button = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_dish_lish = new javax.swing.JTable();

        dish_list_dialog.setLocation(new java.awt.Point(300, 300));

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

        delete_dialog_btn.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        delete_dialog_btn.setText("Hủy");
        delete_dialog_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_dialog_btnActionPerformed(evt);
            }
        });

        add_dialog_btn.setBackground(new java.awt.Color(132, 70, 133));
        add_dialog_btn.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        add_dialog_btn.setForeground(new java.awt.Color(255, 255, 255));
        add_dialog_btn.setText("Thêm");
        add_dialog_btn.setPreferredSize(new java.awt.Dimension(72, 26));
        add_dialog_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_dialog_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(227, Short.MAX_VALUE)
                .addComponent(delete_dialog_btn)
                .addGap(18, 18, 18)
                .addComponent(add_dialog_btn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(delete_dialog_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(add_dialog_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addGap(0, 358, Short.MAX_VALUE)
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
            .addGap(0, 358, Short.MAX_VALUE)
        );

        jPanel13.add(jPanel17, java.awt.BorderLayout.LINE_END);

        jLabel6.setBackground(new java.awt.Color(69, 96, 134));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(69, 96, 134));
        jLabel6.setText("Thêm món ăn");

        add_loaimonan_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_loaimonan_fieldActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(69, 96, 134));
        jLabel1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel1.setText("Tên món ăn");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel2.setText("Loại món ăn");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel3.setText("Đơn giá");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
            .addComponent(add_monan_field)
            .addComponent(add_loaimonan_field)
            .addComponent(dongia_monan_field)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(7, 7, 7)
                .addComponent(add_monan_field, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(add_loaimonan_field, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dongia_monan_field, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        jPanel13.add(jPanel18, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout dish_list_dialogLayout = new javax.swing.GroupLayout(dish_list_dialog.getContentPane());
        dish_list_dialog.getContentPane().setLayout(dish_list_dialogLayout);
        dish_list_dialogLayout.setHorizontalGroup(
            dish_list_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dish_list_dialogLayout.setVerticalGroup(
            dish_list_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setPreferredSize(new java.awt.Dimension(1180, 730));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(69, 96, 134));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("DANH SÁCH MÓN ĂN");

        add_btn.setBackground(new java.awt.Color(99, 122, 48));
        add_btn.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        add_btn.setForeground(new java.awt.Color(255, 255, 255));
        add_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plus.png"))); // NOI18N
        add_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_btnActionPerformed(evt);
            }
        });

        edit_dish_btn.setBackground(new java.awt.Color(248, 189, 141));
        edit_dish_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Edit.png"))); // NOI18N

        delete_dish_btn.setBackground(new java.awt.Color(132, 70, 133));
        delete_dish_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Delete.png"))); // NOI18N
        delete_dish_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_dish_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(100, 853, Short.MAX_VALUE)
                .addComponent(add_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delete_dish_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(edit_dish_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(add_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edit_dish_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delete_dish_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel5, java.awt.BorderLayout.LINE_START);

        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(238, 230, 226));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Search_1.png"))); // NOI18N

        search_field.setBackground(new java.awt.Color(238, 230, 226));
        search_field.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        search_field.setBorder(null);
        search_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_fieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search_field, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(search_field, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/MenuFood 1 (1).png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 591, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 341, Short.MAX_VALUE)
        );

        jPanel7.add(jPanel6, java.awt.BorderLayout.LINE_END);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        back_button.setBackground(new java.awt.Color(69, 96, 134));
        back_button.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        back_button.setForeground(new java.awt.Color(255, 255, 255));
        back_button.setText("Quay Lại");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(back_button, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 958, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(back_button, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 65, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel8, java.awt.BorderLayout.PAGE_END);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        table_dish_lish.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        table_dish_lish.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Ten mon an", "Loai mon an", "Don gia", "Tuy chinh"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_dish_lish.setRowHeight(30);
        table_dish_lish.setShowGrid(true);
        jScrollPane2.setViewportView(table_dish_lish);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 958, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel9, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel7, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void add_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_btnActionPerformed
        // TODO add your handling code here:
        dish_list_dialog.setSize(400,400);
        dish_list_dialog.setVisible(true);
    }//GEN-LAST:event_add_btnActionPerformed

    private void search_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search_fieldActionPerformed

    private void add_dialog_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_dialog_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add_dialog_btnActionPerformed

    private void add_loaimonan_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_loaimonan_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add_loaimonan_fieldActionPerformed

    private void delete_dialog_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_dialog_btnActionPerformed
        // TODO add your handling code here:
        dish_list_dialog.setVisible(false);
    }//GEN-LAST:event_delete_dialog_btnActionPerformed

    private void delete_dish_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_dish_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_delete_dish_btnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_btn;
    private javax.swing.JButton add_dialog_btn;
    private javax.swing.JTextField add_loaimonan_field;
    private javax.swing.JTextField add_monan_field;
    private javax.swing.JButton back_button;
    private javax.swing.JButton delete_dialog_btn;
    private javax.swing.JButton delete_dish_btn;
    private javax.swing.JDialog dish_list_dialog;
    private javax.swing.JTextField dongia_monan_field;
    private javax.swing.JButton edit_dish_btn;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField search_field;
    private javax.swing.JTable table_dish_lish;
    // End of variables declaration//GEN-END:variables
}
