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
package br.ufpb.dicomflow.integrationAPI.message.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="result")
public class Result {
	
	private String originalMessageID;
	private String timestamp;
	private Completed completed;
	
	private Data data;
	
	private List<Object> object;
	private List<URL> url;
	private List<ServiceDescriptor> serviceDescriptor;
	
	
	
	public Result(){
		this.object = new ArrayList<Object>();
		this.url = new ArrayList<URL>();
		this.serviceDescriptor = new ArrayList<ServiceDescriptor>();
	}
	
		
	public String getOriginalMessageID() {
		return originalMessageID;
	}

	public void setOriginalMessageID(String originalMessageID) {
		this.originalMessageID = originalMessageID;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public Completed getCompleted() {
		return completed;
	}

	public void setCompleted(Completed completed) {
		this.completed = completed;
	}
	

	public Data getData() {
		return data;
	}


	public void setData(Data data) {
		this.data = data;
	}


	public List<Object> getObject() {
		return object;
	}

	@XmlElementWrapper(name="objects")
	public void setObject(List<Object> object) {
		this.object = object;
	}
	
	public boolean addObject(Object object){
		return this.object.add(object);
	}


	public List<URL> getUrl() {
		return url;
	}

	@XmlElementWrapper(name="urls")
	public void setUrl(List<URL> url) {
		this.url = url;
	}
	
	public boolean addUrl(URL url){
		return this.url.add(url);
	}

	
	public List<ServiceDescriptor> getServiceDescriptor() {
		return serviceDescriptor;
	}

	@XmlElementWrapper(name="services")
	public void setServiceDescriptor(List<ServiceDescriptor> serviceDescriptor) {
		this.serviceDescriptor = serviceDescriptor;
	}
	
	public boolean addServiceDescriptor(ServiceDescriptor serviceDescriptor){
		return this.serviceDescriptor.add(serviceDescriptor);
	}
	
	

			
	
}
