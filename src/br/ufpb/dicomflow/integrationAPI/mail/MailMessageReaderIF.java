/*
 * 	This file is part of DicomFlow.
 * 
 * 	DicomFlow is free software: you can redistribute it and/or modify
 * 	it under the terms of the GNU General Public License as published by
 * 	the Free Software Foundation, either version 3 of the License, or
 * 	(at your option) any later version.
 * 
 * 	This program is distributed in the hope that it will be useful,
 * 	but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 	GNU General Public License for more details.
 * 
 * 	You should have received a copy of the GNU General Public License
 * 	along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package br.ufpb.dicomflow.integrationAPI.mail;

import java.util.List;

import javax.mail.Message;
import javax.mail.Session;

public interface MailMessageReaderIF {
	
	public static final int SMTP_MESSAGE_STRATEGY = 1;

	public void openFolder(Session session);
	
	public List<Message> getMessages(FilterIF filter);
	
	public void closeFolder();
	
	//TODO getMesssages em lotes para limitações de ambiente
	
	public int getType();

}
