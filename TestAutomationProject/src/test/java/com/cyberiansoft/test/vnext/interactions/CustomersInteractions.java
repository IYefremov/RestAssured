package com.cyberiansoft.test.vnext.interactions;

import com.cyberiansoft.test.dataclasses.AppCustomer;
import com.cyberiansoft.test.vnext.screens.customers.VNextCustomersScreen;
import org.openqa.selenium.By;

public class CustomersInteractions {

    public static boolean isCustomerExists(AppCustomer customer) {
        VNextCustomersScreen customersScreen = new VNextCustomersScreen();
        return customersScreen.isCustomerExists(customer);
    }
}
