package com.example.testtask.service;

import com.example.testtask.model.Book;
import com.example.testtask.repository.BookRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;
    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooksFromLabirint(String amount) {
        String sourceURL = "https://igraslov.store/shop/?products-per-page=all";
        String labirintSearchURL = "https://www.labirint.ru/search/";
        String labirintURL = "https://www.labirint.ru";
        int quantity;
        int cnt = 0;
        try {
            Document sourceDoc = Jsoup.connect(sourceURL).maxBodySize(0).timeout(500000).get();
            Elements elements = sourceDoc.select(".title");
            try {
                quantity = Math.min(Integer.parseInt(amount),elements.size());
            }
            catch (NumberFormatException e){
                quantity = elements.size();
            }
            for(var element : elements) {
                if (cnt < quantity) {
                    cnt++;
                    String title = element.text();
                    Book book = new Book();
                    book.setTitle(title);
                    try {
                        Document labirintDoc = Jsoup.connect(labirintSearchURL + title).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6").timeout(20000).get();
                        Element ref = labirintDoc.select("a.product-title-link").first();
                        if (ref != null) {
                            String link = labirintURL + ref.attr("href");
                            Document LinkedDoc = Jsoup.connect(link).userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6").timeout(20000).get();
                            Element ISBN = LinkedDoc.select(".isbn").first();
                            if (ISBN != null) {
                                book.setISBN(ISBN.text());
                            }
                            Element authors = LinkedDoc.select(".authors").first();
                            if (authors != null) {
                                book.setAuthors(authors.text());
                            }
                            Element publisher = LinkedDoc.select(".publisher").first();
                            if (publisher != null) {
                                book.setPublishers(publisher.text());
                            }
                            Element description = LinkedDoc.select("div#product-about").first();
                            if (description != null) {
                                book.setDescription(description.text().substring(0, 254));
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    bookRepository.save(book);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookRepository.findAll();
    }

}
