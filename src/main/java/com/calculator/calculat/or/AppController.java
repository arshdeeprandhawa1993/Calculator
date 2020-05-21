package com.calculator.calculat.or;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AppController {
	
	// list to save the latest 10 records
	Queue<Calculator> cl= new LinkedList<>();
	

	/**
	 * welcome page 
	 */
	@RequestMapping("/list_operation")
	public String welcome(Model model, HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		
		ArrayList<Calculator> temparr =  new ArrayList<>();
		Queue<Calculator> tempQueue = new LinkedList<>();
		
		while (!cl.isEmpty()) {
			Calculator c = cl.poll();
			temparr.add(c);
			tempQueue.add(c);
		}
		
		cl = tempQueue;
		Collections.reverse(temparr);
		
		model.addAttribute("name", name);
		model.addAttribute("list",temparr);
		return "test";
	}
	
	@MessageMapping("/chat.register")
	@SendTo("/topic/public")
	public Calculator register(@Payload Calculator calculator, SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("cl",calculator);
		return calculator;
	}
	
	@MessageMapping("/chat.send")
	@SendTo("/topic/public")
	public String sendMessage(@Payload String calculator, Model model) {
		model.addAttribute("list",cl);
		return "test";
	}
	
	@RequestMapping(value = "/list_value") 
	public String calculate(Model model, HttpServletRequest request, HttpServletResponse response) {
		String b = request.getParameter("inputName");
		String name = request.getParameter("name");
	
		if(b.equals("1")) {
			float operand1 = Float.parseFloat(request.getParameter("operand1"));
			float operand2 = Float.parseFloat(request.getParameter("operand2"));
			String operator = request.getParameter("operator");
			String op = "";
			float result = 0;
			
			try {
				if (operator.equalsIgnoreCase("add")) {
					op = "+";
					result = operand1 + operand2;
				} else if (operator.equalsIgnoreCase("sub")) {
					op = "-";
					result = operand1 - operand2;
				} else if (operator.equalsIgnoreCase("mul")) {
					op = "*";
					result = operand1 * operand2;
				} else {
					op = "/";
					result = operand1 / operand2;
				}
			} catch (Exception e) {
				String error = "err";
				model.addAttribute("err", error);
			}
			
			Calculator p  = new Calculator(name, operand1, operand2, op, result);
			
			if(cl.size()>=10) {
				
				cl.poll();
				cl.add(p);
	
			}else{
				cl.add(p);
	
			}
		}		
		
		ArrayList<Calculator> temparr =  new ArrayList<>();
		Queue<Calculator> tempQueue = new LinkedList<>();
		
		while (!cl.isEmpty()) {
			Calculator c = cl.poll();
			temparr.add(c);
			tempQueue.add(c);
		}
		
		cl = tempQueue;
		Collections.reverse(temparr);
		
		model.addAttribute("list",temparr);
		model.addAttribute("name", name);
		
		return "test"; 
	}
	

	@RequestMapping(value = "/refresh")
	public String refreshPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		model.addAttribute("name", name);
		model.addAttribute("list", cl);
		
		return "test";
	}

}
