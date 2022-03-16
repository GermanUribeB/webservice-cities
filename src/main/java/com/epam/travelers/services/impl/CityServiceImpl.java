package com.epam.travelers.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.epam.travelers.repositories.CityRepository;
import com.epam.travelers.services.CityService;

import io.spring.guides.gs_producing_web_service.City;

@Service
public class CityServiceImpl implements CityService {
	
	@Autowired
	private CityRepository cityRepository;
	
	public List<City> findCity(Integer code) {
		Assert.notNull(code, "The city's code must not be null");
		return cityRepository.findCityByCode(code);
	}
}
