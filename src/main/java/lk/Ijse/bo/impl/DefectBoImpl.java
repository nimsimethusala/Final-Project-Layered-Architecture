package lk.Ijse.bo.impl;

import lk.Ijse.bo.DefectBO;
import lk.Ijse.dao.DefectDAO;
import lk.Ijse.dao.SQLUtil;
import lk.Ijse.dao.impl.DefectDaoImpl;
import lk.Ijse.dto.DefectDTO;
import lk.Ijse.entity.Defect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DefectBoImpl implements DefectBO {
    DefectDAO defectDAO = new DefectDaoImpl();

    @Override
    public boolean deleteDefect(String defectId) throws SQLException, ClassNotFoundException {
        return defectDAO.delete(defectId);
    }

    @Override
    public boolean saveDefect(DefectDTO defectDTO) throws SQLException, ClassNotFoundException {
        return defectDAO.save(new Defect(
                defectDTO.getDefectId(),
                defectDTO.getDescription(),
                defectDTO.getPrice(),
                defectDTO.getSpareId()
        ));
    }

    @Override
    public boolean updateDefect(DefectDTO defectDTO) throws SQLException, ClassNotFoundException {
        return defectDAO.update(new Defect(
                defectDTO.getDefectId(),
                defectDTO.getDescription(),
                defectDTO.getPrice(),
                defectDTO.getSpareId()
        ));
    }

    @Override
    public List<DefectDTO> getAllDefects() throws SQLException, ClassNotFoundException {
        ArrayList<Defect> defects = defectDAO.getAll();
        ArrayList<DefectDTO> defectDTOS = new ArrayList<>();

        for (Defect defect : defects){
            DefectDTO defectDTO = new DefectDTO(defect.getDefectId(), defect.getDescription(), defect.getPrice(), defect.getSpareId());
            defectDTOS.add(defectDTO);
        }
        return defectDTOS;
    }

    @Override
    public String getDefectDescription(String defectId) throws SQLException, ClassNotFoundException {
        return defectDAO.getDescription(defectId);
    }

    @Override
    public List<String> getDefectId() throws SQLException, ClassNotFoundException {
        return defectDAO.getId();
    }

    @Override
    public String generateNextDefectId() throws SQLException, ClassNotFoundException {
        return defectDAO.generateNextId();
    }

    @Override
    public DefectDTO searchByDefectId(String tel) throws SQLException, ClassNotFoundException {
        Defect defect = defectDAO.searchById(tel);
        return new DefectDTO(defect.getDefectId(), defect.getDescription(), defect.getPrice(), defect.getSpareId());
    }
}
