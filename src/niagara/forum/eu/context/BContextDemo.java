// Copyright (c) 2013
// Licensed under the Academic Free License 3.0

package niagara.forum.eu.context;

import javax.baja.sys.Action;
import javax.baja.sys.BComponent;
import javax.baja.sys.Context;
import javax.baja.sys.Flags;
import javax.baja.sys.Property;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.util.Lexicon;

/**
 * @author: Matthew Giannini
 */
public class BContextDemo extends BComponent
{
  /*-
    class BContextDemo
    {
      properties
      {
        lastInvocationUser : String
          default {[ "?" ]}
          flags { summary, transient, readonly }
      }
      actions
      {
        myAction()
      }
    }
   -*/
/*+ ------------ BEGIN BAJA AUTO GENERATED CODE ------------ +*/
/*@ $niagara.forum.eu.context.BContextDemo(364031849)1.0$ @*/
/* Generated Mon Mar 25 11:53:10 EDT 2013 by Slot-o-Matic 2000 (c) Tridium, Inc. 2000 */

////////////////////////////////////////////////////////////////
// Property "lastInvocationUser"
////////////////////////////////////////////////////////////////

  /**
   * Slot for the <code>lastInvocationUser</code> property.
   * @see niagara.forum.eu.context.BContextDemo#getLastInvocationUser
   * @see niagara.forum.eu.context.BContextDemo#setLastInvocationUser
   */
  public static final Property lastInvocationUser = newProperty(Flags.SUMMARY|Flags.TRANSIENT|Flags.READONLY, "?",null);

  /**
   * Get the <code>lastInvocationUser</code> property.
   * @see niagara.forum.eu.context.BContextDemo#lastInvocationUser
   */
  public String getLastInvocationUser() { return getString(lastInvocationUser); }

  /**
   * Set the <code>lastInvocationUser</code> property.
   * @see niagara.forum.eu.context.BContextDemo#lastInvocationUser
   */
  public void setLastInvocationUser(String v) { setString(lastInvocationUser,v,null); }

////////////////////////////////////////////////////////////////
// Action "myAction"
////////////////////////////////////////////////////////////////

  /**
   * Slot for the <code>myAction</code> action.
   * @see niagara.forum.eu.context.BContextDemo#myAction()
   */
  public static final Action myAction = newAction(0,null);

  /**
   * Invoke the <code>myAction</code> action.
   * @see niagara.forum.eu.context.BContextDemo#myAction
   */
  public void myAction() { invoke(myAction,null,null); }

////////////////////////////////////////////////////////////////
// Type
////////////////////////////////////////////////////////////////

  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BContextDemo.class);

/*+ ------------ END BAJA AUTO GENERATED CODE -------------- +*/

  private static final Lexicon lex = Lexicon.make(BContextDemo.class);

  public void doMyAction(Context cx)
  {
    if (cx == null)
      setLastInvocationUser(lex.getText("context.demo.noContext"));
    else if (cx.getUser() == null)
      setLastInvocationUser(lex.getText("context.demo.noUser"));
    else
      setLastInvocationUser(lex.getText("context.demo.user",
        new Object[]{cx.getUser().getUsername()}));
  }
}
