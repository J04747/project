public class Battle {
    private Pokemon player;
    private Pokemon boss;
    private rolling_dice dice;

    public Battle(Pokemon player, Pokemon boss) {
        this.player = player;
        this.boss = boss;
        this.dice = new rolling_dice();
    }

    public Pokemon getPlayer() {
        return player;
    }

    public Pokemon getBoss() {
        return boss;
    }

    public String attack() {
        boolean playerWins = dice.Battledice();
        int playerRoll = dice.getPlayerDice();
        int bossRoll = dice.getBossDice();

        double playerdamage = player.getAttack() * Attributes.findMultiplier(player.getAttribute(), boss.getAttribute());
        double bossdamage = boss.getAttack() * Attributes.findMultiplier(boss.getAttribute(), player.getAttribute());

        if (playerWins) {
            boss.setHp(boss.getHp() - playerdamage);
            if (playerdamage > player.getAttack()){
                System.out.println("Yesss, boss weak in "+player.getAttribute());
            }
            return String.format(
                "%s rolled %d, %s rolled %d\n%s attacks!\n%s's HP is now %.1f",
                player.getName(), playerRoll,
                boss.getName(), bossRoll,
                player.getName(),
                boss.getName(), boss.getHp()
            );
        } else {
            player.setHp(player.getHp() - bossdamage);
            if (bossdamage > boss.getAttack()){
                System.out.println("Nooo, pokemon weak in "+boss.getAttribute());
            }
            return String.format(
                "%s rolled %d, %s rolled %d\n%s attcks!\n%s's HP is now %.1f",
                player.getName(), playerRoll,
                boss.getName(), bossRoll,
                boss.getName(),
                player.getName(), player.getHp()
            );
        }
    }

    public boolean isBattleOver() {
        return boss.getHp() <= 0 || player.getHp() <= 0;
    }

    public boolean callcatch() {
        return boss.getHp() <= 0;
    }

    public String getWinner() {
        if (player.getHp() <= 0) return boss.getName() + " wins!";
        if (boss.getHp() <= 0) return player.getName() + " wins!";
        return "No winner yet.";
    }

    @Override
    public String toString() {
        return String.format(
            "Battle Status:\nPlayer → %s (HP: %.1f)\nBoss → %s (HP: %.1f)",
            player.getName(), player.getHp(),
            boss.getName(), boss.getHp()
        );
    }
}


