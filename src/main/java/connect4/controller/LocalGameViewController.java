package connect4.controller;

import connect4.model.AIPlayer;
import connect4.model.Game;
import connect4.model.GameField;
import connect4.model.GameState;
import connect4.model.network.Settings;
import connect4.views.interfaces.GameView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LocalGameViewController extends GameViewController {

    public LocalGameViewController(GameView view, Navigator navigator) {
        super(view, navigator);
    }

    @Override
    void init(GameState startGameState) {
        GameField gameField = new GameField();
        AIPlayer opponent = new AIPlayer(gameField);
        this.game = new Game(gameField, opponent, startGameState);
        this.game.setListener(this);
        opponent.setListener(this.game);
        opponent.startWithFirstDisk();
    }
    
    @Override
    public void SaveGamePressed() {
        try {
            String saveGamePath = Settings.getSaveGamePath();
            try (ObjectOutputStream gameSaveStream = new ObjectOutputStream(new FileOutputStream(saveGamePath))) {
                this.game.saveTo(gameSaveStream);
            }
        } catch (IOException ex) {
            Logger.getLogger(LocalGameViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initForResumeGame() {
        ObjectInputStream in;
        try {
            String saveGamePath = Settings.getSaveGamePath();
            in = new ObjectInputStream(new FileInputStream(saveGamePath));

            GameField gameField = new GameField();
            AIPlayer opponent = new AIPlayer(gameField);
            this.game = new Game(gameField, opponent, GameState.OpponentsTurn);
            this.game.setListener(this);
            opponent.setListener(this.game);

            this.game.resume(in);

            in.close();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(GameViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void CloseGamePressed() {
        SaveGamePressed();
        super.CloseGamePressed();
    }
}
