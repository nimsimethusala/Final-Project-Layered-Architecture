package lk.Ijse.bo.impl;

import com.beust.ah.A;
import lk.Ijse.bo.PlaceJobBO;
import lk.Ijse.bo.SpareBO;
import lk.Ijse.dao.*;
import lk.Ijse.dao.impl.*;
import lk.Ijse.db.DbConnection;
import lk.Ijse.dto.JobDTO;
import lk.Ijse.dto.JobDetailDTO;
import lk.Ijse.dto.PlaceJobDTO;
import lk.Ijse.entity.Job;
import lk.Ijse.entity.JobDetail;
import lk.Ijse.entity.PlaceJob;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceJobBoImpl implements PlaceJobBO {
    JobDAO jobDAO = (JobDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.JOB);

    JobDetailDAO jobDetailDAO = (JobDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.JOB_DETAIL);

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    SpareDAO spareDAO = (SpareDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SPARE);

    @Override
    public boolean save(JobDTO jobDTO) throws SQLException, ClassNotFoundException {
        return jobDAO.save(new Job(
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
        ));
    }

    @Override
    public String generateNextId() throws SQLException, ClassNotFoundException {
        return jobDAO.generateNextId();
    }

    @Override
    public List<String> getId() throws SQLException, ClassNotFoundException {
        return jobDAO.getId();
    }

    @Override
    public String getEmployeeId(String jobId) throws SQLException, ClassNotFoundException {
        return jobDAO.getEmployeeId(jobId);
    }

    @Override
    public String getSpareId(String jobId) throws SQLException, ClassNotFoundException {
        return jobDAO.getSpareId(jobId);
    }

    @Override
    public String getDefectId(String jobId) throws SQLException, ClassNotFoundException {
        return jobDAO.getDefectId(jobId);
    }

    @Override
    public boolean save(List<JobDetailDTO> jobList) throws SQLException, ClassNotFoundException {
        for (JobDetailDTO list : jobList) {
            boolean isSaved = save(list);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean save(JobDetailDTO list) throws SQLException, ClassNotFoundException {
        JobDetail jobDetail = new JobDetail(list.getItemId(), list.getItemCount(), list.getModel(), list.getJobId(), list.getSpareCount());
        return jobDetailDAO.save(jobDetail);
    }

    @Override
    public boolean placeOrder(PlaceJobDTO placeJobDTO) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        ArrayList<JobDetail> jobDetails = new ArrayList<>();

        try {
            boolean isJobSaved = jobDAO.save(placeJobDTO.getJob());
            System.out.println("1 "+ isJobSaved);
            if (isJobSaved) {
                for (JobDetailDTO list : placeJobDTO.getJobList()){
                    JobDetail jobDetail = new JobDetail(list.getItemId(), list.getItemCount(), list.getModel(), list.getJobId(), list.getSpareCount());
                    jobDetails.add(jobDetail);
                }
                boolean isQtyUpdated = itemDAO.update(jobDetails);
                System.out.println("2 "+ isQtyUpdated);
                if (isQtyUpdated) {
                    boolean isOrderDetailSaved = jobDetailDAO.save(jobDetails);
                    System.out.println("3 "+ isOrderDetailSaved);
                    if (isOrderDetailSaved) {
                        boolean isSpareQtyUpdate = spareDAO.update(placeJobDTO.getJob());
                        System.out.println("4 "+ isSpareQtyUpdate);
                        if (isSpareQtyUpdate) {
                            connection.commit();
                            return true;
                        }
                    }
                }
            }
            connection.rollback();
            return false;

        } catch (Exception e) {
            connection.rollback();
            return false;

        } finally {
            connection.setAutoCommit(true);
        }
    }
}
