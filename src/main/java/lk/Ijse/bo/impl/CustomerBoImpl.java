package lk.Ijse.bo.impl;

import lk.Ijse.bo.CustomerBO;
import lk.Ijse.dao.CustomerDAO;
import lk.Ijse.dao.SQLUtil;
import lk.Ijse.dao.impl.CustomerDaoImpl;
import lk.Ijse.dto.CustomerDTO;
import lk.Ijse.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBO {
    CustomerDAO customerDAO = new CustomerDaoImpl();

    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress(), customerDTO.getTel()));
    }

    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getAddress(), customerDTO.getTel()));
    }

    public boolean deleteCustomer(String cusId) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(cusId);
    }

    public CustomerDTO searchByCustomerId(String tel) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.searchById(tel);
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress(), customer.getTel());
    }

    public List<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();

        for (Customer customer : customers) {
            CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress(), customer.getTel());
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    public List<String> getCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.getId();
    }

    public String getCustomerName(String cusId) throws SQLException, ClassNotFoundException {
        return customerDAO.getName(cusId);
    }

    public String generateNextCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNextId();
    }
}
