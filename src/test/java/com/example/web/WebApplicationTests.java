package com.example.web;


import com.example.web.entity.Salary;
import com.example.web.mapper.SalaryMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebApplicationTests {

    @Autowired
    private SalaryMapper salaryMapper;

    @Test
    public void contextLoads() {
        List<Salary> list = salaryMapper.selectList(null);
        System.out.println(list);
    }

}
