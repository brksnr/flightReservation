package com.example.demo.dto;

import java.util.List;

public record MemberInfoRequest(String fullName, String telephone, String birthDate, Integer height, Integer weight, List<String> allergy) {
}
