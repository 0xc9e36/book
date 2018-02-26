package com.bie.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bie.po.UserInfo;
import com.bie.system.dao.UserInfoDao;
import com.bie.system.dao.impl.UserInfoDaoImpl;
import com.bie.system.service.UserInfoInsertService;
import com.bie.utils.BaseDao;
import com.bie.utils.DbUtils;
import com.bie.utils.HelpUtils;
import com.bie.utils.MarkUtils;

/***
 * 1.4:这是业务逻辑层的实现类，实现用户信息的接口
 *
 * 切忌新手写好service业务逻辑层需要test测试(junit)
 * @author biehongli
 *
 */
public class UserInfoInsertServiceImpl implements UserInfoInsertService {

    private UserInfoDao userInfoDao = new UserInfoDaoImpl();

    @Override
    public boolean insertUser(UserInfo user) {
        try {

            if (user != null && user.getUserAccount() != null) {

                String sql = "INSERT INTO user_info(user_account,user_pw,user_number,user_name,user_age,user_sex,user_mark) VALUES(?,?,?,?,?,?,?)";

                List<Object> list = new ArrayList<>();


                //加入List顺序和sql语句中占位符一致
                list.add(user.getUserAccount().trim());

                //密码使用md5加密存储
                String secret = HelpUtils.str2Md5(user.getUserPw());
                list.add(secret);

                list.add(user.getUserNumber().trim());
                list.add(user.getUserName().trim());
                list.add(user.getUserAge());
                list.add(user.getUserSex().trim());
                list.add(user.getUserMark().trim());


                //将封装到集合list中的信息和sql语句传递到DbUtils封装好的 方法中
                //这里sql转化位String语句，list转化为数组类型
                int count = BaseDao.addAndUpdate(sql.toString(), list.toArray());
                //System.out.println(count);//测试返回值是0还是1
                return count > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public UserInfo login(UserInfo user) {
        UserInfo login = new UserInfo();
        if (user != null && user.getUserAccount() != null && user.getUserPw() != null && !"".equals(user.getUserAccount()) && !"".equals(user.getUserPw())) {
            login = userInfoDao.login(user);
        }
        return login;
    }


    @Override
    public List<UserInfo> selectUser(UserInfo user) {
        StringBuilder sql = new StringBuilder("select * from user_info where 1=1 ");
        List<Object> list = new ArrayList<Object>();

        if (user != null) {
            //按照姓名查询
            if (user.getUserName() != null) {
                sql.append(" and user_name like ? ");
                list.add("%" + user.getUserName() + "%");
            }
            //按照用户账号查询
            if (user.getUserAccount() != null) {
                sql.append(" and user_account like ? ");
                list.add("%" + user.getUserAccount() + "%");
            }

        }
        sql.append(" order by user_id asc ");
        return userInfoDao.selectUser(sql.toString(), list.toArray());
    }

    @Override
    public boolean deleteUser(UserInfo user) {
        try {
            //不做伪删除，这里做真删除
            String sql = "DELETE FROM user_info WHERE user_id=? ";
            List<Object> list = new ArrayList<Object>();
            list.add(user.getUserId());

            boolean mark = BaseDao.addUpdateDelete(sql, list.toArray());
            return mark;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public boolean updateUser(UserInfo user) {
        try {
            System.out.println(user.getUserPw());

            String pass = "";
            //密码有修改
            if ((null != user.getUserPw()) && (user.getUserPw().length() > 0)) {
                pass = "user_pw=?,";
            }

            String sql = "update user_info set user_account=?," + pass
                    + "user_number=?,user_name=?,user_age=?,user_sex=?,user_mark=?  where user_id=? ";
            List<Object> list = new ArrayList<Object>();

            if (null != user) {
                list.add(user.getUserAccount().trim());
                if (0 != pass.length()) {
                    //密码使用md5加密存储
                    String secret = HelpUtils.str2Md5(user.getUserPw());
                    list.add(secret);
                }
                list.add(user.getUserNumber().trim());
                list.add(user.getUserName().trim());
                list.add(user.getUserAge());
                list.add(user.getUserSex().trim());
                list.add(user.getUserMark().trim());
                list.add(user.getUserId());
            }
            boolean mark = BaseDao.addUpdateDelete(sql, list.toArray());
            return mark;
        } catch (Exception e) {

        }
        return false;
    }

    @Override
    public UserInfo getUserId(UserInfo user) {
        try {
            if (user != null) {
                return userInfoDao.getUserId(user.getUserId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean registerUser(UserInfo user) {
        try {
            if (user != null && user.getUserAccount() != null) {
                String sql = "INSERT INTO user_info(user_account,user_pw, user_number,user_name,user_age,user_sex,user_mark) VALUES(?,?,?,?,?,?,?)";
                //只允许普通用户注册
                user.setUserMark(MarkUtils.USER_MARK_MEMBER);

                List<Object> list = new ArrayList<Object>();
                list.add(user.getUserAccount().trim());
                //密码使用md5加密存储
                String secret = HelpUtils.str2Md5(user.getUserPw());
                list.add(secret);
                list.add(user.getUserNumber().trim());
                list.add(user.getUserName().trim());
                list.add(user.getUserAge());
                list.add(user.getUserSex().trim());
                list.add(user.getUserMark().trim());

                //将封装到集合list中的信息和sql语句传递到DbUtils封装好的 方法中
                //这里sql转化位String语句，list转化位数组类型
                int count = BaseDao.addAndUpdate(sql.toString(), list.toArray());
                return count > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}


