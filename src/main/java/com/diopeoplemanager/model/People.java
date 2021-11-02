package com.diopeoplemanager.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class People extends PeopleId{
    
    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String phone;
}
