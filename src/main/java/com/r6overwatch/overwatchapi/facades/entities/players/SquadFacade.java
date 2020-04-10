package com.r6overwatch.overwatchapi.facades.entities.players;

import com.google.common.collect.Lists;
import com.r6overwatch.overwatchapi.converters.entities.players.impl.SquadConverter;
import com.r6overwatch.overwatchapi.facades.entities.OverwatchFacade;
import com.r6overwatch.overwatchapi.models.entities.players.Squad;
import com.r6overwatch.overwatchapi.resources.entities.players.SquadResource;
import com.r6overwatch.overwatchapi.services.entities.players.SquadService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Facade for {@link SquadResource}
 *
 * @author Stephen Prizio <a href="http://www.saprizio.com">www.saprizio.com</a>
 * @version 1.0
 */
@Component
public class SquadFacade implements OverwatchFacade<SquadResource> {

    @Resource(name = "squadConverter")
    private SquadConverter squadConverter;

    @Resource(name = "squadService")
    private SquadService squadService;


    //  METHODS

    @Override
    public SquadResource find(Long id) {

        Optional<Squad> squad = this.squadService.find(id);

        if (squad.isPresent()) {
            return this.squadConverter.convert(squad.get());
        }
        return new SquadResource();
    }

    @Override
    public List<SquadResource> findAll() {
        return Lists.newArrayList(this.squadConverter.convertAll(this.squadService.findAll()));
    }

    @Override
    public SquadResource create(Map<String, Object> params) {

        Squad squad = this.squadService.create(params);

        if (squad != null) {
            return this.squadConverter.convert(squad);
        }

        return new SquadResource();
    }

    @Override
    public void delete(Long id) {
        this.squadService.delete(id);
    }
}
