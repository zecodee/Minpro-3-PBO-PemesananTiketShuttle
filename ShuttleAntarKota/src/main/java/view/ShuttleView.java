/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.util.Scanner;
import controller.ShuttleController;
import model.Penumpang;
import model.JadwalShuttle;
import model.Pemesanan;
import model.Tiket;
import model.Pembayaran;
import model.PembayaranTunai;
import model.PembayaranQRIS;

/**
 *
 * @author zefrialrizkullah
 */
public class ShuttleView {
    private Scanner input;
    private ShuttleController controller;

    public ShuttleView(ShuttleController controller) {
        this.controller = controller;
        input = new Scanner(System.in);
    }

    public void jalankan() {
        int pilihan;

        splashScreen();
        
        do {
            System.out.println("==================================");
            System.out.println(" SISTEM SHUTTLE ANTAR KOTA PLATKT ");
            System.out.println("==================================");
            System.out.println("1. Kelola Penumpang");
            System.out.println("2. Kelola Jadwal");
            System.out.println("3. Kelola Pemesanan");
            System.out.println("4. Cari Tiket");
            System.out.println("5. Ringkasan Sistem");
            System.out.println("0. Keluar");
            pilihan = inputInt("Pilih menu: ", 0, 5);

            switch (pilihan) {
                case 1:
                    menuPenumpang();
                    break;

                case 2:
                    menuJadwal();
                    break;

                case 3:
                    menuPemesanan();
                    break;

                case 4:
                    cariTiket();
                    break;

                case 5:
                    tampilkanRingkasan();
                    break;

                case 0:
                    System.out.println("Anda keluar dari aplikasi.");
                    break;
            }
        } while (pilihan != 0);
        
        input.close();
    }
    
    private void splashScreen() {
        System.out.println("==========================================");
        System.out.println("              SHUTTLE APPS                ");
        System.out.println("==========================================");
        System.out.println();
        System.out.println("     SISTEM SHUTTLE ANTAR KOTA PLATKT     ");
        System.out.println();
        jeda(700);
        System.out.println("           Starting System.....           ");
        System.out.println();

        try {
            System.out.print("        [");

            for (int i = 0; i < 20; i++) {
                System.out.print("#");
                Thread.sleep(80);
            }

            System.out.println("] 100%");
        }
        
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println();
        ketik("           System Ready!");
        jeda(700);
        bersihkanLayar();
    }
    
