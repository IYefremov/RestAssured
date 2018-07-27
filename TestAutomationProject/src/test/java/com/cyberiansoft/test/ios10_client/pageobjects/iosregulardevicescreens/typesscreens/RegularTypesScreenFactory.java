package com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.typesscreens;

import com.cyberiansoft.test.ios10_client.appcontexts.TypeScreenContext;
import com.cyberiansoft.test.ios10_client.pageobjects.iosregulardevicescreens.wizarscreens.RegularInvoiceInfoScreen;
import com.cyberiansoft.test.ios10_client.pageobjects.screensinterfaces.ITypeScreen;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class RegularTypesScreenFactory {

    private static final Map<TypeScreenContext, Supplier<ITypeScreen>> screensMap = new HashMap<>();

    private static  final Supplier<ITypeScreen> myWorkOrdersScreenSupplier = () -> new RegularMyWorkOrdersScreen();

    private static  final Supplier<ITypeScreen> myInspectionsScreenSupplier = () -> new RegularMyInspectionsScreen();

    private static  final Supplier<ITypeScreen> myInvoicesScreenSupplier = () -> new RegularMyInvoicesScreen();

    private static  final Supplier<ITypeScreen> serviceRequestsScreenSupplier = () -> new RegularServiceRequestsScreen();

    private static  final Supplier<ITypeScreen> teamWorkOrdersScreenSupplier = () -> new RegularTeamWorkOrdersScreen();

    private static  final Supplier<ITypeScreen> teamInspectionsScreenSupplier = () -> new RegularTeamInspectionsScreen();

    private static  final Supplier<ITypeScreen> invoicesInfoScreenSupplier = () -> new RegularInvoiceInfoScreen();

    static {
        screensMap.put(TypeScreenContext.WORKORDER, myWorkOrdersScreenSupplier);
        screensMap.put(TypeScreenContext.INSPECTION, myInspectionsScreenSupplier);
        screensMap.put(TypeScreenContext.INVOICE, myInvoicesScreenSupplier);
        screensMap.put(TypeScreenContext.SERVICEREQUEST, serviceRequestsScreenSupplier);
        screensMap.put(TypeScreenContext.TEAMWORKORDER, teamWorkOrdersScreenSupplier);
        screensMap.put(TypeScreenContext.TEAMINSPECTION, teamInspectionsScreenSupplier);
        screensMap.put(TypeScreenContext.INVOICEINFO, invoicesInfoScreenSupplier);
    }

    public static final ITypeScreen getTypeScreen(TypeScreenContext typeContext) {
        return screensMap.get(typeContext).get();
    }
}
