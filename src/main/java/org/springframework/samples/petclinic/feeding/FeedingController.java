package org.springframework.samples.petclinic.feeding;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/feeding")
public class FeedingController {
    
	@Autowired
	private PetService petService;
	
	@Autowired
	private FeedingService feedingService;
	
	private static final String VIEW_CREATE_OR_UPDATE_FEEDING_FORM = "feedings/createOrUpdateFeedingForm";
	private static final String VIEW_HOME = "welcome";
	
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
	
	@PostMapping("/create")
	public String processCreateForm(@Valid Feeding feeding, BindingResult result) {
		if (result.hasErrors()) {
			return VIEW_CREATE_OR_UPDATE_FEEDING_FORM;
		} else {
			try {
				feedingService.save(feeding);
			} catch (UnfeasibleFeedingException e) {
				result.rejectValue("feedingType", "La mascota seleccionada no se le puede asignar el plan de alimentación especificado.");
				return VIEW_CREATE_OR_UPDATE_FEEDING_FORM;
			}
			return "redirect:/" + VIEW_HOME;
		}
	}
	
}
