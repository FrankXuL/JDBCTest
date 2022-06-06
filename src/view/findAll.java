package view;

import dao.Impl.orderDaoImpl;
import entity.order;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

/*
 * @author Administrator
 * 窗口：查询所有的合同并显示
 */

public class findAll extends JFrame {
    orderDaoImpl stuDao = new orderDaoImpl();

    public findAll() throws SQLException {
        //设置窗口弹出位置居中
        this.setLocationRelativeTo(null);
        //设置窗口名称
        this.setTitle("查询所有合同");
        //窗口布局
        this.setLayout(new BorderLayout());
        JPanel jPanel = new JPanel();
        //面板布局
        jPanel.setLayout(new BorderLayout());
        //利用表格来展示查询到的数据库结果
        JTable jTable = getTable();
        //表格分成表头和表尾来添加到面板
        jPanel.add(jTable.getTableHeader(), BorderLayout.NORTH);
        jPanel.add(jTable, BorderLayout.CENTER);
        jPanel.add(jTable);
        this.add(jPanel);
        //窗口自动调整大小
        this.pack();
        //设置窗口可见
        this.setVisible(true);
    }

    public JTable getTable() {
        List<order> lists;
        orderDaoImpl sd=new orderDaoImpl();
        //从数据库查询所有课程，返回一个List
        lists = sd.findAll();
        //表格的列名
        Object[] columnNames = {"编号", "甲方", "乙方", "时间","地点"};
        //表格的数据区域（表尾）
        Object[][] data = new Object[lists.size()][5];
        for (int i = 0; i < lists.size(); i++) {
            data[i][0] = lists.get(i).getId();
            data[i][1] = lists.get(i).getPartya();
            data[i][2] = lists.get(i).getPartyb();
            data[i][3] = lists.get(i).getDate();
            data[i][4] = lists.get(i).getAddress();
        }
        JTable jTable = new JTable(data, columnNames);
        //设置表格数据居中显示
        DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
        defaultTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        jTable.setDefaultRenderer(Object.class, defaultTableCellRenderer);
        return jTable;
    }
}
