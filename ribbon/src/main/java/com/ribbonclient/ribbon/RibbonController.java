package com.ribbonclient.ribbon;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;*/
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;





@RestController
//@RibbonClient(name = "MessageInstance", configuration = MessageInstanceConfiguration.class)
public class RibbonController {

	
	
	//@LoadBalanced
	@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Autowired
	RestTemplate restTemplate;

	
	
	@GetMapping("/getmsg")
	public ResponseEntity<List> getMsgsFromSerivce() throws IOException {
		double i=Math.random()*100;
		ResponseEntity<List> responseEntity;
		if(i<50){
			responseEntity = restTemplate.getForEntity(
				"http://localhost:8081/getmessages",List.class);
		}else{
			responseEntity = restTemplate.getForEntity(
					"http://localhost:8082/getmessages",List.class);
		}
		return  responseEntity;
	}

	@RequestMapping(value = "/getmsg/{id}", method = RequestMethod.GET)
	public MessageBean getMsgFromService(@PathVariable("id")  int id) {
		MessageBean response;
		double i=Math.random()*100;
		if(i<50){
		 response= this.restTemplate.getForObject("http://localhost:8081/getmessages/"+id, MessageBean.class);
		}
		else{
			response= this.restTemplate.getForObject("http://localhost:8082/getmessages/"+id, MessageBean.class);
		}
		return response;
	}
	
	@PostMapping("/msg")
	public MessageBean createMessage(@RequestBody MessageBean message){
		
		MessageBean response;
		double i=Math.random()*100;
		if(i>50){
		response = this.restTemplate.postForObject("http://localhost:8082/message", message, MessageBean.class);
		}
		else{
			response = this.restTemplate.postForObject("http://localhost:8081/message", message, MessageBean.class);
		}
		return response;
	}
}
