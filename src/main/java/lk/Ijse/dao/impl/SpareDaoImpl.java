package lk.Ijse.dao.impl;

import lk.Ijse.dao.SQLUtil;
import lk.Ijse.dao.SpareDAO;
import lk.Ijse.entity.Spares;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpareDaoImpl implements SpareDAO {
    @Override
    public boolean delete(String spareId) throws SQLException, ClassNotFoundException {
        /*String sql = "DELETE FROM spare WHERE Spare_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, spareId);

        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("DELETE FROM spare WHERE Spare_id = ?", spareId);
    }

    @Override
    public boolean save(Spares spares) throws SQLException, ClassNotFoundException {
        /*String sql = "INSERT INTO spare VALUES(?, ?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, spares.getSpareId());
        pstm.setObject(2,spares.getName());
        pstm.setObject(3, spares.getCount());
        pstm.setObject(4, spares.getPrice());
        pstm.setObject(5, spares.getSupplierId());

        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("INSERT INTO spare VALUES(?, ?, ?, ?, ?)", spares);
    }

    @Override
    public boolean update(Spares spares) throws SQLException, ClassNotFoundException {
        /*String sql = "UPDATE spare SET Name = ?, count = ?, price = ?, supplier_id = ? WHERE Spare_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, spares.getName());
        pstm.setObject(2, spares.getCount());
        pstm.setObject(3, spares.getPrice());
        pstm.setObject(4, spares.getSupplierId());
        pstm.setObject(5, spares.getSpareId());

        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("UPDATE spare SET Name = ?, count = ?, price = ?, supplier_id = ? WHERE Spare_id = ?", spares);
    }

    @Override
    public List<Spares> getAll() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT * FROM spare";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM spare");

        List<Spares> spareList = new ArrayList<>();

        while (resultSet.next()){
            String spareId = resultSet.getString(1);
            String name = resultSet.getString(2);
            int count = resultSet.getInt(3);
            double price = resultSet.getDouble(4);
            String supplierId = resultSet.getString(5);

            Spares spares = new Spares(spareId, name, count, price, supplierId);
            spareList.add(spares);
        }

        return spareList;
    }

    @Override
    public List<String> getId() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT Spare_id FROM spare";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/

        ResultSet resultSet = SQLUtil.execute("SELECT Spare_id FROM spare");

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    @Override
    public String getName(String spareId) throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT Name FROM spare WHERE Spare_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, spareId);*/

        ResultSet resultSet = SQLUtil.execute("SELECT Name FROM spare WHERE Spare_id = ?", spareId);

        while (resultSet.next()){
            String spareName = resultSet.getString(1);

            return spareName;
        }
        return null;
    }

    @Override
    public boolean update(Job job) throws SQLException {
        boolean isUpdateCount = updateQty(job.getSpareId(), job.getSpareCount());

        if(!isUpdateCount) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateQty(String spareId, int spareCount) throws SQLException, ClassNotFoundException {
        /*String sql = "UPDATE spare SET count = count - ? WHERE Spare_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1, spareCount);
        pstm.setString(2, spareId);

        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("UPDATE spare SET count = count - ? WHERE Spare_id = ?", spareId, spareCount);
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT Spare_id FROM spare ORDER BY Spare_id DESC LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/

        ResultSet resultSet = SQLUtil.execute("SELECT Spare_id FROM spare ORDER BY Spare_id DESC LIMIT 1");

        if(resultSet.next()) {
            return splitSpareId(resultSet.getString(1));
        }
        return splitSpareId(null);
    }

    public String splitSpareId(String string) {
        if(string != null) {
            String[] strings = string.split("SP0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "SP00"+id;
            }else {
                if (length < 3){
                    return "SP0"+id;
                }else {
                    return "SP"+id;
                }
            }
        }
        return "SP001";
    }

    @Override
    public Spares searchById(String id) throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT * FROM spare WHERE Spare_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);*/

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM spare WHERE Spare_id = ?", id);
        if (resultSet.next()) {
            String spareId = resultSet.getString(1);
            String name = resultSet.getString(2);
            int count = resultSet.getInt(3);
            double price = resultSet.getDouble(4);
            String supplierId = resultSet.getString(5);

            Spares spares = new Spares(spareId, name, count, price,supplierId);

            return spares;
        }
        return null;
    }
}
