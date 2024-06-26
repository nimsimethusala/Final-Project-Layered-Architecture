package lk.Ijse.dao.impl;

import lk.Ijse.dao.ItemDAO;
import lk.Ijse.dao.SQLUtil;
import lk.Ijse.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDAO {
    public boolean delete(String itemId) throws SQLException, ClassNotFoundException {
        /*String sql = "DELETE FROM item WHERE item_No = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, itemId);

        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("DELETE FROM item WHERE item_No = ?", itemId);
    }

    public List<String> getId() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT item_No FROM item";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/

        ResultSet resultSet = SQLUtil.execute("SELECT item_No FROM item");

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    public String getName(String itemId) throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT Name FROM item WHERE item_No = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, itemId);*/

        ResultSet resultSet = SQLUtil.execute("SELECT Name FROM item WHERE item_No = ?", itemId);
        if (resultSet.next()){
            String itemName = resultSet.getString(1);

            return itemName;
        }

        return null;
    }

    public boolean update(List<JobDetail> jobList) throws SQLException {
        for (JobDetail list : jobList) {
            boolean isUpdateCount = updateQty(list.getItemId(), list.getItemCount());

            if(!isUpdateCount) {
                return false;
            }
        }
        return true;
    }

    public boolean updateQty(String itemId, int itemCount) throws SQLException, ClassNotFoundException {
        /*String sql = "UPDATE item SET Item_count = Item_count - ? WHERE item_No = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1, itemCount);
        pstm.setString(2, itemId);

        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("UPDATE item SET Item_count = Item_count - ? WHERE item_No = ?", itemId, itemCount);
    }

    public boolean save(Item item) throws SQLException, ClassNotFoundException {
        /*String sql = "INSERT INTO item VALUES(?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, item.getItemID());
        pstm.setObject(2, item.getItemName());
        pstm.setObject(3, item.getItemCount());
        pstm.setObject(4, item.getDefectId());

        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("INSERT INTO item VALUES(?, ?, ?, ?)", item);
    }

    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        /*String sql = "UPDATE item SET Name = ?, defect_id = ?, Item_count = ? WHERE item_No = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, item.getItemName());
        pstm.setObject(2, item.getDefectId());
        pstm.setObject(3, item.getItemCount());
        pstm.setObject(4, item.getItemID());

        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("UPDATE item SET Name = ?, defect_id = ?, Item_count = ? WHERE item_No = ?", item);
    }

    public List<Item> getAll() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT * FROM item";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM item");

        List<Item> itemList = new ArrayList<>();

        while (resultSet.next()){
            String itemId = resultSet.getString(1);
            String itemName = resultSet.getString(2);
            int itemCount = resultSet.getInt(3);
            String defectId = resultSet.getString(4);

            Item item = new  Item(itemId,itemName, itemCount, defectId);
            itemList.add(item);
        }

        return itemList;
    }

    public String generateNextId() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT item_No FROM item ORDER BY item_No DESC LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/

        ResultSet resultSet = SQLUtil.execute("SELECT item_No FROM item ORDER BY item_No DESC LIMIT 1");

        if(resultSet.next()) {
            return splitItemId(resultSet.getString(1));
        }
        return splitItemId(null);
    }

    public String splitItemId(String string) {
        if(string != null) {
            String[] strings = string.split("I0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "I00"+id;
            }else {
                if (length < 3){
                    return "I0"+id;
                }else {
                    return "I"+id;
                }
            }
        }
        return "I001";
    }

    public Item searchById(String id) throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT * FROM item WHERE item_No = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);*/

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM item WHERE item_No = ?", id);
        if (resultSet.next()) {
            String itemId = resultSet.getString(1);
            String itemName = resultSet.getString(2);
            int itemCount = resultSet.getInt(3);
            String defectId = resultSet.getString(4);

            Item item = new  Item(itemId, itemName, itemCount, defectId);

            return item;
        }
        return null;
    }
}
