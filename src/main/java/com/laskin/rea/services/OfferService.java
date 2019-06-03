package com.laskin.rea.services;

import com.laskin.rea.domain.models.service.OfferServiceModel;

import java.util.List;

public interface OfferService {

    void registerOffer(OfferServiceModel offerServiceModel);

    List<OfferServiceModel> getAllOffers();
}
