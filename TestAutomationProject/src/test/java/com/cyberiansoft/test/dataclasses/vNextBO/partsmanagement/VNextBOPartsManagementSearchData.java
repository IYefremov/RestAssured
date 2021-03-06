package com.cyberiansoft.test.dataclasses.vNextBO.partsmanagement;

import com.cyberiansoft.test.dataclasses.vNextBO.VNextBOBaseData;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VNextBOPartsManagementSearchData extends VNextBOBaseData {

    @JsonProperty("customer")
    private String customer;

    @JsonProperty("phase")
    private String phase;

    @JsonProperty("repairStatus")
    private String repairStatus;

    @JsonProperty("woNum")
    private String woNum;

    @JsonProperty("stockNum")
    private String stockNum;

    @JsonProperty("etaFromDate")
    private String etaFromDate;

    @JsonProperty("etaToDate")
    private String etaToDate;

    @JsonProperty("partNum")
    private String partNum;

    @JsonProperty("vinNum")
    private String vinNum;

    @JsonProperty("woType")
    private String woType;

    @JsonProperty("notes")
    private String notes;

    @JsonProperty("fromDate")
    private String fromDate;

    @JsonProperty("toDate")
    private String toDate;

    @JsonProperty("orderedFrom")
    private String orderedFrom;

    @JsonProperty("searchName")
    private String searchName;
}