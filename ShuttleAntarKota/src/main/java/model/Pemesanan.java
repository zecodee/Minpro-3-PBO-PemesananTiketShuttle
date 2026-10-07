/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author zefrialrizkullah
 */
public class Pemesanan {
    private final String idPemesanan;
    private final String idPenumpang;
    private final String idJadwal;
    private ArrayList<Tiket> daftarTiket;
    private Pembayaran pembayaran;
    private boolean sudahDibayar;

    public Pemesanan(String idPemesanan, String idPenumpang, String idJadwal) {
        this.idPemesanan = idPemesanan;
        this.idPenumpang = idPenumpang;
        this.idJadwal = idJadwal;
        this.daftarTiket = new ArrayList<>();
        this.pembayaran = null;
        this.sudahDibayar = false;
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

    public ArrayList<Tiket> getDaftarTiket() {
        return daftarTiket;
    }

    public int getJumlahTiket() {
        return daftarTiket.size();
    }

    public double getTotalHarga() {
        double total = 0;

        for (Tiket tiket : daftarTiket) {
            total += tiket.hitungHarga();
        }

        return total;
    }

    public void tambahTiket(Tiket tiket) {
        if (tiket == null) {
            throw new IllegalArgumentException("Tiket tidak boleh kosong.");
        }

        daftarTiket.add(tiket);
    }

    public void tampilkanData() {
        System.out.println("ID Pemesanan : " + idPemesanan);
        System.out.println("ID Penumpang : " + idPenumpang);
        System.out.println("ID Jadwal    : " + idJadwal);
        System.out.println("Jumlah Tiket : " + getJumlahTiket());
        System.out.println("Total Harga  : Rp. " + getTotalHarga());

        if (sudahDibayar) {
            System.out.println("Pembayaran   : " + pembayaran.getMetodePembayaran());
            System.out.println("Status       : LUNAS");
        } else {
            System.out.println("Pembayaran   : -");
            System.out.println("Status       : BELUM DIBAYAR");
        }
        System.out.println("Daftar Tiket:");

        for (Tiket tiket : daftarTiket) {
            System.out.println( "- " + tiket.getNomorTiket() + " | " + tiket.getJenisTiket() + " | Kursi " + tiket.getNomorKursi());
        }
    }
    
    public Pembayaran getPembayaran() {
        return pembayaran;
    }

    public boolean isSudahDibayar() {
        return sudahDibayar;
    }
    
    public void bayar(Pembayaran pembayaran) {
        if (pembayaran == null) {
            throw new IllegalArgumentException("Metode pembayaran tidak boleh kosong.");
        }

        if (daftarTiket.isEmpty()) {
            throw new IllegalArgumentException("Belum ada tiket yang dapat dibayar.");
        }

        if (sudahDibayar) {
            throw new IllegalArgumentException("Pemesanan sudah dibayar.");
        }

        boolean berhasil = pembayaran.prosesPembayaran(getTotalHarga());

        if (berhasil) {
            this.pembayaran = pembayaran;
            this.sudahDibayar = true;
        }
    }
}
