package lk.Ijse.bo;

import lk.Ijse.dto.ItemDTO;

import java.sql.SQLException;
import java.util.List;

public interface ItemBO extends SuperBO{
    public boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    public boolean deleteItem(String cusId) throws SQLException, ClassNotFoundException;

    public ItemDTO searchByItemId(String tel) throws SQLException, ClassNotFoundException;

    public List<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;

    public List<String> getItemId() throws SQLException, ClassNotFoundException;

    public String getItemName(String cusId) throws SQLException, ClassNotFoundException;

    public String generateNextItemId() throws SQLException, ClassNotFoundException;

    public boolean update(List<JobDetail> jobList) throws SQLException;

    public boolean updateItemQty(String itemId, int itemCount) throws SQLException, ClassNotFoundException;
}
