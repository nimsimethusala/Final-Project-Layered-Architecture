package lk.Ijse.bo;

import lk.Ijse.dto.EmployeeDTO;
import lk.Ijse.entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeBO extends SuperBO{
    public boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    public boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    public boolean deleteEmplyee(String cusId) throws SQLException, ClassNotFoundException;

    public EmployeeDTO searchByEmployeeId(String tel) throws SQLException, ClassNotFoundException;

    public List<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException;

    public List<String> getEmployeeId() throws SQLException, ClassNotFoundException;

    public String getEmployeeName(String empId) throws SQLException, ClassNotFoundException;

    public String generateNextEmployeeId() throws SQLException, ClassNotFoundException;

    public double getEmployeeCost(String empId) throws SQLException, ClassNotFoundException;

    public int getArrivedEmpCount() throws SQLException, ClassNotFoundException;

    public int getAbsentEmpCount() throws SQLException, ClassNotFoundException;
}
