package main.java.dal;

import main.java.model.Characters;
import main.java.model.Currency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrencyDAO {

    public static Currency createCurrency(
            Connection cxn,
            String currencyName,
            int cap,
            int weeklyCap
    ) throws SQLException {
        String insertCurrencySql = "INSERT INTO currency (Currency_name, Cap, WeeklyCap) values (?,?,?) ";
        try (PreparedStatement ps = cxn.prepareStatement(insertCurrencySql)) {
            ps.setString(1, currencyName);
            ps.setInt(2, cap);
            ps.setInt(3, weeklyCap);
            ps.executeUpdate();
            return new Currency(
                    currencyName,
                    cap,
                    weeklyCap
            );
        }
    }

    public static Currency getCurrencyByName(
            Connection cxn,
            String currencyName
    ) throws SQLException {
        String selectCurrencySql = "SELECT * FROM currency WHERE Currency_name = ?";
        try (PreparedStatement ps = cxn.prepareStatement(selectCurrencySql)) {
            ps.setString(1, currencyName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Currency(
                            currencyName,
                            rs.getInt("Cap"),
                            rs.getInt("WeeklyCap")
                    );
                } else {
                    return null;
                }
            }
        }
    }
}
