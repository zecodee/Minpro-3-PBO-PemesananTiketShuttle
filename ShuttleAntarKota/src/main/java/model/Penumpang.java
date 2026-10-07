/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author zefrialrizkullah
 */
public class Penumpang {
    private final String idPenumpang;
    private String nama;
    private String noHp;
    
    public Penumpang(String idPenumpang, String nama, String noHp) {
        this.idPenumpang    = idPenumpang;
        setNama(nama);
        setNoHp(noHp);
    }
    public String getIdPenumpang() {
        return idPenumpang;
    }
    public String getNama() {
        return nama;
    }
    
    public String getNoHp() {
        return noHp;
    }
    
    public void setNama(String nama) {
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong.");
        }

        if (nama.trim().length() < 3) {
            throw new IllegalArgumentException("Nama minimal 3 karakter.");
        }

        if (!nama.trim().matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException(
                    "Nama hanya boleh berisi huruf dan spasi.");
        }

        this.nama = nama.trim();
    }
    
    public void setNoHp(String noHp) {
        if (noHp == null || !noHp.matches("[0-9]{10,15}")) {
            throw new IllegalArgumentException("Nomor HP harus terdiri dari 10-15 digit angka.");
        }
        this.noHp = noHp;
    }
    
    public void tampilkanData() {
        System.out.println("ID Penumpang    : " + idPenumpang);
        System.out.println("Nama            : " + nama);
        System.out.println("No HP           : " + noHp);
    }
}
