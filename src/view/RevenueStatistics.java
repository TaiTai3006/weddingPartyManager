/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import dao.ChiTietBaoCaoDAO;
import dao.BaoCaoDoanhThuDAO;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import model.BaoCaoDoanhThu;
import model.ChiTietBaoCao;

import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartPanel;  
import org.jfree.chart.JFreeChart;  
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset; 
//import model.LineChart;

/**
 *
 * @author macbookpro
 */
public class RevenueStatistics extends javax.swing.JInternalFrame {
    
    private int MinValueYear_BCDT = BaoCaoDoanhThuDAO.getInstance().MinValueYear();
    private int MaxValueYear_BCDT = BaoCaoDoanhThuDAO.getInstance().MaxValueYear();
    private DefaultTableModel defaultTableModel_RStatistics;
    /**
     * Creates new form WorkingTimeList
     */
    public RevenueStatistics() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        jpStatisticsByDay.setVisible(true);
        jpStatisticsByMonth.setVisible(false);
        jpStatisticsByYear.setVisible(false);
        jpChart.setVisible(false);
//        LineChart example = new LineChart(0);
////        example.setVisible(true);
////        example.setSize(400, 400);  
//        jpChart.removeAll();
//        jpChart.add(example).setVisible(true);
//        DefaultCategoryDataset dataset = createDataset();  
//    // Create chart
//    JFreeChart chart = ChartFactory.createLineChart(  
//        "Site Traffic", // Chart title  
//        "Date", // X-Axis Label  
//        "Number of Visitor", // Y-Axis Label  
//        dataset  
//        );
//
//    chart.setBackgroundPaint(Color.WHITE);
//    chart.setBorderPaint(Color.BLUE);
////      chart.setBackgroundImage(Color.GREEN);
//  
//    ChartPanel panel = new ChartPanel(chart);  
//    setContentPane(panel);
    }    
    
    public String RoundDoubleExample(double number)
    {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String roundedNumber = decimalFormat.format(number);
        
       
        return roundedNumber;
    }
    
    public void CreateDataTableByDay(int month, int year) {
        NumberFormat currencyFormatVN = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        defaultTableModel_RStatistics = (DefaultTableModel) ThongKeDoanhThuTable.getModel();
        ArrayList<ChiTietBaoCao> ctbc = ChiTietBaoCaoDAO.getInstance().SelectByMonth(month, year);
        int i = 0;
        for (ChiTietBaoCao x : ctbc) {
           
            String[] arrDate = x.getNgay().split("-");
            defaultTableModel_RStatistics.addRow(new Object[]{++i, arrDate[2], x.getSoLuongTiec(), String.valueOf(currencyFormatVN.format(x.getDoanhThu())) , RoundDoubleExample(x.getTiLe()*100) + "%"});
        }
    }
    
    public void CreateDataTableByMonth(int year) {
        NumberFormat currencyFormatVN = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        defaultTableModel_RStatistics = (DefaultTableModel) ThongKeDoanhThuTable.getModel();
        ArrayList<BaoCaoDoanhThu> bcdt = BaoCaoDoanhThuDAO.getInstance().SelectByYear(year);
        int i = 0;
//        String[] arrDate;
        for (BaoCaoDoanhThu x : bcdt) {
            
            defaultTableModel_RStatistics.addRow(new Object[]{++i, x.getThang(), String.valueOf(currencyFormatVN.format(x.getTongDoanhThu()))});
        }
    }
    
    public void CreateDataTableByForYear1ToYear2(int year1, int year2) {
        NumberFormat currencyFormatVN = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        defaultTableModel_RStatistics = (DefaultTableModel) ThongKeDoanhThuTable.getModel();
        ArrayList<BaoCaoDoanhThu> bcdt = BaoCaoDoanhThuDAO.getInstance().SelectByFromY1toY2(year1, year2);
        int i = 0;
        for (BaoCaoDoanhThu x : bcdt) {
            defaultTableModel_RStatistics.addRow(new Object[]{++i, x.getNam(), String.valueOf(currencyFormatVN.format(x.getTongDoanhThu()))});
        }
    }
    
    public void DeleteDataTable()
    {
//        ThongKeDoanhThuTable.setRowCount(0);
    }
    
    public void SetDataTableByDayNull()
    {
        ThongKeDoanhThuTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            },
        new String [] {
            "STT", "Ngày", "Số lượng tiệc cưới", "Doanh thu ", "Tỷ lệ"
        }
        ));
    }
    
    public void SetDataTableByMonthNull()
    {
        ThongKeDoanhThuTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            },
        new String [] {
            "STT", "Tháng", "Doanh thu"
        }
        ));
    }
    
    public void SetDataTableByYearNull()
    {
        ThongKeDoanhThuTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {
            },
        new String [] {
            "STT", "Năm","Doanh thu "
        }
        ));  
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ThongKeDoanhThuTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jcbStatistics = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jpStatisticsByDay = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jTextThang_Ngay = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextNam_Ngay = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLThongBao_Ngay = new javax.swing.JLabel();
        jpStatisticsByMonth = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jTextNam_Thang = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jl_ThongBao_Thang = new javax.swing.JLabel();
        jpStatisticsByYear = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jTextNam1_Nam = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextNam2_Nam = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jL_ThongBao_Nam = new javax.swing.JLabel();
        jpChart = new javax.swing.JDesktopPane();

        addForm.setMinimumSize(new java.awt.Dimension(531, 490));
        addForm.setModal(true);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(69, 96, 134));
        jLabel4.setText("Thêm ca làm ");

        jLabel5.setFont(new java.awt.Font("SansSerif", 2, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Tên ca làm");

        jTextField2.setBackground(new java.awt.Color(242, 242, 242));
        jTextField2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField2.setText("PhuocPhan");

        jLabel7.setFont(new java.awt.Font("SansSerif", 2, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("Giờ bắt đầu");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("*");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("*");

        jTextField3.setBackground(new java.awt.Color(242, 242, 242));
        jTextField3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField3.setText("7:00:00");

        jLabel9.setFont(new java.awt.Font("SansSerif", 2, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 153, 153));
        jLabel9.setText("Giờ kết thúc");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("*");

        jTextField4.setBackground(new java.awt.Color(242, 242, 242));
        jTextField4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField4.setText("21:00:00");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Clock.png"))); // NOI18N

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Clock.png"))); // NOI18N

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButton2.setText("Hủy");

        jButton3.setBackground(new java.awt.Color(132, 70, 133));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Thêm ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout addFormLayout = new javax.swing.GroupLayout(addForm.getContentPane());
        addForm.getContentPane().setLayout(addFormLayout);
        addFormLayout.setHorizontalGroup(
            addFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        addFormLayout.setVerticalGroup(
            addFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setPreferredSize(new java.awt.Dimension(1170, 730));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1170, 730));

        ThongKeDoanhThuTable.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        ThongKeDoanhThuTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Ngày", "Số lượng tiệc cưới", "Doanh thu ", "Tỷ lệ"
            }
        ));
        ThongKeDoanhThuTable.setFocusable(false);
        ThongKeDoanhThuTable.setRowHeight(25);
        ThongKeDoanhThuTable.setSelectionBackground(new java.awt.Color(69, 96, 134));
        jScrollPane1.setViewportView(ThongKeDoanhThuTable);
        if (ThongKeDoanhThuTable.getColumnModel().getColumnCount() > 0) {
            ThongKeDoanhThuTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        }
        ThongKeDoanhThuTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        ThongKeDoanhThuTable.getTableHeader().setOpaque(false);
        ThongKeDoanhThuTable.getTableHeader().setBackground(new Color(243,246,249));
        ThongKeDoanhThuTable.setDefaultEditor(Object.class, null);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(69, 96, 134));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THỐNG KÊ DOANH THU");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel1.setPreferredSize(new java.awt.Dimension(400, 36));

        jcbStatistics.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jcbStatistics.setForeground(new java.awt.Color(69, 96, 134));
        jcbStatistics.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ngày", "Tháng", "Năm" }));
        jcbStatistics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbStatisticsActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(69, 96, 134));
        jLabel2.setText("Thống kê theo:");

        jpStatisticsByDay.setBackground(new java.awt.Color(255, 255, 255));
        jpStatisticsByDay.setPreferredSize(new java.awt.Dimension(355, 224));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel14.setText("Tháng");

        jTextThang_Ngay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextThang_NgayActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel15.setText("Năm");

        jButton4.setBackground(new java.awt.Color(132, 70, 133));
        jButton4.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Xem");
        jButton4.setPreferredSize(new java.awt.Dimension(72, 37));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLThongBao_Ngay.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jpStatisticsByDayLayout = new javax.swing.GroupLayout(jpStatisticsByDay);
        jpStatisticsByDay.setLayout(jpStatisticsByDayLayout);
        jpStatisticsByDayLayout.setHorizontalGroup(
            jpStatisticsByDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpStatisticsByDayLayout.createSequentialGroup()
                .addGroup(jpStatisticsByDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpStatisticsByDayLayout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpStatisticsByDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextNam_Ngay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                        .addComponent(jTextThang_Ngay, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLThongBao_Ngay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpStatisticsByDayLayout.setVerticalGroup(
            jpStatisticsByDayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpStatisticsByDayLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextThang_Ngay, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextNam_Ngay, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLThongBao_Ngay, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jpStatisticsByMonth.setBackground(new java.awt.Color(255, 255, 255));
        jpStatisticsByMonth.setPreferredSize(new java.awt.Dimension(355, 232));

        jLabel16.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel16.setText("Năm");

        jButton5.setBackground(new java.awt.Color(132, 70, 133));
        jButton5.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Xem");
        jButton5.setPreferredSize(new java.awt.Dimension(72, 37));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jl_ThongBao_Thang.setForeground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout jpStatisticsByMonthLayout = new javax.swing.GroupLayout(jpStatisticsByMonth);
        jpStatisticsByMonth.setLayout(jpStatisticsByMonthLayout);
        jpStatisticsByMonthLayout.setHorizontalGroup(
            jpStatisticsByMonthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpStatisticsByMonthLayout.createSequentialGroup()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jTextNam_Thang, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
            .addComponent(jl_ThongBao_Thang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpStatisticsByMonthLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117))
        );
        jpStatisticsByMonthLayout.setVerticalGroup(
            jpStatisticsByMonthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpStatisticsByMonthLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextNam_Thang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jl_ThongBao_Thang, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        jpStatisticsByYear.setBackground(new java.awt.Color(255, 255, 255));
        jpStatisticsByYear.setPreferredSize(new java.awt.Dimension(392, 224));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel19.setText("Từ năm");

        jLabel20.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel20.setText("Đến năm");

        jButton7.setBackground(new java.awt.Color(132, 70, 133));
        jButton7.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Xem");
        jButton7.setPreferredSize(new java.awt.Dimension(72, 37));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jL_ThongBao_Nam.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jpStatisticsByYearLayout = new javax.swing.GroupLayout(jpStatisticsByYear);
        jpStatisticsByYear.setLayout(jpStatisticsByYearLayout);
        jpStatisticsByYearLayout.setHorizontalGroup(
            jpStatisticsByYearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextNam1_Nam, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jTextNam2_Nam)
            .addGroup(jpStatisticsByYearLayout.createSequentialGroup()
                .addGroup(jpStatisticsByYearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jpStatisticsByYearLayout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(113, Short.MAX_VALUE))
            .addComponent(jL_ThongBao_Nam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpStatisticsByYearLayout.setVerticalGroup(
            jpStatisticsByYearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpStatisticsByYearLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextNam1_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextNam2_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jL_ThongBao_Nam, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLayeredPane1.setLayer(jpStatisticsByDay, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jpStatisticsByMonth, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jpStatisticsByYear, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(jpStatisticsByDay, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpStatisticsByMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane1Layout.createSequentialGroup()
                    .addComponent(jpStatisticsByYear, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 27, Short.MAX_VALUE)))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpStatisticsByDay, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpStatisticsByMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpStatisticsByYear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE))
        );

        jpChart.setBackground(new java.awt.Color(255, 255, 255));
        jpChart.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jpChartLayout = new javax.swing.GroupLayout(jpChart);
        jpChart.setLayout(jpChartLayout);
        jpChartLayout.setHorizontalGroup(
            jpChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 635, Short.MAX_VALUE)
        );
        jpChartLayout.setVerticalGroup(
            jpChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addGap(100, 100, 100))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jcbStatistics, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jpChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(67, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcbStatistics, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jpChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addGap(52, 52, 52))
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

    private void jcbStatisticsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbStatisticsActionPerformed
        // TODO add your handling code here:
        switch (jcbStatistics.getSelectedIndex()) {
            case 0:
            {
                jpStatisticsByDay.setVisible(true);
                jpStatisticsByMonth.setVisible(false);
                jpStatisticsByYear.setVisible(false);
                jTextThang_Ngay.setText("");
                jTextNam_Ngay.setText("");
                SetDataTableByDayNull();
                jpChart.removeAll();
                jpChart.setVisible(false);
                break;
            }
            case 1:
            {
                jpStatisticsByDay.setVisible(false);
                jpStatisticsByMonth.setVisible(true);
                jpStatisticsByYear.setVisible(false);
                jTextNam_Thang.setText("");
                SetDataTableByMonthNull();
                jpChart.removeAll();
                jpChart.setVisible(false);
                
                break;
            }
            case 2:
            {
                jpStatisticsByDay.setVisible(false);
                jpStatisticsByMonth.setVisible(false);
                jpStatisticsByYear.setVisible(true);
                jTextNam1_Nam.setText("");
                jTextNam2_Nam.setText("");
                SetDataTableByYearNull();   
                jpChart.removeAll();
                jpChart.setVisible(false);
                break;
            }
            default:
                throw new AssertionError();
        }
        
    }//GEN-LAST:event_jcbStatisticsActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        SetDataTableByDayNull();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        String textMonth = jTextThang_Ngay.getText();
        String textYear = jTextNam_Ngay.getText();
            try {
                int month = Integer.valueOf(textMonth);
                int year = Integer.valueOf(textYear);
                if(month >= 1 && month <=12)
                {
                    if(year >= MinValueYear_BCDT && year <= currentYear)
                    {
                        ArrayList<ChiTietBaoCao> ctbc0 = ChiTietBaoCaoDAO.getInstance().SelectByMonth(month, year);
                        if(ctbc0.size() == 0)
                        {
                            jpChart.setVisible(false);
                            jLThongBao_Ngay.setText("* Dữ liệu doanh thu của tháng, năm này rỗng!");
//                            jTextNam_Ngay.setText("");
//                            jTextThang_Ngay.setText("");
                        }
                        else
                        {
                            jpChart.setVisible(true);
                            jLThongBao_Ngay.setText("");
                            LineChart example = new LineChart(0, month , year, year);
                            jpChart.add(example).setVisible(true);
                            CreateDataTableByDay(month, year);
                        }
                    }
                    else
                    {
                        if(year > currentYear)
                        {
                            jpChart.setVisible(false);
                            jLThongBao_Ngay.setText("* Vui lòng nhập từ năm "+  currentYear + " trở về trước!");
                            jTextThang_Ngay.setText("");
                            jTextNam_Ngay.setText("");
                        }
                        else
                        {
                            jpChart.setVisible(false);
                            jLThongBao_Ngay.setText("Vui lòng nhập từ năm " + MinValueYear_BCDT +"!");
                            jTextThang_Ngay.setText("");
                            jTextNam_Ngay.setText("");
                        }
                    }
                }
                else
                {
                    jpChart.setVisible(false);
                    if(year >= MinValueYear_BCDT && year <= currentYear)
                    {
                        jLThongBao_Ngay.setText("* Vui lòng nhập đúng tháng!");
                        jTextThang_Ngay.setText("");
                        jTextNam_Ngay.setText("");
                    }
                    else
                    {
                        if(year > currentYear)
                        {

                            jLThongBao_Ngay.setText("* Vui lòng nhập đúng tháng, và từ năm "+  currentYear + " trở về trước!");
                            jTextThang_Ngay.setText("");
                            jTextNam_Ngay.setText("");
                        }
                        else
                        {

                            jLThongBao_Ngay.setText("Vui lòng nhập đúng tháng, và từ năm " + MinValueYear_BCDT +"!");
                            jTextThang_Ngay.setText("");
                            jTextNam_Ngay.setText("");
                        }
                    }
                }
                // Số hợp lệ, xử lý tại đây
            } catch (NumberFormatException ex) {
                // Không phải số, xử lý tại đây
                System.out.print(ex);
                jpChart.setVisible(false);
                jLThongBao_Ngay.setText("* Vui lòng nhập tháng, năm hợp lệ!");
                jTextNam_Ngay.setText("");
                jTextThang_Ngay.setText("");
            }
        
//        ArrayList<ChiTietBaoCao> ctbc0 = ChiTietBaoCaoDAO.getInstance().SelectByMonth(month, year);
//        if(ctbc0.size() == 0)
//        {
//            jLThongBao_Ngay.setText("* Dữ liệu doanh thu của tháng, năm này rỗng!");
//            jTextNam_Ngay.setText("");
//            jTextNam_Ngay.setText("");
//        }
//        else
//        {
//            jpChart.setVisible(true);
//            jLThongBao_Ngay.setText("");
//            LineChart example = new LineChart(0, month , year, year);
//            jpChart.add(example).setVisible(true);
//            SetDataTableByDayNull();
//            CreateDataTableByDay(month, year);
//        }

        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextThang_NgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextThang_NgayActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextThang_NgayActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
//        int year = Integer.parseInt(jTextNam_Thang.getText());
        SetDataTableByMonthNull();
//        int year = 0;
        String text = jTextNam_Thang.getText();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                try {
                    int year = Integer.parseInt(text);
//                    System.out.println("adsfadsfads" + year);
                    if(year >= MinValueYear_BCDT && year <= 2027)
                    {
                        ArrayList<BaoCaoDoanhThu> bcdt = BaoCaoDoanhThuDAO.getInstance().SelectByYear(year);
                        if(bcdt.size() == 0)
                        {
                            jl_ThongBao_Thang.setText("* Doanh thu của tháng này rỗng");
                            jTextNam_Thang.setText("");
                        }
                        else
                        {
                            jpChart.setVisible(true);
                            LineChart example = new LineChart(1, 0 , year, year); 
                            jpChart.removeAll();
                            jpChart.add(example).setVisible(true);
                            CreateDataTableByMonth(year);
                            jl_ThongBao_Thang.setText("");                           
                        }

                    }
                    else
                    {
                        jpChart.setVisible(false);
                        if(year > currentYear)
                        {
                            jl_ThongBao_Thang.setText("* Vui lòng nhập từ năm "+ MinValueYear_BCDT +"  đến "+  2027 + " !");
                            jTextNam_Thang.setText("");
                        }
                            
                        else
                        {
                            jl_ThongBao_Thang.setText("* Vui lòng nhập từ năm "+ MinValueYear_BCDT +"  đến "+  2027 + " !");
                            jTextNam_Thang.setText("");
                        }
                            
                    }
                    // Số hợp lệ, xử lý tại đây
                } catch (NumberFormatException ex) {
                    // Không phải số, xử lý tại đây
                    jpChart.setVisible(false);
                    jl_ThongBao_Thang.setText("* Vui lòng nhập năm hợp lệ!");
                    jTextNam_Thang.setText("");
                }
        

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        SetDataTableByYearNull();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        String textYear1 = jTextNam1_Nam.getText();
        String textYear2 = jTextNam2_Nam.getText();
        
        try {
            int year1 = Integer.parseInt(textYear1);
            int year2 = Integer.parseInt(textYear2);
            if(year2 - year1 >= 1)
            {
                if(year1 < MinValueYear_BCDT || year2 > currentYear + 4)
                {
                    jpChart.setVisible(false);
                    jL_ThongBao_Nam.setText("Vui lòng nhập từ năm " + MinValueYear_BCDT + " đến tối đa hơn 4 năm hiện tại!");
                    jTextNam1_Nam.setText("");
                    jTextNam2_Nam.setText("");
                }
                else
                {
                    jL_ThongBao_Nam.setText("");
                    jpChart.setVisible(true);
                    LineChart example = new LineChart(2, 0 , year1, year2);  
                    jpChart.removeAll();
                    jpChart.add(example).setVisible(true);
                    CreateDataTableByForYear1ToYear2(year1, year2);
                }
            }
            else
            {
                jpChart.setVisible(false);
                if(year1 < MinValueYear_BCDT && year2 < currentYear + 5)
                {
                    jL_ThongBao_Nam.setText("Vui lòng nhập từ năm " + MinValueYear_BCDT + " đến tối đa hơn 4 năm hiện tại!");
                    jTextNam1_Nam.setText("");
                    jTextNam2_Nam.setText("");
                }
                else
                {
                    jL_ThongBao_Nam.setText("Vui lòng nhập đúng khoảng cách năm");
                    jTextNam1_Nam.setText("");
                    jTextNam2_Nam.setText("");
                }                
            }
//                  
                    // Số hợp lệ, xử lý tại đây
        } 
        catch (NumberFormatException ex) {
            // Không phải số, xử lý tại đây
            jpChart.setVisible(false);
            jL_ThongBao_Nam.setText("* Vui lòng nhập năm hợp lệ!");
            jTextNam1_Nam.setText("");
            jTextNam2_Nam.setText("");
        }
    }//GEN-LAST:event_jButton7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable ThongKeDoanhThuTable;
    private javax.swing.JDialog addForm;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLThongBao_Ngay;
    private javax.swing.JLabel jL_ThongBao_Nam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextNam1_Nam;
    private javax.swing.JTextField jTextNam2_Nam;
    private javax.swing.JTextField jTextNam_Ngay;
    private javax.swing.JTextField jTextNam_Thang;
    private javax.swing.JTextField jTextThang_Ngay;
    private javax.swing.JComboBox<String> jcbStatistics;
    private javax.swing.JLabel jl_ThongBao_Thang;
    private javax.swing.JDesktopPane jpChart;
    private javax.swing.JPanel jpStatisticsByDay;
    private javax.swing.JPanel jpStatisticsByMonth;
    private javax.swing.JPanel jpStatisticsByYear;
    // End of variables declaration//GEN-END:variables
}
