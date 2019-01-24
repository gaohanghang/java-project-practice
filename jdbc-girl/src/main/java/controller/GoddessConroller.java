package controller;

import dao.GoddessDao;
import model.Goddess;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/23 19:22
 */
public class GoddessConroller {
    public static void main(String[] args) throws Exception {
        // GoddessDao goddessDao = new GoddessDao();
        //
        // List<Goddess> gs = goddessDao.query();
        //
        // for (Goddess goddess : gs) {
        //     System.out.println(goddess.getUserName() + "," + goddess.getAge());
        // }

        GoddessDao goddessDao = new GoddessDao();
        Goddess goddess = new Goddess();

        goddess.setUserName("小夏");
        goddess.setAge(19);
        goddess.setSex(1);
        goddess.setBirthday(new Date());
        goddess.setEmail("1111@qq.con");
        goddess.setMobile("189999999");
        goddess.setUpdateUser("ADMIN");
        goddess.setIsdel(1);
        goddess.setId(3);

        Goddess goddess1 = goddessDao.get(4);
        System.out.println(goddess1.toString());

        // goddessDao.delGoddess(3);

        // goddessDao.updateGoddess(goddess);

        // goddessDao.addGoddess(goddess);


        // 根据名称查询
        List<Goddess> result = goddessDao.query("小美");
        for (Goddess goddess2 : result) {
            System.out.println(goddess2.toString());
        }
    }
}
