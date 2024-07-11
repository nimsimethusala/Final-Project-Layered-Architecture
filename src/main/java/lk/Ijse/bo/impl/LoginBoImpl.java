package lk.Ijse.bo.impl;

import lk.Ijse.bo.LoginBO;
import lk.Ijse.dao.DAOFactory;
import lk.Ijse.dao.LoginDAO;

public class LoginBoImpl implements LoginBO {
    LoginDAO loginDAO = (LoginDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    public boolean checkCredential(String password, String username) {
        return loginDAO.checkCredential(password, username);
    }
}
