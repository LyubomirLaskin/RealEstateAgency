package com.laskin.rea.web.controllers;

import com.laskin.rea.domain.models.binding.OfferRegisterBindingModel;
import com.laskin.rea.domain.models.service.OfferServiceModel;
import com.laskin.rea.services.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OfferController {

    private final OfferService offerService;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferController(OfferService offerService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/reg")
    public String register() {
        return "register.html";
    }

    @PostMapping("/reg")
    public String registerConfirm(@ModelAttribute(name = "name") OfferRegisterBindingModel model) {

        try {

            this.offerService.registerOffer(this.modelMapper.map(model, OfferServiceModel.class));

        } catch (IllegalArgumentException iae) {
            return "redirect:/register.html";
        }
        return "redirect:/";
    }
}
