package com.cyberiansoft.test.targetprocessintegration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EntityStatesDTO {

    @JsonProperty("Items")
    private List<EntityTypeDTO> items =  null;
}
