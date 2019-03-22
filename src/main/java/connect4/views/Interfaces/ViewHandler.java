package connect4.views.Interfaces;

public interface ViewHandler {
    StartView switchToStartView();
    LocalGameCreationView switchToLocalGameCreationView();
    HelpView switchToHelpView();
    NetworkView switchToNetworkView();
    GameView switchToGameView(int width, int height);
}
