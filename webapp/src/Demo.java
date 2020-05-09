import dao.InfoDao;
import dao.SignDao;
import dao.UserDao;
import vo.Info;
import vo.Sign;
import vo.User;

import java.sql.Date;
import java.util.ArrayList;

import static factory.DaoFactory.*;

/**
 * 参考示例
 * @author Ji Rui
 */
public class Demo {
    public static void main(String[] args) {
        //userTest();
        //infoTest();
        //signTest();
    }

    public static void userTest() {

        UserDao userDao = getUserDaoInstance();

        //增
        User user = new User();
        user.setUsername("CofJus");
        user.setPassword("123456");
        user.setName("季锐");
        user.setId("1806010633");
        user.setAcademy("计算机与信息学院");
        user.setGrade("二");
        user.setMajor("计算机科学与技术");
        user.setClassName("计算机6班");
        user.setPhoneNumber("18994635650");

        userDao.insert(user);

        //查
        User user1 = userDao.queryById("1806010633");
        System.out.println("Username:" + user1.getUsername());
        System.out.println("Name:" + user1.getName());
        System.out.println("Phone:" + user1.getPhoneNumber());

        //改
        userDao.updatePassword("1806010633", "654321");
    }

    public static void infoTest() {

        InfoDao infoDao = getInfoDaoInstance();

        //增
        Info info = new Info();
        info.setId("1806010633");
        info.setHasFever(0);
        info.setIsHealthy(0);
        info.setHasContactWithPatients(0);
        info.setHasContactWithForeigners(0);
        info.setIsDanger(0);

        infoDao.insert(info);

        //改
        info.setIsDanger(1);
        infoDao.updateById(info);

        //查
        Info info1 = infoDao.queryById("1806010633");
        System.out.println(info1.getId()+" "+info1.getIsDanger());

        ArrayList<Info> arrayList=infoDao.queryAll();
        for(Info information : arrayList){
            System.out.println(information.getId()+" "+information.getHasFever()+" "+information.getHasContactWithForeigners());
        }
    }

    public static void signTest() {

        SignDao signDao=getSignDaoInstance();

        Sign sign = new Sign();
        sign.setUserId("1806010633");
        sign.setName("季锐");
        //默认设置为今天的日期，精确到日
        sign.setDate(new Date(new java.util.Date().getTime()));
        sign.setSigned(0);

        //增
        signDao.insert(sign);

        //查
        Sign sign1=signDao.queryById("1806010633",new Date(new java.util.Date().getTime()));
        ArrayList<Sign> arrayList=signDao.queryByStates(new Date(new java.util.Date().getTime()),1);
        for(Sign sign2:arrayList){
            System.out.println(sign2.getSigned());
        }
    }
}
