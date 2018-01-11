package com.spring.henallux.bookStore.dataAccess.util;

import com.spring.henallux.bookStore.dataAccess.entity.*;

import org.springframework.stereotype.Component;

import com.spring.henallux.bookStore.Model.*;

@Component
public class ProviderConverter {
	
	public ProviderConverter(){}
	
	public Author authorEntityToAuthorModel(AuthorEntity authorEntity)
	{
		Author authorModel = new Author();
		authorModel.setAuthorID(authorEntity.getAuthorId());
		authorModel.setFirstName(authorEntity.getFirstName());
		authorModel.setName(authorEntity.getName());
		return authorModel;
	}
	public CurrentLanguage currentLanguageEntityToCurrentLanguageModel(CurrentLanguageEntity currentLanguageEntity)
	{
		CurrentLanguage currentLanguageModel = new CurrentLanguage();
		currentLanguageModel.setCurrentLanguage_id(currentLanguageEntity.getCurrentLanguage_id());
		currentLanguageModel.setNameLanguage(currentLanguageEntity.getNameLanguage());
		return currentLanguageModel;
	}
	public Customer customerEntityToCustomerModel(CustomerEntity customerEntity)
	{
		Customer customerModel = new Customer();
		customerModel.setBirthDate(customerEntity.getBirthDate());
		customerModel.setCity(customerEntity.getCity());
		customerModel.setCountry(customerEntity.getCountry());
		customerModel.setCustomer_id(customerEntity.getCustomer_id());
		customerModel.setEmail(customerEntity.getEmail());
		customerModel.setFirstName(customerEntity.getFirstName());
		customerModel.setName(customerEntity.getName());
		customerModel.setPassword(customerEntity.getPassword());
		customerModel.setPhoneNumber(customerEntity.getPhoneNumber());
		customerModel.setPostalCode(customerEntity.getPostalCode());
		customerModel.setStreet(customerEntity.getStreet());
		customerModel.setStreetNumber(customerEntity.getStreetNumber());
		return customerModel;
	}
	public OrderCustomer orderCustomerEntityToOrderCustomerModel(OrderCustomerEntity orderCustomerEntity)
	{
		OrderCustomer orderCustomerModel = new OrderCustomer();
		orderCustomerModel.setCustomer_id(customerEntityToCustomerModel(orderCustomerEntity.getCustomer_id()));
		orderCustomerModel.setOrderCustomer_id(orderCustomerEntity.getOrderCustomer_id());
		orderCustomerModel.setOrderCustomerDate(orderCustomerEntity.getOrderCustomerDate());
		return orderCustomerModel;
	}
	public CommandLine commandLineEntityToCommandLineModel(CommandLineEntity commandLineEntity)
	{
		CommandLine commandLineModel = new CommandLine();
		commandLineModel.setCommandLine_id(commandLineEntity.getCommandLine_id());
		commandLineModel.setBook(bookEntityToBookModel(commandLineEntity.getBook()));
		commandLineModel.setQuantity(commandLineEntity.getQuantity());
		commandLineModel.setOrderCustomer(orderCustomerEntityToOrderCustomerModel(commandLineEntity.getOrderCustomer()));
		return commandLineModel;
	}
	public Book bookEntityToBookModel(BookEntity bookEntity)
	{
		Book BookModel = new Book();
		BookModel.setAuthor(authorEntityToAuthorModel(bookEntity.getAuthor()));
		BookModel.setCategory(categoryEntityToCategoryModel(bookEntity.getCategory()));
		BookModel.setHeight(bookEntity.getHeight());
		BookModel.setIsbn(bookEntity.getIsbn());
		BookModel.setNumberOfPages(bookEntity.getNumberOfPages());
		BookModel.setPrice(bookEntity.getPrice());		
		BookModel.setPublicationDate(bookEntity.getPublicationDate());;
		BookModel.setPublishingHouse(publishingHouseEntityToPublishingHouseModel(bookEntity.getPublishingHouse()));
		BookModel.setStock(bookEntity.getStock());
		BookModel.setSummary(bookEntity.getSummary());
		BookModel.setThickness(bookEntity.getThickness());
		BookModel.setWeight(bookEntity.getWeight());
		BookModel.setWidth(bookEntity.getWidth());
		BookModel.setTypeOfBook(bookEntity.getTypeOfBook());
		BookModel.setFileSize(bookEntity.getFileSize());
		BookModel.setExtension(bookEntity.getExtension());
		return BookModel;	
	}
	public Category categoryEntityToCategoryModel(CategoryEntity categoryEntity) {
		Category categoryModel  = new Category();
		categoryModel.setCategory_id(categoryEntity.getCategory_id());
		return categoryModel;
	}
	public LanguageTranslationTitleOfBook languageTranslationTitleOfBookEntityToLanguageTranslationTitleOfBookModel(LanguageTranslationTitleOfBookEntity languageTranslationTitleOfBookEntity)
	{
		LanguageTranslationTitleOfBook languageTranslationTitleOfBookModel = new LanguageTranslationTitleOfBook();
		languageTranslationTitleOfBookModel.setLanguageTranslationTitleOfBook_id(languageTranslationTitleOfBookEntity.getLanguageTranslationTitleOfBookid());
		languageTranslationTitleOfBookModel.setTranslationTitleOfBook(languageTranslationTitleOfBookEntity.getTranslationTitleOfBook());
		languageTranslationTitleOfBookModel.setBook_id(bookEntityToBookModel(languageTranslationTitleOfBookEntity.getBook_id()));
		languageTranslationTitleOfBookModel.setCurrentLanguage_id(currentLanguageEntityToCurrentLanguageModel(languageTranslationTitleOfBookEntity.getCurrentLanguage_id()));
		return languageTranslationTitleOfBookModel;
	}
	public PublishingHouse publishingHouseEntityToPublishingHouseModel(PublishingHouseEntity publishingHouseEntity)
	{
		PublishingHouse publishingHouseModel = new PublishingHouse();
		publishingHouseModel.setPublishingHouse_id(publishingHouseEntity.getPublishingHouse_id());
		publishingHouseModel.setWording(publishingHouseEntity.getWording());
		return publishingHouseModel;
	}
	public LanguageTranslationWordingOfCategory languageTranslationWordingOfCategoryEntiyToLanguageTranslationWordingOfCategoryModel(LanguageTranslationWordingOfCategoryEntity languageTranslationWordingOfCategoryEntity)
	{
		LanguageTranslationWordingOfCategory languageTranslationWordingOfCategoryModel = new LanguageTranslationWordingOfCategory();
		languageTranslationWordingOfCategoryModel.setLanguageTranslationWordingOfCategory_id(languageTranslationWordingOfCategoryEntity.getLanguageTranslationWordingOfCategory_id());
		languageTranslationWordingOfCategoryModel.setTranslationWordingOfCategory(languageTranslationWordingOfCategoryEntity.getTranslationWordingOfCategory());
		languageTranslationWordingOfCategoryModel.setCategory_id(categoryEntityToCategoryModel(languageTranslationWordingOfCategoryEntity.getCategory_id()));
		languageTranslationWordingOfCategoryModel.setCurrentLanguage_id(currentLanguageEntityToCurrentLanguageModel(languageTranslationWordingOfCategoryEntity.getCurrentLanguage_id()));
		return languageTranslationWordingOfCategoryModel;
	}
	public Promotion promotionEntityToPromotionModel(PromotionEntity promotionEntity)
	{
		Promotion promotionModel = new Promotion();
		promotionModel.setBook_id(bookEntityToBookModel(promotionEntity.getBook_id()));
		promotionModel.setEndDate(promotionEntity.getEndDate());
		promotionModel.setPercentage(promotionEntity.getPercentage());
		promotionModel.setPromotion_id(promotionEntity.getPromotion_id());
		promotionModel.setStartDate(promotionEntity.getStartDate());
		promotionModel.setSummary(promotionEntity.getSummary());
		return promotionModel;
	}
	
