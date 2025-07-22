import java.util.Random;

public class rolling_dice {
    protected int playerDice;
    protected int BossDice;
    private Random random = new Random();

    public rolling_dice() {
        dice();
    }

    // Roll dice for both player and boss
    private void dice() {
        playerDice = random.nextInt(6) + 1; // 1-6
        BossDice = random.nextInt(6) + 1;   // 1-6
    }

    // Setter and Getter 
    public int getPlayerDice() {
        return playerDice;
    }

    public void setPlayerDice(int playerDice) {
        this.playerDice = playerDice;
    }

    public int getBossDice() {
        return BossDice;
    }

    public void setBossDice(int bossDice) {
        BossDice = bossDice;
    }

    // Battle dice logic
    public boolean Battledice() {
        dice();
        if (BossDice > playerDice) {
            return false;
        } else if (BossDice < playerDice) {
            return true;
        } else {
            // Tie: re-roll until not a tie
            return Battledice();
        }
    }
}