package dao;

import vo.User;

/**
 * @author Ji Rui
 */

public interface UserDao {
    /**
     * 插入
     * @param user 用户注册的各项信息
     */
    void insert(User user);

    /**
     * 修改密码
     * @param id 要改密码的用户id
     * @param password 新密码
     */
    void updatePassword(String id, String password);

    /**
     * 根据学生id查找学生信息
     * @param id 学生id
     * @return 学生注册时填写的信息
     */
    User queryById(String id);
}
