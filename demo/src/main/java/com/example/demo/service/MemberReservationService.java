package com.example.demo.service;

import com.example.demo.entity.CreateFlight;
import com.example.demo.entity.Seat;
import com.example.demo.entity.Travel;
import com.example.demo.entity.member.CreditCard;
import com.example.demo.entity.member.Member;
import com.example.demo.exceptions.ApiException;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class MemberReservationService {

    private final MemberReservationRepository memberReservationRepository;
    private final CreateFlightRepository createFlightRepository;
    private final MemberRepository memberRepository;
    private final CreditCardRepository creditCardRepository;
    private final SeatRepository seatRepository;
    private final TravelRepository travelRepository;
    private final PaymentRepository paymentRepository;

    @Autowired
    public MemberReservationService(MemberReservationRepository memberReservationRepository, CreateFlightRepository createFlightRepository, MemberRepository memberRepository, CreditCardRepository creditCardRepository, SeatRepository seatRepository, TravelRepository travelRepository, PaymentRepository paymentRepository) {
        this.memberReservationRepository = memberReservationRepository;
        this.createFlightRepository = createFlightRepository;
        this.memberRepository = memberRepository;
        this.creditCardRepository = creditCardRepository;
        this.seatRepository = seatRepository;
        this.travelRepository = travelRepository;
        this.paymentRepository = paymentRepository;
    }

    public Travel createReservation(Long plannedId, Long memberId, Long creditCardId, Travel travel) {
        if (memberReservationRepository.existsByCreatedByIdAndShipIdAndDepartureDate(memberId, plannedId, travel.getDepartureDate())) {
            throw new ApiException("Bu uçuşa zaten rezervasyon yaptınız!", HttpStatus.CONFLICT);
        }

        CreateFlight plannedFlight = createFlightRepository.findById(plannedId)
                .orElseThrow(() -> new ApiException("Böyle bir uçuş bulunamadı!", HttpStatus.NOT_FOUND));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ApiException("Kullanıcı bulunamadı!", HttpStatus.NOT_FOUND));

        CreditCard creditCard = creditCardRepository.findById(creditCardId)
                .orElseThrow(() -> new ApiException("Böyle bir kredi kartı bulunamadı!", HttpStatus.NOT_FOUND));

        Long shipId = plannedFlight.getShip().getId();
        Seat seatRequest = travel.getSeats().get(0);

        Seat selectedSeat = seatRepository.findBySeatNumberAndShipId(seatRequest.getSeatNumber(), shipId)
                .orElseThrow(() -> new ApiException("Böyle bir koltuk bulunamadı!", HttpStatus.NOT_FOUND));

        if (!selectedSeat.getAvailable()) {
            throw new ApiException("Bu koltuk zaten rezerve edilmiş!", HttpStatus.CONFLICT);
        }

        selectedSeat.setAvailable(false);
        seatRepository.save(selectedSeat);

        travel.setShip(plannedFlight.getShip());
        travel.setDeparturePlanet(plannedFlight.getDeparturePlanet());
        travel.setArrivalPlanet(plannedFlight.getArrivalPlanet());
        travel.setDepartureDate(plannedFlight.getDepartureDateTime());
        travel.setArrivalDate(plannedFlight.getArrivalDateTime());
        travel.setPrice(plannedFlight.getPrice());
        travel.setCreatedBy(member);
        travel.setCreditCard(creditCard);
        travel.setSeats(Collections.singletonList(selectedSeat));

        return memberReservationRepository.save(travel);
    }

    public void deleteReservation(Long memberId, Long travelId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ApiException("Kullanıcı bulunamadı!", HttpStatus.NOT_FOUND));

        Travel travel = travelRepository.findById(travelId)
                .orElseThrow(() -> new ApiException("Uçuş bulunamadı!", HttpStatus.NOT_FOUND));

        if (!member.getId().equals(travel.getCreatedBy().getId())) {
            throw new ApiException("Bu uçuşu silme yetkiniz yok!", HttpStatus.FORBIDDEN);
        }

        Seat seat = travel.getSeats().get(0);
        seat.setAvailable(true);
        seatRepository.save(seat);


        travelRepository.delete(travel);
    }







}
