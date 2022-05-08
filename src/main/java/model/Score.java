package model;

public class Score extends Model
{
    private int score;

    public Score()
    {
        this.score = 0;
    }

    public int getScore()
    {
        return score;
    }

    public void resetScore()
    {
        score = 0;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public void increaseScore(int additive)
    {
        score += additive;
    }
}
