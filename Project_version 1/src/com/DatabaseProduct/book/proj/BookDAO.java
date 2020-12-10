package com.DatabaseProduct.book.proj;

import javax.sql.DataSource;

public class BookDAO {
	private static BookDAO dao = new BookDAO();
	private final String CONNECTION_POOL_NAME = "jdbc/testdb";
	private final String TABLE_NAME = "book_";
	private DataSource dataSource;
}
