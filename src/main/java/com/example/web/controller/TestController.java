package com.example.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.web.entity.Employee;
import com.example.web.entity.Salary;
import com.example.web.request.SalaryParam;
import com.example.web.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/get/data")
    @ResponseBody
    public List<Salary> getList() {
        return testService.getList();
    }

    @GetMapping("/get/page")
    @ResponseBody
    public IPage<Salary> getPage() {
        return testService.getPageList();
    }

    @GetMapping("/get/salary/by_id")
    @ResponseBody
    public Salary getSalaryById(@RequestParam("id") Long id) {
        return testService.getSalaryById(id);
    }

    @GetMapping("/select/page")
    @ResponseBody
    public IPage<Salary> selectPage() {
        return testService.selectPage();
    }

    @PostMapping("/insert/salary")
    @ResponseBody
    public void insertSalary(@RequestBody SalaryParam param) {
        testService.insertSalary(param.getSalary());
    }

    @GetMapping("/get/employee")
    @ResponseBody
    public List<Employee> getEmployeeList() {
        List<Employee> employeeList = testService.selectEmployee();
        return employeeList;
    }

    @GetMapping("/select/employee")
    @ResponseBody
    public List<Employee> selectEmployeeList() {
        List<Employee> employeeList = testService.selectEmployeeList();
        return employeeList;
    }

    @GetMapping("/login")
    @ResponseBody
    public String login(HttpServletRequest request){
        HttpSession session = request.getSession(true);
        return "login success";
    }
}
