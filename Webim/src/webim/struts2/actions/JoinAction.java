/*
 * JoinAction.java
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package webim.struts2.actions;

import org.json.JSONObject;

import webim.client.WebimClient;
import webim.client.WebimGroup;
import webim.service.WebimService;

/**
 * 加入群组: /Webim/join.do
 * 
 * @author Ery Lee <ery.lee at gmail.com>
 * @since 1.0
 */
@SuppressWarnings("serial")
public class JoinAction extends WebimAction {

	private String id;
	
	private String nick = "";
	
	private WebimGroup data = null;

	public String execute() throws Exception {
		WebimClient c = WebimService.instance().currentClient(this.ticket);
		data = WebimService.instance().getGroup(id);
		if(data == null) {
			data = WebimService.instance().newTmpGroup(id, nick);
		}
		JSONObject o = c.join(id);
		data.setCount(o.getInt(id));
		return SUCCESS;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public void setData(WebimGroup g) {
		data = g;
	}
	
	public WebimGroup getData() {
		return data;
	}

}

