/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import dao.LoaiMonAnDAO;
import dao.MonAnDAO;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_OPTION;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import model.LoaiMonAn;
import model.MonAn;

/**
 *
 * @author macbookpro
 */

public class DishList extends javax.swing.JInternalFrame {
    
int count = 0;
int macount = 0;
DefaultTableModel defaulttable = new DefaultTableModel();
ArrayList<MonAn> items = new ArrayList<>(MonAnDAO.getInstance().SelectAll());
    public void Message(String message, int messageType) {
        JOptionPane jOptionPane = new JOptionPane(message, messageType);
        JDialog dialog = jOptionPane.createDialog(null, "Message");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
public int findMissingNumber(int[] numbers) {
        Arrays.sort(numbers);
        
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i + 1] - numbers[i] > 1) {
                return numbers[i] + 1;
            }
        }
        
        // Nếu không tìm thấy số bị thiếu giữa các số đã cho
        return -1;
    }
public int lastChars(String maMonAn){
    int length = maMonAn.length();
    String lastchar = maMonAn.substring(length - 4);
    return Integer.parseInt(lastchar);
}
    public void CreateDataTable() {
        defaulttable = (DefaultTableModel) table_dish_list.getModel();
        int i = 0;
        for (MonAn x : items) {
            defaulttable.addRow(new Object[]{++i, x.getMaMonAn(), x.getTenMonAn(),x.getMaLoaiMonAn(), x.getDonGia()});
        }
    }
    public void ReloadDataTable() {
        items = MonAnDAO.getInstance().SelectAll();
        defaulttable.setRowCount(0);
        CreateDataTable();
    }
