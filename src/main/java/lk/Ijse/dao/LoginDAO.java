package lk.Ijse.dao;

public interface LoginDAO extends SuperDAO{
    public boolean checkCredential(String password, String username);
}
