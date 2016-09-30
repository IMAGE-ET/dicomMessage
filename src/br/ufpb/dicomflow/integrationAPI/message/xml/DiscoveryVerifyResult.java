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

@XmlRootElement(name="service")
public class DiscoveryVerifyResult extends Service {
	
	public static final String DISCOVERY_VERIFY_RESULT_NAME = "Discovery";
	public static final String DISCOVERY_VERIFY_RESULT_ACTION = "VerifyResult";
	public static final String DISCOVERY_VERIFY_RESULT_VERSION = "1.0";
	
	private String timezone;
	private int priority;
	
	private List<Result> result;
	
	public DiscoveryVerifyResult(){
		super.setName(DISCOVERY_VERIFY_RESULT_NAME);
		super.setAction(DISCOVERY_VERIFY_RESULT_ACTION);
		super.setVersion(DISCOVERY_VERIFY_RESULT_VERSION);
		super.setType(ServiceIF.DISCOVERY_VERIFY_RESULT);
		this.result = new ArrayList<Result>();
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public List<Result> getResult() {
		return result;
	}

	public void setResult(List<Result> result) {
		this.result = result;
	}

	public boolean addResult(Result result){
		return this.result.add(result);
	}
	
	
	
}