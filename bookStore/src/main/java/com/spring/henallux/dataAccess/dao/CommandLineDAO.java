 package com.spring.henallux.dataAccess.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.henallux.Model.CommandLine;
import com.spring.henallux.Model.Customer;
import com.spring.henallux.dataAccess.entity.CommandLineEntity;
import com.spring.henallux.dataAccess.entity.CustomerEntity;
import com.spring.henallux.dataAccess.repository.CommandLineRepository;
import com.spring.henallux.dataAccess.util.ProviderConverter;
import com.spring.henallux.exception.CustomerAlreadyExistException;

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
