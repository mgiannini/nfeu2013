// Copyright (c) 2013
// Licensed under the Academic Free License 3.0

package niagara.forum.eu.async;

import javax.baja.sys.BFacets;
import javax.baja.sys.Context;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

import com.tridium.util.backport.concurrent.Callable;
import com.tridium.util.backport.concurrent.ScheduledFuture;
import com.tridium.util.backport.concurrent.ScheduledThreadPoolExecutor;
import com.tridium.util.backport.concurrent.ThreadPoolExecutor;
import com.tridium.util.backport.concurrent.TimeUnit;

/**
 * @author: Matthew Giannini
 */
public class BScheduledThreadPool extends BAbstractThreadPool
{
  /*-
    class BScheduledThreadPool
    {
      properties
      {
        corePoolSize: int
          -- The number of threads to keep in the pool; even if they are idle.
          default {[ 4 ]}
          slotfacets {[ MIN = 1 ]}

      }
    }
   -*/
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $niagara.forum.eu.async.BScheduledThreadPool(2757748324)1.0$ @*/
/* Generated Wed Mar 06 14:35:09 EST 2013 by Slot-o-Matic 2000 (c) Tridium, Inc. 2000 */

////////////////////////////////////////////////////////////////
// Property "corePoolSize"
////////////////////////////////////////////////////////////////

  /**
   * Slot for the <code>corePoolSize</code> property.
   * The number of threads to keep in the pool; even if
   * they are idle.
   * @see niagara.forum.eu.async.BScheduledThreadPool#getCorePoolSize
   * @see niagara.forum.eu.async.BScheduledThreadPool#setCorePoolSize
   */
  public static final Property corePoolSize = newProperty(0, 4, BFacets.make(BFacets.MIN, 1));

  /**
   * Get the <code>corePoolSize</code> property.
   * The number of threads to keep in the pool; even if
   * they are idle.
   * @see niagara.forum.eu.async.BScheduledThreadPool#corePoolSize
   */
  public int getCorePoolSize() { return getInt(corePoolSize); }

  /**
   * Set the <code>corePoolSize</code> property.
   * The number of threads to keep in the pool; even if
   * they are idle.
   * @see niagara.forum.eu.async.BScheduledThreadPool#corePoolSize
   */
  public void setCorePoolSize(int v) { setInt(corePoolSize,v,null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////

  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BScheduledThreadPool.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  protected final ScheduledThreadPoolExecutor executor;

  public BScheduledThreadPool()
  {
    executor = new ScheduledThreadPoolExecutor(getCorePoolSize());
    executor.setRemoveOnCancelPolicy(true);
  }

  protected ThreadPoolExecutor getExecutor()
  {
    return executor;
  }

  public ScheduledFuture schedule(Runnable command, long delay, TimeUnit unit)
  {
    return executor.schedule(command, delay, unit);
  }

  public ScheduledFuture schedule(Callable callable, long delay, TimeUnit unit)
  {
    return executor.schedule(callable, delay, unit);
  }

  public ScheduledFuture scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)
  {
    return executor.scheduleAtFixedRate(command, initialDelay, period, unit);
  }

  public ScheduledFuture scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)
  {
    return executor.scheduleWithFixedDelay(command, initialDelay, delay, unit);
  }

  public void changed(Property property, Context context)
  {
    super.changed(property, context);
    if (!isRunning())
      return;
    if (property == corePoolSize)
      setCorePoolSize(getCorePoolSize());
  }
}
