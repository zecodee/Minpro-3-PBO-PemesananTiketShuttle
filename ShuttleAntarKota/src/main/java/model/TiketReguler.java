/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author zefrialrizkullah
 */
public class TiketReguler extends Tiket {
    public TiketReguler(String nomorTiket, String idPemesanan, String idPenumpang, String idJadwal, int nomorKursi, double hargaDasar) {
        super(nomorTiket, idPemesanan, idPenumpang, idJadwal, nomorKursi, hargaDasar);
    }

    @Override
    public String getJenisTiket() {
        return "Reguler";
    }

    @Override
    public void tampilkanTiket() {
        super.tampilkanTiket();
        System.out.println("Layanan     : Kursi dipilih otomatis");
    }
}
