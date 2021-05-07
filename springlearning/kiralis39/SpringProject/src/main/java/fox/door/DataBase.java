package fox.door;


import fox.clinics.PetClinic;
import org.postgresql.util.GT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class DataBase {
    private static Connection conn;

    public DataBase(Connection connection) throws ClassNotFoundException {
        this.conn = connection;
        System.out.println("Data base is connected.");
    }

    public static PetClinic addClinic(PetClinic newClinic) {
        try (PreparedStatement pSt = conn.prepareStatement("INSERT INTO clinics (\"name\", \"fiasGUID\", \"phoneNumber\", \"urlAddress\", \"comment\", \"photoId\") values (?,?,?,?,?,?);")) {
            pSt.setString(1, newClinic.getName());
            pSt.setObject(2, newClinic.getFias());
            pSt.setLong(3, newClinic.getPhone());
            pSt.setObject(4, newClinic.getUrlIndex());
            pSt.setString(5, newClinic.getComment());
            pSt.setObject(6, newClinic.getPhotoDirIndex());

            pSt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Не удалось создать клинику '" + newClinic.getName() + "'! " + e.getMessage());
            e.printStackTrace();
            return null;
        }

        return newClinic;
    }

    public static void clearDB() {
        try (
                PreparedStatement pSt1 = conn.prepareStatement("SELECT id FROM clinics;");
                PreparedStatement pSt2 = conn.prepareStatement("SELECT doctor_id FROM clidoc WHERE clinic_id = ?;");
                PreparedStatement pSt3 = conn.prepareStatement("DELETE FROM clidoc WHERE doctor_id = ?;");
        ) {
            conn.setAutoCommit(false);

            Savepoint save = conn.setSavepoint();
            try {
                ResultSet clinicsRs = pSt1.executeQuery();
                while (clinicsRs.next()) {
                    pSt2.setInt(1, clinicsRs.getInt(1));
                    ResultSet doctorsRs = pSt2.executeQuery();
                    while (doctorsRs.next()) {
                        pSt3.setInt(1, doctorsRs.getInt(1));
                        pSt3.execute();
                    }

                    Savepoint saveBeforeError = conn.setSavepoint();
                    try {
                        pSt3.setInt(8, 13);
                        pSt3.execute();
                    } catch (Exception e) {
                        System.out.println("Error rollback checked.");
                        conn.rollback(saveBeforeError);
                    }

                }
            } catch (Exception e) {
                System.out.println("Error executing!");
                conn.rollback(save);
            }

            conn.setAutoCommit(true);
        } catch (Exception e) {
            System.out.println("Не удалось создать клинику почистить базу.");
            e.printStackTrace();
        }
    }

    public static List<PetClinic> getClinics() {
        ArrayList<PetClinic> existsClinics = new ArrayList<>();

        try (Statement statmt = conn.createStatement()) {
            ResultSet rs = statmt.executeQuery("SELECT * FROM clinics;");
            while (rs.next()) {
                existsClinics.add(new PetClinic(rs));
            }
        } catch (Exception e) {
            System.out.println("Не удалось выбрать клиники из базы данных!");
            e.printStackTrace();
            return null;
        }

        return existsClinics;
    }

    public void init() {
        System.out.println("Init the connection DB...");
    }

    public void closeDataBase() throws Exception {
        System.out.println("Destroying the connection DB...");
        if (conn != null && !conn.isClosed()) {conn.close();}
    }

    public void example() {
        try (Statement statmt = conn.createStatement()) {
            int tInt = statmt.executeQuery("SELECT * FROM type WHERE typename = '';").getInt("id");
            if (tInt < 0) {
                System.err.println("DataBase: addNewData(): ERR: tInt is " + tInt);
                return;
            }

            try {
                statmt.execute("INSERT INTO 'aids' ("
                        + "'name', 'type', 'description', "
                        + "'modificКРС', 'modificМРС', 'modificHrs', 'modificPig', 'modificPAn', 'modificBrd', 'modificDgz', 'modificCts', 'picpath'"
                        + ") VALUES ("
                        + "'" + "', '" + tInt + "', '" + "', '" + "');");
            } catch (SQLException e) {
                System.out.println("Не удалось создать элемент. Возможно, он уже есть.");
                System.out.println("Попытка обновить запись id #");

                Statement updStatmt = conn.createStatement();
                updStatmt.execute("UPDATE aids SET "
                        + "name='" + "', "
                        + "type='" + tInt + "', "
                        + "description='" + "', "
                        + "modific='" + "', "
                        + "picpath='" + "' "
                        + "WHERE id=" + ";");

                updStatmt.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeFromDB(String removableItemName) {
        try (Statement statmt = conn.createStatement()) {
            int correct = statmt.executeQuery("SELECT EXISTS (SELECT * FROM aids WHERE name='" + removableItemName + "' LIMIT 1);").getInt(1);

            if (correct != 0) {
                statmt.execute("DELETE FROM aids WHERE aids.name='" + removableItemName + "';");
                JOptionPane.showConfirmDialog(null,
                        "Удаление препарата '" + removableItemName + "' завершено.",
                        "Выполнено!", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showConfirmDialog(null,
                        "Не существует '" + removableItemName + "' в базе!",
                        "Ошибка:", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getTypeList() {
        ArrayList<String> result = new ArrayList<String>();

        try (
                Statement tmp = conn.createStatement();
                ResultSet resSet = tmp.executeQuery("SELECT * FROM 'type';")
        ) {
            while (resSet.next()) {
                result.add(resSet.getString("typename"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public ArrayList<String> getElementsOfType(String typeChosen) {
        ArrayList<String> result = new ArrayList<String>();

        try (
                Statement tmp = conn.createStatement();
                ResultSet resSet = tmp.executeQuery("SELECT * FROM aids WHERE type=(SELECT id FROM type WHERE typename='" + typeChosen + "');")
        ) {
            while (resSet.next()) {
                result.add(resSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("getElementsOfType() result: " + Arrays.asList(result));
        return result;
    }
}