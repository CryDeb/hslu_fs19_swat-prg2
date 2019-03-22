package connect4.controller;

import connect4.model.GameState;
import connect4.model.network.NetworkPlayerSearcher;
import connect4.model.network.request.handling.NetworkRequestManager;
import connect4.model.network.Settings;
import connect4.views.interfaces.GameView;
import connect4.views.interfaces.HelpView;
import connect4.views.interfaces.LocalGameCreationView;
import connect4.views.interfaces.NetworkView;
import connect4.views.interfaces.StartView;
import connect4.views.interfaces.ViewHandler;
import connect4.views.subpanel.PlayerTurn;

public class Navigator {

    private final ViewHandler viewHandler;
    private final NetworkPlayerSearcher networkPlayerSearcher;
    private final NetworkRequestManager networkRequestManager;

    public Navigator(ViewHandler viewHandler, NetworkPlayerSearcher networkPlayerSearcher, NetworkRequestManager networkRequestManager) {
        this.viewHandler = viewHandler;
        this.networkPlayerSearcher = networkPlayerSearcher;
        this.networkRequestManager = networkRequestManager;
    }

    public void navigateToStartView() {
        StartView view = this.viewHandler.switchToStartView();
        StartViewController controller = new StartViewController(this);
        view.setListener(controller);
        controller.init();
    }

    public void navigateToLocalGameCreationView() {
        LocalGameCreationView view = this.viewHandler.switchToLocalGameCreationView();
        LocalGameCreationViewController controller = new LocalGameCreationViewController(this);
        view.setListener(controller);
        controller.init();
    }

    public void navigateToNetworkView() {
        NetworkView view = this.viewHandler.switchToNetworkView();
        NetworkViewController controller = new NetworkViewController(view, this, this.networkPlayerSearcher);
        view.setListener(controller);
        controller.init();
    }

    public void navigateToGameViewForLocalPlay() {
        GameView view = this.switchToGameViewWithSizeDefinedInSettings();
        GameViewController controller = new LocalGameViewController(view, this);
        view.setListener(controller);
        controller.init(GameState.OpponentsTurn);
    }

    public void navigateToGameViewForResumingLocalGame() {
        GameView view = this.switchToGameViewWithSizeDefinedInSettings();
        GameViewController controller = new LocalGameViewController(view, this);
        view.setListener(controller);
        controller.initForResumeGame();
    }
    
    public void navigateToGameViewForInitializingNetworkPlay(String ipAddress) {
        GameView view = this.switchToGameViewWithSizeDefinedInSettings();
        GameViewController controller = new NetworkGameViewController(view, this, this.networkRequestManager, ipAddress);
        view.setListener(controller);
        view.setPlayer(PlayerTurn.OTHER_PLAYER);
        controller.init(GameState.OpponentsTurn);
    }
    
    public void navigateToGameViewForAcceptingNetworkPlay(String ipAddress) {
        GameView view = this.switchToGameViewWithSizeDefinedInSettings();
        GameViewController controller = new NetworkGameViewController(view, this, this.networkRequestManager, ipAddress);
        view.setListener(controller);
        view.setPlayer(PlayerTurn.LOCAL_PLAYER);
        controller.init(GameState.MyTurn);
    }

    public void navigateToHelpView() {
        HelpView view = this.viewHandler.switchToHelpView();
        HelpViewController controller = new HelpViewController(this);
        view.setListener(controller);
        controller.init();
    }
    
    private GameView switchToGameViewWithSizeDefinedInSettings(){
        return this.viewHandler.switchToGameView(Settings.getGameFieldWidth(), Settings.getGameFieldHeight());
    }
}
