package lk.Ijse.dao.impl;

import lk.Ijse.dao.CustomerDAO;
import lk.Ijse.dao.SQLUtil;
import lk.Ijse.db.DbConnection;
import lk.Ijse.dto.CustomerDTO;
import lk.Ijse.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDAO {
    @Override
    public boolean save(Customer customer) throws SQLException, ClassNotFoundException {
        /*String sql = "INSERT INTO customer VALUES(?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, customer.getId());
        pstm.setObject(2, customer.getName());
        pstm.setObject(3, customer.getAddress());
        pstm.setObject(4, customer.getTel());

        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("INSERT INTO customer VALUES(?, ?, ?, ?)", customer.getId(), customer.getName(), customer.getAddress(), customer.getTel());
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        /*String sql = "UPDATE customer SET customer_name = ?, address = ?, contact = ? WHERE customer_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, customer.getName());
        pstm.setObject(2, customer.getAddress());
        pstm.setObject(3, customer.getTel());
        pstm.setObject(4, customer.getId());

        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("UPDATE customer SET customer_name = ?, address = ?, contact = ? WHERE customer_id = ?",
                customer.getName(),
                customer.getAddress(),
                customer.getTel(),
                customer.getId()
        );
    }

    @Override
    public boolean delete(String cusId) throws SQLException, ClassNotFoundException {
        /*String sql = "DELETE FROM customer WHERE customer_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, cusId);

        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("DELETE FROM customer WHERE customer_id = ?", cusId);
    }

    @Override
    public Customer searchById(String tel) throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT * FROM customer WHERE contact = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, tel);*/

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE contact = ?", tel);

        if (resultSet.next()) {
            String cusId = resultSet.getString(1);
            String cusName = resultSet.getString(2);
            String address = resultSet.getString(3);
            int contact = Integer.parseInt(resultSet.getString(4));

            Customer customer = new Customer(cusId, cusName, address, contact);

            return customer;
        }
        return null;
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT * FROM customer";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer");

        ArrayList<Customer> customerList = new ArrayList<>();

        while (resultSet.next()){
            String cusId = resultSet.getString(1);
            String cusName = resultSet.getString(2);
            String address = resultSet.getString(3);
            int contact = resultSet.getInt(4);

            Customer customer = new Customer(cusId, cusName, address, contact);
            customerList.add(customer);
        }

        return customerList;
    }

    @Override
    public List<String> getId() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT customer_id FROM customer";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/

        ResultSet resultSet = SQLUtil.execute("SELECT customer_id FROM customer");

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    @Override
    public String getName(String cusId) throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT * FROM customer WHERE customer_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, cusId);*/

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE customer_id = ?", cusId);
        if (resultSet.next()){
            String cusName = resultSet.getString(2);

            return cusName;
        }
        return null;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT customer_id FROM customer ORDER BY customer_id DESC LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/

        ResultSet resultSet = SQLUtil.execute("SELECT customer_id FROM customer ORDER BY customer_id DESC LIMIT 1");

        if(resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private String splitOrderId(String string) {
        if(string != null) {
            String[] strings = string.split("C0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "C00"+id;
            }else {
                if (length < 3){
                    return "C0"+id;
                }else {
                    return "C"+id;
                }
            }
        }
        return "C001";
    }
}
