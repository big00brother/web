package com.example.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

    List<Employee> selectEmployee();

    List<Employee> selectEmployeeList();

}
