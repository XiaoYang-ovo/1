package com.itxiaoyang.controller;

import com.itxiaoyang.pojo.Emp;
import com.itxiaoyang.pojo.EmpQueryParam;
import com.itxiaoyang.pojo.PageResult;
import com.itxiaoyang.pojo.Result;
import com.itxiaoyang.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

//员工管理
@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    //条件分页查询
    @GetMapping
    public Result page(EmpQueryParam empQueryParam){//如果参数太多就可以封装到对象里面
        log.info("分页查询,参数:{}",empQueryParam);//记录日志
        PageResult<Emp> pageResult = empService.page(empQueryParam);//调用service分页查询
        return Result.success(pageResult);//把结果封装到对象返回给前端
    }


    //新增员工
    @PostMapping
    public Result save(@RequestBody Emp emp){//加了这个注解就可以把前端json参数传递给对象,上面不需要是因为不是json格式
        log.info("新增员工:{}",emp);
        empService.save(emp);
        return Result.success();
    }

    //员工删除---数组
//    @DeleteMapping
//    public Result delete(Integer[] ids){
//
//        log.info("员工删除:{}", Arrays.toString(ids));
//        return Result.success();
//    }

    //员工删除---list集合
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){//复杂集合之类的就要用这个注解
        log.info("员工删除:{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    //根据id查询员工信息
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){//这个注解可以把路径参数传给对象

        log.info("查询员工信息,id:{}",id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    //修改员工信息
    @PutMapping
    public Result update(@RequestBody Emp emp){

        log.info("修改员工信息:{}"+emp);
        empService.update(emp);
        return Result.success();
    }

    //查询所有员工
    @GetMapping("/list")
    public Result list() {
        log.info("查询所有员工信息");
        List<Emp> list = empService.allList();
        return Result.success(list);
    }
}



//    //条件分页查询
//    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10")Integer pageSize,
//                       String name, Integer gender,
//                       @DateTimeFormat (pattern = "yyyy-MM-dd") LocalDate begin,
//                       @DateTimeFormat (pattern = "yyyy-MM-dd") LocalDate end){
//
//        log.info("分页查询,参数:{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);//记录日志
//        PageResult<Emp> pageResult = empService.page(page,pageSize,name,gender,begin,end);//调用service分页查询
//        return Result.success(pageResult);//把结果封装到对象返回给前端
//    }
//}
