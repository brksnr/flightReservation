package com.example.demo.controller;

import com.example.demo.entity.Travel;
import com.example.demo.service.MemberReservationService;
import com.example.demo.exceptions.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class MemberReservationController {

    private final MemberReservationService memberReservationService;

    @Autowired
    public MemberReservationController(MemberReservationService memberReservationService) {
        this.memberReservationService = memberReservationService;
    }

    @PostMapping("/create/{plannedId}/{memberId}/{creditCardId}")
    public ResponseEntity<Travel> createReservation(
            @PathVariable Long plannedId,
            @PathVariable Long memberId,
            @PathVariable Long creditCardId,
            @RequestBody Travel travel) {

        Travel createdTravel = memberReservationService.createReservation(plannedId, memberId, creditCardId, travel);
        return ResponseEntity.ok(createdTravel);
    }

    @DeleteMapping("/delete/{memberId}/{travelId}")
    public ResponseEntity<String> deleteReservation(@PathVariable Long memberId, @PathVariable Long travelId){
        memberReservationService.deleteReservation(memberId, travelId);
        return ResponseEntity.ok("Reservasyonunuz iptal edildi!");
    }
}
