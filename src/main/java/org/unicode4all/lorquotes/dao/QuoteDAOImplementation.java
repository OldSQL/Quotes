/*
 * Copyright 2015 Unicode4all.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.unicode4all.lorquotes.dao;

import java.util.List;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.unicode4all.lorquotes.model.Quote;

/**
 *
 * @author Unicode4all
 */
@Repository
public class QuoteDAOImplementation implements QuoteDAO {
    private static final Logger logger = Logger.getLogger(QuoteDAOImplementation.class.getName());
 
    private SessionFactory sessionFactory;
     
    public void setSessionFactory(SessionFactory sf){
        this.sessionFactory = sf;
    }
 
    @Override
    public void addQuote(Quote q) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(q);
        logger.info("Quote saved successfully, Quote Details="+q);
    }
 
    @Override
    public void updateQuote(Quote q) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(q);
        logger.info("Quote updated successfully, Quote Details="+q);
    }
 
    @SuppressWarnings("unchecked")
    @Override
    public List<Quote> listQuotes() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Quote> quotesList = session.createQuery("from Quote").list();
        for(Quote p : quotesList){
            logger.info("Person List::"+p);
        }
        return quotesList;
    }
 
    @Override
    public Quote getQuotesById(int id) {
        Session session = this.sessionFactory.getCurrentSession();      
        Quote q = (Quote) session.load(Quote.class, new Integer(id));
        logger.info("Quote loaded successfully, Quote details="+q);
        return q;
    }
 
    @Override
    public void removeQuote(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Quote q = (Quote) session.load(Quote.class, new Integer(id));
        if(null != q){
            session.delete(q);
        }
        logger.info("Quote deleted successfully, Quote details="+q);
    }
 
}
