package com.jsp.Agrow.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.Agrow.dao.EquipmentDao;
import com.jsp.Agrow.dao.PaymentHistoryDao;
import com.jsp.Agrow.dao.RentalDao;
import com.jsp.Agrow.dao.UserDao;
import com.jsp.Agrow.dao.exception.EquipmentNotFound;
import com.jsp.Agrow.dao.exception.UserDoesNotExit;
import com.jsp.Agrow.dao.utils.ResponseStructure;
import com.jsp.Agrow.entity.Equipment;
import com.jsp.Agrow.entity.PaymentHistory;
import com.jsp.Agrow.entity.Rental;
import com.jsp.Agrow.entity.User;


@Service
public class RentalService {
	@Autowired
	RentalDao dao;
	
	@Autowired
	EquipmentDao edao;
	
	@Autowired
	PaymentHistoryDao pdao;
	
	@Autowired
	UserDao udao;
	
//	=================================save rental========================================
	public ResponseEntity<ResponseStructure<Rental>> saveRental(int eid,int uid,String startTime,String endTime){
		Equipment equipment=edao.fetchEquipById(eid);
		User user=udao.findUserById(uid);
		if(equipment!=null) {
			if(user!=null) {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			Rental rental=new Rental();
			rental.setStartTime(LocalDateTime.parse(startTime,formatter));
			rental.setEndTime(LocalDateTime.parse(endTime,formatter));
			rental.setEquipments(equipment);
//			payment details
			PaymentHistory paymentHistory=new PaymentHistory();
			paymentHistory.setPaymentTime(LocalDateTime.now());
			paymentHistory.setUser(user);
			
//			calaulate the cost
	        Duration duration = Duration.between(rental.getStartTime(), rental.getEndTime());
	        Double total_time=(double) duration.toMinutes();
	        Double cost=(total_time/60)*equipment.getCostPerHour();
//	        =================================================================
	        paymentHistory.setAmount(cost);
	        PaymentHistory pdb=pdao.savePayment(paymentHistory);
	        rental.setPaymentDetails(pdb);
	        Rental rdb=dao.saveRental(rental);
//	        =================================
//	        removing equipment quantity by 1
	        equipment.setQuantity(equipment.getQuantity()-1);
	        edao.saveEquipment(equipment);
	        ResponseStructure<Rental> rs=new ResponseStructure<Rental>();
	        rs.setData(rdb);
	        rs.setMessage("rental updated successfully");
	        rs.setStatus(HttpStatus.OK.value());
	        return new ResponseEntity<ResponseStructure<Rental>>(rs,HttpStatus.OK);
			}
			else {
				throw new UserDoesNotExit("No user with id :"+uid);
			}
		}
		else {
			throw new EquipmentNotFound("No equipment with id: "+eid);
		}
	}
//	=====================================================================================
	
//	================================
}