public void notification(int check){
    if(check == 1){
        int ret = JOptionPane.showConfirmDialog(null, "Thêm dữ liệu thành công","NOTIFICATION", JOptionPane.CLOSED_OPTION);
        dish_list_dialog.setVisible(false);
        add_monan_field.setText("");
        add_loaimonan_field.setText("");
        dongia_monan_field.setText("");
//        if(ret == JOptionPane.YES_OPTION){
//            dish_list_dialog.setVisible(false);
//        }else{
//            dish_list_dialog.setVisible(true);
//        }
    }else{
        JOptionPane.showConfirmDialog(null, "Thêm dữ liệu thất bại","NOTIFICATION", JOptionPane.CLOSED_OPTION);
    }
}
    /**
     * Creates new form DishList
     */
    public DishList() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        ArrayList<MonAn> filteredItems = new ArrayList<>();

        table_dish_list.setModel(defaulttable);
        defaulttable.addColumn("STT");
        defaulttable.addColumn("Mã món ăn");
        defaulttable.addColumn("Tên món ăn");
        defaulttable.addColumn("Loại món ăn");
        defaulttable.addColumn("Đơn giá");

        for(MonAn row : items){
            defaulttable.addRow(new Object[]{defaulttable.getRowCount() + 1, row.getMaMonAn(), row.getTenMonAn(), row.getMaLoaiMonAn(), row.getDonGia()});
        }

            search_field.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                String filterText = search_field.getText().toLowerCase();
                filteredItems.clear();
                ArrayList<MonAn> listMonAn = new ArrayList<>(MonAnDAO.getInstance().SelectAll());
                for (MonAn item : listMonAn) {
                    if (item.getMaMonAn().toLowerCase().contains(filterText) || item.getTenMonAn().toLowerCase().contains(filterText)) {
                        filteredItems.add(item);
                    }
                }
                DefaultTableModel model = (DefaultTableModel) table_dish_list.getModel();
                model.setRowCount(0);
                for (MonAn row : filteredItems) {
                count = defaulttable.getRowCount() + 1;
                    model.addRow(new Object[]{count, row.getMaMonAn(), row.getTenMonAn(), row.getMaLoaiMonAn(), row.getDonGia()});
                } 
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String filterText = search_field.getText().toLowerCase();
                filteredItems.clear();
                ArrayList<MonAn> listMonAn = new ArrayList<>(MonAnDAO.getInstance().SelectAll());
                for (MonAn item : listMonAn) {
                    if (item.getMaMonAn().toLowerCase().contains(filterText) || item.getTenMonAn().toLowerCase().contains(filterText)) {
                        filteredItems.add(item);
                    }
                }
                DefaultTableModel model = (DefaultTableModel) table_dish_list.getModel();
                model.setRowCount(0);
                for (MonAn row : filteredItems) {
                count = defaulttable.getRowCount() + 1;
                    model.addRow(new Object[]{count, row.getMaMonAn(), row.getTenMonAn(), row.getMaLoaiMonAn(), row.getDonGia()});
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
        dish_listUpdate_dialog = new javax.swing.JDialog();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        deleteUpdate_dialog_btn = new javax.swing.JButton();
        update_dialog_btn = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        Update_monan_field = new javax.swing.JTextField();
        Update_loaimonan_field = new javax.swing.JTextField();
        Update_dongia_field = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        add_dish_btn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_dish_list = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        delete_dish_btn = new javax.swing.JButton();
        edit_dish_btn = new javax.swing.JButton();
        jPanel35 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        search_field = new javax.swing.JTextField();
        BackPage3 = new javax.swing.JButton();

        dish_list_dialog.setBackground(new java.awt.Color(255, 255, 255));
        dish_list_dialog.setLocation(new java.awt.Point(300, 300));
        dish_list_dialog.setMinimumSize(new java.awt.Dimension(400, 400));

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

        dongia_monan_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dongia_monan_fieldActionPerformed(evt);
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

        dish_listUpdate_dialog.setBackground(new java.awt.Color(255, 255, 255));
        dish_listUpdate_dialog.setLocation(new java.awt.Point(300, 300));
        dish_listUpdate_dialog.setMinimumSize(new java.awt.Dimension(400, 400));

        jPanel19.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel19.add(jPanel20, java.awt.BorderLayout.PAGE_START);

        deleteUpdate_dialog_btn.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        deleteUpdate_dialog_btn.setText("Hủy");
        deleteUpdate_dialog_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteUpdate_dialog_btnActionPerformed(evt);
            }
        });

        update_dialog_btn.setBackground(new java.awt.Color(132, 70, 133));
        update_dialog_btn.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        update_dialog_btn.setForeground(new java.awt.Color(255, 255, 255));
        update_dialog_btn.setText("Cập nhật");
        update_dialog_btn.setPreferredSize(new java.awt.Dimension(72, 26));
        update_dialog_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_dialog_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(191, Short.MAX_VALUE)
                .addComponent(deleteUpdate_dialog_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(update_dialog_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteUpdate_dialog_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(update_dialog_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel19.add(jPanel21, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 358, Short.MAX_VALUE)
        );

        jPanel19.add(jPanel22, java.awt.BorderLayout.LINE_START);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 358, Short.MAX_VALUE)
        );

        jPanel19.add(jPanel23, java.awt.BorderLayout.LINE_END);

        jLabel7.setBackground(new java.awt.Color(69, 96, 134));
        jLabel7.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(69, 96, 134));
        jLabel7.setText("Cập nhật món ăn");

        Update_monan_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Update_monan_fieldActionPerformed(evt);
            }
        });

        Update_loaimonan_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Update_loaimonan_fieldActionPerformed(evt);
            }
        });

        Update_dongia_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Update_dongia_fieldActionPerformed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(69, 96, 134));
        jLabel5.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel5.setText("Tên món ăn");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel8.setText("Loại món ăn");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel9.setText("Đơn giá");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
            .addComponent(Update_monan_field)
            .addComponent(Update_loaimonan_field)
            .addComponent(Update_dongia_field)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(7, 7, 7)
                .addComponent(Update_monan_field, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Update_loaimonan_field, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Update_dongia_field, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        jPanel19.add(jPanel24, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout dish_listUpdate_dialogLayout = new javax.swing.GroupLayout(dish_listUpdate_dialog.getContentPane());
        dish_listUpdate_dialog.getContentPane().setLayout(dish_listUpdate_dialogLayout);
        dish_listUpdate_dialogLayout.setHorizontalGroup(
            dish_listUpdate_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        dish_listUpdate_dialogLayout.setVerticalGroup(
            dish_listUpdate_dialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setPreferredSize(new java.awt.Dimension(1180, 730));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1170, 730));

        add_dish_btn.setBackground(new java.awt.Color(99, 122, 48));
        add_dish_btn.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        add_dish_btn.setForeground(new java.awt.Color(255, 255, 255));
        add_dish_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plus.png"))); // NOI18N
        add_dish_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_dish_btnActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/MenuFood 1 (1).png"))); // NOI18N

        table_dish_list.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        table_dish_list.setModel(new javax.swing.table.DefaultTableModel(
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
                "STT", "Mã món ăn", "Tên món ăn", "Loại món ăn", "Đơn giá"
            }
        ));
        table_dish_list.setFocusable(false);
        table_dish_list.setRowHeight(25);
        table_dish_list.setSelectionBackground(new java.awt.Color(69, 96, 134));
        table_dish_list.setShowGrid(false);
        jScrollPane1.setViewportView(table_dish_list);
        if (table_dish_list.getColumnModel().getColumnCount() > 0) {
            table_dish_list.getColumnModel().getColumn(0).setMinWidth(100);
            table_dish_list.getColumnModel().getColumn(0).setPreferredWidth(100);
            table_dish_list.getColumnModel().getColumn(0).setMaxWidth(20);
            table_dish_list.getColumnModel().getColumn(1).setMinWidth(100);
            table_dish_list.getColumnModel().getColumn(1).setPreferredWidth(100);
            table_dish_list.getColumnModel().getColumn(1).setMaxWidth(20);
        }
        table_dish_list.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table_dish_list.getTableHeader().setOpaque(false);
        table_dish_list.getTableHeader().setBackground(new Color(243,246,249));
        table_dish_list.setDefaultEditor(Object.class, null);

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
        jLabel13.setText("DANH SÁCH MÓN ĂN");
        jPanel5.add(jLabel13);

        delete_dish_btn.setBackground(new java.awt.Color(132, 70, 133));
        delete_dish_btn.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        delete_dish_btn.setForeground(new java.awt.Color(255, 255, 255));
        delete_dish_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Delete.png"))); // NOI18N
        delete_dish_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_dish_btnActionPerformed(evt);
            }
        });

        edit_dish_btn.setBackground(new java.awt.Color(248, 189, 141));
        edit_dish_btn.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        edit_dish_btn.setForeground(new java.awt.Color(255, 255, 255));
        edit_dish_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Edit.png"))); // NOI18N
        edit_dish_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_dish_btnActionPerformed(evt);
            }
        });

        jPanel35.setBackground(new java.awt.Color(238, 230, 226));
        jPanel35.setPreferredSize(new java.awt.Dimension(260, 50));

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Group 9.png"))); // NOI18N

        search_field.setBackground(new java.awt.Color(238, 230, 226));
        search_field.setBorder(null);
        search_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_fieldActionPerformed(evt);
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
                .addComponent(search_field, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
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
                .addComponent(search_field)
                .addContainerGap())
        );

        BackPage3.setBackground(new java.awt.Color(69, 96, 134));
        BackPage3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        BackPage3.setForeground(new java.awt.Color(255, 255, 255));
        BackPage3.setText("Quay lại");
        BackPage3.setPreferredSize(new java.awt.Dimension(90, 40));

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
                        .addComponent(BackPage3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 970, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(add_dish_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(delete_dish_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(edit_dish_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
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
                    .addComponent(delete_dish_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_dish_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edit_dish_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BackPage3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1168, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void add_dialog_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_dialog_btnActionPerformed
        // TODO add your handling code here:
       int check = 0;
       macount = defaulttable.getRowCount() + 1;
       int i = 0;
       int[] numbers = new int[macount];

       // Retrieve the list of MonAn objects from the database
       ArrayList<MonAn> listMonAn = new ArrayList<>(MonAnDAO.getInstance().SelectAll());

       // Extract the last characters from the MaMonAn field for each MonAn object
       for(MonAn row : listMonAn){
           numbers[i] = lastChars(row.getMaMonAn());;
           i++;
       }
       String mamonan = "";

       // Find the missing number in the sequence
       int missingNumber = findMissingNumber(numbers);

       // Generate the MaMonAn based on the missing number or the current count
       if (missingNumber != -1){
           // Use the missing number
           if(missingNumber < 10){
                mamonan = "MA000" + String.valueOf(missingNumber);
           }else if(missingNumber < 100){
                mamonan = "MA00" + String.valueOf(missingNumber);
           }else if(missingNumber < 1000){
                mamonan = "MA0" + String.valueOf(missingNumber);
           }else if(missingNumber < 10000){
                mamonan = "MA" + String.valueOf(missingNumber);
           }
       }else{
           // Use the current count
           if(macount < 10){
                mamonan = "MA000" + String.valueOf(macount);
           }else if(macount < 100){
                mamonan = "MA00" + String.valueOf(macount);
           }else if(macount < 1000){
                mamonan = "MA0" + String.valueOf(macount);
           }else if(macount < 10000){
                mamonan = "MA" + String.valueOf(macount);
           }
       }

       // Get the input values for the new MonAn object
       String tenmonan = add_monan_field.getText();
       String loaimonan = add_loaimonan_field.getText();
       long dongia = 0;
        try {
            dongia = Long.parseLong(dongia_monan_field.getText());
            check = 1;
        } catch (NumberFormatException numberFormatException) {
            check = 0;
        }
       if(check == 1){
            // Create a new MonAn object with the generated MaMonAn and input values
            MonAn monan = new MonAn(mamonan, tenmonan, dongia, loaimonan);
            // Insert the new MonAn into the database
            MonAnDAO.getInstance().Insert(monan);
            // Add a new row to the default table model with the values of the new MonAn
            if(missingNumber != -1){
                defaulttable.addRow(new Object[]{missingNumber, mamonan, tenmonan, loaimonan, dongia});
            }else{
                defaulttable.addRow(new Object[]{macount, mamonan, tenmonan, loaimonan, dongia});
            }
        }else{
            JOptionPane.showConfirmDialog(null, "Đơn giá không được chứa kí tự","WARNING", JOptionPane.CLOSED_OPTION);
       }




       // Update the table model of the JTable

       ReloadDataTable();
       // Close the dialog and reset the input fields
       notification(check);

    }//GEN-LAST:event_add_dialog_btnActionPerformed

    private void add_loaimonan_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_loaimonan_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add_loaimonan_fieldActionPerformed

    private void delete_dialog_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_dialog_btnActionPerformed
        // TODO add your handling code here:

        int ret = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát", "WARNING", JOptionPane.YES_NO_OPTION);
        if(ret == JOptionPane.YES_OPTION){
            dish_list_dialog.setVisible(false);
        }else{
            dish_list_dialog.setVisible(true);
        }
        
    }//GEN-LAST:event_delete_dialog_btnActionPerformed

    private void add_dish_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_dish_btnActionPerformed
        // TODO add your handling code here:
        dish_list_dialog.setLocationRelativeTo(null);
        dish_list_dialog.setVisible(true);
    }//GEN-LAST:event_add_dish_btnActionPerformed

    private void delete_dish_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_dish_btnActionPerformed
        // TODO add your handling code here:
        int selectedRow = table_dish_list.getSelectedRow();
        int rowCount = selectedRow+1;
        if (selectedRow == -1){
            JOptionPane.showConfirmDialog(null, "Hãy chọn hàng trước", "WARNING", JOptionPane.CLOSED_OPTION);
        }else{
            int ret = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa dữ liệu dòng "+rowCount+"", "NOTIFICATION", JOptionPane.YES_NO_OPTION);
            if(ret == YES_OPTION){
        //        int modelIndex = table_dish_list.convertRowIndexToModel(selectedRow);
                Object maValue = defaulttable.getValueAt(selectedRow, 1);
                MonAn monan = new MonAn();
                monan.setMaMonAn(String.valueOf(maValue));
                MonAn x = MonAnDAO.getInstance().SelectById(monan);

                MonAnDAO.getInstance().Delete(x);

                ReloadDataTable();
                JOptionPane.showConfirmDialog(null, "Xóa dữ liệu thành công", "NOTIFICATION", JOptionPane.CLOSED_OPTION);

            }else{
                
            }
        }

    }//GEN-LAST:event_delete_dish_btnActionPerformed

    private void edit_dish_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_dish_btnActionPerformed
        // TODO add your handling code here:
        dish_listUpdate_dialog.setLocationRelativeTo(null);
        dish_listUpdate_dialog.setVisible(true);
        int selectedRow = table_dish_list.getSelectedRow();
        System.out.println(selectedRow + 1);
        Object tenValue = defaulttable.getValueAt(selectedRow,2);
        Object giaValue = defaulttable.getValueAt(selectedRow,4);
        Object loaiValue = defaulttable.getValueAt(selectedRow,3);
        Update_monan_field.setText(String.valueOf(tenValue));
        Update_loaimonan_field.setText(String.valueOf(loaiValue));
        Update_dongia_field.setText(String.valueOf(giaValue));
    }//GEN-LAST:event_edit_dish_btnActionPerformed

    private void search_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_search_fieldActionPerformed

    private void dongia_monan_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dongia_monan_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dongia_monan_fieldActionPerformed

    private void deleteUpdate_dialog_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUpdate_dialog_btnActionPerformed
        // TODO add your handling code here:
        dish_listUpdate_dialog.setVisible(false);
    }//GEN-LAST:event_deleteUpdate_dialog_btnActionPerformed

    private void update_dialog_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_dialog_btnActionPerformed
        // TODO add your handling code here:
        int selectedRow = table_dish_list.getSelectedRow();
        int rowCount = selectedRow + 1;
        Object maValue = defaulttable.getValueAt(selectedRow,1);
        MonAn monan = new MonAn();
        monan.setMaMonAn(String.valueOf(maValue));
        String tenmonan = Update_monan_field.getText();
        String loaimonan = Update_loaimonan_field.getText();
        long dongia = Long.parseLong(Update_dongia_field.getText());
        if(!tenmonan.equals(String.valueOf(table_dish_list.getValueAt(selectedRow, 2))) 
                || !loaimonan.equals(String.valueOf(table_dish_list.getValueAt(selectedRow, 3)))
                || !Update_dongia_field.getText().equals(String.valueOf(table_dish_list.getValueAt(selectedRow, 4)))){
                    MonAn monAnCapNhat = new MonAn(String.valueOf(maValue),tenmonan,dongia,loaimonan);
                    MonAnDAO.getInstance().Update(monAnCapNhat);
        
        dish_listUpdate_dialog.setVisible(false);
        ReloadDataTable();
        Message("Cập nhật dữ liệu dòng "+rowCount+" thành công",-1);
        }else{
            Message("Cập nhật dữ liệu dòng "+rowCount+" thất bại",-1);
            dish_listUpdate_dialog.setVisible(false);
        }

