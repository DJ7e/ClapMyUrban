package com.dbms.UrbanClaps;

import com.dbms.UrbanClaps.dao.ServicesProvidedDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

@ExtendWith(MockitoExtension.class)
public class SomeTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ServicesProvidedDao underTest;

    @Test
    public void test1(){

    }

}
