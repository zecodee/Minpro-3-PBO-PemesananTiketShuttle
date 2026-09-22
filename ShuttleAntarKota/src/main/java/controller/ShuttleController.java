/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import model.Penumpang;
import model.JadwalShuttle;
import model.Pemesanan;
import model.Tiket;
import model.TiketReguler;
import model.TiketPrioritas;

/**
 *
 * @author zefrialrizkullah
 */
public class ShuttleController {
    private ArrayList<Penumpang> daftarPenumpang;
    private ArrayList<JadwalShuttle> daftarJadwal;
    private ArrayList<Pemesanan> daftarPemesanan;
    private int counterPenumpang = 1;
    private int counterJadwal = 1;
    private int counterPemesanan = 1;
    private int counterTiket = 1;
    private static final double BIAYA_PRIORITAS = 25000;

    public ShuttleController() {
        daftarPenumpang = new ArrayList<>();
        daftarJadwal = new ArrayList<>();
        daftarPemesanan = new ArrayList<>();
        isiDummyData();
    }

    private String buatIdPenumpang() {
        return String.format("P%03d", counterPenumpang++);
    }

    private String buatIdJadwal() {
        return String.format("J%03d", counterJadwal++);
    }

    private String buatIdPemesanan() {
        return String.format("PS%03d", counterPemesanan++);
    }

    private String buatNomorTiket() {
        return String.format("TKT%04d", counterTiket++);
    }

    private void isiDummyData() {
        Penumpang penumpang = new Penumpang(buatIdPenumpang(), "Zeyya Alvyoza", "081316120091");
        daftarPenumpang.add(penumpang);
        JadwalShuttle jadwal = new JadwalShuttle(buatIdJadwal(), "Samarinda", "Balikpapan", "08:00", 150000, 10);
        daftarJadwal.add(jadwal);
    }

    public ArrayList<Penumpang> getDaftarPenumpang() {
        return daftarPenumpang;
    }

    public ArrayList<JadwalShuttle> getDaftarJadwal() {
        return daftarJadwal;
    }

    public ArrayList<Pemesanan> getDaftarPemesanan() {
        return daftarPemesanan;
    }

    public void tambahPenumpang(String nama, String noHp) {
        Penumpang penumpang = new Penumpang(buatIdPenumpang(), nama, noHp);
        daftarPenumpang.add(penumpang);
        System.out.println("Penumpang berhasil ditambahkan.");
        System.out.println("ID Penumpang: " + penumpang.getIdPenumpang());
    }

    public void tampilkanPenumpang() {
        if (daftarPenumpang.isEmpty()) {
            System.out.println("Data penumpang masih kosong.");
            return;
        }

        for (Penumpang penumpang : daftarPenumpang) {
            System.out.println("\n--------------------------");
            penumpang.tampilkanData();
            System.out.println("--------------------------");
        }
    }

    public Penumpang cariPenumpang(String id) {
        for (Penumpang penumpang : daftarPenumpang) {
            if (penumpang.getIdPenumpang().equalsIgnoreCase(id)) {
                return penumpang;
            }
        }

        return null;
    }

    public void ubahPenumpang(String id, String nama, String noHp) {
        Penumpang penumpang = cariPenumpang(id);

        if (penumpang == null) {
            System.out.println("Penumpang tidak ditemukan.");
            return;
        }

        penumpang.setNama(nama);
        penumpang.setNoHp(noHp);
        System.out.println("Data penumpang berhasil diubah.");
    }

    public boolean penumpangMemilikiPemesanan(String idPenumpang) {
        for (Pemesanan pemesanan : daftarPemesanan) {
            if (pemesanan.getIdPenumpang().equalsIgnoreCase(idPenumpang)) {
                return true;
            }
        }

        return false;
    }

    public void hapusPenumpang(String id) {
        Penumpang penumpang = cariPenumpang(id);

        if (penumpang == null) {
            System.out.println("Penumpang tidak ditemukan.");
            return;
        }

        if (penumpangMemilikiPemesanan(id)) {
            System.out.println("Penumpang tidak dapat dihapus.");
            System.out.println("Batalkan pemesanan terlebih dahulu.");
            return;
        }

        daftarPenumpang.remove(penumpang);
        System.out.println("Penumpang berhasil dihapus.");
    }

