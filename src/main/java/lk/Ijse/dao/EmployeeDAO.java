package lk.Ijse.dao;

import lk.Ijse.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeDAO extends CrudDAO<Employee>{
    public double getEmployeeCost(String empId) throws SQLException, ClassNotFoundException;

    public int getArrivedEmpCount() throws SQLException, ClassNotFoundException;

    public int getAbsentEmpCount() throws SQLException, ClassNotFoundException;
}
