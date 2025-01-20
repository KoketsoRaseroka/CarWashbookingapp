package com.example.launch;

import android.os.StrictMode;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection {

        Connection con = null;

        public Connection createConnection() {
            String ip, port, db, un, pass, url = null;
            un = "sa";
            pass = "Password1";
            db = "Carwashdb";
            ip = "192.168.8.113";
            port = "1433";

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                url = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename=" + db + ";user=" + un + ";password=" + pass + ";";
                con = DriverManager.getConnection(url);
            } catch (Exception e) {
                Log.e("ERROR: ", e.getMessage());
            }

            return con;
        }


    }
