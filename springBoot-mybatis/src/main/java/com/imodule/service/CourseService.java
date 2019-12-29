package com.imodule.service;

import com.imodule.dao.entity.Category;

import java.util.List;

public interface CourseService {
    List<Category> searchCourse();
    Category findOne(String id);
}
