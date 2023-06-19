/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import dao.DichVuDAO;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import model.DichVu;

/**
 *
 * @author macbookpro
 */
public class ServiceList extends javax.swing.JInternalFrame {
    private DefaultTableModel defaultTableModelServiceList;
    private ArrayList<DichVu> dichVus = DichVuDAO.getInstance().SelectAll();
    private NumberFormat currencyFormatVN = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

    /**
     * Creates new form ServiceList
     */
    public ServiceList(){
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        CreateTable();
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.RIGHT); // Set the desired horizontal alignment

        // Set the custom cell renderer to the desired column
        table_service_list.getColumnModel().getColumn(3).setCellRenderer(renderer); // Column index 
    }
    
    public void CreateTable()
    {
        defaultTableModelServiceList = (DefaultTableModel)table_service_list.getModel();
        int i = 0;
        for(DichVu x: dichVus){
            defaultTableModelServiceList.addRow(new Object[]{++i, x.getMaDichVu(), x.getTenDichVu(),currencyFormatVN.format(x.getDonGia())});
        }
    }
    public void SearchTable(){
        defaultTableModelServiceList.setRowCount(0);
        defaultTableModelServiceList = (DefaultTableModel)table_service_list.getModel();
        int i = 0;
        String value = search_service_field.getText();
        for(DichVu x: dichVus){
            System.out.println();
            if(x.getMaDichVu().toLowerCase().contains(value.toLowerCase()) || x.getTenDichVu().toLowerCase().contains(value.toLowerCase()))
            defaultTableModelServiceList.addRow(new Object[]{++i, x.getMaDichVu(), x.getTenDichVu(),currencyFormatVN.format(x.getDonGia())});
        }
    }
     
    public void ReloadDataTable(){
        dichVus = DichVuDAO.getInstance().SelectAll();
        defaultTableModelServiceList.setRowCount(0);
        CreateTable();
    }
    
    public void Message(String message, int messageType) {
        JOptionPane jOptionPane = new JOptionPane(message, messageType);
        JDialog dialog = jOptionPane.createDialog(null, "Thông báo");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

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
        jPanel2 = new javax.swing.JPanel();
        add_dichvu_field = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        add_dongia_field = new javax.swing.JTextField();
        service_list_dialog_update = new javax.swing.JDialog();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        delete_dichvu_dialog_btn1 = new javax.swing.JButton();
        add_dich_dialog_btn1 = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        update_dichvu_field1 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        update_dongia_field1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        BackPage3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_service_list = new javax.swing.JTable();
        jPanel30 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        search_service_field = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        add_service_btn = new javax.swing.JButton();
        delete_service_btn = new javax.swing.JButton();
        edit_service_btn = new javax.swing.JButton();
        jPanel32 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        service_list_dialog.setLocation(new java.awt.Point(400, 400));

        jPanel13.setLayout(new java.awt.BorderLayout());

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

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

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

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
                .addGap(17, 17, 17)
                .addComponent(delete_dichvu_dialog_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 210, Short.MAX_VALUE)
                .addComponent(add_dich_dialog_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(add_dich_dialog_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delete_dichvu_dialog_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel13.add(jPanel15, java.awt.BorderLayout.PAGE_END);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 239, Short.MAX_VALUE)
        );

        jPanel13.add(jPanel16, java.awt.BorderLayout.LINE_START);

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 239, Short.MAX_VALUE)
        );

        jPanel13.add(jPanel17, java.awt.BorderLayout.LINE_END);

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setBackground(new java.awt.Color(69, 96, 134));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(69, 96, 134));
        jLabel6.setText("Thêm dịch vụ");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        add_dichvu_field.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel1.setBackground(new java.awt.Color(69, 96, 134));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel1.setText("Tên dịch vụ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(add_dichvu_field, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(7, 7, 7)
                .addComponent(add_dichvu_field, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel3.setText("Đơn giá");

        add_dongia_field.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        add_dongia_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_dongia_fieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(add_dongia_field, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(add_dongia_field, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
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

        service_list_dialog_update.setLocation(new java.awt.Point(400, 400));

        jPanel19.setLayout(new java.awt.BorderLayout());

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

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

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));

        delete_dichvu_dialog_btn1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        delete_dichvu_dialog_btn1.setText("Hủy");
        delete_dichvu_dialog_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_dichvu_dialog_btn1ActionPerformed(evt);
            }
        });

        add_dich_dialog_btn1.setBackground(new java.awt.Color(132, 70, 133));
        add_dich_dialog_btn1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        add_dich_dialog_btn1.setForeground(new java.awt.Color(255, 255, 255));
        add_dich_dialog_btn1.setText("Cập nhật");
        add_dich_dialog_btn1.setPreferredSize(new java.awt.Dimension(72, 26));
        add_dich_dialog_btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_dich_dialog_btn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(delete_dichvu_dialog_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 187, Short.MAX_VALUE)
                .addComponent(add_dich_dialog_btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(add_dich_dialog_btn1, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(delete_dichvu_dialog_btn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel19.add(jPanel21, java.awt.BorderLayout.PAGE_END);

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 222, Short.MAX_VALUE)
        );

        jPanel19.add(jPanel22, java.awt.BorderLayout.LINE_START);

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 222, Short.MAX_VALUE)
        );

        jPanel19.add(jPanel23, java.awt.BorderLayout.LINE_END);

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setBackground(new java.awt.Color(69, 96, 134));
        jLabel7.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(69, 96, 134));
        jLabel7.setText("Cập nhật dịch vụ");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setBackground(new java.awt.Color(69, 96, 134));
        jLabel4.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel4.setText("Tên dịch vụ");

        update_dichvu_field1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        update_dongia_field1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        update_dongia_field1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_dongia_field1ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel5.setText("Đơn giá");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(update_dongia_field1, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(update_dongia_field1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(update_dichvu_field1, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(7, 7, 7)
                .addComponent(update_dichvu_field1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel19.add(jPanel24, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout service_list_dialog_updateLayout = new javax.swing.GroupLayout(service_list_dialog_update.getContentPane());
        service_list_dialog_update.getContentPane().setLayout(service_list_dialog_updateLayout);
        service_list_dialog_updateLayout.setHorizontalGroup(
            service_list_dialog_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        service_list_dialog_updateLayout.setVerticalGroup(
            service_list_dialog_updateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
        );

        setPreferredSize(new java.awt.Dimension(1170, 730));

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));
        jPanel34.setPreferredSize(new java.awt.Dimension(1170, 730));

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

        table_service_list.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        table_service_list.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã dịch vụ", "Tên dịch vụ", "Đơn giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_service_list.setFocusable(false);
        table_service_list.setRowHeight(25);
        table_service_list.setSelectionBackground(new java.awt.Color(69, 96, 134));
        table_service_list.setShowGrid(false);
        jScrollPane1.setViewportView(table_service_list);
        if (table_service_list.getColumnModel().getColumnCount() > 0) {
            table_service_list.getColumnModel().getColumn(0).setMinWidth(100);
            table_service_list.getColumnModel().getColumn(0).setPreferredWidth(100);
            table_service_list.getColumnModel().getColumn(0).setMaxWidth(20);
        }
        table_service_list.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table_service_list.getTableHeader().setOpaque(false);
        table_service_list.getTableHeader().setBackground(new Color(243,246,249));
        table_service_list.setDefaultEditor(Object.class, null);

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(69, 96, 134));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("DANH SÁCH DỊCH VỤ");
        jLabel15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 1182, Short.MAX_VALUE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel31.setBackground(new java.awt.Color(238, 230, 226));

        search_service_field.setBackground(new java.awt.Color(238, 230, 226));
        search_service_field.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        search_service_field.setBorder(null);
        search_service_field.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_fieldActionPerformed(evt);
            }
        });

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Group 9.png"))); // NOI18N

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(search_service_field, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(search_service_field, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        search_service_field.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                SearchTable();
            }
            public void removeUpdate(DocumentEvent e) {
                if(search_service_field.getText().equals("")){
                    defaultTableModelServiceList.setRowCount(0);
                    CreateTable();
                }else{
                    SearchTable();
                }
            }
            public void insertUpdate(DocumentEvent e) {
                SearchTable();
            }
        });

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
        edit_service_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_service_btnActionPerformed(evt);
            }
        });

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/service 1.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/smoke 1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addContainerGap())
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(590, 590, 590)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(add_service_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(delete_service_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edit_service_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(101, 101, 101))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(delete_service_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(edit_service_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(add_service_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BackPage3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 982, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel34Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(18, Short.MAX_VALUE)))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                .addContainerGap(111, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BackPage3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel34Layout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(66, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void delete_dichvu_dialog_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_dichvu_dialog_btnActionPerformed
        // TODO add your handling code here:
        service_list_dialog.setVisible(false);
    }//GEN-LAST:event_delete_dichvu_dialog_btnActionPerformed

    private void add_dich_dialog_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_dich_dialog_btnActionPerformed
        // TODO add your handling code here:
        
            
        if(add_dichvu_field.getText().equals("") || add_dongia_field.getText().equals("")){
            Message("Vui lòng nhập dữ liệu!", JOptionPane.WARNING_MESSAGE);
        } else{
            int donGia = Integer.parseInt(add_dongia_field.getText());
            if(donGia > 0)
            {
                String maDV =  String.valueOf(Integer.parseInt(String.valueOf(defaultTableModelServiceList.getValueAt(defaultTableModelServiceList.getRowCount() - 1, 1)).substring(2)) + 1);
                switch(maDV.length()){
                    case 1: 
                        maDV = "DV000" + maDV;
                        break;
                    case 2:
                        maDV = "DV00" + maDV;
                        break;
                    case 3:
                        maDV = "DV0" + maDV;
                        break;
                    case 4:
                        maDV = "DV" + maDV;
                    break;
                }
                int kq = 0;
                try{
                    kq = DichVuDAO.getInstance().Insert(new DichVu(maDV, add_dichvu_field.getText(), donGia));
                } 
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                if(kq != 0){
                    ReloadDataTable();
                    service_list_dialog.setVisible(false);
                    Message("Thêm dữ liệu thành công.", JOptionPane.CLOSED_OPTION);
                    add_dichvu_field.setText("");
                    add_dongia_field.setText("");
                }else{
                    Message("Lỗi! Thêm dữ liệu thất bại. Vui lòng nhập lại dữ liệu.", JOptionPane.ERROR_MESSAGE);
                    add_dichvu_field.setText("");
                    add_dongia_field.setText("");
                }
            }
            else
            {
                Message("Lỗi! Đơn giá phải lớn hơn 0!", JOptionPane.ERROR_MESSAGE);
            }
            
        }
    }//GEN-LAST:event_add_dich_dialog_btnActionPerformed

    private void add_dongia_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_dongia_fieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add_dongia_fieldActionPerformed

    private void delete_service_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_service_btnActionPerformed
        // TODO add your handling code here:
        int row = table_service_list.getSelectedRow();
        int[] rows = table_service_list.getSelectedRows();
        if(row < 0){
            Message("Vui lòng chọn dữ liệu muốn xoá!", JOptionPane.INFORMATION_MESSAGE);
        } else{
            
            String mess = "";
            if(rows.length == 1){
              mess = String.valueOf(table_service_list.getValueAt(row, 1)) + " ";
            }else{
              for(int r : rows){
                  mess += String.valueOf(table_service_list.getValueAt(r, 1)) + " ";
              }
            }
            
            int x = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá " + mess + "hay không?");
            if(x == JOptionPane.YES_OPTION){
                int kq = 0;
                if(rows.length == 1){
                    String maDichVu = String.valueOf(table_service_list.getValueAt(row, 1));
                    String tenDichVu = String.valueOf(table_service_list.getValueAt(row, 2));
                    int donGia = 0;
            
                    try {  
                        donGia = currencyFormatVN.parse( String.valueOf(table_service_list.getValueAt(row, 3))).intValue();
                    } catch (ParseException ex) {
                        Logger.getLogger(ServiceList.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try{
                        kq = DichVuDAO.getInstance().Delete(new DichVu(maDichVu, tenDichVu, donGia));
                    
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                 
                }else{
                    for(int r : rows){
                        String maDichVu = String.valueOf(table_service_list.getValueAt(r, 1));
                        String tenDichVu = String.valueOf(table_service_list.getValueAt(r, 2));
//                        int donGia = Integer.parseInt(String.valueOf(table_service_list.getValueAt(r, 3)));
                        int donGia = 0;
            
                        try {
                            donGia = currencyFormatVN.parse(String.valueOf(table_service_list.getValueAt(r, 3))).intValue();
                        } catch (ParseException ex) {
                            Logger.getLogger(DishList.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        try{
                            kq = DichVuDAO.getInstance().Delete(new DichVu(maDichVu, tenDichVu, donGia));  
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }  
                }
                if(kq>0){
                    ReloadDataTable();
                    Message("Xóa dữ liệu thành công.", JOptionPane.CLOSED_OPTION);
                    
                }
                else{
                    Message("Xoá dữ liệu thất bại!", JOptionPane.ERROR_MESSAGE);
                } 
            }
        }
    }//GEN-LAST:event_delete_service_btnActionPerformed

    private void add_service_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_service_btnActionPerformed
        // TODO add your handling code here:
        service_list_dialog.setSize(400,400);
        service_list_dialog.setLocationRelativeTo(null);
        service_list_dialog.setVisible(true);
    }//GEN-LAST:event_add_service_btnActionPerformed

    private void search_fieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_fieldActionPerformed
        // TODO add your handling code here:
        SearchTable();
    }//GEN-LAST:event_search_fieldActionPerformed

    private void edit_service_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_service_btnActionPerformed
        // TODO add your handling code here:
        
        int row = table_service_list.getSelectedRow();
        if(row < 0){
            Message("Vui lòng chọn dữ liệu muốn chỉnh sửa!", JOptionPane.INFORMATION_MESSAGE);
        }else{
            update_dichvu_field1.setText(String.valueOf(table_service_list.getValueAt(row, 2)));
            int donGia = 0;
            
            try {
                donGia = currencyFormatVN.parse(String.valueOf(table_service_list.getValueAt(row, 3))).intValue();
            } catch (ParseException ex) {
                Logger.getLogger(ServiceList.class.getName()).log(Level.SEVERE, null, ex);
            }
            update_dongia_field1.setText(String.valueOf(donGia));
            service_list_dialog_update.setSize(400,400);
            service_list_dialog_update.setLocationRelativeTo(null);
            service_list_dialog_update.setVisible(true);
        }
    }//GEN-LAST:event_edit_service_btnActionPerformed

    private void delete_dichvu_dialog_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_dichvu_dialog_btn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_delete_dichvu_dialog_btn1ActionPerformed

    private void add_dich_dialog_btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_dich_dialog_btn1ActionPerformed
        // TODO add your handling code here:
        int row = table_service_list.getSelectedRow();
        String maDichVu = String.valueOf(table_service_list.getValueAt(row, 1));
        int kq = 0;
        if(!update_dichvu_field1.getText().equals("") && !update_dongia_field1.getText().equals(""))
        {
            int donGia = 0;
            donGia = Integer.parseInt(update_dongia_field1.getText());
            if(donGia > 0)
            {
                if(!update_dichvu_field1.getText().equals(String.valueOf(table_service_list.getValueAt(row, 2))) || 
                    !update_dongia_field1.getText().equals(String.valueOf(table_service_list.getValueAt(row, 3))))
                {
                    kq = DichVuDAO.getInstance().Update(new DichVu(maDichVu, update_dichvu_field1.getText(),  donGia));
                }
                if(kq!=0)
                {
                    ReloadDataTable();
                    service_list_dialog_update.setVisible(false);
                    Message("Chỉnh sửa dữ liệu thành công!", JOptionPane.CLOSED_OPTION);
                    update_dichvu_field1.setText("");
                    update_dongia_field1.setText("");
                }
                else{
                    service_list_dialog_update.setVisible(false);
                    Message("Chỉnh sửa dữ liệu thất bại!", JOptionPane.ERROR_MESSAGE);

                }
            }
            else
            {
                Message("Vui lòng nhập đơn giá lớn hơn 0", JOptionPane.ERROR_MESSAGE);
            }
                                   
        }
        
    }//GEN-LAST:event_add_dich_dialog_btn1ActionPerformed

    private void update_dongia_field1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_dongia_field1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_update_dongia_field1ActionPerformed

    private void BackPage3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackPage3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BackPage3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackPage3;
    private javax.swing.JButton add_dich_dialog_btn;
    private javax.swing.JButton add_dich_dialog_btn1;
    private javax.swing.JTextField add_dichvu_field;
    private javax.swing.JTextField add_dongia_field;
    private javax.swing.JButton add_service_btn;
    private javax.swing.JButton delete_dichvu_dialog_btn;
    private javax.swing.JButton delete_dichvu_dialog_btn1;
    private javax.swing.JButton delete_service_btn;
    private javax.swing.JButton edit_service_btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
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
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField search_service_field;
    private javax.swing.JDialog service_list_dialog;
    private javax.swing.JDialog service_list_dialog_update;
    private javax.swing.JTable table_service_list;
    private javax.swing.JTextField update_dichvu_field1;
    private javax.swing.JTextField update_dongia_field1;
    // End of variables declaration//GEN-END:variables
}
