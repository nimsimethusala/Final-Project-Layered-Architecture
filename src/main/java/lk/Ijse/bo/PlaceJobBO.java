package lk.Ijse.bo;

import lk.Ijse.db.DbConnection;
import lk.Ijse.dto.JobDTO;
import lk.Ijse.dto.JobDetailDTO;
import lk.Ijse.dto.PlaceJobDTO;
import lk.Ijse.entity.Job;
import lk.Ijse.entity.JobDetail;
import lk.Ijse.entity.PlaceJob;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PlaceJobBO extends SuperBO{
    public boolean save(JobDTO jobDTO) throws SQLException, ClassNotFoundException;

    public String generateNextId() throws SQLException, ClassNotFoundException;

    public List<String> getId() throws SQLException, ClassNotFoundException;

    public String getEmployeeId(String jobId) throws SQLException, ClassNotFoundException;

    public String getSpareId(String jobId) throws SQLException, ClassNotFoundException;

    public String getDefectId(String jobId) throws SQLException, ClassNotFoundException;

    public boolean save(List<JobDetailDTO> jobDetailDTOList) throws SQLException, ClassNotFoundException;

    public boolean save(JobDetailDTO list) throws SQLException, ClassNotFoundException;

    public boolean placeOrder(PlaceJobDTO placeJobDTO) throws SQLException;
}
