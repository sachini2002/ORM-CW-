package lk.ijse.bo.impl;

import lk.ijse.bo.UserBo;
import lk.ijse.dao.DaoFactory;
import lk.ijse.dao.DaoType;
import lk.ijse.dao.UserDao;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;

import java.util.ArrayList;
import java.util.List;


public class UserBoImpl implements UserBo {

    UserDao userDao  = (UserDao) DaoFactory.getInstance().getDao(DaoType.User);




    public List<String> getAllRoles() {
        return userDao.getAllRoles();
    }

    @Override
    public List<UserDTO> getUserDetails(UserDTO userDTO) {
        List<UserDTO> userdt = new ArrayList<>();
        List<User> userDetails = userDao.getUserDetails(new User(userDTO.getRole(), userDTO.getUsername(), userDTO.getPassword(),userDTO.getStudents()));

        for (User user : userDetails){
            UserDTO userDTO1 = new UserDTO(user.getUsername(), user.getPassword(),user.getRole(),user.getStudents());
            userdt.add(userDTO1);
        }
        return userdt;

    }

    @Override
    public boolean saveUsers(UserDTO coordinator) {
      return   userDao.save(new User(coordinator.getUsername(), coordinator.getPassword(), coordinator.getRole()));
    }

    @Override
    public boolean updateUser(UserDTO coordinator) {
       return userDao.update(new User(coordinator.getUsername(), coordinator.getPassword(), coordinator.getRole()));
    }

    @Override
    public UserDTO searchUsers(String username) {
        User user = userDao.find(username);

        UserDTO userDTO = new UserDTO(user.getUsername(), user.getPassword(), user.getRole(), user.getStudents());
        return userDTO;

    }

    @Override
    public boolean deleteUser(String username) {
       return userDao.delete(username);
    }

    @Override
    public List<UserDTO> loadTable() {
        List<User> users = userDao.loadTale();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users){
            UserDTO userDTO = new UserDTO(user.getUsername(), user.getPassword(), user.getRole(), user.getStudents());
            userDTOs.add(userDTO);

        }
        return userDTOs;

    }


}
