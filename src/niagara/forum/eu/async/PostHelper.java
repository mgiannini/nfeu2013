// Copyright (c) 2013
// Licensed under the Academic Free License 3.0

package niagara.forum.eu.async;

import javax.baja.sys.Action;
import javax.baja.sys.BComponent;
import javax.baja.sys.BValue;
import javax.baja.sys.Context;
import javax.baja.sys.ServiceNotFoundException;
import javax.baja.util.IFuture;

import com.tridium.sys.Nre;
import com.tridium.util.backport.concurrent.atomic.AtomicBoolean;

/**
 * @author: Matthew Giannini
 */
public class PostHelper
{
  private final BComponent instance;
  private final Action action;
  private final boolean coalesceInvocations;
  private final AtomicBoolean isInvocableState;

  public PostHelper(BComponent instance, Action action)
  {
    this(instance, action, false);
  }

  public PostHelper(BComponent instance, Action action, boolean coalesceInvocations)
  {
    this.instance = instance;
    this.action = action;
    this.coalesceInvocations = coalesceInvocations;
    this.isInvocableState = new AtomicBoolean(true);
  }

  public IFuture post(final BValue arg, final Context cx)
  {
    try
    {
      BThreadPoolService service =
        (BThreadPoolService)Nre.serviceManager.getService(BThreadPoolService.TYPE.toString());
      if (!coalesceInvocations ||
         (coalesceInvocations && isInvocableState.compareAndSet(true, false)))
      {
        service.getBasic().submit(new Runnable() {
          public void run()
          {
            System.out.println("PostHelper Executing " + action.getName());
            instance.doInvoke(action, arg, cx);
            isInvocableState.set(true);
          }
        });
      }
    }
    catch (ServiceNotFoundException e)
    {
      e.printStackTrace();
      // just revert to control engine thread :-(
      Nre.engineManager.enqueueAction(instance, action, arg);
    }
    return null;
  }
}
