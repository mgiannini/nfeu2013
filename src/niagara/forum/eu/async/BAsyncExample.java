// Copyright (c) 2013
// Licensed under the Academic Free License 3.0

package niagara.forum.eu.async;

import javax.baja.sys.Action;
import javax.baja.sys.BComponent;
import javax.baja.sys.BInteger;
import javax.baja.sys.BValue;
import javax.baja.sys.Context;
import javax.baja.sys.Flags;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.util.IFuture;
import javax.baja.util.Invocation;
import javax.baja.util.Queue;
import javax.baja.util.Worker;

/**
 * @author: Matthew Giannini
 */
public class BAsyncExample extends BComponent
{
  /*-
    class BAsyncExample
    {
      properties
      {
        a1: int
          default {[ 0 ]}
          flags { readonly, summary, transient }
      }
      actions
      {
        async1()
          flags { async }

        async2()
          flags { async }

        async3Coalesced()
          flags { async }

        async2Times(times: BInteger)
          default {[ BInteger.make(1) ]}

        async3Times(times: BInteger)
          default {[ BInteger.make(1) ]}
      }
    }
   -*/
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $niagara.forum.eu.async.BAsyncExample(474692739)1.0$ @*/
/* Generated Fri Apr 05 08:38:43 EDT 2013 by Slot-o-Matic 2000 (c) Tridium, Inc. 2000 */

////////////////////////////////////////////////////////////////
// Property "a1"
////////////////////////////////////////////////////////////////

  /**
   * Slot for the <code>a1</code> property.
   * @see niagara.forum.eu.async.BAsyncExample#getA1
   * @see niagara.forum.eu.async.BAsyncExample#setA1
   */
  public static final Property a1 = newProperty(Flags.READONLY|Flags.SUMMARY|Flags.TRANSIENT, 0,null);

  /**
   * Get the <code>a1</code> property.
   * @see niagara.forum.eu.async.BAsyncExample#a1
   */
  public int getA1() { return getInt(a1); }

  /**
   * Set the <code>a1</code> property.
   * @see niagara.forum.eu.async.BAsyncExample#a1
   */
  public void setA1(int v) { setInt(a1,v,null); }

////////////////////////////////////////////////////////////////
// Action "async1"
////////////////////////////////////////////////////////////////

  /**
   * Slot for the <code>async1</code> action.
   * @see niagara.forum.eu.async.BAsyncExample#async1()
   */
  public static final Action async1 = newAction(Flags.ASYNC,null);

  /**
   * Invoke the <code>async1</code> action.
   * @see niagara.forum.eu.async.BAsyncExample#async1
   */
  public void async1() { invoke(async1,null,null); }

////////////////////////////////////////////////////////////////
// Action "async2"
////////////////////////////////////////////////////////////////

  /**
   * Slot for the <code>async2</code> action.
   * @see niagara.forum.eu.async.BAsyncExample#async2()
   */
  public static final Action async2 = newAction(Flags.ASYNC,null);

  /**
   * Invoke the <code>async2</code> action.
   * @see niagara.forum.eu.async.BAsyncExample#async2
   */
  public void async2() { invoke(async2,null,null); }

////////////////////////////////////////////////////////////////
// Action "async3Coalesced"
////////////////////////////////////////////////////////////////

  /**
   * Slot for the <code>async3Coalesced</code> action.
   * @see niagara.forum.eu.async.BAsyncExample#async3Coalesced()
   */
  public static final Action async3Coalesced = newAction(Flags.ASYNC,null);

  /**
   * Invoke the <code>async3Coalesced</code> action.
   * @see niagara.forum.eu.async.BAsyncExample#async3Coalesced
   */
  public void async3Coalesced() { invoke(async3Coalesced,null,null); }

////////////////////////////////////////////////////////////////
// Action "async2Times"
////////////////////////////////////////////////////////////////

  /**
   * Slot for the <code>async2Times</code> action.
   * @see niagara.forum.eu.async.BAsyncExample#async2Times()
   */
  public static final Action async2Times = newAction(0,BInteger.make(1),null);

  /**
   * Invoke the <code>async2Times</code> action.
   * @see niagara.forum.eu.async.BAsyncExample#async2Times
   */
  public void async2Times(BInteger times) { invoke(async2Times,times,null); }

////////////////////////////////////////////////////////////////
// Action "async3Times"
////////////////////////////////////////////////////////////////

  /**
   * Slot for the <code>async3Times</code> action.
   * @see niagara.forum.eu.async.BAsyncExample#async3Times()
   */
  public static final Action async3Times = newAction(0,BInteger.make(1),null);

  /**
   * Invoke the <code>async3Times</code> action.
   * @see niagara.forum.eu.async.BAsyncExample#async3Times
   */
  public void async3Times(BInteger times) { invoke(async3Times,times,null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////

  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BAsyncExample.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  private final Worker workerAsync1 = new Worker(new Queue(128));

  // To get async action to be coalescable, use CoalesceQueue
  //
  //private final Worker workerAsync1 = new Worker(new CoalesceQueue(128));


  private final PostHelper postAsync2 = new PostHelper(this, async2);
  private final PostHelper postAsync3 = new PostHelper(this, async3Coalesced, true);

  public BAsyncExample()
  {
  }

  public void started() throws Exception
  {
    super.started();
    workerAsync1.start("Async1-Worker");
  }

  public void stopped() throws Exception
  {
    super.stopped();
    workerAsync1.stop();
  }

  public IFuture post(Action action, BValue argument, Context cx)
  {
    if (async1 == action)
    {
      ((Queue)workerAsync1.getTodo()).enqueue(new Invocation(this, action, argument, cx));
      return null;
    }
    else if (async2 == action)
    {
      return postAsync2.post(argument, cx);
    }
    else if (async3Coalesced == action)
    {
      return postAsync3.post(argument, cx);
    }
    return super.post(action, argument, cx);
  }

////////////////////////////////////////////////////////////////
// Actions
////////////////////////////////////////////////////////////////

  public void doAsync1(Context cx)
  {
    setA1(getA1() + 1);
  }

  public void doAsync2(Context cx)
  {
    setA1(getA1() + 2);
  }

  public void doAsync3Coalesced(Context cx)
  {
    setA1(getA1() + 100);
    try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
  }

  public void doAsync2Times(BInteger times, Context cx)
  {
    System.out.println("Invoke async2 " + times + " times");
    for (int i=0; i<times.getInt(); ++i)
      async2();
  }
  public void doAsync3Times(BInteger times, Context cx)
  {
    System.out.println("Invoke async3 " + times + " times");
    for (int i=0; i<times.getInt(); ++i)
      async3Coalesced();
  }
}
