/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author zefrialrizkullah
 */
public class PembayaranQRIS implements Pembayaran {
    @Override
    public boolean prosesPembayaran(double totalBayar) {
        System.out.println();
        System.out.println("PEMBAYARAN QRIS");
        System.out.println("Total Bayar : Rp" + totalBayar);
        System.out.println("Pembayaran QRIS berhasil.");
        return true;
    }

    @Override
    public String getMetodePembayaran() {
        return "QRIS";
    }
}
