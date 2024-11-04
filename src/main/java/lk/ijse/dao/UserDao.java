package lk.ijse.dao;


import lk.ijse.entity.User;



import java.util.List;

public interface UserDao extends CrudDao<User>{

    public List<String> getAllRoles() ;


    List<User> getUserDetails(User user);

    List<User> loadTale();

}
