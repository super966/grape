import Component.*;
import MathComponent.Matrix;
import MathComponent.Vector4d;
import MathComponent.Vertex;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Vertex v1 = new Vertex(new Vector4d(1,0,0,1));
        Vertex v2 = new Vertex(new Vector4d(0,1,0,1));
        Vertex v3 = new Vertex(new Vector4d(0,0,0,1));
        Vertex v4 = new Vertex(new Vector4d(1,1,0,1));
        Vertex v5 = new Vertex(new Vector4d(1,0,1,1));
        Vertex v6 = new Vertex(new Vector4d(0,1,1,1));
        Vertex v7 = new Vertex(new Vector4d(0,0,1,1));
        Vertex v8 = new Vertex(new Vector4d(1,1,1,1));
        v1.setNormal(new Vector4d(0,0,-1,0));
        v2.setNormal(new Vector4d(0,0,-1,0));
        v3.setNormal(new Vector4d(0,0,-1,0));
        v4.setNormal(new Vector4d(0,0,-1,0));
        v1.setMaterial(new Material(0.2,0.4,0.6,2));
        v2.setMaterial(new Material(0.2,0.4,0.6,2));
        v3.setMaterial(new Material(0.2,0.4,0.6,2));
        v4.setMaterial(new Material(0.2,0.4,0.6,2));
        v5.setMaterial(new Material(0.2,0.4,0.6,2));
        v6.setMaterial(new Material(0.2,0.4,0.6,2));
        v7.setMaterial(new Material(0.2,0.4,0.6,2));
        v8.setMaterial(new Material(0.2,0.4,0.6,2));

        v1.setColor(Color.red);
        v2.setColor(Color.green);
        v3.setColor(Color.blue);
        v4.setColor(Color.red);
        v5.setColor(Color.green);
        v6.setColor(Color.blue);
        v7.setColor(Color.red);
        v8.setColor(Color.green);

        v1.setTexture(new TextureCoord(1,0));
        v2.setTexture(new TextureCoord(0, 1));
        v3.setTexture(new TextureCoord(0,0));
        v4.setTexture(new TextureCoord(1,1 ));

        Vertex[] points = new Vertex[3];
        points[0] = v1;
        points[1] = v2;
        points[2] = v3;
        Obj obj = new Obj();
        obj.addtri(
                new Triangle(points)
        );
        points[0] = v1;
        points[1] = v4;
        points[2] = v2;
        obj.addtri(
                new Triangle(points)
        );
        //正方体顶
        v2.setNormal(new Vector4d(0,1,0,0));
        v4.setNormal(new Vector4d(0,1,0,0));
        v6.setNormal(new Vector4d(0,1,0,0));
        v8.setNormal(new Vector4d(0,1,0,0));
        v2.setTexture(new TextureCoord(0,0));
        v4.setTexture(new TextureCoord(1, 0));
        v6.setTexture(new TextureCoord(0,1));
        v8.setTexture(new TextureCoord(1,1 ));
        points[0] = v4;
        points[1] = v6;
        points[2] = v2;
        obj.addtri(
                new Triangle(points)
        );
        points[0] = v4;
        points[1] = v8;
        points[2] = v6;
        obj.addtri(
                new Triangle(points)
        );
