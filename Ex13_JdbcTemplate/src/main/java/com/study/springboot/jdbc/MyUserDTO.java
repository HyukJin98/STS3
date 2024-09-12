package com.study.springboot.jdbc;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyUserDTO {

    private int deptno;
    private String dname;
    private String loc;
        
}
