package com.imodule.dao.provider;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.ORDER_BY;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT_DISTINCT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.imodule.dao.entity.Category;
import com.imodule.dao.entity.CategoryExample.Criteria;
import com.imodule.dao.entity.CategoryExample.Criterion;
import com.imodule.dao.entity.CategoryExample;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.jdbc.SQL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategorySqlProvider {

    public String countByExample(CategoryExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("category");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(CategoryExample example) {
        BEGIN();
        DELETE_FROM("category");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(Category record) {
        BEGIN();
        INSERT_INTO("category");

        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=VARCHAR}");
        }

        if (record.getName() != null) {
            VALUES("name", "#{name,jdbcType=VARCHAR}");
        }

        if (record.getLabel() != null) {
            VALUES("label", "#{label,jdbcType=VARCHAR}");
        }

        if (record.getParentid() != null) {
            VALUES("parentid", "#{parentid,jdbcType=VARCHAR}");
        }

        if (record.getIsshow() != null) {
            VALUES("isshow", "#{isshow,jdbcType=CHAR}");
        }

        if (record.getOrderby() != null) {
            VALUES("orderby", "#{orderby,jdbcType=INTEGER}");
        }

        if (record.getIsleaf() != null) {
            VALUES("isleaf", "#{isleaf,jdbcType=CHAR}");
        }

        return SQL();
    }

    public String selectByExample(CategoryExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("id");
        } else {
            SELECT("id");
        }
        SELECT("name");
        SELECT("label");
        SELECT("parentid");
        SELECT("isshow");
        SELECT("orderby");
        SELECT("isleaf");
        FROM("category");
        applyWhere(example, false);

        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }

        return SQL();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        Category record = (Category) parameter.get("record");
        CategoryExample example = (CategoryExample) parameter.get("example");

        BEGIN();
        UPDATE("category");

        if (record.getId() != null) {
            SET("id = #{record.id,jdbcType=VARCHAR}");
        }

        if (record.getName() != null) {
            SET("name = #{record.name,jdbcType=VARCHAR}");
        }

        if (record.getLabel() != null) {
            SET("label = #{record.label,jdbcType=VARCHAR}");
        }

        if (record.getParentid() != null) {
            SET("parentid = #{record.parentid,jdbcType=VARCHAR}");
        }

        if (record.getIsshow() != null) {
            SET("isshow = #{record.isshow,jdbcType=CHAR}");
        }

        if (record.getOrderby() != null) {
            SET("orderby = #{record.orderby,jdbcType=INTEGER}");
        }

        if (record.getIsleaf() != null) {
            SET("isleaf = #{record.isleaf,jdbcType=CHAR}");
        }

        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("category");

        SET("id = #{record.id,jdbcType=VARCHAR}");
        SET("name = #{record.name,jdbcType=VARCHAR}");
        SET("label = #{record.label,jdbcType=VARCHAR}");
        SET("parentid = #{record.parentid,jdbcType=VARCHAR}");
        SET("isshow = #{record.isshow,jdbcType=CHAR}");
        SET("orderby = #{record.orderby,jdbcType=INTEGER}");
        SET("isleaf = #{record.isleaf,jdbcType=CHAR}");

        CategoryExample example = (CategoryExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(Category record) {
        BEGIN();
        UPDATE("category");

        if (record.getName() != null) {
            SET("name = #{name,jdbcType=VARCHAR}");
        }

        if (record.getLabel() != null) {
            SET("label = #{label,jdbcType=VARCHAR}");
        }

        if (record.getParentid() != null) {
            SET("parentid = #{parentid,jdbcType=VARCHAR}");
        }

        if (record.getIsshow() != null) {
            SET("isshow = #{isshow,jdbcType=CHAR}");
        }

        if (record.getOrderby() != null) {
            SET("orderby = #{orderby,jdbcType=INTEGER}");
        }

        if (record.getIsleaf() != null) {
            SET("isleaf = #{isleaf,jdbcType=CHAR}");
        }

        WHERE("id = #{id,jdbcType=VARCHAR}");

        return SQL();
    }

    protected void applyWhere(CategoryExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }

        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }

        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }

                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }

                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }

        if (sb.length() > 0) {
            WHERE(sb.toString());
        }
    }

    public String selectByIds(Inputparam inputparam) {
        return new SQL() {
            {
                SELECT("*");
                FROM("category");
                ArrayList<String> ids = inputparam.getIds();
                if (ids!=null&&ids.size()>0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < ids.size(); i++) {
                        sb.append(" or id = "+ids.get(i));
                    }
                    WHERE("id="+ids.get(0)+" "+sb);
                }
            }

        }.toString();
    }
}