//
//        //正方体右侧
        v1.setNormal(new Vector4d(1,0,0,0));
        v4.setNormal(new Vector4d(1,0,0,0));
        v5.setNormal(new Vector4d(1,0,0,0));
        v8.setNormal(new Vector4d(1,0,0,0));
        v1.setTexture(new TextureCoord(0,0));
        v4.setTexture(new TextureCoord(0, 1));
        v5.setTexture(new TextureCoord(1,0));
        v8.setTexture(new TextureCoord(1,1 ));
        points[0] = v5;
        points[1] = v4;
        points[2] = v1;
        obj.addtri(
                new Triangle(points)
        );
        points[0] = v5;
        points[1] = v8;
        points[2] = v4;
        obj.addtri(
                new Triangle(points)
        );

        //正方体底面
        v1.setNormal(new Vector4d(0,-1,0,0));
        v3.setNormal(new Vector4d(0,-1,0,0));
        v5.setNormal(new Vector4d(0,-1,0,0));
        v7.setNormal(new Vector4d(0,-1,0,0));
        v1.setTexture(new TextureCoord(1,1));
        v3.setTexture(new TextureCoord(0, 1));
        v5.setTexture(new TextureCoord(1,0));
        v7.setTexture(new TextureCoord(0,0 ));
        points[0] = v5;
        points[1] = v3;
        points[2] = v7;
        obj.addtri(
                new Triangle(points)
        );
        points[0] = v5;
        points[1] = v1;
        points[2] = v3;
        obj.addtri(
                new Triangle(points)
        );
//
//        //左侧
        v2.setNormal(new Vector4d(-1,0,0,0));
        v3.setNormal(new Vector4d(-1,0,0,0));
        v6.setNormal(new Vector4d(-1,0,0,0));
        v7.setNormal(new Vector4d(-1,0,0,0));
        v2.setTexture(new TextureCoord(1,1));
        v3.setTexture(new TextureCoord(1, 0));
        v6.setTexture(new TextureCoord(0,1));
        v7.setTexture(new TextureCoord(0,0 ));
        points[0] = v3;
        points[1] = v6;
        points[2] = v7;
        obj.addtri(
                new Triangle(points)
        );
        points[0] = v3;
        points[1] = v2;
        points[2] = v6;
        obj.addtri(
                new Triangle(points)
        );
//
//        //后侧
        v5.setNormal(new Vector4d(0,0,1,0));
        v6.setNormal(new Vector4d(0,0,1,0));
        v7.setNormal(new Vector4d(0,0,1,0));
        v8.setNormal(new Vector4d(0,0,1,0));
        v5.setTexture(new TextureCoord(0,0));
        v6.setTexture(new TextureCoord(1, 1));
        v7.setTexture(new TextureCoord(1,0));
        v8.setTexture(new TextureCoord(0,1 ));
        points[0] = v7;
        points[1] = v8;
        points[2] = v5;
        obj.addtri(
                new Triangle(points)
        );
        points[0] = v7;
        points[1] = v6;
        points[2] = v8;
        obj.addtri(
                new Triangle(points)
        );

        Vector4d campos = new Vector4d(0,0,-3,1);
        Vector4d camright = new Vector4d(1,0,0,1);
        Vector4d camup = new Vector4d(0,1,0,1);

        Camera cam = new Camera(campos,camup,camright,60,1,0,5);

        AmbtLight ambtLight = new AmbtLight(255,244,214);
        PointLight pointLight = new PointLight(255,255,255, new Vector4d(-3,-3,2,0));

        ViewPoint viewPoint = new ViewPoint(0,0,1500,850,0,2);

        Vector4d objPos = new Vector4d(-2,-2,2,1);
        Vector4d scale = new Vector4d(1,1,1,1);
        double xdeg = 0;
        double ydeg = 0;
        double zdeg = 0;

        Matrix worldMatrix = new Matrix().worldTransform(objPos,scale,xdeg,ydeg,zdeg);
        obj.setWorldmatrix(worldMatrix);
        obj.setBounds(0, 70, 1000, 800);

        Scene scene = Scene.getInstance();
        scene.setCamera(cam);
        scene.setAmbtLight(ambtLight);
        scene.setPointLight(pointLight);
        scene.setViewPoint(viewPoint);
        scene.addObj(obj);
        File img = new File("src/box.bmp");
        BufferedImage bi = ImageIO.read(img);
        scene.setTexture(bi);

