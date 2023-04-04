package com.example.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.web.entity.Salary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SalaryMapper extends BaseMapper<Salary> {

    Salary selectSalaryByID(@Param("id") Long id);

    IPage<Salary> selectPageList(@Param("page")Page<Salary> page);
}
