package com.dove.seriviceImpl;


import com.dove.dao.entity.Category;
import com.dove.service.CourseService;
import com.dove.dao.entity.CategoryExample;
import com.dove.dao.mapper.CategoryMapper;
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
