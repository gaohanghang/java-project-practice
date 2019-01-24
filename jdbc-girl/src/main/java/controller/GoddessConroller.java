package controller;

import dao.GoddessDao;
import model.Goddess;

import java.util.*;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/23 19:22
 */
public class GoddessConroller {
    public void add(Goddess goddess) throws Exception {
        GoddessDao goddessDao = new GoddessDao();
        goddess.setSex(1);
        goddess.setCreateUser("ADMIN");
        goddess.setUpdateUser("ADMIN");
        goddess.setIsdel(0);
        goddessDao.addGoddess(goddess);
    }

    public Goddess get(Integer id) throws Exception {
        GoddessDao goddessDao = new GoddessDao();
        return goddessDao.get(id);
    }

    public void edit(Goddess goddess) throws Exception {
        GoddessDao goddessDao = new GoddessDao();
        goddessDao.updateGoddess(goddess);
    }

    public void delete(Integer id) throws Exception {
        GoddessDao goddessDao = new GoddessDao();
        goddessDao.delGoddess(id);
    }

    public List<Goddess> query() throws Exception {
        GoddessDao dao = new GoddessDao();
        return dao.query();
    }

    public List<Goddess> query(List<Map<String, Object>> params) throws Exception {
        GoddessDao dao = new GoddessDao();
        return dao.query(params);
    }

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

        // Goddess goddess1 = goddessDao.get(4);
        // System.out.println(goddess1.toString());

        // goddessDao.delGoddess(3);

        // goddessDao.updateGoddess(goddess);

        // goddessDao.addGoddess(goddess);


        // List<Goddess> result = goddessDao.query("小夏", "1","1");
        // for (Goddess goddess2 : result) {
        //     System.out.println(goddess2.toString());
        // }

        List<Map<String, Object>> params = new ArrayList();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("name", "user_name");
        param.put("rela", "like");
        param.put("value", "'%小%'");
        params.add(param);
        param = new HashMap<String, Object>();
        param.put("name", "mobile");
        param.put("rela", "like");
        param.put("value", "'%1%'");
        params.add(param);
        List<Goddess> result = goddessDao.query(params);
        for (Goddess goddess2 : result) {
            System.out.println(goddess2.toString());
        }
    }
}
