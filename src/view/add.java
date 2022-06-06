package view;

import dao.Impl.orderDaoImpl;
import entity.order;

import javax.swing.*;
import java.awt.*;

/**
 *  @author Administrator
 *  窗口：添加合同
 *  */
public class add extends JFrame {
    public add() {
        // 设置窗口弹出位置居中
        this.setLocationRelativeTo(null);
        // 设置窗口名称
        this.setTitle("添加合同信息");
        JTextField id, partya, partyb, date,address;
        // 提示标签
        JLabel jl1 = new JLabel("编号:");
        // 输入框
        id = new JTextField(10);
        JLabel jl2 = new JLabel("甲方:");
        partya = new JTextField(10);
        JLabel jl3 = new JLabel("乙方:");
        partyb = new JTextField(10);
        JLabel jl4 = new JLabel("日期:");
        date = new JTextField(10);
        JLabel jl5 = new JLabel("地点:");
        address = new JTextField(10);
        // 设置主面板为4*2的网格布局
        JPanel jp = new JPanel(new GridLayout(5, 2));
        // 将各个组件加入到面板中
        jp.add(jl1);
        jp.add(id);
        jp.add(jl2);
        jp.add(partya);
        jp.add(jl3);
        jp.add(partyb);
        jp.add(jl4);
        jp.add(date);
        jp.add(jl5);
        jp.add(address);
        JButton jb = new JButton("确认");
        JLabel jlinfo;
        jlinfo = new JLabel("合同信息", JLabel.CENTER);
        // 为“确认”按钮添加动作监听
        jb.addActionListener(
                e -> {
                    // 如果没有输入完整信息
                    if (id.getText().equals("")
                            || partya.getText().equals("")
                            || partyb.getText().equals("")
                            || date.getText().equals("")
                            || address.getText().equals("")) {
                        // 弹出窗口，提示输入完整信息
                        JOptionPane.showMessageDialog(
                                null, "信息不完整，请填写好信息", "提示", JOptionPane.WARNING_MESSAGE);
                    } else {
                        if ((!Window.isDigital(id.getText())) || (!Window.isDigital(date.getText()))) {
                            JOptionPane.showMessageDialog(
                                    null, "输入的不是整形，请重新输入！", "提示", JOptionPane.WARNING_MESSAGE);
                        }else{
                            // 将输入的信息封装成对象
                            order order = new order();
                            order.setId(Integer.parseInt(id.getText()));
                            order.setPartya(partya.getText());
                            order.setPartyb(partyb.getText());
                            order.setDate(Integer.parseInt(date.getText()));
                            order.setAddress(address.getText());
                            boolean result = true;
                            // 创建dao层实例化对象
                            orderDaoImpl sd = new orderDaoImpl();
                            // 执行添加方法，返回布尔值
                            sd.add(order);
                            // 如果添加成功，将文本域清空，以便继续添加
                            if (result) {
                                // 弹出添加成功提示框
                                JOptionPane.showMessageDialog(null, "添加成功！", "结果", JOptionPane.WARNING_MESSAGE);
                                id.setText("");
                                partya.setText("");
                                partyb.setText("");
                                date.setText("");
                                address.setText("");
                            } else {
                                // 课程表的合同号为主键，因此不能有重复的合同号，这里弹出添加失败
                                JOptionPane.showMessageDialog(
                                        null, "已有编号为" + id.getText() + "的合同，添加失败！", "结果", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    }
                });

        //添加面板
        this.add(jp);
        this.add(jlinfo, BorderLayout.NORTH);
        //在底部添加确认按钮
        this.add(jb, BorderLayout.SOUTH);
        //窗口自动调整大小
        this.pack();
        //设置窗口可见
        this.setVisible(true);
    }
}
