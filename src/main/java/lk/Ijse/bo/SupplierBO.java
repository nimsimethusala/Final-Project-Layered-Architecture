package lk.Ijse.bo;

import lk.Ijse.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.List;

public interface SupplierBO {
    public boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;

    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;

    public boolean deleteSupplier(String cusId) throws SQLException, ClassNotFoundException;

    public SupplierDTO searchBySupplierId(String tel) throws SQLException, ClassNotFoundException;

    public List<SupplierDTO> getAllSuppliers() throws SQLException, ClassNotFoundException;

    public List<String> getSupplierId() throws SQLException, ClassNotFoundException;

    public String generateNextSupplierId() throws SQLException, ClassNotFoundException;
}
