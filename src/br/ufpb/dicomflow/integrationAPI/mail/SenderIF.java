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

import java.io.File;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

import br.ufpb.dicomflow.integrationAPI.message.xml.ServiceIF;


public interface SenderIF {
	
	public String send(ServiceIF service);
	
	public String send(ServiceIF service, File attachement);

	public String sendCipher(ServiceIF service, X509Certificate signCert, X509Certificate encryptCert, PrivateKey privateKey );
	
	public String sendCipher(ServiceIF service, File attachement, X509Certificate signCert, X509Certificate encryptCert, PrivateKey privateKey );

}
