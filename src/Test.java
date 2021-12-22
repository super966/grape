import MathComponent.Matrix;
import MathComponent.Vector4d;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * @author LSY
 * @date 2021/12/09 16:01
 **/
public class Test extends JPanel implements KeyListener {

    static Color[][] Scene = new Color[800][600];
    int x=40,y=40;
    Test(){
        JFrame frame = new JFrame();
        frame.setSize( 800, 600);
        frame.setLayout(null);
        this.setBounds(0, 0, 800, 700);

        frame.setSize( 800, 600);
        frame.setLayout(null);

        final JSlider slider = new JSlider(0, 20, 10);

        // 设置主刻度间隔
        slider.setMajorTickSpacing(5);

        // 设置次刻度间隔
        slider.setMinorTickSpacing(1);

        // 绘制 刻度 和 标签
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);


        // 添加刻度改变监听器
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println("当前值: " + slider.getValue());
            }
        });

        // 添加滑块到内容面板
        this.add(slider);

        frame.addKeyListener(this);
        frame.add(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    public void moveleft(){
        this.x--;
    }

    public void moveright(){
        this.x++;
    }

    public void moveup(){
        this.y--;
    }
    public void movedown(){
        this.y++;
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_A){
            x++;
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
           x--;
        }

        if(e.getKeyCode() == KeyEvent.VK_W){
            y++;
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            y--;
        }
        System.out.println(x + " " + y);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public static void main(String[] args) {
        new Test();
        Random r = new Random(35);
        for (int i = 0; i < Scene.length; i++) {
            for (int j = 0; j < Scene[i].length; j++) {
                Scene[i][j] = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
            }
        }
    }
}

