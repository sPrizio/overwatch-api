package com.r6overwatch.overwatchapi.services.entities.games;

import com.google.common.collect.Lists;
import com.r6overwatch.overwatchapi.models.entities.games.Map;
import com.r6overwatch.overwatchapi.repositories.games.map.MapRepository;
import com.r6overwatch.overwatchapi.services.entities.OverwatchEntityService;
import com.r6overwatch.overwatchapi.translators.games.MapTranslator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the {@link OverwatchEntityService} architecture for {@link Map}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Service
public class MapService implements OverwatchEntityService<Map> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapService.class);

    @Resource(name = "mapRepository")
    private MapRepository mapRepository;

    @Resource(name = "mapTranslator")
    private MapTranslator mapTranslator;


    //  METHODS

    /**
     * Obtains a {@link Map} for the given map name
     *
     * @param name name of map
     * @return {@link Map} who's name matches the given string
     */
    public Optional<Map> findMapForName(String name) {

        if (StringUtils.isEmpty(name)) {
            LOGGER.error("Required param 'name' was null or missing");
            return Optional.empty();
        }

        return Optional.ofNullable(this.mapRepository.findByName(name));
    }

    @Override
    public void refresh(Map entity) {
        this.mapRepository.refresh(entity);
    }

    @Override
    public Optional<Map> find(Long id) {
        return this.mapRepository.findById(id);
    }

    @Override
    public List<Map> findAll() {
        return Lists.newArrayList(this.mapRepository.findAll());
    }

    @Override
    public Map save(Map entity) {
        return this.mapRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        find(id).ifPresent(map -> this.mapRepository.deleteById(map.getId()));
    }

    @Override
    public Map create(java.util.Map<String, Object> params) {

        Map map = this.mapTranslator.translate(params);

        if (map != null) {
            return this.mapRepository.save(map);
        }

        return null;
    }
}
