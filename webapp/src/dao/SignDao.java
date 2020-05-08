package dao;

import vo.Sign;

import java.util.ArrayList;

/**
 * TODO 管理端CRUD
 * @author Ji Rui
 */
public interface SignDao {
    /**
     * 新插入签到信息
     * @param sign 签到相关信息
     */
    void insert(Sign sign);

    /**
     * 根据id信息签到
     * @param id 学生id
     */
    void updateById(String id);

    /**
     * 根据id查询签到信息
     * @param id 学生id
     * @return 签到信息
     */
    Sign queryById(String id);

    /**
     * 根据学生名字查询签到信息
     * @param name 学生名字
     * @return 签到信息
     */
    Sign queryByName(String name);

    /**
     * 查询尚未签到的学生信息
     * @return 学生信息
     */
    ArrayList<Sign> queryBySigned();
}