	public AuthorEntity authorModelToAuthorEntity(Author authorModel)
	{
		AuthorEntity authorEntity = new AuthorEntity();
		authorEntity.setAuthorID(authorModel.getAuthorID());
		authorEntity.setFirstName(authorModel.getFirstName());
		authorEntity.setName(authorModel.getName());
		return authorEntity;
	}
	public CurrentLanguageEntity currentLanguageEntityToCurrentLanguageModel(CurrentLanguage currentLanguageModel)
	{
		CurrentLanguageEntity currentLanguageEntity = new CurrentLanguageEntity();
		currentLanguageEntity.setCurrentLanguage_id(currentLanguageModel.getCurrentLanguage_id());
		currentLanguageEntity.setNameLanguage(currentLanguageModel.getNameLanguage());
		return currentLanguageEntity;
	}
	public CustomerEntity customerModelToCustomerEntity(Customer customerModel)
	{
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setBirthDate(customerModel.getBirthDate());
		customerEntity.setCity(customerModel.getCity());
		customerEntity.setCountry(customerModel.getCountry());
		customerEntity.setCustomer_id(customerModel.getCustomer_id());
		customerEntity.setEmail(customerModel.getEmail());
		customerEntity.setFirstName(customerModel.getFirstName());
		customerEntity.setName(customerModel.getName());
		customerEntity.setPassword(customerModel.getPassword());
		customerEntity.setPhoneNumber(customerModel.getPhoneNumber());
		customerEntity.setPostalCode(customerModel.getPostalCode());
		customerEntity.setStreet(customerModel.getStreet());
		customerEntity.setStreetNumber(customerModel.getStreetNumber());
		return customerEntity;
	}
	public OrderCustomerEntity orderCustomerModelToOrderCustomerEntity(OrderCustomer orderCustomerModel)
	{
		OrderCustomerEntity orderCustomerEntity = new OrderCustomerEntity();
		orderCustomerEntity.setCustomer_id(customerModelToCustomerEntity(orderCustomerModel.getCustomer_id()));
		orderCustomerEntity.setOrderCustomer_id(orderCustomerModel.getOrderCustomer_id());
		orderCustomerEntity.setOrderCustomerDate(orderCustomerModel.getOrderCustomerDate());
		return orderCustomerEntity;
	}
	public CommandLineEntity commandLineModelToCommandLineEntity(CommandLine commandLineModel)
	{
		CommandLineEntity commandLineEntity = new CommandLineEntity();
		commandLineEntity.setCommandLine_id(commandLineModel.getCommandLine_id());
		commandLineEntity.setBook(bookModelToBookEntity(commandLineModel.getBook()));
		commandLineEntity.setQuantity(commandLineModel.getQuantity());
		commandLineEntity.setOrderCustomer(orderCustomerModelToOrderCustomerEntity(commandLineModel.getOrderCustomer()));
		return commandLineEntity;
	}
	public BookEntity bookModelToBookEntity(Book bookModel)
	{
		BookEntity BookEntity = new BookEntity();
		BookEntity.setAuthor(authorModelToAuthorEntity(bookModel.getAuthor()));
		BookEntity.setCategory(categoryModelToCategoryEntity(bookModel.getCategory()));
		BookEntity.setHeight(bookModel.getHeight());
		BookEntity.setIsbn(bookModel.getIsbn());
		BookEntity.setNumberOfPages(bookModel.getNumberOfPages());
		BookEntity.setPrice(bookModel.getPrice());		
		BookEntity.setPublicationDate(bookModel.getPublicationDate());;
		BookEntity.setPublishingHouse(publishingHouseModelToPublishingHouseEntity(bookModel.getPublishingHouse()));
		BookEntity.setStock(bookModel.getStock());
		BookEntity.setSummary(bookModel.getSummary());
		BookEntity.setThickness(bookModel.getThickness());
		BookEntity.setWeight(bookModel.getWeight());
		BookEntity.setWidth(bookModel.getWidth());
		BookEntity.setTypeOfBook(bookModel.getTypeOfBook());
		BookEntity.setFileSize(bookModel.getFileSize());
		BookEntity.setExtension(bookModel.getExtension());
		return BookEntity;	
	}
	public CategoryEntity categoryModelToCategoryEntity(Category categoryModel) {
		CategoryEntity categoryEntity  = new CategoryEntity();
		categoryEntity.setCategory_id(categoryModel.getCategory_id());
		return categoryEntity;
	}
	public LanguageTranslationTitleOfBookEntity languageTranslationTitleOfBookModelToLanguageTranslationTitleOfBookEntity(LanguageTranslationTitleOfBook languageTranslationTitleOfBookModel)
	{
		LanguageTranslationTitleOfBookEntity languageTranslationTitleOfBookEntity = new LanguageTranslationTitleOfBookEntity();
		languageTranslationTitleOfBookEntity.setLanguageTranslationTitleOfBookid(languageTranslationTitleOfBookModel.getLanguageTranslationTitleOfBook_id());
		languageTranslationTitleOfBookEntity.setTranslationTitleOfBook(languageTranslationTitleOfBookModel.getTranslationTitleOfBook());
		languageTranslationTitleOfBookEntity.setBook_id(bookModelToBookEntity(languageTranslationTitleOfBookModel.getBook_id()));
		languageTranslationTitleOfBookEntity.setCurrentLanguage_id(currentLanguageEntityToCurrentLanguageModel(languageTranslationTitleOfBookModel.getCurrentLanguage_id()));
		return languageTranslationTitleOfBookEntity;
	}
	public PublishingHouseEntity publishingHouseModelToPublishingHouseEntity(PublishingHouse publishingHouseModel)
	{
		PublishingHouseEntity publishingHouseEntity = new PublishingHouseEntity();
		publishingHouseEntity.setPublishingHouse_id(publishingHouseModel.getPublishingHouse_id());
		publishingHouseEntity.setWording(publishingHouseModel.getWording());
		return publishingHouseEntity;
	}
	public LanguageTranslationWordingOfCategoryEntity languageTranslationWordingOfCategoryModelToLanguageTranslationWordingOfCategoryEntity(LanguageTranslationWordingOfCategory languageTranslationWordingOfCategoryModel)
	{
		LanguageTranslationWordingOfCategoryEntity languageTranslationWordingOfCategoryEntity = new LanguageTranslationWordingOfCategoryEntity();
		languageTranslationWordingOfCategoryEntity.setLanguageTranslationWordingOfCategory_id(languageTranslationWordingOfCategoryModel.getLanguageTranslationWordingOfCategory_id());
		languageTranslationWordingOfCategoryEntity.setTranslationWordingOfCategory(languageTranslationWordingOfCategoryModel.getTranslationWordingOfCategory());
		languageTranslationWordingOfCategoryEntity.setCategory_id(categoryModelToCategoryEntity(languageTranslationWordingOfCategoryModel.getCategory_id()));
		languageTranslationWordingOfCategoryEntity.setCurrentLanguage_id(currentLanguageEntityToCurrentLanguageModel(languageTranslationWordingOfCategoryModel.getCurrentLanguage_id()));
		return languageTranslationWordingOfCategoryEntity;
	}
	public PromotionEntity promotionModelToPromotionEntity(Promotion promotionModel)
	{
		PromotionEntity promotionEntity = new PromotionEntity();
		promotionEntity.setBook_id(bookModelToBookEntity(promotionModel.getBook_id()));
		promotionEntity.setEndDate(promotionModel.getEndDate());
		promotionEntity.setPercentage(promotionModel.getPercentage());
		promotionEntity.setPromotion_id(promotionModel.getPromotion_id());
		promotionEntity.setStartDate(promotionModel.getStartDate());
		promotionEntity.setSummary(promotionModel.getSummary());
		return promotionEntity;
	}
}
