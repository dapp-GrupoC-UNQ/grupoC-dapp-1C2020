package com.example.demo;

import com.example.demo.model.StoreSchedule;
import com.example.demo.model.merchandise.Merchandise;
import com.example.demo.model.merchandise.MerchandiseCategory;
import com.example.demo.model.store.Store;
import com.example.demo.model.store.StoreCategory;
import com.example.demo.repositories.merchandise.MerchandiseRepository;
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
        generateUsers();

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
        Merchandise fideos = new Merchandise("Fideos", "Marolio", 20.5, 12, MerchandiseCategory.GROCERY, "https://jumboargentina.vteximg.com.br/arquivos/ids/539292-750-750/Fideos-Spaghetti-Marolio-500-Gr-1-44037.jpg?v=636989736779430000");
        Merchandise mayonesa = new Merchandise("Mayonesa", "Helmanns", 15.0, 12, MerchandiseCategory.GROCERY, "https://cdn11.bigcommerce.com/s-3stx4pub31/images/stencil/608x608/products/492/1313/mayonesa950__07850.1563569504.jpg?c=2");
        Merchandise mostaza = new Merchandise("Mostaza", "Savora", 21.5, 12, MerchandiseCategory.GROCERY, "https://statics.dinoonline.com.ar/imagenes/full_600x600_ma/2440460_f.jpg");
        Merchandise arroz = new Merchandise("Arroz", "Gallo", 25.0, 12, MerchandiseCategory.GROCERY, "https://statics.dinoonline.com.ar/imagenes/full_600x600_ma/2290212_f.jpg");
        Merchandise lavandina = new Merchandise("Lavandina", "Ayudin", 50.45, 12, MerchandiseCategory.CLEANING_SUPPLIES, "https://statics.dinoonline.com.ar/imagenes/large_460x460/2830186_l.jpg");
        Merchandise trapo = new Merchandise("Trapo de piso", "Me lo llevo del dpto de la costa", 25.0, 12, MerchandiseCategory.CLEANING_SUPPLIES, "http://www.aromenai.com/wp-content/uploads/2017/09/trapo-piso-duramas-gris-630x630.jpg");
        Merchandise helado = new Merchandise("Chomp", "Frigo", 150.0, 12, MerchandiseCategory.FROZEN_FOOD, "https://supermercado.carrefour.com.ar/media/catalog/product/cache/1/small_image/214x/9df78eab33525d08d6e5fb8d27136e95/7/6/7613034790194_02.jpg");
        Merchandise ferne = new Merchandise("Fernet", "Branca", 400.0, 12, MerchandiseCategory.ALCOHOLIC_DRIKS, "https://d26lpennugtm8s.cloudfront.net/stores/447/761/products/fernet-branca-1l-011-2ae9281c7fca470b9515882681698500-1024-1024.jpg");
        Merchandise papitasN = new Merchandise("Papas Noisette", "McCain", 180.0, 12, MerchandiseCategory.FROZEN_FOOD, "http://www.mccain.com.ar/wp-content/uploads/2013/12/xInternaProd_Noisettes2.png.pagespeed.ic.GssU4n5J64.webp");
        Merchandise comidaPerro = new Merchandise("Alimento Balanceado", "Excelent", 500.0, 12, MerchandiseCategory.PET_STUFFS, "https://puppis.vteximg.com.br/arquivos/ids/167600-600-600/150040.jpg?v=637021773071770000");
        Merchandise nesquik = new Merchandise("Cereal", "Nesquik", 180.0, 12, MerchandiseCategory.CEREALS, "https://cdn.shopify.com/s/files/1/1380/8833/products/Nesquik_400g_Cereales_540x.png?v=1589459970");
        Merchandise sugus = new Merchandise("Caramelos", "Sugus", 150.0, 12, MerchandiseCategory.SWEET_AND_CANDY, "https://www.distribuidorapop.com.ar/wp-content/uploads/2016/06/caramelos-sugus-venta.jpg.webp");
        Merchandise pañales = new Merchandise("Pañales", "Pampers", 600.0, 12, MerchandiseCategory.BABY_CARE, "https://www.toymania.com.ar/wp-content/uploads/2019/03/921221-MLA29624880921_032019-O.jpg");
        Merchandise donuts = new Merchandise("Donas", "The Simpons", 200.0, 12, MerchandiseCategory.BAKERY, "https://cdn.metro-group.com/de/de_pim_210693002001_01.png?w=400&h=400&mode=pad");
        Merchandise pan = new Merchandise("Pan Lactal", "Bimbo", 120.0, 12, MerchandiseCategory.BAKERY, "https://tienda.tvmutual.com.ar/262267-thickbox_default/pan-liviano-xl-730g-bimbo.jpg");
        Merchandise mantequilla = new Merchandise("Mantequilla de Mani", "King", 235.0, 12, MerchandiseCategory.GROCERY, "https://saboresandinos.com/wp-content/uploads/2020/05/DSC_0026-scaled.jpg");
        Merchandise banana = new Merchandise("Banana", "Ecuador", 100.0, 12, MerchandiseCategory.GREENGROCER, "https://www.bidfoodiberia.com/media/catalog/product/cache/1/image/600x/17f82f742ffe127f42dca9de82fb58b1/p/l/ple2.jpg");
        Merchandise manzana = new Merchandise("Manzana", "Moño Azul", 95.0, 12, MerchandiseCategory.GREENGROCER, "https://statics.dinoonline.com.ar/imagenes/large_460x460/3390810_l.jpg");
        Merchandise cerveza = new Merchandise("Cerveza Roja", "Andes", 150.0, 12, MerchandiseCategory.ALCOHOLIC_DRIKS, "https://statics.dinoonline.com.ar/imagenes/large_460x460/3100628_l.jpg");
        Merchandise mermelada = new Merchandise("Mermelada de Frutos Rojos", "BC", 12.0, 12, MerchandiseCategory.GROCERY, "https://walmartar.vteximg.com.br/arquivos/ids/847191-292-292/0779336082630-1.jpg?v=636942158415200000");
        Merchandise chocolate = new Merchandise("Chocolate con Mani", "Shot", 80.0, 12, MerchandiseCategory.SWEET_AND_CANDY, "https://http2.mlstatic.com/chocolate-shot-170-grs-tableta-barata-la-golosineria-D_NQ_NP_644956-MLA41456237493_042020-O.webp");
        Merchandise mym = new Merchandise("Confites de chocolate", "M&M", 100.0, 12, MerchandiseCategory.SWEET_AND_CANDY, "https://elpoderdelconsumidor.org/wp-content/uploads/2017/09/mm.jpg");
        Merchandise chocoPasas = new Merchandise("Chocolate con Pasas", "M&Cadbury", 130.0, 12, MerchandiseCategory.SWEET_AND_CANDY, "https://jumboargentina.vteximg.com.br/arquivos/ids/182267-750-750/Chocolate-Cadbury-Con-Leche-X-170-Gr-Chocolate-Cadbury-Con-Leche-Con-Pasas-De-Uva-Tableta-170-G-1-17725.jpg?v=636383427679270000");
        Merchandise milaSoja = new Merchandise("Milanesas de Soja", "Granja del Sol", 85.0, 12, MerchandiseCategory.FROZEN_FOOD, "https://walmartar.vteximg.com.br/arquivos/ids/802951-292-292/Milanesa-De-Soja-Granja-Del-Sol-330-Gr-4-U-1-35619.jpg?v=636252834325800000");
        Merchandise coca = new Merchandise("Gaseosa Cola Original 1.5 Lts", "Coca-Cola", 120.0, 12, MerchandiseCategory.DRINKS, "https://statics.dinoonline.com.ar/imagenes/full_600x600_ma/3080013_f.jpg");
        Merchandise cocaZero = new Merchandise("Gaseosa Cola Sin Azucar 1.5 Lts", "Coca-Cola", 120.0, 12, MerchandiseCategory.DRINKS, "https://www.casa-segal.com/wp-content/uploads/2020/03/coca-cola-sin-azucares-15L-almacen-gaseosas-casa-segal-mendoza.jpg");
        Merchandise cocaLata = new Merchandise("Gaseosa Cola Original Lata", "Coca-Cola", 120.0, 12, MerchandiseCategory.DRINKS, "https://static.elcontainer.cl/2094-big_default/pack-24-o-48-latas-de-coca-cola-regular-zero-o-light.jpg");
        Merchandise cocaDosLts = new Merchandise("Gaseosa Cola Original 2.25 Lts", "Coca-Cola", 120.0, 12, MerchandiseCategory.DRINKS, "https://http2.mlstatic.com/coca-cola-botella-25-l-x6-ud-D_NQ_NP_892054-MLA41287516776_032020-F.webp");
        Merchandise cocalataZero = new Merchandise("Gaseosa Cola Sin Azucar Lata", "Coca-Cola", 120.0, 12, MerchandiseCategory.DRINKS, "https://static3.elcontainer.cl/2095-big_default/pack-24-o-48-latas-de-coca-cola-regular-zero-o-light.jpg");
        Merchandise rexona = new Merchandise("Desodorante Corporal Masculino", "Rexona", 75.30, 12, MerchandiseCategory.PERSONAL_CARE, "https://www.vassallo.com.ar/8514-large_default/rexona-deo-aer-men-hombre-x90g.jpg");
        Merchandise sobrecitoPollo = new Merchandise("Sobrecito Adulto Sabor Pollo", "Pedigree", 33.0, 12, MerchandiseCategory.PET_STUFFS, "https://puppis.vteximg.com.br/arquivos/ids/163559-600-600/Sobre-AdultoPollo-01_0010.png?v=636739936345170000");
        Merchandise sobrecitoCarne = new Merchandise("Sobrecito Adulto Sabor Carne", "Pedigree", 33.0, 12, MerchandiseCategory.PET_STUFFS,"https://puppis.vteximg.com.br/arquivos/ids/163558-600-600/Sobre-AdultoCarne-01_0010.jpg?v=636739934687870000");
        Merchandise sobrecitoCachPollo = new Merchandise("Sobrecito Cachorro Sabor Pollo", "Pedigree", 33.0, 12, MerchandiseCategory.PET_STUFFS, "https://puppis.vteximg.com.br/arquivos/ids/163560-600-600/Sobre-Cachorro-01_0010.png?v=636739938081070000");
        Merchandise sobrecitoCachCarne = new Merchandise("Sobrecito Cachorro Sabor Carne", "Pedigree", 33.0, 12, MerchandiseCategory.PET_STUFFS, "https://puppis.vteximg.com.br/arquivos/ids/163560-600-600/Sobre-Cachorro-01_0010.png?v=636739938081070000");
        Merchandise quaker = new Merchandise("Cereales Honey Graham", "Quaker", 175.0, 12, MerchandiseCategory.CEREALS, "https://jumboargentina.vteximg.com.br/arquivos/ids/549670-1000-1000/Cereales-Quaker-Honey-Graham-200-Gr-1-47107.jpg?v=637051080751930000");

        storeService.addStore(historietas);
        storeService.addMerchandiseToStore(historietas.id(), fideos);
        storeService.addMerchandiseToStore(historietas.id(), mostaza);
        storeService.addStore(kwickEMart);
        storeService.addMerchandiseToStore(kwickEMart.id(), mayonesa);
        storeService.addMerchandiseToStore(kwickEMart.id(),arroz);
        storeService.addMerchandiseToStore(kwickEMart.id(),pan);
        storeService.addStore(leftorium);
        storeService.addStore(cents);
        storeService.addMerchandiseToStore(cents.id(), manzana);
        storeService.addMerchandiseToStore(cents.id(), banana);
        storeService.addStore(helados);
        storeService.addMerchandiseToStore(helados.id(), helado);
        storeService.addStore(donas);
        storeService.addMerchandiseToStore(donas.id(), donuts);
        storeService.addStore(edna);
        storeService.addMerchandiseToStore(edna.id(), milaSoja);
        storeService.addMerchandiseToStore(edna.id(), coca);
        storeService.addMerchandiseToStore(edna.id(), cocaDosLts);
        storeService.addMerchandiseToStore(edna.id(), cocaZero);
        storeService.addMerchandiseToStore(edna.id(), cocalataZero);
        storeService.addMerchandiseToStore(edna.id(), cocaLata);
        storeService.addStore(gorras);
        storeService.addStore(moe);
        storeService.addMerchandiseToStore(moe.id(), cerveza);
        storeService.addStore(cleaner);
        storeService.addMerchandiseToStore(cleaner.id(), lavandina);
        storeService.addMerchandiseToStore(cleaner.id(), trapo);
        storeService.addMerchandiseToStore(cleaner.id(), pañales);
        storeService.addMerchandiseToStore(cleaner.id(), rexona);
        storeService.addStore(burger);
        storeService.addStore(monstruomercado);
        storeService.addMerchandiseToStore(monstruomercado.id(), ferne);
        storeService.addMerchandiseToStore(monstruomercado.id(), mantequilla);
        storeService.addMerchandiseToStore(monstruomercado.id(), mermelada);
        storeService.addMerchandiseToStore(monstruomercado.id(), quaker);
        storeService.addMerchandiseToStore(monstruomercado.id(), nesquik);
        storeService.addStore(tryNSave);
        storeService.addMerchandiseToStore(tryNSave.id(), comidaPerro);
        storeService.addMerchandiseToStore(tryNSave.id(), sobrecitoPollo);
        storeService.addMerchandiseToStore(tryNSave.id(), sobrecitoCarne);
        storeService.addMerchandiseToStore(tryNSave.id(), sobrecitoCachPollo);
        storeService.addMerchandiseToStore(tryNSave.id(), sobrecitoCachCarne);
        storeService.addStore(candy);
        storeService.addMerchandiseToStore(candy.id(), sugus);
        storeService.addMerchandiseToStore(candy.id(), chocolate);
        storeService.addMerchandiseToStore(candy.id(), mym );
        storeService.addMerchandiseToStore(candy.id(), chocoPasas);
        storeService.addStore(trufaDorada);
        storeService.addMerchandiseToStore(trufaDorada.id(), papitasN);
        storeService.addStore(luigi);
    }

    private void generateUsers() {
        userService.addUser("margesimpson@gmail.com", "123456", "Av. Siempreviva 742");
        userService.addUser("homerosimpson@gmail.com", "cerveza", "Av. Siempreviva 742");
        userService.addUser("bartsimpson@gmail.com", "aycaramba", "Av. Siempreviva 742");
        userService.addUser("lisasimpson@gmail.com", "yolosetodo", "Av. Siempreviva 742");
        userService.addUser("maggiesimpson@gmail.com", "nohabla", "Av. Siempreviva 742");
    }

    private Store generateStore(String name, String address, Integer distanceInKm, StoreScheduleRepository storeScheduleRepository, String imageUrl) {
        List<StoreCategory> categories = Arrays.asList(StoreCategory.HYGIENE_PRODUCTS, StoreCategory.BAKERY);
        List<String> paymentMethods = Arrays.asList("Efectivo", "Tarjeta de credito");
        List<DayOfWeek> days = Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.THURSDAY);
        StoreSchedule schedule = new StoreSchedule(days, LocalTime.of(9,0), LocalTime.of(17, 0));
        storeScheduleRepository.save(schedule);
        return new Store(name, categories, address, distanceInKm, paymentMethods,schedule, LocalDate.now(), imageUrl);
    }

}
