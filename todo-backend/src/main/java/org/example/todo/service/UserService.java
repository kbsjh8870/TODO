package org.example.todo.service;

import lombok.RequiredArgsConstructor;
import org.example.todo.domain.User;
import org.example.todo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 사용자 등록
    public User createUser(User user) {

        if (user.getName() == null || user.getName().trim().isEmpty()) {
            return null;
        }
        return userRepository.save(user);
    }

    // 사용자 수정
    public User modifyUser(User user) {
        return userRepository.save(user);
    }

    // 사용자 삭제
    public void deleteUser(int id) {
       userRepository.deleteById(id);
    }

    // id로 단건 조회
    public User findUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    // 전체 조회
    public List<User> findAllUser() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().forEach(list::add);
        return list;
    }

    // 로그인
    public User login(String name, String password) {
        return userRepository.findByNameAndPassword(name,password).orElse(null);
    }
}
