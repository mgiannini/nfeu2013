// Copyright (c) 2013
// Licensed under the Academic Free License 3.0

package niagara.forum.eu.async;

import javax.baja.spy.SpyWriter;
import javax.baja.sys.BComponent;
import javax.baja.sys.BIcon;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

import com.tridium.util.backport.concurrent.ThreadPoolExecutor;

/**
 * @author: Matthew Giannini
 */
public abstract class BAbstractThreadPool extends BComponent
{
  /*-
    class BAbstractThreadPOol
    {
    }
   -*/
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $niagara.forum.eu.async.BAbstractThreadPool(673347704)1.0$ @*/
/* Generated Wed Mar 06 14:50:57 EST 2013 by Slot-o-Matic 2000 (c) Tridium, Inc. 2000 */

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////

  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BAbstractThreadPool.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  protected abstract ThreadPoolExecutor getExecutor();

  public final void purge()
  {
    getExecutor().purge();
  }

  /**
   * Forces a shutodown of the executor
   */
  public void stopped() throws Exception
  {
    super.stopped();
    getExecutor().shutdownNow();
  }

  public void spy(SpyWriter out) throws Exception
  {
    super.spy(out);
    ThreadPoolExecutor executor = getExecutor();
    out.startProps("General Thread Pool Spy");
    out.prop("Core Pool Size", executor.getCorePoolSize());
    out.prop("Current Pool Size", executor.getPoolSize());
    out.prop("# Working Threads", executor.getActiveCount());
    out.prop("Largest Pool Size", executor.getLargestPoolSize());
    out.prop("Approx. # Tasks Completed", executor.getCompletedTaskCount());
    out.endProps();
  }

  public BIcon getIcon() { return icon; }
  private static final BIcon icon = BIcon.std("gears.png");
}
