import dao.InfoDao;
import dao.SignDao;
import dao.UserDao;
import vo.Info;
import vo.Sign;
import vo.User;

import java.sql.Date;
import java.util.ArrayList;

import static factory.DaoFactory.*;
import static utils.Md5Util.doubleSalt;

/**
 * @author Ji Rui
 */
public class Test {
    public static void main(String[] args) {
        //userTest();
        //infoTest();
        signTest();
    }

    public static void infoTest(){
        Info info = new Info();
        InfoDao infoDao = getInfoDaoInstance();
        info.setId("1806010633");
        info.setHasFever(0);
        info.setHasContactWithPatients(0);
        info.setHasContactWithForeigners(0);
        info.setIsHealthy(1);
        info.setIsDanger(0);
        infoDao.insert(info);
        System.out.println("Insert successfully.");
        Info info1 = infoDao.queryById("1806010633");
        System.out.println(info1.getId()+" "+info1.getIsHealthy()+" "+info1.getHasContactWithForeigners());
        ArrayList<Info> arrayList=infoDao.queryByDanger();
        for (Info info2 : arrayList) {
            System.out.println(info2.getId() + " " + info2.getIsHealthy() + " " + info2.getHasContactWithForeigners());
        }
    }
    public static void userTest(){
        User user = new User();
        user.setId("1806010633");
        user.setName("季锐");
        user.setUsername("CofJus");
        user.setPassword("jiruishigedashibi");
        user.setAcademy("计算机与信息学院");
        user.setMajor("计算机科学与技术");
        user.setGrade("2");
        user.setClassName("计算机6班");
        user.setPhoneNumber("18994635650");
        UserDao userDao=getUserDaoInstance();
        userDao.insert(user);
        User res=userDao.queryById("1806010633");
        System.out.println(res.getId()+" "+res.getName()+" "+res.getAcademy());
        userDao.updatePassword("1806010633",doubleSalt("jiruishigeruozhi"));
        User res2=userDao.queryById("1806010633");
        System.out.println(res2.getPassword());
    }

    public static void signTest(){
        Sign sign=new Sign();
        SignDao signDao=getSignDaoInstance();
        sign.setId("1806010632");
        sign.setName("萌瀚");
        sign.setDate(new Date(System.currentTimeMillis()));
        sign.setSigned(1);
        signDao.insert(sign);
        Sign sign1=signDao.queryById("1806010633");
        System.out.println(sign1.getName());
        Sign sign2=signDao.queryByName("季锐");
        System.out.println(sign2.getId());
        ArrayList<Sign> arrayList=signDao.queryBySigned();
        for(Sign sign3:arrayList){
            System.out.println(sign3.getId()+" "+sign3.getName());
        }
        signDao.updateById("2");
        Sign sign4=signDao.queryById("2");
        System.out.println(sign4.getSigned());
    }
}
