package com.device.util.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.StaleObjectStateException;

import com.device.util.Logger;
import com.device.util.PeakSessionFactory;


public class HibernateSessionRequestFilter implements Filter {
	private static final Logger log = new Logger();

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		int factoryCount = PeakSessionFactory.getFactoryCount();
		boolean isFilter = true;
		try {

			HttpServletRequest converRequest = (HttpServletRequest) request;

			if (converRequest.getServletPath().indexOf(
					"message2_authMessage.sip") > -1) {
				isFilter = false;
			}
			log.debug("Starting a database transaction "
					+ converRequest.getServletPath());
			if (isFilter) {
				for (int i = 0; i < factoryCount; i++) {
					PeakSessionFactory.instance().getCurrentSession(i)
							.beginTransaction();
				}
			}

			chain.doFilter(request, response);

			// Commit and cleanup
			log.debug("Committing the database transaction");
			if (isFilter) {
				for (int i = 0; i < factoryCount; i++) {
					PeakSessionFactory.instance().getCurrentSession(i)
							.getTransaction().commit();
				}
			}

		} catch (StaleObjectStateException staleEx) {
			log
					.error("This interceptor does not implement optimistic concurrency control!");
			log
					.error("Your application will not work until you add compensation actions!");

			throw staleEx;
		} catch (Throwable ex) {
			// Rollback only
			ex.printStackTrace();
			try {
				for (int i = 0; i < factoryCount; i++) {
					if (PeakSessionFactory.instance().getCurrentSession(i)
							.getTransaction().isActive()) {
						log
								.debug("Trying to rollback database transaction after exception");
						if (isFilter) {
							PeakSessionFactory.instance().getCurrentSession(i)
									.getTransaction().rollback();
						}
					}
				}
			} catch (Throwable rbEx) {
				log.error("Could not rollback transaction after exception!",
						rbEx);
			}

			// Let others handle it... maybe another interceptor for exceptions?
			throw new ServletException(ex);
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		log.debug("Initializing filter...");
		log
				.debug("Obtaining SessionFactory from static HibernateUtil singleton");

	}

	public void destroy() {
	}

}
