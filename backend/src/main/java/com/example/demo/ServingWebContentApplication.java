package com.example.demo;

import com.example.demo.model.StoreSchedule;
import com.example.demo.model.store.Store;
import com.example.demo.model.store.StoreCategory;
import com.example.demo.model.user.ClientUser;
import com.example.demo.repositories.StoreRepository;
import com.example.demo.repositories.storeSchedule.StoreScheduleRepository;
import com.example.demo.repositories.threshold.MoneyThresholdRepository;
import com.example.demo.repositories.users.UserRepository;
import com.example.demo.services.StoreService;
import com.example.demo.services.users.UserService;
import org.apache.tomcat.jni.Local;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ServingWebContentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServingWebContentApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserService userService, StoreService storeService,StoreScheduleRepository storeScheduleRepository){
		return args -> {
			userService.addUser("MargeSimpson@gmail.com", "123456");
			userService.addUser("HomeroSimpson@gmail.com", "cerveza");
			userService.addUser("BartSimpson@gmail.com", "aycaramba");
			userService.addUser("LisaSimpson@gmail.com", "yolosetodo");
			userService.addUser("MaggieSimpson@gmail.com", "nohabla");

            Store historietas = generateStore("Calabozo del androide y expendio de tarjetas de baseball", "calle falsa 123", 2, storeScheduleRepository);
            Store kwickEMart = generateStore("Kwik-E-Mart", "calle apus 123", 3,storeScheduleRepository);
            storeService.addStore(historietas);
            storeService.addStore(kwickEMart);
		};
	}

    private Store generateStore(String name, String address, Integer distanceInKm, StoreScheduleRepository storeScheduleRepository) {
        List<StoreCategory> categories = Arrays.asList(StoreCategory.HYGIENE_PRODUCTS);
        List<String> paymentMethods = Arrays.asList("Efectivo", "Tarjeta de credito");
        List<DayOfWeek> days = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.THURSDAY);
        StoreSchedule schedule = new StoreSchedule(days, LocalTime.of(9,0), LocalTime.of(17, 0));
        storeScheduleRepository.save(schedule);
        return new Store(name, categories, address, distanceInKm, paymentMethods,schedule, LocalDate.now());
    }


}