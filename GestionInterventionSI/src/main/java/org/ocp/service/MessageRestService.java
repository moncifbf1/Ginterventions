package org.ocp.service;

import java.util.List;

import org.ocp.entities.Message;
import org.ocp.metier.MessageMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageRestService {
	@Autowired
	private MessageMetier messageMetier;
	
	@RequestMapping(value="/sendMessage",method=RequestMethod.PUT)
	public @ResponseBody Message sendMessage(@RequestBody Message m) {
		return messageMetier.sendMessage(m);
	}
	
	@RequestMapping(value="/deleteMessage/{idMessage}",method=RequestMethod.DELETE)
	public boolean deleteMessage(@PathVariable("idMessage") int idMessage) {
		return messageMetier.deleteMessage(idMessage);
	}
	
	@RequestMapping(value="/getMessages/{username:.+}")
	public List<Message> getMessagesByCodeUser(@PathVariable("username") String username) {
		return messageMetier.getMessagesByCodeUser(username);
	}
	
	@RequestMapping(value="/getMessagesEnv/{username:.+}")
	public List<Message> getMessagesByCodeUserEn(@PathVariable("username") String codeUser) {
		return messageMetier.getMessagesByCodeUserEn(codeUser);
	}

	@RequestMapping(value="/getMessage/{idMessage}")
	public Message getMessageById(@PathVariable("idMessage") int idMessage) {
		return messageMetier.getMessageById(idMessage);
	}
	
	@RequestMapping(value="/changeState",method=RequestMethod.PUT)
	public void changeStat(@RequestBody Message m) {
		messageMetier.changeStat(m);
	}
	
	@RequestMapping(value="/getMessagesByState/{state:.+}")
	public List<Message> getMessagesByState(@PathVariable("state") int stateMessage) {
		return messageMetier.getMessagesByState(stateMessage);
	}
	
	@RequestMapping(value="/getMessagesByStateSend/{state:.+}/{code:.+}")
	public List<Message> findMessagesByStateAndSend(@PathVariable("state") int state,@PathVariable("code") String code) {
		return messageMetier.findMessagesByStateAndSend(state, code);
	}
	
	
}
