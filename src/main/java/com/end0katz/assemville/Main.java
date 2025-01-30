package com.end0katz.assemville;

import javax.swing.*;
import java.awt.event.*;

public class Main {

    protected static JFrame win;
    protected static boolean active = true;

    public static void close() {
        System.out.println("close()");
        System.out.println(Main.active);
        Main.active = false;
        System.out.println(Main.active);
    }

    public static void loadmods() {
    }

    public static void startrender() {
        Main.win = new JFrame("Assemville");
        win.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //win.setUndecorated(true);
        win.setVisible(true);
        win.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Main.close();
                System.out.println("Blippity bloppity this window is now breathing manually");
            }
        });
    }

    public static void update() {
    }

    public static void render() {
    }

    public static void main(String[] args) {
        loadmods();
        startrender();
        System.out.println("appENTER");
        while (Main.active) {
            update();
            render();
        }
        win.dispose();
        System.out.println("appExit");
    }
}
