package model;

public class Models
{
    private static final String boardModelName = "Board";
    private static final String scoreModelName = "Score";
    private static final String highScoresModelName = "HighScores";
    private static final String gameStateModelName = "GameState";
    public static String[] getModelsNames()
    {
        return new String[] {boardModelName, scoreModelName, highScoresModelName, gameStateModelName};
    }

    public static String getBoardModelName()
    {
        return boardModelName;
    }

    public static String getScoreModelName()
    {
        return scoreModelName;
    }

    public static String getHighScoresModelName()
    {
        return highScoresModelName;
    }

    public static String getGameStateModelName()
    {
        return gameStateModelName;
    }
}
