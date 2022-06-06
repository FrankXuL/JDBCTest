package view;

import dao.Impl.orderDaoImpl;
import entity.order;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class Window extends JFrame {
    orderDaoImpl stuDao = new orderDaoImpl();

    public Window() throws HeadlessException {
        // 设置主界面标题
        this.setTitle("合同管理");
        // 设置窗口弹出居中
        this.setLocationRelativeTo(null);
        // 设置窗口大小
        this.setSize(800, 400);
        // 设置关闭方式为程序退出
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 窗口布局
        this.setLayout(new BorderLayout());
        // 新建面板，并设置网格布局
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(0, 1));
        // 在窗口左侧添加图片
        JLabel label = new JLabel();
        ImageIcon imageIcon = new ImageIcon("src/image/back2.jpg");
        label.setIcon(imageIcon);
        // 新建操作按钮
        JButton searchAllButton = new JButton("查询所有合同");
        JButton searchIdButton = new JButton("编号查询");
        //JButton searchNameButton = new JButton("课程名查询");
        JButton addButton = new JButton("添加合同");
        JButton deleteIdButton = new JButton("按编号删除");
        //JButton deleteNameButton = new JButton("课程名删除");
        JButton updateButton = new JButton("更改合同信息");
        // 设置按钮的大小
        searchAllButton.setSize(50, 50);
        searchIdButton.setSize(50, 50);
        //searchNameButton.setSize(50, 50);
        addButton.setSize(50, 50);
        deleteIdButton.setSize(50, 50);
        //deleteNameButton.setSize(50, 50);
        updateButton.setSize(50, 50);
        // 查询所有课程 按钮 添加动作监听
        searchAllButton.addActionListener(
                e -> {
                    try {
                        // 弹出一个新的查询结果界面
                        new findAll();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                });

        // 为根据Id搜索学生 按钮添加动作监听
        searchIdButton.addActionListener(
                e -> {
                    // 直到点击取消按钮或者叉，关闭窗口
                    while (true) {
                        // 弹出输入框，输入id，类型为String
                        String id = JOptionPane.showInputDialog(null, "请输入合同号");
                        // 如果没有输入，直接点击取消或者叉
                        if (id == null) {
                            // 弹出正在取消按钮
                            JOptionPane.showMessageDialog(null, "正在取消……", "提示", JOptionPane.WARNING_MESSAGE);
                            // 跳出循环，不再弹出窗口
                            break;
                        }
                        // 如果没有输入而点击确认按钮
                        else if (id.equals("")) {
                            // 弹出提示框：请输入课程号
                            JOptionPane.showMessageDialog(null, "未输入合同号！", "提示", JOptionPane.WARNING_MESSAGE);
                            // 继续循环
                            continue;
                        }
                        // 有信息输入
                        else {
                            // 如果输入的是数字串
                            if (isDigital(id)) {
                                order result = null;
                                // 将字符串转换成整形数字，查询数据库
                                orderDaoImpl sd = new orderDaoImpl();
                                result = sd.findId(Integer.parseInt(id));
                                // 成功查询
                                if (result != null) {
                                    // 弹出提示框，展示查询据俄国
                                    JOptionPane.showMessageDialog(
                                            null, result, "查询结果", JOptionPane.WARNING_MESSAGE);
                                } else {
                                    // 没有查询到相关课程
                                    JOptionPane.showMessageDialog(
                                            null, "未查询到该合同", "查询结果", JOptionPane.WARNING_MESSAGE);
                                }
                            }
                            // 如果字符串不是数字串
                            else {
                                // 弹出提示：输入的不是整型
                                JOptionPane.showMessageDialog(
                                        null, "输入的不是整形合同号，请重新输入！", "提示", JOptionPane.WARNING_MESSAGE);
                                continue;
                            }
                        }
                    }
                });

        // 为新增按钮添加动作监听
        addButton.addActionListener(
                e -> new add());

        // 为根据课程号删除  按钮  添加动作监听
        deleteIdButton.addActionListener(
                e -> {
                    while (true) {
                        // 弹出窗口，输入课程号
                        String id = JOptionPane.showInputDialog(null, "请输入要删除的合同号");
                        // 如果点击取消按钮或者叉，弹出正在取消界面
                        if (id == null) {
                            JOptionPane.showMessageDialog(null, "正在取消……", "提示", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                        // 如果没有输入而点击确定，弹出提示框
                        else if (id.equals("")) {
                            JOptionPane.showMessageDialog(null, "未输入合同号！", "提示", JOptionPane.WARNING_MESSAGE);
                            continue;
                        }
                        // 如果有信息输入
                        else {
                            // 输入的字符串是数字串
                            if (isDigital(id)) {
                                boolean result = true;
                                // 创建dao层实例化对象
                                orderDaoImpl sd = new orderDaoImpl();
                                // 执行添加课程方法，返回布尔值
                                sd.delete(Integer.parseInt(id));
                                // 删除成功
                                if (result) {
                                    // 弹出提示框
                                    JOptionPane.showMessageDialog(
                                            null, "已查询到该合同，删除成功！", "查询结果", JOptionPane.WARNING_MESSAGE);
                                } else {
                                    // 没有找到相应的课程  删除失败
                                    JOptionPane.showMessageDialog(
                                            null, "未查询到该合同，删除失败！", "查询结果", JOptionPane.WARNING_MESSAGE);
                                }
                            } else {
                                // 如果输入的不是整形数字串 弹出提示窗口
                                JOptionPane.showMessageDialog(
                                        null, "输入的不是整形合同号，请重新输入！", "提示", JOptionPane.WARNING_MESSAGE);
                                continue;
                            }
                        }
                    }
                });

        // 为  更改课程信息  添加动作监听
        updateButton.addActionListener(
                e -> {
                    // 弹出一个新的窗口
                    new Update();
                });

        // 将各个按钮添加到面板中
        jPanel.add(searchIdButton);
        //jPanel.add(searchNameButton);
        jPanel.add(addButton);
        //jPanel.add(deleteNameButton);
        jPanel.add(deleteIdButton);
        jPanel.add(updateButton);
        jPanel.add(searchAllButton);


        // 将图片标签添加到窗口
        this.add(label);
        // 将面板添加到窗口的右侧
        this.add(jPanel, BorderLayout.EAST);
        // 设置窗口可见
        this.setVisible(true);
    }

    /**
     * 判断字符串是否为整形数字串
     */
    public static boolean isDigital(String string) {
        for (int i = 0; i < string.length(); i++) {
            // 如果不是'0'-'9'，则不是数字串，返回false
            if (!(string.charAt(i) >= 48 && string.charAt(i) <= 57)) {
                return false;
            }
        }
        return true;
    }
}