    public void tambahJadwal(String asal, String tujuan, String jam, double harga, int kapasitas) {
        JadwalShuttle jadwal = new JadwalShuttle(buatIdJadwal(), asal, tujuan, jam, harga, kapasitas);
        daftarJadwal.add(jadwal);
        System.out.println("Jadwal berhasil ditambahkan.");
        System.out.println("ID Jadwal: " + jadwal.getIdJadwal());
    }

    public void tampilkanJadwal() {
        if (daftarJadwal.isEmpty()) {
            System.out.println("Data jadwal masih kosong.");
            return;
        }

        for (JadwalShuttle jadwal : daftarJadwal) {
            System.out.println("\n--------------------------");
            jadwal.tampilkanData();
            System.out.println("--------------------------");
        }
    }

    public JadwalShuttle cariJadwal(String id) {
        for (JadwalShuttle jadwal : daftarJadwal) {

            if (jadwal.getIdJadwal().equalsIgnoreCase(id)) {
                return jadwal;
            }
        }
        return null;
    }

    public boolean jadwalMemilikiPemesanan(String idJadwal) {
        for (Pemesanan pemesanan : daftarPemesanan) {
            if (pemesanan.getIdJadwal().equalsIgnoreCase(idJadwal)) {
                return true;
            }
        }
        return false;
    }

    public void ubahJadwal(String id, String asal, String tujuan, String jam, double harga, int kapasitas) {
        JadwalShuttle jadwal = cariJadwal(id);

        if (jadwal == null) {
            System.out.println("Jadwal tidak ditemukan.");
            return;
        }

        if (kapasitas < jadwal.getKursiTerisi().size()) {
            System.out.println("Kapasitas tidak boleh kurang " + "dari jumlah kursi yang sudah terisi.");
            return;
        }

        jadwal.setRute(asal, tujuan);
        jadwal.setJamBerangkat(jam);
        jadwal.setHarga(harga);
        jadwal.setKapasitasKursi(kapasitas);
        System.out.println("Jadwal berhasil diubah.");
    }

    public void hapusJadwal(String id) {
        JadwalShuttle jadwal = cariJadwal(id);

        if (jadwal == null) {
            System.out.println("Jadwal tidak ditemukan.");
            return;
        }

        if (jadwalMemilikiPemesanan(id)) {
            System.out.println("Jadwal tidak dapat dihapus.");
            System.out.println("Masih terdapat pemesanan " + "pada jadwal tersebut.");
            return;
        }

        daftarJadwal.remove(jadwal);
        System.out.println("Jadwal berhasil dihapus.");
    }

    public Pemesanan buatPemesanan(Penumpang penumpang, JadwalShuttle jadwal) {
        Pemesanan pemesanan = new Pemesanan(buatIdPemesanan(), penumpang.getIdPenumpang(), jadwal.getIdJadwal());
        return pemesanan;
    }

    public Tiket buatTiketReguler(Pemesanan pemesanan, JadwalShuttle jadwal) {
        int nomorKursi = jadwal.cariKursiKosongPertama();

        if (nomorKursi == -1) {
            throw new IllegalArgumentException("Tidak ada kursi tersedia.");
        }

        Tiket tiket = new TiketReguler(buatNomorTiket(), pemesanan.getIdPemesanan(), 
                pemesanan.getIdPenumpang(), pemesanan.getIdJadwal(), nomorKursi, jadwal.getHarga());

        jadwal.isiKursi(nomorKursi);
        pemesanan.tambahTiket(tiket);
        return tiket;
    }

    public Tiket buatTiketPrioritas(Pemesanan pemesanan, JadwalShuttle jadwal, int nomorKursi) {
        if (!jadwal.apakahKursiTersedia(nomorKursi)) {
            throw new IllegalArgumentException("Kursi tidak tersedia.");
        }

        Tiket tiket = new TiketPrioritas(buatNomorTiket(), pemesanan.getIdPemesanan(), 
                pemesanan.getIdPenumpang(), pemesanan.getIdJadwal(), nomorKursi, jadwal.getHarga(), BIAYA_PRIORITAS);

        jadwal.isiKursi(nomorKursi);
        pemesanan.tambahTiket(tiket);
        return tiket;
    }

