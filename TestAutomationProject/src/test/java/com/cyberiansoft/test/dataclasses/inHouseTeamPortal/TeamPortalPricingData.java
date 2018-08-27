package com.cyberiansoft.test.dataclasses.inHouseTeamPortal;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TeamPortalPricingData {

    @JsonProperty("editionName")
    private String editionName;

    @JsonProperty("price")
    private String price;

    @JsonProperty("minCommitments")
    private List<String> minCommitments;

    @JsonProperty("prices")
    private List<String> prices;

    @JsonProperty("featureGroupName")
    private String featureGroupName;

    @JsonProperty("featureGroupState")
    private String featureGroupState;

    @JsonProperty("featureGroupMarketingInfo")
    private String featureGroupMarketingInfo;

    @JsonProperty("featureState")
    private String featureState;

    @JsonProperty("featureNames")
    private List<String> featureNames;

    @JsonProperty("featureDescriptions")
    private List<String> featureDescriptions;

    @JsonProperty("featureMarketingInfoList")
    private List<String> featureMarketingInfoList;

    public List<String> getMinCommitments() {
        return minCommitments;
    }

    public List<String> getPrices() {
        return prices;
    }

    public String getEditionName() {
        return editionName;
    }

    public String getPrice() {
        return price;
    }

    public String getFeatureGroupName() {
        return featureGroupName;
    }

    public String getFeatureGroupState() {
        return featureGroupState;
    }

    public String getFeatureGroupMarketingInfo() {
        return featureGroupMarketingInfo;
    }

    public String getFeatureState() {
        return featureState;
    }

    public List<String> getFeatureNames() {
        return featureNames;
    }

    public List<String> getFeatureDescriptions() {
        return featureDescriptions;
    }

    public List<String> getFeatureMarketingInfoList() {
        return featureMarketingInfoList;
    }
}
