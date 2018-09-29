/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knightstour;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Пользователь
 */
public class KnightsTour {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int size = 0;
        try{
            size = Integer.parseInt(String.valueOf(JOptionPane.showInputDialog("Please, enter the board size!")));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Invalid value entered!", null, JOptionPane.ERROR_MESSAGE);
        }
        if(size < 5){
           JOptionPane.showMessageDialog(null, "No Solution Exists!", null, JOptionPane.INFORMATION_MESSAGE);
        }else{
            Board board = new Board(size);
            board.setVisible(true);
            board.setResizable(false);
            board.setLocation(200, 100);
            board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            board.setSize(600,600);
        }
    }
}