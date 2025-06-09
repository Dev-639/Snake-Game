import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class gameplay extends JPanel implements KeyListener,ActionListener {
    private ImageIcon title=new ImageIcon(getClass().getResource("./components/snaketitle.jpg"));
    private int[] snakexlength=new int[750];
    private int[] snakeylength=new int[750];

    private int foodx,foody;

    private boolean up = false;
    private boolean down = false;
    private boolean left = false;
    private boolean right = true;
    private ImageIcon upmouth=new ImageIcon(getClass().getResource("./components/upmouth.png"));
    private ImageIcon downmouth=new ImageIcon(getClass().getResource("./components/downmouth.png"));
    private ImageIcon leftmouth=new ImageIcon(getClass().getResource("./components/leftmouth.png"));
    private ImageIcon rightmouth=new ImageIcon(getClass().getResource("./components/rightmouth.png"));
    private ImageIcon snakeimage=new ImageIcon(getClass().getResource("./components/snakeimage.png"));
    private ImageIcon food=new ImageIcon(getClass().getResource("./components/enemy.png"));

    private Timer timer;
    private int delay=100;
    private int score=0;
    private boolean gameEnd=false;

    private int snakelength=3;
    private int moves=0;

    public gameplay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        timer=new Timer(delay, this);
        timer.start();

        newFood();
    }
    public void paint(Graphics g){
        
        super.paint(g);

        if(moves==0){
            snakexlength[0]=100;
            snakexlength[1]=75;
            snakexlength[2]=50;


            snakeylength[0]=100;
            snakeylength[1]=100;
            snakeylength[2]=100;
        }

        g.setColor(Color.WHITE);
        g.drawRect(24, 10, 851, 55);
        
        title.paintIcon(this, g, 25, 11);
        
        g.setColor(Color.WHITE);
        g.drawRect(24, 74, 851, 577);
        g.setColor(Color.BLACK);
        g.fillRect(25, 75, 850, 575);
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Segoe UI", Font.BOLD, 14));
        g.drawString("Score :"+score, 750, 30);
        g.drawString("Length :"+snakelength, 750, 50);

        if(right){
            rightmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
        }
        if(up){
            upmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
        }
        if(down){
            downmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
        }
        if(left){
            leftmouth.paintIcon(this, g, snakexlength[0], snakeylength[0]);
        }
        for(int i=1;i<snakelength;i++){
            snakeimage.paintIcon(this, g, snakexlength[i], snakeylength[i]);
        }

        food.paintIcon(this, g, foodx, foody);

        gameover();

        if(gameEnd){
            g.setColor(Color.white);
            g.setFont(new Font("Segoe UI", Font.BOLD, 50));
            g.drawString("Game Over", 300, 300);

            
            g.setFont(new Font("Segoe UI", Font.BOLD, 20));
            g.drawString("Press Space to Restart", 320, 350);

        }

        g.dispose();


    }



    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i=snakelength-1;i>0;i--){
            snakexlength[i]=snakexlength[i-1];
            snakeylength[i]=snakeylength[i-1];
        }
        if(left){
            snakexlength[0]-=25;
        }
        if(right){
            snakexlength[0]+=25;
        }
        if(down){
            snakeylength[0]+=25;
        }
        if(up){
            snakeylength[0]-=25;
        }
        if(snakexlength[0]>850) snakexlength[0]=25;
        if(snakexlength[0]<25) snakexlength[0]=850;

        if(snakeylength[0]>625) snakeylength[0]=75;
        if(snakeylength[0]<75) snakeylength[0]=625;

        
        if(snakexlength[0]==foodx && snakeylength[0]==foody){
            snakelength++;
            score++;
            newFood();
        }


        repaint();
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_LEFT && !right){
            left=true;
            right=false;
            up=false;
            down=false;
            moves++;
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT && !left){
            left=false;
            right=true;
            up=false;
            down=false;
            moves++;
        }
        if(e.getKeyCode()==KeyEvent.VK_UP && !down){
            left=false;
            right=false;
            up=true;
            down=false;
            moves++;
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN && !up){
            left=false;
            right=false;
            up=false;
            down=true;
            moves++;
        }
        if(e.getKeyCode()==KeyEvent.VK_SPACE){
            gameEnd=false;
            moves=0;
            snakelength=3;
            right=true;
            up=false;
            down=false;
            left=false;
            score=0;
            timer.start();
            repaint();
        }
    }

    public void newFood(){
        foodx=25 * (1 + (int)(Math.random() *34));
        foody=25 * (3 + (int)(Math.random() *23));
    }

    public void gameover(){
        for(int i=snakelength-1;i>0;i--){
            if(snakexlength[0]==snakexlength[i] && snakeylength[0]==snakeylength[i]) {
                timer.stop();
                gameEnd=true;
            }
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
    
    }
    @Override
    public void keyReleased(KeyEvent e) {
    
    }


}
