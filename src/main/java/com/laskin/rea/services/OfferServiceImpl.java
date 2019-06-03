package com.laskin.rea.services;

import com.laskin.rea.domain.enteties.Offer;
import com.laskin.rea.domain.models.service.OfferServiceModel;
import com.laskin.rea.repositories.OfferRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService{

    private final OfferRepository offerRepository;
    private final Validator validator;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, Validator validator, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }


    @Override
    public void registerOffer(OfferServiceModel offerServiceModel) {

        if (this.validator.validate(offerServiceModel).size() != 0){
            throw new IllegalArgumentException("Wrong input!");
        }

        this.offerRepository.saveAndFlush(this.modelMapper.map(offerServiceModel, Offer.class));

    }

    @Override
    public List<OfferServiceModel> getAllOffers() {

        return this.offerRepository
                .findAll()
                .stream()
                .map(o -> this.modelMapper.map(o, OfferServiceModel.class))
                .collect(Collectors.toList());

    }
}
