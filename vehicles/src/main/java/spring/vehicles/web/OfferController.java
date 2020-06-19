package spring.vehicles.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.vehicles.model.Offer;
import spring.vehicles.service.BrandService;
import spring.vehicles.service.OfferService;

@Controller
@RequestMapping("/offers")
@Slf4j
public class OfferController {

    private BrandService brandService;
    private OfferService offerService;

    @Autowired
    public OfferController(BrandService brandService, OfferService offerService) {
        this.brandService = brandService;
        this.offerService = offerService;
    }

    @GetMapping
    public String getOffers(Model offers) {
        offers.addAttribute("offers", offerService.getOffers());
        return "offers";
    }

    @GetMapping("/add")
    public String getOfferForm(@ModelAttribute("offer") Offer offer) {
        return "offer-add";
    }

    @PostMapping("/add")
    public String createNewOffer(@ModelAttribute("offer") Offer offer) {
        try {
            offerService.createOffer(offer);
        } catch (Exception e) {
            log.error("Error creating offer", e);
            return "offer-add";
        }
        return "redirect:/offers";
    }
}
