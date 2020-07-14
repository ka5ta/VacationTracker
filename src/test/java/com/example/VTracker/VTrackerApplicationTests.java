package com.example.VTracker;

import com.example.VTracker.services.LeaveService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class VTrackerApplicationTests {

    @Autowired
    private LeaveService leaveService;

    @Test
    public void isWeekendTest() {
        LocalDate date = LocalDate.of(2020, 6, 28);
        assertTrue(leaveService.isWeekend(date));

    }

    @Test
    public void isNotWeekendTest() {
        LocalDate date = LocalDate.of(2020, 6, 29);
        assertFalse(leaveService.isWeekend(date));
    }

    @Test
    public void isCountWeekDaysCorrectTest() {

//todo https://www.baeldung.com/spring-jpa-test-in-memory-database
    }


}
