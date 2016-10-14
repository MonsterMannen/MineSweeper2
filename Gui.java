import java.awt.*;
import javax.swing.*;

public class Gui {
    private JFrame frame;
    private int width;
    private int height;
    private JButton[] buttons;
    private JButton restartButton;

    public Gui(int width, int height){
        this.width = width;
        this.height = height;
        frame = new JFrame("Minesweeper");
        buttons = new JButton[width*height];
        initGui();
    }

    public void initGui(){
        frame.setLayout(new BorderLayout());
        JButton button;
        Color c = new Color(150, 150, 150);
        //frame.setBackground(c);

        Container pane = new JPanel();
        pane.setBackground(c);
        pane.setLayout(new GridLayout(height, width));

        for(int i = 0; i < width*height; i++){
            button = new JButton(" ");
            button.setFont(new Font("Impact", Font.PLAIN, 12));
            button.setPreferredSize(new Dimension(40, 40));
            //c = new Color(180, 180, 180);
            //button.setBackground(c);
            button.setOpaque(true);
            button.setBorderPainted(true);
            pane.add(button);
            buttons[i] = button;
        }

        Container paneMenu = new JPanel();
        restartButton = new JButton("Restart");
        //paneMenu.setBackground(new Color(150, 150, 150));
        paneMenu.add(restartButton);

        frame.add(pane, BorderLayout.NORTH);
        frame.add(paneMenu, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    // clear all buttons
    public void newButtons(){
        for(int i = 0; i < width*height; i++){
            buttons[i].setText(" ");
            buttons[i].setEnabled(true);
            buttons[i].setBackground(new JButton().getBackground());
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

    public JButton getRestartButton(){
        return restartButton;
    }

    /*
    public static void main(String[] args) {
        int w = 5;
        int h = 5;
        Gui window = new Gui(w, h);
    }
    */
}
