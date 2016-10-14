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
                if(value == -1){
                    buttons[i].setText("X");
                    buttons[i].setBackground(new Color(200, 50, 50));
                }else if(value == 0){
                    buttons[i].setText(" ");
                }else if(value == 1){
                    //buttons[i].setText("<html><font color=blue>" + value + "</font></html>");
                    buttons[i].setForeground(Color.BLUE);
                    buttons[i].setText(value+"");
                }else if(value == 2){
                    //buttons[i].setText("<html><font color=green>" + value + "</font></html>");
                    buttons[i].setForeground(Color.GREEN);
                    buttons[i].setText(value+"");
                }else if(value == 3){
                    //buttons[i].setText("<html><font color=red>" + value + "</font></html>");
                    buttons[i].setForeground(Color.RED);
                    buttons[i].setText(value+"");
                }else if(value == 4){
                    //buttons[i].setText("<html><font color=black>" + value + "</font></html>");
                    buttons[i].setForeground(Color.BLACK);
                    buttons[i].setText(value+"");
                }else{
                    buttons[i].setText(value+"");
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
        int w = 8;
        int h = 8;
        int bombs = 8;
        Gui gui = new Gui(w, h);
        Game game = new Game(w, h, bombs);
        Controller c = new Controller(game, gui);
        c.newGame(w, h, bombs);
    }
}
