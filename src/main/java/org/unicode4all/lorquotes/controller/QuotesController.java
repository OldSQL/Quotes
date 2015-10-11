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
package org.unicode4all.lorquotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.unicode4all.lorquotes.model.Quote;
import org.unicode4all.lorquotes.service.QuoteService;
 

 
@Controller
public class QuotesController {
     
    private QuoteService quoteService;
     
    @Autowired(required=true)
    @Qualifier(value="quoteService")
    public void setQuoteService(QuoteService qs){
        this.quoteService = qs;
    }
     
    @RequestMapping(value = "/quotes", method = RequestMethod.GET)
    public String listQuotes(Model model) {
        model.addAttribute("quote", new Quote());
        model.addAttribute("listQuotes", this.quoteService.listQuotes());
        return "quotes";
    }
     
    //For add and update quote both
    @RequestMapping(value= "/quote/add", method = RequestMethod.POST)
    public String addQuote(@ModelAttribute("quote") Quote p){
         
        if(p.getId() == 0){
            //add new quote
            this.quoteService.addQuote(p);
        }else{
            //trigger update if exists
            this.quoteService.updateQuote(p);
        }
         
        return "redirect:/quotes";
         
    }
     
    @RequestMapping("/remove/{id}")
    public String removeQuote(@PathVariable("id") int id){
         
        this.quoteService.removeQuote(id);
        return "redirect:/quotes";
    }
    
    @RequestMapping("/quotes/{id}")
    public String viewQuote(@PathVariable("id") int id, Model model){
        model.addAttribute("quote", this.quoteService.getQuotesById(id));
        return "quotes/viewquote";
    }
    
    @RequestMapping("/edit/{id}")
    public String editQuote(@PathVariable("id") int id, Model model){
        model.addAttribute("quote", this.quoteService.getQuotesById(id));
        model.addAttribute("listQuotes", this.quoteService.listQuotes());
        return "quote";
    }
}