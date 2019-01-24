import controller.GoddessConroller;
import model.Goddess;

import java.util.*;

/**
 * @Description:
 * @author: Gao Hang Hang
 * @date 2019/01/24 19:41
 */
public class Test {
    public static void main(String[] args) throws Exception {
        GoddessConroller conroller = new GoddessConroller();


        /* 查询
        List<Goddess> query = conroller.query();
        for (Goddess goddess : query) {
            System.out.println(goddess);
        }*/

        // 创建
        // Goddess goddess = new Goddess();
        // goddess.setUserName("小青1");
        // goddess.setAge(19);
        // goddess.setSex(1);
        // goddess.setBirthday(new Date());
        // goddess.setEmail("1111@qq.con");
        // goddess.setMobile("189999999");
        // goddess.setUpdateUser("ADMIN");
        // goddess.setIsdel(1);
        // goddess.setId(5);

        // conroller.add(goddess);

        // conroller.edit(goddess);

        // conroller.delete(5);

        // List<Map<String, Object>> params = new ArrayList();
        // Map<String, Object> param = new HashMap<String, Object>();
        // param.put("name", "user_name");
        // param.put("rela", "like");
        // param.put("value", "'%小%'");
        // params.add(param);
        // param = new HashMap<String, Object>();
        // param.put("name", "mobile");
        // param.put("rela", "like");
        // param.put("value", "'%1%'");
        // params.add(param);
        // List<Goddess> result = conroller.query(params);
        // for (Goddess goddess2 : result) {
        //     System.out.println(goddess2.toString());
        // }
    }
}
