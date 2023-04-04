package com.example.web.service;

import com.example.web.entity.Salary;
import com.example.web.mapper.SalaryMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class TestService2 {

    @Resource
    private SalaryMapper salaryMapper;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void insertAnotherSalary(Float salary) {
        salaryMapper.insert(Salary.builder().salary(salary).build());
//        throw new RuntimeException("service error");
    }
}
