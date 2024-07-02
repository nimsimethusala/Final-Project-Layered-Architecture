package lk.Ijse.bo.impl;

import lk.Ijse.bo.EmployeeBO;
import lk.Ijse.dao.DAOFactory;
import lk.Ijse.dao.EmployeeDAO;
import lk.Ijse.dao.impl.EmployeeDaoImpl;
import lk.Ijse.dto.EmployeeDTO;
import lk.Ijse.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBoImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(
                employeeDTO.getEmpId(),
                employeeDTO.getName(),
                employeeDTO.getAttendance(),
                employeeDTO.getContact(),
                employeeDTO.getAddress(),
                employeeDTO.getSalary(),
                employeeDTO.getCost()
        ));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(
                employeeDTO.getEmpId(),
                employeeDTO.getName(),
                employeeDTO.getAttendance(),
                employeeDTO.getContact(),
                employeeDTO.getAddress(),
                employeeDTO.getSalary(),
                employeeDTO.getCost()
        ));
    }

    @Override
    public boolean deleteEmplyee(String empId) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(empId);
    }

    @Override
    public EmployeeDTO searchByEmployeeId(String tel) throws SQLException, ClassNotFoundException{
        Employee employee = employeeDAO.searchById(tel);
        return new EmployeeDTO(
                employee.getEmpId(),
                employee.getName(),
                employee.getAttendance(),
                employee.getContact(),
                employee.getAddress(),
                employee.getSalary(),
                employee.getCost()
        );
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() throws SQLException, ClassNotFoundException{
        ArrayList<Employee> employees = employeeDAO.getAll();
        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();

        for (Employee employee : employees){
            EmployeeDTO employeeDTO = new EmployeeDTO(employee.getEmpId(), employee.getName(), employee.getAttendance(), employee.getContact(), employee.getAddress(), employee.getSalary(), employee.getCost());
            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;
    }

    @Override
    public List<String> getEmployeeId() throws SQLException, ClassNotFoundException {
        return employeeDAO.getId();
    }

    @Override
    public String getEmployeeName(String empId) throws SQLException, ClassNotFoundException {
        return employeeDAO.getName(empId);
    }

    @Override
    public String generateNextEmployeeId() throws SQLException, ClassNotFoundException {
        return employeeDAO.generateNextId();
    }

    @Override
    public double getEmployeeCost(String empId) throws SQLException, ClassNotFoundException {
        return employeeDAO.getEmployeeCost(empId);
    }

    @Override
    public int getArrivedEmpCount() throws SQLException, ClassNotFoundException {
        return employeeDAO.getArrivedEmpCount();
    }

    @Override
    public int getAbsentEmpCount() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAbsentEmpCount();
    }
}
