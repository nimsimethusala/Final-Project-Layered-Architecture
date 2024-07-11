package lk.Ijse.dao;

public interface LoginDAO extends SuperDAO{
    public boolean checkCredential(String password, String username);

    public boolean update(String cpw, String npw);
}
