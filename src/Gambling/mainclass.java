package Gambling;
import java.util.HashMap;

import javax.swing.*;

public class mainclass {
  public static void main(String[] args) {
    // declare the current game and the hashmap for plays
    Game CurrentGame = new Game(0);
    HashMap<String, Play> Players = new HashMap<String, Play>();

    // ask if the player needs debug mode
    int reply = JOptionPane.showConfirmDialog(null,
            "Do you want to play in debug mode", "", JOptionPane.YES_NO_OPTION);
    if (reply == JOptionPane.YES_OPTION) {
      CurrentGame.SetDebugMode(true);
    } else if (reply == JOptionPane.NO_OPTION) {
      CurrentGame.SetDebugMode(false);
    }

    ShowMenu(Players, CurrentGame);
  }
  // show the menu screen
  static void ShowMenu(HashMap<String, Play> Players, Game CurrentGame) {
    if (Game.GetGameSize() >= Game.GetMaxGameSize()) {
      CollateResults(Players, CurrentGame);
    }

    String Menuoutput, usersChoice;
    int num = 0;

    // Set output for the  menu
    Menuoutput = "Select one of the menu options \n\n";
    Menuoutput = Menuoutput + "1 - Play the game \n";
    Menuoutput = Menuoutput + "2 - Collate results \n";
    Menuoutput = Menuoutput + "3 - Exit \n";

    // take the menu input into a variable
    usersChoice = JOptionPane.showInputDialog(Menuoutput);

    try {
      num = Integer.parseInt(usersChoice);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "enter a number not a string");
      ShowMenu(Players, CurrentGame);
    }

    // compare the menu input
    if (num == 1) {
      // play the game
      Go(Players, CurrentGame);
    }
    if (num == 2) {
      // collate the results
      CollateResults(Players, CurrentGame);
    }
    if (num == 3) {
      // quit the system
      System.exit(0);
    }
  }
  // start a go
  static void Go(HashMap<String, Play> Players, Game CurrentGame) {
    String NameOutput = "Please enter a player name...";
    String Name = JOptionPane.showInputDialog(NameOutput);

    if (Players.get(Name) != null) {
      if (Players.get(Name).GetTurns() > 2) {
        JOptionPane.showMessageDialog(null, "This player has no more turns.");
      } else {
        Players.get(Name).RollDice();
      }
    } else {
      Players.put(Name, (new Play(Name, 0)));
      Players.get(Name).RollDice();
    }

    ShowMenu(Players, CurrentGame);
  }
  // collate the results
  static void CollateResults(HashMap<String, Play> Players, Game CurrentGame) {
    int HouseProfitLoss = 0;
    String finalOutput;
    for (Play i : Players.values()) {
      if (i.TotalWinnings() >= 0) {
        finalOutput = i.GetName() + " won \n\n";
        finalOutput = finalOutput + i.TotalWinnings() + " Pounds \n\n";
        finalOutput = finalOutput + "Through " + i.GetWins() + " wins";

        JOptionPane.showMessageDialog(null, finalOutput);
      } else if (i.TotalWinnings() < 0) {
        finalOutput = i.GetName() + " lost \n\n";
        finalOutput = finalOutput + i.TotalWinnings() + " Pounds \n\n";
        finalOutput = finalOutput + "Through " + i.GetWins() + " wins";

        JOptionPane.showMessageDialog(null, finalOutput);
      }
    }

    for (Play value : Players.values()) {
      HouseProfitLoss = (value.GetTurns() - value.GetBalance());
      CurrentGame.SetLossProfit(HouseProfitLoss);
    }

    if (HouseProfitLoss >= 0) {
      JOptionPane.showMessageDialog(null,
              "The house Made a profit of " + CurrentGame.GetLossProfit());
    } else if (HouseProfitLoss < 0) {
      JOptionPane.showMessageDialog(
              null, "The house Made a loss of " + CurrentGame.GetLossProfit());
    }
  }
}
