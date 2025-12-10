package com.itxiaoyang.mapper;

import com.itxiaoyang.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    //查询所有的部门数据
    @Select("SELECT id,name,create_time,update_time from dept ORDER BY update_time desc")
    List<Dept> findAll();

    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);//根据id删除部门

    @Insert("insert into dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);//添加部门

    @Select("select id,name,create_time,update_time from dept where id = #{id}")
    Dept getById(Integer id);//根据id查询部门

    @Update("update dept set name = #{name},update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);//修改部门信息
}
