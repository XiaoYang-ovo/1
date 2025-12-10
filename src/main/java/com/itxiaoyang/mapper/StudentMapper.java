package com.itxiaoyang.mapper;

import com.itxiaoyang.pojo.Student;
import com.itxiaoyang.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    List<Student> list(StudentQueryParam studentQueryParam);//分页查询所有学生信息

    void deleteByIds(List<Integer> ids);//批量删除学生信息


    @Insert("insert into student(name,no,gender,phone,id_card,is_college,address,degree,graduation_date,clazz_id,create_time, update_time)" +
            "values(#{name},#{no},#{gender},#{phone},#{idCard},#{isCollege},#{address},#{degree},#{graduationDate},#{clazzId},#{createTime}, #{updateTime})")
    void insert(Student student);//添加学生信息


    @Select("select * from student where id = #{id}")
    Student getById(Integer id);//根据id查询学生信息

    void updateById(Student student);//修改学生信息

    List<Map<String, Object>> countStudentClazzData();//统计学生班级人数

    List<Map<String, Object>> countStudentDegreeData();//统计学生学历人数
}
