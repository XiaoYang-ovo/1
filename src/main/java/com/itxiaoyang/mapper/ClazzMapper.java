package com.itxiaoyang.mapper;

import com.itxiaoyang.pojo.Clazz;
import com.itxiaoyang.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {
    List<Clazz> list(ClazzQueryParam clazzQueryParam);//查询班级

    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);//删除班级


    @Insert("insert into clazz(name,room,begin_date,end_date,master_id,subject,create_time,update_time)" +
            "values(#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void insert(Clazz clazz);//新增班级



    Clazz getById(Integer id);//根据id查询班级信息


    void updateById(Clazz clazz);//修改班级信息

    @Select("select * from clazz")
    List<Clazz> allList();//查询所有班级
}
