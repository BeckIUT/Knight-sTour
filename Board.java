/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knightstour;
import java.awt.Color;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
/**
 *
 * @author Пользователь``
 */
public class Board extends JFrame{
    private JLabel labels[][];
    private JPanel cells[][];
    private int cell_Visited[][];
    private JTextField numbering[][];
    private final Icon icon = new ImageIcon(getClass().getResource("chess_piece_knight.png"));
    private int x_position = 0;
    private int y_position = 0;
    private int size = 0;
    private int move_Counter = 0;
    private final int horizontal[] = {1,1,2,2,-1,-1,-2,-2};
    private final int vertical[] = {2,-2,1,-1,2,-2,1,-1};
    private Color exColor;
    private boolean knight_Placed = false;
    
    Board(){}
    
    Board(int dimension){  // parametrized constructor
        super("Knoght's Tour");
        size = dimension;
        setLayout(new GridLayout(size,size));
        labels = new JLabel[size][size];
        cells = new JPanel[size][size];
        cell_Visited = new int[size][size];
        numbering = new JTextField[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                labels[i][j] = new JLabel(icon);
                cells[i][j] = new JPanel();
                labels[i][j].setVisible(false);
                numbering[i][j] = new JTextField();
                cell_Visited[i][j] = 0;
                final int current_y = j;
                final int current_x = i;
                labels[i][j].add(numbering[i][j]);
                cells[i][j].add(labels[i][j]);
                cells[i][j].addMouseListener(new MouseAdapter (){
                    
                    @Override
                    public void mouseClicked( MouseEvent e)
                    {

                        if(!knight_Placed)
                        {
                            x_position = current_x;
                            y_position = current_y;
                            cells[x_position][y_position].setBackground(Color.MAGENTA);
                            // At the end, the cell, where the cursor was put after knight had been placed, may go back to its original color
                            labels[current_x][current_y].setVisible(true);
                            cell_Visited[current_x][current_y] = 1;
                            knight_Placed = true;
                        }
                    }
                    @Override
                    public void mousePressed( MouseEvent e){                    
                    }
                    @Override
                    public void mouseReleased( MouseEvent e){
                    }
                    @Override
                    public void mouseDragged( MouseEvent e){
                    }
                    @Override
                    public void mouseMoved( MouseEvent e){                    
                    }
                    @Override
                    public void mouseEntered(MouseEvent e){
                        exColor = e.getComponent().getBackground();
                        e.getComponent().setBackground(Color.GREEN);
                    }
                    @Override
                    public void mouseExited(MouseEvent e){
                      e.getComponent().setBackground(exColor);
                    }
                });                                
                        if( ( i + j ) % 2 == 0)
                            cells[i][j].setBackground(Color.white);
                        else
                            cells[i][j].setBackground(Color.black);
                        
                add(cells[i][j]);
            }
        }
        
                addKeyListener(new KeyListener(){
                    @Override
                    public void keyTyped(KeyEvent ke) {}
                    @Override
                    public void keyPressed(KeyEvent ke) {
                        if(knight_Placed){
                            if(move_Counter == size*size - 1){
                                cells[x_position][y_position].setBackground(Color.MAGENTA);
                                labels[x_position][y_position].setIcon(null);
                                labels[x_position][y_position].setText(String.valueOf(++move_Counter));
                                JOptionPane.showMessageDialog(null, "Tour is COMPLETED", null, JOptionPane.INFORMATION_MESSAGE);
                            }else{
                                if(ke.getKeyCode() == KeyEvent.VK_SPACE){
                                   next_Move(x_position, y_position);
                                }
                            }
                        }else{
                           JOptionPane.showMessageDialog(null, "Choose a place for KNIGHT", null, JOptionPane.INFORMATION_MESSAGE); 
                        }
                    }
                    @Override
                    public void keyReleased(KeyEvent ke) {}
                });
    }
    
    private void next_Move(int x, int y){
        int possible_moves = 0;
        int max_moves = 8;
        
        int x_next, y_next, x_next_next, y_next_next;
        int nearest_x = -1;
        int nearest_y = -1;
        
        try{
            for(int i = 0; i < 8; i++){
                x_next = x + horizontal[i];
                y_next = y + vertical[i];
                
                if(is_Safe(x_next,y_next)){
                    possible_moves = 0;
                    for(int j = 0; j < 8; j++){
                        x_next_next = x_next + horizontal[j];
                        y_next_next = y_next + vertical[j];
                        if(is_Safe(x_next_next, y_next_next)){
                            possible_moves++;
                        }
                    }
                    if(max_moves >= possible_moves){
                        max_moves = possible_moves;
                        nearest_x = x_next;
                        nearest_y = y_next;
                    }   
                }
            }
                labels[x_position][y_position].setIcon(null);
                labels[x_position][y_position].setText(String.valueOf(++move_Counter));
                cells[x_position][y_position].setBackground(Color.MAGENTA);
                cell_Visited[x_position][y_position] = 1;
                x_position = nearest_x;
                y_position = nearest_y;
                labels[x_position][y_position].setVisible(true);
        }catch(ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "The Solution does NOT exist!", null, JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean is_Safe(int x, int y){
        return (( x >= 0 ) && ( x < size ) && ( y >= 0 ) && ( y < size ) && ( cell_Visited[x][y] != 1 ));
    }
}