//        Bg frame = new Bg("Scene");
//        frame.setObj(obj);



        JFrame frame = new JFrame("Scene");
        JButton bli = new JButton("光照模式转换");
        JButton bw = new JButton("w");
        JButton bs = new JButton("s");
        JButton ba = new JButton("a");
        JButton bd = new JButton("d");
        JButton bq = new JButton("q");
        JButton be = new JButton("e");
        JButton bl = new JButton("线框模式");
        JButton bt = new JButton("纹理模式");
        obj.setBli(bli);
        bli.addActionListener(obj);
        bli.setBounds(550,0,120,30);
        obj.setBw(bw);
        bw.addActionListener(obj);
        bw.setBounds(400,0,50,30);
        obj.setBs(bs);
        bs.addActionListener(obj);
        bs.setBounds(400,30,50,30);
        obj.setBa(ba);
        ba.addActionListener(obj);
        ba.setBounds(350,30,50,30);
        obj.setBd(bd);
        bd.addActionListener(obj);
        bd.setBounds(450,30,50,30);
        obj.setBq(bq);
        bq.addActionListener(obj);
        bq.setBounds(350,0,50,30);
        obj.setBe(be);
        be.addActionListener(obj);
        be.setBounds(450,0,50,30);
        obj.setBl(bl);
        bl.addActionListener(obj);
        bl.setBounds(550,30,120,30);
        obj.setBt(bt);
        bt.addActionListener(obj);
        bt.setBounds(700,30,120,30);

//
        JSlider slider = new JSlider(0, 10,2);
        slider.setBounds(18,10,100,30);
//        slider.setMajorTickSpacing(5);
//        slider.setMinorTickSpacing(1);
//        slider.setPaintTicks(true);
//        slider.setPaintLabels(true);
        slider.addChangeListener(obj);
        obj.setSlider(slider);

        JSlider slider1 = new JSlider(0, 10,4);
        slider1.setBounds(18,40,100,30);
//        slider1.setMajorTickSpacing(5);
//        slider1.setMinorTickSpacing(1);
//        slider1.setPaintTicks(true);
//        slider1.setPaintLabels(true);
        slider1.addChangeListener(obj);
        obj.setSlider1(slider1);

        JSlider slider2 = new JSlider(0, 10,6);
        slider2.setBounds(133,10,100,30);
//        slider2.setMajorTickSpacing(5);
//        slider2.setMinorTickSpacing(1);
//        slider2.setPaintTicks(true);
//        slider2.setPaintLabels(true);
        slider2.addChangeListener(obj);
        obj.setSlider2(slider2);

        JSlider slider3 = new JSlider(0, 10,2);
        slider3.setBounds(133,40,100,30);
//        slider3.setMajorTickSpacing(5);
//        slider3.setMinorTickSpacing(1);
//        slider3.setPaintTicks(true);
//        slider3.setPaintLabels(true);
        slider3.addChangeListener(obj);
        obj.setSlider3(slider3);
//        frame.setSlider(slider);
//        frame.setButton(b1);
//        frame.init();


        JLabel Ka =new JLabel("Ka");
        Ka.setBounds(5,15,20,20);
        JLabel Kd =new JLabel("Kd");
        Kd.setBounds(5,45,20,20);

        JLabel Ks =new JLabel("Ks");
        Ks.setBounds(118,15,20,20);

        JLabel n =new JLabel("n");
        n.setBounds(118,45,20,20);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0,0,300,100);
        panel.add(Ka);
        panel.add(slider);
        panel.add(Kd);
        panel.add(slider1);
        panel.add(Ks);
        panel.add(slider2);
        panel.add(n);
        panel.add(slider3);

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(550,0,120,100);
        panel1.add(bli);
        panel1.add(bw);
        panel1.add(ba);
        panel1.add(bs);
        panel1.add(bd);
        panel1.add(be);
        panel1.add(bq);
        panel1.add(bt);
        panel1.add(bl);

        JPanel panel2 = new JPanel();
        panel1.setLayout(null);
        panel1.setBounds(0,0,1220,890);
        panel1.add(obj);
//
//
        frame.add(panel);
        frame.add(panel1);
        frame.setSize(1000, 900);
        frame.addKeyListener(obj);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }

}
