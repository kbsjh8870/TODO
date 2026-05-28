package org.example.todo.service;

import org.example.todo.dao.UserDAO;
import org.example.todo.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.util.List;

@Service
public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    // 사용자 등록
    public boolean createUser(UserDTO userDTO) {

        if (userDTO.getName() == null || userDTO.getName().trim().isEmpty()) {
            return false;
        }
        return userDAO.createUser(userDTO)>0;
    }

    // 사용자 수정
    public boolean modifyUser(UserDTO userDTO) {
        return userDAO.modifyUser(userDTO)>0;
    }

    // 사용자 삭제
    public boolean deleteUser(int id) {
       return userDAO.deleteUser(id)>0;
    }

    // id로 단건 조회
    public UserDTO findUserById(int id) {
        return userDAO.findById(id);
    }

    // 전체 조회
    public List<UserDTO> findAllUser() {
        return userDAO.findAllUser();
    }

    // 로그인
    public UserDTO login(String name, String password) {
        return userDAO.login(name,password);
    }
}
