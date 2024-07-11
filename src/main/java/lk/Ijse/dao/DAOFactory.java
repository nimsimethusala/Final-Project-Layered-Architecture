package lk.Ijse.dao;

import lk.Ijse.dao.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER,DEFECT,EMPLOYEE,ITEM,JOB,JOB_DETAIL,PAYMENT,SPARE,SUPPLIER,USER
    }

    public SuperDAO getDAO(DAOTypes types){
        switch (types) {
            case CUSTOMER:
                return new CustomerDaoImpl();
            case DEFECT:
                return new DefectDaoImpl();
            case EMPLOYEE:
                return new EmployeeDaoImpl();
            case ITEM:
                return new ItemDaoImpl();
            case JOB:
                return new JobDaoImpl();
            case JOB_DETAIL:
                return new JobDetailDaoImpl();
            case PAYMENT:
                return new PaymentDaoImpl();
            case SPARE:
                return new SpareDaoImpl();
            case SUPPLIER:
                return new SupplierDaoImpl();
            case USER:
                return new LoginDaoImpl();
            default:
                return null;
        }
    }
}
