package com.example.VTracker.services;

import com.example.VTracker.entities.BankHoliday;
import com.example.VTracker.entities.Country;
import com.example.VTracker.entities.LeaveRequest;
import com.example.VTracker.repository.HolidaysRepository;
import com.example.VTracker.repository.LeaveRepository;
import com.example.VTracker.staticCategories.LeaveStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

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

        List<BankHoliday> allHolidaysByCountryAndDateRange =
                holidaysRepository.findAllHolidaysByCountryAndDateRange(
                        country, startDate, endDate
                );

/*        Collections.sort(allHolidaysByCountryAndDateRange,
                new Comparator<BankHoliday>() {
                    @Override
                    public int compare(BankHoliday h1, BankHoliday h2) {
                        return h1.getDay().compareTo(h2.getDay());
                    }
                });*/


// < ------ this will  order items from early date till later date ---->
        allHolidaysByCountryAndDateRange.sort(Comparator.comparing(b -> b.getDay()));


        // LocalDate nextHoliday = allHolidaysByCountryAndDateRange.get(0).getDay();
        int countDays = 0;
        for (LocalDate date = startDate; date.isBefore(endDate) || date.isEqual(endDate); date = date.plusDays(1)) {
            if (isWeekend(date)) {
                continue;
            }

            //if(nextHoliday!=null) {
            //todo iterate through sorted bank holidays
            final LocalDate currentDate = date;
            if (allHolidaysByCountryAndDateRange.stream()
                    .anyMatch(day -> day.getDay().equals(currentDate))
            ) {
                continue;
            }
            //}

            countDays++;
        }
        leaveRequest.setLeaveCost(countDays);
    }


    public LeaveRequest save(LeaveRequest leaveRequest) {
        countLeaveDays(leaveRequest);
        return leaveRepository.save(leaveRequest);
    }

    public void updatePartiallyRequest(LeaveRequest leaveRequest, Map<String, Object> updates) {

        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            switch (entry.getKey()) {
                case "status":
                    String k = (String) entry.getValue();
                    LeaveStatus leaveStatus = LeaveStatus.valueOf(k);
                    leaveRequest.setLeaveStatus(leaveStatus);
                    break;
                //case "approver":
                //todo add approver change here
                default:
                    throw new IllegalArgumentException("Wrong key name");

            }
            leaveRepository.save(leaveRequest);

        }

    }


}
