// Copyright (c) 2013
// Licensed under the Academic Free License 3.0

package niagara.forum.eu.async;

import javax.baja.sys.BFacets;
import javax.baja.sys.BRelTime;
import javax.baja.sys.Context;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

import com.tridium.util.backport.concurrent.BlockingQueue;
import com.tridium.util.backport.concurrent.Callable;
import com.tridium.util.backport.concurrent.Future;
import com.tridium.util.backport.concurrent.LinkedBlockingDeque;
import com.tridium.util.backport.concurrent.ThreadPoolExecutor;
import com.tridium.util.backport.concurrent.TimeUnit;

/**
 * @author: Matthew Giannini
 */
public class BThreadPool extends BAbstractThreadPool
{
  /*-
    class BThreadPool
    {
      properties
      {
         maximumPoolSize: int
           default {[ 4 ]}
           slotfacets {[ MIN = 1 ]}

         keepAliveTime: BRelTime
           default {[ BRelTime.makeSeconds(60) ]}
           slotfacets {[ MIN = BRelTime.makeSeconds(0) ]}
      }
    }
   -*/
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $niagara.forum.eu.async.BThreadPool(2483114815)1.0$ @*/
/* Generated Wed Mar 06 14:03:51 EST 2013 by Slot-o-Matic 2000 (c) Tridium, Inc. 2000 */

////////////////////////////////////////////////////////////////
// Property "maximumPoolSize"
////////////////////////////////////////////////////////////////

  /**
   * Slot for the <code>maximumPoolSize</code> property.
   * @see niagara.forum.eu.async.BThreadPool#getMaximumPoolSize
   * @see niagara.forum.eu.async.BThreadPool#setMaximumPoolSize
   */
  public static final Property maximumPoolSize = newProperty(0, 4,BFacets.make(BFacets.MIN,1));

  /**
   * Get the <code>maximumPoolSize</code> property.
   * @see niagara.forum.eu.async.BThreadPool#maximumPoolSize
   */
  public int getMaximumPoolSize() { return getInt(maximumPoolSize); }

  /**
   * Set the <code>maximumPoolSize</code> property.
   * @see niagara.forum.eu.async.BThreadPool#maximumPoolSize
   */
  public void setMaximumPoolSize(int v) { setInt(maximumPoolSize,v,null); }

////////////////////////////////////////////////////////////////
// Property "keepAliveTime"
////////////////////////////////////////////////////////////////

  /**
   * Slot for the <code>keepAliveTime</code> property.
   * @see niagara.forum.eu.async.BThreadPool#getKeepAliveTime
   * @see niagara.forum.eu.async.BThreadPool#setKeepAliveTime
   */
  public static final Property keepAliveTime = newProperty(0, BRelTime.makeSeconds(60),BFacets.make(BFacets.MIN,BRelTime.makeSeconds(0)));

  /**
   * Get the <code>keepAliveTime</code> property.
   * @see niagara.forum.eu.async.BThreadPool#keepAliveTime
   */
  public BRelTime getKeepAliveTime() { return (BRelTime)get(keepAliveTime); }

  /**
   * Set the <code>keepAliveTime</code> property.
   * @see niagara.forum.eu.async.BThreadPool#keepAliveTime
   */
  public void setKeepAliveTime(BRelTime v) { set(keepAliveTime,v,null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////

  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BThreadPool.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  protected final ThreadPoolExecutor executor;
  protected final BlockingQueue workQueue;

  public BThreadPool()
  {
    workQueue = new LinkedBlockingDeque();
    executor = new ThreadPoolExecutor(0,
      getMaximumPoolSize(),
      getKeepAliveTime().getSeconds(),
      TimeUnit.SECONDS,
      workQueue);
    executor.allowCoreThreadTimeOut(true);
  }

  protected ThreadPoolExecutor getExecutor()
  {
    return executor;
  }

  public void execute(Runnable command)
  {
    executor.execute(command);
  }

  public Future submit(Runnable task)
  {
    return executor.submit(task);
  }

  public Future submit(Runnable task, Object result)
  {
    return executor.submit(task, result);
  }

  public Future submit(Callable task)
  {
    return executor.submit(task);
  }

  public void changed(Property property, Context context)
  {
    super.changed(property, context);
    if (!isRunning())
      return;
    if (property == maximumPoolSize)
      executor.setMaximumPoolSize(getMaximumPoolSize());
    else if (property == keepAliveTime)
      executor.setKeepAliveTime(getKeepAliveTime().getSeconds(), TimeUnit.SECONDS);
  }
}
