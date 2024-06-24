package lk.Ijse.bo;

import lk.Ijse.dto.CustomerDTO;
import lk.Ijse.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO{
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    public boolean deleteCustomer(String cusId) throws SQLException, ClassNotFoundException;

    public CustomerDTO searchByCustomerId(String tel) throws SQLException, ClassNotFoundException;

    public List<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;

    public List<String> getCustomerId() throws SQLException, ClassNotFoundException;

    public String getCustomerName(String cusId) throws SQLException, ClassNotFoundException;

    public String generateNextCustomerId() throws SQLException, ClassNotFoundException;
}
