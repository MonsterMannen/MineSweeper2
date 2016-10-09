import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Gui extends JFrame {
    private int[][] field;
    private int width;
    private int height;
    private JButton[] buttons;

    public Gui(int width, int height){
        super("Minesweeper");
        this.width = width;
        this.height = height;
        buttons = new JButton[width*height];
    }

    public void setField(int[][] f){
        field = f;
    }

    public void initGui(){
        Container pane = getContentPane();
        pane.setLayout(new GridLayout(width, height));
        JButton button;

        int i = 0;
        for(int row = 0; row < height; row++){
            for(int col = 0; col < width; col++){
                int x = field[row][col];
                button = new JButton(x+"");
                button.setFont(new Font("Courier", Font.PLAIN, 26));
                pane.add(button);
                buttons[i++] = button;
            }
        }
    }

    public static void main(String[] args) {
        int w = 5;
        int h = 5;
        int bombs = 2;
        Game game = new Game(w, h, bombs);
        Gui window = new Gui(w, h);
        window.setField(game.getField());
        window.initGui();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.pack();
        window.setVisible(true);
    }
}
