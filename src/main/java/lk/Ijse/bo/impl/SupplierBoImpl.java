package lk.Ijse.bo.impl;

import lk.Ijse.bo.SupplierBO;
import lk.Ijse.dao.SupplierDAO;
import lk.Ijse.dao.impl.SupplierDaoImpl;
import lk.Ijse.dto.SupplierDTO;
import lk.Ijse.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBoImpl implements SupplierBO {
    SupplierDAO supplierDAO = new SupplierDaoImpl();

    @Override
    public boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(
                supplierDTO.getSupplierId(),
                supplierDTO.getName(),
                supplierDTO.getContact(),
                supplierDTO.getLocation()
        ));
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(
                supplierDTO.getSupplierId(),
                supplierDTO.getName(),
                supplierDTO.getContact(),
                supplierDTO.getLocation()
        ));
    }

    @Override
    public boolean deleteSupplier(String supplierId) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(supplierId);
    }

    @Override
    public SupplierDTO searchBySupplierId(String id) throws SQLException, ClassNotFoundException {
        Supplier supplier = supplierDAO.searchById(id);
        return new SupplierDTO(supplier.getSupplierId(), supplier.getName(), supplier.getContact(), supplier.getLocation());
    }

    @Override
    public List<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> suppliers = new ArrayList<>();
        ArrayList<SupplierDTO> supplierDTOS = new ArrayList<>();

        for (Supplier supplier : suppliers){
            SupplierDTO supplierDTO = new SupplierDTO(supplier.getSupplierId(), supplier.getName(), supplier.getContact(), supplier.getLocation());
            supplierDTOS.add(supplierDTO);
        }
        return supplierDTOS;
    }

    @Override
    public List<String> getSupplierId() throws SQLException, ClassNotFoundException{
        return supplierDAO.getId();
    }

    @Override
    public String generateNextSupplierId() throws SQLException, ClassNotFoundException {
        return supplierDAO.generateNextId();
    }
}
