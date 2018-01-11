package com.spring.henallux.bookStore.dataAccess.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.henallux.bookStore.dataAccess.entity.AuthorEntity;
import com.spring.henallux.bookStore.dataAccess.repository.AuthorRepository;
import com.spring.henallux.bookStore.dataAccess.util.ProviderConverter;
import com.spring.henallux.bookStore.Model.Author;

@Service
@Transactional
public class AuthorDAO {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private ProviderConverter converter = new ProviderConverter();

    public ArrayList<Author> getAllAuthors()
    {
        ArrayList<Author> authors = new ArrayList<Author>();
        List<AuthorEntity> authorEntities = authorRepository.findAll();
        for(AuthorEntity authorEntity: authorEntities)
        {
            authors.add(converter.authorEntityToAuthorModel(authorEntity));
        }
        return authors;
    }
}