package lk.Ijse.dao.impl;

import lk.Ijse.dao.DefectDAO;
import lk.Ijse.dao.SQLUtil;
import lk.Ijse.db.DbConnection;
import lk.Ijse.entity.Defect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefectDaoImpl implements DefectDAO {
    @Override
    public boolean delete(String defectId) throws SQLException, ClassNotFoundException {
        /*String sql = "DELETE FROM defect WHERE defect_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, defectId);

        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("DELETE FROM defect WHERE defect_id = ?", defectId);
    }

    @Override
    public boolean save(Defect defect) throws SQLException, ClassNotFoundException {
        /*String sql = "INSERT INTO defect VALUES(?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, defect.getDefectId());
        pstm.setObject(2, defect.getDescription());
        pstm.setObject(3, defect.getPrice());
        pstm.setObject(4, defect.getSpareId());

        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("INSERT INTO defect VALUES(?, ?, ?, ?)", defect);
    }

    @Override
    public boolean update(Defect defect) throws SQLException, ClassNotFoundException {
        /*String sql = "UPDATE defect SET description = ?, price = ?, Spare_id = ? WHERE defect_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, defect.getDescription());
        pstm.setObject(2, defect.getPrice());
        pstm.setObject(3, defect.getSpareId());
        pstm.setObject(4, defect.getDefectId());

        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("UPDATE defect SET description = ?, price = ?, Spare_id = ? WHERE defect_id = ?",
                defect.getDescription(),
                defect.getPrice(),
                defect.getSpareId(),
                defect.getDefectId());
    }

    @Override
    public ArrayList<Defect> getAll() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT * FROM defect";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM defect");
        ArrayList<Defect> defectList = new ArrayList<>();

        while (resultSet.next()){
            String defectId = resultSet.getString(1);
            String desc = resultSet.getString(2);
            double price = resultSet.getDouble(3);
            String spareId = resultSet.getString(4);

            Defect defect = new Defect(defectId, desc, price, spareId);
            defectList.add(defect);
        }

        return defectList;
    }

    @Override
    public String getDescription(String defectId) throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT description FROM defect WHERE defect_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, defectId);*/

        ResultSet resultSet = SQLUtil.execute("SELECT description FROM defect WHERE defect_id = ?", defectId);
        while (resultSet.next()){
            String desc = resultSet.getString(1);

            return desc;
        }

        return null;
    }

    @Override
    public List<String> getId() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT defect_id FROM defect";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/

        ResultSet resultSet = SQLUtil.execute("SELECT defect_id FROM defect");

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT defect_id FROM defect ORDER BY defect_id DESC LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/

        ResultSet resultSet = SQLUtil.execute("SELECT defect_id FROM defect ORDER BY defect_id DESC LIMIT 1");

        if(resultSet.next()) {
            return splitDefectId(resultSet.getString(1));
        }
        return splitDefectId(null);
    }

    private String splitDefectId(String string) {
        if(string != null) {
            String[] strings = string.split("D0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "D00"+id;
            }else {
                if (length < 3){
                    return "D0"+id;
                }else {
                    return "D"+id;
                }
            }
        }
        return "D001";
    }

    @Override
    public Defect searchById(String tel) throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT * FROM defect WHERE defect_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, tel);*/

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM defect WHERE defect_id = ?", tel);
        if (resultSet.next()) {
            String defectId = resultSet.getString(1);
            String defectName = resultSet.getString(2);
            double price = resultSet.getDouble(3);
            String spareId = resultSet.getString(4);

            Defect defect = new Defect(defectId, defectName, price, spareId);

            return defect;
        }
        return null;
    }

    @Override
    public String getName(String cusId) throws SQLException, ClassNotFoundException {
        return null;
    }
}
