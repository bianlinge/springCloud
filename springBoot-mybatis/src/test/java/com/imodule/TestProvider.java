package com.imodule;

import com.google.gson.Gson;
import com.imodule.dao.entity.Category;
import com.imodule.dao.entity.CategoryExample;
import com.imodule.dao.mapper.ContactsMapper;
import com.imodule.dao.provider.CategoryDao;
import com.imodule.dao.provider.CategorySqlProvider;
import com.imodule.dao.provider.Inputparam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan({"com.imodule.dao.mapper","com.imodule.dao.provider"})
public class TestProvider {
    @Autowired
    CategoryDao categoryDao;
    @Autowired
    ContactsMapper contactsMapper;
    @Test
    public void testProvider(){
        Category category = categoryDao.selectByPrimaryKey("1");
        Gson gson = new Gson();
        String s = gson.toJson(category);
        System.out.println(s);
    }
    @Test
    public void testIds(){
       Inputparam inputparam = new Inputparam();
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("1-1");
        list.add("1-10");
        inputparam.setIds((ArrayList<String>) list);
        /*CategoryExample example = new CategoryExample();
        example.createCriteria().andIdIn(list);
        List<Category> categories = categoryDao.selectByExample(example);
        System.out.println(new Gson().toJson(categories));*/

        CategorySqlProvider categorySqlProvider = new CategorySqlProvider();
        String sql = categorySqlProvider.selectByIds(inputparam);
        CategoryExample example = new CategoryExample();
        example.createCriteria().andIdIn(list);
        String sqll = categorySqlProvider.selectByExample(example);
        System.out.println(sqll);
       /* SELECT id, name, label, parentid, isshow, orderby, isleaf
        FROM category
        WHERE ((id in (#{oredCriteria[0].allCriteria[0].value[0]},
         #{oredCriteria[0].allCriteria[0].value[1]},
        #{oredCriteria[0].allCriteria[0].value[2]})))*/
        System.out.println("----------------------------");
        System.out.println(sql);

        List<Category> categories = categoryDao.selectByIds(inputparam);
        System.out.println(new Gson().toJson(categories));
    }

    @Test
    public void testInsertdata() {

        List<Map<String, Object>> lables = new ArrayList<>();
        HashMap<String, Object> map1 = new HashMap<>();
       // map1.put("name", "aaaa");
        map1.put("contactsId", "aaaa");
        map1.put("interest", null);
        map1.put("infoRemarks", "aaaa");
       // map1.put("type", "aaaa");
        map1.put("labelName", "aaaa");
        map1.put("contents", null);
        map1.put("sortno", "aaaa");
       // map1.put("createUser", "aaaa");
        //map1.put("updateUser", "aaaa");


        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("name", "bbbb");
        map2.put("contactsId", "bbbb");
        map2.put("interest", "bbbb");
        map2.put("infoRemarks", null);
        //map2.put("type", "bbbb");
        map2.put("labelName", "bbbb");
        //map2.put("contents","bbbb");
        map2.put("sortno","bbbb");
        map2.put("createUser",null);
        map2.put("updateUser","bbbb");
        lables.add(map1);
        lables.add(map2);
        int i = contactsMapper.addPriInfoLabel(lables);
        if (i> 0) {
            System.out.println("insert successful"+"========================="+i);
        }
    }

    @Test
    public void testBigDeciaml(){
        String decimal = "123,123,12".replace(",", "");
        BigDecimal bigDecimal = new BigDecimal(decimal);
        System.out.println(bigDecimal);
    }
}
