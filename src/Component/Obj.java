package Component;

import MathComponent.Matrix;
import MathComponent.Vector2d;
import MathComponent.Vector4d;
import MathComponent.Vertex;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LSY
 * @date 2021/12/09 13:05
 **/
public class Obj extends JPanel implements KeyListener, ChangeListener, ActionListener {

    private List<Triangle> ltri = new ArrayList<>();
    private Matrix worldmatrix;
    public JPanel panel = new JPanel(new BorderLayout());
    private Color[][] Frame = new Color[1000][800];
    private double[][] Z_Buffer = new double[1000][800];
    private boolean LINE = false;
    private boolean LIGHT = false;

    public List<Triangle> getLtri() {
        return ltri;
    }

    public void setSlider1(JSlider slider1) {
        this.slider1 = slider1;
    }

    private JSlider slider;
    private JSlider slider1;
    private JSlider slider2;

    public void setSlider2(JSlider slider2) {
        this.slider2 = slider2;
    }

    public void setSlider3(JSlider slider3) {
        this.slider3 = slider3;
    }

    private JSlider slider3;
    private JButton bli;
    private JButton bw;

    public void setBli(JButton bli) {
        this.bli = bli;
    }

    public void setBw(JButton bw) {
        this.bw = bw;
    }

    public void setBs(JButton bs) {
        this.bs = bs;
    }

    public void setBa(JButton ba) {
        this.ba = ba;
    }

    public void setBd(JButton bd) {
        this.bd = bd;
    }

    public void setBe(JButton be) {
        this.be = be;
    }

    public void setBq(JButton bq) {
        this.bq = bq;
    }

    public void setBl(JButton bl) {
        this.bl = bl;
    }

    public void setBt(JButton bt) {
        this.bt = bt;
    }

    private JButton bs;
    private JButton ba;
    private JButton bd;
    private JButton be;
    private JButton bq;
    private JButton bl;
    private JButton bt;


    public void changeLIGHT(){
        LIGHT = !LIGHT;
    }

    public void setLINEture() {
        this.LINE = true;
    }

    public void setLINEfalse() {
        this.LINE = false;
    }

