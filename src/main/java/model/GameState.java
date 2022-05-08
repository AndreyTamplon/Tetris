package model;

public class GameState extends Model
{
    GameStates gameState;

    public GameState()
    {
        this.gameState = GameStates.RUNNING;
    }

    public GameStates getGameState()
    {
        return gameState;
    }

    public void setGameState(GameStates gameState)
    {
        this.gameState = gameState;
    }

    public void stopGame()
    {
        gameState = GameStates.STOPPED;
    }

    public void resumeGame()
    {
        gameState = GameStates.RUNNING;
    }

    public void endTheGame()
    {
        gameState = GameStates.GAME_OVER;
    }

    public boolean isEnded()
    {
        return gameState == GameStates.GAME_OVER;
    }

    public boolean isRunning()
    {
        return gameState == GameStates.RUNNING;
    }

    public boolean isStopped()
    {
        return gameState == GameStates.STOPPED;
    }

}
