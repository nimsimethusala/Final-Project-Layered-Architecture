package lk.Ijse.dao.impl;

import javafx.scene.control.Alert;
import lk.Ijse.dao.LoginDAO;
import lk.Ijse.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDaoImpl implements LoginDAO {
    public boolean checkCredential(String password, String username) {
        //String sql = "SELECT username, password from credential where username = ?";

        try {
            /*Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, username);*/
            ResultSet resultSet = SQLUtil.execute("SELECT username, password from credential where username = ?", username);

            if (resultSet.next()){
                String pw = resultSet.getString("password");

                if (password.equals(pw)){
                    return true;
                }else {
                    new Alert(Alert.AlertType.ERROR,"Sorry! Your Password is incorrect").show();
                }
            }else {
                new Alert(Alert.AlertType.ERROR,"Sorry! Your Username is incorrect").show();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean update(String cpw, String npw) {
        //String sql = "UPDATE credential SET password = ? WHERE username = 'nimsi'";

        try {
            if (npw.equals(cpw)){
                /*Connection connection = DbConnection.getInstance().getConnection();
                PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setObject(1, cpw);

                return pstm.executeUpdate() > 0;*/
                return SQLUtil.execute("UPDATE credential SET password = ? WHERE username = 'nimsi'", cpw);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}
