/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import java.util.ArrayList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.plaf.basic.BasicInternalFrameUI;




/**
 *
 * @author macbookpro
 */
public class PartyHallList extends javax.swing.JInternalFrame {
    private DefaultTableModel defaultTableModelHall;
    private ArrayList<Hall> arr;
    
    public class Hall{
        private String maSanh;
        private String tenSanh;
        private String loaiSanh;
        private long donGiaToiThieu;
        private long soLuongBanToiDa;
        private boolean chon;

        public Hall(String maSanh,String tenSanh, String loaiSanh, long donGiaToiThieu, long soLuongBanToida, boolean chon) {
            this.maSanh = maSanh;
            this.tenSanh = tenSanh;
            this.loaiSanh = loaiSanh;
            this.donGiaToiThieu = donGiaToiThieu;
            this.soLuongBanToiDa = soLuongBanToida;
            this.chon = chon;
        }

        public String getTenSanh() {
            return tenSanh;
        }

        public String getLoaiSanh() {
            return loaiSanh;
        }

        public long getDonGiaTT() {
            return donGiaToiThieu;
        }

        public long getSLBanToiDa() {
            return soLuongBanToiDa;
        }

        public boolean isChon() {
            return chon;
        }

        public void setTenSanh(String tenSanh) {
            this.tenSanh = tenSanh;
        }

        public void setLoaiSanh(String loaiSanh) {
            this.loaiSanh = loaiSanh;
        }

        public void setDonGiaTT(long donGiaTT) {
            this.donGiaToiThieu = donGiaTT;
        }

        public void setSLBanToiDa(long sLBanTD) {
            this.soLuongBanToiDa = sLBanTD;
        }

        public void setChon(boolean chon) {
            this.chon = chon;
        }
    }
    /**
     * Creates new form PartyHallList
     */
    public PartyHallList() {
        arr = new ArrayList<Hall>();
        arr.add(new Hall("S001","a","A", 3000,5000, false));
        arr.add(new Hall("S002","b","B", 3000,5000, false));
        arr.add(new Hall("S003","c","C", 3000,5000, false));
        arr.add(new Hall("S004","d","D", 3000,5000, false));
        arr.add(new Hall("S005","e","E", 3000,5000, false));
//        boolean flase = false;
//        Panel_Edit_Delete.setVisible(flase);
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        defaultTableModelHall = (DefaultTableModel)Table_Hall.getModel();
//        defaultTableModelHall = new DefaultTableModel();
//        Table_Hall.setModel(defaultTableModelHall);
//        defaultTableModelHall.addColumn("STT", "Ten Sanh", "Loai Sanh", "Don Gia", "SLBTD", "Tuy Chon");
//        defaultTableModelHall.addColumn("STT");
//        defaultTableModelHall.addColumn("Ten Sanh");
//        defaultTableModelHall.addColumn("Loai Sanh");
//        defaultTableModelHall.addColumn("Don Gia");
//        defaultTableModelHall.addColumn("SLBTD");
//        defaultTableModelHall.addColumn("Tuy Chon");
        int i = 0;
//        defaultTableModelHall.addRow(new Object[]{i++,"á", "sdsf", 100000, 10000, true});
        for(Hall x: arr){
            defaultTableModelHall.addRow(new Object[]{++i,x.tenSanh, x.loaiSanh, x.donGiaToiThieu, x.soLuongBanToiDa, x.chon});
        }
//        ListSelectionModel listSelectionModelMA = Table_Hall.getSelectionModel();
//        listSelectionModelMA.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//         listSelectionModelMA.addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                int row = Table_Hall.getSelectedRow();
//            }
//        }
//        );
//        for(Hall x: arr){
//        }
        
//        jTable1.getColumnModel().getColumn(5).setCellRenderer(new Table_Option());
//        Icon i = Icon_Sanh.getIcon();
//        ImageIcon icon = (ImageIcon)i;
//        Image img = icon.getImage().getScaledInstance(Icon_Sanh.getWidth(), Icon_Sanh.getHeight(), Image.SCALE_SMOOTH);
//        Icon_Sanh.setIcon(new ImageIcon(img));
    }
    

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Add_Hall_List_Dialog = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel_HallList_AddHall = new javax.swing.JPanel();
        HallList = new javax.swing.JLabel();
        Button_AddHall = new javax.swing.JButton();
        jPanel_Content = new javax.swing.JPanel();
        Icon_Hall = new javax.swing.JLabel();
        jPanel_SearchContent2 = new javax.swing.JPanel();
        jTextField7 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table_Hall = new javax.swing.JTable();

