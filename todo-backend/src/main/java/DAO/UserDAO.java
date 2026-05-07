package DAO;

import DTO.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    // 사용자 등록
    public int createUser(Connection conn, UserDTO userDTO){
        String sql = "insert into user(name) values (?)";
        int result = 0;

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1,userDTO.getName());

            result = pstmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    // 사용자 수정
    public int modifyUser(Connection conn, UserDTO userDTO){
        String sql = "update user set name = ? where id=?";
        int result = 0;

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1,userDTO.getName());
            pstmt.setInt(2,userDTO.getId());

            result = pstmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    // 사용자 삭제
    public int deleteUser(Connection conn, int userID){
        String sql = "delete from user where id = ?";
        int result = 0;

        try(PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1,userID);

            result = pstmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public UserDTO findById(Connection conn, int id) {
        String sql = "select id, name from user where id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    UserDTO userDTO = new UserDTO();
                    userDTO.setId(rs.getInt("id"));
                    userDTO.setName(rs.getString("name"));
                    return userDTO;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<UserDTO> findAllUser(Connection conn) {
        String sql = "select id, name from user";
        List<UserDTO> users = new ArrayList<>();

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                UserDTO userDTO = new UserDTO();
                userDTO.setId(rs.getInt("id"));
                userDTO.setName(rs.getString("name"));
                users.add(userDTO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
