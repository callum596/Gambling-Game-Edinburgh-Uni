package Gambling;

public class Game {
  // variables
  private static int MaxGameSize = 10;
  private static int GameSize;
  private int HouseLossProfit;
  private static boolean DebugMode;

  // Methods to control the game
  public Game(int LossProfit) {
    SetLossProfit(LossProfit);
  }
  public int GetLossProfit() {
    return this.HouseLossProfit;
  }
  public void SetLossProfit(int amountToChange) {
    this.HouseLossProfit = this.HouseLossProfit + amountToChange;
  }
  public static int GetMaxGameSize() {
    return MaxGameSize;
  }
  public static void AddGameSize() {
    GameSize = GameSize + 1;
  }
  public static int GetGameSize() {
    return GameSize;
  }
  public void SetDebugMode(Boolean Debug) {
    this.DebugMode = Debug;
  }
  public static boolean GetDebugMode() {
    return DebugMode;
  }
}
