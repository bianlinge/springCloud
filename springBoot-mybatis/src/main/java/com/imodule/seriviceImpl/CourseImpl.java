package com.imodule.seriviceImpl;


import com.imodule.dao.entity.Category;
import com.imodule.dao.entity.CategoryExample;
import com.imodule.dao.mapper.CategoryMapper;
import com.imodule.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseImpl implements CourseService {
    @Autowired
    CategoryMapper categoryMapper;//此处报红因为动态生成代理 此时没有生成
    @Override
    public List<Category> searchCourse() {
        CategoryExample example = new CategoryExample();
        CategoryExample.Criteria criteria = example.createCriteria().andIdIsNotNull();
        return categoryMapper.selectByExample(example);
    }

    @Override
    public Category findOne(String id) {
        Category category = categoryMapper.selectByPrimaryKey(id);
        return category;
    }

}