    public Obj(){

        for (int i = 0; i < Frame.length; i++) {
            for (int j = 50; j <Frame[i].length; j++) {
                Frame[i][j] = Color.white;
                Z_Buffer[i][j] = Double.MAX_VALUE;
            }
        }
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
//
        for (int i = 0; i < ltri.size(); i++) {

            Camera c = Scene.getInstance().getCamera();
//            System.out.println(c);
            Triangle t = new Triangle(ltri.get(i));
            if(LINE){
                Matrix rotateX = new Matrix().rotateX(0.003);
                Matrix rotateY = new Matrix().rotateY(0.003);
                worldmatrix  = worldmatrix.mul(rotateX);
                worldmatrix = worldmatrix.mul(rotateY);
            }else{
                Matrix rotateX = new Matrix().rotateX(0.03);
                Matrix rotateY = new Matrix().rotateY(0.03);
                worldmatrix  = worldmatrix.mul(rotateX);
                worldmatrix = worldmatrix.mul(rotateY);

            }
            List<Triangle>  temp = t.draw(worldmatrix, c, LINE, LIGHT);
            for (int i1 = 0; i1 < temp.size(); i1++) {


                Vertex one = temp.get(i1).point[0];
                Vertex two = temp.get(i1).point[1];
                Vertex three = temp.get(i1).point[2];

                int x1 = (int) one.getPos().getX();
                int y1 = (int) one.getPos().getY();
                int x2 = (int) two.getPos().getX();
                int y2 = (int) two.getPos().getY();
                int x3 = (int) three.getPos().getX();
                int y3 = (int) three.getPos().getY();

                if ((x1 == x2 && x1 == x3) || (y1 == y2 && y1 == y3))
                    continue;

                if (one.getPos().getY() > two.getPos().getY()) {
                    Vertex swap = two;
                    two = one;
                    one = swap;
                }
                if (one.getPos().getY() > three.getPos().getY()) {
                    Vertex swap = three;
                    three = one;
                    one = swap;
                }
                if (two.getPos().getY() > three.getPos().getY()) {
                    Vertex swap = two;
                    two = three;
                    three = swap;
                }
//                x1 = (int) one.getPos().getX();
//                y1 = (int) one.getPos().getY();
//                x2 = (int) two.getPos().getX();
//                y2 = (int) two.getPos().getY();
//                x3 = (int) three.getPos().getX();
//                y3 = (int) three.getPos().getY();

                if(LINE){
                    Vector2d oneV2 = new Vector2d(one.getPos().getX(), one.getPos().getY());
                    Vector2d twoV2 = new Vector2d(two.getPos().getX(), two.getPos().getY());
                    Vector2d threeV2 = new Vector2d(three.getPos().getX(), three.getPos().getY());

                    dda(oneV2,twoV2,g2d,one.getColor(),two.getColor());
                    dda(twoV2,threeV2,g2d,two.getColor(),three.getColor());
                    dda(oneV2,threeV2,g2d,one.getColor(),three.getColor());
                }
                else{
                    if (Math.abs(one.getPos().getY() - two.getPos().getY()) < 0.00001f) {
                        downTriangle(one, two, three, g2d);
                    }
                    else if (Math.abs(two.getPos().getY() - three.getPos().getY()) < 0.00001f) {
                        topTriangle(one, two, three, g2d);
                    }
                    else {
                        int tempVer_x = (int) (one.getPos().getX() + 0.5 + 1.0 * (two.getPos().getY() - one.getPos().getY()) *
                                (three.getPos().getX() - one.getPos().getX()) / (three.getPos().getY() - one.getPos().getY()));
                        int tempVer_r = (int) (one.getColor().getRed() + 1.0 * (two.getPos().getY() - one.getPos().getY()) *
                                (three.getColor().getRed() - one.getColor().getRed()) / (three.getPos().getY() - one.getPos().getY()));
                        int tempVer_g = (int) (one.getColor().getGreen() + 1.0 * (two.getPos().getY() - one.getPos().getY()) *
                                (three.getColor().getGreen() - one.getColor().getGreen()) / (three.getPos().getY() - one.getPos().getY()));
                        int tempVer_b = (int) (one.getColor().getBlue() + 1.0 * (two.getPos().getY() - one.getPos().getY()) *
                                (three.getColor().getBlue() - one.getColor().getBlue()) / (three.getPos().getY() - one.getPos().getY()));

                        tempVer_r = tempVer_r < 0 ? 0 : Math.min(tempVer_r, 255);
                        tempVer_b = tempVer_b < 0 ? 0 : Math.min(tempVer_b, 255);
                        tempVer_g = tempVer_g < 0 ? 0 : Math.min(tempVer_g, 255);

                        Color tempVer_c = new Color(tempVer_r, tempVer_g, tempVer_b);
                        Vertex tempVer = new Vertex(two);
                        tempVer.setTexture(new TextureCoord(two.getTexture().getU(), two.getTexture().getV()));
                        tempVer.setNormal(new Vector4d(two.getNormal()));
                        tempVer.setMaterial(new Material(two.getMaterial().getKa(), two.getMaterial().getKd(), two.getMaterial().getKs(), two.getMaterial().getN()));
                        tempVer.setPos(new Vector4d(0, 0, 0, 0));
                        tempVer.setZ_deep(two.getZ_deep());
                        tempVer.getPos().setX(tempVer_x);
                        tempVer.getPos().setY(two.getPos().getY());
                        tempVer.getPos().setZ(two.getPos().getZ());
                        tempVer.getPos().setW(two.getPos().getW());
                        tempVer.setColor(tempVer_c);

                        double s = (two.getPos().getY() - one.getPos().getY()) / (three.getPos().getY() - one.getPos().getY());
                        double z1 = one.getZ_deep();
                        double z3 = three.getZ_deep();
                        double zt = 0;
                        double k = s;
                        if (z1 != 0 && z3 != 0) zt = 1 / z1 + s * (1 / z3 - 1 / z1);
                        if (zt != 0) zt = 1 / zt;
                        if (z1 != z3) k = (zt - z1) / (z3 - z1);

                        tempVer.setZ_deep(zt);

                        try {
                            tempVer.getTexture().setU(one.getTexture().getU() + k * (three.getTexture().getU() - one.getTexture().getU()));
                            tempVer.getTexture().setV(one.getTexture().getV() + k * (three.getTexture().getV() - one.getTexture().getV()));
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        topTriangle(one, tempVer, two, g2d);
                        downTriangle(tempVer, two, three, g2d);
                    }

                }

            }
        }
        if(LINE==false){
            for (int i = 0; i < Frame.length; i++) {
                for (int j = 10; j < Frame[i].length; j++) {
                    g2d.setColor(Frame[i][j]);
                    g2d.drawLine(i,j,i,j);
                }
            }
        }
        removeAll();
        repaint();
        if(LINE == false){
            for (int i = 0; i < Frame.length; i++) {
                for (int j = 0; j <Frame[i].length; j++) {
                    Frame[i][j] = Color.white;
                    Z_Buffer[i][j] = Double.MAX_VALUE;
                }
            }
        }
    }

    private void topTriangle(Vertex one, Vertex two, Vertex three, Graphics2D g2d) {
        //one 底
        if(Math.abs(one.getPos().getY()- three.getPos().getY())<0.00001f){
            Vertex temp = two;
            two = one;
            one = temp;
        }else if(Math.abs(two.getPos().getY() - one.getPos().getY()) < 0.00001f){
            Vertex temp = three;
            three = one;
            one = temp;
        }else if(Math.abs(three.getPos().getY() - two.getPos().getY()) < 0.00001f){

        }else{
            return;
        }
        if(three.getPos().getX() > two.getPos().getX()){
            Vertex temp = two;
            two = three;
            three = temp;
        }else if(Math.abs(two.getPos().getX() - one.getPos().getX()) < 0.0001f){
            return;
        }

        double x1 = one.getPos().getX();
        double x2 = two.getPos().getX();
        double x3 = three.getPos().getX();
        double y1 = one.getPos().getY();
        double y2 = two.getPos().getY();
        double y3 = three.getPos().getY();
        Color c1,c2,c3;

            c1 = one.getColor();
            c2 = two.getColor();
            c3 = three.getColor();


        double xleft = (x3-x1)/(y3-y1);
        double xright = (x2-x1)/(y2-y1);
        double red_l = ((c3.getRed() - c1.getRed())/(y3-y1));
        double red_r = ((c2.getRed() - c1.getRed())/(y2-y1));
        double blue_l = ((c3.getBlue() - c1.getBlue())/(y3-y1));
        double blue_r = ((c2.getBlue() - c1.getBlue())/(y2-y1));
        double green_l = ((c3.getGreen() - c1.getGreen())/(y3-y1));
        double green_r = ((c2.getGreen() - c1.getGreen())/(y2-y1));

        double xs = x1;
        double xe = x1;
        double rs = c1.getRed();
        double re = c1.getRed();
        double gs = c1.getGreen();
        double ge = c1.getGreen();
        double bs = c1.getBlue();
        double be = c1.getBlue();

        for(double y = y1; y < y3; y++){

            double s= (y - y1)/(y3 - y1);
            double z1 = one.getZ_deep();
            double z2 = two.getZ_deep();
            double z3 = three.getZ_deep();
            double zt = 0;
            double k = s;
            if(z1 != 0 && z3 != 0) zt = 1/z1 + s * (1/z3 - 1/ z1);
            if(zt != 0) zt = 1/zt;
            if(z1 != z3) k = (zt - z1)/(z3 - z1);

            double zl = zt;
            double ul = one.getTexture().getU() + k * (three.getTexture().getU()  - one.getTexture().getU());
            double vl = one.getTexture().getV() + k * (three.getTexture().getV()  - one.getTexture().getV());

            k = s;
            if(z1 != 0 && z2 != 0) zt = 1/z1 + s * (1/z2 - 1/ z1);
            if(zt != 0) zt = 1/zt;
            if(z1 != z2) k = (zt - z1)/(z2 - z1);

            double zr = zt;
            double ur = one.getTexture().getU() + k * (two.getTexture().getU()  - one.getTexture().getU());
            double vr = one.getTexture().getV() + k * (two.getTexture().getV()  - one.getTexture().getV());

            line(xs,xe,y,rs,gs,bs,re,ge,be,zl,zr,ul,ur,vl,vr,g2d);

            xs += xleft;
            xe += xright;
            rs += red_l;
            re += red_r;
            gs += green_l;
            ge += green_r;
            bs += blue_l;
            be += blue_r;
        }

    }

    public Color getColor(int c){
        int red =   (c>>16 & 0x0000ff);
        int green = (c>>8  & 0x0000ff);
        int blue =  (c     & 0x0000ff);

        Color color = new Color(red,green,blue);
        return color;
    }

    //平底三角形
    private void downTriangle(Vertex one, Vertex two, Vertex three, Graphics2D g2d) {
        //three 顶
        if(Math.abs(three.getPos().getY()- one.getPos().getY())<0.00001f){
            Vertex temp = two;
            two = three;
            three = temp;
        }else if(Math.abs(three.getPos().getY() - two.getPos().getY()) < 0.00001f){
            Vertex temp = three;
            three = one;
            one = temp;
        }else if(Math.abs(one.getPos().getY() - two.getPos().getY()) < 0.00001f){

        }else{
            return;
        }
        if(one.getPos().getX() > two.getPos().getX()){
            Vertex temp = two;
            two = one;
            one = temp;
        }

        double x1 = one.getPos().getX();
        double x2 = two.getPos().getX();
        double x3 = three.getPos().getX();
        double y1 = one.getPos().getY();
        double y2 = two.getPos().getY();
        double y3 = three.getPos().getY();

        Color c1,c2,c3;

            c1 = one.getColor();
            c2 = two.getColor();
            c3 = three.getColor();
        double xleft = (x3-x1)/(y3-y1);
        double xright = (x3-x2)/(y3-y2);
        double red_l = ((c3.getRed() - c1.getRed())/(y3-y1));
        double red_r = ((c3.getRed() - c2.getRed())/(y3-y2));
        double blue_l = ((c3.getBlue() - c1.getBlue())/(y3-y1));
        double blue_r = ((c3.getBlue() - c2.getBlue())/(y3-y2));
        double green_l = ((c3.getGreen() - c1.getGreen())/(y3-y1));
        double green_r = ((c3.getGreen() - c2.getGreen())/(y3-y2));

        double xs = x1;
        double xe = x2;
        double rs = c1.getRed();
        double re = c2.getRed();
        double gs = c1.getGreen();
        double ge = c2.getGreen();
        double bs = c1.getBlue();
        double be = c2.getBlue();

        for(double y = y1; y < y3; y++){

            double s= (y - y1)/(y3 - y1);
            double z1 = one.getZ_deep();
            double z2 = two.getZ_deep();
            double z3 = three.getZ_deep();
            double zt = 0;
            double k = s;
            if(z1 != 0 && z3 != 0) zt = 1/z1 + s * (1/z3 - 1/ z1);
            if(zt != 0) zt = 1/zt;
            if(z1 != z3) k = (zt - z1)/(z3 - z1);

            double zl = zt;
            double ul = one.getTexture().getU() + k * (three.getTexture().getU()  - one.getTexture().getU());
            double vl = one.getTexture().getV() + k * (three.getTexture().getV()  - one.getTexture().getV());

            k = s;
            if(z3 != 0 && z2 != 0) zt = 1/z2 + s * (1/z3 - 1/ z2);
            if(zt != 0) zt = 1/zt;
            if(z3 != z2) k = (zt - z2)/(z3 - z2);

            double zr = zt;
            double ur = two.getTexture().getU() + k * (three.getTexture().getU()  - two.getTexture().getU());
            double vr = two.getTexture().getV() + k * (three.getTexture().getV()  - two.getTexture().getV());

            line(xs,xe,y,rs,gs,bs,re,ge,be,zl,zr,ul,ur,vl,vr,g2d);

            xs += xleft;
            xe += xright;
            rs += red_l;
            re += red_r;
            gs += green_l;
            ge += green_r;
            bs += blue_l;
            be += blue_r;
        }
    }

    private void line(double xs, double xe, double y,double rs, double gs, double bs, double re, double ge, double be, double zl, double zr, double ul, double ur, double vl, double vr, Graphics2D g2d) {
        double deltaR = 0.0;
        double deltaG = 0.0;
        double deltaB = 0.0;

        if(Math.abs(xe - xs) > 0.0001f){
            deltaR = (re - rs) / (xe - xs);
            deltaG = (ge - gs) / (xe - xs);
            deltaB = (be - bs) / (xe - xs);
        }
        int textheight = Scene.getInstance().getTexture().getHeight();
        int textwidth = Scene.getInstance().getTexture().getWidth();
        double x = 0;
        for( x = xs;x < xe;x++){
            double fac_x = (x - xs)/(xe - xs);
            double new_z = 0.0;
            double fac_t = fac_x;
            if(zl != 0.0 && zr != 0.0) new_z = 1/zl + fac_x * (1/zr - 1/ zl);
            if(new_z != 0) new_z = 1 / new_z;
            if(zl != zr) fac_t = (new_z - zl)/(zr - zl);
            double u = ul + fac_t * (ur - ul);
            double v = vl + fac_t * (vr - vl);

//            double u = ul;
//            double v = vl;

            v = (v > 1.0) ? 1.0 : Math.max(v, 0.0);
            u = (u > 1.0) ? 1.0 : Math.max(u, 0.0);

            int c = 0;
            try {
                c = Scene.getInstance().getTexture().getRGB((int) ((textwidth-1) * u), (int) ((textheight - 1) * v));
            }catch (Exception e){
                System.out.println(e);
            }

            int red = (int)   (rs * (c>>16 & 0x0000ff) / 255) ;
            int green = (int)  (gs * (c>>8  & 0x0000ff) / 255);
            int blue = (int)   (bs * (c     & 0x0000ff) / 255);
//
            red  = red < 0? 0 : Math.min(red, 255);
            blue  = blue < 0? 0 : Math.min(blue, 255);
            green  = green < 0? 0 : Math.min(green, 255);

////            System.out.println(red + " " + blue + " " + green);
            Color color = new Color(red,green,blue);
            int xn  = x < 0? 0 : (int) Math.min(x, 999);
            y  = y < 0? 0 : Math.min(y, 799);
            if(Z_Buffer[xn][(int) y] > new_z){
                Z_Buffer[xn][(int) y] = new_z;
                Frame[xn][(int) y]= color;
            }

//            g2d.setColor(getColor(c));
//            g2d.drawLine((int) x, (int) y, (int) x, (int) y);
            rs += deltaR;
            bs += deltaB;
            gs += deltaG;
        }
    }

    public void dda(Vector2d v1, Vector2d v2, Graphics2D g, Color c1, Color c2){

        double increx, increy, x, y, length;
        length = Math.max(Math.abs(v2.getX()-v1.getX()),Math.abs(v2.getY()-v1.getY()));
        increx = ((v2.getX()- v1.getX())/length);
        increy = ((v2.getY() - v1.getY())/length);
        x = v1.getX();
        y = v1.getY();
        double dis = Math.sqrt(Math.pow(v2.getX() - v1.getX(),2) + Math.pow(v2.getY()-v1.getY(),2));
        int red, green, blue;
        for (int i = 0; i < length ;i++){

            double t = Math.sqrt(Math.pow(x - v1.getX(),2) + Math.pow(y-v1.getY(),2))/dis;
            if(c1.getRed() > c2.getRed()) {
                red = (int) (c1.getRed() - t * (Math.abs(c1.getRed() - c2.getRed())));
            }else{
                red = (int) (c1.getRed() + t * (Math.abs(c1.getRed() - c2.getRed())));
            }

            if(c1.getBlue() > c2.getBlue()) {
                blue = (int) (c1.getBlue() - t * (Math.abs(c1.getBlue() - c2.getBlue())));
            }else{
                blue = (int) (c1.getBlue() + t * (Math.abs(c1.getBlue() - c2.getBlue())));
            }

            if(c1.getGreen() > c2.getGreen()) {
                green = (int) (c1.getGreen() - t * (Math.abs(c1.getGreen() - c2.getGreen())));
            }else{
                green = (int) (c1.getGreen() + t * (Math.abs(c1.getGreen() - c2.getGreen())));
            }

            red  = red < 0? 0 : Math.min(red, 255);
            blue  = blue < 0? 0 : Math.min(blue, 255);
            green  = green < 0? 0 : Math.min(green, 255);

            g.setColor(new Color(red,blue,green));
            g.drawLine((int) ((int) x+0.5), (int) ((int)y+0.5), (int) ((int) x+0.5), (int) ((int)y+0.5));
            x += increx;
            y += increy;
        }
    }


    public void addtri(Triangle t){
        this.ltri.add(t);
    }

    public void setWorldmatrix(Matrix wpos){
        this.worldmatrix = wpos;
    }

    public Vector4d moveLeft(double step) {
        Vector4d pos = Scene.getInstance().getCamera().getPos();
        Vector4d n_right = new Vector4d(Scene.getInstance().getCamera().getRight());
        pos = pos.add(n_right.mul(step));
        return pos;
    }

    public Vector4d moveRight(double step) {
        Vector4d pos = Scene.getInstance().getCamera().getPos();
        Vector4d n_right = new Vector4d(Scene.getInstance().getCamera().getRight());

        pos = pos.minus(n_right.mul(step));
        return pos;
    }

    public Vector4d moveUp(double step) {
        Vector4d pos = Scene.getInstance().getCamera().getPos();
        Vector4d n_up = new Vector4d(Scene.getInstance().getCamera().getUp());
        pos = pos.add(n_up.mul(step));
        return pos;
    }

    public Vector4d moveDown(double step) {
        Vector4d pos = Scene.getInstance().getCamera().getPos();
        Vector4d n_up = new Vector4d(Scene.getInstance().getCamera().getUp());
        pos = pos.minus(n_up.mul(step));
        return pos;
    }

    public Vector4d forword(double step){
        Vector4d pos = Scene.getInstance().getCamera().getPos();
        Vector4d n_up = new Vector4d(Scene.getInstance().getCamera().getUp());
        Vector4d n_right = new Vector4d(Scene.getInstance().getCamera().getRight());
        pos = pos.add(n_right.crossMul(n_up).mul(step));
        return pos;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void setSlider(JSlider slider) {
        this.slider = slider;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_L || e.getKeyCode() == KeyEvent.VK_T || e.getKeyCode() == KeyEvent.VK_P){
            if (e.getKeyCode() == KeyEvent.VK_L) {
                this.setLINEture();
            }
            if(e.getKeyCode() == KeyEvent.VK_P) {
                changeLIGHT();
            }
            if (e.getKeyCode() == KeyEvent.VK_T) {
                this.setLINEfalse();
            }
        }else {

            Vector4d pos = null;
            if (e.getKeyCode() == KeyEvent.VK_A) {
                pos = moveRight(0.1);
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                pos = moveLeft(0.1);
            }

            if (e.getKeyCode() == KeyEvent.VK_W) {
                pos = moveUp(0.1);
            }
            if (e.getKeyCode() == KeyEvent.VK_S) {
                pos = moveDown(0.1);
            }
            if (e.getKeyCode() == KeyEvent.VK_Q) {
                pos = forword(0.1);
            }
            if (e.getKeyCode() == KeyEvent.VK_E) {
                pos = forword(-0.1);
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
        List<Obj> obj = Scene.getInstance().getObj();
        for (int i = 0; i < obj.size(); i++) {
            List<Triangle> ltri = obj.get(i).getLtri();
            double ka = slider.getValue() / 10.0;


            double kd = slider1.getValue() / 10.0;
            double ks = slider2.getValue() / 10.0;
            int n = slider3.getValue();
            Material new_mat = new Material(ka, kd, ks, n);
            System.out.println(ka+" " +
                                kd + " "
                                + ks + " "
                                + n);
            for (int i1 = 0; i1 < ltri.size(); i1++) {
                ltri.get(i1).point[0].setMaterial(new_mat);
                ltri.get(i1).point[1].setMaterial(new_mat);
                ltri.get(i1).point[2].setMaterial(new_mat);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == bl || source == bt || source == bli){
            if(source == bt){
                this.setLINEfalse();
            }
            if(source == bl){
                this.setLINEture();
            }
            if (source == bli) {
                changeLIGHT();
            }
        }else {
            Vector4d pos = null;
            if (source == bw) {
                pos = moveUp(0.1);
            }
            if (source == ba) {
                pos = moveRight(0.1);
            }
            if (source == bs) {
                pos = moveDown(0.1);
            }
            if (source == bd) {
                pos = moveLeft(0.1);
            }
            if (source == bq) {
                pos = forword(0.1);
            }
            if (source == be) {
                pos = forword(-0.1);
            }
            Vector4d n_up = new Vector4d(Scene.getInstance().getCamera().getUp());
            Vector4d n_right = new Vector4d(Scene.getInstance().getCamera().getRight());
            Matrix n_viewTransform = new Matrix().viewTrans(pos, n_up, n_right);
            Scene.getInstance().getCamera().setPos(pos);
            Scene.getInstance().getCamera().setViewTransform(n_viewTransform);
        }
    }
}
