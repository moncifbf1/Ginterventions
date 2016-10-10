package org.ocp.metier;

import java.util.List;

import org.ocp.entities.Message;

public interface MessageMetier {
	public Message sendMessage(Message m);
	public boolean deleteMessage(int idMessage);
	public List<Message> getMessagesByCodeUser(String codeUser);
	public List<Message> getMessagesByCodeUserEn(String codeUser);
	public Message getMessageById(int idMessage);
	public void changeStat(Message m);
	public List<Message> getMessagesByState(int stateMessage);
	public List<Message> findMessagesByStateAndSend(int state, String codeUser);
}
