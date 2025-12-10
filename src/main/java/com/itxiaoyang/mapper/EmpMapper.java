package com.itxiaoyang.mapper;

import com.itxiaoyang.pojo.Emp;
import com.itxiaoyang.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

//员工信息的接口
@Mapper
public interface EmpMapper {
    //条件查询员工信息
    public List<Emp> list(EmpQueryParam empQueryParam);


    //查询全部员工
    @Select("select * from emp")
    public List<Emp> allList();

    //新增员工基本信息
    @Options(useGeneratedKeys = true,keyProperty = "id")//用这个注解就可以获取到生成的主键id，然后就可以用来绑定工作经历，不然后面增加工作经历不知道是哪个员工的
    @Insert("insert into emp(username,name,gender,phone,job,salary,image,entry_date,dept_id,create_time,update_time)" +
            "values(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);


    //批量删除员工的基本信息
    void deleteByIds(List<Integer> ids);

    //根据id查询员工信息
    Emp getById(Integer id);

    //修改员工基本信息
    void updateById(Emp emp);

    //统计员工职位人数
    @MapKey("pos")//告诉MyBatis，这个Map的key是pos,正常来说不加也行，但是插件会误报
    List<Map<String,Object>> countEmpJobData();


    //统计员工性别人数
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    //登录,根据用户名和密码查询员工
    @Select("select id,username,name from emp where username = #{username} and password = #{password}")
    Emp selectByNameAndPassword(Emp emp);
}















//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc")
//    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    //-------------------------以下是原始的分页查询---------------------------------
//    //查询总记录数
//    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
//    public Long Count();
//
//    //分页查询
//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id " +
//            "order by e.update_time desc limit #{start},#{pageSize}")
//    public List<Emp> list(Integer start,Integer pageSize);
//}
