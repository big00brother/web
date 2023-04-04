package com.example.web.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.web.entity.Employee;
import com.example.web.entity.Salary;
import com.example.web.mapper.EmployeeMapper;
import com.example.web.mapper.SalaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {

    ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @Resource
    private SalaryMapper salaryMapper;

    @Resource
    private EmployeeMapper employeeMapper;

    @Autowired
    private TestService2 testService2;
    @Transactional
    public List<Salary> getList() {
        threadLocal.set("12");
        return salaryMapper.selectList(Wrappers.lambdaQuery(Salary.class).last("limit 5"));
    }

    public IPage<Salary> getPageList() {
        LambdaQueryWrapper<Salary> queryWrapper = Wrappers.lambdaQuery(Salary.class);
        IPage<Salary> buildPage = new Page<>(2, 5);
        // ServiceImpl的page调用的是baseMapper的selectPage, getBaseMapper().selectPage()
        IPage<Salary> page = salaryMapper.selectPage(buildPage, queryWrapper);
        return page;
    }

//    @Transactional
    public Salary getSalaryById(Long id) {
        Salary salary1 = salaryMapper.selectSalaryByID(id);
        Salary salary2 = salaryMapper.selectSalaryByID(id);
        System.out.println("同一个sqlSession若开启一级缓存，则salary1 == salary2 : " + (salary1 == salary2));
        return salary2;
    }

    public IPage<Salary> selectPage() {
        Page<Salary> buildPage = new Page<>(2, 5);
        IPage<Salary> page = salaryMapper.selectPageList(buildPage);
        return page;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void insertSalary(Float salary) {
        int nums = salaryMapper.insert(Salary.builder().salary(salary).build());
        testService2.insertAnotherSalary(salary + 100);
        throw new RuntimeException("service error");

    }

    @Transactional(propagation = Propagation.NEVER)
    public void insertAnotherSalary(Float salary) {
        salaryMapper.insert(Salary.builder().salary(salary).build());
//        throw new RuntimeException("service error");
    }

    public List<Employee> selectEmployee() {
        List<Employee> employeeList = employeeMapper.selectEmployee();
        return employeeList;
    }

    public List<Employee> selectEmployeeList() {
        List<Employee> employeeList = employeeMapper.selectEmployeeList();
        System.out.println("salary延迟加载之前");
        List<Employee> copyEmployeeList = copyEmployeeList(employeeList);
        return copyEmployeeList;
    }

    private List<Employee> copyEmployeeList(List<Employee> employeeList) {
        List<Employee> copyEmployeeList = new ArrayList<>();
        for(Employee employee : employeeList) {
            Employee copyEmployee = new Employee(employee.getId(), employee.getName(), null);
            copyEmployeeList.add(copyEmployee);
        }
        return copyEmployeeList;
    }
}
