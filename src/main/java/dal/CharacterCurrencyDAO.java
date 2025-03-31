package main.java.dal;

import main.java.model.CharacterCurrency;
import main.java.model.Characters;
import main.java.model.Currency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CharacterCurrencyDAO {

    public static CharacterCurrency create(
            Connection cxn,
            Currency currency,
            Characters characters,
            int totalAmount,
            int weeklyAmount
    ) throws SQLException {
        String insertCharacterCurrencySQL = "INSERT INTO CharacterCurrencies (CurrencyName, CharacterID, TotalAmount, WeeklyAmount) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = cxn.prepareStatement(insertCharacterCurrencySQL)) {
            ps.setString(1, currency.getCurrencyName());
            ps.setInt(2, characters.getCharacterID());
            ps.setInt(3, totalAmount);
            ps.setInt(4, weeklyAmount);
            ps.executeUpdate();
            return new CharacterCurrency(
                    currency,
                    characters,
                    totalAmount,
                    weeklyAmount
            );
        }
    }

    public static CharacterCurrency getCharacterCurrencyByIdAndName(
            Connection cxn,
            int characterId,
            String currencyName
    ) throws SQLException {
        String getCharacterCurrencySQL = "SELECT * FROM CharacterCurrencies WHERE CharacterID = ? AND CurrencyName = ?";
        try (PreparedStatement ps = cxn.prepareStatement(getCharacterCurrencySQL)) {
            ps.setInt(1, characterId);
            ps.setString(2, currencyName);
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    Characters character = CharacterDAO.getCharacterByID(cxn, characterId);
                    Currency currency = CurrencyDAO.getCurrencyByName(cxn, currencyName);
                    return new CharacterCurrency(
                            currency,
                            character,
                            rs.getInt("TotalAmount"),
                            rs.getInt("WeeklyAmount")
                    );
                } else {
                    return null;
                }
            }
        }
    }
}
