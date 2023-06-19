/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import dao.ChiTietBaoCaoDAO;
import dao.BaoCaoDoanhThuDAO;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import model.ChiTietBaoCao;
import model.BaoCaoDoanhThu;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;



import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author ACER
 */
public class LineChart extends javax.swing.JInternalFrame {

    /**
     * Creates new form LineChartExample
     */
    public LineChart(int NumberType, int month, int year, int year1) {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI)this.getUI();
        ui.setNorthPane(null);
        switch (NumberType) {
            case 0:
            {
                ArrayList<ChiTietBaoCao> ctbc = ChiTietBaoCaoDAO.getInstance().SelectByMonth(month, year);
                
                if(ctbc.size() <= 10)
                {

                    DefaultPieDataset dataset = new DefaultPieDataset();
                    for(ChiTietBaoCao a0 : ctbc)
                    {   
                        dataset.setValue("Ngày " + a0.getNgay().substring(a0.getNgay().length()-2), (double) (a0.getTiLe()*100));
                    }
                    JFreeChart chart = ChartFactory.createPieChart(
                            "", dataset, true, true, false);
                    PiePlot plot = (PiePlot) chart.getPlot();
                    DecimalFormat df = new DecimalFormat("0.00%");
                    NumberFormat nf = NumberFormat.getPercentInstance(Locale.FRANCE);
                    df.setMaximumFractionDigits(2);
                    plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {2}",df, df));
                    plot.setLabelBackgroundPaint(Color.PINK);
                    ChartPanel panel = new ChartPanel(chart);  
                    setContentPane(panel);

                }
                else
                {
                    final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                    for(ChiTietBaoCao a0 : ctbc)
                    {
                        dataset.addValue( Math.round(a0.getTiLe()*100*10)/10.0 , "Chi tiet bao cao", a0.getNgay().substring(a0.getNgay().length()-2));
                    }
                    JFreeChart barChart = ChartFactory.createBarChart(
                    "",
                    "Tháng", "Doanh thu (Triệu)",
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
                
                
                break;
            }
            case 1:
            {
                ArrayList<BaoCaoDoanhThu> bcdt = BaoCaoDoanhThuDAO.getInstance().SelectByYear(year);
//                DefaultPieDataset dataset = new DefaultPieDataset();
//                for(BaoCaoDoanhThu a0 : bcdt)
//                {
////                    dataset.addValue(a0.getDoanhThu()/1000000, "Thang 3", a0.getNgay().substring(a0.getNgay().length()-2));
//                    dataset.setValue("Tháng " + a0.getThang(), new Double(a0.getTongDoanhThu()/a0.getTongThang()*100));
//                }
//
//                JFreeChart chart = ChartFactory.createPieChart(
//                        "", dataset, true, true, false);
//                PiePlot plot = (PiePlot) chart.getPlot();
//                plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: ({2})"));
//                plot.setLabelBackgroundPaint(Color.PINK);
//                ChartPanel panel = new ChartPanel(chart);  
//                setContentPane(panel);
                final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                int MonthValue = 1;
                System.out.println("Số phần tử: " + bcdt.size());
                if(bcdt.isEmpty())
                    for(int i = 1; i < 13; i++)
                    {
                        dataset.addValue(0.0, "Bao cao thang", String.valueOf(i));
                    }
                else            
                {
                    

                    for(BaoCaoDoanhThu a0 : bcdt)
                    {
                        System.out.println("TEst: " + String.valueOf(a0.getThang()));
                        while(MonthValue != a0.getThang() && MonthValue <= 12)
                        {
                            dataset.addValue(0.0, "Bao cao thang", String.valueOf(MonthValue));
                            System.out.println("thêm tháng " + MonthValue);
                            MonthValue++;
                        }
                        dataset.addValue(a0.getTongDoanhThu()/1000000.0, "Bao cao thang", String.valueOf(a0.getThang()));
                        MonthValue++;
                    }

                    while (MonthValue <= 12) {
                    dataset.addValue(0, "Bao cao thang", String.valueOf(MonthValue));
                    MonthValue++;
                    }
                }
                                   
                JFreeChart barChart = ChartFactory.createBarChart(
                "",
                "Tháng", "Doanh thu (Triệu)",
                dataset, PlotOrientation.VERTICAL, false, false, false);

                CategoryPlot plot = barChart.getCategoryPlot();
                plot.setBackgroundPaint(new java.awt.Color(215, 212, 212));
                
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

                BarRenderer renderer = (BarRenderer) plot.getRenderer();
                renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
                renderer.setDefaultItemLabelsVisible(true);
                renderer.setSeriesPaint(0, new java.awt.Color(18,98,207));
                
                ChartPanel panel = new ChartPanel(barChart);
                setContentPane(panel);
                
                
                
                break;
            }
            case 2:
            {
                ArrayList<BaoCaoDoanhThu> bcdt = BaoCaoDoanhThuDAO.getInstance().SelectByFromY1toY2(year, year1);
                final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                if(bcdt.size() == 0)
                    for(int i = year; i < year1; i++)
                    {
                        dataset.addValue(0.0, "Bao cao nam", String.valueOf(i));
                    }
                else
                {
                    for(int i=year; i<=year1;i++)
                    {
                        for(BaoCaoDoanhThu a0 : bcdt)
                        {
                            while(i != a0.getNam() && i <= year1)
                            {
                                dataset.addValue(0.0, "Bao cao nam", String.valueOf(i));
                                i++;
                            }
                            dataset.addValue(a0.getTongNam()/1000000.0, "Bao cao nam", String.valueOf(a0.getNam()));
                            i++;
                        }
                        if(i<=year1)
                            dataset.addValue(0.0, "Bao cao nam", String.valueOf(i));
                    }
//                    for(BaoCaoDoanhThu a0 : bcdt)
//                    {
////                        System.out.println("TEst: " + String.valueOf(a0.getNam()));
//                        while(yearValue != a0.getNam() && yearValue <= year1)
//                        {
////                            System.out.println("TEst for: " + String.valueOf(yearValue));
//                            dataset.addValue(0.0, "Bao cao nam", String.valueOf(yearValue));
//                            yearValue++;  
//                        }
//                        dataset.addValue(a0.getTongNam()/1000000.0, "Bao cao nam", String.valueOf(a0.getNam()));
//                        yearValue++;
//                    }
                }
                JFreeChart barChart = ChartFactory.createBarChart(
                "",
                "Năm", "Doanh thu (Triệu)",
                dataset, PlotOrientation.VERTICAL, false, false, false);

                CategoryPlot plot = barChart.getCategoryPlot();
                plot.setBackgroundPaint(new java.awt.Color(215, 212, 212));
                
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

                BarRenderer renderer = (BarRenderer) plot.getRenderer();
                renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
                renderer.setDefaultItemLabelsVisible(true);
                renderer.setSeriesPaint(0, new java.awt.Color(18,98,207));
                
                ChartPanel panel = new ChartPanel(barChart);
                setContentPane(panel);
                break;
            }               
            default:
                throw new AssertionError();
        }
    }
    
    public void Message(String message, int messageType) {
        JOptionPane jOptionPane = new JOptionPane(message, messageType);
        JDialog dialog = jOptionPane.createDialog(null, "Message");
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
    
    public LineChart() {
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
        setPreferredSize(new java.awt.Dimension(635, 320));
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
            .addGap(0, 294, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private double getMaxValue(DefaultCategoryDataset dataset) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
