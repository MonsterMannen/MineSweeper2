import javax.swing.JButton;
import java.awt.*;
import java.awt.event.*;

public class Controller {
    private Game game;
    private Gui gui;
    private ActionListener listener;

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

        for(int i = 0; i < width*height; i++){
            row = i / width;
            col = i % width;
            if(visibleField[row][col]){
                int value = game.getField(row, col);
                buttons[i].setText(value+"");
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
        Game game = new Game(w, h, b);
        gui.newButtons();
        System.out.println("Starting");
    }

    public static void main(String[] args) {
        int w = 6;
        int h = 6;
        int bombs = 5;
        Gui gui = new Gui(w, h);
        Game game = new Game(w, h, bombs);
        Controller c = new Controller(game, gui);
        c.newGame(w, h, bombs);

    }
}
