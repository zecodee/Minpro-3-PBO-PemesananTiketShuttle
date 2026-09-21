/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import controller.ShuttleController;
import view.ShuttleView;

/**
 *
 * @author zefrialrizkullah
 */
public class Main {
    public static void main(String[] args) {
            ShuttleController controller = new ShuttleController();
            ShuttleView view = new ShuttleView(controller);
            view.jalankan();
        }
}
