package com.mystudy.jdbc;

import com.spi.DataBaseDriver;

public class JdbcDriver implements DataBaseDriver {
    @Override
    public String connect(String hospt) {
        return "jdbc connect";
    }
}
