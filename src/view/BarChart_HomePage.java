/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import dao.ChiTietBaoCaoDAO;
import dao.BaoCaoDoanhThuDAO;
import database.JDBCUtil;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import model.ChiTietBaoCao;
import model.BaoCaoDoanhThu;
import model.TaiKhoan;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author ACER
 */
public class BarChart_HomePage extends javax.swing.JInternalFrame {

    /**
     * Creates new form LineChartExample
     */
    public BarChart_HomePage(int year, int month) {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        CreateBarChart(year, month);
    }
    
    public void CreateBarChart(int year, int month){
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<String[]> results1 = new ArrayList<>();
        System.out.println(month);
        try {
            Connection con = JDBCUtil.getConnection();

            String sql = "SELECT phieudattieccuoi.ngayDaiTiec, COUNT(*) FROM phieudattieccuoi WHERE YEAR(phieudattieccuoi.ngayDaiTiec) = ? and MONTH(phieudattieccuoi.ngayDaiTiec) = ? GROUP BY phieudattieccuoi.ngayDaiTiec";

            PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, year);
            st.setInt(2, month);

            ResultSet kq = st.executeQuery();

            while (kq.next()) {
                String[] row = {kq.getString("ngayDaiTiec"),kq.getString("COUNT(*)")};
                results1.add(row);
                
            }
            System.out.println(results1);

            JDBCUtil.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
        {
            int ngay = 1;
                if(results1.isEmpty())
                    for(int i = 1; i < 32; i++)
                    {
                        dataset.addValue(0, "Bao cao thang", String.valueOf(i));
                    }
                else            
                {
                    

                    for(int j = 0; j < results1.size(); j++)
                    {
                        LocalDate date = LocalDate.parse(results1.get(j)[0], DateTimeFormatter.ISO_DATE);
                        int dayOfMonth = date.getDayOfMonth();
                        System.out.println(results1.get(j)[0]);
//                        System.out.println("TEst: " + String.valueOf(a0.getThang()));
                        while(ngay != dayOfMonth && dayOfMonth <= 31)
                        {
                            
                            dataset.addValue(0, "Bao cao thang", String.valueOf(ngay));
                            
//                            System.out.println("thêm tháng " + ngay);
                            ngay++;
                        }
                        dataset.addValue(Integer.parseInt(results1.get(j)[1]), "Bao cao thang", String.valueOf(dayOfMonth));
                        ngay++;
                    }

                    while (ngay <= 31) {
                    dataset.addValue(0, "Bao cao thang", String.valueOf(ngay));
                    ngay++;
                    }
                }
        }
        
        if (month == 4 || month == 6 || month == 9 || month == 11)
        {
            int ngay = 1;

                if(results1.isEmpty())
                    for(int i = 1; i < 31; i++)
                    {
                        dataset.addValue(0, "Bao cao thang", String.valueOf(i));
                    }
                else            
                {
                    

                    for(int j = 0; j < results1.size(); j++)
                    {
                        LocalDate date = LocalDate.parse(results1.get(j)[0], DateTimeFormatter.ISO_DATE);
                        int dayOfMonth = date.getDayOfMonth();
//                        System.out.println("TEst: " + String.valueOf(a0.getThang()));
                        while(ngay != dayOfMonth && dayOfMonth <= 30)
                        {
                            dataset.addValue(0, "Bao cao thang", String.valueOf(ngay));
//                            System.out.println("thêm tháng " + ngay);
                            ngay++;
                        }
                        dataset.addValue(Integer.parseInt(results1.get(j)[1]), "Bao cao thang", String.valueOf(dayOfMonth));
                        ngay++;
                    }

                    while (ngay <= 30) {
                    dataset.addValue(0, "Bao cao thang", String.valueOf(ngay));
                    ngay++;
                    }
                }
        }

        if(month == 2)
            {
                boolean isLeapYear = Year.isLeap(year);
                int daysInFebruary = isLeapYear ? 29 : 28;
                int ngay = 1;

                if(results1.isEmpty())
                    for(int i = 1; i < daysInFebruary + 1; i++)
                    {
                        dataset.addValue(0, "Bao cao thang", String.valueOf(i));
                    }
                else            
                {
                    

                    for(int j = 0; j < results1.size(); j++)
                    {
                        LocalDate date = LocalDate.parse(results1.get(j)[0], DateTimeFormatter.ISO_DATE);
                        int dayOfMonth = date.getDayOfMonth();
//                        System.out.println("TEst: " + String.valueOf(a0.getThang()));
                        while(ngay != dayOfMonth && dayOfMonth <= daysInFebruary)
                        {
                            dataset.addValue(0, "Bao cao thang", String.valueOf(ngay));
//                            System.out.println("thêm tháng " + ngay);
                            ngay++;
                        }
                        dataset.addValue(Integer.parseInt(results1.get(j)[1]), "Bao cao thang", String.valueOf(dayOfMonth));
                        ngay++;
                    }

                    while (ngay <= daysInFebruary) {
                    dataset.addValue(0, "Bao cao thang", String.valueOf(ngay));
                    ngay++;
                    }
                }
                
        }
//        dataset.addValue( 10 , "Chi tiet bao cao", "a");
//        dataset.addValue( 20 , "Chi tiet bao cao", "b");
//        dataset.addValue( 30 , "Chi tiet bao cao", "c");
//        dataset.addValue( 40 , "Chi tiet bao cao", "d");
        JFreeChart barChart = ChartFactory.createBarChart(
        "",
        "Ngày" + " (Tháng " + month + ")", "Số lượng tiệc cưới",
        dataset, PlotOrientation.VERTICAL, false, false, false);

        CategoryPlot plot = barChart.getCategoryPlot();
        plot.setBackgroundPaint(new java.awt.Color(215, 212, 212));

        // Tăng kích thước khung plot
        plot.setInsets(new org.jfree.chart.ui.RectangleInsets(0, -5, 0, 0));

//                BarRenderer renderer = (BarRenderer) plot.getRenderer();

//                double max = getMaxValue(dataset);
        double max = 0;
        int rowCount = dataset.getRowCount();
        int columnCount = dataset.getColumnCount();

        for (int column = 0; column < columnCount; column++) {
            double value = dataset.getValue(0, column).doubleValue();
            if (value > max) {
                max = value;
            }
        }

//                // Tăng giới hạn giá trị của trục tung
        ValueAxis rangeAxis = plot.getRangeAxis();
        if(max == 0)
            rangeAxis.setRange(0, 1);
        else
            rangeAxis.setRange(0, max*1.1);

        CategoryAxis domainAxis = plot.getDomainAxis();
//                    domainAxis.setLowerMargin(1.02); // Giảm 10% độ dài trục bên trái
//                    domainAxis.setUpperMargin(1.02);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setSeriesPaint(0, new java.awt.Color(18,98,207));

        ChartPanel panel = new ChartPanel(barChart);
        setContentPane(panel);
    }
    
    public void Message(String message, int messageType) {
        JOptionPane jOptionPane = new JOptionPane(message, messageType);
        JDialog dialog = jOptionPane.createDialog(null, "Message");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
    
    public BarChart_HomePage() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(729, 235));
        setRequestFocusEnabled(false);
        setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 583, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 215, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private double getMaxValue(DefaultCategoryDataset dataset) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
