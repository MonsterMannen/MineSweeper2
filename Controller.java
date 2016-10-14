import javax.swing.JButton;
import java.awt.*;
import java.awt.event.*;

public class Controller {
    private Game game;
    private Gui gui;
    private ActionListener listener;
    private ActionListener restartListener;

    public Controller(Game game, Gui gui){
        this.game = game;
        this.gui = gui;
        initActionListeners();
    }

    private void initActionListeners(){
        listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton[] buttons = gui.getButtons();
                for(int i = 0; i < buttons.length; i++){
                    if(e.getSource() == buttons[i]){
                        buttonClicked(i);
                    }
                }
            }
        };

        JButton[] buttons = gui.getButtons();
        for(int i = 0; i < buttons.length; i++){
            buttons[i].addActionListener(listener);
        }

        restartListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGame(game.getWidth(), game.getHeight(), game.getBombs());
            }
        };
        gui.getRestartButton().addActionListener(restartListener);
    }

    private void buttonClicked(int btn){
        JButton[] buttons = gui.getButtons();
        boolean[][] visibleField = game.getVisibleField();
        int width = gui.getWidth();
        int height = gui.getHeight();
        int row = btn / width;
        int col = btn % width;

        System.out.println("row:" + row + " col:" + col);
        game.clickField(row, col);

        // reveal all visible buttons
        for(int i = 0; i < width*height; i++){
            row = i / width;
            col = i % width;
            if(visibleField[row][col]){
                int value = game.getField(row, col);
                switch(value){
                    case -1:
                        buttons[i].setText("X");
                        buttons[i].setBackground(new Color(140, 0, 0));
                        break;
                    case 0:
                        buttons[i].setText(" ");
                        break;
                    case 1:
                        buttons[i].setText("<html><font color=blue>" + value + "</font></html>");
                        break;
                    case 2:
                        buttons[i].setText("<html><font color=green>" + value + "</font></html>");
                        break;
                    case 3:
                        buttons[i].setText("<html><font color=red>" + value + "</font></html>");
                        break;
                    case 4:
                        buttons[i].setText("<html><font color=black>" + value + "</font></html>");
                        break;
                    default:
                        buttons[i].setText(value+"");
                        break;
                }
                buttons[i].setEnabled(false);
            }
        }
        if(game.isGameOver()){
            System.out.println("game lost");
        }
        if(game.isGameWon()){
            System.out.println("game won");
        }
    }

    public void newGame(int w, int h, int b){
        game = new Game(w, h, b);
        gui.newButtons();
        System.out.println("Starting");
    }

    public static void main(String[] args) {
        int w = 7;
        int h = 7;
        int bombs = 7;
        Gui gui = new Gui(w, h);
        Game game = new Game(w, h, bombs);
        Controller c = new Controller(game, gui);
        c.newGame(w, h, bombs);
    }
}
