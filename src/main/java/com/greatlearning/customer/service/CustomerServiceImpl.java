package com.greatlearning.customer.service;

import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.greatlearning.customer.entity.Customer;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@Repository
public class CustomerServiceImpl implements CustomerService {

	private SessionFactory sessionFactory;

	// create session
	private Session session;

	@Autowired
	CustomerServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}

	}

	@Transactional
	public List<Customer> findAll() {
		// Session session;
		//
		// try {
		// session = sessionFactory.getCurrentSession();
		// } catch (HibernateException e) {
		// session = sessionFactory.openSession();
		// }
		Transaction tx = session.beginTransaction();

		// find all the records from the database table
		List<Customer> Customers = session.createQuery("from Customer").list();

		tx.commit();

		return Customers;
	}

	@Transactional
	public Customer findById(int id) {

		Customer customer = new Customer();

		// Session session;
		//
		// try {
		// session = sessionFactory.getCurrentSession();
		// } catch (HibernateException e) {
		// session = sessionFactory.openSession();
		// }
		Transaction tx = session.beginTransaction();

		// find record with Id from the database table
		customer = session.get(Customer.class, id);

		tx.commit();

		return customer;
	}

	@Transactional
	public void save(Customer theCustomer) {

		// Session session;
		//
		// try {
		// session = sessionFactory.getCurrentSession();
		// } catch (HibernateException e) {
		// session = sessionFactory.openSession();
		// }
		Transaction tx = session.beginTransaction();

		// save transaction
		session.saveOrUpdate(theCustomer);

		tx.commit();

	}

	@Transactional
	public void deleteById(int id) {

		// Session session;
		//
		// try {
		// session = sessionFactory.getCurrentSession();
		// } catch (HibernateException e) {
		// session = sessionFactory.openSession();
		// }
		Transaction tx = session.beginTransaction();

		// get transaction
		Customer customer = session.get(Customer.class, id);

		// delete record
		session.delete(customer);

		tx.commit();

	}

	// print the loop
	@Transactional
	public void print(List<Customer> customer) {

		for (Customer b : customer) {
			System.out.println(b);
		}
	}

	
}