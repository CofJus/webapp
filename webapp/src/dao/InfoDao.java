package dao;

import vo.Info;

import java.util.ArrayList;

/**
 * @author Ji Rui
 */

public interface InfoDao {

    /**
     * 插入健康信息
     * @param info 各项健康信息
     */
    void insert(Info info);

    /**
     * 通过id查找并更新健康信息
     * @param info 要更新的信息
     */
    void updateById(Info info);

    /**
     * 通过id查询学生的健康信息
     * @param id 学生id
     * @return info 包含一个学生的所有健康信息
     */
    Info queryById(String id);

    /**
     * 获取所有学生的健康信息
     * @return arraylist 包含所有学生的所有健康信息
     */
    ArrayList<Info> queryAll();

    /**
     * 获取风险学生的健康信息
     * @return 应当重点关注的学生名单
     */
    ArrayList<Info> queryByDanger();
}
