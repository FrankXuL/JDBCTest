package dao;

import entity.order;

import java.util.List;
import java.util.Map;

public interface orderDao {

    public List<order> findAll();
    //增加
    public void add(order order);
    //查找
    public order findId(int id);
    //修改
    public void update(order order);
    //删除
    public void delete(int id);
    //
    public int findTotalCount(Map<String, String[]> parameterMap);
    //
    public List<order> findByPage(int start, int rows, Map<String, String[]> parameterMap);
    //
}
