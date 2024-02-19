package ejercicios.ejercicios71.ejercicio713;

public class Transaction {
    private String date;
    private int week;
    private String type;
    private int player;
    private int from;
    private int to;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public String buildMySQLInsert() {
        String insert = String.format("INSERT INTO fantasy_transactions (player_id, transaction_type, week, source_team, destination_team, transaction_date) VALUES (%d, '%s', %d, %d, %d, '%s');", player, type, week, from, to, date);
        return insert;
    }
}
