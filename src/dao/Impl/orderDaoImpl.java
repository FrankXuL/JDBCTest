package dao.Impl;

import dao.orderDao;
import entity.order;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class orderDaoImpl implements orderDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<order> findAll() {
        //使用JDBC操作数据库
        //1.定义sql
        List<order> ords;
        String sql = "SELECT * FROM `order`";
        ords = template.query(sql, new BeanPropertyRowMapper<>(order.class));

        return ords;
    }

    @Override
    public void add(order order) {
        //1.定义sql语句
        String sql = "insert into `order` (`id`,`partya`,`partyb`,`date`,`address`) value (?,?,?,?,?)";
        //2.执行sql
        template.update(sql, order.getId(), order.getPartya(), order.getPartyb(), order.getDate(), order.getAddress());
    }

    @Override
    public order findId(int id) {
        //1.定义sql
        String sql = "select * from `order` where id = ?";
        //2.执行sql语句,将信息转为对象
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(order.class), id);
    }

    @Override
    public void update(order order) {
        //1.定义sql
        String sql = "update `order` set id = ?, partya = ?, partyb = ?, date = ? ,address = ? where id = ?";
        //2.执行sql语句,将信息转为对象
        template.update(sql, order.getId(), order.getPartya(), order.getPartyb(), order.getDate(), order.getAddress(), order.getId());
    }

    @Override
    public void delete(int id) {
        //1.定义sql
        String sql = "delete from `order` where id = ?";
        //2.执行sql语句
        template.update(sql, id);
    }


    @Override
    public int findTotalCount(Map<String, String[]> parameterMap) {
        //1.定义模板初始化sql
        String sql = "select count(*) from `order` where 1 = 1";
        StringBuilder sb = new StringBuilder(sql);

        //2.遍历map
        Set<String> keySet = parameterMap.keySet();
        //定义一个参数集合
        List<Object> params = new ArrayList<>();
        for (String key : keySet) {
            //排除分页参数
            if ("currentPage".equals(key) || "rows".equals(key)) {
                continue;
            }
            //获取value
            String value = parameterMap.get(key)[0];
            //判断value是否有值
            if (value != null && !"".equals(value)) {
                //有值
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");//?条件的值
            }
        }
        return template.queryForObject(sb.toString(), Integer.class, params.toArray());
    }

    @Override
    public List<order> findByPage(int start, int rows, Map<String, String[]> parameterMap) {
        //1.定义sql
        //1.定义模板初始化sql
        String sql = "select * from `order` where 1 = 1";
        StringBuilder sb = new StringBuilder(sql);

        //2.遍历map
        Set<String> keySet = parameterMap.keySet();
        //定义一个参数集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {
            //排除分页参数
            if ("currentPage".equals(key) || "rows".equals(key) || "id".equals(key)) {
                continue;
            }
            //获取value
            String value = parameterMap.get(key)[0];
            //判断value是否有值
            if (value != null && !"".equals(value)) {
                //有值
                sb.append(" and " + key + " like ? ");
                params.add("%" + value + "%");//?条件的值
            }
        }

        //添加分页查询
        sb.append(" limit ?,?");
        //添加分页查询参数值
        params.add(start);
        params.add(rows);
        List<order> query = template.query(sb.toString(), new BeanPropertyRowMapper<>(order.class), params.toArray());
        //2.执行sql语句
        return query;
    }


}
