package connect4.main;

import connect4.controller.Navigator;
import connect4.model.Network.NetworkPlayerSearcher;
import connect4.model.Network.RequestHandling.NetworkRequestManager;
import connect4.model.Network.RequestHandling.RequestHandlerFactory;
import connect4.model.Network.Settings;
import connect4.views.ViewHandlerImpl;

class Main {

    public static void main(String[] args) {
        Settings.init();
        
        RequestHandlerFactory requestHandlerFactory = new RequestHandlerFactory();
        NetworkRequestManager networkRequestManager = new NetworkRequestManager(requestHandlerFactory);
        networkRequestManager.start();
        
        final ViewHandlerImpl viewHandler = new ViewHandlerImpl() {};
        final NetworkPlayerSearcher networkPlayerSearcher = new NetworkPlayerSearcher();
        final Navigator navigator = new Navigator(viewHandler, networkPlayerSearcher, networkRequestManager);
        
        requestHandlerFactory.setNavigator(navigator);
        
        navigator.navigateToStartView();
    }
}
