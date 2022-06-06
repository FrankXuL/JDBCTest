package test;

import dao.Impl.orderDaoImpl;
import entity.order;

import java.util.List;

public class jdbctest {
    public static void main(String[] args) {
        orderDaoImpl orderDao = new orderDaoImpl();
        System.out.println("查询所有合同信息");
        List<order> all = orderDao.findAll();
        for (int i = 0; i < all.size(); i++) {
            System.out.println(all.get(i));
        }
        System.out.println("增加合同信息");
        orderDao.add(new order(2, "Frank", "Lily", 2002, "西安"));
        orderDao.add(new order(3, "Smith", "Lily", 2002, "西安"));
        orderDao.add(new order(4, "Frank", "Smith", 2002, "西安"));
        List<order> all1 = orderDao.findAll();
        for (int i = 0; i < all1.size(); i++) {
            System.out.println(all1.get(i));
        }
        System.out.println("删除合同信息");
        orderDao.delete(2);
        List<order> all2 = orderDao.findAll();
        for (int i = 0; i < all2.size(); i++) {
            System.out.println(all2.get(i));
        }
        System.out.println("修改合同信息");
        orderDao.update(new order(3, "小李", "小王", 2001, "上海"));
        List<order> all3 = orderDao.findAll();
        for (int i = 0; i < all3.size(); i++) {
            System.out.println(all3.get(i));
        }
    }
}