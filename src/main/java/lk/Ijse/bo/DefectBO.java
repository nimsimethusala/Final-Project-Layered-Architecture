package lk.Ijse.bo;

import lk.Ijse.dto.DefectDTO;

import java.sql.SQLException;
import java.util.List;

public interface DefectBO extends SuperBO{
    public boolean saveDefect(DefectDTO defectDTO) throws SQLException, ClassNotFoundException;

    public boolean updateDefect(DefectDTO customer) throws SQLException, ClassNotFoundException;

    public boolean deleteDefect(String cusId) throws SQLException, ClassNotFoundException;

    public DefectDTO searchByDefectId(String tel) throws SQLException, ClassNotFoundException;

    public List<DefectDTO> getAllDefects() throws SQLException, ClassNotFoundException;

    public List<String> getDefectId() throws SQLException, ClassNotFoundException;

    public String generateNextDefectId() throws SQLException, ClassNotFoundException;

    public String getDefectDescription(String defectId) throws SQLException, ClassNotFoundException;
}
