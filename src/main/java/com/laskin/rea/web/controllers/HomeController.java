package com.laskin.rea.web.controllers;

import com.laskin.rea.domain.view.OfferViewModel;
import com.laskin.rea.services.OfferService;
import com.laskin.rea.utils.HtmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final static String htmlIndexPathFile = "E:\\Java\\JavaWebSpring\\HomeWork\\RealEstateAgency\\src\\main\\resources\\static\\index.html";

    private final OfferService offerService;
    private final HtmlParser htmlParser;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeController(OfferService offerService, HtmlParser htmlParser, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.htmlParser = htmlParser;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    public String index() throws IOException {

        String parsedHtml = this.htmlParser.read(htmlIndexPathFile);

        StringBuilder offerBuilder = new StringBuilder();

        List<OfferViewModel> offers = this.offerService.getAllOffers().stream()
                .map(o -> this.modelMapper.map(o, OfferViewModel.class))
                .collect(Collectors.toList());

        if (offers.size() == 0){
            return parsedHtml.replace("{{offer}}", "There aren't any offers!");
        }

        for (OfferViewModel offer : offers) {
            offerBuilder.append(String.format("<p>Rent: %.2f</p>/n<p>Type: %s</p>/n<p>Commission: %.2f</p>"
                    ,offer.getApartmentRent(), offer.getApartmentType(), offer.getAgencyCommission()));
        }

        return offerBuilder.toString().trim();
    }

    /**<p>Rent: 700.00</p>
     <p>Type: Two Room apartment</p>
     <p>Commission: 50.00</p> **/
}
