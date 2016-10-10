package org.ocp.metier;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.ocp.dao.MessageRepository;
import org.ocp.entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageMetierImpl implements MessageMetier{
	@Autowired
	private MessageRepository messageRepository;
	
	@Override
	public Message sendMessage(Message m) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		String strDate = dateFormat.format(date);
		String strTime = timeFormat.format(date);
		m.setEtat_message(0);
		m.setDate_message(strDate);
		m.setHeure_message(strTime);
		messageRepository.save(m);
		return m;
	}

	@Override
	public boolean deleteMessage(int idMessage) {
		messageRepository.delete(idMessage);
		return true;
	}

	@Override
	public List<Message> getMessagesByCodeUser(String codeUser) {
		return messageRepository.findMessagesByCode(codeUser);
	}

	@Override
	public Message getMessageById(int idMessage) {
		return messageRepository.findOne(idMessage);
	}

	@Override
	public void changeStat(Message m) {
		Message message = messageRepository.findOne(m.getId_message());
		message.setEtat_message(1);
		messageRepository.saveAndFlush(message);
	}

	@Override
	public List<Message> getMessagesByState(int stateMessage) {
		return messageRepository.findMessagesByState(stateMessage);
	}

	@Override
	public List<Message> getMessagesByCodeUserEn(String codeUser) {
		return messageRepository.findMessagesByCodeEnv(codeUser);
	}

	@Override
	public List<Message> findMessagesByStateAndSend(int state, String codeUser) {
		return messageRepository.findMessagesByStateAndSend(state, codeUser);
	}
	
	
}
