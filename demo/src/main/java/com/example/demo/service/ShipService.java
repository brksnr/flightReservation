package com.example.demo.service;

import com.example.demo.entity.Ship;
import com.example.demo.exceptions.ApiException;
import com.example.demo.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipService {

    private final ShipRepository shipRepository;

    @Autowired
    public ShipService(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    public void addShip(Ship ship){
        if(shipRepository.findByName(ship.getName()).isPresent()){
            throw new ApiException(("Bu gemi zaten daha önce eklenmiş!"), HttpStatus.BAD_REQUEST);
        }
        shipRepository.save(ship);
    }

    public void deleteShip (Long id){
        Ship ship = shipRepository.findById(id)
                .orElseThrow(() ->{
                    throw new ApiException("Gemi bulunamadı!", HttpStatus.NOT_FOUND);
                });
        shipRepository.delete(ship);
    }

    public List<Ship> getAllShip(){
        return shipRepository.findAll();
    }
}
