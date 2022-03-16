package com.epam.travelers.repositories;

import java.util.List;

import io.spring.guides.gs_producing_web_service.City;

public interface CityRepository {

	public List<City> findCityByCode(Integer code);
}
