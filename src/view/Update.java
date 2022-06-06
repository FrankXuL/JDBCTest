package view;

import dao.Impl.orderDaoImpl;
import entity.order;

import javax.swing.*;
import java.awt.*;

/**
 * @author Administrator 窗口：更新课程信息
 */
public class Update extends JFrame {
    public Update() {
        // 设置窗口弹出位置居中
        this.setLocationRelativeTo(null);
        // 设置窗口名称
        this.setTitle("修改合同信息");
        // 窗口布局
        this.setLayout(new BorderLayout());
        // 声明文本域对象
        JTextField id, newId, newpartya, newpartyb, newdate, newaddress;
        // 提示标签
        JLabel jl0 = new JLabel("原编号：");
        // 用来输入的文本域
        id = new JTextField(10);
        JLabel jl1 = new JLabel("编号:");
        newId = new JTextField(10);
        JLabel jl2 = new JLabel("甲方:");
        newpartya = new JTextField(10);
        JLabel jl3 = new JLabel("乙方:");
        newpartyb = new JTextField(10);
        JLabel jl4 = new JLabel("日期:");
        newdate = new JTextField(10);
        JLabel jl5 = new JLabel("地点:");
        newaddress = new JTextField(10);
        JPanel jp = new JPanel(new GridLayout(6, 2));
        // 将标签和文本域添加到面板中
        jp.add(jl0);
        jp.add(id);
        jp.add(jl1);
        jp.add(newId);
        jp.add(jl2);
        jp.add(newpartya);
        jp.add(jl3);
        jp.add(newpartyb);
        jp.add(jl4);
        jp.add(newdate);
        jp.add(jl5);
        jp.add(newaddress);
        // 确认按钮
        JButton jb = new JButton("确认");
        // 为确认按钮添加动作监听
        jb.addActionListener(
                e -> {
                    // 如果没有输入完整信息，比如课程名没有填写等
                    if (id.getText().equals("")
                            || newId.getText().equals("")
                            || newpartya.getText().equals("")
                            || newpartyb.getText().equals("")
                            || newdate.getText().equals("")
                            || newaddress.getText().equals("")) {
                        // 弹出窗口，提示输入完整信息
                        JOptionPane.showMessageDialog(
                                null, "信息不完整，请填写好信息", "提示", JOptionPane.WARNING_MESSAGE);
                    } else {
                        // 原课程号和课程号输入不是整形数字串
                        if ((!Window.isDigital(id.getText())) || (!Window.isDigital(newId.getText()))|| (!Window.isDigital(newdate.getText()))){
                            JOptionPane.showMessageDialog(
                                    null, "时间or编号输入的不是整形，请重新输入！", "提示", JOptionPane.WARNING_MESSAGE);
                        } else {
                            // 信息输入完成，进行更改操作
                            orderDaoImpl sd = new orderDaoImpl();
                            // 将输入的信息封装成一个Bean对象
                            order neword = new order();
                            neword.setId(Integer.parseInt(newId.getText()));
                            neword.setPartya(newpartya.getText());
                            neword.setPartyb(newpartyb.getText());
                            neword.setDate(Integer.parseInt(newdate.getText()));
                            neword.setAddress(newaddress.getText());
                            boolean result = true;
                            // 执行添加课程方法，返回布尔值
                            sd.update(neword);
                            // 如果修改成功，将输入文本域清空，以便继续修改
                            if (result) {
                                // 弹出提示框，修改成功
                                JOptionPane.showMessageDialog(null, "修改成功！", "结果", JOptionPane.WARNING_MESSAGE);
                                id.setText("");
                                newId.setText("");
                                newpartya.setText("");
                                newpartyb.setText("");
                                newdate.setText("");
                                newaddress.setText("");
                            } else {
                                // 课程表的课程号为主键，因此不能有重复的课程号，这里弹出添加失败
                                JOptionPane.showMessageDialog(
                                        null,
                                        "没有学号为" + id.getText() + "的学生，修改失败！",
                                        "结果",
                                        JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    }
                });
        // 将面板添加进窗口
        this.add(jp);
        // 将按钮添加到窗口底部
        this.add(jb, BorderLayout.SOUTH);
        // 自动调整窗口大小
        this.pack();
        // 设置窗口可见
        this.setVisible(true);
    }
}
