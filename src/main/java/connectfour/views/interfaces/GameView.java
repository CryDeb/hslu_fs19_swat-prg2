package connectfour.views.interfaces;

import connectfour.views.subpanel.PlayerTurn;

public interface GameView extends ListenerSetter<GameViewListener> {
    void showNewDiskForMe(int column, int row);
    void showNewDiskForOpponent(int column, int row);
    void showIWonDialog();
    void showOpponentWonDialog();

    void setPlayer(PlayerTurn gameState);
    
}
