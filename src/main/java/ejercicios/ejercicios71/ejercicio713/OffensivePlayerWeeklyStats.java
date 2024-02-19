package ejercicios.ejercicios71.ejercicio713;

public class OffensivePlayerWeeklyStats extends PlayerWeeklyStats{
    private int completions;
    private int incompletePasses;
    private int passingYards;
    private int passingTouchdowns;
    private int interceptions;
    private int timesSacked;
    private int rushYards;
    private int rushTouchdowns;
    private int receptions;
    private int receivingYards;
    private int receivingTouchdowns;
    private int returnYards;
    private int returnTouchdowns;
    private int fumbleReturnTouchdowns;
    private int twoPointConversions;
    private int fumblesLost;
    public int getCompletions() {
        return completions;
    }

    public void setCompletions(int completions) {
        this.completions = completions;
    }

    public int getIncompletePasses() {
        return incompletePasses;
    }

    public void setIncompletePasses(int incompletePasses) {
        this.incompletePasses = incompletePasses;
    }

    public int getPassingYards() {
        return passingYards;
    }

    public void setPassingYards(int passingYards) {
        this.passingYards = passingYards;
    }

    public int getPassingTouchdowns() {
        return passingTouchdowns;
    }

    public void setPassingTouchdowns(int passingTouchdowns) {
        this.passingTouchdowns = passingTouchdowns;
    }

    public int getInterceptions() {
        return interceptions;
    }

    public void setInterceptions(int interceptions) {
        this.interceptions = interceptions;
    }

    public int getTimesSacked() {
        return timesSacked;
    }

    public void setTimesSacked(int timesSacked) {
        this.timesSacked = timesSacked;
    }

    public int getRushYards() {
        return rushYards;
    }

    public void setRushYards(int rushYards) {
        this.rushYards = rushYards;
    }

    public int getRushTouchdowns() {
        return rushTouchdowns;
    }

    public void setRushTouchdowns(int rushTouchdowns) {
        this.rushTouchdowns = rushTouchdowns;
    }

    public int getReceptions() {
        return receptions;
    }

    public void setReceptions(int receptions) {
        this.receptions = receptions;
    }

    public int getReceivingYards() {
        return receivingYards;
    }

    public void setReceivingYards(int receivingYards) {
        this.receivingYards = receivingYards;
    }

    public int getReceivingTouchdowns() {
        return receivingTouchdowns;
    }

    public void setReceivingTouchdowns(int receivingTouchdowns) {
        this.receivingTouchdowns = receivingTouchdowns;
    }

    public int getReturnYards() {
        return returnYards;
    }

    public void setReturnYards(int returnYards) {
        this.returnYards = returnYards;
    }

    public int getReturnTouchdowns() {
        return returnTouchdowns;
    }

    public void setReturnTouchdowns(int returnTouchdowns) {
        this.returnTouchdowns = returnTouchdowns;
    }

    public int getFumbleReturnTouchdowns() {
        return fumbleReturnTouchdowns;
    }

    public void setFumbleReturnTouchdowns(int fumbleReturnTouchdowns) {
        this.fumbleReturnTouchdowns = fumbleReturnTouchdowns;
    }

    public int getTwoPointConversions() {
        return twoPointConversions;
    }

    public void setTwoPointConversions(int twoPointConversions) {
        this.twoPointConversions = twoPointConversions;
    }

    public int getFumblesLost() {
        return fumblesLost;
    }

    public void setFumblesLost(int fumblesLost) {
        this.fumblesLost = fumblesLost;
    }


    public String buildMySQLInsert() {
        String insert = String.format("INSERT INTO offensive_player_stats (player_id, season, week, opponent, completions, incomplete_passes, passing_yards, passing_touchdowns, interceptions, times_sacked, rush_yards, rush_touchdowns, receptions, receiving_yards, receiving_touchdowns, return_yards, return_touchdowns, fumble_return_touchdowns, two_point_conversions, fumbles_lost, fantasy_points) VALUES (%d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %s);", getId(), getSeason(), getWeek(), getOpponent(), completions, incompletePasses, passingYards, passingTouchdowns, interceptions, timesSacked, rushYards, rushTouchdowns, receptions, receivingYards, receivingTouchdowns, returnYards, returnTouchdowns, fumbleReturnTouchdowns, twoPointConversions, fumblesLost, getFantasyPoints());
        return insert;
    }
}

