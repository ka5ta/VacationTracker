package com.example.VTracker.controllers;

import com.example.VTracker.entities.LeaveRequest;
import com.example.VTracker.entities.User;
import com.example.VTracker.errorAPI.ApiError;
import com.example.VTracker.errorAPI.NoSuchUserException;
import com.example.VTracker.repository.LeaveRepository;
import com.example.VTracker.repository.UserHolidaysBalanceRepository;
import com.example.VTracker.services.LeaveService;
import com.example.VTracker.services.UserService;
import com.example.VTracker.staticCategories.LeaveStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/leave")
public class LeaveController {

    @Autowired
    private LeaveRepository leaveRepository;
    @Autowired
    private LeaveService leaveService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserHolidaysBalanceRepository userHolidaysBalanceRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LeaveRequest save(@RequestBody LeaveRequest leaveRequest) {
        return leaveService.save(leaveRequest);
    }
    //todo why user object is with null fields in response?


    @GetMapping("/by-user/{adUserId}")
    @ResponseStatus(HttpStatus.OK)
    public Iterable<LeaveRequest> getByRequester(@PathVariable("adUserId") String id) throws NoSuchUserException {
        User userByAdUserID = userService.findUserByAdUserID(id);
        return leaveRepository.getLeaveRequestsByUser(userByAdUserID);

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LeaveRequest getById(@PathVariable Integer id) throws ApiError {
        return leaveRepository.findById(id).orElseThrow(() -> new ApiError());
    }
    // todo noSuchEntityException

    @PatchMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Leave status is updated.")
    public ResponseEntity<Void> update(@Valid @PathVariable Integer id, @RequestBody Map<String, Object> updates) {

        Optional<LeaveRequest> leaveRequestByIdOptional = leaveRepository.findById(id);
        if(!leaveRequestByIdOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }

        LeaveRequest leaveRequest = leaveRequestByIdOptional.get();
        leaveService.updatePartiallyRequest(leaveRequest, updates);
        return ResponseEntity.noContent().build();
    }
    //todo look at JsonPatch and JsonMergePatch

}
