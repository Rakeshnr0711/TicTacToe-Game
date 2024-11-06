import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGame {
    private JFrame frame;
    private JButton[] buttons;
    private char currentPlayer;
    private boolean gameOver;

    public TicTacToeGame() {
        frame = new JFrame("Tic-Tac-Toe");
        frame.setLayout(new GridLayout(3, 3));
        buttons = new JButton[9];
        currentPlayer = 'X';
        gameOver = false;

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 60));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(new ButtonClickListener(i));
            frame.add(buttons[i]);
        }

        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        private int index;

        public ButtonClickListener(int index) {
            this.index = index;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (gameOver || !buttons[index].getText().equals("")) {
                return; // Ignore if game is over or the button is already clicked
            }
            
            buttons[index].setText(String.valueOf(currentPlayer));
            if (checkWin()) {
                JOptionPane.showMessageDialog(frame, currentPlayer + " wins!");
                gameOver = true;
                return;
            }

            if (checkDraw()) {
                JOptionPane.showMessageDialog(frame, "It's a draw!");
                gameOver = true;
                return;
            }

            // Switch player
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    private boolean checkWin() {
        // Check rows, columns, and diagonals for a win
        return (checkLine(0, 1, 2) || checkLine(3, 4, 5) || checkLine(6, 7, 8) // Rows
                || checkLine(0, 3, 6) || checkLine(1, 4, 7) || checkLine(2, 5, 8) // Columns
                || checkLine(0, 4, 8) || checkLine(2, 4, 6)); // Diagonals
    }

    private boolean checkLine(int a, int b, int c) {
        return (buttons[a].getText().equals(String.valueOf(currentPlayer)) 
                && buttons[b].getText().equals(String.valueOf(currentPlayer)) 
                && buttons[c].getText().equals(String.valueOf(currentPlayer)));
    }

    private boolean checkDraw() {
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                return false; // If there's an empty button, it's not a draw
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new TicTacToeGame();
    }
}
