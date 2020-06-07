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
           	Store leftorium = generateStore("Leftorium", "Av. Siempre Viva 123", 2, storeScheduleRepository);
           	Store cents = generateStore("Tienda de 33 centavos", "calle 2 456", 3, storeScheduleRepository);
			Store helados = generateStore("Helados Mantecon", "calle 25 254", 3, storeScheduleRepository);
			Store donas = generateStore("Lard Lad Donuts", "calle 12 587", 3, storeScheduleRepository);
			Store edna = generateStore("Edna's Edible", "calle 14 1141", 4, storeScheduleRepository);
			Store gorras = generateStore("El Maloso Lanudo", "calle 11 124", 3, storeScheduleRepository);
			Store moe = generateStore("La Taberna de Moe", "calle 2 2524", 3, storeScheduleRepository);
			Store cleaner = generateStore("Spring Field Cleaner", "calle 4 2524", 3, storeScheduleRepository);
			Store burger = generateStore("Krusty Burger", "calle 1 252", 3, storeScheduleRepository);
			Store monstruomercado = generateStore("Mounstro Mercado", "calle 3 254", 3, storeScheduleRepository);
			Store tryNSave = generateStore("A ver si ahorra", "calle 3 223", 3, storeScheduleRepository);
			Store furniture = generateStore("99 Furniture Store", "calle 1 254", 3, storeScheduleRepository);
			Store candy = generateStore("Candy Most Dandy", "calle 1 300", 3, storeScheduleRepository);
			Store lullabuys = generateStore("Lullabuy$", "calle 1 350", 3, storeScheduleRepository);

			storeService.addStore(historietas);
			storeService.addStore(kwickEMart);
			storeService.addStore(leftorium);
			storeService.addStore(cents);
			storeService.addStore(helados);
			storeService.addStore(donas);
			storeService.addStore(edna);
			storeService.addStore(gorras);
			storeService.addStore(moe);
			storeService.addStore(cleaner);
			storeService.addStore(burger);
			storeService.addStore(monstruomercado);
			storeService.addStore(tryNSave);
			storeService.addStore(furniture);
			storeService.addStore(candy);
			storeService.addStore(lullabuys);
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