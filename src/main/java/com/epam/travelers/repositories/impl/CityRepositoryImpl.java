package com.epam.travelers.repositories.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.epam.travelers.repositories.CityRepository;

import io.spring.guides.gs_producing_web_service.City;

@Component
public class CityRepositoryImpl implements CityRepository {
	private static final List<City> cities = new ArrayList<>();

	@PostConstruct
	public void initData() {
		City becket = new City();
		becket.setName("Becket");
		becket.setCode(413);

		cities.add(becket);
		
		City cummington = new City();
		cummington.setName("Cummington");
		cummington.setCode(413);

		cities.add(cummington);
		
		City hancock = new City();
		hancock.setName("Hancock");
		hancock.setCode(413);

		cities.add(hancock);

		City orlando = new City();
		orlando.setName("Orlando");
		orlando.setCode(321);

		cities.add(orlando);

		City cocoaBeach = new City();
		cocoaBeach.setName("Cocoa Beach");
		cocoaBeach.setCode(321);

		cities.add(cocoaBeach);
	}

	public List<City> findCityByCode(Integer code) {
		return cities.stream().filter(c -> code.equals(c.getCode())).collect(Collectors.toList());
	}
}