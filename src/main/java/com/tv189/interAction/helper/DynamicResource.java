package com.tv189.interAction.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DynamicResource
{
  private File file;
  private long lastModified;
  private static Map<String, DynamicResource> resources = new HashMap();
  private Properties prop = new Properties();
  
  public DynamicResource(String resource)
  {
    try
    {
      Object o = getClass().getClassLoader();
      if(null == o){
    	  System.out.println("**** getClass().getClassLoader() is null");
      }
      URI fileUri = getClass().getClassLoader().getResource(resource).toURI();
      this.file = new File(fileUri);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    load();
  }
  
  public static DynamicResource getResource(String resource)
  {
    if (resources.containsKey(resource)) {
      return (DynamicResource)resources.get(resource);
    }
    synchronized (resources)
    {
      if (resources.containsKey(resource)) {
        return (DynamicResource)resources.get(resource);
      }
      resources.put(resource, new DynamicResource(resource));
    }
    return (DynamicResource)resources.get(resource);
  }
  
  private void load()
  {
    FileInputStream is = null;
    try
    {
      if ((this.file == null) || (!this.file.exists())) {
        return;
      }
      Properties prop = new Properties();
      is = new FileInputStream(this.file);
      prop.load(is);
      this.prop = prop;
      this.lastModified = this.file.lastModified();
    }
    catch (IOException e)
    {
      e.printStackTrace();
      if (is != null) {
        try
        {
          is.close();
        }
        catch (IOException localIOException2) {}
      }
    }
    finally
    {
      if (is != null) {
        try
        {
          is.close();
        }
        catch (IOException localIOException3) {}
      }
    }
  }
  
  private synchronized void check()
  {
    if ((this.file == null) || (!this.file.exists())) {
      return;
    }
    if (this.lastModified != this.file.lastModified()) {
      load();
    }
  }
  
  public Properties getProperties()
  {
    check();
    return this.prop;
  }
  
  public String getString(String key)
  {
    check();
    return this.prop.getProperty(key);
  }
  
  public String getString(String key, String def)
  {
    check();
    return this.prop.getProperty(key, def);
  }
}
