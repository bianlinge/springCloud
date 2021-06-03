package com.dove.service;

import com.dove.dao.entity.Category;

import java.util.List;

public interface CourseService {
    List<Category> searchCourse();
    Category findOne(String id);
}