    private void jeda(int waktu) {
        try {
            Thread.sleep(waktu);

        }
        
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private void ketik(String teks) {
        try {
            for (int i = 0; i < teks.length(); i++) {
                System.out.print(teks.charAt(i));
                Thread.sleep(35);
            }
            
            System.out.println();
        } 
        
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private void bersihkanLayar() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }

    private void menuPenumpang() {
        int pilihan;

        do {
            System.out.println();
            System.out.println("MENU PENUMPANG");
            System.out.println("1. Tambah");
            System.out.println("2. Tampilkan");
            System.out.println("3. Ubah");
            System.out.println("4. Hapus");
            System.out.println("0. Kembali");
            pilihan = inputInt("Pilihan: ", 0, 4);

            try {
                switch (pilihan) {
                    case 1:
                        tambahPenumpang();
                        break;

                    case 2:
                        controller.tampilkanPenumpang();
                        break;

                    case 3:
                        ubahPenumpang();
                        break;

                    case 4:
                        hapusPenumpang();
                        break;
                }
            } 
            
            catch (IllegalArgumentException e) {
                System.out.println("Gagal: " + e.getMessage());
            }

        } while (pilihan != 0);
    }

    private void tambahPenumpang() {
        System.out.println("\nTAMBAH PENUMPANG");
        String nama = inputNama("Nama  : ");
        String noHp = inputNoHp("No HP : ");
        loading("Menyimpan data");
        controller.tambahPenumpang(nama, noHp);
    }

    private void ubahPenumpang() {
        controller.tampilkanPenumpang();
        String id = inputTeks("ID Penumpang yang diubah: ");
        Penumpang penumpang = controller.cariPenumpang(id);

        if (penumpang == null) {
            System.out.println("Penumpang tidak ditemukan.");
            return;
        }

        String nama = inputNama("Nama baru  : ");
        String noHp = inputNoHp("No HP baru : ");
        controller.ubahPenumpang(id, nama, noHp);
    }

    private void hapusPenumpang() {
        controller.tampilkanPenumpang();
        String id = inputTeks("ID Penumpang yang dihapus: ");
        controller.hapusPenumpang(id);
    }

    private void menuJadwal() {
        int pilihan;

        do {
            System.out.println();
            System.out.println("MENU JADWAL");
            System.out.println("1. Tambah");
            System.out.println("2. Tampilkan");
            System.out.println("3. Ubah");
            System.out.println("4. Hapus");
            System.out.println("5. Lihat Kursi");
            System.out.println("0. Kembali");
            pilihan = inputInt("Pilihan: ", 0, 5);

            try {
                switch (pilihan) {
                    case 1:
                        tambahJadwal();
                        break;

                    case 2:
                        controller.tampilkanJadwal();
                        break;

                    case 3:
                        ubahJadwal();
                        break;

                    case 4:
                        hapusJadwal();
                        break;

                    case 5:
                        lihatKursi();
                        break;
                }

            } 
            
            catch (IllegalArgumentException e) {
                System.out.println("Gagal: " + e.getMessage());
            }
        } while (pilihan != 0);
    }

    private void tambahJadwal() {
        System.out.println("\nTAMBAH JADWAL");
        String asal = inputNama("Kota Asal      : ");
        String tujuan = inputNama("Kota Tujuan    : ");
        String jam = inputJam("Jam (HH:mm)    : ");
        double harga = inputDouble("Harga          : ", 10000);
        int kapasitas = inputInt("Kapasitas      : ", 1, 30);
        loading("Menyimpan jadwal");
        controller.tambahJadwal(asal, tujuan, jam, harga, kapasitas);
    }

    private void ubahJadwal() {
        controller.tampilkanJadwal();
        String id = inputTeks("ID Jadwal yang diubah: ");
        JadwalShuttle jadwal = controller.cariJadwal(id);

        if (jadwal == null) {
            System.out.println("Jadwal tidak ditemukan.");
            return;
        }
        String asal = inputNama("Kota Asal baru   : ");
        String tujuan = inputNama("Kota Tujuan baru : ");
        String jam = inputJam("Jam baru         : ");
        double harga = inputDouble("Harga baru       : ", 10000);
        int kapasitas = inputInt("Kapasitas baru   : ", 1, 30);
        controller.ubahJadwal(id, asal, tujuan, jam, harga, kapasitas);
    }

    private void hapusJadwal() {
        controller.tampilkanJadwal();
        String id = inputTeks("ID Jadwal yang dihapus: ");
        controller.hapusJadwal(id);
    }

    private void lihatKursi() {
        controller.tampilkanJadwal();
        String id = inputTeks("ID Jadwal: ");

        JadwalShuttle jadwal = controller.cariJadwal(id);

        if (jadwal == null) {
            System.out.println("Jadwal tidak ditemukan.");
            return;
        }
        jadwal.tampilkanKursi();
    }

    private void menuPemesanan() {
        int pilihan;

        do {
            System.out.println();
            System.out.println("MENU PEMESANAN");
            System.out.println("1. Buat Pemesanan");
            System.out.println("2. Tampilkan Pemesanan");
            System.out.println("3. Bayar Pemesanan");
            System.out.println("4. Batalkan Pemesanan");
            System.out.println("0. Kembali");
            pilihan = inputInt("Pilihan: ", 0, 4);

            try {
                switch (pilihan) {
                    case 1:
                        buatPemesanan();
                        break;

                    case 2:
                        controller.tampilkanPemesanan();
                        break;

                    case 3:
                        bayarPemesanan();
                        break;

                    case 4:
                        batalkanPemesanan();
                        break;
                }
            } 
            
            catch (IllegalArgumentException e) {
                System.out.println("Gagal: " + e.getMessage());
            }
        } 
        while (pilihan != 0);
    }

    private void buatPemesanan() {
        if (controller.getDaftarPenumpang().isEmpty()) {
            System.out.println("Belum ada data penumpang.");
            return;
        }

        if (controller.getDaftarJadwal().isEmpty()) {
            System.out.println("Belum ada jadwal.");
            return;
        }

        System.out.println("\nPILIH PENUMPANG");

        for (int i = 0; i < controller.getDaftarPenumpang().size(); i++) {
            Penumpang p = controller.getDaftarPenumpang().get(i);
            System.out.println((i + 1) + ". " + p.getNama());
        }
        
        int pilihPenumpang = inputInt("Pilih penumpang: ", 1, controller.getDaftarPenumpang().size());
        Penumpang penumpang = controller.getDaftarPenumpang().get(pilihPenumpang - 1);
        System.out.println("\nPILIH JADWAL");

        for (int i = 0; i < controller.getDaftarJadwal().size(); i++) {
            JadwalShuttle j = controller.getDaftarJadwal().get(i);
            System.out.println((i + 1) + ". " + j.getKotaAsal() + " -> " + j.getKotaTujuan() + " | " + j.getJamBerangkat() + " | Sisa: " + j.getTiketTersedia()
            );
        }

        int pilihJadwal = inputInt("Pilih jadwal: ", 1, controller.getDaftarJadwal().size());
        JadwalShuttle jadwal = controller.getDaftarJadwal().get(pilihJadwal - 1);

        if (jadwal.getTiketTersedia() == 0) {
            System.out.println(
                    "Tiket pada jadwal ini sudah habis.");
            return;
        }

        int maksimalTiket = Math.min(10, jadwal.getTiketTersedia());
        int jumlahTiket = inputInt("Jumlah tiket: ", 1, maksimalTiket);
        Pemesanan pemesanan = controller.buatPemesanan(penumpang, jadwal);

        for (int i = 1; i <= jumlahTiket; i++) {
            System.out.println();
            System.out.println("TIKET KE-" + i);
            System.out.println("1. Reguler");
            System.out.println("   Harga normal, kursi otomatis");
            System.out.println("2. Prioritas");
            System.out.println("   Pilih kursi + biaya Rp25.000");
            int jenis = inputInt("Pilih jenis tiket: ", 1, 2);

            if (jenis == 1) {
                Tiket tiket = controller.buatTiketReguler(pemesanan, jadwal);
                System.out.println("Kursi otomatis: " + tiket.getNomorKursi());

            } else {
                jadwal.tampilkanKursi();
                int nomorKursi;

                while (true) {
                    nomorKursi = inputInt("Pilih nomor kursi: ", 1, jadwal.getKapasitasKursi());

                    if (jadwal.apakahKursiTersedia(nomorKursi)) {
                        break;
                    }

                    System.out.println("Kursi sudah terisi. " + "Pilih kursi lain.");
                }

                controller.buatTiketPrioritas(pemesanan, jadwal, nomorKursi);
            }
        }

        controller.simpanPemesanan(pemesanan);
        loading("Memproses pemesanan");
        System.out.println();
        System.out.println("==================================");
        System.out.println("       PEMESANAN BERHASIL");
        System.out.println("==================================");
        System.out.println("ID Pemesanan : " + pemesanan.getIdPemesanan());
        System.out.println("Jumlah Tiket : " + pemesanan.getJumlahTiket());
        System.out.printf("Total Harga  : Rp%,.0f%n", pemesanan.getTotalHarga());
        System.out.println("Pembayaran   : -");
        System.out.println("Status       : BELUM DIBAYAR");
        System.out.println();
        System.out.println("NOMOR TIKET");

        for (Tiket tiket : pemesanan.getDaftarTiket()) {
            System.out.println(tiket.getNomorTiket() + " | " + tiket.getJenisTiket() + " | Kursi " + tiket.getNomorKursi());
        }

        System.out.println("==================================");
        System.out.println("Silakan lakukan pembayaran " + "melalui menu Bayar Pemesanan.");
    }

    private void batalkanPemesanan() {
        controller.tampilkanPemesanan();
        String id = inputTeks("ID Pemesanan yang dibatalkan: ");
        controller.hapusPemesanan(id);
    }

    private void cariTiket() {
        if (!controller.adaTiket()) {
            System.out.println();
            System.out.println("Belum ada tiket yang dapat dicari.");
            System.out.println("Silakan buat pemesanan terlebih dahulu.");
            return;
        }

        controller.tampilkanDaftarTiket();
        System.out.println();
        String nomor = inputTeks("Masukkan nomor tiket yang ingin dicari: ");
        Tiket tiket = controller.cariTiket(nomor);

        if (tiket == null) {
            System.out.println();
            System.out.println("Tiket dengan nomor " + nomor.toUpperCase() + " tidak ditemukan.");
            return;
        }

        Penumpang penumpang = controller.cariPenumpang(tiket.getIdPenumpang());
        JadwalShuttle jadwal = controller.cariJadwal(tiket.getIdJadwal());
        Pemesanan pemesanan = controller.cariPemesanan(tiket.getIdPemesanan());
        System.out.println();
        System.out.println("==================================");
        System.out.println("          TIKET SHUTTLE           ");
        System.out.println("==================================");
        tiket.tampilkanTiket();

        if (penumpang != null) {
            System.out.println("Penumpang   : " + penumpang.getNama());
        }

        if (jadwal != null) {
            System.out.println("Rute        : " + jadwal.getKotaAsal() + " -> " + jadwal.getKotaTujuan());
            System.out.println("Berangkat   : " + jadwal.getJamBerangkat());
        }
        
        if (pemesanan != null) {
            System.out.println("Status      : " + (pemesanan.isSudahDibayar() ? "LUNAS" : "BELUM DIBAYAR"));
        }
        System.out.println("==================================");
    }

    private void tampilkanRingkasan() {
        System.out.println();
        System.out.println("==========================================");
        System.out.println("            STATISTIK SHUTTLE             ");
        System.out.println("==========================================");
        System.out.println();
        System.out.println("DATA UTAMA");
        System.out.println("------------------------------------------");
        System.out.printf("%-24s : %d%n", "Total Penumpang", controller.getDaftarPenumpang().size());
        System.out.printf("%-24s : %d%n", "Total Jadwal", controller.getDaftarJadwal().size());
        System.out.printf("%-24s : %d%n", "Total Pemesanan", controller.getDaftarPemesanan().size());
        System.out.println();
        System.out.println("PENJUALAN TIKET");
        System.out.println("------------------------------------------");
        System.out.printf("%-24s : %d%n", "Total Tiket Terjual", controller.getTotalTiketTerjual());
        System.out.printf("%-24s : %d%n", "Tiket Reguler", controller.getTotalTiketReguler());
        System.out.printf("%-24s : %d%n", "Tiket Prioritas", controller.getTotalTiketPrioritas());
        System.out.println();
        System.out.println("PENDAPATAN");
        System.out.println("------------------------------------------");
        System.out.printf("%-24s : Rp%,.0f%n", "Reguler", controller.getPendapatanReguler());
        System.out.printf("%-24s : Rp%,.0f%n", "Prioritas", controller.getPendapatanPrioritas());
        System.out.println("------------------------------------------");
        System.out.printf("%-24s : Rp%,.0f%n", "Total Pendapatan", controller.getTotalPendapatan());
        System.out.println();
        System.out.println("==========================================\n\n");
    }

    private int inputInt(String pesan, int minimal, int maksimal) {
        while (true) {
            try {
                System.out.print(pesan);
                int nilai = Integer.parseInt(input.nextLine());

                if (nilai < minimal || nilai > maksimal) {
                    System.out.println("Input harus antara " + minimal + " sampai " + maksimal + ".");
                    continue;
                }
                return nilai;

            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
            }
        }
    }

    private double inputDouble(String pesan, double minimal) {
        while (true) {
            try {
                System.out.print(pesan);
                double nilai = Double.parseDouble(input.nextLine());

                if (nilai < minimal) {
                    System.out.println("Nilai minimal " + minimal + ".");
                    continue;
                }
                return nilai;

            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
            }
        }
    }

    private String inputNama(String pesan) {
        while (true) {
            System.out.print(pesan);
            String nama = input.nextLine().trim();

            if (nama.length() < 3) {
                System.out.println("Nama minimal 3 karakter.");
                continue;
            }

            if (!nama.matches("[a-zA-Z ]+")) {
                System.out.println("Nama hanya boleh berisi huruf dan spasi.");
                continue;
            }

            return nama;
        }
    }

    private String inputNoHp(String pesan) {

        while (true) {
            System.out.print(pesan);
            String noHp = input.nextLine().trim();

            if (!noHp.matches("[0-9]{10,15}")) {
                System.out.println("Nomor HP harus 10-15 digit angka.");
                continue;
            }
            return noHp;
        }
    }

    private String inputJam(String pesan) {
        while (true) {
            System.out.print(pesan);
            String jam = input.nextLine().trim();
            if (!jam.matches("([01][0-9]|2[0-3]):[0-5][0-9]")) {
                System.out.println("Gunakan format HH:mm. " + "Contoh: 08:30.");
                continue;
            }
            return jam;
        }
    }

    private void loading(String pesan) {
        System.out.print(pesan);
        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(300);
                System.out.print(".");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println();
    }
    
    private String inputTeks(String pesan) {
        while (true) {
            System.out.print(pesan);
            String nilai = input.nextLine().trim();

            if (nilai.isEmpty()) {
                System.out.println("Input tidak boleh kosong.");
                continue;
            }

            return nilai;
        }
    }
    
    private void bayarPemesanan() {
        if (controller.getDaftarPemesanan().isEmpty()) {
            System.out.println(
                    "Belum ada pemesanan yang dapat dibayar.");
            return;
        }

        controller.tampilkanPemesanan();

        String id = inputTeks(
                "ID Pemesanan yang akan dibayar: ");

        Pemesanan pemesanan =
                controller.cariPemesanan(id);

        if (pemesanan == null) {
            System.out.println("Pemesanan tidak ditemukan.");
            return;
        }

        if (pemesanan.isSudahDibayar()) {
            System.out.println(
                    "Pemesanan tersebut sudah dibayar.");
            return;
        }

        System.out.println();
        System.out.println("TOTAL PEMBAYARAN");
        System.out.println(
                "Rp" + pemesanan.getTotalHarga());

        System.out.println();
        System.out.println("METODE PEMBAYARAN");
        System.out.println("1. Tunai");
        System.out.println("2. QRIS");

        int pilihan =
                inputInt("Pilih metode pembayaran: ", 1, 2);

        Pembayaran pembayaran;

        if (pilihan == 1) {
            pembayaran = new PembayaranTunai();
        } else {
            pembayaran = new PembayaranQRIS();
        }

        loading("Memproses pembayaran");

        controller.bayarPemesanan(id, pembayaran);

        System.out.println();
        System.out.println("Pembayaran berhasil.");
        System.out.println(
                "Metode : "
                + pembayaran.getMetodePembayaran());
        System.out.println("Status : LUNAS");
    }
}