//        int row = table_dish_list.getSelectedRow();
//        String maLoaiSanh = String.valueOf(table_dish_list.getValueAt(row, 1));
//        int kq = 0;
//        if (!tenmonan.equals(String.valueOf(table_dish_list.getValueAt(row, 2)))
//                || !loaimonan.equals(String.valueOf(table_dish_list.getValueAt(row, 3))) ) {
//            kq = MonAnDAO.getInstance().Update(new MonAn(maLoaiSanh, tenmonan, dongia,loaimonan));
//        }
//        if (kq > 0) {
//            dish_listUpdate_dialog.setVisible(false);
//            ReloadDataTable();
//            Update_monan_field.setText("");
//            Update_loaimonan_field.setText("");
//        } else {
//            dish_listUpdate_dialog.setVisible(false);
//            Message("Chỉnh sửa dữ liệu thất bại!", JOptionPane.ERROR_MESSAGE);
//
//        }
    }//GEN-LAST:event_update_dialog_btnActionPerformed

    private void Update_loaimonan_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Update_loaimonan_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Update_loaimonan_fieldActionPerformed

    private void Update_dongia_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Update_dongia_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Update_dongia_fieldActionPerformed

    private void Update_monan_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Update_monan_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Update_monan_fieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackPage3;
    private javax.swing.JTextField Update_dongia_field;
    private javax.swing.JTextField Update_loaimonan_field;
    private javax.swing.JTextField Update_monan_field;
    private javax.swing.JButton add_dialog_btn;
    private javax.swing.JButton add_dish_btn;
    private javax.swing.JTextField add_loaimonan_field;
    private javax.swing.JTextField add_monan_field;
    private javax.swing.JButton deleteUpdate_dialog_btn;
    private javax.swing.JButton delete_dialog_btn;
    private javax.swing.JButton delete_dish_btn;
    private javax.swing.JDialog dish_listUpdate_dialog;
    private javax.swing.JDialog dish_list_dialog;
    private javax.swing.JTextField dongia_monan_field;
    private javax.swing.JButton edit_dish_btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
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
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField search_field;
    private javax.swing.JTable table_dish_list;
    private javax.swing.JButton update_dialog_btn;
    // End of variables declaration//GEN-END:variables
}
