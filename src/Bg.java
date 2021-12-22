import Component.Obj;
import Component.Scene;
import MathComponent.Matrix;
import MathComponent.Vector4d;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author LSY
 * @date 2021/12/22 18:08
 **/
public class Bg extends JFrame implements KeyListener, ChangeListener, ActionListener {

    Obj obj;
    JSlider slider ;
    JButton button ;
    JPanel panel = new JPanel();

    public void setSlider(JSlider slider) {
        this.slider = slider;
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public void setObj(Obj obj) {
        this.obj = obj;
    }

    public Bg(String name){
        super(name);
    }

    public void init(){

        button.setBounds(1020,0,30,30);
        slider.setBounds(0,0,500,50);
        slider.setMajorTickSpacing(5);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        this.add(panel);
        this.add(button);
        this.add(slider);
        this.slider.addChangeListener(this);
        this.button.addActionListener(this);
        this.obj.addKeyListener(this);
        this.add(obj);
        this.setBounds(0,0,1080,890);
        this.setVisible(true);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == button){
            obj.changeLIGHT();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_L || e.getKeyCode() == KeyEvent.VK_T || e.getKeyCode() == KeyEvent.VK_P){
            if (e.getKeyCode() == KeyEvent.VK_L) {
                obj.setLINEture();
            }
            if(e.getKeyCode() == KeyEvent.VK_P) {
                obj.changeLIGHT();
            }
            if (e.getKeyCode() == KeyEvent.VK_T) {
                obj.setLINEfalse();
            }
        }else {

            Vector4d pos = null;
            if (e.getKeyCode() == KeyEvent.VK_A) {
                pos = obj.moveRight(0.1);
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                pos = obj.moveLeft(0.1);
            }

            if (e.getKeyCode() == KeyEvent.VK_W) {
                pos = obj.moveUp(0.1);
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                pos = obj.moveDown(0.1);
            }
            if (e.getKeyCode() == KeyEvent.VK_Q) {
                pos = obj.forword(0.1);
            }
            if (e.getKeyCode() == KeyEvent.VK_E) {
                pos = obj.forword(-0.1);
            }
            Vector4d n_up = new Vector4d(Scene.getInstance().getCamera().getUp());
            Vector4d n_right = new Vector4d(Scene.getInstance().getCamera().getRight());
            Matrix n_viewTransform = new Matrix().viewTrans(pos, n_up, n_right);
            Scene.getInstance().getCamera().setPos(pos);
            Scene.getInstance().getCamera().setViewTransform(n_viewTransform);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        System.out.println("当前值: " + slider.getValue());
    }
}
