package com.example.demo;

import com.example.demo.model.StoreSchedule;
import com.example.demo.model.store.Store;
import com.example.demo.model.store.StoreCategory;
import com.example.demo.repositories.storeSchedule.StoreScheduleRepository;
import com.example.demo.services.StoreService;
import com.example.demo.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseInitializate implements CommandLineRunner {

    @Autowired
    UserService userService;

    @Autowired
    StoreScheduleRepository storeScheduleRepository;

    @Autowired
    StoreService storeService;

    @Override
    public void run(String... args) throws Exception {
        userService.addUser("MargeSimpson@gmail.com", "123456");
        userService.addUser("HomeroSimpson@gmail.com", "cerveza");
        userService.addUser("BartSimpson@gmail.com", "aycaramba");
        userService.addUser("LisaSimpson@gmail.com", "yolosetodo");
        userService.addUser("MaggieSimpson@gmail.com", "nohabla");

        Store historietas = generateStore("Calabozo del androide y expendio de tarjetas de baseball", "calle falsa 123", 2, storeScheduleRepository, "https://s3.us-east-1.amazonaws.com/musiquiatra/upload/monthly_2018_06/800px-Androidsdungeon.png.2d079f7f971d3794cf23275925e32bff.png");
        Store kwickEMart = generateStore("Kwik-E-Mart", "calle apus 123", 3,storeScheduleRepository, "https://www.tonica.la/__export/1534892802783/sites/debate/img/2018/08/21/kwik-e-mart_sc.jpg_1902800913.jpg");
        Store leftorium = generateStore("Leftorium", "Av. Siempre Viva 123", 2, storeScheduleRepository, "https://vignette.wikia.nocookie.net/simpsons/images/2/2e/Sex%2C_Pies%2C_and_Idiot_Scrapes_-00144.jpg/revision/latest/scale-to-width-down/340?cb=20150115022043");
        Store cents = generateStore("Tienda de 33 centavos", "calle 2 456", 3, storeScheduleRepository, "https://lh3.googleusercontent.com/proxy/WzTWj0vaHAltp_QUvCNhZEdEX_w8BodfcXgBWqoWiT1IVvanFV3OgiZQi9E2luQPZUnTebe66ezzSX0olKWWx4prhwBdc3FwdOISZTuP5TkHIQHljZpPsitplJ3gMcDR37iYEk8YWl9rQw");
        Store helados = generateStore("Helados Mantecon", "calle 25 254", 3, storeScheduleRepository, "https://vignette.wikia.nocookie.net/simpsons/images/c/c4/Splitsville.jpg/revision/latest/top-crop/width/360/height/360?cb=20120105055048");
        Store donas = generateStore("Lard Lad Donuts", "calle 12 587", 3, storeScheduleRepository, "https://i.pinimg.com/originals/0a/53/2a/0a532a02ec87046356d4f83d097bf896.png");
        Store edna = generateStore("Edna's Edible", "calle 14 1141", 4, storeScheduleRepository, "https://vignette.wikia.nocookie.net/simpsons/images/4/40/Edna%27s_Edibles.png/revision/latest?cb=20100519080645");
        Store gorras = generateStore("El Maloso Lanudo", "calle 11 124", 3, storeScheduleRepository, "https://vignette.wikia.nocookie.net/simpsons/images/9/9c/Wooly_bully.png/revision/latest/top-crop/width/360/height/360?cb=20100903120446");
        Store moe = generateStore("La Taberna de Moe", "calle 2 2524", 3, storeScheduleRepository, "https://vignette.wikia.nocookie.net/simpsons/images/7/76/Moe%27s_Tavern.PNG/revision/latest?cb=20151025003531");
        Store cleaner = generateStore("Spring Field Cleaner", "calle 4 2524", 3, storeScheduleRepository, "https://vignette.wikia.nocookie.net/simpsons/images/2/27/Cleaners.jpg/revision/latest/top-crop/width/360/height/360?cb=20120119050327");
        Store burger = generateStore("Krusty Burger", "calle 1 252", 3, storeScheduleRepository, "https://cdn.hobbyconsolas.com/sites/navi.axelspringer.es/public/styles/1200/public/media/image/2020/01/kursty-burger.jpg?itok=bVJGiESu");
        Store monstruomercado = generateStore("Mounstro Mercado", "calle 3 254", 3, storeScheduleRepository, "https://vignette.wikia.nocookie.net/simpsonstappedout/images/5/56/Monstromart_animation.png/revision/latest/top-crop/width/300/height/300?cb=20181126200035");
        Store tryNSave = generateStore("A ver si ahorra", "calle 3 223", 3, storeScheduleRepository, "https://vignette.wikia.nocookie.net/lossimpson/images/1/1b/Trynsave.png/revision/latest/scale-to-width-down/340?cb=20180708175512&path-prefix=es");
        Store candy = generateStore("All Creatures", "calle 1 300", 3, storeScheduleRepository, "https://img1.wikia.nocookie.net/__cb20101028182609/lossimpson/es/images/e/e8/250px-All_creatures_great_and_cheap.png");
        Store trufaDorada = generateStore("La trufa dorada", "calle 1 350", 3, storeScheduleRepository, "https://media.malditosnerds.com/adjuntos/290/migration/__export/1543350848181/sites/claro/malditosnerds/img/2015/09/21/aea433_THE_GILDED_TRUFFLE.jpg_875081608.jpg");
        Store luigi = generateStore("Luigi's", "calle 6 5047", 4, storeScheduleRepository, "https://vignette.wikia.nocookie.net/simpsons/images/7/79/Luigi%27s.png/revision/latest/top-crop/width/300/height/300?cb=20150518202729");
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
        storeService.addStore(candy);
        storeService.addStore(trufaDorada);
        storeService.addStore(luigi);
    }

    private Store generateStore(String name, String address, Integer distanceInKm, StoreScheduleRepository storeScheduleRepository, String imageUrl) {
        List<StoreCategory> categories = Arrays.asList(StoreCategory.HYGIENE_PRODUCTS);
        List<String> paymentMethods = Arrays.asList("Efectivo", "Tarjeta de credito");
        List<DayOfWeek> days = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.THURSDAY);
        StoreSchedule schedule = new StoreSchedule(days, LocalTime.of(9,0), LocalTime.of(17, 0));
        storeScheduleRepository.save(schedule);
        return new Store(name, categories, address, distanceInKm, paymentMethods,schedule, LocalDate.now(), imageUrl);
    }

}
