package com.spring.henallux.bookStore.dataAccess.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.henallux.bookStore.Model.Promotion;
import com.spring.henallux.bookStore.dataAccess.entity.PromotionEntity;
import com.spring.henallux.bookStore.dataAccess.repository.PromotionRepository;
import com.spring.henallux.bookStore.dataAccess.util.ProviderConverter;

@Service
@Transactional
public class PromotionDAO {

    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private ProviderConverter converter = new ProviderConverter();
    @Autowired
    private SessionFactory sessionFactory;

    public ArrayList<Promotion> getCurrentPromotions (Date date)
    {
        ArrayList<Promotion> promotionsModel = new ArrayList<Promotion>();
        Promotion promotionModel;
        Collection<PromotionEntity> promotionsEntity;


        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Query query = session.getNamedQuery("findCurrentPromotions").setDate("date", date);
        promotionsEntity = query.list();

        for(PromotionEntity promotionEntity : promotionsEntity)
        {
            promotionModel = converter.promotionEntityToPromotionModel(promotionEntity);
            promotionsModel.add(promotionModel);
        }

        session.getTransaction().commit();
        return promotionsModel;
    }
}
