package com.cyberiansoft.test.ios10_client.hdclientsteps;

import com.cyberiansoft.test.ios10_client.pageobjects.ioshddevicescreens.HomeScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.ioshddevicescreens.typesscreens.TeamWorkOrdersScreen;

public class HomeScreenSteps {

    public static void navigateToTeamWorkOrdersScreen() {
        HomeScreen homeScreen = new HomeScreen();
        homeScreen.clickTeamWorkordersButton();
        TeamWorkOrdersScreen teamWorkOrdersScreen = new TeamWorkOrdersScreen();
        teamWorkOrdersScreen.waitTeamWorkOrdersScreenLoaded();
    }

    public static void navigateToMyWorkOrdersScreen() {
        HomeScreen homeScreen = new HomeScreen();
        homeScreen.clickMyWorkOrdersButton();
        MyWorkOrdersSteps.waitMyWorkOrdersLoaded();
    }

    public static void navigateToStatusScreen() {
        HomeScreen homeScreen = new HomeScreen();
        homeScreen.clickStatusButton();
    }
}
