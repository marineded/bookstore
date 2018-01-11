package com.spring.henallux.bookStore.dataAccess.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.henallux.bookStore.Model.CommandLine;
import com.spring.henallux.bookStore.Model.Customer;
import com.spring.henallux.bookStore.dataAccess.entity.CommandLineEntity;
import com.spring.henallux.bookStore.dataAccess.entity.CustomerEntity;
import com.spring.henallux.bookStore.dataAccess.repository.CommandLineRepository;
import com.spring.henallux.bookStore.dataAccess.util.ProviderConverter;
import com.spring.henallux.bookStore.exception.CustomerAlreadyExistException;

@Service
@Transactional
public class CommandLineDAO {

    @Autowired
    private CommandLineRepository commandLineRepository;
    @Autowired
    private ProviderConverter converter = new ProviderConverter();

    public void addCommandLine(CommandLine commandLineModel)
    {

        CommandLineEntity commandLineEntity = converter.commandLineModelToCommandLineEntity(commandLineModel);
        commandLineEntity = commandLineRepository.save(commandLineEntity);
    }
}

