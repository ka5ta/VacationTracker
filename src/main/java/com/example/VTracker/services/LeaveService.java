package com.example.VTracker.services;

import com.example.VTracker.entities.BankHoliday;
import com.example.VTracker.entities.Country;
import com.example.VTracker.entities.LeaveRequest;
import com.example.VTracker.repository.HolidaysRepository;
import com.example.VTracker.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private HolidaysRepository holidaysRepository;

    public boolean isWeekend(LocalDate day) {
        DayOfWeek weekDay = day.getDayOfWeek();
        return weekDay == DayOfWeek.SATURDAY || weekDay == DayOfWeek.SUNDAY;
    }

    public void countLeaveDays(LeaveRequest leaveRequest) {
        Country country = leaveRequest.getUser().getCountry();
        LocalDate startDate = leaveRequest.getStartDate();
        LocalDate endDate = leaveRequest.getEndDate();

        Collection<BankHoliday> allHolidaysByCountryAndDateRange =
                holidaysRepository.findAllHolidaysByCountryAndDateRange(
                        country, startDate, endDate
                );

        // var sortedHolidays = sort(allHolidays)
        // var nextHoliday = sortedHolidays.first()

        int countDays = 0;
        for(LocalDate date = startDate; date.isBefore(endDate); date=date.plusDays(1)){
            if(isWeekend(date)){
                continue;
            }else {
                LocalDate finalDate = date;
                // if (date == nextHoliday) {
                //   nextHoliday = sortedHolidays.after(nextHoliday)
                //   continue;
                // }
                if(allHolidaysByCountryAndDateRange.stream()
                                .anyMatch(day -> day.getDay().equals(finalDate))
                ){
                    continue;
                }else{
                    countDays++;
                }
            }
        }
        leaveRequest.setLeaveCost(countDays);
    }


}
