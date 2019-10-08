package com.example.javaqa.models;

public class GameStatistics  {

  private Integer wins;
  private Integer loses;
  private Integer draws;

  private Integer games;
  private Integer averageScore;
  private Integer gamesWithOutLoses;

  private Integer javaCoreFullGames;
  private Integer buildToolsFullGames;
  private Integer vscFullGames;
  private Integer databasesFullGames;

  private Integer javaCoreWins;
  private Integer buildToolsWins;
  private Integer vscWins;
  private Integer databasesWins;

  public GameStatistics(){}

  public GameStatistics(Integer wins, Integer loses, Integer draws, Integer games, Integer averageScore, Integer gamesWithOutLoses,
                        Integer javaCoreFullGames, Integer buildToolsFullGames, Integer vscFullGames,
                        Integer databasesFullGames, Integer javaCoreWins, Integer buildToolsWins, Integer vscWins, Integer databasesWins) {
    this.wins = wins;
    this.loses = loses;
    this.draws = draws;
    this.games = games;
    this.averageScore = averageScore;
    this.gamesWithOutLoses = gamesWithOutLoses;
    this.javaCoreFullGames = javaCoreFullGames;
    this.buildToolsFullGames = buildToolsFullGames;
    this.vscFullGames = vscFullGames;
    this.databasesFullGames = databasesFullGames;
    this.javaCoreWins = javaCoreWins;
    this.buildToolsWins = buildToolsWins;
    this.vscWins = vscWins;
    this.databasesWins = databasesWins;
  }

  public Integer getWins() {
    return wins;
  }

  public void setWins(Integer wins) {
    this.wins = wins;
  }

  public Integer getLoses() {
    return loses;
  }

  public void setLoses(Integer loses) {
    this.loses = loses;
  }

  public Integer getDraws() {
    return draws;
  }

  public void setDraws(Integer draws) {
    this.draws = draws;
  }

  public Integer getGames() {
    return games;
  }

  public void setGames(Integer games) {
    this.games = games;
  }

  public Integer getAverageScore() {
    return averageScore;
  }

  public void setAverageScore(Integer averageScore) {
    this.averageScore = averageScore;
  }

  public Integer getGamesWithOutLoses() {
    return gamesWithOutLoses;
  }

  public void setGamesWithOutLoses(Integer gamesWithOutLoses) {
    this.gamesWithOutLoses = gamesWithOutLoses;
  }

  public Integer getJavaCoreFullGames() {
    return javaCoreFullGames;
  }

  public void setJavaCoreFullGames(Integer javaCoreFullGames) {
    this.javaCoreFullGames = javaCoreFullGames;
  }

  public Integer getBuildToolsFullGames() {
    return buildToolsFullGames;
  }

  public void setBuildToolsFullGames(Integer buildToolsFullGames) {
    this.buildToolsFullGames = buildToolsFullGames;
  }

  public Integer getVscFullGames() {
    return vscFullGames;
  }

  public void setVscFullGames(Integer vscFullGames) {
    this.vscFullGames = vscFullGames;
  }

  public Integer getDatabasesFullGames() {
    return databasesFullGames;
  }

  public void setDatabasesFullGames(Integer databasesFullGames) {
    this.databasesFullGames = databasesFullGames;
  }

  public Integer getJavaCoreWins() {
    return javaCoreWins;
  }

  public void setJavaCoreWins(Integer javaCoreWins) {
    this.javaCoreWins = javaCoreWins;
  }

  public Integer getBuildToolsWins() {
    return buildToolsWins;
  }

  public void setBuildToolsWins(Integer buildToolsWins) {
    this.buildToolsWins = buildToolsWins;
  }

  public Integer getVscWins() {
    return vscWins;
  }

  public void setVscWins(Integer vscWins) {
    this.vscWins = vscWins;
  }

  public Integer getDatabasesWins() {
    return databasesWins;
  }

  public void setDatabasesWins(Integer databasesWins) {
    this.databasesWins = databasesWins;
  }
}
