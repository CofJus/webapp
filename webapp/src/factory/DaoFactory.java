package factory;

import dao.InfoDao;
import dao.SignDao;
import dao.UserDao;
import dao.impl.InfoDaoImpl;
import dao.impl.SignDaoImpl;
import dao.impl.UserDaoImpl;

/**
 * @author Ji Rui
 */

public class DaoFactory {
    public static UserDao getUserDaoInstance() {
        return new UserDaoImpl();
    }

    public static InfoDao getInfoDaoInstance() {
        return new InfoDaoImpl();
    }

    public static SignDao getSignDaoInstance(){
        return new SignDaoImpl();
    }

}
