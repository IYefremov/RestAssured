package com.cyberiansoft.test.dataclasses.vNextBO.servicerequests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class VNextBOSRData {

    @JsonProperty("searchData")
    private VNextBOSRSearchData searchData;

    @JsonProperty("infoText")
    private String infoText;

    @JsonProperty("expectedValue")
    private String expectedValue;

    @JsonProperty("expected")
    private String[] expected;
}
