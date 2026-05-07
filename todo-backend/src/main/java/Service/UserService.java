package Service;

import DAO.UserDAO;
import DBUtil.DBConnection;
import DTO.UserDTO;

import java.sql.Connection;
import java.util.List;

public class UserService {
//test
    private final UserDAO userDAO = new UserDAO();

    // 사용자 등록
    public boolean createUser(UserDTO userDTO) {

        if (userDTO.getName() == null || userDTO.getName().trim().isEmpty()) {
            return false;
        }

        try (Connection conn = DBConnection.getConnection()) {
            int result = userDAO.createUser(conn, userDTO);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 사용자 수정
    public boolean modifyUser(UserDTO userDTO) {
        try (Connection conn = DBConnection.getConnection()) {
            int result = userDAO.modifyUser(conn, userDTO);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 사용자 삭제
    public boolean deleteUser(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            int result = userDAO.deleteUser(conn, id);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // id로 단건 조회
    public UserDTO findUserById(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            return userDAO.findById(conn, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 전체 조회
    public List<UserDTO> findAllUser() {
        try (Connection conn = DBConnection.getConnection()) {
            return userDAO.findAllUser(conn);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
