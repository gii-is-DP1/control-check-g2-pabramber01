package org.springframework.samples.petclinic.feeding;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.pet.PetType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class FeedingType extends BaseEntity {
    
	@NotBlank
	@Size(min = 5, max = 30)
	@Column(unique = true)
    String name;
    
	@NotBlank
    String description;
    
	@NotNull
	@JoinColumn(name = "pet_type_id")
	@ManyToOne(optional = false)
    PetType petType;

}
