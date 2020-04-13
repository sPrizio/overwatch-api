package com.r6overwatch.overwatchapi.commandlinerunners;

import com.google.common.collect.Lists;
import com.r6overwatch.overwatchapi.models.entities.game.Map;
import com.r6overwatch.overwatchapi.repositories.game.MapRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Loads {@link Map} data into the system
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
@Order(1)
public class MapDataLoader implements CommandLineRunner {

    @Resource(name = "mapRepository")
    private MapRepository mapRepository;


    //  METHODS

    @Override
    public void run(String... args) throws Exception {

        Map map1 = new Map("Bank");
        Map map2 = new Map("Border");
        Map map3 = new Map("Chalet");
        Map map4 = new Map("Club House");

        Map map5 = new Map("Coastline");
        Map map6 = new Map("Consulate");
        Map map7 = new Map("Favela");
        Map map8 = new Map("Fortress");

        Map map9 = new Map("Hereford Base");
        Map map10 = new Map("House");
        Map map11 = new Map("Kafe Dostoyevsky");
        Map map12 = new Map("Kanal");

        Map map13 = new Map("Oregon");
        Map map14 = new Map("Outback");
        Map map15 = new Map("Presidential Plane");
        Map map16 = new Map("Skyscraper");

        Map map17 = new Map("Theme Park");
        Map map18 = new Map("Tower");
        Map map19 = new Map("Villa");
        Map map20 = new Map("Yacht");

        this.mapRepository.saveAll(Lists.newArrayList(
                map1, map2, map3, map4, map5, map6, map7, map8, map9, map10,
                map11, map12, map13, map14, map15, map16, map17, map18, map19, map20
        ));
    }
}
