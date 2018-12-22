
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//Heuristic, cost and goal state not implemented since we're not gonna perform
//AStar, UCS or Bidirectional on this problem!
public class Rubik implements OptimizationProblem {

    private ArrayList<Action> acts;
    int[] initColors = new int[24];

    public Rubik() {
        Scanner input = null;
        try {
            input = new Scanner(new File("1.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 24; i++) {
            initColors[i] = input.nextInt();
        }

        acts = new ArrayList<>();

        acts.add(new Action(0));
        acts.add(new Action(1));
        acts.add(new Action(2));
        acts.add(new Action(3));
        acts.add(new Action(4));
        acts.add(new Action(5));
//        
//        acts.add(new Action(11));
//        acts.add(new Action(12));
//        acts.add(new Action(21));
//        acts.add(new Action(22));
//        acts.add(new Action(31));
//        acts.add(new Action(32));
//        acts.add(new Action(41));
//        acts.add(new Action(42));
//        acts.add(new Action(51));
//        acts.add(new Action(52));
//        acts.add(new Action(61));
//        acts.add(new Action(62));

    }

    @Override
    public State initialState() {
        return new RubikState(initColors);
    }

    @Override
    public ArrayList<Action> actions(State s) {
        return acts;
    }

    @Override
    public ArrayList<State> result(State s, Action a) {
        int[] rs = ((RubikState) s).colors;
        int[] newColors = new int[24];
        System.arraycopy(rs, 0, newColors, 0, 24);
        switch (a.actionCode) {
            case 0: //Top
                newColors[4] = rs[20];
                newColors[5] = rs[21];
                newColors[16] = rs[4];
                newColors[17] = rs[5];
                newColors[14] = rs[17];
                newColors[15] = rs[16];
                newColors[20] = rs[15];
                newColors[21] = rs[14];
                newColors[0] = rs[2];
                newColors[1] = rs[0];
                newColors[2] = rs[3];
                newColors[3] = rs[1];
                break;
            case 1: //TopR
                newColors[20] = rs[4];
                newColors[21] = rs[5];
                newColors[4] = rs[16];
                newColors[5] = rs[17];
                newColors[17] = rs[14];
                newColors[16] = rs[15];
                newColors[15] = rs[20];
                newColors[14] = rs[21];
                newColors[2] = rs[0];
                newColors[0] = rs[1];
                newColors[3] = rs[2];
                newColors[1] = rs[3];
                break;
            case 2: //Front
                newColors[20] = rs[2];
                newColors[22] = rs[3];
                newColors[8] = rs[22];
                newColors[9] = rs[20];
                newColors[17] = rs[8];
                newColors[19] = rs[9];
                newColors[2] = rs[19];
                newColors[3] = rs[17];
                newColors[7] = rs[5];
                newColors[5] = rs[4];
                newColors[6] = rs[7];
                newColors[4] = rs[6];
                break;
            case 3: //FrontR
                newColors[2] = rs[20];
                newColors[3] = rs[22];
                newColors[22] = rs[8];
                newColors[20] = rs[9];
                newColors[8] = rs[17];
                newColors[9] = rs[19];
                newColors[19] = rs[2];
                newColors[17] = rs[3];
                newColors[5] = rs[7];
                newColors[4] = rs[5];
                newColors[7] = rs[6];
                newColors[6] = rs[4];
                break;
            case 4: //Right
                newColors[1] = rs[5];
                newColors[3] = rs[7];
                newColors[15] = rs[3];
                newColors[13] = rs[1];
                newColors[9] = rs[13];
                newColors[11] = rs[15];
                newColors[5] = rs[9];
                newColors[7] = rs[11];
                newColors[20] = rs[22];
                newColors[21] = rs[20];
                newColors[22] = rs[23];
                newColors[23] = rs[21];
                break;
            case 5: //RightR
                newColors[5] = rs[1];
                newColors[7] = rs[3];
                newColors[3] = rs[15];
                newColors[1] = rs[13];
                newColors[13] = rs[9];
                newColors[15] = rs[11];
                newColors[9] = rs[5];
                newColors[11] = rs[7];
                newColors[22] = rs[20];
                newColors[20] = rs[21];
                newColors[23] = rs[22];
                newColors[21] = rs[23];
                break;
        }

        RubikState newState = new RubikState(newColors);
        ArrayList<State> singleState = new ArrayList<>();
        singleState.add(newState);
        return singleState;

    }

    @Override
    public int eval(State s) {
        int[] rs = ((RubikState) s).colors;

        int a = 0;
        for (int i = 0; i < 24; i += 4) {
            for (int j = 0; j < 4; j++) {
                for (int k = j + 1; k < 4; k++) {
                    if (rs[i + j] != rs[i + k]) {
                        a++;
                    }
                }
            }
        }
        return a;

    }

}

class RubikState implements State {

    public int[] colors;

    public RubikState(int[] colors) {
        this.colors = colors;
    }

    @Override
    public boolean isEquals(State s) {
        RubikState rs = (RubikState) s;
        for (int i = 0; i < 24; i++) {
            if (this.colors[i] != rs.colors[i]) {
                return false;
            }
        }
        return true;
    }

}
