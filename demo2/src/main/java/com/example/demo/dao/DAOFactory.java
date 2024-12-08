package com.example.demo.dao;

public class DAOFactory {
    public static IMyDAO getDAO() {
        return new MySQLDAO();
    }
}
