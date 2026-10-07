/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author zefrialrizkullah
 */
public class PembayaranTunai implements Pembayaran {
    @Override
    public boolean prosesPembayaran(double totalBayar) {
        System.out.println();
        System.out.println("PEMBAYARAN TUNAI");
        System.out.println("Total Bayar : Rp" + totalBayar);
        System.out.println("Pembayaran tunai berhasil.");
        return true;
    }

    @Override
    public String getMetodePembayaran() {
        return "Tunai";
    }
}
