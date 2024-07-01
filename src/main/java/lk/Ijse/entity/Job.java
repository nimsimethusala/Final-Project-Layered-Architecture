package lk.Ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Job {
    private String jobId;
    private String model;
    private Date date;
    private String customerId;
    private String defectId;
    private String defectName;
    private int itemCount;
    private String spareId;
    private String SpareName;
    private int spareCount;
    private String empId;
    private double empCost;
    private String empName;
}
