package com.example.gobanggame.bean;

public class ChessTable {
    private int id;
    private  String userBlack;
    private  String userWhite;
    private String lastCheck;
    private int gameId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserBlack() {
        return userBlack;
    }

    public void setUserBlack(String userBlack) {
        this.userBlack = userBlack;
    }

    public String getUserWhite() {
        return userWhite;
    }

    public void setUserWhite(String userWhite) {
        this.userWhite = userWhite;
    }

    public String getLastCheck() {
        return lastCheck;
    }

    public void setLastCheck(String lastCheck) {
        this.lastCheck = lastCheck;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
