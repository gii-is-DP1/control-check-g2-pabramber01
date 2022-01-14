package org.springframework.samples.petclinic.feeding;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/feeding")
public class FeedingController {
    
	@Autowired
	private PetService petService;
	
	@Autowired
	private FeedingService feedingService;
	
	private static final String VIEW_CREATE_OR_UPDATE_FEEDING_FORM = "feedings/createOrUpdateFeedingForm";
	
	@GetMapping("/create")
	public String initCreateForm(ModelMap model) {
		Feeding feeding = new Feeding();
		Collection<PetType> pets = petService.findPetTypes();
		List<FeedingType> feedingTypes = feedingService.getAllFeedingTypes();
		model.addAttribute("feeding", feeding);
		model.addAttribute("pets", pets);
		model.addAttribute("feedingTypes", feedingTypes);
		return VIEW_CREATE_OR_UPDATE_FEEDING_FORM;
	}
	
}
