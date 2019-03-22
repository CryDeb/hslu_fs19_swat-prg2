package connect4.Controller;

import connect4.Model.GameState;
import connect4.Model.Network.NetworkPlayerSearcher;
import connect4.Model.Network.RequestHandling.NetworkRequestManager;
import connect4.Model.Network.Settings;
import connect4.Views.Interfaces.GameView;
import connect4.Views.Interfaces.HelpView;
import connect4.Views.Interfaces.LocalGameCreationView;
import connect4.Views.Interfaces.NetworkView;
import connect4.Views.Interfaces.StartView;
import connect4.Views.Interfaces.ViewHandler;
import connect4.Views.subPanel.PlayerTurn;

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
