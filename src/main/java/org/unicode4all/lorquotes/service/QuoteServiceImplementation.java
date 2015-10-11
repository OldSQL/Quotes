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
package org.unicode4all.lorquotes.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.unicode4all.lorquotes.dao.QuoteDAO;
import org.unicode4all.lorquotes.model.Quote;

/**
 *
 * @author Unicode4all
 */
@Service
public class QuoteServiceImplementation implements QuoteService {
    
    private QuoteDAO quoteDAO;
 
    public void setQuoteDAO(QuoteDAO quoteDAO) {
        this.quoteDAO = quoteDAO;
    }
 
    @Override
    @Transactional
    public void addQuote(Quote p) {
        this.quoteDAO.addQuote(p);
    }
 
    @Override
    @Transactional
    public void updateQuote(Quote p) {
        this.quoteDAO.updateQuote(p);
    }
 
    @Override
    @Transactional
    public List<Quote> listQuotes() {
        return this.quoteDAO.listQuotes();
    }
 
    @Override
    @Transactional
    public Quote getQuotesById(int id) {
        return this.quoteDAO.getQuotesById(id);
    }
 
    @Override
    @Transactional
    public void removeQuote(int id) {
        this.quoteDAO.removeQuote(id);
    }
 
}
