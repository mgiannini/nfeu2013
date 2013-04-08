// Copyright (c) 2013
// Licensed under the Academic Free License 3.0

package niagara.forum.eu.async;

import javax.baja.sys.BComponent;
import javax.baja.sys.BIService;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

/**
 * @author: Matthew Giannini
 */
public final class BThreadPoolService extends BComponent implements BIService
{
  /*-
    class BThreadPoolService
    {
      properties
      {
        basic: BThreadPool
          default {[ new BThreadPool() ]}

        scheduled: BScheduledThreadPool
          default {[ new BScheduledThreadPool() ]}
      }
    }
   -*/
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $niagara.forum.eu.async.BThreadPoolService(955449098)1.0$ @*/
/* Generated Wed Mar 06 15:15:40 EST 2013 by Slot-o-Matic 2000 (c) Tridium, Inc. 2000 */

////////////////////////////////////////////////////////////////
// Property "basic"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the <code>basic</code> property.
   * @see niagara.forum.eu.async.BThreadPoolService#getBasic
   * @see niagara.forum.eu.async.BThreadPoolService#setBasic
   */
  public static final Property basic = newProperty(0, new BThreadPool(),null);
  
  /**
   * Get the <code>basic</code> property.
   * @see niagara.forum.eu.async.BThreadPoolService#basic
   */
  public BThreadPool getBasic() { return (BThreadPool)get(basic); }
  
  /**
   * Set the <code>basic</code> property.
   * @see niagara.forum.eu.async.BThreadPoolService#basic
   */
  public void setBasic(BThreadPool v) { set(basic,v,null); }

////////////////////////////////////////////////////////////////
// Property "scheduled"
////////////////////////////////////////////////////////////////
  
  /**
   * Slot for the <code>scheduled</code> property.
   * @see niagara.forum.eu.async.BThreadPoolService#getScheduled
   * @see niagara.forum.eu.async.BThreadPoolService#setScheduled
   */
  public static final Property scheduled = newProperty(0, new BScheduledThreadPool(),null);
  
  /**
   * Get the <code>scheduled</code> property.
   * @see niagara.forum.eu.async.BThreadPoolService#scheduled
   */
  public BScheduledThreadPool getScheduled() { return (BScheduledThreadPool)get(scheduled); }
  
  /**
   * Set the <code>scheduled</code> property.
   * @see niagara.forum.eu.async.BThreadPoolService#scheduled
   */
  public void setScheduled(BScheduledThreadPool v) { set(scheduled,v,null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////
  
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BThreadPoolService.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  public BThreadPoolService()
  {
  }

  public Type[] getServiceTypes()
  {
    return new Type[] { TYPE };
  }

  public void serviceStarted() throws Exception
  {
  }

  public void serviceStopped() throws Exception
  {
  }
}
