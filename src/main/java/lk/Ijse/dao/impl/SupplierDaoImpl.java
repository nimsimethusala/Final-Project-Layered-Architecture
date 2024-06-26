package lk.Ijse.dao.impl;

import lk.Ijse.dao.SQLUtil;
import lk.Ijse.dao.SupplierDAO;
import lk.Ijse.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoImpl implements SupplierDAO {
    @Override
    public boolean delete(String supId) throws SQLException, ClassNotFoundException {
        /*String sql = "DELETE FROM supplier WHERE supplier_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, supId);

        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("DELETE FROM supplier WHERE supplier_id = ?", supId);
    }

    @Override
    public boolean save(Supplier supplier) throws SQLException, ClassNotFoundException {
        /*String sql = "INSERT INTO supplier VALUES(supplier_id = ?, name = ?, location = ?, contact = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, supplier.getSupplierId());
        pstm.setObject(2, supplier.getName());
        pstm.setObject(3, supplier.getContact());
        pstm.setObject(4, supplier.getLocation());

        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("INSERT INTO supplier VALUES(supplier_id = ?, name = ?, location = ?, contact = ?", supplier);
    }

    @Override
    public boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        /*String sql = "UPDATE supplier SET name = ?, location = ?, contact = ? WHERE supplier_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, supplier.getSupplierId());
        pstm.setObject(2, supplier.getName());
        pstm.setObject(3, supplier.getContact());
        pstm.setObject(4, supplier.getLocation());

        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("UPDATE supplier SET name = ?, location = ?, contact = ? WHERE supplier_id = ?", supplier);
    }

    @Override
    public List<Supplier> getAll() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT * FROM supplier";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM supplier");

        List<Supplier> supplierList = new ArrayList<>();

        while (resultSet.next()){
            String supId = resultSet.getString(1);
            String supName = resultSet.getString(2);
            String location = resultSet.getString(3);
            int contact = resultSet.getInt(4);

            Supplier supplier = new Supplier(supId, supName, contact, location);
            supplierList.add(supplier);
        }

        return supplierList;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT supplier_id FROM supplier ORDER BY supplier_id DESC LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/

        ResultSet resultSet = SQLUtil.execute("SELECT supplier_id FROM supplier ORDER BY supplier_id DESC LIMIT 1");

        if(resultSet.next()) {
            return splitSupplierId(resultSet.getString(1));
        }
        return splitSupplierId(null);
    }

    private String splitSupplierId(String string) {
        if(string != null) {
            String[] strings = string.split("S0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "S00"+id;
            }else {
                if (length < 3){
                    return "S0"+id;
                }else {
                    return "S"+id;
                }
            }
        }
        return "S001";
    }

    @Override
    public Supplier searchById(String id) throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT * FROM supplier WHERE supplier_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);*/

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM supplier WHERE supplier_id = ?", id);
        if (resultSet.next()) {
            String supId = resultSet.getString(1);
            String supName = resultSet.getString(2);
            String location = resultSet.getString(3);
            int contact = resultSet.getInt(4);

            Supplier supplier = new Supplier(supId, supName, contact, location);

            return supplier;
        }
        return null;
    }

    @Override
    public List<String> getId() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT supplier_id FROM supplier";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/

        ResultSet resultSet = SQLUtil.execute("SELECT supplier_id FROM supplier");

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    @Override
    public String getName(String cusId) throws SQLException, ClassNotFoundException {
        return null;
    }
}
