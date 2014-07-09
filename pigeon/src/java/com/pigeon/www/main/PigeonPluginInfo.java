package com.pigeon.www.main;

import java.io.File;

import org.jivesoftware.openfire.XMPPServer;
import org.jivesoftware.openfire.component.InternalComponentManager;
import org.jivesoftware.openfire.container.Plugin;
import org.jivesoftware.openfire.container.PluginManager;
import org.jivesoftware.openfire.interceptor.InterceptorManager;

import com.pigeon.www.handler.iq.UserInfoHandler;

/**
 * pigeon插件配置
 * @author Jsming
 *
 */
public class PigeonPluginInfo implements Plugin{

	/**
     * XMPP协议
     */
    private XMPPServer server;
    private static PluginManager pluginManager;
    private InterceptorManager interceptorManager;
    private InternalComponentManager internalComponentManager ;
    
    public  PigeonPluginInfo(){
    	 interceptorManager = InterceptorManager.getInstance();
         internalComponentManager = InternalComponentManager.getInstance();
    }
    
	@Override
	public void initializePlugin(PluginManager manager, File pluginDirectory) {
		// TODO Auto-generated method stub
		pluginManager = manager; 
		server = XMPPServer.getInstance();
		server.getIQRouter().addHandler(new UserInfoHandler());
	}

	@Override
	public void destroyPlugin() {
		// TODO Auto-generated method stub
		
	}
	
	 public static PluginManager getPluginManager() {
	        return pluginManager;
	    }

	//get set方法
    public XMPPServer getServer()
    {
        return server;
    }

    public void setServer(XMPPServer server)
    {
        this.server = server;
    }
}
