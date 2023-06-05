package com.org.latelier.application;

import com.org.latelier.models.TennisPlayer;
import com.org.latelier.models.entities.TennisPlayerEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
// explicitly provide the injection strategy to retrieve this mapper
public interface TennisPlayerMapper {
    TennisPlayerMapper INSTANCE = Mappers.getMapper(TennisPlayerMapper.class);

    TennisPlayerEntity mapTennisPlayerToTennisPlayerEntity(TennisPlayer tennisPlayer);

    List<TennisPlayerEntity> map(List<TennisPlayer> tennisPlayerList);

}
