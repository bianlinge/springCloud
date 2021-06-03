package com.dove.dao.provider;

import com.dove.dao.entity.Category;
import com.dove.dao.entity.CategoryExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.web.bind.annotation.RequestParam;

public interface CategoryDao {
    @SelectProvider(type=CategorySqlProvider.class, method="countByExample")
    int countByExample(CategoryExample example);

    @DeleteProvider(type=CategorySqlProvider.class, method="deleteByExample")
    int deleteByExample(CategoryExample example);

    @Delete({
        "delete from category",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into category (id, name, ",
        "label, parentid, ",
        "isshow, orderby, isleaf)",
        "values (#{id,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{label,jdbcType=VARCHAR}, #{parentid,jdbcType=VARCHAR}, ",
        "#{isshow,jdbcType=CHAR}, #{orderby,jdbcType=INTEGER}, #{isleaf,jdbcType=CHAR})"
    })
    int insert(Category record);

    @InsertProvider(type=CategorySqlProvider.class, method="insertSelective")
    int insertSelective(Category record);

    @SelectProvider(type=CategorySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="label", property="label", jdbcType=JdbcType.VARCHAR),
        @Result(column="parentid", property="parentid", jdbcType=JdbcType.VARCHAR),
        @Result(column="isshow", property="isshow", jdbcType=JdbcType.CHAR),
        @Result(column="orderby", property="orderby", jdbcType=JdbcType.INTEGER),
        @Result(column="isleaf", property="isleaf", jdbcType=JdbcType.CHAR)
    })
    List<Category> selectByExample(CategoryExample example);

    @Select({
        "select",
        "id, name, label, parentid, isshow, orderby, isleaf",
        "from category",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="label", property="label", jdbcType=JdbcType.VARCHAR),
        @Result(column="parentid", property="parentid", jdbcType=JdbcType.VARCHAR),
        @Result(column="isshow", property="isshow", jdbcType=JdbcType.CHAR),
        @Result(column="orderby", property="orderby", jdbcType=JdbcType.INTEGER),
        @Result(column="isleaf", property="isleaf", jdbcType=JdbcType.CHAR)
    })
    Category selectByPrimaryKey(String id);

    @UpdateProvider(type=CategorySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    @UpdateProvider(type=CategorySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    @UpdateProvider(type=CategorySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Category record);

    @Update({
        "update category",
        "set name = #{name,jdbcType=VARCHAR},",
          "label = #{label,jdbcType=VARCHAR},",
          "parentid = #{parentid,jdbcType=VARCHAR},",
          "isshow = #{isshow,jdbcType=CHAR},",
          "orderby = #{orderby,jdbcType=INTEGER},",
          "isleaf = #{isleaf,jdbcType=CHAR}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Category record);

    @SelectProvider(type=CategorySqlProvider.class, method="selectByIds")
    List<Category> selectByIds(@RequestParam(value = "inputparam",required = true) Inputparam inputparam);
}