package DAO;

import java.time.LocalDate;

import model.HistoryModel;

public interface HistoryDao {
	HistoryModel show(LocalDate date);

}
