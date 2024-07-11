package lk.Ijse.bo;

import lk.Ijse.bo.impl.*;
import lk.Ijse.dao.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,DEFECT,EMPLOYEE,ITEM,JOB,PLACE_JOB,PAYMENT,SPARE,SUPPLIER,USER
    }

    //Object creation logic for BO objects
    public SuperBO getBO(BOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerBoImpl();
            case DEFECT:
                return new DefectBoImpl();
            case EMPLOYEE:
                return new EmployeeBoImpl();
            case ITEM:
                return new ItemBoImpl();
            case PLACE_JOB:
                return new PlaceJobBoImpl();
            case PAYMENT:
                return new PaymentBoImpl();
            case SPARE:
                return new SpareBoImpl();
            case SUPPLIER:
                return new SupplierBoImpl();
            case USER:
                return new LoginBoImpl();
            default:
                return null;
        }
    }
}
