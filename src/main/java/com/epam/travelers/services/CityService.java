package com.epam.travelers.services;

import java.util.List;

import io.spring.guides.gs_producing_web_service.City;

public interface CityService {

	public List<City> findCity(Integer code);
}
