package com.abistudio.testspringboot.user;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class RegistrationInfo {
    //TODO 5.Buat sebuah class RegistrationInfo yang mengandung username dan password pengguna baru, dilengkapi dengan annotation untuk memeriksa (clue: gunakan regex101.com) :
    //TODO 8.(Soal “Bonus” penambah nilai) Lengkapi annotation di class RegistrationInfo yang mengandung username dan password pengguna baru, dilengkapi dengan annotation untuk memeriksa (clue: gunakan regex101.com) :

    //TODO 5a.username: i.tidak boleh kosong, ii.panjangnya antara 5-16 karakter,
    //TODO 8a.username:i.hanya boleh berisi huruf kecil, huruf besar, atau angka
    @NotNull
    @Length(min = 5,max = 16)
    @Pattern(regexp = "^[a-zA-Z0-9]+$",message = "Format Salah")
    private String username;

    //TODO 5b.password (String polos biasa, berisi data password yang belum di encode): i.tidak boleh kosong, ii. panjang password antara 8-16 karakter
    //TODO 8b.password (String polos biasa, berisi data password yang belum di encode): i.mengandung minimal 1 huruf kapital ,ii.mengandung minimal 1 angka dan mengandung minimal 1 karakter khusus di antara karakter berikut: !@#_
    @NotNull
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*\\u0021|.*\\u0040|.*\\u0023|.*\\u005F)(.*)$", message = "Format Salah")
    @Length(min = 8, max = 16)
    private String password;

    //TODO 5c.Name : i.boleh kosong, ii. Panjang max 50 karakter, min tidak ditentukan
    @Nullable
    @Length(max = 50)
    private String name;

    //TODO 5d.Address : i.boleh kosong, ii. Panjang max 50 karakter, min tidak ditentukan
    @Nullable
    @Length(max = 50)
    private String address;

    public RegistrationInfo(){
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Nullable
    public String getAddress() {
        return address;
    }

    public void setAddress(@Nullable String address) {
        this.address = address;
    }

    public RegistrationInfo(String username, String password, @Nullable String name, @Nullable String address){
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
    }

}
