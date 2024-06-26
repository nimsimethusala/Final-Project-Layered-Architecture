package lk.Ijse.bo.impl;

import lk.Ijse.bo.SpareBO;
import lk.Ijse.dao.SpareDAO;
import lk.Ijse.dao.impl.SpareDaoImpl;
import lk.Ijse.dto.SparesDTO;
import lk.Ijse.entity.Spares;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpareBoImpl implements SpareBO {
    SpareDAO spareDAO = new SpareDaoImpl();
    public boolean saveSpares(SparesDTO sparesDTO) throws SQLException, ClassNotFoundException {
        return spareDAO.save(new Spares(
                sparesDTO.getSpareId(),
                sparesDTO.getName(),
                sparesDTO.getCount(),
                sparesDTO.getPrice(),
                sparesDTO.getSupplierId()
        ));
    }

    public boolean updateSpares(SparesDTO sparesDTO) throws SQLException, ClassNotFoundException {
        return spareDAO.save(new Spares(
                sparesDTO.getSpareId(),
                sparesDTO.getName(),
                sparesDTO.getCount(),
                sparesDTO.getPrice(),
                sparesDTO.getSupplierId()
        ));
    }

    public boolean deleteSpares(String spareId) throws SQLException, ClassNotFoundException {
        return spareDAO.delete(spareId);
    }

    public SparesDTO searchBySpareId(String id) throws SQLException, ClassNotFoundException {
        Spares spares = spareDAO.searchById(id);
        return new SparesDTO(spares.getSpareId(), spares.getName(), spares.getCount(), spares.getPrice(), spares.getSupplierId());
    }

    public List<SparesDTO> getAllSpares() throws SQLException, ClassNotFoundException {
        ArrayList<Spares> spares = new ArrayList<>();
        ArrayList<SparesDTO> sparesDTOS = new ArrayList<>();

        for (Spares spare : spares){
            SparesDTO sparesDTO = new SparesDTO(spare.getSpareId(), spare.getName(), spare.getCount(), spare.getPrice(), spare.getSupplierId());
            sparesDTOS.add(sparesDTO);
        }
        return sparesDTOS;
    }

    public List<String> getSpareId() throws SQLException, ClassNotFoundException {
        return spareDAO.getId();
    }

    public String getSpareName(String spareId) throws SQLException, ClassNotFoundException{
        return spareDAO.getName(spareId);
    }

    public String generateNextSpareId() throws SQLException, ClassNotFoundException {
        return spareDAO.generateNextId();
    }

    public boolean update(Job job) throws SQLException {
        return spareDAO.update(job);
    }

    public boolean updateSpareQty(String spareId, int spareCount) throws SQLException, ClassNotFoundException {
        return spareDAO.updateQty(spareId, spareCount);
    }
}
