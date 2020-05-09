package dao;

import vo.Sign;

import java.sql.Date;
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
     * 根据id查询某日的签到信息
     * @param userId 学生id
     * @param date 要查询的日期
     * @return 签到信息
     */
    Sign queryById(String userId, Date date);

    /**
     * 根据学生名字查询某日签到信息
     * @param name 学生名字
     * @param date 要查询的日期
     * @return 签到信息
     */
    Sign queryByName(String name,Date date);

    /**
     * 查询某日某签到情况的学生信息
     * @param date 要查询的日期
     * @param state 0(未签到) 1(已签到但未签退) 2(已签到且已签退)
     * @return 学生信息
     */
    ArrayList<Sign> queryByStates(Date date,int state);
}
