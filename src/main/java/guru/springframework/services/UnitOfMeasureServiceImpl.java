package guru.springframework.services;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.domains.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
@Slf4j
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService{
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {
     Iterable<UnitOfMeasure> iterable = unitOfMeasureRepository.findAll();
     iterable.forEach(unitOfMeasure-> log.info(unitOfMeasure.getId()+""));
     return StreamSupport.stream(iterable.spliterator(),false).map(unitOfMeasureToUnitOfMeasureCommand::convert).collect(Collectors.toSet());
    }
}
