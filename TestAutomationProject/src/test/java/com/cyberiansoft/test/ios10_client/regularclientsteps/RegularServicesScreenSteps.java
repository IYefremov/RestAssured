package com.cyberiansoft.test.ios10_client.regularclientsteps;

import com.cyberiansoft.test.dataclasses.*;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularPriceMatricesScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.RegularSelectedServiceBundleScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.wizarscreens.RegularServicesScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.wizarscreens.RegularSubServicesScreen;

import java.util.List;

public class RegularServicesScreenSteps {

    public static void selectServiceWithServiceData(ServiceData serviceData) {
        openCustomServiceDetails(serviceData.getServiceName());
        RegularServiceDetailsScreenSteps.setServiceDetailsDataAndSave(serviceData);
    }

    public static void selectService(String serviceName) {
        RegularServicesScreen servicesScreen = new RegularServicesScreen();
        servicesScreen.selectService(serviceName);
    }

    public static void selectServices(List<ServiceData> servicesData) {
        RegularServicesScreen servicesScreen = new RegularServicesScreen();
        servicesScreen.selectServices(servicesData);
    }

    public static void selectSubService(String subServiceName) {
        RegularSubServicesScreen subServicesScreen = new RegularSubServicesScreen();
        subServicesScreen.selectServiceSubSrvice(subServiceName);
    }

    public static void openCustomServiceDetails(String serviceName) {
        RegularServicesScreen servicesScreen = new RegularServicesScreen();
        servicesScreen.openCustomServiceDetails(serviceName);
    }

    public static void selectMatrixServiceData(MatrixServiceData matrixServiceData) {
        selectMatrixService(matrixServiceData);
        if (matrixServiceData.getVehiclePartsData() != null) {
            for (VehiclePartData vehiclePartData : matrixServiceData.getVehiclePartsData()) {
                RegularVehiclePartsScreenSteps.selectVehiclePartAndSetData(vehiclePartData);
                RegularVehiclePartsScreenSteps.saveVehiclePart();
            }
        }
        if (matrixServiceData.getVehiclePartData() != null) {
            RegularVehiclePartsScreenSteps.selectVehiclePartAndSetData(matrixServiceData.getVehiclePartData());
        }
        RegularVehiclePartsScreenSteps.savePriceMatrixData();
    }

    public static void selectMatrixService(MatrixServiceData matrixServiceData) {
        RegularServicesScreen servicesScreen = new RegularServicesScreen();
        servicesScreen.selectService(matrixServiceData.getMatrixServiceName());
        if (matrixServiceData.getHailMatrixName() != null) {
            RegularPriceMatricesScreen priceMatricesScreen = new RegularPriceMatricesScreen();
            priceMatricesScreen.selectPriceMatrice(matrixServiceData.getHailMatrixName());
        }
    }

    public static void selectBundleService(BundleServiceData bundleServiceData) {
        openCustomServiceDetails(bundleServiceData.getBundleServiceName());
        RegularSelectedServiceBundleScreen selectedservicebundlescreen = new RegularSelectedServiceBundleScreen();
        if (bundleServiceData.getServices() != null) {
            for (ServiceData serviceData : bundleServiceData.getServices()) {
                if ((serviceData.getServiceQuantity() != null) || (serviceData.getServicePrice() != null) || (serviceData.getVehiclePart() != null)) {
                    selectedservicebundlescreen.openBundleInfo(serviceData.getServiceName());
                    RegularServiceDetailsScreenSteps.setServiceDetailsDataAndSave(serviceData);
                } else
                    selectedservicebundlescreen.selectBundle(serviceData.getServiceName());
            }
        }
        if (bundleServiceData.getBundleServiceAmount() != null)
            selectedservicebundlescreen.changeAmountOfBundleService(bundleServiceData.getBundleServiceAmount());
        RegularServiceDetailsScreenSteps.saveServiceDetails();
    }

    public static void selectServiceWithSubServices(DamageData damageData) {
        for (ServiceData serviceData : damageData.getMoneyServices()) {
            selectService(damageData.getDamageGroupName());
            final String subServiceName = damageData.getDamageGroupName() + " (" + serviceData.getServiceName() + ")";
            selectSubService(subServiceName);
        }
    }

    public static void selectPanelServiceData(DamageData damageData) {
        selectServicePanel(damageData);
        if (damageData.getMoneyService() != null) {
            selectServiceWithServiceData(damageData.getMoneyService());
        }
        if (damageData.getMoneyServices() != null) {
            for (ServiceData serviceData : damageData.getMoneyServices())
                selectServiceWithServiceData(serviceData);
        }
        if (damageData.getBundleService() != null) {
            selectBundleService(damageData.getBundleService());
        }
        if (damageData.getMatrixService() != null) {
            selectMatrixServiceData(damageData.getMatrixService());
            RegularPriceMatrixScreenSteps.savePriceMatrix();
        }
        if (damageData.getLaborService() != null) {
            selectLaborServiceAndSetData(damageData.getLaborService());
            RegularServiceDetailsScreenSteps.saveServiceDetails();
        }
    }

    public static void selectServicePanelGroupData(ServicePanelGroup servicePanelGroup) {
        selectServicePanelGroup(servicePanelGroup);
        if (servicePanelGroup.getMoneyService() != null) {
            selectServiceWithServiceData(servicePanelGroup.getMoneyService());
        }
        if (servicePanelGroup.getMoneyServices() != null) {
            for (ServiceData serviceData : servicePanelGroup.getMoneyServices())
                selectServiceWithServiceData(serviceData);
        }
        if (servicePanelGroup.getLaborService() != null) {
            selectLaborServiceAndSetData(servicePanelGroup.getLaborService());
            RegularServiceDetailsScreenSteps.saveServiceDetails();
        }
    }

    public static void selectServicePanelGroup(ServicePanelGroup servicePanelGroup) {
        RegularServicesScreen servicesScreen = new RegularServicesScreen();
        servicesScreen.selectServicePanel(servicePanelGroup.getPanelGroupName());
    }


    public static void selectServicePanel(DamageData damageData) {
        RegularServicesScreen servicesScreen = new RegularServicesScreen();
        servicesScreen.selectServicePanel(damageData.getDamageGroupName());
    }

    public static void selectLaborServiceAndSetData(LaborServiceData laborServiceData) {
        openCustomServiceDetails(laborServiceData.getServiceName());
        RegularServiceDetailsScreenSteps.setLaborServiceData(laborServiceData);
    }

    public static void switchToSelectedServices() {
        waitServicesScreenLoad();
        RegularServicesScreen servicesScreen = new RegularServicesScreen();
        servicesScreen.switchToSelectedServicesTab();
        RegularSelectedServicesSteps.waitSelectedServicesScreenLoaded();
    }

    public static void switchToAvailableServices() {
        waitServicesScreenLoad();
        RegularServicesScreen servicesScreen = new RegularServicesScreen();
        servicesScreen.switchToAvailableServicesTab();
        waitServicesScreenLoad();
    }

    public static void waitServicesScreenLoad() {
        RegularServicesScreen servicesScreen = new RegularServicesScreen();
        servicesScreen.waitServicesScreenLoaded();
    }

    public static void clickServiceTypesButton() {
        RegularServicesScreen servicesScreen = new RegularServicesScreen();
        servicesScreen.clickBackServicesButton();
    }

    public static void clickVehiclePartsButton() {
        RegularServicesScreen servicesScreen = new RegularServicesScreen();
        servicesScreen.clickVehiclePartsButton();
    }
}
