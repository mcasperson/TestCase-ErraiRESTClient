package com.test.erraiclient.client.local;

import javax.annotation.PostConstruct;

import org.jboss.errai.bus.client.api.ErrorCallback;
import org.jboss.errai.bus.client.api.Message;
import org.jboss.errai.bus.client.api.RemoteCallback;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.errai.ioc.client.api.EntryPoint;

import com.test.restserver.RESTChildObject;
import com.test.restserver.RESTObject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Example code showing how to use Errai-JAXRS.
 * 
 * @author Christian Sadilek <csadilek@redhat.com>
 */
@EntryPoint
public class App
{
	private static final String REST_SERVER = "http://localhost:8080/TestRESTServer/rest";

	final RemoteCallback<RESTObject<RESTChildObject<Integer>>> callback = new RemoteCallback<RESTObject<RESTChildObject<Integer>>>()
	{
		@Override
		public void callback(final RESTObject<RESTChildObject<Integer>> restObject)
		{
			Window.alert("Success!");
		}
	};
	
	final RemoteCallback<String> stringCallback = new RemoteCallback<String>()
	{
		@Override
		public void callback(final String restObject)
		{
			Window.alert("Success!");
		}
	};

	final ErrorCallback errorCallback = new ErrorCallback()
	{

		@Override
		public boolean error(final Message message, final Throwable throwable)
		{
			throwable.printStackTrace();
			Window.alert("Failure!");			
			return true;
		}
	};

	@PostConstruct
	public void init()
	{
		/* Init the REST service */
		RestClient.setApplicationRoot(REST_SERVER);
		RestClient.setJacksonMarshallingActive(true);

		final Button go = new Button("Go", new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent clickEvent)
			{
				final REST restMethod = RestClient.create(REST.class, callback, errorCallback);
				restMethod.printMessage();
			}
		});

		RootPanel.get().add(go);
	}
}