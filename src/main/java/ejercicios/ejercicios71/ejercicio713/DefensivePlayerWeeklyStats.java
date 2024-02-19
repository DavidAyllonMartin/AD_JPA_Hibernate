package ejercicios.ejercicios71.ejercicio713;

import java.math.BigDecimal;

public class DefensivePlayerWeeklyStats extends PlayerWeeklyStats{
    private int totalTackles;
    private int assistedTackles;
    private BigDecimal sacks;
    private int tacklesForLoss;
    private BigDecimal sackYards;
    private int interceptions;
    private int forcedFumbles;
    private int fumblesRecovered;
    private int interceptionsTouchdown;
    private int fumblesTouchdown;
    private int blockedKickTouchdowns;
    private int safeties;
    private int blockedKicks;
    private int passDeflections;
    private int quarterbackHits;
    private int interceptionReturnYards;
    private int fumbleReturnYards;

    public int getTotalTackles() {
        return totalTackles;
    }

    public void setTotalTackles(int totalTackles) {
        this.totalTackles = totalTackles;
    }

    public int getAssistedTackles() {
        return assistedTackles;
    }

    public void setAssistedTackles(int assistedTackles) {
        this.assistedTackles = assistedTackles;
    }

    public BigDecimal getSacks() {
        return sacks;
    }

    public void setSacks(BigDecimal sacks) {
        this.sacks = sacks;
    }

    public int getTacklesForLoss() {
        return tacklesForLoss;
    }

    public void setTacklesForLoss(int tacklesForLoss) {
        this.tacklesForLoss = tacklesForLoss;
    }

    public BigDecimal getSackYards() {
        return sackYards;
    }

    public void setSackYards(BigDecimal sackYards) {
        this.sackYards = sackYards;
    }

    public int getInterceptions() {
        return interceptions;
    }

    public void setInterceptions(int interceptions) {
        this.interceptions = interceptions;
    }

    public int getForcedFumbles() {
        return forcedFumbles;
    }

    public void setForcedFumbles(int forcedFumbles) {
        this.forcedFumbles = forcedFumbles;
    }

    public int getFumblesRecovered() {
        return fumblesRecovered;
    }

    public void setFumblesRecovered(int fumblesRecovered) {
        this.fumblesRecovered = fumblesRecovered;
    }

    public int getInterceptionsTouchdown() {
        return interceptionsTouchdown;
    }

    public void setInterceptionsTouchdown(int interceptionsTouchdown) {
        this.interceptionsTouchdown = interceptionsTouchdown;
    }

    public int getFumblesTouchdown() {
        return fumblesTouchdown;
    }

    public void setFumblesTouchdown(int fumblesTouchdown) {
        this.fumblesTouchdown = fumblesTouchdown;
    }

    public int getBlockedKickTouchdowns() {
        return blockedKickTouchdowns;
    }

    public void setBlockedKickTouchdowns(int blockedKickTouchdowns) {
        this.blockedKickTouchdowns = blockedKickTouchdowns;
    }

    public int getSafeties() {
        return safeties;
    }

    public void setSafeties(int safeties) {
        this.safeties = safeties;
    }

    public int getBlockedKicks() {
        return blockedKicks;
    }

    public void setBlockedKicks(int blockedKicks) {
        this.blockedKicks = blockedKicks;
    }

    public int getPassDeflections() {
        return passDeflections;
    }

    public void setPassDeflections(int passDeflections) {
        this.passDeflections = passDeflections;
    }

    public int getQuarterbackHits() {
        return quarterbackHits;
    }

    public void setQuarterbackHits(int quarterbackHits) {
        this.quarterbackHits = quarterbackHits;
    }

    public int getInterceptionReturnYards() {
        return interceptionReturnYards;
    }

    public void setInterceptionReturnYards(int interceptionReturnYards) {
        this.interceptionReturnYards = interceptionReturnYards;
    }

    public int getFumbleReturnYards() {
        return fumbleReturnYards;
    }

    public void setFumbleReturnYards(int fumbleReturnYards) {
        this.fumbleReturnYards = fumbleReturnYards;
    }


    @Override
    public String buildMySQLInsert() {
        String query = String.format("INSERT INTO defensive_player_stats (player_id, season, week, opponent, total_tackles, assisted_tackles, sacks, tackles_for_loss, sack_yards, interceptions, forced_fumbles, fumbles_recovered, interceptions_touchdown, fumbles_touchdown, blocked_kick_touchdowns, safeties, blocked_kicks, pass_deflections, quarterback_hits, interception_return_yards, fumble_return_yards, fantasy_points) VALUES (%d, %d, %d, %d, %d, %d, %s, %d, %s, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %d, %s);",
                getId(), getSeason(), getWeek(), getOpponent(), totalTackles, assistedTackles, sacks, tacklesForLoss, sackYards, interceptions, forcedFumbles, fumblesRecovered, interceptionsTouchdown, fumblesTouchdown, blockedKickTouchdowns, safeties, blockedKicks, passDeflections, quarterbackHits, interceptionReturnYards, fumbleReturnYards, getFantasyPoints());
        return query;
    }
}