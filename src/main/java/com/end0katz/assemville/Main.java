package com.end0katz.assemville;

import javax.swing.*;

public class Main {

    protected static JFrame win;
    protected static boolean active = true;
    public static String state = "init";

    public static String file(String path) {
        return "src/main/" + path;
    }

    public static void close() {
        win.dispose();
        Main.active = false;
    }

    public static void loadmods() {
    }

    public static void startrender() {
        Main.win = new JFrame("Assemville");
        win.add(new JLabel(new ImageIcon(file("assets/grass.png"))));
        win.setLayout(null);
        win.setExtendedState(JFrame.MAXIMIZED_BOTH);
        win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        win.setVisible(true);
    }

    public static void update() {
    }

    public static void render() {
    }

    public static void main(String[] args) {
        loadmods();
        startrender();
        while (win.isDisplayable()) {
            update();
            render();
        }
        close();
    }
}
