package connect4.model;

public interface TurnEvaluatedListener {
    void opponentTurnEvaluated(TurnResult turnResult);
    void myTurnEvaluated(TurnResult turnResult);
}