    public void simpanPemesanan(Pemesanan pemesanan) {
        if (pemesanan.getJumlahTiket() == 0) {
            throw new IllegalArgumentException("Pemesanan minimal memiliki 1 tiket.");
        }

        daftarPemesanan.add(pemesanan);
    }

    public Pemesanan cariPemesanan(String id) {
        for (Pemesanan pemesanan : daftarPemesanan) {
            if (pemesanan.getIdPemesanan().equalsIgnoreCase(id)) {
                return pemesanan;
            }
        }
        return null;
    }

    public void tampilkanPemesanan() {
        if (daftarPemesanan.isEmpty()) {
            System.out.println("Data pemesanan masih kosong.");
            return;
        }

        for (Pemesanan pemesanan : daftarPemesanan) {
            System.out.println("\n--------------------------");
            pemesanan.tampilkanData();
            System.out.println("--------------------------");
        }
    }

    public void hapusPemesanan(String id) {
        Pemesanan pemesanan = cariPemesanan(id);

        if (pemesanan == null) {
            System.out.println("Pemesanan tidak ditemukan.");
            return;
        }

        JadwalShuttle jadwal = cariJadwal(pemesanan.getIdJadwal());

        if (jadwal != null) {
            for (Tiket tiket : pemesanan.getDaftarTiket()) {
                jadwal.kosongkanKursi(tiket.getNomorKursi());
            }
        }

        daftarPemesanan.remove(pemesanan);
        System.out.println("Pemesanan berhasil dibatalkan.");
        System.out.println("Kursi kembali tersedia.");
    }

    public Tiket cariTiket(String nomorTiket) {
        for (Pemesanan pemesanan : daftarPemesanan) {
            for (Tiket tiket : pemesanan.getDaftarTiket()) {
                if (tiket.getNomorTiket().equalsIgnoreCase(nomorTiket)) {
                    return tiket;
                }
            }
        }
        return null;
    }

    public int getTotalTiketTerjual() {
        int total = 0;

        for (Pemesanan pemesanan : daftarPemesanan) {
            total += pemesanan.getJumlahTiket();
        }
        return total;
    }
    
    public int getTotalTiketReguler() {
        int total = 0;

        for (Pemesanan pemesanan : daftarPemesanan) {
            for (Tiket tiket : pemesanan.getDaftarTiket()) {
                if (tiket.getJenisTiket().equalsIgnoreCase("Reguler")) {
                    total++;
                }
            }
        }

        return total;
    }
    
    public int getTotalTiketPrioritas() {
        int total = 0;

        for (Pemesanan pemesanan : daftarPemesanan) {
            for (Tiket tiket : pemesanan.getDaftarTiket()) {
                if (tiket.getJenisTiket().equalsIgnoreCase("Prioritas")) {
                    total++;
                }
            }
        }

        return total;
    }
    
    public double getPendapatanReguler() {
        double total = 0;

        for (Pemesanan pemesanan : daftarPemesanan) {
            for (Tiket tiket : pemesanan.getDaftarTiket()) {
                if (tiket.getJenisTiket().equalsIgnoreCase("Reguler")) {
                    total += tiket.hitungHarga();
                }
            }
        }

        return total;
    }
    
    public double getPendapatanPrioritas() {
        double total = 0;

        for (Pemesanan pemesanan : daftarPemesanan) {
            for (Tiket tiket : pemesanan.getDaftarTiket()) {
                if (tiket.getJenisTiket().equalsIgnoreCase("Prioritas")) {
                    total += tiket.hitungHarga();
                }
            }
        }

        return total;
    }
    
    public double getTotalPendapatan() {
        double total = 0;

        for (Pemesanan pemesanan : daftarPemesanan) {
            total += pemesanan.getTotalHarga();
        }

        return total;
    }
}
