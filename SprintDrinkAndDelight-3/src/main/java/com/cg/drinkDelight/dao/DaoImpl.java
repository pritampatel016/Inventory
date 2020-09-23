package com.cg.drinkDelight.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.drinkDelight.entity.Vendor;

@Repository

public class DaoImpl implements DrinkDelightDao{

	@PersistenceContext
	private EntityManager em;
	
	//This function is used to fetch fetch the details of the vendor based on the vendor ID from database
	@Override
	public Vendor viewVendor(long vendorId) {
		return em.find(Vendor.class , vendorId);
	}

	//This function is used to fetch all the vendor details available in database
	@Override
	public List<Vendor> viewVendors() {
		String jpql= "from Vendor v ";
		TypedQuery<Vendor> query = em.createQuery(jpql, Vendor.class);
		return query.getResultList();
		
	}
   
	//This function is used to fetch all the vendor details based on the vendor type
	@Override
	public List<Vendor> viewVendorbyType(String VendorType) {
		String jpql = "from Vendor v where lower(v.vendorType) = lower(:vtype)";
		TypedQuery<Vendor> query = em.createQuery(jpql, Vendor.class);
		query.setParameter("vtype", VendorType);
		return query.getResultList();

	}

	//This function is going to add the vendor details in the database
	@Override
	public boolean addVendor(Vendor vendor) {
		em.persist(vendor);
		return true;
	}

	//This function is used to edit the details of the vendor from the database
	@Override
	public boolean editVendor(Vendor vendor) {
		em.merge(vendor);
		return true;
	}


}
