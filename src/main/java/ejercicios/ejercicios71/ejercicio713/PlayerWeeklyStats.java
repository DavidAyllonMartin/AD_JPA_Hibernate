package ejercicios.ejercicios71.ejercicio713;

import java.math.BigDecimal;

public abstract class PlayerWeeklyStats {
    private int id;
    private String playerName;
    private String position;
    private String nflTeam;
    private int season;
    private int week;
    private int opponent;
    private BigDecimal fantasyPoints;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getNflTeam() {
        return nflTeam;
    }

    public void setNflTeam(String nflTeam) {
        this.nflTeam = nflTeam;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getOpponent() {
        return opponent;
    }

    public void setOpponent(int opponent) {
        this.opponent = opponent;
    }

    public BigDecimal getFantasyPoints() {
        return fantasyPoints;
    }

    public void setFantasyPoints(BigDecimal fantasyPoints) {
        this.fantasyPoints = fantasyPoints;
    }

    public abstract String buildMySQLInsert();
}
