package lk.Ijse.bo;

import lk.Ijse.dto.SparesDTO;
import lk.Ijse.entity.Spares;

import java.sql.SQLException;
import java.util.List;

public interface SpareBO extends SuperBO{
    public boolean saveSpares(SparesDTO sparesDTO) throws SQLException, ClassNotFoundException;

    public boolean updateSpares(SparesDTO sparesDTO) throws SQLException, ClassNotFoundException;

    public boolean deleteSpares(String spareId) throws SQLException, ClassNotFoundException;

    public SparesDTO searchBySpareId(String id) throws SQLException, ClassNotFoundException;

    public List<SparesDTO> getAllSpares() throws SQLException, ClassNotFoundException;

    public List<String> getSpareId() throws SQLException, ClassNotFoundException;

    public String getSpareName(String spareId) throws SQLException, ClassNotFoundException;

    public String generateNextSpareId() throws SQLException, ClassNotFoundException;

    public boolean update(Job job) throws SQLException;

    public boolean updateSpareQty(String spareId, int spareCount) throws SQLException, ClassNotFoundException;
}
