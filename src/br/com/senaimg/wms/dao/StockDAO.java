/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.dao;

import br.com.senaimg.wms.hibernate.SessionHibernateUtil;
import br.com.senaimg.wms.language.Lang;
import br.com.senaimg.wms.model.warehouse.stock.Stock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ÁlefeLucas
 */
public abstract class StockDAO {

	/**
	 *
	 * @param stock
	 */
	public static void insertStock(Stock stock) {

		try {
			Session session = SessionHibernateUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();

			session.save(stock);

			transaction.commit();
			session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}

		Lang.loadLanguage();

	}

	/**
	 * Updates a record in the stock table in database through Hibernate.
	 *
	 * @param stock
	 */
	public static void updateStock(Stock stock) {

		try {
			Session session = SessionHibernateUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();

			session.update(stock);

			transaction.commit();
			session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}

	}

	/**
	 * Deletes a record in the stock table in database through Hibernate.
	 *
	 * @param stock
	 */
	public static void deleteStock(Stock stock) {

		try {
			Session session = SessionHibernateUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();

			session.delete(stock);

			transaction.commit();
			session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}

	}

	/**
	 * Updates or inserts if not exists a record in the stock table in database
	 * through Hibernate.
	 *
	 * @param stock
	 */
	public static void mergeStock(Stock stock) {

		try {
			Session session = SessionHibernateUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();

			session.merge(stock);

			transaction.commit();
			session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}

	}

	/**
	 * Inserts several new records into the stock table in database through
	 * Hibernate.
	 *
	 * @param stocks
	 */
	public static void insertStocks(List<Stock> stocks) {

		try {
			Session session = SessionHibernateUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();

			stocks.forEach(stock -> session.save(stock));

			transaction.commit();
			session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}

	}

	/**
	 * Updates several records in the stock table in database through Hibernate.
	 *
	 * @param stocks
	 */
	public static void updateStocks(List<Stock> stocks) {

		try {
			Session session = SessionHibernateUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();

			stocks.forEach(stock -> session.update(stock));

			transaction.commit();
			session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}

	}

	/**
	 * Deletes several records in the stock table in database through Hibernate.
	 *
	 * @param stocks
	 */
	public static void deleteStocks(List<Stock> stocks) {

		try {
			Session session = SessionHibernateUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();

			stocks.forEach(stock -> session.delete(stock));

			transaction.commit();
			session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}

	}

	/**
	 * Updates or inserts if not exists several records in the stock table in
	 * database through Hibernate.
	 *
	 * @param stocks
	 */
	public static void mergeStocks(List<Stock> stocks) {

		try {
			Session session = SessionHibernateUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();

			stocks.forEach(stock -> session.merge(stock));

			transaction.commit();
			session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}

	}

	/**
	 * Lists all the records from the stock table in database through Hibernate.
	 *
	 * @return List &lt Stock>
	 */
	public static List<Stock> selectStocks() {
		List<Stock> stocks = new ArrayList<>();

		try {
			Session session = SessionHibernateUtil.getSessionFactory().openSession();

			Criteria criteria = session.createCriteria(Stock.class);
			stocks = criteria.list();
			session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}
		return stocks;
	}

	public static List<Stock> selectStocksTurnover() {

		System.out.println("SELECT STOCK 1");

		List<Stock> stocks = selectStocks();

		System.out.println("SELECT STOCK 2");
		ArrayList<Stock> stockTurnover = new ArrayList<>();
		System.out.println("SELECT STOCK 3");

		for (Stock stock : stocks) {
			if (stock.getItem().getBatch().getMetaItem().getOperation().isTurnover()) {
				stockTurnover.add(stock);
			}
		}

		System.out.println("SELECT STOCK 4");
		return stocks;
	}

	public static List<Stock> selectStocksFifo() {
		List<Stock> stocks = new ArrayList<>();

		try {
			Session session = SessionHibernateUtil.getSessionFactory().openSession();

			Criteria criteria = session.createCriteria(Stock.class);
			stocks = criteria.list();

			Collections.sort(stocks, (o1, o2) -> {

				int compare = 0;
				try {
					compare = o1.getItem().getBatch().getExpiration()
							.compareTo(o2.getItem().getBatch().getExpiration());
				} catch (Exception ex) {
					compare = 0;
				}
				if (compare == 0) {
					compare = (o1.getPallet().getId() - o2.getPallet().getId() > 0 ? 1 : -1);
				}
				return compare;
			});

			session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}

		return stocks;
	}

	public static List<Stock> selectStocksFilo() {
		List<Stock> stocks = new ArrayList<>();

		try {
			Session session = SessionHibernateUtil.getSessionFactory().openSession();

			Criteria criteria = session.createCriteria(Stock.class);
			stocks = criteria.list();

			Collections.sort(stocks, (o1, o2) -> {
				int compare = o2.getItem().getBatch().getExpiration()
						.compareTo(o1.getItem().getBatch().getExpiration());

				if (compare == 0) {
					compare = (o1.getPallet().getId() - o2.getPallet().getId() > 0 ? 1 : -1);
				}
				return compare;
			});

			session.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}
		return stocks;
	}
}
