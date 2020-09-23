package com.cg.drinkDelight.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.drinkDelight.dao.DrinkDelightDao;
import com.cg.drinkDelight.entity.Vendor;
import com.cg.drinkDelight.exception.NoVendorException;
import com.cg.drinkDelight.util.VendorConstants;

@Service
@Transactional
public class ServiceImpl implements DrinkDelightService{
	
	@Autowired
	private DrinkDelightDao dao;
	
	//This function is used to view the particular vendor based on the vendor ID
	@Override
	public Vendor viewvendor(long vendorId) throws NoVendorException{
		
		  Vendor vendor = dao.viewVendor(vendorId); 
		  if(vendor==null ) 
			  throw new NoVendorException(VendorConstants.VENDORID_DOES_NOT_EXIST); 
		  return vendor;
		 
	}

	//This function is used to view all the vendors based on a particular vendor type
	@Override
	public List<Vendor> viewvendorbyType(String VendorType) throws NoVendorException {
		List<Vendor> plist = dao.viewVendorbyType(VendorType);
		if(plist.isEmpty())
			throw new NoVendorException(VendorConstants.VENDOR_TYPE_DOES_NOT_EXIST);
		plist.sort((v1,v2)->v1.getVendorType().compareTo(v2.getVendorType()));
		return plist;
	}

	//This function is used to view all the vendors available
	@Override
	public List<Vendor> viewvendors() throws NoVendorException {
		List<Vendor> plst = dao.viewVendors();
		if(plst.isEmpty())
			throw new NoVendorException(VendorConstants.VENDOR_DOES_NOT_EXIST);
		return plst;
	}
    
	//This function is used to add a new vendor
	@Override
	public boolean addvendor(Vendor vendor) throws NoVendorException {
		return dao.addVendor(vendor);

	}

	//This function is used to edit the details of the vendor
	@Override
	public boolean editvendor(Vendor vendor) {
		return dao.editVendor(vendor);
	}

	
	
}
