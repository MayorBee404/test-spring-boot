package com.abistudio.testspringboot;

import java.sql.SQLException;
import java.util.Vector;

public class UserRepository {
    // class ini digunakan sebagai pembungkus UserDBManager, agar class lain yang ingin mengolah data user
    // dapat lepas dari kompleksitas operasi database yang muncul di class UserDBManager.

    // class ini dibuat static karena tidak memiliki property (tidak ada "state" yang disimpan)

    public static Vector<MyUser> getAllUsersData(){
        try {
            //lakukan konversi format data disini jika diperlukan
            //tapi dalam contoh ini tidak diperlukan

            return UserDBManager.getAllUsers();

        } catch (SQLException e) {
            return null; //terjadi error pada saat operasi terhadap database, kembalikan null
        }
    }

    public static int deleteUser(String username) {
        try {
            return UserDBManager.deleteUser(username); //kembalikan jumlah baris yg berhasil dihapus
        } catch (SQLException throwables) {
            return -1; //kembalikan -1 jika proses penghapusan gagal
        }
    }

    public static MyUser getUserData(String username) {
        try {
            return UserDBManager.getUser(username);
        } catch (SQLException throwables) {
            return null;
        }
    }

    public static int createUser(MyUser newUserData) {
        try {
            return UserDBManager.insertUser(newUserData);
        } catch (SQLException throwables) {
            return -1; //kembalikan -1 jika proses penambahan data gagal
        }
    }
}
