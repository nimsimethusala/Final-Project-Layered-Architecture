package lk.Ijse.bo;

public interface LoginBO extends SuperBO{
    public boolean checkCredential(String password, String username);

    public boolean update(String cpw, String npw);
}
