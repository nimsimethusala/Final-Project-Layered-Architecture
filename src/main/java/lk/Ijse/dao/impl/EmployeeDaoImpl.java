package lk.Ijse.dao.impl;

import lk.Ijse.dao.EmployeeDAO;
import lk.Ijse.dao.SQLUtil;
import lk.Ijse.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDAO {

    public boolean delete(String empId) throws SQLException, ClassNotFoundException {
        /*String sql = "DELETE FROM employee WHERE Emp_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, empId);

        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("DELETE FROM employee WHERE Emp_id = ?", empId);
    }

    public boolean save(Employee employee) throws SQLException, ClassNotFoundException {
        /*String sql = "INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, employee.getEmpId());
        pstm.setObject(2, employee.getName());
        pstm.setObject(3, employee.getAttendance());
        pstm.setObject(4,employee.getAddress());
        pstm.setObject(5, employee.getContact());
        pstm.setObject(6, employee.getSalary());
        pstm.setObject(7, employee.getCost());

        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?, ?)", employee);
    }

    public boolean update(Employee employee) throws SQLException, ClassNotFoundException {
        /*String sql = "UPDATE employee SET Name = ?, attendence = ?, address = ?, contact = ?, salary = ?, cost = ? WHERE Emp_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setObject(1, employee.getName());
        pstm.setObject(2, employee.getAttendance());
        pstm.setObject(3, employee.getAddress());
        pstm.setObject(4, employee.getContact());
        pstm.setObject(5, employee.getSalary());
        pstm.setObject(6, employee.getCost());
        pstm.setObject(7, employee.getEmpId());

        return pstm.executeUpdate() > 0;*/

        return SQLUtil.execute("UPDATE employee SET Name = ?, attendence = ?, address = ?, contact = ?, salary = ?, cost = ? WHERE Emp_id = ?", employee);
    }

    public List<Employee> getAll() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT * FROM employee";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee");
        List<Employee> empList = new ArrayList<>();

        while (resultSet.next()){
            String empId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String attendence = resultSet.getString(3);
            String address = resultSet.getString(4);
            int contact = resultSet.getInt(5);
            double salary = resultSet.getDouble(6);
            double cost = resultSet.getDouble(7);

            Employee employee = new Employee(empId, name, attendence, contact, address, salary, cost);
            empList.add(employee);
        }

        return empList;
    }

    public double getEmployeeCost(String empId) throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT cost FROM employee WHERE Emp_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, empId);*/

        ResultSet resultSet = SQLUtil.execute("SELECT cost FROM employee WHERE Emp_id = ?", empId);

        if (resultSet.next()){
            double cost = resultSet.getDouble(1);
            return cost;
        }
        return 0.0;
    }

    public List<String> getId() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT Emp_id FROM employee";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/

        ResultSet resultSet = SQLUtil.execute("SELECT Emp_id FROM employee");

        List<String> idList = new ArrayList<>();
        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }

    public String generateNextId() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT Emp_id FROM employee ORDER BY Emp_id DESC LIMIT 1";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/

        ResultSet resultSet = SQLUtil.execute("SELECT Emp_id FROM employee ORDER BY Emp_id DESC LIMIT 1");

        if(resultSet.next()) {
            return splitEmployeeId(resultSet.getString(1));
        }
        return splitEmployeeId(null);
    }

    private String splitEmployeeId(String string) {
        if(string != null) {
            String[] strings = string.split("E0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "E00"+id;
            }else {
                if (length < 3){
                    return "E0"+id;
                }else {
                    return "E"+id;
                }
            }
        }
        return "E001";
    }

    public String getName(String empId) throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT Name FROM employee WHERE Emp_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, empId);*/

        ResultSet resultSet = SQLUtil.execute("SELECT Name FROM employee WHERE Emp_id = ?", empId);

        while (resultSet.next()){
            String empName = resultSet.getString(1);

            return empName;
        }
        return null;
    }

    public Employee searchById(String id) throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT * FROM employee WHERE Emp_id = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);*/

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee WHERE Emp_id = ?", id);

        while (resultSet.next()){
            String employeeId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String attendence = resultSet.getString(3);
            String address = resultSet.getString(4);
            int contact = resultSet.getInt(5);
            double salary = resultSet.getDouble(6);
            double cost = resultSet.getDouble(7);

            Employee employee = new Employee(employeeId, name, attendence, contact, address, salary, cost);
            return employee;
        }

        return null;
    }

    public int getArrivedEmpCount() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT attendence FROM employee WHERE attendence = 'Yes'";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/

        int count = 0;

        ResultSet resultSet = SQLUtil.execute("SELECT attendence FROM employee WHERE attendence = 'Yes'");
        while (resultSet.next()){
            count += 1;

            return count;
        }
        return 0;
    }

    public int getAbsentEmpCount() throws SQLException, ClassNotFoundException {
        /*String sql = "SELECT attendence FROM employee WHERE attendence = 'No'";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);*/

        int count = 0;

        ResultSet resultSet = SQLUtil.execute("SELECT attendence FROM employee WHERE attendence = 'No'");
        while (resultSet.next()){
            count += 1;

            return count;
        }
        return 0;
    }
}
