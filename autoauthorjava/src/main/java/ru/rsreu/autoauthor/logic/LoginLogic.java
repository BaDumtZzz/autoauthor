package ru.rsreu.autoauthor.logic;

import ru.rsreu.autoauthor.dao.oracle.OracleUserDao;
import ru.rsreu.autoauthor.domain.User;

import java.util.List;

public class LoginLogic {
    public static User checkLogin(String enterLogin, String enterPass) {
        OracleUserDao userDao = new OracleUserDao();
        List<User> userList = userDao.getAllWithOrgName();
        for (User user: userList){
            if (user.getNickname().equals(enterLogin) && user.getPassword().equals(PasswordEncryption.encryptPassword(enterPass))){
                return user;
            }
        }
        return null;
        //return ADMIN_LOGIN.equals(enterLogin) && ADMIN_PASS.equals(enterPass);
    }
}

