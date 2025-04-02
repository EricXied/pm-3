
import main.java.dal.*;
import main.java.model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class Driver {
    public static void main(String[] args) {
        try {
            resetSchema();
            insertRecords();
        } catch (SQLException e) {
            System.out.print("SQL Exception: ");
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static void insertRecords() throws SQLException {
        try (
                Connection cxn = ConnectionManager.getConnection()
        ) {
            // INSERT objects from our model.
            Date date = new Date();
            Player player1 = PlayerDAO.create(cxn, "2hy", "1066153598@qq.com");
            Player player2 = PlayerDAO.create(cxn, "NovaKing", "NovaaaaKinggggg@gmail.com");
            Player player3 = PlayerDAO.create(cxn, "Chinamaster", "chinamaster@gmail.com");
            Player player4 = PlayerDAO.create(cxn, "Player_4", "player4@game.com");
            Player player5 = PlayerDAO.create(cxn, "Player_5", "player5@game.com");
            Player player6 = PlayerDAO.create(cxn, "Player_6", "player6@game.com");
            Player player7 = PlayerDAO.create(cxn, "Player_7", "player7@game.com");

            Clan clan1 = ClanDAO.create(cxn, Race.ELF, "Duskwight");
            Clan clan2 = ClanDAO.create(cxn, Race.ELF, "Wildwood");
            Clan clan3 = ClanDAO.create(cxn, Race.ORC, "Bloodfang");
            Clan clan4 = ClanDAO.create(cxn, Race.ORC, "Ironjaw");
            Clan clan5 = ClanDAO.create(cxn, Race.HUMAN, "Midlanders");
            Clan clan6 = ClanDAO.create(cxn, Race.HUMAN, "Highlanders");
            Clan clan7 = ClanDAO.create(cxn, Race.DWARF, "Stonebeard");
            Clan clan8 = ClanDAO.create(cxn, Race.DWARF, "Thunderhammer");
            Clan clan9 = ClanDAO.create(cxn, Race.GOBLIN, "Rotgear Syndicate");
            Clan clan10 = ClanDAO.create(cxn, Race.GOBLIN, "Boomgob Brigands");
            Clan clan11 = ClanDAO.create(cxn, Race.HALFLING, "Riverfoot");
            Clan clan12 = ClanDAO.create(cxn, Race.HALFLING, "Hearthhill");

            Characters character1 = CharacterDAO.create(cxn, player1, "Shark", "Z", Race.ELF, clan1);
            Characters character2 = CharacterDAO.create(cxn, player2, "Ryan", "Walker", Race.ORC, clan3);
            Characters character3 = CharacterDAO.create(cxn, player3, "Nova", "Vex", Race.HUMAN, clan5);
            Characters character4 = CharacterDAO.create(cxn, player4, "Elara", "Nightbloom", Race.DWARF, clan7);
            Characters character5 = CharacterDAO.create(cxn, player5, "Zane", "Draven", Race.GOBLIN, clan9);
            Characters character6 = CharacterDAO.create(cxn, player6, "Sable", "Orion", Race.HALFLING, clan11);
            Characters character7 = CharacterDAO.create(cxn, player7, "Wyatt", "Wyatt", Race.GOBLIN, clan10);

            CharacterJob characterJob1 = CharacterJobDAO.createCharacterJob(cxn, Job.WARRIOR, character1, 20, 1500, false);
            CharacterJob characterJob2 = CharacterJobDAO.createCharacterJob(cxn, Job.MAGE, character2, 15, 1200, true);
            CharacterJob characterJob3 = CharacterJobDAO.createCharacterJob(cxn, Job.THIEF, character3, 30, 2000, false);
            CharacterJob characterJob4 = CharacterJobDAO.createCharacterJob(cxn, Job.HUNTER, character4, 10, 800, true);
            CharacterJob characterJob5 = CharacterJobDAO.createCharacterJob(cxn, Job.NECROMANCER, character5, 25, 1800, false);

            Currency currency1 = CurrencyDAO.create(cxn, "Gold", 100000, 5000);
            Currency currency2 = CurrencyDAO.create(cxn, "Silver", 5000000, 100000);
            Currency currency3 = CurrencyDAO.create(cxn, "Platinum", 5000, 200);
            Currency currency4 = CurrencyDAO.create(cxn, "MagicCoins", 100000, 3000);
            Currency currency5 = CurrencyDAO.create(cxn, "DivineCoins", 500, 50);
            Currency currency6 = CurrencyDAO.create(cxn, "ChaosCoins", 500, 50);
            Currency currency7 = CurrencyDAO.create(cxn, "ExaltedCoins", 500, 50);

            CharacterStats characterStats11 = CharacterStatsDAO.create(cxn, Statistics.STRENGTH, character1,12);
            CharacterStats characterStats12 = CharacterStatsDAO.create(cxn, Statistics.DEXTERITY, character1,8);
            CharacterStats characterStats13 = CharacterStatsDAO.create(cxn, Statistics.VITALITY, character1,10);
            CharacterStats characterStats14 = CharacterStatsDAO.create(cxn, Statistics.INTELLIGENCE, character1,7);
            CharacterStats characterStats15 = CharacterStatsDAO.create(cxn, Statistics.HP, character1,120);
            CharacterStats characterStats16 = CharacterStatsDAO.create(cxn, Statistics.MP, character1,90);
            CharacterStats characterStats17 = CharacterStatsDAO.create(cxn, Statistics.CRITICAL_RATE, character1,3);
            CharacterStats characterStats18 = CharacterStatsDAO.create(cxn, Statistics.EVASION_RATE, character1,2);

            CharacterStats characterStats21 = CharacterStatsDAO.create(cxn, Statistics.STRENGTH, character2,9);
            CharacterStats characterStats22 = CharacterStatsDAO.create(cxn, Statistics.DEXTERITY, character2,14);
            CharacterStats characterStats23 = CharacterStatsDAO.create(cxn, Statistics.VITALITY, character2,7);
            CharacterStats characterStats24 = CharacterStatsDAO.create(cxn, Statistics.INTELLIGENCE, character2,10);
            CharacterStats characterStats25 = CharacterStatsDAO.create(cxn, Statistics.HP, character2,100);
            CharacterStats characterStats26 = CharacterStatsDAO.create(cxn, Statistics.MP, character2,110);
            CharacterStats characterStats27 = CharacterStatsDAO.create(cxn, Statistics.CRITICAL_RATE, character2,4);
            CharacterStats characterStats28 = CharacterStatsDAO.create(cxn, Statistics.EVASION_RATE, character2,3);

            CharacterStats characterStats31 = CharacterStatsDAO.create(cxn, Statistics.STRENGTH, character3,8);
            CharacterStats characterStats32 = CharacterStatsDAO.create(cxn, Statistics.DEXTERITY, character3,10);
            CharacterStats characterStats33 = CharacterStatsDAO.create(cxn, Statistics.VITALITY, character3,15);
            CharacterStats characterStats34 = CharacterStatsDAO.create(cxn, Statistics.INTELLIGENCE, character3,6);
            CharacterStats characterStats35 = CharacterStatsDAO.create(cxn, Statistics.HP, character3,140);
            CharacterStats characterStats36 = CharacterStatsDAO.create(cxn, Statistics.MP, character3,70);
            CharacterStats characterStats37 = CharacterStatsDAO.create(cxn, Statistics.CRITICAL_RATE, character3,2);
            CharacterStats characterStats38 = CharacterStatsDAO.create(cxn, Statistics.EVASION_RATE, character3,4);

            CharacterStats characterStats41 = CharacterStatsDAO.create(cxn, Statistics.STRENGTH, character4,6);
            CharacterStats characterStats42 = CharacterStatsDAO.create(cxn, Statistics.DEXTERITY, character4,7);
            CharacterStats characterStats43 = CharacterStatsDAO.create(cxn, Statistics.VITALITY, character4,8);
            CharacterStats characterStats44 = CharacterStatsDAO.create(cxn, Statistics.INTELLIGENCE, character4,18);
            CharacterStats characterStats45 = CharacterStatsDAO.create(cxn, Statistics.HP, character4,80);
            CharacterStats characterStats46 = CharacterStatsDAO.create(cxn, Statistics.MP, character4,160);
            CharacterStats characterStats47 = CharacterStatsDAO.create(cxn, Statistics.CRITICAL_RATE, character4,5);
            CharacterStats characterStats48 = CharacterStatsDAO.create(cxn, Statistics.EVASION_RATE, character4,3);

            Gear gear1 = GearDAO.create(cxn, "Iron Helm", 5, 1, 1200, 1, Slot.HELMET);
            Gear gear2 = GearDAO.create(cxn, "Steel Visor", 10, 1, 2500, 5, Slot.HELMET);
            Gear gear3 = GearDAO.create(cxn, "Mystic Hood", 15, 1, 4000, 8, Slot.HELMET);
            Gear gear4 = GearDAO.create(cxn, "Shadow Mask", 20, 1, 5200, 10, Slot.HELMET);
            Gear gear5 = GearDAO.create(cxn, "Dragon Helm", 30, 1, 10000, 15, Slot.HELMET);

            Gear gear6 = GearDAO.create(cxn, "Leather Vest", 5, 1, 1400, 1, Slot.HELMET);
            Gear gear7 = GearDAO.create(cxn, "Chainmail Chestplate", 10, 1, 3000, 5, Slot.HELMET);
            Gear gear8 = GearDAO.create(cxn, "Runic Battleplate", 18, 1, 6000, 9, Slot.HELMET);
            Gear gear9 = GearDAO.create(cxn, "Necromancer Robe", 22, 1, 7500, 12, Slot.HELMET);
            Gear gear10 = GearDAO.create(cxn, "Titan Armor", 35, 1, 12000, 18, Slot.HELMET);


        }
    }

    private static void resetSchema() throws SQLException {
        try (
                Connection cxn = ConnectionManager.getSchemalessConnection()
        ) {
            cxn.createStatement().executeUpdate(
                    "drop schema if exists PM3;"
            );
            cxn.createStatement().executeUpdate("create schema PM3;");
            cxn.createStatement().executeUpdate("use PM3;");
        }

        try (
                Connection cxn = ConnectionManager.getConnection()
        ) {
            cxn.createStatement().executeUpdate("""
                                        CREATE TABLE Player (
                                            PlayerID INT AUTO_INCREMENT PRIMARY KEY,
                                            FullName VARCHAR(255) NOT NULL,
                                            Email VARCHAR(255) UNIQUE NOT NULL,
                                            AccountCreated TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
                                        );              
                    """
            );

            cxn.createStatement().executeUpdate("""
                                        CREATE TABLE Race (
                                            RaceName VARCHAR(100) PRIMARY KEY
                                        );
                    """
            );

            cxn.createStatement().executeUpdate("""
                                        CREATE TABLE Clan (
                                              RaceName VARCHAR(100) NOT NULL,
                                              ClanName VARCHAR(100) NOT NULL,
                                              PRIMARY KEY (RaceName, ClanName),
                                              FOREIGN KEY (RaceName) REFERENCES Race(RaceName)
                                                ON DELETE CASCADE ON UPDATE CASCADE,
                                          	CONSTRAINT unique_race_clan UNIQUE (ClanName, RaceName)
                                          );
                    """
            );

            cxn.createStatement().executeUpdate("""
                                                           
                                         CREATE TABLE Job (
                                            JobName VARCHAR(100) PRIMARY KEY
                                        );
                    """
            );

            cxn.createStatement().executeUpdate("""
                                        CREATE TABLE Characters (
                                            CharacterID INT AUTO_INCREMENT PRIMARY KEY,
                                            PlayerID INT NOT NULL,
                                            FirstName VARCHAR(100) NOT NULL,
                                            LastName VARCHAR(100) NOT NULL,
                                            RaceName VARCHAR(100),
                                            ClanName VARCHAR(100),
                                        -- ON DELETE CASCADE (Cascade Delete): Deleting a Player should delete all their Characters
                                            FOREIGN KEY (PlayerID) REFERENCES Player(PlayerID)
                                              ON DELETE CASCADE ON UPDATE CASCADE,
                                            FOREIGN KEY (RaceName) REFERENCES Race(RaceName)
                                              ON DELETE SET NULL ON UPDATE CASCADE,
                                            FOREIGN KEY (RaceName, ClanName) REFERENCES Clan(RaceName, ClanName)
                                              ON DELETE SET NULL ON UPDATE CASCADE
                                        );
                    """
            );

            cxn.createStatement().executeUpdate("""
                                        CREATE TABLE CharacterJob (
                                            JobName VARCHAR(100) NOT NULL,
                                            CharacterID INT NOT NULL,
                                            `Level` INT NOT NULL DEFAULT 1,
                                            XP INT NOT NULL DEFAULT 0,
                                            `Lock` BOOLEAN NOT NULL DEFAULT FALSE,
                                            PRIMARY KEY (JobName, CharacterID),
                                            FOREIGN KEY (JobName) REFERENCES Job(JobName)
                                              ON DELETE CASCADE ON UPDATE CASCADE,
                                        -- Deleting a Character should delete its related data
                                            FOREIGN KEY (CharacterID) REFERENCES Characters(CharacterID)
                                              ON DELETE CASCADE ON UPDATE CASCADE
                                        );
                    """
            );
            cxn.createStatement().executeUpdate("""
                                        CREATE TABLE Currency (
                                              CurrencyName VARCHAR(100) PRIMARY KEY,
                                              Cap INT,
                                              WeeklyCap INT
                                          );
                    """
            );

            cxn.createStatement().executeUpdate("""
                                        CREATE TABLE CharacterCurrency (
                                                CurrencyName VARCHAR(100) NOT NULL,
                                                CharacterID INT NOT NULL,
                                                TotalAmount INT NOT NULL,
                                                WeeklyAmount INT NOT NULL,
                                                PRIMARY KEY (CurrencyName, CharacterID),
                                                FOREIGN KEY (CurrencyName) REFERENCES Currency(CurrencyName)
                                                  ON DELETE CASCADE ON UPDATE CASCADE,
                                                FOREIGN KEY (CharacterID) REFERENCES Characters(CharacterID)
                                                ON DELETE CASCADE ON UPDATE CASCADE
                                            );
                    """
            );

            cxn.createStatement().executeUpdate("""           
                                        CREATE TABLE Statistics (
                                         StatsName VARCHAR(100) PRIMARY KEY
                                                       );          
                    """
            );

            cxn.createStatement().executeUpdate("""
                                        CREATE TABLE CharacterStats (
                                            StatsName VARCHAR(100) NOT NULL,
                                            CharacterID INT NOT NULL,
                                            `Value` INT NOT NULL DEFAULT 0,
                                            PRIMARY KEY (StatsName, CharacterID),
                                            FOREIGN KEY (StatsName) REFERENCES Statistics(StatsName)
                                              ON DELETE CASCADE ON UPDATE CASCADE,
                                            FOREIGN KEY (CharacterID) REFERENCES Characters(CharacterID)
                                              ON DELETE CASCADE ON UPDATE CASCADE
                                        );
                    """
            );


            cxn.createStatement().executeUpdate("""
                                        CREATE TABLE Slot (
                                            SlotName VARCHAR(100) PRIMARY KEY
                                        );
                    """
            );

            cxn.createStatement().executeUpdate("""
                                        CREATE TABLE Item (
                                            ItemID INT AUTO_INCREMENT PRIMARY KEY,
                                            ItemName VARCHAR(255) NOT NULL,
                                            `Level` INT NOT NULL DEFAULT 1,
                                            MaxStackSize INT NOT NULL,
                                            Price DECIMAL(10,2),
                                            RequiredLevel INT
                                        );
                    """
            );

            cxn.createStatement().executeUpdate("""
                                        CREATE TABLE Gear (
                                            ItemID INT PRIMARY KEY,
                                            SlotName VARCHAR(100) NOT NULL,
                                        -- Deleting an Item should delete its related bonuses
                                        -- When an Item is deleted, any associated bonuses (GearBonus, ConsumableBonus)\s
                                        -- should also be deleted since they are directly linked to that item.
                                            FOREIGN KEY (ItemID) REFERENCES Item(ItemID)
                                              ON DELETE CASCADE ON UPDATE CASCADE,
                                            FOREIGN KEY (SlotName) REFERENCES Slot(SlotName)
                                              ON DELETE CASCADE ON UPDATE CASCADE
                                        );
                    """
            );

            cxn.createStatement().executeUpdate("""
                                        CREATE TABLE Weapon (
                                            ItemID INT PRIMARY KEY,
                                            JobName VARCHAR(100) NOT NULL,
                                            Damage INT NOT NULL,
                                            FOREIGN KEY (ItemID) REFERENCES Item(ItemID)
                                              ON DELETE CASCADE ON UPDATE CASCADE,
                                            FOREIGN KEY (JobName) REFERENCES Job(JobName)
                                              ON DELETE CASCADE ON UPDATE CASCADE
                                        );
                    """
            );

            cxn.createStatement().executeUpdate("""
                                        CREATE TABLE Consumable (
                                            ItemID INT PRIMARY KEY,
                                            `Description` TEXT NOT NULL,
                                            FOREIGN KEY (ItemID) REFERENCES Item(ItemID)
                                              ON DELETE CASCADE ON UPDATE CASCADE
                                        );
                    """
            );

            cxn.createStatement().executeUpdate("""
                                        CREATE TABLE GearBonus (
                                            ItemID INT NOT NULL,
                                            StatsName VARCHAR(100) NOT NULL,
                                            `Value` INT NOT NULL,
                                            PRIMARY KEY (ItemID, StatsName),
                                            FOREIGN KEY (ItemID) REFERENCES Item(ItemID)
                                              ON DELETE CASCADE ON UPDATE CASCADE,
                                            FOREIGN KEY (StatsName) REFERENCES Statistics(StatsName)
                                              ON DELETE CASCADE ON UPDATE CASCADE
                                        );
                    """
            );

            cxn.createStatement().executeUpdate("""
                                        CREATE TABLE ConsumableBonus (
                                            ItemID INT NOT NULL,
                                            StatsName VARCHAR(100) NOT NULL,
                                            `Value` INT NOT NULL,
                                            BonusCap INT NOT NULL,
                                            BonusPercentage DECIMAL(5,2) NOT NULL,
                                            PRIMARY KEY (ItemID, StatsName),
                                            FOREIGN KEY (ItemID) REFERENCES Consumable(ItemID)
                                              ON DELETE CASCADE ON UPDATE CASCADE,
                                            FOREIGN KEY (StatsName) REFERENCES Statistics(StatsName)
                                              ON DELETE CASCADE ON UPDATE CASCADE
                                        );
                    """
            );

            cxn.createStatement().executeUpdate("""
                                        CREATE TABLE CharacterEquipment (
                                            CharacterID INT NOT NULL,
                                            SlotName VARCHAR(100) NOT NULL,
                                            ItemID INT,
                                            PRIMARY KEY (CharacterID, SlotName),
                                            FOREIGN KEY (CharacterID) REFERENCES Characters(CharacterID)
                                              ON DELETE CASCADE ON UPDATE CASCADE,
                                            FOREIGN KEY (SlotName) REFERENCES Slot(SlotName)
                                              ON DELETE CASCADE ON UPDATE CASCADE,
                                        -- ON DELETE SET NULL (Set Foreign Key to NULL): If an equipped item is deleted, clear the equipment slot instead of deleting the entire row
                                        -- The CharacterEquipment table stores a character’s currently equipped items. If an Item is deleted,\s
                                        -- we should not delete the entire CharacterEquipment record (which contains important slot data).\s
                                        -- Instead, we just set ItemID to NULL, meaning that the equipment slot is now empty.
                                            FOREIGN KEY (ItemID) REFERENCES Item(ItemID)
                                              ON DELETE SET NULL ON UPDATE CASCADE
                                        );
                    """
            );

            cxn.createStatement().executeUpdate("""

                                        CREATE TABLE CharacterInventory (
                                            SlotNumber INT NOT NULL,
                                            CharacterID INT NOT NULL,
                                            ItemID INT,
                                            Quantity INT NOT NULL,
                                            PRIMARY KEY (SlotNumber, CharacterID),
                                            FOREIGN KEY (CharacterID) REFERENCES Characters(CharacterID)
                                              ON DELETE CASCADE ON UPDATE CASCADE,
                                        -- If an item is deleted, update CharacterInventory to reflect that the slot is now empty
                                            FOREIGN KEY (ItemID) REFERENCES Item(ItemID)
                                              ON DELETE SET NULL ON UPDATE CASCADE
                                        );
                    """
            );

            cxn.createStatement().executeUpdate("""

                                       INSERT INTO Race (RaceName) VALUES
                                            ('Elf'),
                                            ('Orc'),
                                            ('Human'),
                                            ('Dwarf'),
                                            ('Goblin'),
                                            ('Halfling');
                    """
            );

            cxn.createStatement().executeUpdate("""
                                        INSERT INTO Job (JobName) VALUES
                                        ('Warrior'), ('Mage'), ('Thief'), ('Hunter'), ('Necromancer');
                    """
            );

            cxn.createStatement().executeUpdate("""

                    INSERT INTO Statistics (StatsName) VALUES
                    ('Strength'), ('Dexterity'), ('Vitality'), ('Intelligence'), ('HP'), ('MP'), ('Critical Rate'), ('Evasion');
                    """
            );

            cxn.createStatement().executeUpdate("""

                    INSERT INTO Slot (SlotName) VALUES
                    ('Helmet'), ('Armor'), ('Boots'), ('Gloves'), ('Weapon');
                    """
            );


        }
    }
}
