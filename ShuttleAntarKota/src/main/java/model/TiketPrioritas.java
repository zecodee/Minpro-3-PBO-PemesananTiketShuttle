/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author zefrialrizkullah
 */
public class TiketPrioritas extends Tiket {
    private final double biayaPrioritas;

    public TiketPrioritas(String nomorTiket, String idPemesanan, String idPenumpang, String idJadwal, int nomorKursi, double hargaDasar, double biayaPrioritas) {
        super(nomorTiket, idPemesanan, idPenumpang, idJadwal, nomorKursi, hargaDasar);

        if (biayaPrioritas < 0) {
            throw new IllegalArgumentException("Biaya prioritas tidak valid.");
        }

        this.biayaPrioritas = biayaPrioritas;
    }

    public double getBiayaPrioritas() {
        return biayaPrioritas;
    }

    @Override
    public String getJenisTiket() {
        return "Prioritas";
    }

    @Override
    public double hitungHarga() {
        return getHargaDasar() + biayaPrioritas;
    }

    @Override
    public void tampilkanTiket() {
        super.tampilkanTiket();
        System.out.println("Layanan     : Bebas memilih kursi");
        System.out.println("Biaya       : Rp. " + biayaPrioritas);
    }
}
