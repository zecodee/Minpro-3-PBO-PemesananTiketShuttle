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
public class JadwalShuttle {
    private final String idJadwal;
    private String kotaAsal;
    private String kotaTujuan;
    private String jamBerangkat;
    private double harga;
    private int kapasitasKursi;
    private ArrayList<Integer> kursiTerisi;

    public JadwalShuttle( String idJadwal, String kotaAsal, String kotaTujuan, String jamBerangkat, double harga, int kapasitasKursi) {
        this.idJadwal = idJadwal;
        setRute(kotaAsal, kotaTujuan);
        setJamBerangkat(jamBerangkat);
        setHarga(harga);
        setKapasitasKursi(kapasitasKursi);
        kursiTerisi = new ArrayList<>();
    }

    public String getIdJadwal() {
        return idJadwal;
    }

    public String getKotaAsal() {
        return kotaAsal;
    }

    public String getKotaTujuan() {
        return kotaTujuan;
    }

    public String getJamBerangkat() {
        return jamBerangkat;
    }

    public double getHarga() {
        return harga;
    }

    public int getKapasitasKursi() {
        return kapasitasKursi;
    }

    public ArrayList<Integer> getKursiTerisi() {
        return kursiTerisi;
    }

    public int getTiketTersedia() {
        return kapasitasKursi - kursiTerisi.size();
    }

    public void setRute(String kotaAsal, String kotaTujuan) {
        if (kotaAsal == null || kotaAsal.trim().isEmpty()
                || kotaTujuan == null || kotaTujuan.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Kota asal dan tujuan tidak boleh kosong.");
        }

        if (!kotaAsal.trim().matches("[a-zA-Z ]+")
                || !kotaTujuan.trim().matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException(
                    "Nama kota hanya boleh berisi huruf dan spasi.");
        }

        if (kotaAsal.trim().equalsIgnoreCase(kotaTujuan.trim())) {
            throw new IllegalArgumentException(
                    "Kota asal dan tujuan tidak boleh sama.");
        }

        this.kotaAsal = kotaAsal.trim();
        this.kotaTujuan = kotaTujuan.trim();
    }

    public void setJamBerangkat(String jamBerangkat) {
        if (jamBerangkat == null || !jamBerangkat.matches("([01][0-9]|2[0-3]):[0-5][0-9]")) {
            throw new IllegalArgumentException("Format jam harus HH:mm. Contoh: 08:30.");
        }

        this.jamBerangkat = jamBerangkat;
    }

    public void setHarga(double harga) {
        if (harga < 10000) {
            throw new IllegalArgumentException("Harga tiket minimal Rp10.000.");
        }

        this.harga = harga;
    }

    public void setKapasitasKursi(int kapasitasKursi) {
        if (kapasitasKursi < 1 || kapasitasKursi > 30) {
            throw new IllegalArgumentException("Kapasitas kursi harus antara 1-30.");
        }

        if (kursiTerisi != null) {
            for (int nomorKursi : kursiTerisi) {
                if (nomorKursi > kapasitasKursi) {
                    throw new IllegalArgumentException("Kapasitas tidak boleh lebih kecil " + "dari nomor kursi yang sudah terisi.");
                }
            }
        }

        this.kapasitasKursi = kapasitasKursi;
    }

    public boolean apakahKursiTersedia(int nomorKursi) {
        if (nomorKursi < 1 || nomorKursi > kapasitasKursi) {
            return false;
        }

        return !kursiTerisi.contains(nomorKursi);
    }

    public void isiKursi(int nomorKursi) {
        if (!apakahKursiTersedia(nomorKursi)) {
            throw new IllegalArgumentException("Nomor kursi tidak tersedia.");
        }

        kursiTerisi.add(nomorKursi);
    }

    public void kosongkanKursi(int nomorKursi) {
        kursiTerisi.remove(Integer.valueOf(nomorKursi));

    }

    public int cariKursiKosongPertama() {
        for (int i = 1; i <= kapasitasKursi; i++) {
            if (apakahKursiTersedia(i)) {
                return i;
            }
        }

        return -1;
    }

    public void tampilkanKursi() {
        System.out.println("\nKETERSEDIAAN KURSI");

        for (int i = 1; i <= kapasitasKursi; i++) {
            if (kursiTerisi.contains(i)) {
                System.out.print("[X] ");
            } else {
                System.out.print("[" + i + "] ");
            }

            if (i % 5 == 0) {
                System.out.println();
            }
        }

        System.out.println();
        System.out.println("X = Kursi sudah terisi");
    }

    public void tampilkanData() {
        System.out.println("ID Jadwal       : " + idJadwal);
        System.out.println("Rute            : " + kotaAsal + " -> " + kotaTujuan);
        System.out.println("Jam Berangkat   : " + jamBerangkat);
        System.out.println("Harga Dasar     : Rp. " + harga);
        System.out.println("Kapasitas Kursi : " + kapasitasKursi);
        System.out.println("Tiket Tersedia  : " + getTiketTersedia());
    }
}
