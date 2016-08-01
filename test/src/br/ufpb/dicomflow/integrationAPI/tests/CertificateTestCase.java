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
package br.ufpb.dicomflow.integrationAPI.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.junit.Test;

import br.ufpb.dicomflow.integrationAPI.exceptions.ServiceCreationException;
import br.ufpb.dicomflow.integrationAPI.mail.FilterIF;
import br.ufpb.dicomflow.integrationAPI.mail.MailAuthenticatorIF;
import br.ufpb.dicomflow.integrationAPI.mail.MailContentBuilderIF;
import br.ufpb.dicomflow.integrationAPI.mail.MailHeadBuilderIF;
import br.ufpb.dicomflow.integrationAPI.mail.impl.SMTPAuthenticator;
import br.ufpb.dicomflow.integrationAPI.mail.impl.SMTPContentBuilder;
import br.ufpb.dicomflow.integrationAPI.mail.impl.SMTPFilter;
import br.ufpb.dicomflow.integrationAPI.mail.impl.SMTPHeadBuilder;
import br.ufpb.dicomflow.integrationAPI.mail.impl.SMTPSender;
import br.ufpb.dicomflow.integrationAPI.main.ServiceFactory;
import br.ufpb.dicomflow.integrationAPI.main.ServiceProcessor;
import br.ufpb.dicomflow.integrationAPI.message.xml.CertificateRequest;
import br.ufpb.dicomflow.integrationAPI.message.xml.ServiceIF;

public class CertificateTestCase extends GenericTestCase {
	
	@Test
	public static void testSendCertificateRequest() {
		
		
		CertificateRequest certRequest = (CertificateRequest) ServiceFactory.createService(ServiceIF.CERTIFICATE_REQUEST);
		certRequest.setDomain("dicomflow.com");
		certRequest.setPort(8443);
		
		File attachment = new File(certDir+File.separator+"dicommessage.crt");
						
			
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "25");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.socketFactory.fallback", "false");

        MailAuthenticatorIF smtpAuthenticatorStrategy =  new SMTPAuthenticator("protocolointegracao@gmail.com", "pr0t0c0l0ap1");
        MailHeadBuilderIF smtpHeadStrategy = new SMTPHeadBuilder("protocolointegracao@gmail.com", "protocolointegracao@gmail.com", "dicomflow.com");
        MailContentBuilderIF smtpSimpleContentStrategy = new SMTPContentBuilder();
        
        SMTPSender sender = new SMTPSender();
        sender.setProperties(props);
        sender.setAuthenticatorBuilder(smtpAuthenticatorStrategy);
        sender.setHeadBuilder(smtpHeadStrategy);
        sender.setContentBuilder(smtpSimpleContentStrategy);
        
        messageID = sender.send(certRequest, attachment);
        

	}
	
	@Test
	public static void testReceiveCertificateRequest() {                

		
		try {
			//System.out.println("MESSAGE-ID : " + messageID);
			FilterIF filter = new SMTPFilter();
			filter.setIdMessage(messageID+"@dicomflow.com");
			
			List<ServiceIF> services = ServiceProcessor.receiveServices(null, null, null, null, filter);
			Iterator<ServiceIF> iterator = services.iterator();
			while (iterator.hasNext()) {
				ServiceIF serviceIF = (ServiceIF) iterator.next();
				//System.out.println("MessageID:" +serviceIF.getMessageID() + "Name: " + serviceIF.getName() + "Action: " +serviceIF.getAction());
			}
			
			List<byte[]> attachs = ServiceProcessor.receiveAttachs(null, null, null, null, filter);
			Iterator<byte[]> iterator2 = attachs.iterator();
			while (iterator2.hasNext()) {
				byte[] bs = (byte[]) iterator2.next();
				//System.out.println("BYTE ARRAY LEGTH : " + bs.length);
				File cert = new File(certDir+File.separator+"attach.crt");
				try {
					if(!cert.exists()){
						cert.createNewFile();
					}
					FileOutputStream fos = new FileOutputStream(cert);
					fos.write(bs);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
			}
		} catch (ServiceCreationException e) {
			fail();
		}
	}
	
}
