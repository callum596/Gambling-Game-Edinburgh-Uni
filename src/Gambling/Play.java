package Gambling;
import java.util.Arrays;
import java.util.Random;

import javax.swing.JOptionPane;

import Gambling.Game;

public class Play {
  // Declare variables
  private String Name;
  private int Balance;
  private int Turns;
  private int Wins;

  // initialise objects
  public Play(String name, int balance) {
    setName(name);
    setBalance(balance);
  }

  // getters and setters
  public void setName(String name) {
    this.Name = name;
  }
  public void setBalance(int balance) {
    this.Balance = balance;
  }
  public int GetBalance() {
    return this.Balance;
  }
  public void IncreaseTurns(int turns) {
    this.Turns = this.Turns + turns;
  }
  public int GetTurns() {
    return this.Turns;
  }
  public String GetName() {
    return this.Name;
  }
  public int GetWins() {
    return Wins;
  }
  public void IncreaseWins(int wins) {
    this.Wins = this.Wins + wins;
  }

  // the method to roll the dice / check if in DebugMode and take inputs for it
  public void RollDice() {
    // temp variables
    int[] Dices = new int[6];
    int Sixes = 0;
    this.Balance = this.Balance - 1;
    this.IncreaseTurns(1);
    int count = 0;
    int num = 0;
    //
    // check for debug mode
    if (Game.GetDebugMode() == true) {
      String Output = "Enter a number between 1 and  6.";

      while (count < 6) {
        String UserInput = JOptionPane.showInputDialog(Output);

        try {
          num = Integer.parseInt(UserInput);
        } catch (Exception e) {
          JOptionPane.showMessageDialog(null, "enter a number not a string");
        }
        // validate inputs
        if ((num < 7) && (num > 0)) {
          Dices[count] = num;
          count++;
        } else if ((num > 6) || (num < 1)) {
          System.out.println("not in Range");
        }
      }
    } else if (Game.GetDebugMode() == false) {
      while (count < 6) {
        Dices[count] = new Random().nextInt(6) + 1;
        count++;
      }
    }
    // count the sixes
    JOptionPane.showMessageDialog(
            null, this.Name + " you rolled.. " + Arrays.toString(Dices));
    Game.AddGameSize();
    for (int i : Dices) {
      if (i == 6) {
        Sixes = Sixes + 1;
      }
    }
    // Switch statement for calculating the prizes
    switch (Sixes) {
      case 1:
        //
        this.Balance = this.Balance + 1;
        IncreaseWins(1);
        break;
      case 2:
        //
        this.Balance = this.Balance + 40;
        IncreaseWins(1);
        break;
      case 3:
        //
        this.Balance = this.Balance + 250;
        IncreaseWins(1);
        break;
      case 4:
        //
        this.Balance = this.Balance + 1500;
        IncreaseWins(1);
        break;
      case 5:
        //
        this.Balance = this.Balance + 10000;
        IncreaseWins(1);
        break;
      case 6:
        //
        this.Balance = this.Balance + 50000;
        IncreaseWins(1);
        break;
    }
  }

  public int TotalWinnings() {
    return Balance;
  }
}
