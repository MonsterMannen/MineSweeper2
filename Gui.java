import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gui {
    private JFrame frame;
    private int width;
    private int height;
    private JButton[] buttons;

    public Gui(int width, int height){
        frame = new JFrame("Minesweeper");
        this.width = width;
        this.height = height;
        buttons = new JButton[width*height];
        initGui();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void initGui(){
        Container pane = new JPanel();
        pane.setLayout(new GridLayout(height, width));
        JButton button;

        for(int i = 0; i < width*height; i++){
            button = new JButton(" ");
            button.setFont(new Font("Courier", Font.PLAIN, 26));
            pane.add(button);
            buttons[i] = button;
        }
        frame.add(pane);
        JButton restart = new JButton("Restart");
        //frame.add(restart);
    }

    public void newButtons(){
        for(int i = 0; i < width*height; i++){
            buttons[i].setText(" ");
        }
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public JButton[] getButtons(){
        return buttons;
    }

    /*
    public static void main(String[] args) {
        int w = 5;
        int h = 5;
        Gui window = new Gui(w, h);
    }
    */
}
