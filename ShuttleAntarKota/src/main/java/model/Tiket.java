/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author zefrialrizkullah
 */
public class Tiket {
    private final String nomorTiket;
    private final String idPemesanan;
    private final String idPenumpang;
    private final String idJadwal;
    private final int nomorKursi;
    private final double hargaDasar;

    public Tiket(String nomorTiket, String idPemesanan, String idPenumpang, String idJadwal, int nomorKursi, double hargaDasar) {
        this.nomorTiket = nomorTiket;
        this.idPemesanan = idPemesanan;
        this.idPenumpang = idPenumpang;
        this.idJadwal = idJadwal;
        this.nomorKursi = nomorKursi;
        this.hargaDasar = hargaDasar;
    }

    public String getNomorTiket() {
        return nomorTiket;
    }

    public String getIdPemesanan() {
        return idPemesanan;
    }

    public String getIdPenumpang() {
        return idPenumpang;
    }

    public String getIdJadwal() {
        return idJadwal;
    }

    public int getNomorKursi() {
        return nomorKursi;
    }

    public double getHargaDasar() {
        return hargaDasar;
    }

    public String getJenisTiket() {
        return "Tiket";
    }

    public double hitungHarga() {
        return hargaDasar;
    }

    public void tampilkanTiket() {
        System.out.println("Nomor Tiket : " + nomorTiket);
        System.out.println("Jenis Tiket : " + getJenisTiket());
        System.out.println("Nomor Kursi : " + nomorKursi);
        System.out.println("Harga       : Rp. " + hitungHarga());
    }
}
