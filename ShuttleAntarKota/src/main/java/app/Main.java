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

/*
    Catatan ini sebagai tanggapan dari konsep abgnya.
    Saya tidak menambahkan konsep inheritance baru pada penumpang karena menurut saya
    ini sudah cukup baik, sesuai dengan konsep yang telah saya pahamin ini dan selain itu juga
    tidak banyak waktu buat menambah fitur lebih lagi karna banyak tugas hehe, 
    btw terimakasih atas masukannya bang😁.
*/