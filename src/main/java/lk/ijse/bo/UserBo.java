package lk.ijse.bo;
import lk.ijse.dto.UserDTO;
import java.util.List;

public interface UserBo  extends SuperBo{




      List<String> getAllRoles() ;


      List<UserDTO> getUserDetails(UserDTO userDTO);

      boolean saveUsers(UserDTO coordinator);

    boolean updateUser(UserDTO coordinator);

    UserDTO searchUsers(String username);

    boolean deleteUser(String username);

    List<UserDTO> loadTable();
}
