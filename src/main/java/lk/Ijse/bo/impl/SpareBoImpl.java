package lk.Ijse.bo.impl;

import lk.Ijse.bo.SpareBO;
import lk.Ijse.dao.SpareDAO;
import lk.Ijse.dao.impl.SpareDaoImpl;
import lk.Ijse.dto.JobDTO;
import lk.Ijse.dto.SparesDTO;
import lk.Ijse.entity.Job;
import lk.Ijse.entity.Spares;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpareBoImpl implements SpareBO {
    SpareDAO spareDAO = new SpareDaoImpl();

    @Override
    public boolean saveSpares(SparesDTO sparesDTO) throws SQLException, ClassNotFoundException {
        return spareDAO.save(new Spares(
                sparesDTO.getSpareId(),
                sparesDTO.getName(),
                sparesDTO.getCount(),
                sparesDTO.getPrice(),
                sparesDTO.getSupplierId()
        ));
    }

    @Override
    public boolean updateSpares(SparesDTO sparesDTO) throws SQLException, ClassNotFoundException {
        return spareDAO.save(new Spares(
                sparesDTO.getSpareId(),
                sparesDTO.getName(),
                sparesDTO.getCount(),
                sparesDTO.getPrice(),
                sparesDTO.getSupplierId()
        ));
    }

    @Override
    public boolean deleteSpares(String spareId) throws SQLException, ClassNotFoundException {
        return spareDAO.delete(spareId);
    }

    @Override
    public SparesDTO searchBySpareId(String id) throws SQLException, ClassNotFoundException {
        Spares spares = spareDAO.searchById(id);
        return new SparesDTO(spares.getSpareId(), spares.getName(), spares.getCount(), spares.getPrice(), spares.getSupplierId());
    }

    @Override
    public List<SparesDTO> getAllSpares() throws SQLException, ClassNotFoundException {
        ArrayList<Spares> spares = spareDAO.getAll();
        ArrayList<SparesDTO> sparesDTOS = new ArrayList<>();

        for (Spares spare : spares){
            SparesDTO sparesDTO = new SparesDTO(spare.getSpareId(), spare.getName(), spare.getCount(), spare.getPrice(), spare.getSupplierId());
            sparesDTOS.add(sparesDTO);
        }
        return sparesDTOS;
    }

    @Override
    public List<String> getSpareId() throws SQLException, ClassNotFoundException {
        return spareDAO.getId();
    }

    @Override
    public String getSpareName(String spareId) throws SQLException, ClassNotFoundException{
        return spareDAO.getName(spareId);
    }

    @Override
    public String generateNextSpareId() throws SQLException, ClassNotFoundException {
        return spareDAO.generateNextId();
    }

    @Override
    public boolean update(JobDTO jobDTO) throws SQLException, ClassNotFoundException {
        Job job = new Job(
                jobDTO.getJobId(),
                jobDTO.getModel(),
                jobDTO.getDate(),
                jobDTO.getCustomerId(),
                jobDTO.getDefectId(),
                jobDTO.getDefectName(),
                jobDTO.getItemCount(),
                jobDTO.getSpareId(),
                jobDTO.getSpareName(),
                jobDTO.getSpareCount(),
                jobDTO.getEmpId(),
                jobDTO.getEmpCost(),
                jobDTO.getEmpName()
        );
        return spareDAO.update(job);
    }

    @Override
    public boolean updateSpareQty(String spareId, int spareCount) throws SQLException, ClassNotFoundException {
        return spareDAO.updateQty(spareId, spareCount);
    }
}