        Add_Hall_List_Dialog.setAlwaysOnTop(true);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(69, 96, 134));
        jLabel2.setText("Thêm sảnh");

        jTextField2.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setText("Loại Sảnh");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setText("Tên Sảnh");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setText("Đơn giá tối thiểu");

        jTextField4.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setText("Số lượng bàn tối đa");

        jTextField5.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField5)
                    .addComponent(jTextField4)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 248, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jButton1.setText("Hủy");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(132, 70, 133));
        jButton2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Thêm");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addGap(38, 38, 38))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Add_Hall_List_DialogLayout = new javax.swing.GroupLayout(Add_Hall_List_Dialog.getContentPane());
        Add_Hall_List_Dialog.getContentPane().setLayout(Add_Hall_List_DialogLayout);
        Add_Hall_List_DialogLayout.setHorizontalGroup(
            Add_Hall_List_DialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Add_Hall_List_DialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Add_Hall_List_DialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        Add_Hall_List_DialogLayout.setVerticalGroup(
            Add_Hall_List_DialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Add_Hall_List_DialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);
        setPreferredSize(new java.awt.Dimension(1170, 858));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(30, 30));
        jPanel1.setPreferredSize(new java.awt.Dimension(1158, 822));

        jPanel_HallList_AddHall.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_HallList_AddHall.setPreferredSize(new java.awt.Dimension(1158, 90));

        HallList.setFont(new java.awt.Font("Arial", 1, 35)); // NOI18N
        HallList.setForeground(new java.awt.Color(69, 96, 134));
        HallList.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        HallList.setText("~Danh Mục Sảnh~");
        HallList.setToolTipText("");
        HallList.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        HallList.setVerifyInputWhenFocusTarget(false);

        Button_AddHall.setBackground(new java.awt.Color(132, 70, 133));
        Button_AddHall.setFont(new java.awt.Font("Arial", 1, 22)); // NOI18N
        Button_AddHall.setForeground(new java.awt.Color(255, 255, 255));
        Button_AddHall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/plus.png"))); // NOI18N
        Button_AddHall.setText("Thêm sảnh");
        Button_AddHall.setIconTextGap(15);
        Button_AddHall.setPreferredSize(new java.awt.Dimension(263, 64));
        Button_AddHall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_AddHallActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_HallList_AddHallLayout = new javax.swing.GroupLayout(jPanel_HallList_AddHall);
        jPanel_HallList_AddHall.setLayout(jPanel_HallList_AddHallLayout);
        jPanel_HallList_AddHallLayout.setHorizontalGroup(
            jPanel_HallList_AddHallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_HallList_AddHallLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(HallList, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 352, Short.MAX_VALUE)
                .addComponent(Button_AddHall, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        jPanel_HallList_AddHallLayout.setVerticalGroup(
            jPanel_HallList_AddHallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_HallList_AddHallLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(HallList, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel_HallList_AddHallLayout.createSequentialGroup()
                .addComponent(Button_AddHall, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel_Content.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_Content.setPreferredSize(new java.awt.Dimension(1158, 130));
        jPanel_Content.setRequestFocusEnabled(false);
        jPanel_Content.setVerifyInputWhenFocusTarget(false);

        Icon_Hall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Hall.png"))); // NOI18N

        jPanel_SearchContent2.setBackground(new java.awt.Color(238, 230, 226));
        jPanel_SearchContent2.setPreferredSize(new java.awt.Dimension(346, 60));

        jTextField7.setBackground(new java.awt.Color(238, 230, 226));
        jTextField7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField7.setText("Tìm kiếm...");
        jTextField7.setBorder(null);
        jTextField7.setMinimumSize(new java.awt.Dimension(1, 20));
        jTextField7.setPreferredSize(new java.awt.Dimension(56, 20));
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Search_1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel_SearchContent2Layout = new javax.swing.GroupLayout(jPanel_SearchContent2);
        jPanel_SearchContent2.setLayout(jPanel_SearchContent2Layout);
        jPanel_SearchContent2Layout.setHorizontalGroup(
            jPanel_SearchContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_SearchContent2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
        );
        jPanel_SearchContent2Layout.setVerticalGroup(
            jPanel_SearchContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_SearchContent2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel_SearchContent2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField7, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel_ContentLayout = new javax.swing.GroupLayout(jPanel_Content);
        jPanel_Content.setLayout(jPanel_ContentLayout);
        jPanel_ContentLayout.setHorizontalGroup(
            jPanel_ContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ContentLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(Icon_Hall, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 587, Short.MAX_VALUE)
                .addComponent(jPanel_SearchContent2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101))
        );
        jPanel_ContentLayout.setVerticalGroup(
            jPanel_ContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_ContentLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(jPanel_ContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Icon_Hall, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_ContentLayout.createSequentialGroup()
                        .addComponent(jPanel_SearchContent2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        Table_Hall.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        Table_Hall.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên Sảnh", "Loại Sảnh", "Đơn giá tối thiểu", "Số lượng bàn tối đa", "Tùy chọn"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.Long.class, java.lang.Long.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table_Hall.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Table_Hall.setGridColor(new java.awt.Color(245, 239, 239));
        Table_Hall.setInheritsPopupMenu(true);
        Table_Hall.setName(""); // NOI18N
        Table_Hall.setPreferredSize(new java.awt.Dimension(375, 80));
        Table_Hall.setRowHeight(25);
        Table_Hall.setSelectionBackground(new java.awt.Color(246, 198, 158));
        Table_Hall.setShowGrid(false);
        jScrollPane1.setViewportView(Table_Hall);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel_HallList_AddHall, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel_Content, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 958, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jPanel_HallList_AddHall, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jPanel_Content, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        jPanel_HallList_AddHall.getAccessibleContext().setAccessibleName("");

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void Button_AddHallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_AddHallActionPerformed
        Add_Hall_List_Dialog.setSize(500,530);
        Add_Hall_List_Dialog.setLocationRelativeTo(null);
        Add_Hall_List_Dialog.setVisible(true);    
    }//GEN-LAST:event_Button_AddHallActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Add_Hall_List_Dialog.setVisible(false); 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Add_Hall_List_Dialog;
    private javax.swing.JButton Button_AddHall;
    private javax.swing.JLabel HallList;
    private javax.swing.JLabel Icon_Hall;
    private javax.swing.JTable Table_Hall;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel_Content;
    private javax.swing.JPanel jPanel_HallList_AddHall;
    private javax.swing.JPanel jPanel_SearchContent2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}

