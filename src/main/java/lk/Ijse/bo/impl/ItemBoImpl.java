package lk.Ijse.bo.impl;

import lk.Ijse.bo.ItemBO;
import lk.Ijse.dao.DAOFactory;
import lk.Ijse.dao.ItemDAO;
import lk.Ijse.dao.impl.ItemDaoImpl;
import lk.Ijse.dto.ItemDTO;
import lk.Ijse.dto.JobDetailDTO;
import lk.Ijse.entity.Item;
import lk.Ijse.entity.JobDetail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBoImpl implements ItemBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(
                itemDTO.getItemID(),
                itemDTO.getItemName(),
                itemDTO.getItemCount(),
                itemDTO.getDefectId()
        ));
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(
                itemDTO.getItemID(),
                itemDTO.getItemName(),
                itemDTO.getItemCount(),
                itemDTO.getDefectId()
        ));
    }

    @Override
    public boolean deleteItem(String cusId) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(cusId);
    }

    @Override
    public ItemDTO searchByItemId(String tel) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.searchById(tel);
        return new ItemDTO(item.getItemID(), item.getItemName(), item.getItemCount(), item.getDefectId());
    }

    @Override
    public List<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> items = itemDAO.getAll();
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();

        for (Item item : items){
            ItemDTO itemDTO = new ItemDTO(item.getItemID(), item.getItemName(), item.getItemCount(), item.getDefectId());
            itemDTOS.add(itemDTO);
        }
        return itemDTOS;
    }

    @Override
    public List<String> getItemId() throws SQLException, ClassNotFoundException {
        return itemDAO.getId();
    }

    @Override
    public String getItemName(String itemId) throws SQLException, ClassNotFoundException {
        return itemDAO.getName(itemId);
    }

    @Override
    public String generateNextItemId() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNextId();
    }

    @Override
    public boolean update(List<JobDetailDTO> jobList) throws SQLException, ClassNotFoundException {
        ArrayList<JobDetail> jobDetails = new ArrayList<>();

        for (JobDetailDTO jobDetailDTO : jobList){
            JobDetail jobDetail = new JobDetail(jobDetailDTO.getItemId(), jobDetailDTO.getItemCount(), jobDetailDTO.getModel(), jobDetailDTO.getJobId(), jobDetailDTO.getSpareCount());
            jobDetails.add(jobDetail);
        }
        return itemDAO.update(jobDetails);
    }

    @Override
    public boolean updateItemQty(String itemId, int itemCount) throws SQLException, ClassNotFoundException {
        return itemDAO.updateQty(itemId, itemCount);
    }
}
