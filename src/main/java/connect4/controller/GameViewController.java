package connect4.controller;

import connect4.model.DiskPosition;
import connect4.model.Game;
import connect4.model.GameState;
import connect4.model.TurnResult;
import connect4.views.interfaces.GameView;
import connect4.views.interfaces.GameViewListener;
import connect4.model.WinState;
import connect4.model.TurnEvaluatedListener;

public abstract class GameViewController implements GameViewListener, TurnEvaluatedListener {

    private final GameView view;
    private final Navigator navigator;
    
    Game game;

    GameViewController(GameView view, Navigator navigator) {
        this.view = view;
        this.navigator = navigator;
    }

    abstract void init(GameState startGameState);

    public abstract void initForResumeGame();
    
    @Override
    public abstract void SaveGamePressed();
    @Override public void CloseGamePressed(){
        this.navigator.navigateToStartView();
    }
    
    @Override
    public void DiskColumnPressed(int column) {
        this.game.playDisk(column);
    }

    @Override
    public void myTurnEvaluated(TurnResult turnResult) {
        DiskPosition diskPosition = turnResult.getDiskPosition();
        this.view.showNewDiskForMe(diskPosition.getColumn(), diskPosition.getRow());

        this.notifyViewAboutWinIfRequired(turnResult.getWinState());
    }
    
    @Override
    public void opponentTurnEvaluated(TurnResult turnResult) {
        DiskPosition diskPosition = turnResult.getDiskPosition();
        this.view.showNewDiskForOpponent(diskPosition.getColumn(), diskPosition.getRow());

        this.notifyViewAboutWinIfRequired(turnResult.getWinState());
    }

    private void notifyViewAboutWinIfRequired(WinState winState) {
        if (winState == WinState.OpponentWon) {
            this.view.showOpponentWonDialog();
            this.navigator.navigateToStartView();
        }
        if (winState == WinState.IWon) {
            this.view.showIWonDialog();
            this.navigator.navigateToStartView();
        }
    }
}
